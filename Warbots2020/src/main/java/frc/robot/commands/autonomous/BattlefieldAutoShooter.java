/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.SpinUpFlywheel;
import frc.robot.commands.shooter.LoadShooter;
import frc.robot.subsystems.Shooter;
import frc.robot.util.Constants;

public class BattlefieldAutoShooter extends LoadShooter {
  /**
   * Creates a new BattlefieldAutoShooter.
   */
  public BattlefieldAutoShooter(Shooter s, SpinUpFlywheel spinUp) {
    super(s, spinUp);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  @Override
  public void execute() 
  {
    loader.set(1);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
