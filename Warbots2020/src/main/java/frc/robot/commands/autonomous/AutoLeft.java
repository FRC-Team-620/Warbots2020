package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.CommandFlyWheel;
import frc.robot.commands.SpinUpFlywheel;
import frc.robot.commands.drivetrain.DriveStraight;
import frc.robot.commands.drivetrain.TurnToAngle;
import frc.robot.commands.shooter.LoadShooter;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.Shooter;
import frc.robot.util.Constants;

public class AutoLeft extends SequentialCommandGroup {
    public AutoLeft(DriveTrain drivetrain, FlyWheel flyWheel, Shooter shooter, double waitTime) {
        SpinUpFlywheel stuffFlywheel = new SpinUpFlywheel(flyWheel, Constants.ShooterConstants.STUFF_SPEED);
        // LoadShooter loadShooter = new LoadShooter(shooter, stuffFlywheel);


        addCommands(
            new WaitCommand(waitTime),
            
            stuffFlywheel,

            new InstantCommand(drivetrain::resetDistance),

            new CommandFlyWheel(flyWheel, Constants.ShooterConstants.SHOOT_SPEED),

            new DriveStraight(drivetrain, Constants.DriveTrainConstants.AUTO_DRIVE_DISTANCE),

            new LoadShooter(shooter, stuffFlywheel),
            new LoadShooter(shooter, stuffFlywheel),
            new LoadShooter(shooter, stuffFlywheel),
            new LoadShooter(shooter, stuffFlywheel),

            new DriveStraight(drivetrain, -Constants.DriveTrainConstants.AUTO_DRIVE_DISTANCE / 2),

            new TurnToAngle(-90, drivetrain)
        );
    }
}

