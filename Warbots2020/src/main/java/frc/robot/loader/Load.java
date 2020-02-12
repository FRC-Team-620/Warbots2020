/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.loader;

// TODO - Loader code needs to be completed

import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.robot.*;

public class Load extends CommandBase
{
  //region Constructors
  public Load(Loader l) 
  {
    loader = l;
    addRequirements(loader);
    // this.withTimeout(Constants.Shooter.loaderTimeout);
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
    // if(lastStateOfSwitch == false && loader.ballLoaded() == true) return true;
    // lastStateOfSwitch = loader.ballLoaded();
    // return false;

    return true;
  }
  //endregion

  //region Fields
  private final Loader loader;
  // private boolean lastStateOfSwitch; 
  //endregion
}   