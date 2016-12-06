package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

/**
 * Created by akshaybodla on 12/4/16.
 */
@TeleOp(name="Drive: LinearOp", group="Drive")

public class TeleOpLinear extends LinearOpMode {
    Drivetrain drivetrain;
    /*    BeaconDetect beaconDetect;
        WhiteLineDetector whiteLineDetector;*/
    //FlyWheel flyWheel;
   // ConveyorBelt conveyorBelt;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get("drive_front_left"), hardwareMap.dcMotor.get("drive_front_right"), hardwareMap.dcMotor.get("drive_back_left"), hardwareMap.dcMotor.get("drive_back_right"));
     /*   beaconDetect = new BeaconDetect(hardwareMap.colorSensor.get("beacon_detector"), hardwareMap.deviceInterfaceModule.get("dim1"), hardwareMap.led.get("led1"));
        whiteLineDetector = new WhiteLineDetector(hardwareMap.colorSensor.get("line"), hardwareMap.deviceInterfaceModule.get("dim1"), hardwareMap.led.get("led2"));*/
       // flyWheel =  new FlyWheel(hardwareMap.dcMotor.get("FlyWheelForward"), hardwareMap.dcMotor.get("FlyWheelForward1"));
        //conveyorBelt = new ConveyorBelt(hardwareMap.dcMotor.get("conveyor"));

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
            drivetrain.holonomicDrive(-Math.pow(gamepad1.left_stick_x, 3), -Math.pow(gamepad1.left_stick_y, 3), Math.pow(gamepad1.right_stick_x, 3));
            //flyWheel.setPower(gamepad1.right_trigger);
            idle();
        }
    }
}