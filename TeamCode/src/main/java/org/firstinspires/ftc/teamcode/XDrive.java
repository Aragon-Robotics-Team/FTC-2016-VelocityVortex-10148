package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "XDrive", group = "Holonomic")
public class XDrive extends OpMode {
    DcMotor motorBackLeft;
    DcMotor motorBackRight;
    DcMotor motorFrontLeft;
    DcMotor motorFrontRight;
    DcMotor spin;

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void init() {

        motorBackLeft = hardwareMap.dcMotor.get("bl");
        motorBackRight = hardwareMap.dcMotor.get("br");
        motorFrontLeft = hardwareMap.dcMotor.get("fl");
        motorFrontRight = hardwareMap.dcMotor.get("fr");
        spin = hardwareMap.dcMotor.get("spin");

        motorFrontRight.setDirection(DcMotor.Direction.REVERSE);
        motorBackRight.setDirection(DcMotor.Direction.REVERSE);

        runtime.reset();
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();

        // the left stick drives sideways (left and right), forward (up), and backwards(down)
        // the right stick does all the turns
        // only one gamepad
        // y=x^5

        if (gamepad2.right_bumper) {
            motorFrontRight.setPower(Math.pow(Range.clip(gamepad2.right_stick_x - gamepad2.left_stick_x + gamepad2.left_stick_y, -1, 1), 5));
            motorBackRight.setPower(Math.pow(Range.clip(gamepad2.right_stick_x + gamepad2.left_stick_x + gamepad2.left_stick_y, -1, 1), 5));
            motorBackLeft.setPower(Math.pow(Range.clip(-gamepad2.right_stick_x + gamepad2.left_stick_x + gamepad2.left_stick_y, -1, 1), 5));
            motorFrontLeft.setPower(Math.pow(Range.clip(-gamepad2.right_stick_x - gamepad2.left_stick_x + gamepad2.left_stick_y, -1, 1), 5));

            if (gamepad2.dpad_up) {
                spin.setPower(-0.6);
            } else if (gamepad2.dpad_down) {
                spin.setPower(0.2);
            } else {
                spin.setPower(-0.1);
            }
        } else {
            motorFrontRight.setPower(Math.pow(Range.clip(gamepad1.right_stick_x - gamepad1.left_stick_x + gamepad1.left_stick_y, -1, 1), 5));
            motorBackRight.setPower(Math.pow(Range.clip(gamepad1.right_stick_x + gamepad1.left_stick_x + gamepad1.left_stick_y, -1, 1), 5));
            motorBackLeft.setPower(Math.pow(Range.clip(-gamepad1.right_stick_x + gamepad1.left_stick_x + gamepad1.left_stick_y, -1, 1), 5));
            motorFrontLeft.setPower(Math.pow(Range.clip(-gamepad1.right_stick_x - gamepad1.left_stick_x + gamepad1.left_stick_y, -1, 1), 5));

            if (gamepad1.dpad_up) {
                spin.setPower(-0.6);
            } else if (gamepad1.dpad_down) {
                spin.setPower(0.2);
            } else {
                spin.setPower(-0.1);
            }
        }
    }

    @Override
    public void stop() {
    }
}
