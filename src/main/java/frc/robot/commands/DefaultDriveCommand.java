// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.DriverConstants;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DefaultDriveCommand extends Command {
    /** Creates a new DriveCommand. */
    private final XboxController      joystick;
    private final DrivetrainSubsystem drivetrainSubsystem;

    // The commands constructor
    public DefaultDriveCommand(DrivetrainSubsystem drivetrainSubsystem, XboxController controller) {
        addRequirements(drivetrainSubsystem);
        joystick                 = controller;
        this.drivetrainSubsystem = drivetrainSubsystem;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        // The default command has started
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double moveSpeed   = joystick.getRawAxis(DriverConstants.AXIS_X);
        double rotateSpeed = joystick.getRawAxis(DriverConstants.AXIS_Y);

        drivetrainSubsystem.drive(moveSpeed, rotateSpeed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drivetrainSubsystem.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
