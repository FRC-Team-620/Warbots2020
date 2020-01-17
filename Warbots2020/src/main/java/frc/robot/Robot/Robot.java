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
  private Command m_autonomousCommand;

  @Override
  public void robotInit() 
  {
    bot = this;
    keys = new KeyBinder(bot);
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
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit()
  {
    if (m_autonomousCommand != null) 
    {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() 
  {
  }

  @Override
  public void teleopInit() 
  {
    if (m_autonomousCommand != null) 
    {
      m_autonomousCommand.cancel();
    }
    driveWithJoysticks.schedule();
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() 
  {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
  }

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

  //region Fields
  static Robot bot;
  //endregion
}
