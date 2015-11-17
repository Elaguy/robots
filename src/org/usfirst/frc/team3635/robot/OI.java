package org.usfirst.frc.team3635.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team3635.robot.commands.MoveClawArm;
import org.usfirst.frc.team3635.robot.commands.MoveGripper;
import org.usfirst.frc.team3635.robot.commands.RunClaw;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	public static final int GAMEPAD  = 0;
	public static final int STICK    = 1;
    public static final int LEFT_STICK  = 0;
    public static final int RIGHT_STICK = 1;
    public static final double DEADZONE  = 0.08;
	public static final boolean X_AXIS   = true;
	public static final boolean Y_AXIS   = false;
	
	public static Joystick pad        = new Joystick(GAMEPAD);
	public static Button pad_button1  = new JoystickButton(pad, 1);
	public static Button pad_button2  = new JoystickButton(pad, 2);
	public static Button pad_button3  = new JoystickButton(pad, 3);
	public static Button pad_button4  = new JoystickButton(pad, 4);
	public static Button pad_button5  = new JoystickButton(pad, 5);
	public static Button pad_button6  = new JoystickButton(pad, 6);
	public static Button pad_button7  = new JoystickButton(pad, 7);
	public static Button pad_button8  = new JoystickButton(pad, 8);
	public static Button pad_button9  = new JoystickButton(pad, 9);
	public static Button pad_button10 = new JoystickButton(pad, 10);

	public static Joystick stick        = new Joystick(STICK);
	public static Button stick_button1  = new JoystickButton(stick, 1);
	public static Button stick_button2  = new JoystickButton(stick, 2);
	public static Button stick_button3  = new JoystickButton(stick, 3);
	public static Button stick_button4  = new JoystickButton(stick, 4);
	public static Button stick_button5  = new JoystickButton(stick, 5);
	public static Button stick_button6  = new JoystickButton(stick, 6);
	public static Button stick_button7  = new JoystickButton(stick, 7);
	public static Button stick_button8  = new JoystickButton(stick, 8);
	public static Button stick_button9  = new JoystickButton(stick, 9);
	public static Button stick_button10 = new JoystickButton(stick, 10);
    public static Button stick_button11 = new JoystickButton(stick, 11);

/*******************************************************************************
* 
* NEED TO VERIFY THESE 
*            JOYSTICK LAYOUT                       GAMEPAD LAYOUT                 
*     ________________________________                 TOP VIEW           
*     |                              |      _____________________________ 
*     |         [ trig=1 ]           |     |                            | 
*     |       |-------------|        |     |  [ ]                 [4]   | 
*     |       | top of stick|        |     | [ D ]              [1] [3] | 
*     | [6]   |     [3]     |   [11] |     |  [ ]     [9]  [10]   [2]   | 
*     |       | [4]     [5] |        |     |                            | 
*     | [7]   |     [2]     |   [10] |     |       [x,y]    [3,4]       | 
*     |       |-------------|        |     |       ______________       | 
*     |          [8]    [9]          |     |______|              |______| 
*     |          [ axis 3 ]          |                                    
*     |______________________________|                SIDE VIEW           
*                                            _______              _______ 
*                                           | [7]   |            |  [8]  |
*                                           |       |            |       |
*                                           | [5]   --------------  [6]  |
*                                           |____________________________|
*                                           
*******************************************************************************/
     
	public OI() {
		pad_button1.whenPressed(new MoveClawArm(true));
		pad_button2.whenPressed(new MoveClawArm(false));
		pad_button3.whenPressed(new RunClaw(true));
		pad_button4.whenPressed(new RunClaw(false));
        
		stick_button4.whileHeld(new MoveGripper(true));
		stick_button5.whileHeld(new MoveGripper(false));
	}
	
	public static double getAxis(int pad_stick, boolean axis) {
		double val = 0;
		
		switch(pad_stick) {
			case LEFT_STICK:
				val = axis ? pad.getX() : pad.getY();
				break;
			case RIGHT_STICK:
				val = axis ? pad.getRawAxis(3) : pad.getRawAxis(4);
				break;
			default:
				System.out.println("Invalid joystick selections <" + stick + ">");
			}
			
		return (Math.abs(val) < DEADZONE) ? 0 : val;
	}
}