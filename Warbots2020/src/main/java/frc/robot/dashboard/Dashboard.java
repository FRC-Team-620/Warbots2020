/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.dashboard;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Dashboard extends SubsystemBase {
    public Dashboard() {
    }

    public void addCommand(CommandBase command) {
        SmartDashboard.putData("Commands/" + command.getName(), command);
    }

    public void addCommand(String name, CommandBase command) {
        SmartDashboard.putData("Commands/" + name, command);
    }
    public void addSubsystem(SubsystemBase sub) {
        SmartDashboard.putData(sub);
    }

    @Override
    public void periodic() {

    }
}
