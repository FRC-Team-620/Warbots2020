/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.OIConstants;
import frc.robot.climber.Climber;
import frc.robot.driveTrain.DriveForward;
import frc.robot.driveTrain.DriveTrain;
import frc.robot.driveTrain.DriveWithJoysticks;
import frc.robot.driveTrain.SitStill;
import frc.robot.driveTrain.TestAutoCommand;
import frc.robot.intake.Intake;
import frc.robot.shooter.Shooter;

/**
 * Add your docs here.
 */
public class RobotContainer 
{
   // The robot's subsystems
   private final DriveTrain m_driveTrain = new DriveTrain();
   private final Climber m_climber = new Climber();
   private final Intake m_intake = new Intake();
   private final Shooter m_shooter = new Shooter();
 
   // private final Command m_autoCommand = new InstantCommand(m_driveTrain::DriveForward);
  
   // Xbox Controllers
    XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
    XboxController m_operatorController = new XboxController(OIConstants.kOperatorControllerPort);

    //region Constructors
    public RobotContainer()
    {
        keys = new KeyBinder(this);

        driveTrain = new DriveTrain();
        driveWithJoysticks = new DriveWithJoysticks(driveTrain, keys.driver);
        driveTrain.setDefaultCommand(driveWithJoysticks);
        sitTight = new SitStill(driveTrain);
        driveDistance = new DriveForward(driveTrain, 1);
        test = new TestAutoCommand(driveTrain);
    }
    //endregion
    
   /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return test;
  }

  //region OI
    public KeyBinder keys;
    //endregion

    //region Subsystems
    public DriveTrain driveTrain;
    //endregion

    //region Commands
    protected SitStill sitTight;
    protected DriveWithJoysticks driveWithJoysticks;
    protected DriveForward driveDistance;
    protected TestAutoCommand test;
    //endregion
}
