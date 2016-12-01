package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

/**
 * Created by akshaybodla on 12/1/16.
 */
@TeleOp(name="Drive: DriveTest", group="Drive")

public class TeleOpDrive extends OpMode {
    Drivetrain drivetrain;

    public void init() {
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get("drive_front_left"), hardwareMap.dcMotor.get("drive_front_right"), hardwareMap.dcMotor.get("drive_back_left"), hardwareMap.dcMotor.get("drive_back_right"));
        drivetrain.stop();
    }
    public void loop(){
        drivetrain.holonomicDrive(-Math.pow(gamepad1.left_stick_x,3), Math.pow(gamepad1.left_stick_y,3), Math.pow(gamepad1.right_stick_x,3));

    }

    public void stop(){
        drivetrain.stop();
    }
}
