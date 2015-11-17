
package org.usfirst.frc.team3635.robot.subsystems;

import org.usfirst.frc.team3635.robot.commands.MoveLift;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LiftSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        setDefaultCommand(new MoveLift());
    }
}

