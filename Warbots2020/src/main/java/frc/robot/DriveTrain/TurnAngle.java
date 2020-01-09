/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.DriveTrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot.*;

public class TurnAngle extends CommandBase {
  /**
   * Creates a new TurnAngle.
   */
  public TurnAngle(double degrees, double tolerance) 
  {
    degreesToTurn = degrees;
    angleTolerance = tolerance;
    addRequirements(RobotContainer.driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    RobotContainer.driveTrain.resetYaw();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    RobotContainer.driveTrain.arcadeInput(0, .25 * getRemainingAngle() / Math.abs(getRemainingAngle()));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    return Math.abs(getRemainingAngle()) < angleTolerance;
  }

  private double getRemainingAngle()
  {
    return Math.abs(RobotContainer.driveTrain.getYaw()) - Math.abs(degreesToTurn);
  }

  private final double degreesToTurn; //clockwise - positive; counterclockwise - negative
  private final double angleTolerance;
}
