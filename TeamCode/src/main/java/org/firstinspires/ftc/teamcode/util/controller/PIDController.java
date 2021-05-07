package org.firstinspires.ftc.teamcode.util.controller;

/**
 * u(t) = kP * e(t) + kI * int(0,t)[e(t')dt'] + kD * e'(t) + kF
 * where e(t) = r(t) - y(t) and r(t) is the setpoint and y(t) is the
 * measured value. If we consider e(t) the positional error, then
 * int(0,t)[e(t')dt'] is the total error and e'(t) is the velocity error.
 */
public class PIDController {

    private double kP, kI, kD, kF;
    private double setpoint;
    private double measuredValue;
    private double minIntegral, maxIntegral;

    private double errorVal_p;
    private double errorVal_v;

    private double totalError;
    private double prevErrorVal;

    private double errorTolerance_p = 0.05;
    private double errorTolerance_v = Double.POSITIVE_INFINITY;

    private double lastTimeStamp;
    private double period;

    public PIDController(double kp, double ki, double kd, double kf) {
        this(kp, ki, kd, kf, 0, 0);
    }

    public PIDController(double kp, double ki, double kd, double kf, double setpoint, double current) {
        kP = kp;
        kI = ki;
        kD = kd;
        kF = kf;

        this.setpoint = setpoint;
        measuredValue = current;

        minIntegral = -1.0;
        maxIntegral = 1.0;

        lastTimeStamp = 0;
        period = 0;

        errorVal_p = this.setpoint - measuredValue;
        reset();
    }

    public double calculate() { return calculate(measuredValue); }

    public double calculate(double current, double setpoint) {
        setSetpoint(setpoint);
        return calculate(current);
    }

    /**
     * Calculates the control value, u(t).
     * @param current The current measurement of the process variable.
     * @return the value produced by u(t).
     */
    public double calculate(double current) {
        prevErrorVal = errorVal_p;

        double currentTimeStamp = (double)System.nanoTime() / 1E9;
        if (lastTimeStamp == 0) lastTimeStamp = currentTimeStamp;
        period = currentTimeStamp - lastTimeStamp;
        lastTimeStamp = currentTimeStamp;

        if (measuredValue == current) {
            errorVal_p = setpoint - measuredValue;
        } else {
            errorVal_p = setpoint - current;
            measuredValue = current;
        }

        if (Math.abs(period) > 1E-6) {
            errorVal_v = (errorVal_p - prevErrorVal) / period;
        } else {
            errorVal_v = 0;
        }

        /*
        if total error is the integral from 0 to t of e(t')dt', and
        e(t) = sp - pv, then the total error, E(t), equals sp*t - pv*t.
         */
        totalError = period * (setpoint - measuredValue);
        totalError = totalError < minIntegral ? minIntegral : Math.min(maxIntegral, totalError);

        // returns u(t)
        return kP * errorVal_p + kI * totalError + kD * errorVal_v + kF * setpoint;
    }

    public void reset() {
        totalError = 0;
        prevErrorVal = 0;
        lastTimeStamp = 0;
    }

    public void setTolerance(double positionTolerance) {
        setTolerance(positionTolerance, Double.POSITIVE_INFINITY);
    }

    public void setTolerance(double positionTolerance, double velocityTolerance) {
        errorTolerance_p = positionTolerance;
        errorTolerance_v = velocityTolerance;
    }

    public boolean atSetPoint() {
        return Math.abs(errorVal_p) < errorTolerance_p && Math.abs(errorVal_v) < errorTolerance_v;
    }

    public void setPIDF(double kp, double ki, double kd, double kf) {
        kP = kp;
        kI = ki;
        kD = kd;
        kF = kf;
    }

    public void setIntegrationBounds(double integralMin, double integralMax) {
        minIntegral = integralMin;
        maxIntegral = integralMax;
    }

    public double getSetpoint() { return setpoint; }
    public void setSetpoint(double sp) { setpoint = sp; }
    public double[] getCoefficients() { return new double[]{kP, kI, kD, kF}; }
    public double getPositionError() { return errorVal_p; }
    public double[] getTolerance() { return new double[]{errorTolerance_p, errorTolerance_v}; }
    public double getVelocityError() { return errorVal_v; }
    public void clearTotalError() { totalError = 0; }
    public void setP(double kp) { kP = kp; }
    public void setI(double ki) { kI = ki; }
    public void setD(double kd) { kD = kd; }
    public void setF(double kf) { kF = kf; }
    public double getP() { return kP; }
    public double getI() { return kI; }
    public double getD() { return kD; }
    public double getF() { return kF; }
    public double getPeriod() { return period; }

}
