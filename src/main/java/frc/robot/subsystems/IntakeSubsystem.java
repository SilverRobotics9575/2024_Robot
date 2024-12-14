package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.intakeConstants;

public class IntakeSubsystem extends SubsystemBase{
    private final CANSparkMax intakeMotor; 

    public IntakeSubsystem() {
        intakeMotor = new CANSparkMax(intakeConstants.intakeDeviceID, MotorType.kBrushless); 

        intakeMotor.restoreFactoryDefaults();
    } 

    @Override 
    public void periodic() {
      // This method will be called once per scheduler run. Keeping this here in case anybody wants to use it later on.
  
    }
    public void intake(){ 
        intakeMotor.set(intakeConstants.intakeSpeed);
    }
    public void stop() {
      intakeMotor.set(0);
    }
}
