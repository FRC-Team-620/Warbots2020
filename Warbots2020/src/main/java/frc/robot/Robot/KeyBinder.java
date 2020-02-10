/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class KeyBinder 
{
    //region Constructors
    public KeyBinder(RobotContainer bot)
    {
        driver = new XboxController(Constants.Keybinder.driverControllerPort);
        operator = new XboxController(Constants.Keybinder.operatorControllerPort);
        new JoystickButton(operator, Button.kBumperRight.value).whenPressed(bot.load);
        new JoystickButton(operator, Button.kB.value).whenPressed(bot.spinUp);
    }

    //endregion

    //region Fields
    public final XboxController driver;
    public final XboxController operator;
    DigitalInput digitalInput0;
    DigitalInput digitalInput1;
    DigitalInput digitalInput2;
    DigitalInput digitalInput3;
    //endregion
}