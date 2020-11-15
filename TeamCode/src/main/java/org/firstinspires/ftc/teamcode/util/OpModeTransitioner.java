package org.firstinspires.ftc.teamcode.util;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.internal.opmode.OpModeManagerImpl;

/**
 *
 * OpModeTransitioner will automatically initialize a given OpMode at the end of the autonomous period.
 * For OpModes, place the following line in {@code init()} method, for LinearOpModes, place following line
 * before {@code waitForStart()} method.
 *
 * {@code OpModeTransitioner.config(this, "#TeleOp Mode Name#")}
 *
 * Note that #TeleOp Mode Name# needs to be replaced with the name of the OpMode you want to switch to
 * as it appears in the {@code @TeleOp(name = "#TeleOp Mode Name#")} or
 * {@code @Autonomous(name = "#TeleOp Mode Name#")} annotation in the OpMode.
 *
 */
public class OpModeTransitioner extends Thread {

    private static final OpModeTransitioner INSTANCE = new OpModeTransitioner();

    private OpMode onStop;
    private String transitionTo;
    private OpModeManagerImpl opModeManager;

    private OpModeTransitioner() { this.start(); }

    @Override
    public void run() {
        try {
            while(true) {
                synchronized(this) {
                    if ( (onStop != null) && (opModeManager.getActiveOpMode() != onStop) ) {
                        Thread.sleep(1000);
                        opModeManager.initActiveOpMode(transitionTo);
                        reset();
                    }
                }
                Thread.sleep(50);
            }
        } catch (InterruptedException ex) {
            Log.e("RCActivity", "OpModeTransitioner quit unexpectedly, thread interrupted");
        }
    }

    private void setNewTransition(OpMode onStop, String transitionTo) {
        synchronized(this) {
            this.onStop = onStop;
            this.transitionTo = transitionTo;
            this.opModeManager = (OpModeManagerImpl) onStop.internalOpModeServices;
        }
    }

    private void reset() {
        this.onStop = null;
        this.transitionTo = null;
        this.opModeManager = null;
    }

    /**
     * Configures instance of {@link org.firstinspires.ftc.teamcode.util.OpModeTransitioner OpModeTransitioner}
     * to transition between two given {@link com.qualcomm.robotcore.eventloop.opmode.OpMode OpModes}
     * @param onStop {@link com.qualcomm.robotcore.eventloop.opmode.OpMode OpMode} that stops
     * @param transitionTo {@link com.qualcomm.robotcore.eventloop.opmode.OpMode OpMode} to begin when {@code onStop} ends
     */
    public static void config(OpMode onStop, String transitionTo) {
        INSTANCE.setNewTransition(onStop, transitionTo);
    }

}
