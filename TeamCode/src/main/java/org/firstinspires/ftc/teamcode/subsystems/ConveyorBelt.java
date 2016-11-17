package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class ConveyorBelt {
    private DcMotor conveyor;

    public ConveyorBelt(DcMotor conveyor) {
        this.conveyor = conveyor;
        conveyor.setDirection(DcMotor.Direction.FORWARD);
    }

    public void start() {
        conveyor.setPower(0.0);
    }
    public void setPower(float power) {
        power = Range.clip(power, 0, 1);
        conveyor.setPower(power);
    }
}

