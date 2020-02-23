package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class ExamplePIDDriveCommand extends CommandBase {
    private double distance; // Distance in <InsertUnitHere>
    private double kP = 1.0;
    private double kI = 0.0;
    private double kD = 0.0;
    private PIDController pid;
    private DriveTrain dt;

    public ExamplePIDDriveCommand(DriveTrain dt, double dist) {
        this.distance = dist;
        this.dt = dt;
    }

    @Override
    public void initialize() {
        this.pid = new PIDController(kP, kI, kD);
        this.pid.setTolerance(1.0); // Set Tolorance for when atSetpoint() returns true
        this.pid.setSetpoint(this.distance); // Set Target Distance for pid to move to.
    }

    @Override
    public void execute() {
        this.dt.curvatureInput(this.pid.calculate(this.dt.getDistance()), 0, false); // Calculate and use next pid Value
                                                                                     // based off of current distance.
    }

    @Override
    public boolean isFinished() {
        return this.pid.atSetpoint(); // Finish command when we reach setpoint. (On real bot you may also want to
                                      // include a timeout)
    }

    @Override
    public void end(boolean interrupted) {
        this.dt.curvatureInput(0, 0, false); // Make sure to stop driveTrain if Interupted.
    }

}
