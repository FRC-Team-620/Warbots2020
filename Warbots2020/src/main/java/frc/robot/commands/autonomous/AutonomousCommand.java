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
import frc.robot.commands.drivetrain.DriveBackward;
import frc.robot.commands.drivetrain.DriveForward;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.Shooter;
import frc.robot.util.Constants;

public class AutonomousCommand extends SequentialCommandGroup {
    // class variables
    public int startingSide; // 0 is left, 1 is middle, 2 is right
    public double waitTime;
    protected DriveTrain drivetrain;
    
    private final FlyWheel flyWheel = new FlyWheel();
    private final Shooter shooter = new Shooter();
    SpinUpFlyWheel spinUp = new SpinUpFlyWheel(flyWheel, Constants.ShooterConstants.SHOOT_SPEED);

    // region Constructors
    public AutonomousCommand(DriveTrain dt, int sS, double wT) {
        startingSide = sS;
        waitTime = wT;
        drivetrain = dt;

        if(startingSide == 0) { // Run left side
            addCommands(
                // Drives up to the low goal
                new DriveForward(drivetrain, Constants.DriveTrainConstants.AUTO_DRIVE_DISTANCE),

                // Starts the flywheel
                new SpinUpFlyWheel(flyWheel, Constants.ShooterConstants.SHOOT_SPEED),

                // Begin firing
                new LoadShooter(shooter, spinUp),

                // Waits for the robot to finish firing
                new WaitCommand(5),

                // Backs up 25% the initial distance
                new DriveBackward(drivetrain, Constants.DriveTrainConstants.AUTO_DRIVE_DISTANCE / 4),

                // Insert turn left command

                
                // Drives to the corner of the arena to stop
                new DriveForward(drivetrain, Constants.DriveTrainConstants.AUTO_DRIVE_DISTANCE / 2)
                );
        }
        else if(startingSide == 1) { // Run middle side

        }
        else { // Run right side
            addCommands(
                // Drives up to the low goal
                new DriveForward(drivetrain, Constants.DriveTrainConstants.AUTO_DRIVE_DISTANCE),

                // Starts the flywheel
                new SpinUpFlyWheel(flyWheel, Constants.ShooterConstants.SHOOT_SPEED),

                // Begin firing
                new LoadShooter(shooter, spinUp),

                // Waits for the robot to finish firing
                new WaitCommand(5),

                // Backs up 25% the initial distance
                new DriveBackward(drivetrain, Constants.DriveTrainConstants.AUTO_DRIVE_DISTANCE / 4),

                // Insert turn right command

                
                // Drives to the corner of the arena to stop
                new DriveForward(drivetrain, Constants.DriveTrainConstants.AUTO_DRIVE_DISTANCE / 2)
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
