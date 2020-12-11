package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;

@Autonomous(name = "Example Vuforia Vision", group = "Tests")
public class ExampleVuforiaVisionProcesser extends LinearOpMode {

    // Robot object
    private Robot robot;

    public ExampleVuforiaVisionProcesser() {
        robot = Robot.getInstance();
    }

    @Override
    public void runOpMode() throws InterruptedException {

        // Initialize robot hardware
        robot.init(hardwareMap);

        // Don't wait for start, we are just looking for things, not moving
        // waitForStart();

        // Start vuforia
        robot.vuforiaVision.activate();

        // Loop and look
        while(!isStopRequested()) {

            robot.vuforiaVision.loop(this);

        }

    }

}
