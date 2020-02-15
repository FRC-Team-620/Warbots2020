/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.loader;

import java.time.LocalDateTime;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.shooter.SpinUp;

public class TestLoad extends CommandBase 
{
  Loader loader;
  SpinUp stuff;
  boolean lastFrameBallLoaded;
  public LocalDateTime endTime;

  public TestLoad(Loader l, SpinUp s) 
  {
    addRequirements(l);
    loader = l;
    stuff = s;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    lastFrameBallLoaded = loader.ballLoaded();
    resetEndTime();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    loader.load();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    loader.stopLoading();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    if(!loader.ballLoaded() && lastFrameBallLoaded == true) 
    {
      stuff.resetEndTime();
      resetEndTime();
    }
    if(loader.ballLoaded() && lastFrameBallLoaded == false) 
    {
      return true; 
    }
    if(LocalDateTime.now().isAfter(endTime))
    {
      return true;
    }
    lastFrameBallLoaded = loader.ballLoaded();
    return false;
  }

  //region Methods
  public void resetEndTime()
  {
    endTime = LocalDateTime.now().plusSeconds(10);
  }
  //endregion
}
