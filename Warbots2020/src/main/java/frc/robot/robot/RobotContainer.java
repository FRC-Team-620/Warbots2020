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

import frc.robot.bling.Bling;
import frc.robot.climber.*;
import frc.robot.loader.*;
import frc.robot.intake.*;
import frc.robot.shooter.*;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Loader;
import frc.robot.subsystems.Shooter;
import frc.robot.dashboard.*;
import frc.robot.vision.*;
import frc.robot.drivetrain.*;

public class RobotContainer 
{
    // subsystems
    private final DriveTrain drivetrain = new DriveTrain();
    private final Climber climber = new Climber();
    private final Shooter shooter = new Shooter();
    private final Intake intake = new Intake();
    private final Loader loader = new Loader();   
    private final Bling bling = new  Bling();
    private final Vision vision = new Vision();
    private final Dashboard dashboard = new Dashboard(); 
   
    // OI
    XboxController driver = new XboxController(Constants.OIConstants.driverControllerPort);
    XboxController operator = new XboxController(Constants.OIConstants.operatorControllerPort);
    
    // Autonomous Selector Switches
    DigitalInput digitalInput0 = new DigitalInput(Constants.OIConstants.autoModeSelectorInput0);
    DigitalInput digitalInput1 = new DigitalInput(Constants.OIConstants.autoModeSelectorInput1);
    DigitalInput digitalInput2 = new DigitalInput(Constants.OIConstants.autoModeSelectorInput2);
    DigitalInput digitalInput3 = new DigitalInput(Constants.OIConstants.autoModeSelectorInput3);

    // commands
    private final Command autonomousCommand = new DriveForward(drivetrain, Constants.DriveTrainConstants.autoDriveDistance);
    
    /*
    new SpinUp(shooter, Constants.Shooter.spinRate).andThen(
        // Drive Forward
        new DriveForward(drivetrain, Constants.DriveTrain.autoDriveDistance),
        // Wait until the shooter is at speed before feeding the frisbees
        new WaitUntilCommand(shooter::atSetPoint),
        // Start running the feeder
        new Load(loader),
        // Shoot for the specified time
        new WaitCommand(Constants.Shooter.autoShootTimeSeconds))
        // Add a timeout (will end the command if, for instance, the shooter never gets up to
        // speed)
        .withTimeout(Constants.Shooter.autoTimeoutSeconds)
        // When the command ends, turn off the shooter and the feeder
        .andThen(() -> {
          // shooter.disable();
          // loader.disable();
        });*/

  public RobotContainer()
  {
    configureButtonBindings();

    // set default commands
    drivetrain.setDefaultCommand(new DriveWithJoysticks(drivetrain, driver));  
    dashboard.setDefaultCommand(new Update(dashboard, drivetrain, climber, shooter, intake, loader, bling, vision));
  }

  private void configureButtonBindings()
  {
     // Joystick Buttons
    final JoystickButton leftOperatorBumper = new JoystickButton(operator, Button.kBumperLeft.value);
    final JoystickButton rightOperatorBumper = new JoystickButton(operator, Button.kBumperRight.value);
    final JoystickButton driverStartButton = new JoystickButton(driver, Button.kStart.value);
    final JoystickButton operatorStartButton = new JoystickButton(operator, Button.kStart.value);
    final JoystickButton operatorX = new JoystickButton(operator, Button.kX.value);

    // Command bindings
    var capture = new Capture(intake);
    var extend = new Extend(climber);
    var retract = new Retract(climber, Constants.ClimberConstants.climberSpeed);
    SpinUp spinUp = new SpinUp(shooter, Constants.ShooterConstants.shootSpeed);
    var testLoad = new Load(loader, spinUp);

    leftOperatorBumper.whileHeld(capture);
    rightOperatorBumper.whenPressed(testLoad);
    driverStartButton.whenPressed(extend);  
    operatorStartButton.whenPressed(retract);  
    operatorX.whenPressed(spinUp);
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

    final Boolean dio0 = digitalInput0.get();
    final Boolean dio1 = digitalInput1.get();
    final Boolean dio2 = digitalInput2.get();
    final Boolean dio3 = digitalInput3.get();
    
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

    return autonomousCommand;
  }
}

  
