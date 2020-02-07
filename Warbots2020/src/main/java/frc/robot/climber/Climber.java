/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.climber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.robot.Pin;

public class Climber extends SubsystemBase 
{
  //region Constructors
  public Climber() 
  { 
    climberActuator = new Servo(1);
    climberActuator.set(0);
    climberActuator.setAngle(0);
    
    climberMotor = new TalonSRX(0);
    climberMotor.set(ControlMode.PercentOutput, Pin.ClimberMotor.id);
  }
  //endregion

  //region Overrides
  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
  }
  //endregion

  //region Fields
  private final Servo climberActuator;
  private final TalonSRX climberMotor;
  //endregion
}
