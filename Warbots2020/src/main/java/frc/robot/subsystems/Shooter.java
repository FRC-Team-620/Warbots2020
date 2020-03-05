/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.Constants;
import frc.robot.util.Pin;

public class Shooter extends SubsystemBase {
    private CANSparkMax loaderMotor = new CANSparkMax(Pin.LoaderMotor.id, MotorType.kBrushless);
    private DigitalInput ballLoadedSwitch = new DigitalInput(Pin.BallLoadedLimitSwitch.id);

    public Shooter() {
        loaderMotor.setIdleMode(IdleMode.kBrake);
    }

    public boolean isLoaded() {
        SmartDashboard.putBoolean("Ball Loaded Switch", ballLoadedSwitch.get());
        return ballLoadedSwitch.get();
    }
    
    public void set(double speed) {
        loaderMotor.set(-speed);
    }

    public void forward() {
        loaderMotor.set(-1);
    }

    public void stop() {
        loaderMotor.set(0);
    }
}
