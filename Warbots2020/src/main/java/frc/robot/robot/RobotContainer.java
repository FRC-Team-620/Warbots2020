/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.climber.*;
import frc.robot.driveTrain.*;
import frc.robot.intake.*;
import frc.robot.shooter.*;

public class RobotContainer 
{
  //region Constructors
  public RobotContainer()
  {
    //utilities and drivetrain creation
    keys = new KeyBinder(this);
    driveTrain = new DriveTrain();
    driveWithJoysticks = new DriveWithJoysticks(driveTrain, keys.driver);
    driveTrain.setDefaultCommand(driveWithJoysticks);
    sitTight = new SitStill(driveTrain);
    driveDistance = new DriveForward(driveTrain, -20); //TODO: figure out all of the trickle down negatives and fix them
    test = new TestAutoCommand(driveTrain);
    
    //subsystem creation
    climber = new Climber();
    shooter = new Shooter();
    intake = new Intake();
    loader = new Loader();

    //command creation
    load = new Load(loader);
    spinUp = new SpinUp(shooter, .2);
  }
  //endregion

  public Command getAutonomousCommand() 
  {
    return test;
  }

  //region OI
  public KeyBinder keys;
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
