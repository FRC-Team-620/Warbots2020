/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class LoadShooter extends CommandBase {

    private Shooter shooter;
    private int framesSinceLastShot;
    private boolean lastFrameBallLoaded;

    /*
     * Runs shooter motor until a ball is in the loaded position. Does nothing if
     * ball is already loaded
     */
    public LoadShooter(Shooter shooter) {
        addRequirements(shooter);
        this.shooter = shooter;
        // Use addRequirements() here to declare subsystem dependencies.
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        framesSinceLastShot = 100;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        shooter.forward();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        shooter.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if (framesSinceLastShot++ < 12) {
            return false;
        }
        if (!shooter.isLoaded() && lastFrameBallLoaded == true) {
            framesSinceLastShot = 0;
        }
        if (shooter.isLoaded() && lastFrameBallLoaded == false) {
            return true;
        }
        lastFrameBallLoaded = shooter.isLoaded();
        return false;
    }
}
