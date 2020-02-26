package frc.robot.commands.shooter;

// TODO - Loader code needs to be completed
import java.time.LocalDateTime;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import frc.robot.util.Constants;
import frc.robot.commands.SpinUpFlywheel;
 
public class LoadShooter extends CommandBase 
{
  //region Constructors
  Shooter loader;
  SpinUpFlywheel stuff;
  boolean lastFrameBallLoaded;
  LocalDateTime endTime;
  int framesSinceLastShot;

  public LoadShooter(Shooter l, SpinUpFlywheel s) 
  {
    addRequirements(l);
    loader = l;
    // this.withTimeout(Constants.Shooter.loaderTimeout);
    stuff = s;
    // Use addRequirements() here to declare subsystem dependencies.
  }
  //endregion
 
  //region Overrides
  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    
    lastFrameBallLoaded = loader.isLoaded();
    resetEndTime();
    framesSinceLastShot = 0;
  }
 
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    loader.set(1);
  }
 
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    loader.stop();
  }
 
  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  { 
    if(framesSinceLastShot++ < 12) 
    {
      return false;
    }
    if(!loader.isLoaded() && lastFrameBallLoaded == true) 
    {
      stuff.resetEndTime();
      resetEndTime();
      framesSinceLastShot = 0;
    }
    if(loader.isLoaded() && lastFrameBallLoaded == false) 
    {
      return true; 
    }
    if(LocalDateTime.now().isAfter(endTime))
    {
      return true;
    }
    lastFrameBallLoaded = loader.isLoaded();
    return false;
  }
  //endregion
 
  //region Methods
  public void resetEndTime()
  {
    endTime = LocalDateTime.now().plusSeconds(10);
  }
  //endregion
} 