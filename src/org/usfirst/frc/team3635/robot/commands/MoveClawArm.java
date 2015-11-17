
package org.usfirst.frc.team3635.robot.commands;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3635.robot.Robot;
import org.usfirst.frc.team3635.robot.RobotMap;

/**
 *
 */
public class MoveClawArm extends Command {
	
	private boolean direction;
    private boolean completed;
	
	public MoveClawArm(boolean direction) {
		requires(Robot.clawSubsystem);
		this.direction = direction;
	} 

    // Called just before this Command runs the first time
    protected void initialize() {
//    	System.out.println(this.getClass().getName() + " initializing.");
        completed = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println(this.getClass().getName() + " executing.");
    	
        if (RobotMap.clawLimitUpper.get() && RobotMap.clawLimitLower.get()) {
    		System.err.println("Fatal wiring error; both claw limit switches on.");
            RobotMap.clawMotor.set(Relay.Value.kOff);
    	} else {

            if ( (RobotMap.clawLimitLower.get() && direction) || 
                 (RobotMap.clawLimitUpper.get() && !direction)) {
                 
                 completed = true;
            } else {
                // using window motor which only has one speed
                // to control movement and use switches will burst motor in
                // small increments and check switches for completion        
                if (!RobotMap.clawLimitLower.get() && direction) {
                    RobotMap.clawMotor.set(Relay.Value.kReverse);
                } else if (!RobotMap.clawLimitUpper.get() && !direction) { 
                    RobotMap.clawMotor.set(Relay.Value.kForward);
                }
    
                Timer.delay(0.01);
                RobotMap.clawMotor.set(Relay.Value.kOff);
            }            
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return completed;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.clawMotor.set(Relay.Value.kOff);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
