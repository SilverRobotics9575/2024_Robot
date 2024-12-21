// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.AutonomousCommand;
import frc.robot.commands.DefaultClimberCommand;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.commands.DefaultIntakeCommand;
import frc.robot.commands.DefaultShooterCommand;
import frc.robot.operator.OperatorInput;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot
 * (including subsystems, commands, and button mappings) should be declared
 * here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final OperatorInput       oi               = new OperatorInput();

    private final DrivetrainSubsystem drivetrainSubsystem       = new DrivetrainSubsystem();
    private final IntakeSubsystem     intakeSubsystem  = new IntakeSubsystem();
    private final ShooterSubsystem    shooterSubsystem = new ShooterSubsystem();
    private final ClimberSubsystem    climberSubsystem = new ClimberSubsystem();

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {


        // TODO: pass the operator input (oi) as the first parameter in all default commands
        // TODO: move the button getters to functions in the operator input.

        // Set default commands on subsystems
        drivetrainSubsystem.setDefaultCommand(new DefaultDriveCommand(oi, drivetrainSubsystem));
        intakeSubsystem.setDefaultCommand(new DefaultIntakeCommand(oi, intakeSubsystem));
        shooterSubsystem.setDefaultCommand(new DefaultShooterCommand(shooterSubsystem, oi.getDriverController()));
        climberSubsystem.setDefaultCommand(new DefaultClimberCommand(oi, climberSubsystem));

        // Configure the button bindings
        oi.configureButtonBindings(
            drivetrainSubsystem,
            intakeSubsystem,
            shooterSubsystem,
            climberSubsystem);
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return new AutonomousCommand(oi,
            drivetrainSubsystem, intakeSubsystem, shooterSubsystem, climberSubsystem);
    }
}
