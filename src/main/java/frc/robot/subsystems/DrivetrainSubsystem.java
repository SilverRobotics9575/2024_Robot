// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DrivetrainSubsystem extends SubsystemBase{


  private final CANSparkMax rightUpMotor, rightDownMotor, leftUpMotor, leftDownMotor;

  private DifferentialDrive differentialDrive = null; 

  public DrivetrainSubsystem() {
    rightUpMotor = new CANSparkMax(Constants.driverConstants.rightUpDeviceID, MotorType.kBrushed);
    rightDownMotor = new CANSparkMax(Constants.driverConstants.rightDownDeviceID, MotorType.kBrushed);
    leftUpMotor = new CANSparkMax(Constants.driverConstants.leftUpDeviceID, MotorType.kBrushed);
    leftDownMotor = new CANSparkMax(Constants.driverConstants.leftDownDeviceID, MotorType.kBrushed);

    rightDownMotor.follow(rightUpMotor);
    leftDownMotor.follow(leftUpMotor);
    
    differentialDrive = new DifferentialDrive(leftUpMotor, rightUpMotor);

    rightUpMotor.restoreFactoryDefaults();
    leftUpMotor.restoreFactoryDefaults();
    rightDownMotor.restoreFactoryDefaults();
    leftDownMotor.restoreFactoryDefaults();
  }

  public void DriveCommand(double moveSpeed, double rotateSpeed) {
    differentialDrive.arcadeDrive(moveSpeed, rotateSpeed);
  }
 @Override
  public void periodic() {
    // This method will be called once per scheduler run

    }

  public void stop() {
    differentialDrive.arcadeDrive(0,0);
  }
}