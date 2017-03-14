package org.firstinspires.ftc.teamcode.teleop;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

/*
 *
 * CREATED BY CHLOE, 2/9/17
 */

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Drive: MecanumDrive", group="Drive")
public class MecanumDrive extends OpMode{

    private Drivetrain drivetrain;
    private final double MOTORSPEED = 1.0;

    public void init() {
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get("drive_front_left"),hardwareMap.dcMotor.get("drive_front_right"), hardwareMap.dcMotor.get("drive_back_left"),hardwareMap.dcMotor.get("drive_back_right"));
    }

    public void loop() {
        //mechanum drive algorithm
        drivetrain.mechanumDrive(-Math.pow(gamepad1.left_stick_x, 3), -Math.pow(gamepad1.left_stick_y, 3), Math.pow(gamepad1.right_stick_x, 3));

        telemetry.addData("G1_left_stick:", (gamepad1.left_stick_x*100)+ " " + gamepad1.left_stick_y*100);
        telemetry.addData("G1_right_stick:", (gamepad1.right_stick_x*100)+ " " + gamepad1.right_stick_y*100);
        telemetry.addData("intake", gamepad1.right_trigger);
        telemetry.addData("right_trigger:", gamepad1.right_bumper);
    }
}