// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public final static int controllerPort = 0;  
  }
  
  public static class autoConstants{
    // Input auto constants
  }
  public static class driverConstants{
    public final static int rightUpDeviceID = 1; 
    public final static int leftUpDeviceID = 3;
    public final static int rightDownDeviceID = 2; 
    public final static int leftDownDeviceID = 4;

    public final static int controllerPort = 1;
    public final static double driveSpeed = 1.00;
    public final static int axisX = 4;
    public final static int axisY = 1;
    public final static double rate_limit = 0.5;
  }

  public static class intakeConstants{
    public final static int intakeDeviceID = 8; 
    public final static double intakeSpeed = 0.15; //WHATEVER YOU DO, DO NOT SET THIS TO 1!!!
    public final static int intakeButton = 2; 
  }

  public static class climberConstants{
    // Input climber constants
  }

  public static class shooterConstants{
    public final static int shooterPort = 0;
    public final static int shooterUpDeviceID = 5; 
    public final static int shooterDownDeviceID = 6;
    public final static double waitTime = 1;
    public final static int shootButton1 = 5;
    public final static int shootButton2 = 6;

    public final static double shooterSpeed = 1.00;
  }
}
