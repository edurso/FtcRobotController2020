package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.util.OpModeTransitioner;
import org.firstinspires.ftc.teamcode.vision.ExamplePipeline;

@Autonomous(name = "Example EOCV Vision", group = "Tests")
public class ExampleEOCVAutonomous extends LinearOpMode {

    // Robot object
    private Robot robot;

    // Vision pipeline to be run in autonomous
    private ExamplePipeline visionPipeline;

    public ExampleEOCVAutonomous() {

        // Get the robot instance
        robot = Robot.getInstance();

        // Set-up the vision pipeline
        visionPipeline = new ExamplePipeline(robot.eocvVision);

    }

    @Override
    public void runOpMode() throws InterruptedException {

        // Initialize the robot
        robot.init(hardwareMap);

        // Start the vision camera/pipeline
        robot.eocvVision.startCamera(visionPipeline);

        // Wait for the start button to be pressed
        waitForStart();

        // Loop and look
        while(opModeIsActive()) {

            // Log process stats
            robot.eocvVision.logTelemetry(this);

            // Log pipeline results
            telemetry.addData("Number of Balls in View: ", visionPipeline.getNumObjectsSeen());
            telemetry.update();

        }

        // Stop the camera when done
        robot.eocvVision.stopCamera();

    }

}
