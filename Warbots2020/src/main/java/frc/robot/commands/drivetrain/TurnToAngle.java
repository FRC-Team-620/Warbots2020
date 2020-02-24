package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.util.Constants;

/**
 * A command that will turn the robot to the specified angle.
 */
public class TurnToAngle extends PIDCommand {
    /**
     * Turns to robot to the specified angle.
     *
     * @param targetAngleDegrees The angle to turn to
     * @param drive              The drive subsystem to use
     */
    public TurnToAngle(double targetAngleDegrees, DriveTrain drive) {
        super(new PIDController(Constants.DriveTrainConstants.TURN_P, Constants.DriveTrainConstants.TURN_I,
                Constants.DriveTrainConstants.TURN_D),
                // Close loop on heading
                drive::getYaw,
                // Set reference to target
                targetAngleDegrees,
                // Pipe output to turn robot
                output -> drive.arcadeInput(0, -output),
                // Require the drive
                drive);

        drive.resetYaw();

        // Set the controller to be continuous (because it is an angle controller)
        getController().enableContinuousInput(-180, 180);
        // Set the controller tolerance - the delta tolerance ensures the robot is
        // stationary at the
        // setpoint before it is considered as having reached the reference
        getController().setTolerance(Constants.DriveTrainConstants.TURN_TOL_DEG,
                Constants.DriveTrainConstants.TURN_RATE_TOL_DEG_PER_SEC);
    }

    @Override
    public boolean isFinished() {
        // End when the controller is at the reference.
        return getController().atSetpoint();
    }
}