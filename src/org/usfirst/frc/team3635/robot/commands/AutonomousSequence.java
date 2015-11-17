package org.usfirst.frc.team3635.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousSequence extends CommandGroup {
	
	public AutonomousSequence() {
		addSequential(new AutoLift());
		addSequential(new TacticalRetreat());
	}

}
