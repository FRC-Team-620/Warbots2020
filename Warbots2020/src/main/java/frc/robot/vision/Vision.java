/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.vision;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision extends SubsystemBase {

    private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    // NetworkTableEntry tx = table.getEntry("tx");
    // NetworkTableEntry ty = table.getEntry("ty");
    // NetworkTableEntry ta = table.getEntry("ta");

    public Vision() 
    {
    }

    @Override
    public void periodic() {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("stream").setNumber(0);
        //NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(0);
        // // read values periodically
        // double x = tx.getDouble(0.0);
        // double y = ty.getDouble(0.0);
        // double area = ta.getDouble(0.0);

        // // post to smart dashboard periodically
        // SmartDashboard.putNumber("LimelightX", x);
        // SmartDashboard.putNumber("LimelightY", y);
        // SmartDashboard.putNumber("LimelightArea", area);
    }

    public double getTx()
    {
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0.0);
    }

    public void turnOffLights()
    {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    }

    public void turnOnLights()
    {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
    }

    public double getRPM()
    {
        var theta = (double) NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getNumber(0);

        var d = 0.2032;
        var hg = 2.4384;
        var hc = 0.4572;
        var gamma = 37.0;
        var alpha = 40.0;
        var hs = 0.6096;
        var g = 9.81;

        var num = d + (hg - hc)/Math.tan(gamma + theta);
        var denom = Math.cos(alpha) * Math.sqrt(2 * (hg - hs)/g);
        var v0 = num/denom;
        var rpm = (v0 / 0.1596) * 60;

        return rpm;
    }
}
