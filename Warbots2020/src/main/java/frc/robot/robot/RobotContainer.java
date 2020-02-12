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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.climber.*;
import frc.robot.loader.*;
import frc.robot.intake.*;
import frc.robot.shooter.*;
import frc.robot.drivetrain.*;

public class RobotContainer 
{
    // subsystems
    private final DriveTrain drivetrain = new DriveTrain();
    private final Climber climber = new Climber();
    private final Shooter shooter = new Shooter();
    private final Intake intake = new Intake();
    private final Loader loader = new Loader();    
   
    // OI
    XboxController driver = new XboxController(Constants.oi.driverControllerPort);
    XboxController operator = new XboxController(Constants.oi.operatorControllerPort);
    
    // Autonomous Selector Switches
    DigitalInput digitalInput0 = new DigitalInput(Constants.oi.autoModeSelectorInput0);
    DigitalInput digitalInput1 = new DigitalInput(Constants.oi.autoModeSelectorInput1);
    DigitalInput digitalInput2 = new DigitalInput(Constants.oi.autoModeSelectorInput2);
    DigitalInput digitalInput3 = new DigitalInput(Constants.oi.autoModeSelectorInput3);

    // commands
    Command autoCommand;
  
  public RobotContainer()
  {
    configureButtonBindings();
    
    // set default commands
    drivetrain.setDefaultCommand(new DriveWithJoysticks(drivetrain, driver));  
  }

  private void configureButtonBindings()
  {
     // Joystick Buttons
    final JoystickButton leftDriverBumper = new JoystickButton(driver, Button.kBumperLeft.value);
    final JoystickButton rightDriverBumper = new JoystickButton(driver, Button.kBumperRight.value);
    final JoystickButton leftOperatorBumper = new JoystickButton(operator, Button.kBumperLeft.value);
    final JoystickButton rightOperatorBumper = new JoystickButton(operator, Button.kBumperRight.value);
    final JoystickButton quickTurnButton = new JoystickButton(driver, Button.kA.value);
    final JoystickButton driverStartButton = new JoystickButton(driver, Button.kStart.value);
    final JoystickButton operatorStartButton = new JoystickButton(operator, Button.kStart.value);
     
    // Command bindings
    // TODO Finish button binders
    // TODO Add instant quickturn command
    quickTurnButton.whenPressed(new SetMaxDriveSpeed(drivetrain));
    leftDriverBumper.whenPressed(new Capture(intake));
    rightDriverBumper.whenPressed(new SetMaxDriveSpeed(drivetrain));
    leftOperatorBumper.whenPressed(new Capture(intake));
    rightOperatorBumper.whenPressed(new Load(loader));
  }

  public Command getAutonomousCommand() 
  {
    // Digital inputs 0-4 are connected to two 3-position toggle switches
    // Toggle switch 1 is starting position - left|middle|right
    // Toggle switch 2 is delay time - none|short|long
    // DIO Port 0 / DIO Port 1
    // 00 - middle
    // 01 - right
    // 10 - left
    // DIO Port 2 / DIO Port 3
    // 00 - none
    // 01 - short
    // 10 - long

    Boolean dio0 = digitalInput0.get();
    Boolean dio1 = digitalInput1.get();
    Boolean dio2 = digitalInput2.get();
    Boolean dio3 = digitalInput3.get();
    
    SmartDashboard.putBoolean("Digital Input 0", dio0);
    SmartDashboard.putBoolean("Digital Input 1", dio1);
    SmartDashboard.putBoolean("Digital Input 2", dio2);
    SmartDashboard.putBoolean("Digital Input 3", dio3);

    //TODO Complete Autonomous Commands
    if (!dio0 && !dio1)
    {
     // middle
    }
    else if (!dio0 & dio1)
    {
     // right
    }
    else
    {
      // left
    }

    if (!dio2 && !dio3)
    {
      // none
    }
    else if (!dio2 & dio3)
    {
      // short
    }
    else
    {
      // long
    }
    
    // final SitStill sitTight = new SitStill(drivetrain);
    // final DriveForward driveDistance = new DriveForward(drivetrain, -20); 
    // final SpinUp spinUp = new SpinUp(shooter, .2);

    return new AutonomousCommand(drivetrain);
  }
}

  
