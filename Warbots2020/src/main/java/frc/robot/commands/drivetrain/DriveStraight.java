/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

//package edu.wpi.first.wpilibj.examples.gyrodrivecommands.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.util.Constants;

/**
 * A command that will turn the robot to the specified angle.
 */
public class DriveStraight extends PIDCommand {
  /**
   * Turns to robot to the specified angle.
   *
   * @param targetAngleDegrees The angle to turn to
   * @param drive              The drive subsystem to use
   */
  public DriveStraight(DriveTrain drive, double targetDistance) {
    super(
        new PIDController
        (Constants.DriveTrainConstants.DRIVE_P, 
        Constants.DriveTrainConstants.DRIVE_I, 
        Constants.DriveTrainConstants.DRIVE_D),
        // Close loop on heading
        drive::getDistance,
        // Set reference to target
        targetDistance,
        // Pipe output to drive robot
        output -> drive.arcadeInput(output, 0),
        // Require the drive
        drive);
        
      drive.resetDistance();
    // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
    // setpoint before it is considered as having reached the reference
    getController()
        .setTolerance(
            Constants.DriveTrainConstants.DRIVE_TOL_INCH,
            Constants.DriveTrainConstants.DRIVE_RATE_TOL_INCH_PER_SEC);
  }

  @Override
  public boolean isFinished() {
    // End when the controller is at the reference.
    return getController().atSetpoint();
  }
}