/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.driveTrain;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.robot.*;
import java.util.function.Function;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SPI;

public class DriveTrain extends SubsystemBase 
{
  CANSparkMax controller;
  //region Constructors
  public DriveTrain()
  {
    lf = new CANSparkMax(Pin.LeftFrontMotor.id, MotorType.kBrushless);
    rf = new CANSparkMax(Pin.RightFrontMotor.id, MotorType.kBrushless);
    lr = new CANSparkMax(Pin.LeftRearMotor.id, MotorType.kBrushless);
    rr = new CANSparkMax(Pin.RightRearMotor.id, MotorType.kBrushless);

    lf.restoreFactoryDefaults();
    lr.restoreFactoryDefaults();
    rf.restoreFactoryDefaults();
    rr.restoreFactoryDefaults();

    var mode = IdleMode.kBrake;
    lf.setIdleMode(mode);
    lr.setIdleMode(mode);
    rf.setIdleMode(mode);
    rr.setIdleMode(mode);

    var conversionFactor = 1.0; //TODO: ask Mr. Mercer for revolution to position conversion factor; determine fudge factor ourselves;
    lf.getEncoder().setPositionConversionFactor(conversionFactor);
    lr.getEncoder().setPositionConversionFactor(conversionFactor);
    rf.getEncoder().setPositionConversionFactor(conversionFactor);
    rr.getEncoder().setPositionConversionFactor(conversionFactor);

    var openLoopRampRate = 0.75;
    lf.setOpenLoopRampRate(openLoopRampRate);
    lr.setOpenLoopRampRate(openLoopRampRate);
    rf.setOpenLoopRampRate(openLoopRampRate);
    rr.setOpenLoopRampRate(openLoopRampRate);

    var currentLimit = 20; //TODO: ask Mr. Mercer for revolution to position conversion factor; determine fudge factor ourselves;
    lf.setSmartCurrentLimit(currentLimit);
    lr.setSmartCurrentLimit(currentLimit);
    rf.setSmartCurrentLimit(currentLimit);
    rr.setSmartCurrentLimit(currentLimit);

    distanceTraveled = reset -> 
    {
      var avg = lf.getEncoder().getPosition() * lf.getEncoder().getPositionConversionFactor();
      // avg += lr.getEncoder().getPosition() * lr.getEncoder().getPositionConversionFactor();
      // avg += rf.getEncoder().getPosition() * lr.getEncoder().getPositionConversionFactor();
      // avg += rr.getEncoder().getPosition() * lr.getEncoder().getPositionConversionFactor();
      // avg /= 4.0;
      if(reset) encoderOffsetDistance = avg;
      return avg - encoderOffsetDistance;
    };

    lf.follow(lr, false);
    rf.follow(rr, false);

    var leftSide = lr; //= new SpeedControllerGroup(lf, lr);
    var rightSide =  rr; //= new SpeedControllerGroup(rf, rr);

    diffDrive = new DifferentialDrive(leftSide, rightSide);
    diffDrive.setDeadband(0.05);
    //TODO: set distance per pulse

    navX = new AHRS(SPI.Port.kMXP);
  }
  //endregion

  //region Methods
  public void arcadeInput(double speed, double rotation)
  {
    diffDrive.arcadeDrive(speed, rotation);
  }

  //returns the temperature of the left front motor
  public double getMotorTemp() {
    return lf.getMotorTemperature();
  }

  public void curvatureInput(double speed, double rotation, boolean isCurvartureDrive)
  {
    
    diffDrive.curvatureDrive(speed, rotation, isCurvartureDrive);
  }

  public double getYaw()
  {
    return navX.getYaw();
  } 

  public void resetYaw()
  {
    navX.zeroYaw();
  }
  //endregion

  //region Fields
  private final DifferentialDrive diffDrive;
  private final AHRS navX;
  public final Function<Boolean, Double> distanceTraveled;
  private double encoderOffsetDistance = 0;
  private CANSparkMax lf, rf, rr, lr;
  //endregion
}