/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;



import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import frc.robot.subsystems.Intake;

/**
* Add your docs here.
*/
//Hopper Subsystem
public class Hopper extends Subsystem {
	// belt talons
	public static CANSparkMax belt1 = new CANSparkMax(RobotMap.beltPort1, MotorType.kBrushless);
	public static CANSparkMax belt2 = new CANSparkMax(RobotMap.beltPort2, MotorType.kBrushless);
	public static Timer intakeTime = new Timer();
	public static DigitalInput echo = new DigitalInput(RobotMap.ultraEcho1);
	public static DigitalOutput trig = new DigitalOutput(RobotMap.ultraPing1);
	public static Ultrasonic ultra1 = new Ultrasonic(trig, echo);
	public static Ultrasonic ultra2 = new Ultrasonic(RobotMap.ultraPort2a, RobotMap.ultraPort2b);
	
	
	public static int numCells;
	
	public Hopper(int initialCellCount) {
		numCells = initialCellCount;
	}
	
	// methods
	public static void spinIn() {
		/*belt1.set(1.0);
		belt2.set(1.0);*/
	}
	
	
	public static void spinStop() {
		/*belt1.set(0);
		belt2.set(0);*/
	}
	
	public static void increaseCellCount() {
		numCells++;
	}
	
	public static void decreaseCellCount() {
		numCells--;
	}
	
	public static int currentCellCount() {
		return numCells;
	}
		
		// Not done check logic and fix
		public static void ballPassedIn(){
			if(ultra1.getRangeMM() < 5 && numCells < 5){
				spinIn();
				while(ultra1.getRangeMM() < 5){
					System.out.println("Ball");
				}
				spinStop();
				numCells++;
				if (numCells == 5) {
					Intake.liftIntake();
				}
			}
		}
		
		public static boolean ballPassedOut(){
			/*
			if(ultra2.getRangeMM() < 5 && numCells > 0){
				while(ultra2.getRangeMM() < 5){
					System.out.println("Ball");
				}
				spinStop();
				numCells--;
				return true;
			}
			else {
				return false;
			}*/
			return true;
		}
		
		@Override
		public void initDefaultCommand() {
			// Set the default command for a subsystem here.
			// setDefaultCommand(new MySpecialCommand());
		}
	}