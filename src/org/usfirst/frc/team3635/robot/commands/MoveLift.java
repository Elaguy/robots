
package org.usfirst.frc.team3635.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3635.robot.OI;
import org.usfirst.frc.team3635.robot.Robot;
import org.usfirst.frc.team3635.robot.RobotMap;

/**
 * 
 */
public class MoveLift extends Command {
	
	public MoveLift() {
		requires(Robot.liftSubsystem);
	} 

    // Called just before this Command runs the first time
    protected void initialize() {
//	   	System.out.println(this.getClass().getName() + " initializing.");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("lift: " + RobotMap.liftEncoder.get());
    	
	    double scale = 0.45;
	    double current = OI.stick.getY() * scale;
        
        if (Math.abs(current) < OI.DEADZONE) { current = 0; }
	    
//    	System.out.println(this.getClass().getName() + " executing.");
    	if (RobotMap.liftLimitUpper.get() && RobotMap.liftLimitLower.get()) {
    		System.err.println("Fatal wiring error; both lift limit switches on (YOU BROKE IT).");
    	} else if (!RobotMap.liftLimitUpper.get() && current >= 0) {
    		RobotMap.liftMotor.set(current);
    	} else if (!RobotMap.liftLimitLower.get() && current < 0) {
    		RobotMap.liftMotor.set(current);
    	} else {
    		RobotMap.liftMotor.set(0);
    	}
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
