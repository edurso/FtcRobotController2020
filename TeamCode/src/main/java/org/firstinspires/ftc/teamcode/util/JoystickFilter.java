package org.firstinspires.ftc.teamcode.util;

/**
 * Joystick filter to filter driver input for smoother control of robot.
 */
public class JoystickFilter {

    /**
     * Types of filters
     */
    public enum Mode {
        LINEAR,
        SQUARED,
        CUBED
    }

    private Mode mode;
    private double deadband;
    private double minOutput;
    private double maxOutput;
    private double rampDelta;
    private double lastOutput;

    /**
     * Constructor for {@code JoystickFilter}
     * @param deadband Margin of error allowed on joystick
     * @param minOutput Minimum value the joystick can output
     * @param maxOutput Maximum value the joystick can output
     * @param mode The {@link org.firstinspires.ftc.teamcode.util.JoystickFilter.Mode} of the filter
     */
    public JoystickFilter(double deadband, double minOutput, double maxOutput, Mode mode) {
        this.deadband = deadband;
        this.minOutput = minOutput;
        this.maxOutput = maxOutput;
        this.mode = mode;
        lastOutput = 0;
        rampDelta = maxOutput * 2;
    }

    /**
     * Constructor for {@code JoystickFilter}
     * @param deadband Margin of error allowed on joystick
     * @param mode The {@link org.firstinspires.ftc.teamcode.util.JoystickFilter.Mode} of the filter
     */
    public JoystickFilter(double deadband, Mode mode) {
        this(deadband, -1, 1, mode);
    }

    /**
     * Set the allowed ramp for the joystick
     * @param d The desired ramp delta
     */
    public void setRampDelta(double d) {
        rampDelta = d;
    }

    /**
     * The configured mode of the {@code JoystickFilter}
     * @return The {@link org.firstinspires.ftc.teamcode.util.JoystickFilter.Mode} of the filter
     */
    public Mode getMode() {
        return mode;
    }

    /**
     * Set the configured mode of the {@code JoystickFilter}
     * @param mode The desired {@link org.firstinspires.ftc.teamcode.util.JoystickFilter.Mode} of the filter
     */
    public void setMode(Mode mode) {
        this.mode = mode;
    }

    /**
     * The allowed margin of error from the value 0 on the joystick
     * @return The configured deadband
     */
    public double getDeadband() {
        return deadband;
    }

    /**
     * Set the allowed margin of error from the value 0 on the joystick
     * @param deadband The desired deadband
     */
    public void setDeadband(double deadband) {
        this.deadband = deadband;
    }

    /**
     * The minimum allowed output of the joystick
     * @return The configured minimum output
     */
    public double getMinOutput() {
        return minOutput;
    }

    /**
     * Set the minimum allowed output of the joystick
     * @param minOutput The desired minimum output
     */
    public void setMinOutput(double minOutput) {
        this.minOutput = minOutput;
    }

    /**
     * The maximum allowed output of the joystick
     * @return The configured maximum output
     */
    public double getMaxOutput() {
        return maxOutput;
    }

    /**
     * Set the maximum allowed output of the joystick
     * @param maxOutput The desired maximum output
     */
    public void setMaxOutput(double maxOutput) {
        this.maxOutput = maxOutput;
    }

    /**
     * Filter a joystick value according to the configured parameters
     * @param input Joystick value to be filtered
     * @return Filtered joystick value
     */
    public double filter(double input) {
        boolean negative = (input < 0);
        double output = 0;

        input = Math.abs(input);
        if (input < deadband) return 0.0;

        switch (mode) {
            case LINEAR:
                output = RobotMath.scale(input, deadband, 1, minOutput, maxOutput);
                break;

            case SQUARED:
                output = RobotMath.scale(input * input, deadband * deadband, 1, minOutput, maxOutput);
                break;

            case CUBED:
                output = RobotMath.scale(input * input * input, deadband * deadband * deadband, 1, minOutput, maxOutput);
                break;
        }

        if (negative)
            output = -output;

        output = RobotMath.constrain(output, lastOutput - rampDelta, lastOutput + rampDelta);
        lastOutput = output;

        return output;
    }

}
