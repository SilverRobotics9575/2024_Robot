// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

public class ClimberSubsystem extends SubsystemBase {
  /** Creates a new ClimberSubsystem. */
  // Both motors are different motorcontrollers, so they are delcared seperately
  private final VictorSP leftClimber;
  private final CANSparkMax rightClimber;

  public ClimberSubsystem() {
    leftClimber = new VictorSP(ClimberConstants.LEFT_CLIMBER_DEVICE_ID);
    rightClimber = new CANSparkMax(ClimberConstants.RIGHT_CLIMBER_DEVICE_ID,  MotorType.kBrushed);

    rightClimber.restoreFactoryDefaults();
  }

  public void climb() {
    rightClimber.set(ClimberConstants.CLIMBER_SPEED);
    leftClimber.set(ClimberConstants.CLIMBER_SPEED);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void stop(){
    rightClimber.set(0);
    leftClimber.set(0);
  }
}
