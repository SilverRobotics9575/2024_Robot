// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {
    private final CANSparkMax topShooterMotor, bottomShooterMotor;

    public ShooterSubsystem() {
        topShooterMotor    = new CANSparkMax(ShooterConstants.SHOOTER_MOTOR_CAN_ID, MotorType.kBrushed);
        bottomShooterMotor = new CANSparkMax(ShooterConstants.FEEDER_MOTOR_CAN_ID, MotorType.kBrushed);


        topShooterMotor.restoreFactoryDefaults();
        bottomShooterMotor.restoreFactoryDefaults();
    }

    public void setTopMotorSpeed(double shooterSpeed) {
        // Call this method to update the top motors speed

        shooterSpeed = verifySpeedValue(shooterSpeed);
        topShooterMotor.set(shooterSpeed);
    }

    public void setBottomMotorSpeed(double speedValue) {
        // Call this method to update the bottom motors speed

        speedValue = verifySpeedValue(speedValue);
        bottomShooterMotor.set(speedValue);
    }

    public double verifySpeedValue(double speedValue) {
        // This method clamps speedValue between 1 and 0 to stay within PWM outputs

        if (speedValue > 1) {
            System.out.println("Warning: speedValue is greater than the max speed! Clamping to 1.");
            speedValue = 1;
        }
        else if (speedValue < 0) {
            System.out.println("Warning: speedValue is less than the min speed! Clamping to 0.");
            speedValue = 0;
        }
        return speedValue;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    // Hold button to run top motor
    public void waitSeconds() {
        topShooterMotor.set(-ShooterConstants.MAX_SHOOTER_SPEED);
    }

    // Release button to run bottom motor and send note
    public void shoot() {
        bottomShooterMotor.set(-ShooterConstants.MAX_SHOOTER_SPEED);
    }

    public void stop() {
        topShooterMotor.set(0);
        bottomShooterMotor.set(0);
    }
}
