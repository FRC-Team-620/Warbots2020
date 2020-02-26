/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.CommandFlyWheel;
import frc.robot.commands.drivetrain.DriveStraight;
import frc.robot.commands.drivetrain.TurnToAngle;
import frc.robot.commands.shooter.LoadShooter;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.Shooter;
import frc.robot.util.Constants;

public class AutonomousCommand extends SequentialCommandGroup {

    public AutonomousCommand(DriveTrain drivetrain, FlyWheel flyWheel, Shooter shooter, int startingSide, double waitTime) {

        addCommands(new DriveStraight(drivetrain, -96));

        // new WaitCommand(waitTime);        
        // switch (startingSide) {
        // case 0:
        //     addCommands(
        //             // Drives up to the low goal
        //             new DriveStraight(drivetrain, Constants.DriveTrainConstants.AUTO_DRIVE_DISTANCE));
        //     break;
        // case 1:
        //     addCommands(
        //             // Drives up to the low goal
        //             new ParallelCommandGroup(
        //                     new DriveStraight(drivetrain, Constants.DriveTrainConstants.AUTO_DRIVE_DISTANCE),

        //                     // Starts the flywheel
        //                     new CommandFlyWheel(flyWheel, Constants.ShooterConstants.SHOOT_SPEED)),

        //             // Begin firing
        //             new LoadShooter(shooter).withTimeout(Constants.LoaderConstants.LOADER_TIMEOUT),

        //             // Stop FlyWheel
        //             new CommandFlyWheel(flyWheel, 0),

        //             // Backs up 25% the initial distance
        //             new DriveStraight(drivetrain, -Constants.DriveTrainConstants.AUTO_DRIVE_DISTANCE / 4),

        //             // Insert turn left command
        //             new TurnToAngle(-90, drivetrain),

        //             // Drives to the corner of the arena to stop
        //             new DriveStraight(drivetrain, Constants.DriveTrainConstants.AUTO_DRIVE_DISTANCE / 2));
        //     break;
        // case 2:
        //     addCommands(
        //             // Drives up to the low goal
        //             new TurnToAngle(10, drivetrain));

        //     break;
        //}
    }

    @Override
    public void execute() 
    {
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
