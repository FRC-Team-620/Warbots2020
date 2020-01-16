/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.DriveTrain;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot.*;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.PWMSparkMax;

public class DriveTrain extends SubsystemBase 
{
  //region Constructors
  public DriveTrain()
  {

    var lf = new CANSparkMax(Pin.LeftFrontMotor.id(), MotorType.kBrushed);
    var rf = new CANSparkMax(Pin.RightFrontMotor.id(), MotorType.kBrushed);
    var lr = new CANSparkMax(Pin.LeftRearMotor.id(), MotorType.kBrushed);
    var rr = new CANSparkMax(Pin.RightRearMotor.id(), MotorType.kBrushed);

    var leftSide = new SpeedControllerGroup(lf, lr);
    var rightSide = new SpeedControllerGroup(rf, rr);

    diffDrive = new DifferentialDrive(leftSide, rightSide);

    // lFEncoder = new Encoder(Pin.LeftFrontEncoderA.id(), Pin.LeftFrontEncoderB.id());
    // rFEncoder = new Encoder(Pin.RightFrontEncoderA.id(), Pin.RightFrontEncoderB.id());
    // lREncoder = new Encoder(Pin.LeftRearEncoderA.id(), Pin.LeftRearEncoderB.id());
    // rREncoder = new Encoder(Pin.RightRearEncoderA.id(), Pin.RightRearEncoderB.id());

    // var distancePerPulse = 0; //TODO: set distance per pulse
    // lFEncoder.setDistancePerPulse(distancePerPulse);
    // rFEncoder.setDistancePerPulse(distancePerPulse);
    // lREncoder.setDistancePerPulse(distancePerPulse);
    // rREncoder.setDistancePerPulse(distancePerPulse);

    navX = new AHRS(SPI.Port.kMXP);
    int a = 5;
  }
  //endregion

  //region Methods
  public void arcadeInput(double speed, double rotation)
  {
    diffDrive.arcadeDrive(speed, rotation);
  }

  public double distanceTraveled()
  {
    //return (lFEncoder.getDistance() + lREncoder.getDistance() + rFEncoder.getDistance() + rREncoder.getDistance()) / 4;
    return 0;
  } 

  public void resetDistanceTraveled()
  {
    // lFEncoder.reset();
    // rFEncoder.reset();
    // lREncoder.reset();
    // rREncoder.reset();
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
  // private final Encoder lFEncoder;
  // private final Encoder rFEncoder;
  // private final Encoder lREncoder;
  // private final Encoder rREncoder;
  private final AHRS navX;
  //endregion
}
