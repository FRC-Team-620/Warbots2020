/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.robot.Pin;

public class Climber extends SubsystemBase 
{
  //region Constructors
  public Climber() 
  { 
    climberActuatorUpper = new Servo(Pin.ClimberActuatorUpper.id);
    climberActuatorUpper.set(0);
    climberActuatorUpper.setAngle(0);

    climberActuatorLower = new Servo(Pin.ClimberActuatorLower.id);
    climberActuatorLower.set(0);
    climberActuatorLower.setAngle(0);
    
    climberMotor = new TalonFX(Pin.ClimberMotor.id);
  }
  //endregion
  //region Overrides
  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
  }
  //endregion

  //region Methods
  public void setSpeed(final double speed, final int pos)
  {
    climberMotor.set(ControlMode.PercentOutput, speed);
    position = pos;
  }

  public int getPosition()
  {
    return climberMotor.getSelectedSensorPosition();
  }

  public void setAngleUpper(final double d)
  {
    degrees = d;
    climberActuatorUpper.setAngle(degrees);
  }

  public void setAngleLower(final double d)
  {
    degrees = d;
    climberActuatorLower.setAngle(degrees);
  }

  public boolean atSetPointUpper()
  {
    if(climberActuatorUpper.getAngle() == degrees){
      return true;
    }
    return false;
  }

  public boolean atSetPointLower()
  {
    if(climberActuatorLower.getAngle() == degrees){
      return true;
    }
    return false;
  }

  public boolean atSetPosition()
  {
    if(climberMotor.getSelectedSensorPosition() >= position)
      return true;
    return false;
  }
  
  //endregion
  //region Fields
  private final Servo climberActuatorUpper;
  private final Servo climberActuatorLower;
  private final TalonFX climberMotor;
  private double degrees;
  private int position;
  //endregion
}
