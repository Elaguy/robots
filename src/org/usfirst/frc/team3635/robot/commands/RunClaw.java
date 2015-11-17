
package org.usfirst.frc.team3635.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3635.robot.Robot;
import org.usfirst.frc.team3635.robot.RobotMap;

/**
 * This should be relatively straightforward. While talking with
 * Patrick, I got the impression the claw would be manipulated by
 * an open/close toggle, but Mr. Day thought otherwise, lest the
 * servo damage itself. This is an implementation of the latter,
 * and is meant to be used with whileHeld().
 */
public class RunClaw extends Command {
	
	private boolean direction;
	
	public RunClaw(boolean direction) {
		requires(Robot.clawSubsystem);
		this.direction = direction;
	} 

    // Called just before this Command runs the first time
    protected void initialize() {
//    	System.out.println(this.getClass().getName() + " initializing.");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println(this.getClass().getName() + " executing.");
    	double value = RobotMap.clawServo.getPosition();
    	if (direction == true) {
    		value = value + 0.1;
    	} else {
    		value = value - 0.1;
    	}
    	
    	if (value < 0) { value = 0; }
    	if (value > 1) { value = 1; }
    	RobotMap.clawServo.setPosition(value);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
