/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.climber.Climber;
import frc.robot.driveTrain.DriveForward;
import frc.robot.driveTrain.DriveTrain;
import frc.robot.driveTrain.DriveWithJoysticks;
import frc.robot.driveTrain.SitStill;
import frc.robot.driveTrain.TestAutoCommand;
import frc.robot.intake.Intake;
import frc.robot.shooter.Fire;
import frc.robot.shooter.Shooter;

public class RobotContainer 
{
  //region Constructors
  public RobotContainer()
  {
    keys = new KeyBinder(this);
    driveTrain = new DriveTrain();
    driveWithJoysticks = new DriveWithJoysticks(driveTrain, keys.driver);
    driveTrain.setDefaultCommand(driveWithJoysticks);
    sitTight = new SitStill(driveTrain);
    driveDistance = new DriveForward(driveTrain, -20); //TODO: figure out all of the trickle down negatives and fix them
    test = new TestAutoCommand(driveTrain);
    climber = new Climber();
    shooter = new Shooter();
    intake = new Intake();
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
  //endregion

  //region Commands
  public SitStill sitTight;
  public DriveWithJoysticks driveWithJoysticks;
  public DriveForward driveDistance;
  public TestAutoCommand test;
  public Fire fire;
  //endregion
}
