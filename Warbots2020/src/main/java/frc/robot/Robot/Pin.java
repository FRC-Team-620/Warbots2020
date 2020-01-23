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
        
    LeftFrontMotor(1, CommsStandard.CAN),
    RightFrontMotor(3, CommsStandard.CAN),
    LeftRearMotor(2, CommsStandard.CAN),
    RightRearMotor(4, CommsStandard.CAN),

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
    //endregion

    //region Methods
    public static Pin getPin(int i, CommsStandard s) throws InvalidParameterException
    {
        for(Pin p : values())
        {
            if(p.id == i && p.std == s) return p;
        }
        throw new InvalidParameterException();
    }
    //endregion

    //region Fields
    public final int id;
    public final CommsStandard std;
    //endregion
}