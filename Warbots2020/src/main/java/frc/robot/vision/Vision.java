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
        var verticalAngle = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getNumber(0).intValue();

        switch (verticalAngle)
        {
            case -14: return 3828;
            case -13: return 3653;
            case -12: return 3491;
            case -11: return 3341;
            case -10: return 3201;
            case -9: return 3071;
            case -8: return 2949;
            case -7: return 2834;
            case -6: return 2726;
            case -5: return 2625;
            case -4: return 2528;
            case -3: return 2437;
            case -2: return 2350;
            case -1: return 2268;
            case 0: return 2189;
            case 1: return 2114;
            case 2: return 2043;
            case 3: return 1974;
            case 4: return 1908;
            case 5: return 1845;
            case 6: return 1784;
            case 7: return 1725;
            case 8: return 1669;
            case 9: return 1614;
            case 10: return 1520;//1561;
            case 11: return 1510;
            case 12: return 1461;
            case 13: return 1413;
            case 14: return 1366;
            default: return 0;
        }
    }
}
