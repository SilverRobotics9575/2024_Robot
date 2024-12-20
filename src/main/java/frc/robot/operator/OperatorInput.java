package frc.robot.operator;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.AutoConstants.AutoPattern;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.CancelCommand;
import frc.robot.commands.ShootCommand;
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

    // Define all user devices here...
    // TODO: Change the driverController to use the GameController class.
    // NOTE: Care is required because the Y axis is normalized (not inverted) in the GameController.
    // XBox buttons are numbered as: A=1, B=2, X=3, Y=4, but use the button getters instead of the
    // raw button numbers

    private final XboxController               driverController   = new XboxController(OperatorConstants.DRIVER_CONTROLLER_PORT);

    // Define all Dashboard choosers here...
    private final SendableChooser<AutoPattern> autoPatternChooser = new SendableChooser<>();

    public OperatorInput() {

        // Initialize all of the Dashboard choosers
        autoPatternChooser.setDefaultOption("Do Nothing", AutoPattern.DO_NOTHING);
        SmartDashboard.putData("Auto Pattern", autoPatternChooser);
        autoPatternChooser.addOption("Drive Forward", AutoPattern.DRIVE_FORWARD);
    }

    /*
     * AUTO Pattern
     *
     * Get the selected auto pattern from the dashboard widget
     */
    public AutoPattern getAutoPattern() {

        AutoPattern autoPattern = autoPatternChooser.getSelected();

        if (autoPattern == null) {
            return AutoPattern.DO_NOTHING;
        }

        return autoPattern;
    }

    /*
     * DEFAULT COMMANDS
     *
     * Add any buttons required for the default commands. Use a function name instead
     * of a button name.
     *
     * This separation of concerns allows for remapping of the robot functions to different
     * controller buttons without the need to change the default command.
     */

    /*
     * Cancel Command
     */
    // Cancel all commands when the driver presses the XBox controller three lines (aka. start)
    // button
    public boolean isCancel() {
        return driverController.getStartButton();
    }

    /*
     * Default Intake Command
     */
    public boolean runIntake() {
        return driverController.getBButton();
    }

    /**
     * Use this method to define your button->command mappings. for any commands that are not the
     * default command.
     *
     * NOTE: all subsystems should be passed into this method.
     */
    public void configureButtonBindings(DrivetrainSubsystem drivetrainSubsystem,
        IntakeSubsystem intakeSubsystem, ShooterSubsystem shooterSubsystem, ClimberSubsystem climberSubsystem) {

        // Cancel all commands when the driver presses the XBox controller three lines (aka. start)
        // button
        // FIXME: The cancel command should stop ALL subsystems, so all subsystems should be passed
        // to the
        // CancelCommand
        new Trigger(() -> isCancel())
            .onTrue(new CancelCommand(this, drivetrainSubsystem, intakeSubsystem, shooterSubsystem, climberSubsystem));

        new Trigger(() -> driverController.getRightBumper())
            .onTrue(new ShootCommand(shooterSubsystem));


    }

    @Override
    public void periodic() {

        // Display any operator input values on the smart dashboard.

        // SmartDashboard.putString("Driver Controller", driverController.toString());
    }

    /**
     * Accessor to get the game controller for backwards compatibility
     * FIXME: remove and replace where necessary/used with OperatorInput getter methods.
     * No commands should directly access the Joystick. This allows remapping of controller
     * buttons without searching the entire code base to look for button conficts.
     *
     * @return GameController
     */
    @Deprecated
    public XboxController getDriverController() {
        return driverController;
    }

}
