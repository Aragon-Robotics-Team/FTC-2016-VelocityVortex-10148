package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.BeaconDetector;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

/* Designed to push one beacon
 *
 */
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name= "Autonomous", group= "AutoOp")
public class Autonomous extends LinearOpMode {

    private Drivetrain drivetrain;
    private BeaconDetector colorSensor;
    private ElapsedTime runtime = new ElapsedTime();
    private String color = colorSensor.detectColor();
    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get("drive_front_left"),hardwareMap.dcMotor.get("drive_front_right"), hardwareMap.dcMotor.get("drive_back_left"),hardwareMap.dcMotor.get("drive_back_right"));
        colorSensor = new BeaconDetector(hardwareMap.colorSensor.get("sensor"), hardwareMap.deviceInterfaceModule.get("dim1"), hardwareMap.led.get("led1"));
        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        waitForStart();

        //drive sideways to the left
        sidewaysLeft();
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.3)) {
            telemetry.addData("Sideways to the Left", runtime.seconds());
            telemetry.update();
            idle();
        }
        //drive forward
        driveForward();
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.3)) {
            telemetry.addData("Forward", runtime.seconds());
            telemetry.update();
            idle();
        }

        wait(1000);

        //detect beacon
        switch (color) {
            case "Red":
                telemetry.addData("Color: ", color);
                telemetry.update();

                wait(1000);

                sidewaysLeft();
                runtime.reset();
                while (opModeIsActive() && (runtime.seconds() < 1.3)) {
                    telemetry.addData("If Red, hit beacon", runtime.seconds());
                    telemetry.update();
                    idle();
                }
                break;
            case "Blue":
                telemetry.addData("Color: ", color);
                telemetry.update();

                wait(1000);

                driveBackwards();
                runtime.reset();
                while (opModeIsActive() && (runtime.seconds() < 1.3)) {
                    telemetry.addData("If Blue, drive to the other button", runtime.seconds());
                    telemetry.update();
                    idle();
                }
                sidewaysLeft();
                runtime.reset();
                while (opModeIsActive() && (runtime.seconds() < 1.3)) {
                    telemetry.addData("Hit the Beacon", runtime.seconds());
                    telemetry.update();
                    idle();
                }
                break;
            default:
                telemetry.addData("Color: ", color);
                telemetry.update();

                wait(1000);

                telemetry.addData("Not detecting anything...", runtime.seconds());
                telemetry.update();

                break;
        }
        drivetrain.stop();
    }

    public void driveForward() {
        drivetrain.holonomicDrive(1, 0, 0);
    }
    public void driveBackwards() {
        drivetrain.holonomicDrive(-1, 0, 0);
    }
    public void sidewaysRight() {
        drivetrain.holonomicDrive(0, 1, 0);
    }
    public void sidewaysLeft() {
        drivetrain.holonomicDrive(0, -1, 0);
    }
}
