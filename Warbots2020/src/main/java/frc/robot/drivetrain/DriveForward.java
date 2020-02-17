/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.drivetrain;

import frc.robot.subsystems.DriveTrain;

public class DriveForward extends DriveCommand 
{
  //region Constructors
  public DriveForward(DriveTrain dt, double dist) 
  {
    super(dt);
    distance = dist;
  }
  //endregion

  //region Overrides
  @Override
  public void initialize() 
  {
    driveTrain.resetDistance();
  }

  @Override
  public void execute()
  {
    i++;
    driveTrain.curvatureInput(-.75, 0, false);
  }

  @Override
  public boolean isFinished()
  {
    return Math.abs(driveTrain.getDistance() - distance) < 2.0;
  }

  @Override
  public void end(boolean interrupted)
  {
    driveTrain.stop();
    driveTrain.resetDistance();
  }
  //endregion

  //region Fields
  private double distance;
  int i = 0;
  //endregion
}
