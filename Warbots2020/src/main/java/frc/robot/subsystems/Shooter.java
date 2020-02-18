/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.Constants;
import frc.robot.util.Pin;

public class Shooter extends SubsystemBase {
    protected TalonSRX loaderMotor = new TalonSRX(Pin.LoaderMotor.id);;
    protected DigitalInput ballLoadedSwitch = new DigitalInput(Pin.BallLoadedLimitSwitch.id);

    public Shooter() {
        var talonSRXConfig = new TalonSRXConfiguration();
        talonSRXConfig.continuousCurrentLimit = Constants.LoaderConstants.LOADER_CURRENT_LIMIT;
        loaderMotor.configAllSettings(talonSRXConfig);
    }

    public boolean ballLoaded() {
        SmartDashboard.putBoolean("Ball Loaded Switch", ballLoadedSwitch.get());
        return ballLoadedSwitch.get();
    }

    public void load() {
        loaderMotor.set(ControlMode.PercentOutput, 1);
    }

    public void stopLoading() {
        loaderMotor.set(ControlMode.PercentOutput, 0);
    }
}
