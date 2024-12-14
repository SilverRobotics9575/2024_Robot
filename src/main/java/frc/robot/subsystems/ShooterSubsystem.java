// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {
  private final CANSparkMax shooterUpMotor, shooterDownMotor;

  public ShooterSubsystem() {
    shooterUpMotor = new CANSparkMax(ShooterConstants.SHOOTER_TOP_DEVICE_ID, MotorType.kBrushed);
    shooterDownMotor = new CANSparkMax(ShooterConstants.SHOOTER_BOTTOM_DEVICE_ID, MotorType.kBrushed);


    shooterUpMotor.restoreFactoryDefaults();
    shooterDownMotor.restoreFactoryDefaults();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }
  // Hold button to run top motor
  public void waitSeconds(){
    shooterUpMotor.set(-ShooterConstants.SHOOTER_SPEED);
  }
  // Release button to run bottom motor and send note
  public void shoot(){
    shooterDownMotor.set(-ShooterConstants.SHOOTER_SPEED);
  }

  public void stop() {
    shooterUpMotor.set(0);
    shooterDownMotor.set(0);
  }
}
