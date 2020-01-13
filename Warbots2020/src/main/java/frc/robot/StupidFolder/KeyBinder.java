/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Robot.Robot;

public class KeyBinder 
{
    //region Constructors
    public KeyBinder(Robot bot)
    {
        driver = new XboxController(0);
        operator = new XboxController(1);
    }
    //endregion

    //region Fields
    public final XboxController driver;
    public final XboxController operator;
    //endregion
}
