/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Fire extends CommandBase
{
  private final Shooter shooter;
  private final double targetVelocity;

  /**
   * Creates a new Fire.
   *
   * @param Shooter The subsystem used by this command.
   */
  public Fire(Shooter s, double speed) 
  {
    shooter = s;
    targetVelocity = speed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    shooter.setShootSpeed(targetVelocity);
    shooter.load();
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
    return !shooter.ballLoaded();
  }
}   