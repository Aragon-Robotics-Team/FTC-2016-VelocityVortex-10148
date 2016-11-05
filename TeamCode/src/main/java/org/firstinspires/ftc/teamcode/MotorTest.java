package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.*;

@TeleOp(name="Utility: MotorTest", group="Utility")
public class MotorTest extends OpMode {

    private MotorTestModule motorTestModule;
    double motorPower = 0;
    double buttonPower = 1;

    public void init() {
        motorTestModule.stop();
        motorTestModule = new MotorTestModule(hardwareMap.dcMotor.get("testMotor"));
    }

    @Override
    public void start() {
        motorTestModule.stop();
    }

    @Override
    public void loop() {
        motorPower = gamepad2.left_stick_y;

        if(gamepad2.a){
            motorPower = buttonPower;
        }

        if(gamepad2.b){
            motorPower = -buttonPower;
        }

        if(gamepad2.dpad_up){
            buttonPower += 0.05;
        }

        if(gamepad2.dpad_down){
            buttonPower -= 0.05;
        }

        motorTestModule.runTestMotor(motorPower);
        telemetry.addData("MotorPower",  " :%7d", motorPower);
        telemetry.addData("ButtonPower",  " :%7d", buttonPower);
        telemetry.update();
    }

    public void stop() {
        motorTestModule.stop();
    }
}
