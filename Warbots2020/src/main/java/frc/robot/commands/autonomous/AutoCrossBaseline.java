package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.CommandFlyWheel;
import frc.robot.commands.SpinUpFlywheel;
import frc.robot.commands.drivetrain.DriveStraight;
import frc.robot.commands.drivetrain.TurnToAngle;
import frc.robot.commands.shooter.LoadShooter;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.Shooter;
import frc.robot.util.Constants;

public class AutoCrossBaseline extends SequentialCommandGroup {
    public AutoCrossBaseline(DriveTrain drivetrain, FlyWheel flyWheel, Shooter shooter) {
        addCommands(

            // new SpinUpFlywheel(flyWheel, Constants.ShooterConstants.STUFF_SPEED),

            new InstantCommand(drivetrain::resetDistance),

            new CommandFlyWheel(flyWheel, Constants.ShooterConstants.SHOOT_SPEED),

            new DriveStraight(drivetrain, Constants.DriveTrainConstants.AUTO_DRIVE_DISTANCE),

            new LoadShooter(shooter, new SpinUpFlywheel(flyWheel, Constants.ShooterConstants.STUFF_SPEED))
        //     new ParallelCommandGroup(
        //         new DriveStraight(drivetrain, Constants.DriveTrainConstants.AUTO_DRIVE_DISTANCE),

        //         // Starts the flywheel
        //         new CommandFlyWheel(flyWheel, Constants.ShooterConstants.SHOOT_SPEED)),

        // // Begin firing
        // new LoadShooter(shooter, new SpinUpFlywheel(flyWheel, Constants.ShooterConstants.STUFF_SPEED)).withTimeout(Constants.LoaderConstants.LOADER_TIMEOUT),

        // // Stop FlyWheel
        // new CommandFlyWheel(flyWheel, 0)

        // // Backs up 25% the initial distance
        // new DriveStraight(drivetrain, -Constants.DriveTrainConstants.AUTO_DRIVE_DISTANCE / 4),

        //     new InstantCommand(drivetrain::resetYaw),
        //     new TurnToAngle(-90, drivetrain)
        );
    }
}
