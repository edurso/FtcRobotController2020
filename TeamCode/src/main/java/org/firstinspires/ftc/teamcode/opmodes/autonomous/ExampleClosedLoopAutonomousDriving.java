package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.RobotConfig;
import org.firstinspires.ftc.teamcode.util.JoystickFilter;
import org.firstinspires.ftc.teamcode.util.OpModeTransitioner;

@Autonomous(name = "Example Closed Loop Autonomous Driving", group = "Tests")
public class ExampleClosedLoopAutonomousDriving extends LinearOpMode {

    // Robot object
    private Robot robot;

    // Joystick filter for better control
    private JoystickFilter filter;

    public ExampleClosedLoopAutonomousDriving() {

        // Get the robot
        robot = Robot.getInstance();

        // Set up the joystick filter
        filter = new JoystickFilter(0.05, JoystickFilter.Mode.CUBED);

    }

    @Override
    public void runOpMode() throws InterruptedException {

        // Initialize Robot
        robot.init(hardwareMap);

        // Set up an auto-transition to tele-op when autonomous is complete
        OpModeTransitioner.config(this, "Example TeleOp");

        // Wait for start button to be pressed
        waitForStart();

        robot.drivetrain.closedLoopDrive(1.0); // drive one **unit**
        robot.drivetrain.stop();
        robot.drivetrain.closedLoopTurn(90.0); // turn 90 degrees
        robot.drivetrain.stop();
        robot.drivetrain.closedLoopStrafe(1.0); // strafe one **unit**
        robot.drivetrain.stop();

    }

}
