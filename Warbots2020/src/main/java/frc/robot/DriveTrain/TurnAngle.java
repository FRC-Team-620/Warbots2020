/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.DriveTrain;

public class TurnAngle extends DriveCommand 
{
  //region Constructors
  public TurnAngle(DriveTrain dt, double degrees, double tolerance) 
  {
    super(dt);
    degreesToTurn = degrees;
    angleTolerance = tolerance;
  }
  //endregion

  //region Overrides
  @Override
  public void initialize() 
  {
    driveTrain.resetYaw();
  }

  @Override
  public void execute() 
  {
    driveTrain.arcadeInput(0, .25 * getRemainingAngle() / Math.abs(getRemainingAngle()));
  }

  @Override
  public void end(boolean interrupted) 
  {
  }

  @Override
  public boolean isFinished() 
  {
    return Math.abs(getRemainingAngle()) < angleTolerance;
  }
  //endregion

  //region Methods
  private double getRemainingAngle()
  {
    return Math.abs(driveTrain.getYaw()) - Math.abs(degreesToTurn);
  }
  //endregion

  //region Fields
  private final double degreesToTurn; //clockwise - positive; counterclockwise - negative
  private final double angleTolerance;
  //endregion
}
