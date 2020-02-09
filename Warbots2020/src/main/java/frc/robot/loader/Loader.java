/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.loader;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.robot.*;

public class Loader extends SubsystemBase 
{
  protected TalonSRX loaderMotor;
  protected DigitalInput ballLoadedSwitch;
  
  public Loader() 
  {
    loaderMotor = new TalonSRX(Pin.LoaderMotor.id);
    ballLoadedSwitch = new DigitalInput(Pin.BallLoadedLimitSwitch.id);

    var srxConfig = new TalonSRXConfiguration();
    srxConfig.continuousCurrentLimit = Constants.Shooter.loaderCurrentLimit;
    loaderMotor.configAllSettings(srxConfig);
  }

  public boolean ballLoaded()
  {
    SmartDashboard.putBoolean("Ball Loaded Switch", ballLoadedSwitch.get());
    return ballLoadedSwitch.get();
  }

  public void load()
  {
    loaderMotor.set(ControlMode.PercentOutput, Constants.Shooter.spinRate);
  }
}
