/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveStraightTwo extends CommandBase 
{
  public DriveStraightTwo(DriveTrain dt) 
  {
    addRequirements(dt);
    driveTrain = dt;
  }

  @Override
  public void initialize() 
  {
    driveTrain.resetDistance();
  }

  @Override
  public void execute() 
  {
    driveTrain.arcadeInput(-.75, 0);
  }

  @Override
  public void end(boolean interrupted) 
  {
    driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    //8.4 rev neo brushless theoretically equals 1 rev colson
    return Math.abs(driveTrain.getDistance()) >= 181;
  }

  public final DriveTrain driveTrain;
}