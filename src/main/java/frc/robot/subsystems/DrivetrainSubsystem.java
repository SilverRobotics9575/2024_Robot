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

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriverConstants;

public class DrivetrainSubsystem extends SubsystemBase {
    /** Creates a new DrivetrainSubsystem. */

    private final SparkMax    rightFrontMotor, rightBackMotor, leftFrontMotor, leftBackMotor;

    private DifferentialDrive differentialDrive = null;

    public DrivetrainSubsystem() {

        rightFrontMotor = new SparkMax(DriverConstants.RIGHT_FRONT_DEVICE_ID, MotorType.kBrushed);
        rightBackMotor  = new SparkMax(DriverConstants.RIGHT_BACK_DEVICE_ID, MotorType.kBrushed);
        leftFrontMotor  = new SparkMax(DriverConstants.LEFT_FRONT_DEVICE_ID, MotorType.kBrushed);
        leftBackMotor   = new SparkMax(DriverConstants.LEFT_BACK_DEVICE_ID, MotorType.kBrushed);

        SparkMaxConfig config = new SparkMaxConfig();
        config
            .idleMode(IdleMode.kBrake)
            .disableFollowerMode();

        rightFrontMotor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        // Configure the backMotor to follow the front with the same config
        config.follow(rightFrontMotor);
        rightBackMotor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);


        config = new SparkMaxConfig();
        config
            .idleMode(IdleMode.kBrake)
            .disableFollowerMode();

        leftFrontMotor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        // Configure the rightBackMotor to follow the front with the same config
        config.follow(leftFrontMotor);
        leftBackMotor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);



        differentialDrive = new DifferentialDrive(leftFrontMotor, rightFrontMotor);
    }

    public void drive(double moveSpeed, double rotateSpeed) {
        differentialDrive.arcadeDrive(moveSpeed, rotateSpeed);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

        // Safety code and dashboard updates go here
    }

    public void stop() {
        differentialDrive.arcadeDrive(0, 0);
    }

    public double getMotorSpeedRF() {
        return rightFrontMotor.get();
    }

    public double getMotorSpeedLF() {
        return leftFrontMotor.get();
    }

    public double getMotorSpeedRB() {
        return rightBackMotor.get();
    }

    public double getMotorSpeedLB() {
        return leftBackMotor.get();
    }
    

    // FIXME: All subsystems need a human readable string that describes the state of the subsystem
    // which is used for logging
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass().getSimpleName()).append(" : ")
            .append("R Front Motor Speed ").append(getMotorSpeedRF())
            .append("L Front Motor Speed ").append(getMotorSpeedLF())
            .append("R Back Motor Speed ").append(getMotorSpeedRB())
            .append("L Back Motor Speed ").append(getMotorSpeedLB());

        return sb.toString();
    }
}

