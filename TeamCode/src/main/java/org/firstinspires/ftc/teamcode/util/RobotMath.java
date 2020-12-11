package org.firstinspires.ftc.teamcode.util;

/**
 * Contains some math functions that come in useful when manipulating values.
 */
public class RobotMath {

    /**
     * limits an inputted value to a particular bound
     * @param v value to be limited
     * @param low minimum allowed value
     * @param high maximum allowed value
     * @return the limited value
     */
    public static double limit(double v, double low, double high) {
        return (v < low) ? low : ((v > high) ? high : v);
    }

    /**
     * limits an inputted value to a particular bound
     * @param v value to be limited
     * @param limit the value will be limited between {@code -limit} and {@code +limit}
     * @return the limited value
     */
    public static double limit(double v, double limit) {
        return limit(v, -limit, limit);
    }

    /**
     * limits an inputted value between +/- 1
     * @param input value to be limited
     * @return the limited value between +/- 1
     */
    public static double limit(double input) {
        return limit(input, -1, 1);
    }

    /**
     * Scales given value to particular range
     * @param input value to be scaled
     * @param lowInput minimum allowed input
     * @param highInput maximum allowed input
     * @param lowOutput minimum allowed output
     * @param highOutput maximum allowed output
     * @return the new scaled value
     */
    public static double scale(double input, double lowInput, double highInput, double lowOutput, double highOutput) {
        final double inputRange = highInput - lowInput;
        final double outputRange = highOutput - lowOutput;
        return  (input - lowInput) * outputRange / inputRange + lowOutput;
    }

    /**
     * Constrain given value to a particular range
     * @param n value to be constrained
     * @param min minimum allowed output
     * @param max maximum allowed output
     * @return the constrained value
     */
    public static double constrain(double n, double min, double max) {
        return Math.max(Math.min(n, max), min);
    }

}
