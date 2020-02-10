/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveWithJoysticks extends DriveCommand 
{
  //region Constructors
  public DriveWithJoysticks(DriveTrain dt, XboxController driverXboxController) 
  {
    super(dt);
    driverXbox = driverXboxController;
  }
  //endregion

  //region Overrides
  @Override
  public void execute() 
  {
    driveTrain.curvatureInput(driverXbox.getY(Hand.kLeft), -1 * driverXbox.getX(Hand.kLeft), 
    driverXbox.getAButton());
    SmartDashboard.putNumber("Motor Temperature", driveTrain.getAvgMotorTemp());
  }

  @Override
  public boolean isFinished() 
  {
    return false;
  }
  //endregion

  //region Field
  protected XboxController driverXbox;
  //endregion
}





















