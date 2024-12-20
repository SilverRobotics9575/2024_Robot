package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends Command {
    private final XboxController  joystick;
    private final IntakeSubsystem intakeSubsystem;

    public IntakeCommand(IntakeSubsystem intakeSubsystem, XboxController controller) {
        joystick             = controller;
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void execute() {
        // If the button specified in the constants is pressed, begin the intake motor.
        if (joystick.getRawButtonPressed(IntakeConstants.INTAKE_BUTTON)) {
            intakeSubsystem.intake();
        }
        // Add an else to stop
        if (joystick.getRawButtonReleased(IntakeConstants.INTAKE_BUTTON)) {
            intakeSubsystem.stop();
        }

    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
