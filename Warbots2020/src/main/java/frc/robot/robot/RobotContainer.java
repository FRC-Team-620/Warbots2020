/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.DriveTrain.DriveTrain;
import frc.robot.DriveTrain.DriveWithJoysticks;
import frc.robot.DriveTrain.SitStill;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer
{
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public static void Initiate()
  {
    InitializeFields();
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private static void configureButtonBindings() 
  {
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public static Command getAutonomousCommand() 
  {
    return sitTight;
  }

  private static void InitializeFields()
  {
    driverXBox = new XboxController(0);
    driveTrain = new DriveTrain();
    sitTight = new SitStill();
    driveWithJoysticks = new DriveWithJoysticks();
  }

  //OI:
  public static XboxController driverXBox;

  //Subsystems:
  public static DriveTrain driveTrain;

  //Commands:
  public static SitStill sitTight;
  public static DriveWithJoysticks driveWithJoysticks;
}
