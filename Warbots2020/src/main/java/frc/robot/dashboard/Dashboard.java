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
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.Shooter;

public class Dashboard extends SubsystemBase {
    /**
     * Creates a new Dashboard.
     */
    public Dashboard(DriveTrain train, Climber climb, Shooter shoot, FlyWheel wheel, PowerDistributionPanel p) {
        //Subsystems that ALWAYS exist - do not require if statements
        Shuffleboard.getTab("Drive").add("Motor Temperature", 100).withWidget(BuiltInWidgets.kDial).getEntry().setDouble(train.getAvgMotorTemp());
        Shuffleboard.getTab("Drive").add("Gyro", 360).withWidget(BuiltInWidgets.kGyro).getEntry().setDouble(train.getYaw());
        Shuffleboard.getTab("Drive").add("Battery Voltage", 13.5).withWidget(BuiltInWidgets.kDial).getEntry().setDouble(p.getVoltage());        
        //Subsystems that MAY NOT exist - require if statements
        if(climb != null)
            Shuffleboard.getTab("Climber").add("Activated?", false).withWidget(BuiltInWidgets.kBooleanBox).getEntry().setBoolean(climb.atSetPosition());
        if(wheel != null)
            Shuffleboard.getTab("Shooter").add("Flywheel Speed", 1).withWidget(BuiltInWidgets.kDial).getEntry().setDouble(wheel.flyWheelSpeed());
        if(shoot != null)
            Shuffleboard.getTab("Shooter").add("Ball Loaded", false).withWidget(BuiltInWidgets.kBooleanBox).getEntry().setBoolean(shoot.ballLoaded());
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
