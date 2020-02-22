/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.util;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class ClimberConstants {
        public static final double CLIMBER_UP_SPEED = 1;
        public static final double CLIMBER_DOWN_SPEED = -0.5;
    }

    public static final class IntakeConstants {
        public static final double ROLLER_SPEED_FORWARD = 0.4;
        public static final double ROLLER_SPEED_REVERSE = 0.4;
        public static final double CAPTURE_TIMER = 5;
    }

    public static final class DriveTrainConstants {
        public static final double AUTO_DRIVE_DISTANCE = 2.0;
        public static final double AUTO_WAIT_TIME = 0.0;
    }

    public static final class ShooterConstants {
        public static final double STUFF_SPEED = 0.25;
        public static final double SHOOT_SPEED = 0.27;
        public static final double FLYWHEEL_CONVERSION_FACTOR = 1.0;
    }

    public static final class LoaderConstants {
        public static final int LOADER_CURRENT_LIMIT = 20;
        public static final double LOADER_TIMEOUT = 5.0;
    }

    public static final class OIConstants {
        public static final int DRIVER_CONTROLER_PORT = 0;
        public static final int OPERATOR_CONTROLER_PORT = 1;
        public static final int AUTO_MODE_SELECTOR_INPUT_0 = 0;
        public static final int AUTO_MODE_SELECTOR_INPUT_1 = 1;
        public static final int AUTO_MODE_SELECTOR_INPUT_2 = 2;
        public static final int AUTO_MODE_SELECTOR_INPUT_3 = 3;
        public static final int AUTO_MODE_SELECTOR_INPUT_4 = 4;
    }

    public static final class BlingConstants 
    {
        public static final double OCEAN_COLORED_RAINBOW = -0.95;
        public static final double LARSON_SCANNER = -0.35;
        public static final double FAST_HEART_BEAT = 0.07;
        public static final double SOLID_BLUE = 0.87;
    }
}
