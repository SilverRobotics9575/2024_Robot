// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.operator.OperatorInput;
import frc.robot.subsystems.ClimberSubsystem;

public class DefaultClimberCommand extends Command {

    private final OperatorInput    oi;
    private final ClimberSubsystem climberSubsystem;

    /** Creates a new ClimberCommand. */
    // FIXME: add logging
    public DefaultClimberCommand(OperatorInput oi, ClimberSubsystem climberSubsystem) {
        addRequirements(climberSubsystem);
        this.oi               = oi;
        this.climberSubsystem = climberSubsystem;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

        if (oi.climbUp()) {
            climberSubsystem.climb();
        }
        if (oi.stopClimber()) {
            climberSubsystem.stop();
        }
        //TODO: The climber command should run up when pressed.
        // When released it should run down for a defined amount of time and then stop
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
