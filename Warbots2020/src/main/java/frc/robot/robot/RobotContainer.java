/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.robot;

import frc.robot.driveTrain.DriveForward;
import frc.robot.driveTrain.DriveTrain;
import frc.robot.driveTrain.DriveWithJoysticks;
import frc.robot.driveTrain.SitStill;
import frc.robot.driveTrain.TestAutoCommand;

/**
 * Add your docs here.
 */
public class RobotContainer 
{
    //region Constructors
    public RobotContainer()
    {
        keys = new KeyBinder(this);

        driveTrain = new DriveTrain();
        driveWithJoysticks = new DriveWithJoysticks(driveTrain, keys.driver);
        driveTrain.setDefaultCommand(driveWithJoysticks);
        sitTight = new SitStill(driveTrain);
        driveDistance = new DriveForward(driveTrain, -20);
        test = new TestAutoCommand(driveTrain);
    }
    //endregion
    
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
