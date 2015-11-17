package org.usfirst.frc.team3635.robot.commands;

import org.usfirst.frc.team3635.robot.Robot;
import org.usfirst.frc.team3635.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class TacticalRetreat extends Command {

	private double startTime;
	private double endTime;
	private int position;

	public TacticalRetreat() {
		requires(Robot.liftSubsystem);
		requires(Robot.driveTrainSubsystem);
	}
	
	@Override
	protected void initialize() {
		startTime = Timer.getFPGATimestamp();
		position = DriverStation.getInstance().getLocation();
		System.out.println("Driver station position " + position);
	}

	@Override
	protected void execute() {
		if (RobotMap.liftEncoder.get() < AutoLift.LIFT_HEIGHT)
			RobotMap.liftMotor.set(0.2);
		else
			RobotMap.liftMotor.set(0);
		Robot.driveTrainSubsystem.drive.mecanumDrive_Cartesian(0, 0.25, 0, 0);
	}

	@Override
	protected boolean isFinished() {
		switch(position) {
			case 1:  endTime = 3.4; break;
			default: endTime = 3.6; break;
		}
		return Timer.getFPGATimestamp() - startTime >= endTime;
	}

	@Override
	protected void end() {
		Robot.driveTrainSubsystem.drive.mecanumDrive_Cartesian(0, 0, 0, 0);
		RobotMap.liftMotor.set(0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
