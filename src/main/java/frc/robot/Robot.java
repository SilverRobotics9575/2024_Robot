/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

//import edu.wpi.first.units.Time;

// Using "import static an.enum.or.constants.inner.class.*;" helps reduce verbosity
// this replaces "DoubleSolenoid.Value.kForward" with just kForward
// further reading is available at https://www.geeksforgeeks.org/static-import-java/
//import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;



//import edu.wpi.first.wpilibj.Compressor;
//import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.PneumaticsModuleType;
//import edu.wpi.first.wpilibj.SerialPort;
//import edu.wpi.first.wpilibj.PneumaticsModuleType;
//import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;


//import com.ctre.phoenix.motorcontrol.*;
//import com.ctre.phoenix.motorcontrol.can.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

//import edu.wpi.first.wpilibj.motorcontrol.VictorSPX;

public class Robot extends TimedRobot {



  private boolean isAutonomous, isIntake = false;
  //private Time time;
  
  private DifferentialDrive RobotDriveOne, RobotDriveTwo;
  //private DifferentialDrive ShooterMotors;
  //private DifferentialDrive m3_Robotshoot;
  //private DifferentialDrive m_Robotturn;
  //private DifferentialDrive m2_Robotturn;
  private Joystick driver_controller, shooter_controller;
  //private Joystick m2_controller;
  private static final int rightUpDeviceID = 1; 
  private static final int leftUpDeviceID = 3;
  private static final int rightDownDeviceID = 2; 
  private static final int leftDownDeviceID = 4;
  private static final int shooterUptDeviceID = 5; 
  private static final int shooterDownDeviceID = 6;
  private static final int intakeID = 8;
  private static final int hookRightID = 9;
  private static final int hookLeftID = 10;
  private CANSparkMax rightUpMotor, leftUpMotor, rightDownMotor, leftDownMotor, shooterUpMotor, shooterDownMotor, intakeMotor, hookRightMotor;
  //private CANSparkMax hookLeftMotor;
  private final VictorSPX hookLeftMotor = new VictorSPX(hookLeftID);
  
  UsbCamera camera1;
  NetworkTableEntry cameraSelection;

  //private DoubleSolenoid exampleDoublePCM;
  //private DoubleSolenoid exampleDoublePH;
//private DoubleSolenoid exampleDouble;
//private DoubleSolenoid exampleDouble2;






   // Solenoid corresponds to autoList single solenoid.

  // In this case, it's connected to channel 0 of autoList PH with the default CAN ID.

  //private final Solenoid m_solenoid = new Solenoid(PneumaticsModuleType.REVPH, 0);

  //private ahrs;

  public Timer timer = new Timer();
  
  @Override
  public void robotInit() {
    camera1 = CameraServer.startAutomaticCapture();
    cameraSelection = NetworkTableInstance.getDefault().getTable("").getEntry("CameraSelection");

    camera1.setResolution(320,240);


    String[] autoList={"Shoot, Drive Backwards", "Shoot, Drive, Intake", "Shoot, Drive backwards red amp"};
    SmartDashboard.putStringArray("Auto List", autoList);

  
  //ahrs = new AHRS(SerialPort.Port.kMXP); /* Alternatives:  SPI.Port.kMXP, I2C.Port.kMXP or SerialPort.Port.kUSB */











  //exampleDoublePCM = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 2);
  //exampleDouble = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 4, 5);
  //exampleDouble2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 6, 7);
  //exampleDoublePH = new DoubleSolenoid(9, PneumaticsModuleType.REVPH, 4, 5);


  


  
//exampleDouble = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 2);
/*

     * The output of GetRawButton is true/false depending on whether

     * the button is pressed; Set takes autoList boolean for whether

     * to retract the solenoid (false) or extend it (true).

     */

     //m_solenoid.set(m_stick.getRawButton(kSolenoidButton));

     
     
  /**
   * SPARK MAX controllers are intialized over CAN by constructing autoList CANSparkMax object
   * 
   * The CAN ID, which can be configured using the SPARK MAX Client, is passed as the
   * first parameter
   * 
   * 
   * The motor type is passed as the second parameter. Motor type can either be:
   *  com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless
   *  com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushed
   * 
   * The example below initializes four brushless motors with CAN IDs 1 and 2. Change
   * these parameters to match your setup
   */
    rightUpMotor     = new CANSparkMax(rightUpDeviceID, MotorType.kBrushed);//kBrushless
    leftUpMotor      = new CANSparkMax(leftUpDeviceID, MotorType.kBrushed);
    rightDownMotor   = new CANSparkMax(rightDownDeviceID, MotorType.kBrushed);
    leftDownMotor    = new CANSparkMax(leftDownDeviceID, MotorType.kBrushed);
    shooterUpMotor   = new CANSparkMax(shooterUptDeviceID, MotorType.kBrushed);
    shooterDownMotor = new CANSparkMax(shooterDownDeviceID, MotorType.kBrushed);
    intakeMotor      = new CANSparkMax(intakeID, MotorType.kBrushless);
    hookRightMotor   = new CANSparkMax(hookRightID, MotorType.kBrushed);
    
    
    
    
    //hookLeftMotor = new CANSparkMax(hookLeftID, MotorType.kBrushed);


    /**
     * The RestoreFactoryDefaults method can be used to reset the configuration parameters
     * in the SPARK MAX to their factory default state. If no argument is passed, these
     * parameters will not persist between power cycles
     */
    rightUpMotor.restoreFactoryDefaults();
    leftUpMotor.restoreFactoryDefaults();
    rightDownMotor.restoreFactoryDefaults();
    leftDownMotor.restoreFactoryDefaults();
    shooterUpMotor.restoreFactoryDefaults();
    shooterDownMotor.restoreFactoryDefaults();
    intakeMotor.restoreFactoryDefaults();
    hookRightMotor.restoreFactoryDefaults();
    //hookLeftMotor.restoreFactoryDefaults();
    
    
    RobotDriveOne = new DifferentialDrive(rightUpMotor, leftUpMotor);
    RobotDriveTwo = new DifferentialDrive(rightDownMotor, leftDownMotor);


    //ShooterMotors = new Differenti`alDrive(shooterUpMotor, shooterDownMotor);
    //shooterDownMotor.setInverted(true);
    //intakeMotor.setInverted(true);
  

    driver_controller = new Joystick(1);
    shooter_controller = new Joystick(0);

  }
  
