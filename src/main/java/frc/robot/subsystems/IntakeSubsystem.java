package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase{
    private final CANSparkMax intakeMotor; 

    public IntakeSubsystem() {
        intakeMotor = new CANSparkMax(IntakeConstants.INTAKE_DEVICE_ID, MotorType.kBrushless); 
        intakeMotor.restoreFactoryDefaults();
    } 

    @Override 
    public void periodic() {
      // This method will be called once per scheduler run. Keeping this here in case anybody wants to use it later on.
      // TODO: Add safety code to stop motor from being 100% power
    }
    public void intake(){ 
      intakeMotor.set(IntakeConstants.INTAKE_SPEED);
    }
    public void stop() {
      intakeMotor.set(0);
    }
}
