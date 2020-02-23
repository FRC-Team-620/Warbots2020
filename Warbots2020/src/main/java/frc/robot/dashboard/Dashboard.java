/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.dashboard;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.subsystems.DriveTrain;
import frc.robot.bling.Bling;
import frc.robot.commands.CaptureIntake;
import frc.robot.commands.EjectIntake;
import frc.robot.commands.LoadShooter;
import frc.robot.commands.ReleaseLowerArmClimber;
import frc.robot.commands.ReleaseUpperArmClimber;
import frc.robot.commands.RetractClimber;
import frc.robot.commands.SpinUpFlyWheel;
import frc.robot.commands.StuffFlyWheel;
import frc.robot.commands.drivetrain.DriveBackward;
import frc.robot.commands.drivetrain.DriveForward;
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
    Shooter shooter, Bling bling, Vision vision, PowerDistributionPanel pdp) {

        SmartDashboard.putData("Commands/Drive Forward", new DriveForward(drivetrain, 500));
        SmartDashboard.putData("Commands/TurnToAngle", new TurnToAngle(-90, drivetrain));
        // SmartDashboard.putData("Commands", new DriveBackward(drivetrain, 500));
        // SmartDashboard.putData("Commands", new CaptureIntake(intake));
        // SmartDashboard.putData("Commands", new EjectIntake(intake));
        // // SmartDashboard.putData("Commands", new LoadShooter(shooter, 
        //     // new SpinUpFlyWheel(flyWheel, Constants.ShooterConstants.SHOOT_SPEED), flyWheel));
        // SmartDashboard.putData("Commands", new ReleaseLowerArmClimber(climber));
        // SmartDashboard.putData("Commands", new ReleaseUpperArmClimber(climber));
        // SmartDashboard.putData("Commands", new RetractClimber(climber, Constants.ClimberConstants.CLIMBER_SPEED));
        // SmartDashboard.putData("Commands", new SpinUpFlyWheel(flyWheel, Constants.ShooterConstants.SHOOT_SPEED));
        // SmartDashboard.putData("Commands", new StuffFlyWheel(flyWheel));


        // //Subsystems that ALWAYS exist - do not require if statements
        // Shuffleboard.getTab("Drive").add("Motor Temperature", 100).withWidget(BuiltInWidgets.kDial).withPosition(3, 2).getEntry();
        // Shuffleboard.getTab("Drive").add("Gyro", 360).withWidget(BuiltInWidgets.kGyro).getEntry();
        // Shuffleboard.getTab("Drive").add("Battery Voltage", 13.5).withWidget(BuiltInWidgets.kDial).getEntry();
       
       
        // //Subsystems that MAY NOT exist - require if statements
        // if(climber != null)
        //     Shuffleboard.getTab("Climber").add("Activated?", false).withWidget(BuiltInWidgets.kBooleanBox).getEntry().setBoolean(climber.atSetPosition());
        // if(flyWheel != null)
        //     Shuffleboard.getTab("Shooter").add("Flywheel Speed", 1).withWidget(BuiltInWidgets.kDial).getEntry().setDouble(flyWheel.flyWheelSpeed());
        // if(shooter != null)
        //     Shuffleboard.getTab("Shooter").add("Ball Loaded", false).withWidget(BuiltInWidgets.kBooleanBox).getEntry().setBoolean(shooter.ballLoaded());
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
