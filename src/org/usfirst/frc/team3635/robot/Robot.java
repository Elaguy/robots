
package org.usfirst.frc.team3635.robot;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team3635.robot.commands.AutonomousDrive;
import org.usfirst.frc.team3635.robot.commands.AutonomousSequence;
import org.usfirst.frc.team3635.robot.commands.ExampleCommand;
import org.usfirst.frc.team3635.robot.subsystems.ClawSubsystem;
import org.usfirst.frc.team3635.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3635.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team3635.robot.subsystems.GripperSubsystem;
import org.usfirst.frc.team3635.robot.subsystems.GripperPositionSubsystem;
import org.usfirst.frc.team3635.robot.subsystems.LiftSubsystem;
import org.usfirst.frc.team3635.robot.subsystems.LiftwHoldSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot {

    public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
    public static final DriveTrain driveTrainSubsystem = new DriveTrain();
    public static final LiftSubsystem liftSubsystem = new LiftSubsystem();
    public static final LiftwHoldSubsystem liftwholdSubsystem = new LiftwHoldSubsystem();
    public static final GripperSubsystem gripperSubsystem = new GripperSubsystem();
    public static final GripperPositionSubsystem gripperpositionSubsystem = new GripperPositionSubsystem();
    public static final ClawSubsystem clawSubsystem = new ClawSubsystem();
    public static OI oi;
    
    Command autonomousCommand;
    
    // Potentially useful for reference:
    //Encoder encoder = new Encoder(1, 2, false, Encoder.EncodingType.k4X);
    //AnalogInput potentiometer;
    //long potentValue = 0;
    
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        oi = new OI();
        // instantiate the command used for the autonomous period
        autonomousCommand = new AutonomousSequence();
        
        // configure the camera for view on dashboard
        CameraServer server = CameraServer.getInstance();
        server.setQuality(50);
        server.startAutomaticCapture("Cam1");
        
        RobotMap.liftMotor.set(0);
        RobotMap.gripMotor.set(0);
        RobotMap.clawMotor.set(Relay.Value.kOff);
	}

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit() {
        
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	// For reference, how to get the PDP temperature:
        //IntBuffer status = ByteBuffer.allocateDirect(4).asIntBuffer();
        //double result = PDPJNI.getPDPTemperature(status);
		//System.out.println(result);
        LiveWindow.run();
        
        //Encoders
        System.out.println("FL encoder:   " + RobotMap.encoderFL.get()); 
        System.out.println("FR encoder:   " + RobotMap.encoderFR.get());
        System.out.println("BL encoder:   " + RobotMap.encoderBL.get());
        System.out.println("BR encoder:   " + RobotMap.encoderBR.get());
        System.out.println("Lift encoder: " + RobotMap.liftEncoder.get());
         
        //Switches
        System.out.println("Lift Upper switch: " + RobotMap.liftLimitUpper.get());
        System.out.println("Lift Lower switch: " + RobotMap.liftLimitLower.get());
        System.out.println("Grip Left switch:  " + RobotMap.gripLimitLeft.get());
        System.out.println("Grip Right switch: " + RobotMap.gripLimitRight.get());
        System.out.println("Claw Upper switch:  " + RobotMap.clawLimitUpper.get());
        System.out.println("Claw Lower switch: " + RobotMap.clawLimitLower.get());

        //Analog
        System.out.println("Potentiometer: " + RobotMap.gripPotentiometer.getVoltage());
        
        Timer.delay(0.05);
    }
}
