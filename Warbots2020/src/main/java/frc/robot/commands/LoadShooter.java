/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.time.LocalDateTime;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.Shooter;

public class LoadShooter extends CommandBase {

    private Shooter shooter;
    private FlyWheel flyWheel;
    private SpinUpFlyWheel spinUpFlyWheelCommand;
    private boolean lastFrameBallLoaded;
    private LocalDateTime endTime; // Warning never set in constructor or init.
    private int framesSinceLastShot;

    public LoadShooter(Shooter shooter, SpinUpFlyWheel spinUpFlyWheelCommand, FlyWheel flyWheel) { //TODO DONT pass a command as constructor to a command.
        addRequirements(shooter);
        this.shooter = shooter;
        this.spinUpFlyWheelCommand = spinUpFlyWheelCommand;
        this.flyWheel = flyWheel;
        // Use addRequirements() here to declare subsystem dependencies.
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        lastFrameBallLoaded = shooter.ballLoaded();
        resetEndTime();
        framesSinceLastShot = 0;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if(checkSafety()) return;
        shooter.load();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        shooter.stopLoading();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if (framesSinceLastShot++ < 12) {
            return false;
        }
        if (!shooter.ballLoaded() && lastFrameBallLoaded == true) {
            spinUpFlyWheelCommand.resetEndTime();
            resetEndTime();
            framesSinceLastShot = 0;
        }
        if(checkSafety()) {
            return true;
        }
        if (shooter.ballLoaded() && lastFrameBallLoaded == false) {
            return true;
        }
        if (LocalDateTime.now().isAfter(endTime)) {
            return true;
        }
        lastFrameBallLoaded = shooter.ballLoaded();
        return false;
    }

    private boolean checkSafety()
    {
        return shooter.ballLoaded() && Math.abs(flyWheel.flyWheelSpeed() - spinUpFlyWheelCommand.getTargetVelocity()) >= .05; //TODO BAD don't check a command for status of subsystem.
    }
    // region Methods
    public void resetEndTime() {
        endTime = LocalDateTime.now().plusSeconds(10);
    }
    // endregion
}
