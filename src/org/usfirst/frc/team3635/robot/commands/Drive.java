
package org.usfirst.frc.team3635.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3635.robot.OI;
import org.usfirst.frc.team3635.robot.Robot;

/**
 * This works much like the Drive command from last year; it runs
 * continuously on the DriveTrain's behalf, and sets the motion
 * of the Meccanum wheels based on the two controller axes. Odds
 * are good that execute() will need tweaking to get things going
 * at the correct direction and speed. Also worth considering
 * transforming the joystick inputs (to get a different kind of
 * speed ramp-up).
 */
public class Drive extends Command {
	
	public Drive() {
        requires(Robot.driveTrainSubsystem);
    } 

    // Called just before this Command runs the first time
    protected void initialize() {
//    	System.out.println("Drive initializing.");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	System.out.println("Drive executing.");
    	double scale = 0.6;  // to adjust how sensitive joystick is
    	if (OI.pad_button8.get())
    		scale /= 2;
    	//Robot.driveTrainSubsystem.drive.mecanumDrive_Polar(OI.pad.getMagnitude() * scale, OI.pad.getDirectionDegrees() - 90, OI.getAxis(OI.RIGHT_STICK, OI.X_AXIS) * scale);
    	Robot.driveTrainSubsystem.drive.mecanumDrive_Cartesian(OI.getAxis(OI.LEFT_STICK, OI.X_AXIS) * scale, OI.getAxis(OI.LEFT_STICK, OI.Y_AXIS) * scale, OI.getAxis(OI.RIGHT_STICK, OI.X_AXIS) * scale, 0);
    	
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
