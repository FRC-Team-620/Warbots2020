/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.bling.Bling;

import frc.robot.commands.CaptureIntake;
import frc.robot.commands.CommandFlyWheel;
import frc.robot.commands.EjectIntake;
import frc.robot.commands.SpinUpFlywheel;
import frc.robot.commands.StuffFlyWheel;
import frc.robot.commands.autonomous.AutoLeft;
import frc.robot.commands.autonomous.AutoMiddle;
import frc.robot.commands.autonomous.AutoRight;
import frc.robot.commands.climber.ExtendClimber;
import frc.robot.commands.climber.ReleaseLowerArmClimber;
import frc.robot.commands.climber.ReleaseUpperArmClimber;
import frc.robot.commands.climber.RetractClimber;
import frc.robot.commands.drivetrain.DriveStraight;
import frc.robot.commands.drivetrain.DriveWithJoysticks;
import frc.robot.commands.drivetrain.TurnToAngle;
import frc.robot.commands.shooter.LoadShooter;
import frc.robot.dashboard.Dashboard;
import frc.robot.dashboard.Update;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.util.Constants;
import frc.robot.util.ThreeWaySwitch;
import frc.robot.vision.Vision;

public class RobotContainer {
    // subsystems
    private final DriveTrain drivetrain = new DriveTrain();
    public final Climber climber = new Climber();
    private final FlyWheel flyWheel = new FlyWheel();
    private final Intake intake = new Intake();
    private final Shooter shooter = new Shooter();
    private final Bling bling = new Bling();
    private final Vision vision = new Vision();
    private final PowerDistributionPanel pdp = new PowerDistributionPanel();
    private final Dashboard dashboard = new Dashboard();
    
    // Autonomous Selector Switches
    ThreeWaySwitch autoSelector = new ThreeWaySwitch(Constants.OIConstants.AUTO_MODE_SELECTOR_INPUT_0,
            Constants.OIConstants.AUTO_MODE_SELECTOR_INPUT_1);
    ThreeWaySwitch delaySelector = new ThreeWaySwitch(Constants.OIConstants.AUTO_MODE_SELECTOR_INPUT_2,
            Constants.OIConstants.AUTO_MODE_SELECTOR_INPUT_3);

    // Autonomous data
    public SequentialCommandGroup m_autocommand;
    double waitTime;
    final int startingSide = autoSelector.getPosition(); // from 0-2
    final double waitingTime = delaySelector.getPosition() * 3; // from 0-2 and converts to seconds

    // OI
    XboxController driver = new XboxController(Constants.OIConstants.DRIVER_CONTROLER_PORT);
    XboxController operator = new XboxController(Constants.OIConstants.OPERATOR_CONTROLER_PORT);

    public RobotContainer() {
        configureButtonBindings();
        populateDashboard();
        
        // Start USB Camera
        CameraServer.getInstance().startAutomaticCapture();
        
        // set default commands
        drivetrain.setDefaultCommand(new DriveWithJoysticks(drivetrain, driver));
        dashboard.setDefaultCommand(new Update(dashboard));

        // Sets up Autonomous
        setAutonomous();
    }

