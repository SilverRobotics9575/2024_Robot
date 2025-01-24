// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import com.studica.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

    private RobotContainer robotContainer;
    // private DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
    private AHRS navx = new AHRS(AHRS.NavXComType.kMXP_SPI);
    private DigitalInput proximitySensor;
    private Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        // Instantiate our RobotContainer. This will perform all our button bindings, and put our
        // autonomous chooser on the dashboard.
        robotContainer = new RobotContainer();

        // initialize proximity sensor on DIO 9
        proximitySensor = new DigitalInput(9);
    }

    /**
     * This function is called every 20 ms, no matter the mode. Use this for
     * items like diagnostics that you want ran during disabled, autonomous,
     * teleoperated and test.
     *
     * <p>
     * This runs after the mode specific periodic functions, but before
     * LiveWindow and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        // Runs the Scheduler. This is responsible for polling buttons, adding newly-scheduled
        // commands, running already-scheduled commands, removing finished or interrupted commands,
        // and running subsystem periodic() methods. This must be called from the robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     */
    @Override
    public void disabledInit() {
        // Nothing needed here for disabled iniit
    }

    @Override
    public void disabledPeriodic() {
        // Nothing needed here for disabled periodic 
    }

    /**
     * This autonomous runs the autonomous command selected by your
     * {@link RobotContainer} class.
     */
    @Override
    public void autonomousInit() {

        autonomousCommand = robotContainer.getAutonomousCommand();

        if (autonomousCommand != null) {
            autonomousCommand.schedule();
        }
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
        // Nothing needed here for periodic mode
    }

    @Override
    public void teleopInit() {
        robotContainer.getLEDSubsystem().setColor(0, 255, 0);

        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }

    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        // The if statements are here so you can control what gets outputted that way it won't flood
        // the terminal with unneeded data
        // Navx2 data being printed to terminal
        if (false) {
            System.out.println("Robot Angle: " + navx.getAngle());
            System.out.println("Robot Compass Heading: " + navx.getCompassHeading());
            System.out.println("Robot Raw Gyro X: " + navx.getRawGyroX());
            System.out.println("Robot Raw Gyro Y: " + navx.getRawGyroY());
            System.out.println("Robot Raw Gyro Z: " + navx.getRawGyroZ());
            System.out.println("Robot Raw Acceleration X: " + navx.getRawAccelX());
            System.out.println("Robot Raw Acceleration Y: " + navx.getRawAccelY());
            System.out.println("Robot Raw Acceleration Z: " + navx.getRawAccelZ());
        }

        // Prints whether or not the proximity sensor is detecting something
        if (false) {
            boolean isObjectDetected = proximitySensor.get();

            if (isObjectDetected) {
                System.out.println("Object Detected.");
            } else {
                System.out.println("No Object Detected.");
            }
        }
    }

    @Override
    public void testInit() {
        // Cancels all running commands at the start of test mode.
        CommandScheduler.getInstance().cancelAll();
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
        // No testing
    }

    /**
     * This function is called once when the robot is first started up.
     */
    @Override
    public void simulationInit() {
        // No simulating
    }

    /**
     * This function is called periodically whilst in simulation.
     */
    @Override
    public void simulationPeriodic() {
        // No simulating
    }
}
