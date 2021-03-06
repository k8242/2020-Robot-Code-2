/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.MoveHood;

import java.lang.module.ModuleDescriptor.Requires;
import java.util.Timer;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Encoder;

/**
* Add your docs here.
*/

// Shooter subsystem
public class Shooter extends Subsystem {
  // motors
  public static CANSparkMax turret = new CANSparkMax(RobotMap.turretPort, MotorType.kBrushless);
  public static CANSparkMax hood = new CANSparkMax(RobotMap.hoodPort, MotorType.kBrushless);
  public static CANSparkMax shooterNEO1 = new CANSparkMax(RobotMap.shooterPort1, MotorType.kBrushless);
  public static CANSparkMax shooterNEO2 = new CANSparkMax(RobotMap.shooterPort2, MotorType.kBrushless);
  public static CANEncoder shooterEncoder = shooterNEO1.getEncoder();
  public static CANPIDController shooterPIDController = shooterNEO1.getPIDController();
  public static CANEncoder hoodEncoder = hood.getEncoder();
  public static CANPIDController hoodPIDController = hood.getPIDController();
  public int currentLimit = 28;
  public static Encoder absoluteEncoder = new Encoder(0, 1, 2);
  public static CANEncoder turretHall = turret.getEncoder();

  public static DigitalInput limitSwitchTurret = new DigitalInput(RobotMap.limitSwitchTurret);
  

  
  public static double speed;
  
  public Shooter() {
    /*shooterPIDController.setP(1e-4);
    shooterPIDController.setI(1e-6);
    shooterPIDController.setD(1e-2);
    shooterPIDController.setIZone(0);
    shooterPIDController.setFF(0);
    shooterPIDController.setOutputRange(-1, 0);*/
    /*hoodPIDController.setP(0.05);
    hoodPIDController.setI(1e-5);
    hoodPIDController.setD(1e-3);
    hoodPIDController.setIZone(0);
    hoodPIDController.setFF(0);
    hoodPIDController.setOutputRange(-0.2, 0.2);
    //shooterNEO.setSmartCurrentLimit(currentLimit);
    hood.setSmartCurrentLimit(currentLimit);*/
  }
  
  public static void shooterLimitSwitch(){
    while(limitSwitchTurret.get() == true){
      turret.set(-0.5);
    }
  
    turret.set(0.0);
  }
  
  
public static void spinTurret(double speed) {
    double s = OI.driveJoystick.getRawAxis(1);
    turret.set(speed);
  }
  

  /*public static void spinHood(double speed){
    double s = joy.getRawAxis(1);
    //hood.set(s);
  }*/


  public static void adjustHood(double desiredTicks) {
    //hoodPIDController.setReference(desiredTicks, ControlType.kPosition);
   /* while (absoluteEncoder.getDistance() != desiredTicks){
			if(absoluteEncoder.getDistance() < desiredTicks){
        hood.set(0.5);
      }
    } */
  }

  public static void spinShooter(double s) {
    // on the off chance speed is in RPM, this code mightttttt not work
    /*shooterPIDController.setReference(s, ControlType.kVelocity);
    System.out.println(shooterEncoder.getVelocity());
    shooterNEO2.follow(shooterNEO1, true);*/
  }
  
  public static double getSpeed() {
    //return shooterEncoder.getVelocity();
    return 0;
  }

  public static boolean outLimit(){
  if((int)turretHall.getPosition() == 45){
       turretHall.setPosition(44.0);
      return true;
     }
     else if((int)turretHall.getPosition() == -45){
      turretHall.setPosition(-44.0);
      return true;
    }
    return false;
  }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    //setDefaultCommand(new MoveHood(0.5));
    
  }

}