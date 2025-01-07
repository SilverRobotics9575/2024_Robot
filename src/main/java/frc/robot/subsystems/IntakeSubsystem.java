package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {

    private final SparkMax intakeMotor;

    /** Local copy of the value sent to the controller */
    private double         intakeSpeed = 0;

    public IntakeSubsystem() {

        intakeMotor = new SparkMax(IntakeConstants.INTAKE_DEVICE_ID, MotorType.kBrushless);

        SparkMaxConfig config = new SparkMaxConfig();
        config
            .idleMode(IdleMode.kBrake)
            .disableFollowerMode();

        intakeMotor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
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

    public double getIntakeSpeed() { // Returns the set speed (the variable in Constants), not the
                                     // current active speed.
        return intakeMotor.get();
        // Rather than just returning the variable in constants, we return it via SparkMax.get(),
        // which we can use to make sure the speed is properly set outside of constants.
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

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass().getSimpleName()).append(" : ")
            .append("Intake Speed ").append(getIntakeSpeed());

        return sb.toString();
    }
}
