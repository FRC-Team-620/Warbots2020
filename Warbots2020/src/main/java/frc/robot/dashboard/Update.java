/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.dashboard;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Update extends CommandBase {

    public Update(Dashboard dashboard) {
        addRequirements(dashboard);

        //Create shuffleboardtabs
        //getTab creates the tab if it does not already exist

        //shows the motor temp out of 100 in Shuffleboard tab 'drive'
        
        /**
         * TODO
         * update drive camera
         * battery voltage
         * flywheel speed
         * ball loaded sensor
         * gyro angle
         * elevation and yaw to target
         */

        
        
        
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    //Update all entries in the Shuffleboard, just like in the constructor

    public void execute() {
        Shuffleboard.update();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {

    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
