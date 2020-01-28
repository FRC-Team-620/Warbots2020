/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.driveTrain;


public class DriveDistance extends DriveCommand 
{
  //region Constructors
  public DriveDistance(DriveTrain dt, double dist) 
  {
    super(dt);
    distance = dist;
  }
  //endregion

  //region Overrides
  @Override
  public void initialize() 
  {
    //driveTrain.distanceTraveled.apply(true);
  }

  @Override
  public void execute()
  {
    driveTrain.arcadeInput(.75, 0);
  }

  @Override
  public boolean isFinished()
  {
    return false;//driveTrain.distanceTraveled.apply(false) > distance;
  }
  //endregion

  //region Fields
  private double distance;
  //endregion
}
