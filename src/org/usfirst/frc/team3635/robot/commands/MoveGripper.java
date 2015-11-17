
package org.usfirst.frc.team3635.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3635.robot.Robot;
import org.usfirst.frc.team3635.robot.RobotMap;

/**
 *
 */
public class MoveGripper extends Command {
	
	public static final double SPEED = 0.4;
    private boolean atlimit = false;
	private boolean direction;
	
	public MoveGripper(boolean direction) {
		requires(Robot.gripperSubsystem);
		this.direction = direction;
	} 

    // Called just before this Command runs the first time
    protected void initialize() {
//    	System.out.println(this.getClass().getName() + " initializing.");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println(this.getClass().getName() + " executing.");
    	if (RobotMap.gripLimitLeft.get() && RobotMap.gripLimitRight.get()) {
    		System.err.println("Fatal wiring error; both gripper limit switches on.");
            atlimit = true;
    	} else if (!RobotMap.gripLimitLeft.get() && direction == false) {
    		RobotMap.gripMotor.set(-SPEED);
    	} else if (!RobotMap.gripLimitRight.get() && direction == true) {
    		RobotMap.gripMotor.set(SPEED);
    	} else {
            atlimit = true;
        }
    	Timer.delay(0.05);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return atlimit;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.gripMotor.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        RobotMap.gripMotor.set(0);
    }
}
