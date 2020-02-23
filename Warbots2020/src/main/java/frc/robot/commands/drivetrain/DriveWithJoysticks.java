/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveWithJoysticks extends CommandBase {

    private XboxController driverXbox;
    private DriveTrain driveTrain;

    public DriveWithJoysticks(DriveTrain driveTrain, XboxController driverXbox) {
        this.driveTrain = driveTrain;
        this.driverXbox = driverXbox;
    }

    @Override
    public void execute() {

        var rotation = -1 * driverXbox.getX(Hand.kLeft);
        // var speed = driverXbox.getY(Hand.kLeft);
        System.out.println(driveTrain.getDistance());
        var speed = 0.0;
        if (driverXbox.getTriggerAxis(Hand.kLeft) > driverXbox.getTriggerAxis(Hand.kRight)) {
            speed = -1 * driverXbox.getTriggerAxis(Hand.kLeft);
        } else if (driverXbox.getTriggerAxis(Hand.kRight) > driverXbox.getTriggerAxis(Hand.kLeft)) {
            speed = driverXbox.getTriggerAxis(Hand.kRight);
        }

        if (driverXbox.getBumper(Hand.kRight)) {
            rotation *= 0.65;
            speed *= 0.65;
        }

        driveTrain.curvatureInput(speed, rotation, driverXbox.getAButton());
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
