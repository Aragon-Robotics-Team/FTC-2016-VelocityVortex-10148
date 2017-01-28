package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.subsystems.BasicMotor;
import org.firstinspires.ftc.teamcode.subsystems.ContinuosServo;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.BasicServo;
/**
 * Created by Akshay on 1/26/17.
 */
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Drive: LinOpFinal", group="Drive")
public class TeleOpLinearFinal extends OpMode{

    private Drivetrain drivetrain;
    private BasicMotor suction;
    private BasicMotor intake;
    private BasicMotor flywheelLeft;
    private BasicMotor flywheelRight;
    private BasicServo scooper;
    private ContinuosServo buttonPusher;
    private double neutralSpeed = 0.5;
    private final double ONE = 1.0;


    public void init(){
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get("drive_front_left"),hardwareMap.dcMotor.get("drive_front_right"), hardwareMap.dcMotor.get("drive_back_left"),hardwareMap.dcMotor.get("drive_back_right"));
        suction = new BasicMotor(hardwareMap.dcMotor.get("intake"));//configuration error: intake name controlls suction
        intake = new BasicMotor(hardwareMap.dcMotor.get("suction"));//same as above but vice versa
        buttonPusher = new ContinuosServo(hardwareMap.servo.get("button_pusher"),neutralSpeed);
        scooper = new BasicServo(hardwareMap.servo.get("lift_release"),0);
        flywheelLeft = new BasicMotor(hardwareMap.dcMotor.get("flywheelLeft"));
        flywheelRight = new BasicMotor(hardwareMap.dcMotor.get("flywheelRight"));
    }
    public void loop(){
        drivetrain.holonomicDrive(-Math.pow(gamepad1.left_stick_x, 3), -Math.pow(gamepad1.left_stick_y, 3), Math.pow(gamepad1.right_stick_x, 3));
        telemetry.addData("G1_left_stick:", (gamepad1.left_stick_x*100)+ " " + gamepad1.left_stick_y*100);
        telemetry.addData("G1_right_stick:", (gamepad1.right_stick_x*100)+ " " + gamepad1.right_stick_y*100);
        telemetry.addData("intake", gamepad1.right_trigger);
        telemetry.addData("right_trigger:", gamepad1.right_bumper);

        if (gamepad1.right_trigger > 0) {
            intake.setPower(ONE);
        } else {
            intake.stop();
        }

        if (gamepad1.right_bumper){
            intake.setPower(-ONE);
        } else {
            intake.stop();
        }

        if (gamepad1.left_trigger > 0){
            flywheelLeft.setPower(ONE);
            flywheelLeft.setPower(ONE);
        } else {
            flywheelLeft.stop();
            flywheelRight.stop();
        }

        if (gamepad1.b){
            suction.setPower(-ONE);
        } else {
            suction.stop();
        }

        if (gamepad1.a){
            buttonPusher.setSpeed(0.3);
        }
        else if(gamepad1.x){
            buttonPusher.setSpeed(0.7);
        }

        if (gamepad1.y){
            scooper.setPosition(ONE);
        } else {
            scooper.stop();
        }
    }
}