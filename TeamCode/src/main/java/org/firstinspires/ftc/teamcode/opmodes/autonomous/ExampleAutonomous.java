package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.util.OpModeTransitioner;

@Autonomous(name = "Example Autonomous", group = "Tests")
public class ExampleAutonomous extends LinearOpMode {

    // Robot object
    private Robot robot;

    public ExampleAutonomous() {
        robot = Robot.getInstance();
    }

    @Override
    public void runOpMode() throws InterruptedException {

        // Initialize Robot
        robot.init(hardwareMap);

        // Set up an auto-transition to tele-op when autonomous is complete
        OpModeTransitioner.config(this, "Example TeleOp");

        // Wait for the start button to be pressed
        waitForStart();

        // Spin in circles until stopped
        while(opModeIsActive()) {

            robot.drivetrain.vector(0d, 0d, 1d); // Spin In Circles . . .

        }

    }

}
