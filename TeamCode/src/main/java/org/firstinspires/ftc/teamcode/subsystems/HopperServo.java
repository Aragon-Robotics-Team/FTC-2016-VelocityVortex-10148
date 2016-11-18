package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by member on 11/17/16.
 */
public class HopperServo {
    private Servo whingmeel;
    private double whingmeelPosition;

    public HopperServo(Servo whingmeel){
        this.whingmeel = whingmeel;
        stop();
    }

    public void stop(){
        whingmeel.setPosition(0);
        whingmeelPosition = 0;
    }

    public void setPosition(double position){
        whingmeelPosition = position;
        whingmeel.setPosition(whingmeelPosition);
    }
}
