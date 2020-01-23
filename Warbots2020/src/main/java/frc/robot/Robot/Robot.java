/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.driveTrain.*;

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
  }

  @Override
  public void robotPeriodic() 
  {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void autonomousInit()
  {
    sitTight.schedule();
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
  protected Command autonomousCommand;

  protected SitStill sitTight;
  protected DriveWithJoysticks driveWithJoysticks;
  //endregion
}
