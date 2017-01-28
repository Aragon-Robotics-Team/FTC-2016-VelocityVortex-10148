package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class BasicMotor {
    private DcMotor motor;

    public BasicMotor(DcMotor motor) {
        this.motor = motor;
        motor.setDirection(DcMotor.Direction.FORWARD);
    }

    public void stop() {
        motor.setPower(0.0);
    }
    public void setPower(double power) {
        power = Range.clip(power, 0, 1);
        motor.setPower(power);
    }
}