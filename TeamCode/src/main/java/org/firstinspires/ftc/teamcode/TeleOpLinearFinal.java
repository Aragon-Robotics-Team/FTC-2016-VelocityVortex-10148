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

    public void init(){
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get("drive_front_left"),hardwareMap.dcMotor.get("drive_front_right"), hardwareMap.dcMotor.get("drive_back_left"),hardwareMap.dcMotor.get("drive_back_right"));
    }
    public void loop(){
        drivetrain.holonomicDrive(-Math.pow(gamepad1.left_stick_x, 3), -Math.pow(gamepad1.left_stick_y, 3), Math.pow(gamepad1.right_stick_x, 3));
        telemetry.addData("G1_left_stick:", (gamepad1.left_stick_x*100)+ " " + gamepad1.left_stick_y*100);
        telemetry.addData("G1_right_stick:", (gamepad1.right_stick_x*100)+ " " + gamepad1.right_stick_y*100);
    }
}