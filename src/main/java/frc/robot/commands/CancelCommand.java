package frc.robot.commands;

import frc.robot.operator.OperatorInput;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * This command is used to safely stop the robot in its current position, and to cancel any running
 * commands
 */
public class CancelCommand extends LoggingCommand {

    private final OperatorInput       operatorInput;
    private final DrivetrainSubsystem drivetrainSubsystem;
    private final IntakeSubsystem     intakeSubsystem;
    private final ShooterSubsystem    shooterSubsystem;
    private final ClimberSubsystem    climberSubsystem;

    /**
     * Cancel the commands running on all subsystems.
     *
     * All subsystems must be passed to this command, and each subsystem should have a stop command
     * that safely stops the robot from moving.
     */
    public CancelCommand(OperatorInput operatorInput, DrivetrainSubsystem drivetrainSubsystem, IntakeSubsystem intakeSubsystem,
        ShooterSubsystem shooterSubsystem, ClimberSubsystem climberSubsystem) {

        this.operatorInput       = operatorInput;
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.intakeSubsystem     = intakeSubsystem;
        this.shooterSubsystem    = shooterSubsystem;
        this.climberSubsystem    = climberSubsystem;

        addRequirements(drivetrainSubsystem, intakeSubsystem, shooterSubsystem, climberSubsystem);
    }

    @Override
    public InterruptionBehavior getInterruptionBehavior() {
        /*
         * The Cancel command is not interruptable and only ends when the cancel button is released.
         */
        return InterruptionBehavior.kCancelIncoming;
    }

    @Override
    public void initialize() {

        logCommandStart();

        stopAll();
    }

    @Override
    public void execute() {
        stopAll();
    }

    @Override
    public boolean isFinished() {

        // The cancel command has a minimum timeout of .5 seconds
        if (!isTimeoutExceeded(.5)) {
            return false;
        }

        // Only end once the cancel button is released after .5 seconds has elapsed
        if (!operatorInput.isCancel()) {
            setFinishReason("Cancel button released");
            return true;
        }

        return false;
    }

    @Override
    public void end(boolean interrupted) {
        logCommandEnd(interrupted);
    }

    private void stopAll() {

        // Stop all of the robot movement
        drivetrainSubsystem.stop();
        intakeSubsystem.stop();
        shooterSubsystem.stop();
        climberSubsystem.stop();
    }
}
