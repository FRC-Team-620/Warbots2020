/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.shooter;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.robot.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  public Shooter() 
  {
    shooter = new TalonFX(Pin.ShooterMotor1.id);
    loader = new TalonSRX(Pin.ShooterLoaderMotor.id);

    var fxConfig = new TalonFXConfiguration();
    fxConfig.statorCurrLimit.currentLimit = 20;
    shooter.configAllSettings(fxConfig);

    var srxConfig = new TalonSRXConfiguration();
    srxConfig.continuousCurrentLimit = 20;
    loader.configAllSettings(srxConfig);
  }

  @Override
  public void periodic() {    
    // This method will be called once per scheduler run
  }

  public void setShootSpeed(double speed)
  {
      shooter.set(ControlMode.PercentOutput , speed);
  }

  public void load()
  {
    loader.set(ControlMode.PercentOutput, .2);
  }

  protected TalonFX shooter;
  protected TalonSRX loader;
}
