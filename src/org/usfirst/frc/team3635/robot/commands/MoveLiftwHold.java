
package org.usfirst.frc.team3635.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3635.robot.OI;
import org.usfirst.frc.team3635.robot.Robot;
import org.usfirst.frc.team3635.robot.RobotMap;

/**
 * 
 */
public class MoveLiftwHold extends Command {
	
	public MoveLiftwHold() {
		requires(Robot.liftwholdSubsystem);
	} 

	private double  STEADY_SPEED = 0.0;
	
    // Called just before this Command runs the first time
    protected void initialize() {
//	   	System.out.println(this.getClass().getName() + " initializing.");
		// determine speed to hold steady
		double initial_count = RobotMap.liftEncoder.get();
		double current_count = initial_count;
		
		RobotMap.liftMotor.set(STEADY_SPEED);
		
		while (current_count <= initial_count) {
			Timer.delay(0.05);
			STEADY_SPEED = STEADY_SPEED + 0.025;
			RobotMap.liftMotor.set(STEADY_SPEED);
			current_count = RobotMap.liftEncoder.get();
			System.out.println("Steady speed set to " + STEADY_SPEED);
			
		}
		
		STEADY_SPEED = STEADY_SPEED - 0.025;
		System.out.println("Steady speed set to " + STEADY_SPEED);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	    
	    double current = OI.stick.getY();
        if (Math.abs(current) < OI.DEADZONE) { current = 0; }
	    double value;
	    
//    	System.out.println(this.getClass().getName() + " executing.");
    	if (RobotMap.liftLimitUpper.get() && RobotMap.liftLimitLower.get()) {
    		System.err.println("Fatal wiring error; both lift limit switches on (YOU BROKE IT).");
    	} else if (!RobotMap.liftLimitUpper.get() && current < 0) {
	    	value = current+STEADY_SPEED;
	    	if (value < -1) { value = -1; }
    		RobotMap.liftMotor.set(value);
    	} else if (!RobotMap.liftLimitLower.get() && current >= 0) {
	    	value = current+STEADY_SPEED;
	    	if (value > 1) { value = 1; }
    		RobotMap.liftMotor.set(value);
    	} else {
	    	RobotMap.liftMotor.set(STEADY_SPEED);
    	}
    	
    	Timer.delay(0.01);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
