package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RobotConfig;
import org.firstinspires.ftc.teamcode.util.SubsystemBase;

public class MecanumDrivetrain extends SubsystemBase {

    private DcMotor frontLeft  = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft   = null;
    private DcMotor backRight  = null;

    private class MecanumMotorSpeeds {

        double[] speeds;

        double frontLeft, frontRight, backLeft, backRight;

        MecanumMotorSpeeds(double frontLeft, double frontRight, double backLeft, double backRight) {

            // Load Speeds
            speeds = new double[]{frontLeft, frontRight, backLeft, backRight};

            // Find Largest Speed
            double max = Math.abs(speeds[0]);
            for(double speed : speeds) if(Math.abs(speed) > max) max = Math.abs(speed);

            // Reduce All Speeds If Max Speed Is Outside Allowed Range of [-1,1]
            if(max > 1d) for(int i = 0 ; i < speeds.length ; ++i) speeds[i] /= max;

            this.frontLeft = speeds[0];
            this.frontRight = speeds[1];
            this.backLeft = speeds[2];
            this.backRight = speeds[3];

        }

    }

    @Override
    public void init(HardwareMap hardwareMap) {

        frontLeft  = hardwareMap.get(DcMotor.class, RobotConfig.FRONT_LEFT_MOTOR_NAME);
        frontRight = hardwareMap.get(DcMotor.class, RobotConfig.FRONT_RIGHT_MOTOR_NAME);
        backLeft   = hardwareMap.get(DcMotor.class, RobotConfig.BACK_LEFT_MOTOR_NAME);
        backRight  = hardwareMap.get(DcMotor.class, RobotConfig.BACK_RIGHT_MOTOR_NAME);

        frontLeft  .setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight .setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft   .setDirection(DcMotorSimple.Direction.FORWARD);
        backRight  .setDirection(DcMotorSimple.Direction.FORWARD);

    }

    public void vector(double drive, double strafe, double twist) {

        MecanumMotorSpeeds motorSpeeds = new MecanumMotorSpeeds(
                (drive + strafe + twist), // Front Left
                (drive - strafe - twist), // Front Right
                (drive - strafe + twist), // Back Left
                (drive + strafe - twist)  // Back Right
        );

        // Set Power To Motors
        this.setSpeed(motorSpeeds);

    }

    public void setSpeed(MecanumMotorSpeeds motorSpeeds) {
        frontLeft  .setPower(motorSpeeds.frontLeft);
        frontRight .setPower(motorSpeeds.frontRight);
        backLeft   .setPower(motorSpeeds.backLeft);
        backRight  .setPower(motorSpeeds.backRight);
    }

    public void stop() {
        this.setSpeed(new MecanumMotorSpeeds(0d, 0d, 0d, 0d));
    }

}
