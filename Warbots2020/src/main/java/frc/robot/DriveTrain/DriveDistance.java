/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.DriveTrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.robot.*;

public class DriveDistance extends CommandBase 
{
  /**
   * Creates a new DriveDistandce.
   */
  public DriveDistance(int distance) 
  {
    distanceToTravel = distance;
    addRequirements(RobotContainer.driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    RobotContainer.driveTrain.arcadeInput(.5, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    return RobotContainer.driveTrain.distanceTraveled() > distanceToTravel;
  }

  private final double distanceToTravel;
}
