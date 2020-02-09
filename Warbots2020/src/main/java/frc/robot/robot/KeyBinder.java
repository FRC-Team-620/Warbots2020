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
import frc.robot.drivetrain.SetMaxDriveSpeed;
import frc.robot.intake.Capture;
import frc.robot.loader.Load;
import frc.robot.shooter.Fire;

public class KeyBinder 
{
    public final XboxController driver;
    public final XboxController operator;
    DigitalInput digitalInput0;
    DigitalInput digitalInput1;
    DigitalInput digitalInput2;
    DigitalInput digitalInput3;
    
    public KeyBinder(RobotContainer bot)            //TODO follow WPILib example code and create a configureButtonBindings method
    {
        // Controllers
        driver = new XboxController(Constants.Keybinder.driverControllerPort);
        operator = new XboxController(Constants.Keybinder.operatorControllerPort);

        // Joystick Buttons
        JoystickButton leftDriverBumper = new JoystickButton(driver, Button.kBumperLeft.value);
        JoystickButton rightDriverBumper = new JoystickButton(driver, Button.kBumperRight.value);
        JoystickButton leftOperatorBumper = new JoystickButton(operator, Button.kBumperLeft.value);
        JoystickButton rightOperatorBumper = new JoystickButton(operator, Button.kBumperRight.value);
         
        // Command bindings
        leftDriverBumper.whenPressed(new SetMaxDriveSpeed(bot.drivetrain));
        rightDriverBumper.whenPressed(new Load(bot.loader));
        leftOperatorBumper.whenPressed(new Capture(bot.intake));
        rightOperatorBumper.whenPressed(new Fire(bot.shooter));
     
        // Autonomous Selector Switches
        digitalInput0 = new DigitalInput(Constants.Keybinder.autoModeSelectorInput0);
        digitalInput1 = new DigitalInput(Constants.Keybinder.autoModeSelectorInput1);
        digitalInput2 = new DigitalInput(Constants.Keybinder.autoModeSelectorInput2);
        digitalInput3 = new DigitalInput(Constants.Keybinder.autoModeSelectorInput3);

        //TODO - map selector switches to autonomous command parameters
    }
 }