/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.vision;

import frc.robot.commands.SpinUpFlywheel;
import frc.robot.subsystems.FlyWheel;
import frc.robot.util.Constants.ShooterConstants;


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
    try
    {
      if(usePreservedRPM) shooter.setShootRPM(super.targetVelocity);
      else shooter.setShootRPM(vision.getRPM());
    }
    catch(Exception e)
    {
      shooter.setShootSpeed(ShooterConstants.STUFF_SPEED * 1.5);
    }
  }

  public void preserveRPM() 
  {
    usePreservedRPM = true;
    try
    {
      super.targetVelocity = vision.getRPM();
    }
    catch(Exception e)
    {
      super.targetVelocity = 0;
    }
  }

  public void revertRPM() 
  {
    usePreservedRPM = false;
  }
}