/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.robot;

import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.driveTrain.DriveTrain;

public enum Pin
{

    //vvvvvvvvv ELECTRONICS EDITING START HERE vvvvvvvvvvv


    /*^^^^^^^^^^ ELECTRONICS EDITING END HERE ^^^^^^^^^^^^
     *
     * How to use the Pin document:
     *
     * MYPIN(3, Robot.someSubsystem),
     * YOURPIN(4, Robot.otherSubsystem),
     * THEIRPIN(2, Robot.someSubsystem),
     *    ^     ^          ^           ^
     *    ^     ^      Subsystem       ^
     * Pin Name ^                      ^
     *          ^          Comma at the End of a Value
     *   Pin Number on Rio
     * 
     * 
     * 
     *vvvvvvvvv CAN EDITING START HERE vvvvvvvvvvv*/
    
    LeftFrontMotor(2),
    RightFrontMotor(3),
    LeftRearMotor(1),
    RightRearMotor(4),

    // LeftFrontEncoderA(1, Robot.bot.driveTrain),
    // LeftFrontEncoderB(1, Robot.bot.driveTrain),
    // RightFrontEncoderA(3, Robot.bot.driveTrain),
    // RightFrontEncoderB(3, Robot.bot.driveTrain),
    // LeftRearEncoderA(2, Robot.bot.driveTrain),
    // LeftRearEncoderB(2, Robot.bot.driveTrain),
    // RightRearEncoderA(4, Robot.bot.driveTrain),
    // RightRearEncoderB(4, Robot.bot.driveTrain),

     /*^^^^^^^^^^ CAN EDITING END HERE ^^^^^^^^^^^^
     *
     * 
     */;// <= this isn't a stray semicolon, it's being used for the benefit of electronics


    Pin(int i)
    {
        id = i;
    }

    public final int id;
}