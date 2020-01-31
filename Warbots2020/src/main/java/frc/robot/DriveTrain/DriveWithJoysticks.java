/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.DriveTrain;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

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
    driveTrain.curvatureInput(driverXbox.getY(Hand.kLeft), -1 * driverXbox.getX(Hand.kLeft));
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
