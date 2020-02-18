/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.time.LocalDateTime;

import frc.robot.subsystems.FlyWheel;

public class SpinUpFlyWheel extends CommandFlyWheel {
    public LocalDateTime endTime;

    // region Constructors
    public SpinUpFlyWheel(FlyWheel s, double speed) {
        super(s, speed);
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

    // region Methods
    public void resetEndTime() {
        endTime = LocalDateTime.now().plusSeconds(10);
    }
    // endregion
}