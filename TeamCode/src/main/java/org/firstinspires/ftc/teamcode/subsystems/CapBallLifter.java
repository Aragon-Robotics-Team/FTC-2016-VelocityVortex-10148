package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by member on 11/17/16.
 */
public class CapBallLifter {
    private DcMotor lifter;

    public CapBallLifter(DcMotor lifter) {
        this.lifter = lifter;
        lifter.setDirection(DcMotor.Direction.FORWARD);
    }

    public void stop() {
        lifter.setPower(0.0);
    }
    public void setPower(float power) {
        power = Range.clip(power, 0, 1);
        lifter.setPower(power);
    }
}
