/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.vision;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class Align extends CommandBase {
    protected double pGain = -0.035;//1725;//1.0 / 90.0;
    protected Vision vision;
    protected DriveTrain driveTrain;

    public Align(Vision v, DriveTrain dt) {
        vision = v;
        driveTrain = dt;
        addRequirements(vision);
        addRequirements(driveTrain);
        // Use addRequirements() here to declare subsystem dependencies.
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        vision.turnOnLights();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        driveTrain.arcadeInput(0, pGain * vision.getTx());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        driveTrain.arcadeInput(0, 0);
        //vision.turnOffLights();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return Math.abs(vision.getTx()) < 2.5;
    }
}
