package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.SubsystemBase;

public class Collector extends SubsystemBase {

    // Motor Declaration

    @Override
    public void init(HardwareMap hardwareMap) {

        // Motor Init

        // Motor Directions

    }

    // Functions

    public void in() { this.setPower(1d); }

    public void out() { this.setPower(-1d); }

    public void stop() { this.setPower(0d); }

    public void setPower(double pwr) {

    }

}