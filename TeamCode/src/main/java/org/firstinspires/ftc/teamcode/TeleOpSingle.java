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
    private FlyWheel flyWheel;
    private ConveyorBelt conveyor;

    public void init() {
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get("drive_front_left"), hardwareMap.dcMotor.get("drive_front_right"), hardwareMap.dcMotor.get("drive_back_left"), hardwareMap.dcMotor.get("drive_back_right"));
        drivetrain.stop();

        flyWheel = new FlyWheel(hardwareMap.dcMotor.get("fly_wheel_forward"), hardwareMap.dcMotor.get("other_fly_wheel_"));
        conveyor = new ConveyorBelt(hardwareMap.dcMotor.get("conveyor"));

    }

    @Override
    public void start() {
        drivetrain.stop();
        flyWheel.start();
        conveyor.start();
    }

    @Override
    public void loop() {
        drivetrain.holonomicDrive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

        flyWheel.setPower(gamepad1.right_trigger); // assigns motor power
    }
    public void stop() {
        drivetrain.stop();
        flyWheel.start();
        conveyor.start();
    }
}
