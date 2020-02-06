/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class IntakeConstants {
        private static final double k_RollerSpeedForward = 0.2; // Placeholder value of 0.2
        private static final double k_RollerSpeedReverse = 0.2;
        private static final double k_CaptureTimer = 5;
    }
    public static final class DriveTrainConstants {
        private static final double k_encoderConversionFactor = 1;
        private static final double k_openLoopRampRate = 0.5;
        private static final double currentLimit = 20;
        private static final double deadBand = 0.05;
    }
    public static final class ShooterConstants {
        private static final double spinRate = 0.25;
    }
    public static final class OIConstants {
        public static final int kDriverControllerPort = 0;
        public static final int kOperatorControllerPort = 1;
      }
}