  @Override
  public void teleopPeriodic() {
    //DRIVER CONTROLS-------------------------------------

    RobotDriveOne.arcadeDrive(-driver_controller.getRawAxis(0),driver_controller.getRawAxis(1));

    
    RobotDriveTwo.arcadeDrive(-driver_controller.getRawAxis(0),driver_controller.getRawAxis(1));

    if (driver_controller.getRawButton(8)){
      hookRightMotor.set(-1);
    }
    else if (driver_controller.getRawButton(6)){
      hookRightMotor.set(1);
    }
    else{
      hookRightMotor.set(0);
    }

    if (driver_controller.getRawButton(7)){
      hookLeftMotor.set(VictorSPXControlMode.PercentOutput, -1);
      //System.out.println(hookLeftMotor.getMotorOutputPercent()); // prints the percent output of the motor (0.5)
      //System.out.println(hookLeftMotor.getBusVoltage()); // prints the bus voltage seen by the motor controller
    }
    else if (driver_controller.getRawButton(5)){
      hookLeftMotor.set(VictorSPXControlMode.PercentOutput, 1);
    }
    else{
      hookLeftMotor.set(VictorSPXControlMode.PercentOutput, 0);
    }
    
    
    // SHOOTER CONTROLS----------------------------------------------
    if (shooter_controller.getRawAxis(3)>=0.02||shooter_controller.getRawAxis(2)>=0.02||
    shooter_controller.getRawButton(6)||shooter_controller.getRawButton(5)||shooter_controller.getRawButton(4)){
    //A FRAME
    //RIGHT STICK
    /*if (shooter_controller.getRawAxis(5)>=0.07||shooter_controller.getRawAxis(5)<=0.07){
      //ShooterMotors.arcadeDrive(shooter_controller.getRawAxis(4),-shooter_controller.getRawAxis(5));
      shooterUpMotor.set(-shooter_controller.getRawAxis(5));
      shooterDownMotor.set(-shooter_controller.getRawAxis(5));
    }*/
    //BUTTON R2
    if (shooter_controller.getRawAxis(3)>=0.02){
      shooterUpMotor.set(-shooter_controller.getRawAxis(3));
    
    }
    //BUTTON L2
    if (shooter_controller.getRawAxis(2)>=0.02){
      shooterDownMotor.set(-shooter_controller.getRawAxis(2));
    
    }
    //BUTTON R1
    if (shooter_controller.getRawButton(6)){
      shooterUpMotor.set(1);
    }
    //BUTTON L1
    if (shooter_controller.getRawButton(5)){
      shooterDownMotor.set(1);
    }
    //BUTTON Y AMPLIFIER
    if (shooter_controller.getRawButton(4)){
      shooterUpMotor.set(-0.2);
      shooterDownMotor.set(-0.35);

    }
  }
    else{
      shooterUpMotor.set(0);
      shooterDownMotor.set(0);
    }
    
    //INTAKE

    //BUTTON A
    if (shooter_controller.getRawButton(1)){
      intakeMotor.set(1);
    }
    //BUTTON B
    else if (shooter_controller.getRawButton(2)){
      intakeMotor.set(-1);
    }
    //LEFT STICK
    else if (shooter_controller.getRawAxis(1)>=0.07||shooter_controller.getRawAxis(1)<=0.07){
      intakeMotor.set(shooter_controller.getRawAxis(1));
    }
    else{
      intakeMotor.set(0);
    }


    
  }

