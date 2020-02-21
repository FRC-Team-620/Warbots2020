/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.Constants;
import frc.robot.util.Pin;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;

public class FlyWheel extends SubsystemBase {

    public TalonFX shooter;

    // region Constructors
    public FlyWheel() {
        shooter = new TalonFX(Pin.ShooterMotor1.id);

        final var fxConfig = new TalonFXConfiguration();
        fxConfig.statorCurrLimit.currentLimit = 20;
        shooter.configAllSettings(fxConfig);
    }
    // endregion

    // region Overrides
    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
    // endregion

    // region Methods
    public void setShootSpeed(final double speed) {
        shooter.set(ControlMode.PercentOutput, speed);
    }

    public double flyWheelSpeed() {
        return shooter.getMotorOutputPercent();
    }

    public Boolean atSetPoint() {
        return true; // TODO check if controller is at set point
    }

    // endregion

}
