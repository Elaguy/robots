
package org.usfirst.frc.team3635.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3635.robot.Robot;

public class AutonomousDrive extends Command {

	private double startTime;
	
    public AutonomousDrive() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrainSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Driving autonomously.");
    	startTime = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Still driving.");
    	Robot.driveTrainSubsystem.drive.mecanumDrive_Polar(0.5, 0, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Timer.getFPGATimestamp() - startTime >= 2;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrainSubsystem.drive.mecanumDrive_Polar(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrainSubsystem.drive.mecanumDrive_Polar(0.25, 0, 0);
    }
}
