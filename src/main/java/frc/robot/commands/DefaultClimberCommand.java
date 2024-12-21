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

        // FIXME: Logic needs to run the climber up and down?
        // if (climbUp) {
        // ...set speed up
        // }
        // else if (climbDown) {
        // ...set speed down
        // }
        // else {
        // ...stop
        // }

        if (oi.runClimber()) {
            climberSubsystem.climb();
        }
        if (oi.stopClimber()) {
            climberSubsystem.stop();
        }
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
