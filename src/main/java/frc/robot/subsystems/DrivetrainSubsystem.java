// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriverConstants;

public class DrivetrainSubsystem extends SubsystemBase {
    /** Creates a new DrivetrainSubsystem. */

    private final WPI_VictorSPX rightFrontMotor, rightBackMotor, leftFrontMotor;
    private final WPI_TalonSRX  leftBackMotor;
    // The WPI_ version of the motor controllers provides DifferentialDrive and other necessities

    private DifferentialDrive   differentialDrive = null;

    public DrivetrainSubsystem() {

        rightFrontMotor = new WPI_VictorSPX(DriverConstants.RIGHT_FRONT_DEVICE_ID);
        rightBackMotor  = new WPI_VictorSPX(DriverConstants.RIGHT_BACK_DEVICE_ID);
        leftFrontMotor  = new WPI_VictorSPX(DriverConstants.LEFT_FRONT_DEVICE_ID);
        leftBackMotor   = new WPI_TalonSRX(DriverConstants.LEFT_BACK_DEVICE_ID);
        // Motor 4 is a TalonSRX, but everything else is VictorSPX>

        rightBackMotor.follow(rightFrontMotor);
        leftBackMotor.follow(leftFrontMotor);
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
}