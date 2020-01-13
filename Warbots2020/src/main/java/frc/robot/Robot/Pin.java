/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Robot;

import edu.wpi.first.wpilibj2.command.Subsystem;

public enum Pin
{

    //vvvvvvvvv ELECTRONICS EDITING START HERE vvvvvvvvvvv

    LeftFrontMotor(1, Robot.bot.driveTrain),
    RightFrontMotor(3, Robot.bot.driveTrain),
    LeftRearMotor(2, Robot.bot.driveTrain),
    RightRearMotor(4, Robot.bot.driveTrain),

    LeftFrontEncoderA(1, Robot.bot.driveTrain),
    LeftFrontEncoderB(1, Robot.bot.driveTrain),
    RightFrontEncoderA(3, Robot.bot.driveTrain),
    RightFrontEncoderB(3, Robot.bot.driveTrain),
    LeftRearEncoderA(2, Robot.bot.driveTrain),
    LeftRearEncoderB(2, Robot.bot.driveTrain),
    RightRearEncoderA(4, Robot.bot.driveTrain),
    RightRearEncoderB(4, Robot.bot.driveTrain),

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
     */;// <= this isn't a stray semicolon, it's being used for the benefit of electronics


    Pin(int i, Subsystem ss)
    {
        id = i;
        claz = ss.getClass().getName();
    }

    public int id() throws IllegalArgumentException
    {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        var caller = stackTrace[2].getClassName();
        if(caller.equals(claz)) return id;
        throw new IllegalArgumentException();
    }

    private final int id;
    private final String claz;
}