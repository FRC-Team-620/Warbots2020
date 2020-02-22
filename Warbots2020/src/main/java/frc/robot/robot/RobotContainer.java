/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.bling.Bling;
import frc.robot.commands.CaptureIntake;
import frc.robot.commands.ExtendClimber;
import frc.robot.commands.LoadShooter;
import frc.robot.commands.RetractClimber;
import frc.robot.commands.SpinUpFlyWheel;
import frc.robot.commands.autonomous.AutonomousCommand;
import frc.robot.commands.drivetrain.DriveForward;
import frc.robot.commands.drivetrain.DriveWithJoysticks;
import frc.robot.commands.drivetrain.TurnToAngle;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.util.Constants;
import frc.robot.subsystems.FlyWheel;
import frc.robot.dashboard.*;
import frc.robot.vision.*;

public class RobotContainer
{
    // subsystems
    private final DriveTrain drivetrain = new DriveTrain();
    private final Climber climber = new Climber();
    private final FlyWheel flyWheel = new FlyWheel();
    private final Intake intake = new Intake();
    private final Shooter shooter = new Shooter();
    private final Bling bling;
    private final Vision vision = new Vision();
    private final Dashboard dashboard = new Dashboard(drivetrain, climber, shooter, flyWheel, new PowerDistributionPanel());

    // OI
    XboxController driver = new XboxController(Constants.OIConstants.DRIVER_CONTROLER_PORT);
    XboxController operator = new XboxController(Constants.OIConstants.OPERATOR_CONTROLER_PORT);

    // Autonomous Selector Switches
    DigitalInput digitalInput0 = new DigitalInput(Constants.OIConstants.AUTO_MODE_SELECTOR_INPUT_0);
    DigitalInput digitalInput1 = new DigitalInput(Constants.OIConstants.AUTO_MODE_SELECTOR_INPUT_1);
    DigitalInput digitalInput2 = new DigitalInput(Constants.OIConstants.AUTO_MODE_SELECTOR_INPUT_2);
    DigitalInput digitalInput3 = new DigitalInput(Constants.OIConstants.AUTO_MODE_SELECTOR_INPUT_3);

    public RobotContainer() 
    {
        configureButtonBindings();

        bling = new Bling();

        // set default commands
        drivetrain.setDefaultCommand(new DriveWithJoysticks(drivetrain, driver));
        dashboard.setDefaultCommand(
                new Update(dashboard, drivetrain, climber, flyWheel, intake, shooter, bling, vision));
    }

    private void configureButtonBindings() 
    {
        // Joystick Buttons
        final JoystickButton leftOperatorBumper = new JoystickButton(operator, Button.kBumperLeft.value);
        final JoystickButton rightOperatorBumper = new JoystickButton(operator, Button.kBumperRight.value);
        final JoystickButton driverStartButton = new JoystickButton(driver, Button.kStart.value);
        final JoystickButton operatorStartButton = new JoystickButton(operator, Button.kStart.value);
        final JoystickButton operatorBButton = new JoystickButton(operator, Button.kB.value);
        final JoystickButton operatorX = new JoystickButton(operator, Button.kX.value);

        // Command bindings
        if(intake != null)
        {
            var capture = new CaptureIntake(intake);
            leftOperatorBumper.whileHeld(capture);
        }
        if(climber != null)
        {
            var extend = new ExtendClimber(climber);
            var retract = new RetractClimber(climber, Constants.ClimberConstants.CLIMBER_SPEED);
            driverStartButton.whenPressed(extend);
            operatorBButton.whileHeld(retract);
        }
        if(flyWheel != null)
        {
            SpinUpFlyWheel spinUp = new SpinUpFlyWheel(flyWheel, Constants.ShooterConstants.SHOOT_SPEED);
            operatorX.whenPressed(spinUp);
            if(shooter != null)
            {
                var load = new LoadShooter(shooter, spinUp, flyWheel);
                rightOperatorBumper.whenPressed(load);
            }
        }

        var capture = new CaptureIntake(intake);
        var extend = new ExtendClimber(climber);
        var retract = new RetractClimber(climber, Constants.ClimberConstants.CLIMBER_SPEED);
        SpinUpFlyWheel spinUp = new SpinUpFlyWheel(flyWheel, Constants.ShooterConstants.SHOOT_SPEED);
        var testLoad = new LoadShooter(shooter, spinUp, flyWheel);

        leftOperatorBumper.whileHeld(capture);
        rightOperatorBumper.whenPressed(testLoad);
        driverStartButton.whenPressed(extend);
        operatorBButton.whileHeld(retract);
        operatorX.whenPressed(spinUp);
    }

    public Command getAutonomousCommand() {
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
        
        //Autonomous Variables
        final int startingSide;
        final double waitingTime;

        final Boolean dio0 = digitalInput0.get();
        final Boolean dio1 = digitalInput1.get();
        final Boolean dio2 = digitalInput2.get();
        final Boolean dio3 = digitalInput3.get();

        SmartDashboard.putBoolean("Digital Input 0", dio0);
        SmartDashboard.putBoolean("Digital Input 1", dio1);
        SmartDashboard.putBoolean("Digital Input 2", dio2);
        SmartDashboard.putBoolean("Digital Input 3", dio3);

        if (!dio0 && !dio1) {
            // middle
            startingSide = 1;
        } else if (!dio0 & dio1) {
            // right
            startingSide = 2;
        } else {
            // left
            startingSide = 0;
        }

        if (!dio2 && !dio3) {
            // none
            waitingTime = 0.0;
        } else if (!dio2 & dio3) {
            // short
            waitingTime = 3.0;
        } else {
            // long
            waitingTime = 7.0;
        }

       // return new AutonomousCommand(drivetrain, startingSide, waitingTime);
       //return new DriveForward(drivetrain, Constants.DriveTrainConstants.AUTO_DRIVE_DISTANCE);
        drivetrain.resetYaw();
        System.out.println(drivetrain.getYaw());
        return new TurnToAngle(90, drivetrain);
    }
}
