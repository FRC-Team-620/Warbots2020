/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.dashboard;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.subsystems.DriveTrain;
import frc.robot.bling.Bling;
import frc.robot.commands.CaptureIntake;
import frc.robot.commands.EjectIntake;
import frc.robot.commands.ReleaseLowerArmClimber;
import frc.robot.commands.ReleaseUpperArmClimber;
import frc.robot.commands.RetractClimber;
import frc.robot.commands.SpinUpFlyWheel;
import frc.robot.commands.StuffFlyWheel;
import frc.robot.commands.drivetrain.DriveStraight;
import frc.robot.commands.drivetrain.TurnToAngle;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.util.Constants;
import frc.robot.vision.Vision;

public class Dashboard extends SubsystemBase 
{
    ShuffleboardTab driveTab;
    ShuffleboardTab visionTab;
    ShuffleboardTab commandsTab;

    
    public Dashboard(DriveTrain drivetrain, Climber climber, FlyWheel flyWheel, Intake intake,
    Shooter shooter, Bling bling, Vision vision, PowerDistributionPanel pdp) 
    {
        SmartDashboard.putData("Commands/Drive Forward", new DriveStraight(drivetrain, Constants.DriveTrainConstants.AUTO_DRIVE_DISTANCE));
        SmartDashboard.putData("Commands/TurnToAngle", new TurnToAngle(-90, drivetrain));
        SmartDashboard.putData("Commands/CaptureIntake", new CaptureIntake(intake));
        SmartDashboard.putData("Commands/EjectIntake", new EjectIntake(intake));
        // SmartDashboard.putData("Commands", new LoadShooter(shooter);
        SmartDashboard.putData("Commands/ReleaseLowerArmClimber", new ReleaseLowerArmClimber(climber));
        SmartDashboard.putData("Commands/ReleaseUpperArmClimber", new ReleaseUpperArmClimber(climber));
        SmartDashboard.putData("Commands/RetractClimber", new RetractClimber(climber, Constants.ClimberConstants.CLIMBER_SPEED));
        SmartDashboard.putData("Commands/SpinUpFlyWheel", new SpinUpFlyWheel(flyWheel, Constants.ShooterConstants.SHOOT_SPEED));
        SmartDashboard.putData("Commands/StuffFlyWheel", new StuffFlyWheel(flyWheel));

        SmartDashboard.putNumber("Motor Temperature", 100);
        SmartDashboard.putNumber("Gyro", 360);
        SmartDashboard.putNumber("Battery Voltage", 13.5);
        SmartDashboard.putNumber("Distance Traveled", drivetrain.getDistance());
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
