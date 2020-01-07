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

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;


public class DriveTrain extends SubsystemBase 
{
  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain()
  {
    var lf = SparkMaxReflector.CreateSparkMax(Pin.LeftFrontMotor.id());
    var rf = SparkMaxReflector.CreateSparkMax(Pin.RightFrontMotor.id());
    var lr = SparkMaxReflector.CreateSparkMax(Pin.LeftRearMotor.id());
    var rr = SparkMaxReflector.CreateSparkMax(Pin.RightRearMotor.id());

    var leftSide = new SpeedControllerGroup(lf, lr);
    var rightSide = new SpeedControllerGroup(rf, rr);

    diffDrive = new DifferentialDrive(leftSide, rightSide);

    lFEncoder = new Encoder(Pin.LeftFrontEncoderA.id(), Pin.LeftFrontEncoderB.id());
    rFEncoder = new Encoder(Pin.RightFrontEncoderA.id(), Pin.RightFrontEncoderB.id());
    lREncoder = new Encoder(Pin.LeftRearEncoderA.id(), Pin.LeftRearEncoderB.id());
    rREncoder = new Encoder(Pin.RightRearEncoderA.id(), Pin.RightRearEncoderB.id());

    var distancePerPulse = 0;
    lFEncoder.setDistancePerPulse(distancePerPulse);
    rFEncoder.setDistancePerPulse(distancePerPulse);
    lREncoder.setDistancePerPulse(distancePerPulse);
    rREncoder.setDistancePerPulse(distancePerPulse);

    navX = new AHRS(); //TODO: create with the correct constructor
  }

  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
  }

  public void arcadeInput(double speed, double rotation)
  {
    diffDrive.arcadeDrive(speed, rotation);
  }

  public double distanceTraveled()
  {
    return (lFEncoder.getDistance() + lREncoder.getDistance() + rFEncoder.getDistance() + rREncoder.getDistance()) / 4;
  } 

  public void resetDistanceTraveled()
  {
    lFEncoder.reset();
    rFEncoder.reset();
    lREncoder.reset();
    rREncoder.reset();
  }

  private final DifferentialDrive diffDrive;
  private final Encoder lFEncoder;
  private final Encoder rFEncoder;
  private final Encoder lREncoder;
  private final Encoder rREncoder;
  private final AHRS navX;
}
