package org.firstinspires.ftc.teamcode.subsystems;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


/**
 * Created by albert on 10/16/16.
 */
@TeleOp(name="Drive: TopDownTeleOp Single", group="Drive")
public class TopDownTeleOpSingle extends OpMode {
    public Drivetrain drivetrain;

    double encoderCount;// todo : find out how encoders work
    double encoderTicks = 1440;//
    double botDiameter = 19; // inches - but this is an approximation
    double wheelDiameter = 3.75; // inches

    public void init(){
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get("drive_front_left"), hardwareMap.dcMotor.get("drive_front_right"), hardwareMap.dcMotor.get("drive_back_left"), hardwareMap.dcMotor.get("drive_back_right"));
        drivetrain.stop();
    }

    public void start(){

    }

    public void loop(){
    drivetrain.topDownHolonomicDrive(gamepad1.left_stick_x,gamepad1.left_stick_y,gamepad1.right_stick_x);
    }

    public void stop(){

    }
}
