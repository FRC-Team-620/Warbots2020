/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.DriveTrain;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.robot.*;
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

    LFEncoder = new Encoder(Pin.LeftFrontEncoderA.id(), Pin.LeftFrontEncoderB.id());
    RFEncoder = new Encoder(Pin.RightFrontEncoderA.id(), Pin.RightFrontEncoderB.id());
    LREncoder = new Encoder(Pin.LeftRearEncoderA.id(), Pin.LeftRearEncoderB.id());
    RREncoder = new Encoder(Pin.RightRearEncoderA.id(), Pin.RightRearEncoderB.id());

    var distancePerPulse = 0;
    LFEncoder.setDistancePerPulse(distancePerPulse);
    RFEncoder.setDistancePerPulse(distancePerPulse);
    LREncoder.setDistancePerPulse(distancePerPulse);
    RREncoder.setDistancePerPulse(distancePerPulse);
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
    return (LFEncoder.getDistance() + LREncoder.getDistance() + RFEncoder.getDistance() + RREncoder.getDistance()) / 4;
  } 

  public void resetDistanceTraveled()
  {
    LFEncoder.reset();
    RFEncoder.reset();
    LREncoder.reset();
    RREncoder.reset();
  }

  private final DifferentialDrive diffDrive;
  private final Encoder LFEncoder;
  private final Encoder RFEncoder;
  private final Encoder LREncoder;
  private final Encoder RREncoder;
}
