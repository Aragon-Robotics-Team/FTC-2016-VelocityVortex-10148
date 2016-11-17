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
    private ConveyorBelt conveyorBelt;
    private HopperServo hopperServo;
    private CapBallLifter capBallLifter;

    public void init() {
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get("drive_front_left"), hardwareMap.dcMotor.get("drive_front_right"), hardwareMap.dcMotor.get("drive_back_left"), hardwareMap.dcMotor.get("drive_back_right"));
        drivetrain.stop();

        flyWheel = new FlyWheel(hardwareMap.dcMotor.get("flywheel_forward"),hardwareMap.dcMotor.get("flywheel_backward"));
        flyWheel.start();

        conveyorBelt = new ConveyorBelt(hardwareMap.dcMotor.get("conveyor"));
        conveyorBelt.start();

        hopperServo = new HopperServo(hardwareMap.servo.get("hopper_servo"));
        hopperServo.stop();

        capBallLifter = new CapBallLifter(hardwareMap.dcMotor.get("lift"));

    }

    @Override
    public void start() {
        drivetrain.stop();
    }

    @Override
    public void loop() {
        drivetrain.holonomicDrive(Math.pow(gamepad1.left_stick_x,7), Math.pow(gamepad1.left_stick_y,7), Math.pow(gamepad1.right_stick_x,7));

        flyWheel.setPower(gamepad1.right_trigger);

        if(gamepad1.right_bumper){
            conveyorBelt.setPower(1);
        }
        //conveyorBelt.setPower(gamepad1.right_bumper);

        if(gamepad1.dpad_up){
            hopperServo.changePosition(0.05);
        }
        if(gamepad1.dpad_down){
            hopperServo.changePosition(-0.05);
        }

        capBallLifter.setPower(gamepad1.left_trigger);

    }

    public void stop() {
        drivetrain.stop();
    }
}