    private void populateDashboard() {
        SmartDashboard.putNumber("Auto Selector", autoSelector.getPosition());
        SmartDashboard.putNumber("Wait Selector", delaySelector.getPosition());
        dashboard.addCommand("Auto Middle Command",
                new AutoMiddle(drivetrain, flyWheel, shooter, 0));
        // dashboard.addCommand("Drive Back",
        //         new DriveStraight(drivetrain, -Constants.DriveTrainConstants.AUTO_DRIVE_DISTANCE));
        dashboard.addCommand("TurnToAngle", new TurnToAngle(-90, drivetrain));
        dashboard.addCommand("CaptureIntake", new CaptureIntake(intake));
        dashboard.addCommand("EjectIntake", new EjectIntake(intake));
        dashboard.addCommand("ReleaseLowerArmClimber", new ReleaseLowerArmClimber(climber));
        dashboard.addCommand("ReleaseUpperArmClimber", new ReleaseUpperArmClimber(climber));
        dashboard.addCommand("ExtendClimber", new ExtendClimber(climber));
        dashboard.addCommand("RetractClimber", new RetractClimber(climber, Constants.ClimberConstants.CLIMBER_UP_SPEED));
        dashboard.addCommand("SpinUpFlyWheel", new CommandFlyWheel(flyWheel, Constants.ShooterConstants.SHOOT_SPEED));
        dashboard.addCommand("StuffFlyWheel", new StuffFlyWheel(flyWheel));
        SmartDashboard.putNumber("Distance", drivetrain.getDistance()); //TODO distance will not update
        
        dashboard.addSubsystem(drivetrain); // Should show the navx
    }
    private void configureButtonBindings() {

        /*
         * Operator Controls
         */

        JoystickButton operatorLeftBumper = new JoystickButton(operator, Button.kBumperLeft.value);
        operatorLeftBumper.whileHeld(new CaptureIntake(intake));

        JoystickButton operatorB = new JoystickButton(operator, Button.kB.value);
        operatorB.whileHeld(new RetractClimber(climber, Constants.ClimberConstants.CLIMBER_UP_SPEED));

        JoystickButton operatorY = new JoystickButton(operator, Button.kY.value);
        operatorY.whileHeld(new RetractClimber(climber, Constants.ClimberConstants.CLIMBER_DOWN_SPEED));

        JoystickButton operatorX = new JoystickButton(operator, Button.kX.value);
        var spinUp = new SpinUpFlywheel(flyWheel, Constants.ShooterConstants.STUFF_SPEED);
        operatorX.whenPressed(spinUp);
        //operatorX.whenPressed(new CommandFlyWheel(flyWheel, Constants.ShooterConstants.SHOOT_SPEED));
        //operatorX.whenReleased(new CommandFlyWheel(flyWheel, 0));

        JoystickButton operatorRightBumper = new JoystickButton(operator, Button.kBumperRight.value);
        operatorRightBumper.whenPressed(new LoadShooter(shooter, spinUp)).whenPressed(spinUp);

        //JoystickButton operatorA = new JoystickButton(operator, Button.kA.value);
        //operatorA.whenPressed(new FireShooter(shooter));


        /*
         * Driver Controls
         */

        final JoystickButton driverXButton = new JoystickButton(driver, Button.kX.value);
        //final JoystickButton operatorStartButton = new JoystickButton(operator, Button.kStart.value);

        final JoystickButton driverRightBumper = new JoystickButton(driver, Button.kBumperRight.value);
        final JoystickButton driverA = new JoystickButton(driver, Button.kA.value);
        
        driverA.whenPressed(new InstantCommand(drivetrain::setQuickTurn));
        driverA.whenReleased(new InstantCommand(drivetrain::clearQuickTurn));
        driverRightBumper.whenPressed(new SequentialCommandGroup(new InstantCommand(drivetrain::setSlowDown), new InstantCommand(bling::setSlowDrive)));
        driverRightBumper.whenReleased(new SequentialCommandGroup(new InstantCommand(drivetrain::clearSlowDown), new InstantCommand(bling::setDefault)));


        // TODO: Need to check if BOTH buttons are pressed
        driverXButton.whenPressed(new ExtendClimber(climber));
        //operatorStartButton.whenPressed(new ExtendClimber(climber));
    }

    public Command getAutonomousCommand() {
        return m_autocommand;
    }

    // Sets up Autonomous
    public void setAutonomous() {

        // Sets the wait times
        if(delaySelector.getPosition() == 0) {
            waitTime = 0.0;
        }
        else if(delaySelector.getPosition() == 1) {
            waitTime = 5.0;
        }
        else {
            waitTime = 10.0;
        }
    
        if(autoSelector.getPosition() == 0) { // Left side
            m_autocommand = new AutoLeft(drivetrain, flyWheel, shooter, waitTime);
        }
        else if(autoSelector.getPosition() == 1) { // Middle side
            m_autocommand = new AutoMiddle(drivetrain, flyWheel, shooter, waitTime);
        }
        else { // Right side
            m_autocommand = new AutoRight(drivetrain, flyWheel, shooter, waitTime);
        }
    
    }
}
