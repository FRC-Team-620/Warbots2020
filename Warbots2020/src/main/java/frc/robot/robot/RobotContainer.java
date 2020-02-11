/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.climber.*;
import frc.robot.loader.*;
import frc.robot.intake.*;
import frc.robot.shooter.*;
import frc.robot.drivetrain.*;

public class RobotContainer 
{
  public RobotContainer()
  {
    //subsystem creation
    drivetrain = new DriveTrain();
    climber = new Climber();
    shooter = new Shooter();
    intake = new Intake();
    loader = new Loader();
   
    // commands
    driveWithJoysticks = new DriveWithJoysticks(drivetrain, keyBinder.driver);
    testAutoCommand = new TestAutoCommand(drivetrain);
    sitTight = new SitStill(drivetrain);
    driveDistance = new DriveForward(drivetrain, -20); //TODO: figure out all of the trickle down negatives and fix them
    spinUp = new SpinUp(shooter, .2);
    
    //default commands
    drivetrain.setDefaultCommand(driveWithJoysticks);
  }

  public Command getAutonomousCommand() 
  {
    return testAutoCommand;
  }

  //subsystems
  public final DriveTrain drivetrain;
  public final Climber climber; 
  public final Intake intake; 
  public final Shooter shooter; 
  public final Loader loader;
 
  //commands
  public DriveWithJoysticks driveWithJoysticks; //drivetrain
  public Extend extend;   // climber
  public Retract retract; // climber
  public Capture capture; // intake
  public Eject eject;     // intake
  public SpinUp spinUp;   // shooter
  public Load load;       // loader

  public TestAutoCommand testAutoCommand; // autonomous
  public SitStill sitTight;               // autonomous
  public DriveForward driveDistance;      // autonomous
  
  //OI
  public KeyBinder keyBinder;
}
