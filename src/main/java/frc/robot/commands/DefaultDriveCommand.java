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
        double                    moveSpeed   = 0;
        double                    rotateSpeed = 0;
    
        moveSpeed = oi.getDriveSpeed();
        rotateSpeed = oi.getRotateSpeed();
  
        drivetrainSubsystem.drive(moveSpeed, rotateSpeed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {

        logCommandEnd(interrupted);
        drivetrainSubsystem.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
