/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.subsystems.DriveTrain;

public class DriveWithJoysticks extends DriveCommand 
{
  //region Constructors
  public DriveWithJoysticks(DriveTrain dt, XboxController driverXboxController) 
  {
    super(dt);
    driverXbox = driverXboxController;
  }
  //endregion

  //region Overrides
  @Override
  public void execute() 
  {
    var rotation = -1 * driverXbox.getX(Hand.kLeft);
    var speed = driverXbox.getY(Hand.kLeft);

    //speed = Math.pow(speed, 3);

    if(driverXbox.getBumper(Hand.kRight))
    {
      rotation *= 0.65;
      speed *= 0.65;
    }

    driveTrain.curvatureInput(speed, rotation, driverXbox.getAButton());
  }

  @Override
  public boolean isFinished() 
  {
    return false;
  }
  //endregion

  //region Field
  protected XboxController driverXbox;
  //endregion
}





















