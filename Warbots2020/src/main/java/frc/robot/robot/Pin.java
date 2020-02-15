/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.robot;

import java.security.InvalidParameterException;

public enum Pin
{
    //vvvvvvvvv EDITING START HERE vvvvvvvvvvv

    //region Drivetrain
    LeftFrontMotor(1, CommsStandard.CAN),
    RightFrontMotor(3, CommsStandard.CAN),
    LeftRearMotor(2, CommsStandard.CAN),
    RightRearMotor(4, CommsStandard.CAN),
    //endregion

    //region Mechanisms
    LoaderMotor(5, CommsStandard.CAN),  // T1
    IntakeMotor(6, CommsStandard.CAN),  // T2
    SpinnerMotor(7, CommsStandard.CAN), // T3
    LifterMotor(8, CommsStandard.CAN),  // T4
    ShooterMotor1(9, CommsStandard.CAN),
    ShooterMotor2(10, CommsStandard.CAN),
    ClimberMotor(11, CommsStandard.CAN),
    //endregion

    //region Sensors
    BallLoadedLimitSwitch(4, CommsStandard.DIO),
    BlingLights(3, CommsStandard.PWM),
    //endregion

    //region Servos
    climberActuatorUpper(0, CommsStandard.PWM),
    climberActuatorLower(1, CommsStandard.PWM),
    blingLights(3, CommsStandard.PWM),
    //endregion

    /*^^^^^^^^^^ EDITING END HERE ^^^^^^^^^^^^
     *
     * How to use the Pin document:
     *
     * MYPIN(3, CommsStandard.PWM),
     * YOURPIN(4, CommsStandard.CAN),
     * THEIRPIN(2, CommsStandard.PWM),
     *    ^     ^          ^           ^
     *    ^     ^     Comms Standard   ^
     *  Name    ^                      ^
     *          ^          Comma at the End of a Value
     *  Number on Rio or Bus
     * 
     * 
     * 
     */;// <= this isn't a stray semicolon, it's being used for the benefit of electronics
     
    //region Constructors
     Pin(int i, CommsStandard s)
    {
        id = i;
        std = s;
    }
    //endregion  c  

    //region Methods
    public static Pin getPin(int i, CommsStandard s) throws InvalidParameterException
    {
        for(Pin p : values())
        {
            if(p.id == i && p.std == s) return p;
        }
        throw new InvalidParameterException(); //TODO: safeguard for competition
    }
    //endregion

    //region Fields
    public final int id;
    public final CommsStandard std;
    //endregion
}