// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DriveCommand extends Command {
  /** Creates a new DriveCommand. */
  private final Joystick joystick;
  private final DrivetrainSubsystem m_drivetrainSubsystem;
  
  // The commands constructor
  public DriveCommand(DrivetrainSubsystem drivetrainSubsystem, Joystick controller) {
    joystick = controller;
    m_drivetrainSubsystem = drivetrainSubsystem;
    addRequirements(drivetrainSubsystem);
  }

// Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double moveSpeed = joystick.getRawAxis(Constants.driverConstants.axisX);
    double rotateSpeed = joystick.getRawAxis(Constants.driverConstants.axisY);

    m_drivetrainSubsystem.DriveCommand(moveSpeed, rotateSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
   m_drivetrainSubsystem.stop();
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
