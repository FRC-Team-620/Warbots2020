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

public class Climber extends SubsystemBase {
  /**
   * Creates a new Climber.
   */
  public Climber() {  

    m_climberActuator = new Servo(1);
    m_climberActuator.set(0);
    m_climberActuator.setAngle(0);
    
    m_climberMotor = new TalonSRX(0);
    m_climberMotor.set(ControlMode.PercentOutput, Pin.ClimberMotor.id);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  private final Servo m_climberActuator;
  private final TalonSRX m_climberMotor;
  
}
