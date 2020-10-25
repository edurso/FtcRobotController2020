package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;

@Autonomous(name = "Example Vision", group = "Tests")
public class ExampleVisionProcesser extends LinearOpMode {

    private Robot robot;

    public ExampleVisionProcesser() {
        robot = Robot.getInstance();
    }

    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap);

        // waitForStart();

        robot.vision.activate();

        while(!isStopRequested()) {

            robot.vision.loop(this);

        }
    }

}
