/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.time.LocalDateTime;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class LoadShooter extends CommandBase 
{
  Shooter shooter;
  SpinUpFlyWheel stuff;
  boolean lastFrameBallLoaded;
  LocalDateTime endTime;
  int framesSinceLastShot;

  public LoadShooter(Shooter l, SpinUpFlyWheel s) 
  {
    addRequirements(l);
    shooter = l;
    stuff = s;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    lastFrameBallLoaded = shooter.ballLoaded();
    resetEndTime();
    framesSinceLastShot = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    shooter.load();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    shooter.stopLoading();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    if(framesSinceLastShot++ < 12) 
    {
      return false;
    }
    if(!shooter.ballLoaded() && lastFrameBallLoaded == true) 
    {
      stuff.resetEndTime();
      resetEndTime();
      framesSinceLastShot = 0;
    }
    if(shooter.ballLoaded() && lastFrameBallLoaded == false) 
    {
      return true; 
    }
    if(LocalDateTime.now().isAfter(endTime))
    {
      return true;
    }
    lastFrameBallLoaded = shooter.ballLoaded();
    return false;
  }

  //region Methods
  public void resetEndTime()
  {
    endTime = LocalDateTime.now().plusSeconds(10);
  }
  //endregion
}
