/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.shooter;

import java.time.LocalDateTime;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.robot.Constants;
import frc.robot.subsystems.Shooter;

public class Stuff extends CommandBase 
{
  Shooter shoot;
  LocalDateTime endTime;
  public Stuff(Shooter s) 
  {
    shoot = s;
    addRequirements(s);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    //endTime = LocalDateTime.now().plusSeconds(5);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    shoot.setShootSpeed(Constants.ShooterConstants.stuffSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    shoot.setShootSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    return false;//LocalDateTime.now().isAfter(endTime);
  }
}
