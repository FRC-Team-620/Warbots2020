package frc.robot.commands;
 
import java.time.LocalDateTime;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FlyWheel;

public class SpinUpFlywheel extends CommandBase
{
  public LocalDateTime endTime;
  private double targetVelocity;
  private FlyWheel shooter;
  //region Constructors
  public SpinUpFlywheel(FlyWheel s, double speed) 
  {
    shooter = s;
    targetVelocity = speed;
  }
  //endregion
 
  //region Overrides
  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    resetEndTime();
  }
 
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    shooter.setShootSpeed(targetVelocity);
  }
 
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    shooter.setShootSpeed(0);
  }
 
  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    return LocalDateTime.now().isAfter(endTime);
  }
  //endregion
 
  //region Methods
  public void resetEndTime()
  {
    endTime = LocalDateTime.now().plusSeconds(10);
  }

  public boolean atSpeed()
  {
    return Math.abs(shooter.getFlyWheelSpeed() - targetVelocity) < 0.1;
  }
  //endregion
}   