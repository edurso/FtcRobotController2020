package org.firstinspires.ftc.teamcode.util;

public class RobotMath {

    public static double wheelDiameter = 3;

    public static double wheelCircumference =  wheelDiameter * Math.PI;

    public static double limit(double v, double low, double high) {
        return (v < low) ? low : ((v > high) ? high : v);
    }

    public static double limit(double v, double limit) {
        return limit(v, -limit, limit);
    }

    public static double limit(double input) {
        return limit(input, -1, 1);
    }

    public static double boundThetaNegPiToPi(double theta) {
        return theta - (Math.ceil((theta + Math.PI) / (Math.PI * 2)) - 1) * (Math.PI * 2); // (-π,π]
    }

    public static double boundTheta0To2Pi(double theta) {
        return theta - Math.floor(theta / (Math.PI * 2)) * (Math.PI * 2); // [0,2π)
    }

    public static double boundThetaNeg180to180(double theta) {
        return theta - (Math.ceil((theta + 180)/360)-1)*360; // (-180,180]
    }

    public static double boundTheta0to360(double theta) {
        return theta - Math.floor(theta/360)*360;  // [0,360)
    }

    public static double deltaThetaInDegrees(double from, double to) {
        return boundThetaNeg180to180(to - from);
    }

    public static double deltaThetaInRadians(double from, double to) {
        return boundThetaNegPiToPi(to - from);
    }

    public static int scale(int input,
                            int lowInput, int highInput, int lowOutput, int highOutput) {
        final int inputRange = highInput - lowInput;
        final int outputRange = highOutput - lowOutput;

        return (input - lowInput) * outputRange / inputRange + lowOutput;
    }

    public static double scale(double input,
                                   double lowInput, double highInput, double lowOutput, double highOutput) {
        final double inputRange = highInput - lowInput;
        final double outputRange = highOutput - lowOutput;

        return  (input - lowInput) * outputRange / inputRange + lowOutput;
    }

    public static double deadZone(double input, double deadband) {
        return Math.abs(input) >= deadband ? input : 0;
    }

    public static double constrain(double n, double min, double max) {
        return Math.max(Math.min(n, max), min);
    }

    public static boolean isInRange(double a, double b, double epsilon) {
        return Math.abs(a - b) < epsilon;
    }


    public static boolean isZero(double val) {
        return Math.abs(val) < 0.0000001;
    }

    public static boolean isEqual(double v1, double v2) {
        return isZero(v1 - v2);
    }

    public static boolean epsilonEqual(double v1, double v2, double epsilon) {
        return Math.abs(v1 - v2) < epsilon;
    }

}
