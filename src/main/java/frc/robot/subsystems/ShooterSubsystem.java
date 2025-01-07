// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {
    private final SparkMax topShooterMotor, bottomShooterMotor;

    public ShooterSubsystem() {
        // FIXME: should these now be shooterMotor and feederMotor
        // If you are keeping the top/bottom naming, then maybe set
        // the constants back to top/bottom as
        // well. Make all the names consistent - it is easier to understand.
        // Pick a name, and stick with it :-)
        topShooterMotor    = new SparkMax(ShooterConstants.SHOOTER_MOTOR_CAN_ID, MotorType.kBrushed);
        bottomShooterMotor = new SparkMax(ShooterConstants.FEEDER_MOTOR_CAN_ID, MotorType.kBrushed);

        // FIXME: If you need the shooter and feeder to be inverted, then
        // invert them here - do not put negative signs in the code as
        // this can cause confusion when debugging robot issues
        // topShooterMotor.setInverted(true);
        // bottomShooterMotor.setInverted(true);

        SparkMaxConfig config = new SparkMaxConfig();
        config
            .idleMode(IdleMode.kBrake)
            .disableFollowerMode();

        topShooterMotor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        bottomShooterMotor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    /*
     * FIXME: There are duplicate methods in this subsystem!!!
     *
     * There should not be two methods here: shoot(), and setTopMotorSpeed()
     * one of them needs to be deleted.
     *
     * Also, note that in one of the methods, the speed is inverted
     * when applied to the motor.
     *
     * The SparkMax controller flashes green when set a positive value and
     * red on a negative value. The colour orientation is critical for
     * helping to debug the robot. If you want a positive number to
     * flash green and eject the ring, but because of the orientation
     * of the motor, the factory defaults for the speed controller cause
     * the motor to spin in the opposite direction, you should invert
     * the motor as above.
     *
     */
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

    // FIXME: You probably don't need to verify the shooter motor speed if the limits
    // are zero and one.
    // only verify inputs that can "break" the robot.
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

    public double getShooterSpeed() {
        return topShooterMotor.get();
    }

    public double getFeederSpeed() {
        return bottomShooterMotor.get();
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

    // FIXME: All subsystems need a human readable string that describes the state of the subsystem
    // which is used for logging
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass().getSimpleName()).append(" : ")
            .append("Shooter Speed ").append(getShooterSpeed())
            .append("Feeder Speed ").append(getFeederSpeed());

        return sb.toString();
    }
}
