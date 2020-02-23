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

public class SpinUpFlyWheel extends CommandBase {

    private LocalDateTime endTime;
    private final double targetVelocity;
    private final FlyWheel flyWheel;

    // region Constructors
    public SpinUpFlyWheel(FlyWheel flyWheel, double speed) {

        addRequirements(flyWheel);
        this.flyWheel = flyWheel;
        this.targetVelocity = speed;
    }
    // endregion

    // region Overrides
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        resetEndTime();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        flyWheel.setShootSpeed(targetVelocity);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        flyWheel.setShootSpeed(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return LocalDateTime.now().isAfter(endTime);
    }
    // endregion

    public void resetEndTime() {
        endTime = LocalDateTime.now().plusSeconds(10);
    }

    public double getTargetVelocity() {
        return targetVelocity;
    }
}