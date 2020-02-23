/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.Constants;
import frc.robot.util.Pin;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveTrain extends SubsystemBase 
{

    private final DifferentialDrive diffDrive;
    private final AHRS navX;
    private CANSparkMax lf, rf, rr, lr;

    public DriveTrain()
 {

        lf = new CANSparkMax(Pin.LeftFrontMotor.id, MotorType.kBrushless);
        rf = new CANSparkMax(Pin.RightFrontMotor.id, MotorType.kBrushless);
        lr = new CANSparkMax(Pin.LeftRearMotor.id, MotorType.kBrushless);
        rr = new CANSparkMax(Pin.RightRearMotor.id, MotorType.kBrushless);

        lf.restoreFactoryDefaults();
        lr.restoreFactoryDefaults();
        rf.restoreFactoryDefaults();
        rr.restoreFactoryDefaults();

        var mode = IdleMode.kBrake;
        lf.setIdleMode(mode);
        lr.setIdleMode(mode);
        rf.setIdleMode(mode);
        rr.setIdleMode(mode);
                         
        var openLoopRampRate = 0.75;
        lf.setOpenLoopRampRate(openLoopRampRate);
        lr.setOpenLoopRampRate(openLoopRampRate);
        rf.setOpenLoopRampRate(openLoopRampRate);
        rr.setOpenLoopRampRate(openLoopRampRate);

        var currentLimit = 38;
        lf.setSmartCurrentLimit(currentLimit);
        lr.setSmartCurrentLimit(currentLimit);
        rf.setSmartCurrentLimit(currentLimit);
        rr.setSmartCurrentLimit(currentLimit);

        lf.follow(lr, false);
        rf.follow(rr, false);

        diffDrive = new DifferentialDrive(lr, rr);
        diffDrive.setDeadband(0.05);

        navX = new AHRS(Constants.DriveTrainConstants.NAV_X_USB);

        resetDistance();
    }

    public void stop() {
        diffDrive.stopMotor();
    }

    public void arcadeInput(double speed, double rotation) {
        diffDrive.arcadeDrive(speed, rotation);
    }

    public double getAvgMotorTemp() {
        return (lf.getMotorTemperature() + lr.getMotorTemperature() + rr.getMotorTemperature()
                + rf.getMotorTemperature()) / 4;
    }

    public void curvatureInput(double speed, double rotation, boolean isCurvartureDrive) {
        diffDrive.curvatureDrive(speed, rotation, isCurvartureDrive);
    }

    public double getYaw() {
        return navX.getYaw();
    }

    public void resetYaw() {
        navX.zeroYaw();
    }

    public double getDistance() 
    {
        double distance = -lr.getEncoder().getPosition() 
            * Constants.DriveTrainConstants.DRIVE_CONVERSION_FACTOR
            * Constants.DriveTrainConstants.DRIVE_FUDGE_FACTOR;
        SmartDashboard.putNumber("Drive Distance Left", distance);

        return (distance);
    }

    public void resetDistance() {
        lr.getEncoder().setPosition(0);
        rr.getEncoder().setPosition(0);
    }

    @Override
    public void periodic() {
    }
}