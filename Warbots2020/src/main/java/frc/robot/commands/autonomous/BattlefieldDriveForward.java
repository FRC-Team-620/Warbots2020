/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import java.time.LocalDateTime;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class BattlefieldDriveForward extends CommandBase {
  private DriveTrain driveTrain;
  private LocalDateTime start;
  private LocalDateTime end;
  private Timer timer;
  public BattlefieldDriveForward(DriveTrain dt) 
  {
    driveTrain = dt;
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer = new Timer();
    //start = LocalDateTime.now().plusSeconds(11);
    //end = LocalDateTime.now().plusSeconds(13);
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    if(timer.get() > 11 && timer.get() < 13)
    {
      driveTrain.arcadeInput(.5, 0);
    }
    else     
    {
      driveTrain.arcadeInput(0, 0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    driveTrain.arcadeInput(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
