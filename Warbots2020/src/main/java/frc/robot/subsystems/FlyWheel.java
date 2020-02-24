/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.Pin;

public class FlyWheel extends SubsystemBase { // TODO Make A PID Subsystem.

    private TalonFX shooter;

    public FlyWheel() {
        shooter = new TalonFX(Pin.ShooterMotor1.id);
        shooter.configAllSettings(new TalonFXConfiguration());
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public void setShootSpeed(double speed) {
        shooter.set(ControlMode.PercentOutput, speed); //TODO might have to look into motor safety feature of wpilib if flywheel randomly spins down.
    }

    //Returns true when Shooter is spun up to speed.
    //Currently set to always be true. This will be used when using PID loop control.
    //So you can sequentaly call SpinUpFlywheel -> FireShooter -> StopFlyWheel
    public boolean isAtSpeed() { 
        return true;
    }

    public double getFlyWheelSpeed() {
        return shooter.getMotorOutputPercent();
    }

}
