// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.Constants.ClimberConstants;

public class ClimberCommand extends Command {

  private final Joystick joystick;
  private final ClimberSubsystem m_climberSubsystem;
  /** Creates a new ClimberCommand. */
  public ClimberCommand(ClimberSubsystem climberSubsystem, Joystick controller) {
    // Use addRequirements() here to declare subsystem dependencies.
    joystick = controller;
    m_climberSubsystem = climberSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Climber button climbs while button 3 is being held
    if (joystick.getRawButton(ClimberConstants.CLIMBER_BUTTON)) {
      m_climberSubsystem.climb();
      System.out.println("Climbing"); // Test to see if climbing is working
    }
    // Climber will stop if the button 3 is released
    // TODO: Add a way for the robot to automatically know when max climber height is released
    if (joystick.getRawButtonReleased(ClimberConstants.CLIMBER_BUTTON)){
      m_climberSubsystem.stop();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
