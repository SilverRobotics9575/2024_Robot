package frc.robot.commands;

import frc.robot.Constants.IntakeConstants;
import frc.robot.operator.OperatorInput;
import frc.robot.subsystems.IntakeSubsystem;

public class DefaultIntakeCommand extends LoggingCommand {

    private final OperatorInput oi;
    private final IntakeSubsystem intakeSubsystem;

    public DefaultIntakeCommand(OperatorInput oi, IntakeSubsystem intakeSubsystem) {

        this.oi = oi;
        this.intakeSubsystem = intakeSubsystem;

        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize() {
        logCommandStart();
    }

    @Override
    public void execute() {

        // If B button pressed, begin the intake motor.
        if (oi.runIntake()) {
            intakeSubsystem.setIntakeSpeed(IntakeConstants.INTAKE_SPEED);
        } else {
            intakeSubsystem.stop();
        }
    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.stop();
        logCommandEnd(interrupted);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
