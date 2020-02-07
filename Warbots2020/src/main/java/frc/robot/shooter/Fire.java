/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.shooter;

public class Fire extends ShooterCommand
{
  //region Constructors
  public Fire(Shooter s, double shootSpeed, Loader l) 
  {
    super(s, shootSpeed);
    loader = l;
    addRequirements(loader);
  }
  //endregion

  //region Overrides
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
    loader.load();
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
    return !loader.ballLoaded();
  }
  //endregion

  //region Fields
  private final Loader loader;
  //endregion
}   