package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import org.firstinspires.ftc.teamcode.subsystems.*;

@TeleOp(name="Drive: TeleOp Single", group="Drive")
public class TeleOpSingle extends OpMode {

    private Drivetrain drivetrain;
    private DcMotor flywheel;

    public void init() {
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get("drive_front_left"), hardwareMap.dcMotor.get("drive_front_right"), hardwareMap.dcMotor.get("drive_back_left"), hardwareMap.dcMotor.get("drive_back_right"));
        drivetrain.stop();

        flywheel = hardwareMap.dcMotor.get("fly_wheel");
        // 
        flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void start() {
        drivetrain.stop();
        flywheel.setPower(0.0);
    }

    @Override
    public void loop() {
        drivetrain.holonomicDrive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

        if(gamepad1.a == true)
        {
            flywheel.setPower(0.15);

            // just sends data to user
            while(gamepad1.a == true)
            {
                telemetry.addData("text", "Run at speed");
                telemetry.addData("Power: ", flywheel.getPower());
                telemetry.addData("Position: ", flywheel.getCurrentPosition());
            }
        }
        else
            flywheel.setPower(0.0);

    }
    public void stop() {
        drivetrain.stop();
        flywheel.setPower(0.0);
    }
}
