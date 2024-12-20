// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterCommand extends Command {
    /** Creates a new ShooterCommand */
    private final XboxController   joystick;
    // FIXME Hungarian Notation
    private final ShooterSubsystem shooterSubsystem;

    // The commands constructor
    public ShooterCommand(ShooterSubsystem shooterSubsystem, XboxController controller) {
        joystick              = controller;
        this.shooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // If the 5th button is pressed the shooter will prepare by speeding up the top
        // motor
        if (joystick.getRawButton(ShooterConstants.SHOOT_BUTTON1)) {
            shooterSubsystem.waitSeconds();
            // If the 5th button is being pressed and the 6th button is clicked then the
            // shooter
            // will shoot
        }
        if (joystick.getRawButton(ShooterConstants.SHOOT_BUTTON1)
            && joystick.getRawButtonPressed(ShooterConstants.SHOOT_BUTTON2)) {
            shooterSubsystem.shoot();
        }
        // If both buttons are released the shooter will stop
        if (joystick.getRawButtonReleased(ShooterConstants.SHOOT_BUTTON1)
            || joystick.getRawButtonReleased(ShooterConstants.SHOOT_BUTTON1)
                && joystick.getRawButtonReleased(ShooterConstants.SHOOT_BUTTON2)) {
            shooterSubsystem.stop();
        }

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        shooterSubsystem.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
