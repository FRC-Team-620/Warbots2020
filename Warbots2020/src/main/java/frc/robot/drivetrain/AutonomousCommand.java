/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.drivetrain;

public class AutonomousCommand extends DriveCommand 
{
  //region Constructors
  public AutonomousCommand(DriveTrain dt) 
  {
    super(dt);
  }
  //endregion

  //region Overrides
  @Override
  public void execute() 
  {
    driveTrain.arcadeInput(.5, 0); //TODO Set default autonomous parameters in constants.java
  }

  @Override
  public boolean isFinished() 
  {
    return false;
  }
  //endregion
}
