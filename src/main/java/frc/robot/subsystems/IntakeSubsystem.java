package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {

    private final CANSparkMax intakeMotor;

    /** Local copy of the value sent to the controller */
    private double            intakeSpeed = 0;

    public IntakeSubsystem() {

        intakeMotor = new CANSparkMax(IntakeConstants.INTAKE_DEVICE_ID, MotorType.kBrushless);

        intakeMotor.restoreFactoryDefaults();

        // If new values are set, make sure to burn the flash
        // intakeMotor.burnFlash();
    }

    @Override
    public void periodic() {

        checkSafety();

        SmartDashboard.putNumber("Intake Motor Set Speed", getIntakeSpeed());
    }

    public void setIntakeSpeed(double intakeSpeed) {

        this.intakeSpeed = intakeSpeed;

        checkSafety();

        // Use the value after the safety check
        // instead of the passed in value
        intakeMotor.set(this.intakeSpeed);
    }

    public double getIntakeSpeed() {
        return intakeSpeed;
    }

    public void stop() {
        // Do not set the motor directly, call the setter because
        // the setter performs additional logic
        setIntakeSpeed(0);
    }

    private void checkSafety() {

        // If the intake speed is set too high, then limit the intake speed.
        if (intakeSpeed > IntakeConstants.MAX_INTAKE_SPEED) {

            intakeSpeed = IntakeConstants.MAX_INTAKE_SPEED;
            intakeMotor.set(intakeSpeed);
        }
    }
}
