package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

public class Intake {
    DcMotor intake;

    public Intake(DcMotor intake) {
        this.intake = intake;
        intake.setDirection(DcMotor.Direction.FORWARD);
    }
    public void setPower(double power) {
        power = Range.clip(power, -1, 1);
        intake.setPower(power);
    }
    public void reverse() {
        intake.setDirection(DcMotor.Direction.REVERSE);
    }
    public void stop() {
        intake.setPower(0.0);
    }
}
