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
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.vision.Vision;

public class Dashboard extends SubsystemBase 
{
    ShuffleboardTab driveTab;
    ShuffleboardTab visionTab;
    ShuffleboardTab commandsTab;

    
    public Dashboard(DriveTrain drivetrain, Climber climber, FlyWheel flyWheel, Intake intake,
    Shooter shooter, Bling bling, Vision vision, PowerDistributionPanel pdp) {
        driveTab = Shuffleboard.getTab("Drive");
        commandsTab = Shuffleboard.getTab("Commmands");
        visionTab = Shuffleboard.getTab("Vision");
        
        Shuffleboard.selectTab("Drive");
        
        driveTab.add("Test Number", 1).withPosition(12, 8);
        driveTab.add("Test Number NoPos", 1);


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
