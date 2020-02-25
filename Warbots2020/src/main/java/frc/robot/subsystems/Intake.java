/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.Constants;
import frc.robot.util.Pin;

public class Intake extends SubsystemBase {

    private final TalonSRX intakeMotor;

    public Intake() 
    {
        intakeMotor = new TalonSRX(Pin.IntakeMotor.id);
        var talonSRXConfig = new TalonSRXConfiguration();
        talonSRXConfig.continuousCurrentLimit = Constants.IntakeConstants.INTAKE_CURRENT_LIMIT;
        intakeMotor.configAllSettings(talonSRXConfig);
        intakeMotor.setNeutralMode(NeutralMode.Coast);
    }

    public void intake() {
        intakeMotor.set(ControlMode.PercentOutput, Constants.IntakeConstants.ROLLER_SPEED_FORWARD);
    }

    public void end() {
        intakeMotor.set(ControlMode.PercentOutput, 0);
    }

}