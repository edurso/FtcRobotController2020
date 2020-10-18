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

    @Override
    public void init(HardwareMap hardwareMap) {

        frontLeft  = hardwareMap.get(DcMotor.class, RobotConfig.FRONT_LEFT_MOTOR_NAME);
        frontRight = hardwareMap.get(DcMotor.class, RobotConfig.FRONT_RIGHT_MOTOR_NAME);
        backLeft   = hardwareMap.get(DcMotor.class, RobotConfig.BACK_LEFT_MOTOR_NAME);
        backRight  = hardwareMap.get(DcMotor.class, RobotConfig.BACK_RIGHT_MOTOR_NAME);

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);

    }

    public void vector(double drive, double strafe, double twist) {

        // Declare Speeds Per Motor
        double[] speeds = {
                (drive + strafe + twist), // Front Left  - speeds[0]
                (drive - strafe - twist), // Front Right - speeds[1]
                (drive - strafe + twist), // Back Left   - speeds[2]
                (drive + strafe - twist)  // Back Right  - speeds[3]
        };

        // Find Largest Speed
        double max = Math.abs(speeds[0]);
        for(double speed : speeds) if(Math.abs(speed) > max) max = Math.abs(speed);

        // Reduce All Speeds If Max Speed Is Outside Allowed Range of [-1,1]
        if(max > 1d) for(int i = 0 ; i < speeds.length ; ++i) speeds[i] /= max;

        // Set Power To Motors
        this.setPower(speeds[0],
                speeds[1],
                speeds[2],
                speeds[3]);

    }

    public void setPower(double frontLeftPwr,
                         double frontRightPwr,
                         double backLeftPwr,
                         double backRightPwr) {
        frontLeft.setPower(frontLeftPwr);
        frontRight.setPower(frontRightPwr);
        backLeft.setPower(backLeftPwr);
        backRight.setPower(backRightPwr);
    }

    public void stop() {
        this.setPower(0d, 0d, 0d, 0d);
    }

}
