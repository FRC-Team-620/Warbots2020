/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public abstract class DriveCommand extends CommandBase {
    // region Constructors
    protected DriveCommand(DriveTrain dt) {
        driveTrain = dt;
        addRequirements(dt);
    }
    // endregion

    // region Fields
    protected final DriveTrain driveTrain;
    // endregion
}
