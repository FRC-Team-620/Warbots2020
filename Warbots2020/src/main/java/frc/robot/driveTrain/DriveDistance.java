/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.driveTrain;


public class DriveDistance extends DriveCommand 
{
  public DriveDistance(DriveTrain dt, double dist) 
  {
    super(dt);
    distance = dist;
  }

  @Override
  public void execute()
  {
    driveTrain.arcadeInput(.25, 0);
  }

  @Override
  public boolean isFinished()
  {
    return false; //var avg = driveTrain.
  }

  private double distance;
}
