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

    public DriveWithJoysticks(DriveTrain dt, XboxController dX) {
        addRequirements(dt);
        driveTrain = dt;
        driverXbox = dX;
    }

    @Override
    public void execute() 
    {
        double rotation = -1.0 * driverXbox.getX(Hand.kLeft);
        double speed = 0.0;

        if (driverXbox.getTriggerAxis(Hand.kLeft) > driverXbox.getTriggerAxis(Hand.kRight)) {
            speed = 0.5 * driverXbox.getTriggerAxis(Hand.kLeft);
        } else if (driverXbox.getTriggerAxis(Hand.kRight) > driverXbox.getTriggerAxis(Hand.kLeft)) {
            speed = -1 * 0.5 * driverXbox.getTriggerAxis(Hand.kRight);
        }

        if (driveTrain.getSlowDown()) {
            rotation *= 0.65;
        }
        
        boolean quickTurn = driveTrain.getSlowDown() || driveTrain.getQuickTurn();
        driveTrain.curvatureInput(speed, rotation, quickTurn);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
