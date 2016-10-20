package org.firstinspires.ftc.teamcode;
/*
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
*/

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import org.firstinspires.ftc.teamcode.subsystems.*;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="Autonomous")
@Disabled
public class Auto extends OpMode {

    private DcMotor flywheel;
    private DcMotor frontLeft, frontRight, backLeft, backRight;

    public void init()
    {
        frontLeft = hardwareMap.dcMotor.get("drive_front_left");
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setDirection(DcMotor.Direction.FORWARD);


        frontRight = hardwareMap.dcMotor.get("drive_front_right");
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setDirection(DcMotor.Direction.REVERSE);

        backLeft = hardwareMap.dcMotor.get("drive_back_left");
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setDirection(DcMotor.Direction.FORWARD);

        backRight = hardwareMap.dcMotor.get("drive_back_left");
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        flywheel = hardwareMap.dcMotor.get("fly_wheel");
        flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }


    public void runOpMode ()  {
        
    }

    @Override
    public void loop() {

    }
}
