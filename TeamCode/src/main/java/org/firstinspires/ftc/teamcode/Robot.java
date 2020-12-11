package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.Collector;
import org.firstinspires.ftc.teamcode.subsystems.EOCVVision;
import org.firstinspires.ftc.teamcode.subsystems.Indexer;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDrivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.VuforiaVision;

/**
 * Main Robot Object
 */
public class Robot {

    /**
     * Robot Instance
     */
    private static Robot INSTANCE;

    /* Declare Subsystems */
    // Drivetrain
    public MecanumDrivetrain drivetrain;
    // Vision Subsystems
    public VuforiaVision  vuforiaVision;
    public EOCVVision     eocvVision;
    // Game Piece Manipulators
    public Collector  collector;
    public Indexer    indexer;
    public Shooter    shooter;

    /**
     * Constructor instantiates subsystems
     */
    private Robot() {

        /* Create Subsystem Instances */
        // Drivetrain
        drivetrain     = new MecanumDrivetrain();
        // Vision Subsystems
        vuforiaVision  = new VuforiaVision();
        eocvVision     = new EOCVVision();
        // Game Piece Manipulators
        collector      = new Collector();
        indexer        = new Indexer();
        shooter        = new Shooter();

    }

    /**
     * Retrieves Instance of Robot
     * @return The instance of {@code Robot}
     */
    public static synchronized Robot getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Robot();
        return INSTANCE;
    }

    /**
     * Initializes All Subsystems
     * @param hardwareMap The {@link com.qualcomm.robotcore.hardware.HardwareMap} from which to initialize the robot
     */
    public void init(HardwareMap hardwareMap) {

        /* Initialize Subsystems */
        // Drivetrain
        drivetrain      .init(hardwareMap);
        // Vision Subsystems
        vuforiaVision   .init(hardwareMap);
        eocvVision      .init(hardwareMap);
        // Game Piece Manipulators
        collector       .init(hardwareMap);
        indexer         .init(hardwareMap);
        shooter         .init(hardwareMap);

    }

}
