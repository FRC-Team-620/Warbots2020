/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.DriveForward;
import frc.robot.subsystems.DriveTrain;

import frc.robot.util.Constants;

public class AutonomousCommand extends SequentialCommandGroup {
    // class variables
    public int startingSide; // 0 is left, 1 is middle, 2 is right
    public double waitTime;
    protected DriveTrain drivetrain;

    // region Constructors
    public AutonomousCommand(DriveTrain dt, int sS, double wT) {
        startingSide = sS;
        waitTime = wT;
        drivetrain = dt;

        if(startingSide == 0) { // Run left side
            addCommands(
                new DriveForward(drivetrain, Constants.DriveTrainConstants.AUTO_DRIVE_DISTANCE)
                );
        }
        else if(startingSide == 1) { // Run middle side

        }
        else { // Run right side
            addCommands(
                new DriveForward(drivetrain, Constants.DriveTrainConstants.AUTO_DRIVE_DISTANCE)
                );
        }
    }
    // endregion

    // region Overrides
    @Override
    public void execute() {
        //driveTrain.arcadeInput(.5, 0); // TODO Set default autonomous parameters in constants.java
    }

    @Override
    public boolean isFinished() {
        return false;
    }
    // endregion
}
