// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.ShooterSubsystem;

/** Creates a new ShooterCommand */
// TODO: Test command on robot before making default shooter command
public class ShootCommand extends LoggingCommand {

    private final ShooterSubsystem shooterSubsystem;

    private long                   startTime;
    private long                   currentTime;

    // The commands constructor
    public ShootCommand(ShooterSubsystem shooterSubsystem) {
        addRequirements(shooterSubsystem);
        this.shooterSubsystem = shooterSubsystem;
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

        // TODO: In the LoggingCommand, the start time of the command
        // is captured, and there is a method to check if a time has
        // elapsed since the command was initialized
        // hasElapsed(.5); // checks if .5 seconds has elapsed
        // since the command started.
        // There is also a wpilib Timer class that does similar logic

        // NOTE: The logic below is PERFECT! 10/10 - WELL DONE !!
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
        logCommandEnd(interrupted);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        //TODO: Again, using the if (hasElapsed(1.0)) would be better.
        // But this code is PERFECT.
        currentTime = System.currentTimeMillis();

        if (currentTime - startTime >= 1000) {
            // FIXME: Set the reason code, do not print here
            // the reason code will get printed as part of logCommandEnd()
            // setFinishReason("Shot completed");

            System.out.println("Shooter command ended.");
            // Not sure how to use this method
            return true;
        }

        // TODO: As a matter of style, this does not need to be in
        // an else{} statement. There may be multiple reasons that a command will
        // end, and if we have not found one, and returned true, then we should
        // return false.
        else {
            return false;
        }
    }
}
