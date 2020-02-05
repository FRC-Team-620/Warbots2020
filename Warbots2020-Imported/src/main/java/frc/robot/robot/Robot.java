/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.driveTrain.*;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot 
{
  //region Overrides
  @Override
  public void robotInit() 
  {
    keys = new KeyBinder(this);

    driveTrain = new DriveTrain();
    driveWithJoysticks = new DriveWithJoysticks(driveTrain, keys.driver);
    driveTrain.setDefaultCommand(driveWithJoysticks);
    sitTight = new SitStill(driveTrain);
    driveDistance = new DriveForward(driveTrain, 10);
    test = new TestAutoCommand(driveTrain);
  }

  @Override
  public void robotPeriodic() 
  {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void autonomousInit()
  {
    driveDistance.schedule();
  }

  @Override
  public void teleopInit() 
  {
    sitTight.cancel();
    driveWithJoysticks.schedule();
  }
  //endregion

  //region OI
  public KeyBinder keys;
  //endregion
  
  //region Subsystems
  public DriveTrain driveTrain;
  //endregion
  
  //region Commands
  protected SitStill sitTight;
  protected DriveWithJoysticks driveWithJoysticks;
  protected DriveForward driveDistance;
  protected TestAutoCommand test;
  //endregion
}