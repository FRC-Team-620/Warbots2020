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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot 
{
  //region Overrides
  @Override
  public void robotInit() 
  {
    robotContainer = new RobotContainer();
    keyBinder = new KeyBinder(robotContainer);
  }

  @Override
  public void robotPeriodic() 
  {
    CommandScheduler.getInstance().run();
  }


  @Override
  public void autonomousInit()
  {
    autonomousCommand = robotContainer.getAutonomousCommand();
    if (autonomousCommand != null) autonomousCommand.schedule();
    robotContainer.driveDistance.schedule();
  }

  @Override
  public void autonomousPeriodic() 
  {
    SmartDashboard.putBoolean("Digital Input 0", robotContainer.keyBinder.digitalInput0.get());
    SmartDashboard.putBoolean("Digital Input 1", robotContainer.keyBinder.digitalInput1.get());
    SmartDashboard.putBoolean("Digital Input 2", robotContainer.keyBinder.digitalInput2.get());
    SmartDashboard.putBoolean("Digital Input 3", robotContainer.keyBinder.digitalInput3.get());
   }

  @Override
  public void teleopInit() 
  {
    robotContainer.sitTight.cancel();               //TODO this is not necessary - get the autonomous command and cancel it 
    robotContainer.driveWithJoysticks.schedule();   //TODO scheduling driveWithJoysticks is not required - it is a default command
  }

  @Override
  public void testInit()
  {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }
  //endregion

  //region Unused Overrides
  @Override
  public void disabledInit() 
  {
  }

  @Override
  public void disabledPeriodic() 
  {
  }
  
  @Override
  public void teleopPeriodic() 
  {
  }

  @Override
  public void testPeriodic() 
  {
  }
  //endregion

  //region Fields
  private Command autonomousCommand;
  private RobotContainer robotContainer;
  private KeyBinder keyBinder;
  //endregion
}