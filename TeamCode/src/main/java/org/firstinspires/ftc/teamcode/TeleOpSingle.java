package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystems.*;

@TeleOp(name="Drive: TeleOp Single", group="Drive")
public class TeleOpSingle extends OpMode {

    private Drivetrain drivetrain;
    private FlyWheel flyWheel;

    public void init() {
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get("drive_front_left"), hardwareMap.dcMotor.get("drive_front_right"), hardwareMap.dcMotor.get("drive_back_left"), hardwareMap.dcMotor.get("drive_back_right"));
        drivetrain.stop();
        // flyWheel = new FlyWheel(hardwareMap.dcMotor.get("fly_wheel"));
    }

    @Override
    public void start() {
        drivetrain.stop();
        // flyWheel.stop();
    }

    @Override
    public void loop() {
        drivetrain.holonomicDrive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

       /*
       if (gamepad1.a == true)
            flyWheel.setMaxPow();
        else
            flyWheel.stop();
       */
    }
    public void stop() {
        drivetrain.stop();
        flyWheel.stop();
    }
}
