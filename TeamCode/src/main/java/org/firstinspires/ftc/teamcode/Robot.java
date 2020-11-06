package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.Collector;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDrivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.Vision;

public class Robot {

    private static Robot INSTANCE;

    // Declare Subsystems
    public MecanumDrivetrain drivetrain;
    public Vision vision;
    public Collector collector;
    public Shooter shooter;

    private Robot() {

        // Create Subsystem Instances
        drivetrain = new MecanumDrivetrain();
        vision = new Vision();
        collector = new Collector();
        shooter = new Shooter();

    }

    public static synchronized Robot getInstance() {
        if(INSTANCE == null)
            INSTANCE = new Robot();
        return INSTANCE;
    }

    public void init(HardwareMap hardwareMap) {

        // Initialize Subsystems
        drivetrain.init(hardwareMap);
        vision.init(hardwareMap);
        collector.init(hardwareMap);
        shooter.init(hardwareMap);

    }

}
