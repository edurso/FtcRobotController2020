package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.util.OpModeTransitioner;

@Autonomous(name = "Example Autonomous", group = "Tests")
public class ExampleAutonomous extends LinearOpMode {

    private Robot robot;

    public ExampleAutonomous() {
        robot = Robot.getInstance();
    }

    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap);

        OpModeTransitioner.config(this, "Example TeleOp");

        waitForStart();

        while(opModeIsActive()) {

            robot.drivetrain.vector(0d, 0d, 1d); // Spin In Circles . . .

        }

    }

}
