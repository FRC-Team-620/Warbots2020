/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.robot.Pin;

public class Intake extends SubsystemBase
{ 
  //region Constructors
  public Intake() 
  {
    intakeMotor = new TalonSRX(Pin.IntakeMotor.id);
    intakeMotor.set(ControlMode.PercentOutput, 0);
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
  private final TalonSRX intakeMotor;
  //endregion
}