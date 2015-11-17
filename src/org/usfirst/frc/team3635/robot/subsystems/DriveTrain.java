
package org.usfirst.frc.team3635.robot.subsystems;

import org.usfirst.frc.team3635.robot.RobotMap;
import org.usfirst.frc.team3635.robot.commands.Drive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public RobotDrive drive = new RobotDrive(RobotMap.motorFL, RobotMap.motorBL, RobotMap.motorFR, RobotMap.motorBR);
	//public RobotDrive drive = new RobotDrive(RobotMap.motorFL, RobotMap.motorFR, RobotMap.motorBL, RobotMap.motorBR);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new Drive());
    }
}

