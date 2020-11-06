package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RobotConfig;
import org.firstinspires.ftc.teamcode.util.SubsystemBase;

public class Shooter extends SubsystemBase {

    // Motor Declaration

    @Override
    public void init(HardwareMap hardwareMap) {

        // Motor Init

        // Motor Directions

    }

    // Functions

    public void fire() { this.setPower(1d); }

    public void setPower(double pwr) {

    }

}
