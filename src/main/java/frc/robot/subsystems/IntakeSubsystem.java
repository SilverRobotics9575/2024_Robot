package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {
    private final CANSparkMax intakeMotor;

    public IntakeSubsystem() {
        intakeMotor = new CANSparkMax(IntakeConstants.INTAKE_DEVICE_ID, MotorType.kBrushless);
        intakeMotor.restoreFactoryDefaults();
    }

    @Override
    public void periodic() {

        // TODO: Add safety code to stop motor from being 100% power
    }

    public void intake() {
        intakeMotor.set(Math.min(IntakeConstants.INTAKE_SPEED, IntakeConstants.MAX_INTAKE));
        // Returns the smaller of the two variables.
    }

    public void stop() {
        intakeMotor.set(0);
    }
}
