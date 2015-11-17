package org.usfirst.frc.team3635.robot.commands;

import org.usfirst.frc.team3635.robot.Robot;
import org.usfirst.frc.team3635.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class AutoLift extends Command {

	public static final int LIFT_HEIGHT = 1500;
	
	public AutoLift() {
		requires(Robot.liftSubsystem);
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		RobotMap.liftMotor.set(0.3);
	}

	@Override
	protected boolean isFinished() {
		return RobotMap.liftEncoder.get() >= LIFT_HEIGHT;
	}

	@Override
	protected void end() {
		RobotMap.liftMotor.set(0);
	}

	@Override
	protected void interrupted() {
	}

}
