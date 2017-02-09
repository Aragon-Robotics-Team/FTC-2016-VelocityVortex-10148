package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

public class FlyWheels {
    DcMotor flyWheelLeft;
    DcMotor flyWheelRight;

    public FlyWheels(DcMotor flyWheelLeft, DcMotor flyWheelRight) {
        this.flyWheelLeft = flyWheelLeft;
        this.flyWheelRight = flyWheelRight;
        flyWheelLeft.setDirection(DcMotor.Direction.FORWARD);
        flyWheelLeft.setDirection(DcMotor.Direction.FORWARD);
    }

    public void setPower(double power) {
        power = Range.clip(power, -1, 1);
        flyWheelRight.setPower(power);
        flyWheelLeft.setPower(power);
    }
    public void stop() {
        flyWheelLeft.setPower(0.0);
        flyWheelRight.setPower(0.0);
    }
}
