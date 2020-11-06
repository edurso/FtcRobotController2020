package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.util.JoystickFilter;

@TeleOp(name = "Example TeleOp", group = "Tests")
public class ExampleTeleOp extends OpMode {

    private Robot robot;

    private JoystickFilter filter;

    public ExampleTeleOp() {

        robot = Robot.getInstance();

        filter = new JoystickFilter(0.05, JoystickFilter.Mode.CUBED);

    }

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {

        // Read Values From Gamepad
        double drive  = filter.filter(gamepad1.left_stick_y);
        double strafe = filter.filter(gamepad1.left_stick_x);
        double twist  = filter.filter(gamepad1.right_stick_x);

        // Vector The Robot With The Driver Input
        robot.drivetrain.vector(drive, strafe, twist);

    }
}
