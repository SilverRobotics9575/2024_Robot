package frc.robot.operator;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.CancelCommand;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * The Operator input class is used to map buttons to functions and functions to commands
 * <p>
 * This class extends SubsystemBase so that the periodic() routine is called each loop. The periodic
 * routine can be used to send debug information to the dashboard
 */
public class OperatorInput extends SubsystemBase {

    private final Joystick driverJoystick = new Joystick(OperatorConstants.controllerPort);

    // TODO: Change the Joystick to use the GameController class.
//    private final GameController driverController = new GameController(
//        OperatorConstants.DRIVER_CONTROLLER_PORT,
//        OperatorConstants.GAME_CONTROLLER_STICK_DEADBAND);

    /*
     * Map all functions to buttons.
     *
     * A function should be a description of the robot behavior it is triggering.
     *
     * This separation of concerns allows for remapping of the robot functions to different
     * controller buttons without the need to change the command or the trigger. The mapping
     * from controller button to function is done in the following methods.
     */

    // Cancel all commands when the driver presses the XBox controller three lines (aka. start)
    // button
    public boolean isCancel() {
        return false;
//        return driverController.getStartButton();
    }

    /**
     * Use this method to define your robotFunction -> command mappings.
     *
     * NOTE: all subsystems should be passed into this method.
     */
    public void configureButtonBindings(DrivetrainSubsystem drivetrainSubsystem,
        IntakeSubsystem intakeSubsystem, ShooterSubsystem shooterSubsystem, ClimberSubsystem climberSubsystem) {

        // Cancel all commands when the driver presses the XBox controller three lines (aka. start)
        // button
        // FIXME: The cancel command should stop ALL subsystems
        new Trigger(() -> isCancel())
            .onTrue(new CancelCommand(this, drivetrainSubsystem));

    }

    @Override
    public void periodic() {

        // Display any operator input values on the smart dashboard.

//        SmartDashboard.putString("Driver Controller", driverController.toString());
    }

    /**
     * Accessor to get the game controller for backwards compatibility
     * FIXME: remove and replace where used with OperatorInput getter methods.
     *
     * @return GameController
     */
    @Deprecated
    public Joystick getDriverController() {
        return driverJoystick;
    }

}
