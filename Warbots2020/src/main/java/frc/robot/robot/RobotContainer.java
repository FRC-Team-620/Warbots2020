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
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.climber.*;
import frc.robot.loader.*;
import frc.robot.intake.*;
import frc.robot.shooter.*;
import frc.robot.drivetrain.*;

public class RobotContainer 
{
    // subsystem creation
    private final DriveTrain drivetrain = new DriveTrain();
    private final Climber climber = new Climber();
    private final Shooter shooter = new Shooter();
    private final Intake intake = new Intake();
    private final Loader loader = new Loader();    
   
    XboxController driver = new XboxController(Constants.Keybinder.driverControllerPort);
    XboxController operator = new XboxController(Constants.Keybinder.operatorControllerPort);
    
    // Autonomous Selector Switches
    DigitalInput digitalInput0 = new DigitalInput(Constants.Keybinder.autoModeSelectorInput0);
    DigitalInput digitalInput1 = new DigitalInput(Constants.Keybinder.autoModeSelectorInput1);
    DigitalInput digitalInput2 = new DigitalInput(Constants.Keybinder.autoModeSelectorInput2);
    DigitalInput digitalInput3 = new DigitalInput(Constants.Keybinder.autoModeSelectorInput3);

    // private final Command autoCommand = new InstantCommand(shooter::SpinUp, shooter);
  
  public RobotContainer()
  {

    configureButtonBindings();
    
    // commands
    // TODO: move to autonomous command
    // final SitStill sitTight = new SitStill(drivetrain);
    // final DriveForward driveDistance = new DriveForward(drivetrain, -20); //TODO: figure out all of the trickle down negatives and fix them
    // final SpinUp spinUp = new SpinUp(shooter, .2);

    // Joystick Buttons
    final JoystickButton leftDriverBumper = new JoystickButton(driver, Button.kBumperLeft.value);
    final JoystickButton rightDriverBumper = new JoystickButton(driver, Button.kBumperRight.value);
    final JoystickButton leftOperatorBumper = new JoystickButton(operator, Button.kBumperLeft.value);
    final JoystickButton rightOperatorBumper = new JoystickButton(operator, Button.kBumperRight.value);
     
    // Command bindings
    // leftDriverBumper.whenPressed(new SetMaxDriveSpeed(container.drivetrain));
    rightDriverBumper.whenPressed(new Load(loader));
    leftDriverBumper.whenPressed(new Capture(intake));

  }

  public Command getAutonomousCommand() 
  {
    return new TestAutoCommand(drivetrain);
  }

  private void configureButtonBindings()
    {
        //default commands
      drivetrain.setDefaultCommand(new DriveWithJoysticks(drivetrain, driver));
     
        //TODO - map selector switches to autonomous command parameters
    }
}
