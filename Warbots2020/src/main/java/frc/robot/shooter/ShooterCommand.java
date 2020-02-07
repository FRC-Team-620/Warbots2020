/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;

public abstract class ShooterCommand extends CommandBase 
{
  //region Constructors
  protected ShooterCommand(Shooter s, double speed)
  {
    shooter = s;
    addRequirements(shooter);
    targetVelocity = speed;
  }
  //endregion

  //region Fields
  protected final double targetVelocity;
  protected final Shooter shooter;
  //endregion
}
