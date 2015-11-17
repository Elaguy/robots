package org.usfirst.frc.team3635.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Servo;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	public static Jaguar motorFL = new Jaguar(0);
	public static Jaguar motorFR = new Jaguar(1);
    public static Jaguar motorBL = new Jaguar(2);
    public static Jaguar motorBR = new Jaguar(3);
    
    public static Encoder encoderFL = new Encoder(10, 11);
    public static Encoder encoderFR = new Encoder(12, 13);
    public static Encoder encoderBL = new Encoder(14, 15);
    public static Encoder encoderBR = new Encoder(16, 17);
    
    public static Jaguar liftMotor = new Jaguar(4);
    // Flipped lift limits 11:11 AM 2/27/15
    public static DigitalInput liftLimitUpper = new DigitalInput(1);
    public static DigitalInput liftLimitLower = new DigitalInput(0);
    public static Encoder liftEncoder = new Encoder(18, 19);
    
    public static Jaguar gripMotor = new Jaguar(5);
    public static DigitalInput gripLimitLeft = new DigitalInput(2);
    public static DigitalInput gripLimitRight = new DigitalInput(3);
    public static AnalogInput gripPotentiometer = new AnalogInput(0);
    
    public static Relay clawMotor = new Relay(0);
    public static Servo clawServo = new Servo(19);
    public static DigitalInput clawLimitUpper = new DigitalInput(24);
    public static DigitalInput clawLimitLower = new DigitalInput(25);
}
