/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.drivetrain;

public class SitStill extends DriveCommand 
{
  //region Constructors
  public SitStill(DriveTrain dt) 
  {
    super(dt);
  }
  //endregion

  //region Overrides
  @Override
  public boolean isFinished() 
  {
    return false;
  }
  //endregion
}
