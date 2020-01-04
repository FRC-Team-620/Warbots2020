/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.DriveTrain;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Pin;
import edu.wpi.first.wpilibj.SpeedControllerGroup;


public class DriveTrain extends SubsystemBase 
{
  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() throws Exception
  {
      var lf = SparkMaxReflector.CreateSparkMax(Pin.LEFTFRONTMOTOR.id());
      var rf = SparkMaxReflector.CreateSparkMax(Pin.RIGHTFRONTMOTOR.id());
      var lr = SparkMaxReflector.CreateSparkMax(Pin.LEFTREARMOTOR.id());
      var rr = SparkMaxReflector.CreateSparkMax(Pin.RIGHTREARMOTOR.id());

      var leftSide = new SpeedControllerGroup(lf, lr);
      var rightSide = new SpeedControllerGroup(rf, rr);

      diffDrive = new DifferentialDrive(leftSide, rightSide);
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

  private final DifferentialDrive diffDrive;
}
