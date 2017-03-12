package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.BeaconDetector;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

/* Designed to push one beacon
 *
 */
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name= "Autonomous", group= "AutoOp")
public class Autonomous extends LinearOpMode {

    private Drivetrain drivetrain;
    private BeaconDetector colorSensor;
    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get("drive_front_left"),hardwareMap.dcMotor.get("drive_front_right"), hardwareMap.dcMotor.get("drive_back_left"),hardwareMap.dcMotor.get("drive_back_right"));
        colorSensor = new BeaconDetector(hardwareMap.colorSensor.get("sensor"), hardwareMap.deviceInterfaceModule.get("dim1"), hardwareMap.led.get("led1"));
        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        waitForStart();

        //drive sideways to the left
        sidewaysLeft();
        //drive backwards
        driveBackwards();
        //detect beacon
        if(colorSensor.isRed()) {

        }
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
