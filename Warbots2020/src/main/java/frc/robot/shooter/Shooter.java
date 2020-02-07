/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.shooter;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.robot.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;

//TODO: Scheduling, tuning 
public class Shooter extends SubsystemBase 
{  
  //region Constructors
  public Shooter() 
  {
    shooter = new TalonFX(Pin.ShooterMotor1.id);

    var fxConfig = new TalonFXConfiguration();
    fxConfig.statorCurrLimit.currentLimit = 20;
    shooter.configAllSettings(fxConfig);
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
  public void setShootSpeed(double speed)
  {
    shooter.set(ControlMode.PercentOutput, speed);
  }

  public double flyWheelSpeed()
  {
    return shooter.getSelectedSensorVelocity() * Constants.ShooterConstants.flyWheelConversionFactor;
  }
  //endregion

  //region Fields
  protected TalonFX shooter;
  //endregion
}
