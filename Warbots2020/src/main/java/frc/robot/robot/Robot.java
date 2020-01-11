/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.OI.KeyBinder;
import frc.robot.DriveTrain.DriveTrain;
import frc.robot.DriveTrain.DriveWithJoysticks;
import frc.robot.DriveTrain.SitStill;

public class Robot extends TimedRobot 
{
  //region Overrides
  @Override
  public void robotInit()
  {
    InitializeFields();
  }

  @Override
  public void robotPeriodic() 
  {
    CommandScheduler.getInstance().run();
  }
  
  @Override
  public void autonomousInit() 
  {
    autonomousCommand = sitTight;

    if (autonomousCommand != null) 
    {
      autonomousCommand.schedule();
    }
  }

  @Override
  public void teleopInit() 
  {
    if (autonomousCommand != null) 
    {
      autonomousCommand.cancel();
    }

    driveWithJoysticks.schedule();
  }

  @Override
  public void testInit() 
  {
    CommandScheduler.getInstance().cancelAll();
  }
  //endregion

  //region Methods
  protected void InitializeFields()
  {
    bot = this;
    keys = new KeyBinder(bot);
    driverXBox = new XboxController(0);
    driveTrain = new DriveTrain();
    driveWithJoysticks = new DriveWithJoysticks(driveTrain, keys.driver);
    driveTrain.setDefaultCommand(driveWithJoysticks);
    sitTight = new SitStill(driveTrain);
  }
  //endregion

  //region OI
  public KeyBinder keys;
  protected XboxController driverXBox;
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
