package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by akshaybodla on 10/17/16.
 * Creates Neverest Motors
 */

public class FlyWheel {
    private DcMotor flyWheelForward, flyWheelForward1;

    /* Constructor */
    public FlyWheel(DcMotor flyWheelForward, DcMotor flyWheelForward1, Servo hopperservo)
    {
        this.flyWheelForward = flyWheelForward;
        flyWheelForward.setDirection(DcMotor.Direction.FORWARD);

        this.flyWheelForward1 = flyWheelForward1;
        flyWheelForward.setDirection(DcMotor.Direction.FORWARD);

    }


    public void start()
    {
        flyWheelForward.setPower(0.0);
        flyWheelForward1.setPower(0.0);
    }


    public void setPower(float power)
    {
        power = Range.clip(power, 0, 1);

        flyWheelForward.setPower(power);
        flyWheelForward1.setPower(power);

    }


}