  @Override // Autonomus Stuff for roboto
  public void autonomousInit() {
    isAutonomous = true;
    isIntake = true;
    timer.start();  
    
  }

  @Override
  public void autonomousPeriodic() {
    

// At the beginning of auto
String autoName = SmartDashboard.getString("Auto Selector", "Shoot, Drive Backwards"); // This would make "Drive Forwards the default auto
switch(autoName) {        
   case "Shoot, Drive Backwards":
   // auto here
   if (isAutonomous) {
    //System.out.println("Backwards");
        if (timer.get() < 3) {
          shooterUpMotor.set(-1); 
        } else if (timer.get() < 4) {
          shooterDownMotor.set(-1);
        } else if (timer.get() < 9) {//8
          shooterDownMotor.set(0); // why was this deactivated here
          RobotDriveTwo.arcadeDrive(0,0.5);
          RobotDriveOne.arcadeDrive(0,0.5);
        } else {
          shooterUpMotor.set(0);
          shooterDownMotor.set(0);
          RobotDriveTwo.arcadeDrive(0,0);
          RobotDriveOne.arcadeDrive(0,0);
          intakeMotor.set(0); // intake motor souldnt be on
          isAutonomous = false;
          timer.stop();
          timer.reset();
        }
break; 
      }   
      
   case "Shoot, Drive, Intake":
   // auto here
    // go straight
      if (isAutonomous) {
        //System.out.println("Intake");
        if (timer.get() < 3) {
          shooterUpMotor.set(-1);  // Prepares shooting
        } else if (timer.get() < 4) {
          shooterDownMotor.set(-1); // Shoots
        } else if (timer.get() < 5) {
          shooterDownMotor.set(0);
          // Drives robot backwards
          RobotDriveTwo.arcadeDrive(0,1); 
          RobotDriveOne.arcadeDrive(0,1);
          intakeMotor.set(1);//INTAKE START
        } else if (timer.get() < 6){
          // Robot drives back forward to speaker
          RobotDriveTwo.arcadeDrive(0,-1);
          RobotDriveOne.arcadeDrive(0,-1);
        } else if (timer.get() < 6.9){
          // Motors and itntake are de-activated
          intakeMotor.set(0); 
          RobotDriveTwo.arcadeDrive(0,0);
          RobotDriveOne.arcadeDrive(0,0);
        } else if (timer.get() < 7.8){
          shooterDownMotor.set(-1);//INTAKE COMPLETE, Note is launched
          // Robot drives backwards again
          RobotDriveTwo.arcadeDrive(0,0.5);
          RobotDriveOne.arcadeDrive(0,0.5);
        } else {
          // All motors de-activated, autonomous is complete
          shooterUpMotor.set(0);
          shooterDownMotor.set(0);
          RobotDriveTwo.arcadeDrive(0,0);
          RobotDriveOne.arcadeDrive(0,0);
          intakeMotor.set(0);
          isAutonomous = false;
          timer.stop();
          timer.reset();
        }
break; 
      }   /**/ 
      case "Shoot, Drive backwards red amp":
      if (isAutonomous){
        if (timer.get() < 3) {
          shooterUpMotor.set(-1); 
        } else if (timer.get() < 4) {
          shooterDownMotor.set(-1);
        } else if (timer.get() < 4.9) {
          shooterDownMotor.set(0); 
          RobotDriveTwo.arcadeDrive(0.5,0.5);
          RobotDriveOne.arcadeDrive(0.5,0.5);
        } else if (timer.get() < 9) {
          shooterDownMotor.set(0); 
          RobotDriveTwo.arcadeDrive(0,0.5);
          RobotDriveOne.arcadeDrive(0,0.5);
        }
         else {
          shooterUpMotor.set(0);
          shooterDownMotor.set(0);
          RobotDriveTwo.arcadeDrive(0,0);
          RobotDriveOne.arcadeDrive(0,0);
          intakeMotor.set(0); 
          isAutonomous = false;
          timer.stop();
          timer.reset();
        }
        break;
      }
  }

  }
}

