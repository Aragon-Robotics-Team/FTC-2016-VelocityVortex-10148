package org.firstinspires.ftc.teamcode.subsystems;

import android.text.method.MovementMethod;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;
/**
 * Created by member on 10/20/16.
 */

public class MotorTestModule {


    private DcMotor testMotor;

    public MotorTestModule(DcMotor testMotor){
        this.testMotor = testMotor;
    }


    public void runTestMotor (double power){
        testMotor.setPower(Range.clip(power,-1,1));
    }
    public void stop() {
        runTestMotor(0);
    }
}
