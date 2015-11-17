
package org.usfirst.frc.team3635.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3635.robot.Robot;
import org.usfirst.frc.team3635.robot.RobotMap;

/**
 *
 */
public class GripperPosition extends Command {
	
	public static final double INCH_PER_REV   = 0.25;
    public static final double FULL_SCALE_VDC = 5.0;
    public static final double SIZE_MAX       = 27.0; // need to determine
    public static final double SIZE_MIN       = 10.0; // need to determine

    private double current;
    private double previous;
    private double change;
    private double rev_count = 0;
    private double position = 0;
    private boolean initialized = false;
	
	public GripperPosition() {
		requires(Robot.gripperpositionSubsystem);
	} 

    // Called just before this Command runs the first time
    protected void initialize() {
//    	System.out.println(this.getClass().getName() + " initializing.");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!initialized) {
            // wait for limit switch to be set to initialize position
            if (RobotMap.gripLimitLeft.get()) {
                initialized = true;
                position = SIZE_MAX;
                rev_count = position / INCH_PER_REV;
                previous = RobotMap.gripPotentiometer.getVoltage();
            } else if (RobotMap.gripLimitRight.get()) {
                initialized = true;
                position = SIZE_MIN;
                rev_count = position / INCH_PER_REV;
                previous = RobotMap.gripPotentiometer.getVoltage();
            }
        } else {
            // count initialized monitor movement
        	if (RobotMap.gripLimitLeft.get() && RobotMap.gripLimitRight.get()) {
        		System.err.println("Fatal wiring error; both gripper limit switches on.");
                initialized = false;
        	} else if (RobotMap.gripLimitLeft.get()) {
                position = SIZE_MAX;
                rev_count = position / INCH_PER_REV;
                previous = RobotMap.gripPotentiometer.getVoltage();
        	} else if (RobotMap.gripLimitRight.get()) {
                position = SIZE_MIN;
                rev_count = position / INCH_PER_REV;
                previous = RobotMap.gripPotentiometer.getVoltage();
        	} else {
                current = RobotMap.gripPotentiometer.getVoltage();
                change = current - previous;
                if (Math.abs(change) > (FULL_SCALE_VDC / 2) ) {
                    // rolled over
                    if (change < 0) {
                        rev_count = rev_count + ( (FULL_SCALE_VDC + change) / FULL_SCALE_VDC); 
                    } else {
                        rev_count = rev_count - ( (FULL_SCALE_VDC - change) / FULL_SCALE_VDC);
                    }
                } else {
                    // no rollover
                    rev_count = rev_count - (change / FULL_SCALE_VDC);
                }
            }
        }
        if (initialized) {
            System.out.println("Gripper position " + position + "in with count " + rev_count);
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
