package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.*;

@TeleOp(name="Drive: TeleOp Single", group="Drive")
public class TeleOpSingle extends OpMode {

    private Drivetrain drivetrain;

    public void init() {
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get(" "), hardwareMap.dcMotor.get(" "), hardwareMap.dcMotor.get(" "), hardwareMap.dcMotor.get(" "));
        drivetrain.stop();

    }
    @Override
    public void start() {
        drivetrain.stop();
    }

    @Override
    public void loop() {
        drivetrain.holonomicDrive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
    }
    public void stop() {
        drivetrain.stop();
    }

}
