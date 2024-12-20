// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.ShooterSubsystem;

// FIXME: Extend the LoggingCommand and implement logging - see DefaultIntakeCommand


/** Creates a new ShooterCommand */
// TODO: Test command on robot before making default shooter command
public class ShootCommand extends LoggingCommand {

    private final ShooterSubsystem shooterSubsystem;

    private long                   startTime;
    private long                   currentTime;

    // The commands constructor
    public ShootCommand(ShooterSubsystem shooterSubsystem) {
        this.shooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);
    }

    // Initial code that runs once when the command is called
    @Override
    public void initialize() {
        shooterSubsystem.setTopMotorSpeed(ShooterConstants.MAX_SHOOTER_SPEED);
        startTime = System.currentTimeMillis();
        
        logCommandStart();
    }

    // Called every time the scheduler runs while the command is scheduled.
    // Runs every 20ms
    @Override
    public void execute() {
        currentTime = System.currentTimeMillis();

        // Check if 0.5 seconds have passed
        if (currentTime - startTime < 500) {
            return;
        }
        shooterSubsystem.setBottomMotorSpeed(ShooterConstants.MAX_SHOOTER_SPEED);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        shooterSubsystem.stop();
        log("Shooter command ended.");
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        currentTime = System.currentTimeMillis();

        if (currentTime - startTime >= 1000) {
            System.out.println("Shooter command ended.");
            // Not sure how to use this method
            return true;
        }
        else {
            return false;
        }
    }
}
