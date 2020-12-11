package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.RobotConfig;
import org.firstinspires.ftc.teamcode.util.SubsystemBase;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

public class EOCVVision extends SubsystemBase {

    /* Webcam config constants */
    private static final int WEBCAM_WIDTH   = 320;
    private static final int WEBCAM_HEIGHT  = 240;

    /* Webcam object */
    public OpenCvCamera webcam;

    @Override
    public void init(HardwareMap hardwareMap) {

        /* Initialize webcam */
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, RobotConfig.WEBCAM), cameraMonitorViewId);

    }

    public void startCamera(OpenCvPipeline pipeline) {

        /* Configure pipeline to run */
        webcam.setPipeline(pipeline);

        /* Start Streaming */
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() { webcam.startStreaming(WEBCAM_WIDTH, WEBCAM_HEIGHT, OpenCvCameraRotation.UPRIGHT); }
        });

    }

    public void stopCamera() { webcam.stopStreaming(); }

    public void logTelemetry(OpMode om) {
        om.telemetry.addData("Frame Count", webcam.getFrameCount());
        om.telemetry.addData("FPS", String.format("%.2f", webcam.getFps()));
        om.telemetry.addData("Total frame time ms", webcam.getTotalFrameTimeMs());
        om.telemetry.addData("Pipeline time ms", webcam.getPipelineTimeMs());
        om.telemetry.addData("Overhead time ms", webcam.getOverheadTimeMs());
        om.telemetry.addData("Theoretical max FPS", webcam.getCurrentPipelineMaxFps());
        om.telemetry.update();
    }

}
