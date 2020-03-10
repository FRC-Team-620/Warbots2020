/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.vision;

import frc.robot.commands.SpinUpFlywheel;
import frc.robot.subsystems.FlyWheel;


public class SpinUpFlywheelVision extends SpinUpFlywheel {
  protected Vision vision;
  boolean usePreservedRPM = false;
  public SpinUpFlywheelVision(FlyWheel s, Vision v, double speed) 
  {
    super(s, speed);
    vision = v;
  }

  @Override
  public void execute()
  {
    if(usePreservedRPM)
      shooter.setShootRPM(super.targetVelocity);
    else
      shooter.setShootRPM(vision.getRPM());
  }

  public void preserveRPM() 
  {
    usePreservedRPM = true;
    super.targetVelocity = vision.getRPM();
  }

  public void revertRPM() 
  {
    usePreservedRPM = false;
  }
}