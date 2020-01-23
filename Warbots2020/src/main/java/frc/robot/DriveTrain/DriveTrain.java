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
import edu.wpi.first.wpilibj.SpeedControllerGroup;

import edu.wpi.first.wpilibj.SPI;

public class DriveTrain extends SubsystemBase 
{
  CANSparkMax controller;
  //region Constructors
  public DriveTrain()
  {
    var lf = new CANSparkMax(Pin.LeftFrontMotor.id, MotorType.kBrushless);
    var rf = new CANSparkMax(Pin.RightFrontMotor.id, MotorType.kBrushless);
    var lr = new CANSparkMax(Pin.LeftRearMotor.id, MotorType.kBrushless);
    var rr = new CANSparkMax(Pin.RightRearMotor.id, MotorType.kBrushless);


    lf.setIdleMode(IdleMode.kBrake);
    lr.setIdleMode(IdleMode.kBrake);
    rf.setIdleMode(IdleMode.kBrake);
    rr.setIdleMode(IdleMode.kBrake);



    // distanceTraveled = reset -> 
    // {
    //   var avg = lf.getEncoder().getPosition();
    // };

    var leftSide = new SpeedControllerGroup(lf, lr);
    var rightSide = new SpeedControllerGroup(rf, rr);

    diffDrive = new DifferentialDrive(leftSide, rightSide);
    //TODO: set distance per pulse

    navX = new AHRS(SPI.Port.kMXP);
  }
  //endregion

  //region Methods
  public void arcadeInput(double speed, double rotation)
  {
    //controller.set(.5);
    diffDrive.arcadeDrive(speed, rotation);
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
  public final Function<Boolean, Double> distanceTraveled = null;
  //endregion
}