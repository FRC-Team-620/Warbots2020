/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.bling;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.robot.Constants;
import frc.robot.robot.Pin;

public class Bling extends SubsystemBase {
  
  Spark blinkin = new Spark(Pin.BlingLights.id);

  public Bling() 
  {
    blinkin.set(Constants.BlingConstants.oceanColoredRainbow);
  }

  @Override
  public void periodic()
  {
    
  }

}
