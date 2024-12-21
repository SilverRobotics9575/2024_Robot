// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.operator.OperatorInput;
import frc.robot.subsystems.DrivetrainSubsystem;

/** Creates a new DriveCommand. */
public class DefaultDriveCommand extends LoggingCommand {

    private final OperatorInput       oi;
    private final DrivetrainSubsystem drivetrainSubsystem;

    // The drivetrain variables
    // FIXME: Not sure to declare them here or in the execute method
    // FIXME: RESPONSE: In the execute method
    private double                    moveSpeed   = 0;
    private double                    rotateSpeed = 0;

    // The commands constructor
    public DefaultDriveCommand(OperatorInput oi, DrivetrainSubsystem drivetrainSubsystem) {
        addRequirements(drivetrainSubsystem);
        this.oi                  = oi;
        this.drivetrainSubsystem = drivetrainSubsystem;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        logCommandStart();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

        // FIXME: Not sure what we are trying to do here?
        // The joystick deadbanding will be done in the GameController class, so
        // we do not need to check for zeros. If anything we want to actually stop the
        // robot if the driver lets go of the joystick. If there is a zero
        // value, we want to apply that speed or turn.
        //
        // drive = oi.getDriveSpeed
        // rotate = oi.getRotateSpeed
        // set the speeds (drive, rotate)

        // Checks if the left joystick is being moved
        // Sets the moveSpeed to the speed of the left joystick
        if (oi.move()) {
            moveSpeed = oi.getDriveSpeed();
        }
        // Checks if the right joystick is being moved
        // Sets the rotateSpeed to the speed of the right joystick
        if (oi.rotate()) {
            rotateSpeed = oi.getRotateSpeed();
        }
        drivetrainSubsystem.drive(moveSpeed, rotateSpeed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        // FIXME: Log the state before the command was interrupted.
        // ie. before stopping.
        drivetrainSubsystem.stop();
        logCommandEnd(interrupted);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
