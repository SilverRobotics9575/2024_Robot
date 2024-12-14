// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriverConstants;

public class DrivetrainSubsystem extends SubsystemBase{
  /** Creates a new DrivetrainSubsystem. */

  private final CANSparkMax rightFrontMotor, rightBackMotor, leftFrontMotor, leftBackMotor;

  private DifferentialDrive differentialDrive = null; 

  public DrivetrainSubsystem() {
    rightFrontMotor = new CANSparkMax(DriverConstants.RIGHT_FRONT_DEVICE_ID, MotorType.kBrushed);
    rightBackMotor = new CANSparkMax(DriverConstants.RIGHT_BACK_DEVICE_ID, MotorType.kBrushed);
    leftFrontMotor = new CANSparkMax(DriverConstants.LEFT_FRONT_DEVICE_ID, MotorType.kBrushed);
    leftBackMotor = new CANSparkMax(DriverConstants.LEFT_BACK_DEVICE_ID, MotorType.kBrushed);

    rightBackMotor.follow(rightFrontMotor);
    leftBackMotor.follow(leftFrontMotor);
    
    differentialDrive = new DifferentialDrive(leftFrontMotor, rightFrontMotor);

    // TODO: Test if restoreFactoryDefaults is necessary
    rightFrontMotor.restoreFactoryDefaults();
    leftFrontMotor.restoreFactoryDefaults();
    rightBackMotor.restoreFactoryDefaults();
    leftBackMotor.restoreFactoryDefaults();
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
    differentialDrive.arcadeDrive(0,0);
  }
}