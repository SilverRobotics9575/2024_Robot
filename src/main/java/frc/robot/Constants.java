// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static final). Do not put anything functional in this
 * class.
 *
 * <p>
 * It is advised to static finalally import this class (or one of its inner
 * classes) wherever the
 * constants are needed, to reduce verbosity.
 */


public final class Constants {

    public static final class OperatorConstants {
        public static final int    DRIVER_CONTROLLER_PORT         = 0;
        public static final double GAME_CONTROLLER_STICK_DEADBAND = .2;
    }

    public static final class AutoConstants {

        public static enum AutoPattern {
            DO_NOTHING, DRIVE_FORWARD, OTHER_AUTO
        };
    }

    public static final class DriverConstants {
        // Suggestion... RIGHT_FRONT_MOTOR_CAN_ID
        public static final int    RIGHT_FRONT_DEVICE_ID = 1;
        public static final int    LEFT_FRONT_DEVICE_ID  = 3;
        public static final int    RIGHT_BACK_DEVICE_ID  = 2;
        public static final int    LEFT_BACK_DEVICE_ID   = 4;

        public static final int    CONTROLLER_PORT       = 1;
        public static final double DRIVE_SPEED           = 1.00;
        public static final double RATE_LIMIT            = 0.5;
    }

    public static final class IntakeConstants {
        // Suggestion INTAKE_MOTOR_CAN_ID
        public static final int    INTAKE_DEVICE_ID = 8;
        public static final double INTAKE_SPEED     = 0.15; // WHATEVER YOU DO, DO NOT SET THIS TO
        // 1!
        public static final double MAX_INTAKE_SPEED = 0.3;
        // TODO: Remove this code - all button mappings are hard coded in the OperatorInput class
    }

    public static final class ClimberConstants {
        // Input climber constants
        public static final int    RIGHT_CLIMBER_DEVICE_ID = 9;
        public static final int    LEFT_CLIMBER_DEVICE_ID  = 10;
        public static final double CLIMBER_SPEED           = 0.6; // To be determined
    }

    public static final class ShooterConstants {
        
        public static final int    SHOOTER_MOTOR_CAN_ID   = 5;
        public static final int    FEEDER_MOTOR_CAN_ID = 6;
        public static final double WAIT_TIME                = 1;
        public static final int    SHOOT_BUTTON1            = 5;
        public static final int    SHOOT_BUTTON2            = 6;

        public static final double MAX_SHOOTER_SPEED        = 1.00;
    }
}
