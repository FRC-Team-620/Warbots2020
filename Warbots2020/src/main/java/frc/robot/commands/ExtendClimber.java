/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Climber;
import frc.robot.util.Constants;

public class ExtendClimber extends SequentialCommandGroup {

    private final Climber climber;

    public ExtendClimber(Climber c) {
        climber = c;
        addRequirements(climber);

        addCommands(
            
            new ReleaseLowerArmClimber(climber),

            new WaitCommand(Constants.ClimberConstants.CLIMBER_WAIT_TIME),

            new ReleaseUpperArmClimber(climber),

            new WaitCommand(Constants.ClimberConstants.CLIMBER_WAIT_TIME)

        );
    }

    // region Overrides
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
    // endregion

}