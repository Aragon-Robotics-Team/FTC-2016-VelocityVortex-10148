package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannelController;

import org.firstinspires.ftc.teamcode.subsystems.*;

@TeleOp(name="Drive: TeleOp Single", group="Drive")
@Disabled
public class TeleOpSingle extends OpMode {

    private Drivetrain drivetrain;
    private FlyWheel flyWheel;
    private ConveyorBelt conveyorBelt;
    private HopperServo hopperServo;
    private CapBallLifter capBallLifter;

    private double neutral = 0.478;

    public void init() {
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get("drive_front_left"), hardwareMap.dcMotor.get("drive_front_right"), hardwareMap.dcMotor.get("drive_back_left"), hardwareMap.dcMotor.get("drive_back_right"));
        drivetrain.stop();

        flyWheel = new FlyWheel(hardwareMap.dcMotor.get("flywheel_forward"),hardwareMap.dcMotor.get("flywheel_backward"));
        flyWheel.start();

        conveyorBelt = new ConveyorBelt(hardwareMap.dcMotor.get("conveyor"));

        hopperServo = new HopperServo(hardwareMap.servo.get("hopper_servo"));
        hopperServo.stop();

        capBallLifter = new CapBallLifter(hardwareMap.dcMotor.get("lift"),hardwareMap.servo.get("lift_release"), hardwareMap.servo.get("lift_release2"));
        capBallLifter.stop();
    }

    @Override
    public void start() {
        telemetry.addLine("Dont lose");
    }

    @Override
    public void loop() {

        drivetrain.holonomicDrive(-Math.pow(gamepad1.left_stick_x,7), Math.pow(gamepad1.left_stick_y,7), Math.pow(gamepad1.right_stick_x,7));

        conveyorBelt.setConveyorPower(gamepad1.right_trigger);

        if(gamepad1.right_bumper){
            flyWheel.setPower(1);
        } else{
            flyWheel.setPower(0);
        }

        if(gamepad1.b){
            neutral -= 0.005;
            while(gamepad1.b);
        }
        if(gamepad1.x){
            neutral += 0.005;
            while(gamepad1.x);
        }

        if(gamepad1.dpad_up){
            hopperServo.setSpeed(0.55);
        }else if(gamepad1.dpad_down){
            hopperServo.setSpeed(0.45);
        }else{
            hopperServo.setSpeed(neutral);
        }

        if(gamepad1.left_bumper){
            capBallLifter.setPower(-0.5);
        }else{
            capBallLifter.setPower(gamepad1.left_trigger);
        }

        if(gamepad1.a){
            capBallLifter.releaseLift();
            while(gamepad1.a);
        }
        telemetry.update();

    }

    public void stop() {
        drivetrain.stop();

    }
}
