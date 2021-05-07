package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.util.controller.PIDController;

/**
 * Contains all the robot's configuration names as they appear in the configuration file.
 */
public class RobotConfig {

    // Drivetrain
    public static final String FRONT_LEFT_MOTOR_NAME  = "front_left";
    public static final String FRONT_RIGHT_MOTOR_NAME = "front_right";
    public static final String BACK_LEFT_MOTOR_NAME   = "back_left";
    public static final String BACK_RIGHT_MOTOR_NAME  = "back_right";

    public static final PIDController DRIVE_CONTROLLER  = new PIDController(0.0, 0.0, 0.0, 0.0);
    public static final PIDController STRAFE_CONTROLLER = new PIDController(0.0, 0.0, 0.0, 0.0);
    public static final PIDController TWIST_CONTROLLER  = new PIDController(0.0, 0.0, 0.0, 0.0);

    // Camera
    public static final String WEBCAM = "Webcam 1";

}
