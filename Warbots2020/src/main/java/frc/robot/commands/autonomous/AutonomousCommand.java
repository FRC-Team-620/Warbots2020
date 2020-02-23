/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.LoadShooter;
import frc.robot.commands.SpinUpFlyWheel;
import frc.robot.commands.drivetrain.DriveStraight;
import frc.robot.commands.drivetrain.TurnToAngle;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.Shooter;
import frc.robot.util.Constants;
import frc.robot.util.StartingLocation;

public class AutonomousCommand extends SequentialCommandGroup {
    // class variables

    private final FlyWheel flyWheel = new FlyWheel();
    private final Shooter shooter = new Shooter();
    private SpinUpFlyWheel spinUp = new SpinUpFlyWheel(flyWheel, Constants.ShooterConstants.SHOOT_SPEED);

    // region Constructors
    public AutonomousCommand(DriveTrain drivetrain, StartingLocation startingSide, double waitTime) {

        if (startingSide == StartingLocation.LEFT) { // Run left side
            addCommands(
                    // Drives up to the low goal
                    new DriveStraight(drivetrain, Constants.DriveTrainConstants.AUTO_DRIVE_DISTANCE));
        } else if (startingSide == StartingLocation.MIDDLE) { // Run middle side
            addCommands(
                    // Drives up to the low goal
                    new DriveStraight(drivetrain, Constants.DriveTrainConstants.AUTO_DRIVE_DISTANCE),

                    // Starts the flywheel
                    new SpinUpFlyWheel(flyWheel, Constants.ShooterConstants.SHOOT_SPEED),

                    // Begin firing
                    new LoadShooter(shooter, spinUp, flyWheel),

                    // Waits for the robot to finish firing
                    new WaitCommand(5),

                    // Backs up 25% the initial distance
                    new DriveStraight(drivetrain, Constants.DriveTrainConstants.AUTO_DRIVE_DISTANCE / 4),
//                new DriveBackward(drivetrain, Constants.DriveTrainConstants.AUTO_DRIVE_DISTANCE / 4), //TODO REPLACE WITH DRIVE Straight

                    // Insert turn left command
                    new TurnToAngle(-90, drivetrain),

                    // Drives to the corner of the arena to stop
                    new DriveStraight(drivetrain, Constants.DriveTrainConstants.AUTO_DRIVE_DISTANCE / 2));

        } else { // Run right side
            addCommands(
                    // Drives up to the low goal
                    new DriveStraight(drivetrain, Constants.DriveTrainConstants.AUTO_DRIVE_DISTANCE));

        }
    }
    // endregion

    // region Overrides
    @Override
    public void execute() {
        // driveTrain.arcadeInput(.5, 0); // TODO Set default autonomous parameters in
        // constants.java
    }

    @Override
    public boolean isFinished() {
        return false;
    }
    // endregion
}
