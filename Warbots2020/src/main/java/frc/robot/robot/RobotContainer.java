/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.climber.*;
import frc.robot.drivetrain.*;
import frc.robot.intake.*;
import frc.robot.shooter.*;
import frc.robot.loader.*;

public class RobotContainer 
{
  //region Constructors
  public RobotContainer()
  {
    //utilities and drivetrain creation
    keyBinder = new KeyBinder(this);

    driveTrain = new DriveTrain();
    driveWithJoysticks = new DriveWithJoysticks(driveTrain, keyBinder.driver);
    driveTrain.setDefaultCommand(driveWithJoysticks);
    driveDistance = new DriveForward(driveTrain, -20); //TODO: figure out all of the trickle down negatives and fix them
    
    sitTight = new SitStill(driveTrain);
    test = new TestAutoCommand(driveTrain);
    
    //subsystem creation
    climber = new Climber();
    shooter = new Shooter();
    intake = new Intake();
    loader = new Loader();
  
    //command creation
    spinUp = new SpinUp(shooter, .2);
  }
  //endregion

  public Command getAutonomousCommand() 
  {
    return test;
  }

  //region OI
  public KeyBinder keyBinder;
  //endregion

  //region Subsystems
  public final DriveTrain driveTrain;
  public final Climber climber; 
  public final Intake intake; 
  public final Shooter shooter; 
  public final Loader loader;
  //endregion

  //region Commands
  public SitStill sitTight;
  public DriveWithJoysticks driveWithJoysticks;
  public DriveForward driveDistance;
  public TestAutoCommand test;
  public Load load;
  public SpinUp spinUp;
  //endregion
}
