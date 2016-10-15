package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by akshaybodla on 10/15/16.
 *
 * This class is only a template for the flywheel
 * I will fix it when the flywheel prototype is
 *
 */

public class FlyWheel {
    private DcMotor flyWheel;

    public FlyWheel(DcMotor flyWheel)
    {
        this.flyWheel = flyWheel;
    }

    public void stop()
    {
        flyWheel.setPower(0.0);
    }

    public void setMaxPow()
    {
        flyWheel.setPower(0.0);
        if (flyWheel.getPower() > 1.0)
        {
            flyWheel.setPower(1.0);
        }

        if (flyWheel.getPower() < 0.0)
        {
            flyWheel.setPower(0.0);
        }

        else
            flyWheel.setPower(1.0);
    }
}
