package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.BeaconDetector;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

/* This will measure how much the robot can travel in one second
 *
 */
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name= "Distance Test", group= "AutoOp")
public class DrivingDistanceTest extends LinearOpMode {
    private Drivetrain drivetrain;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get("drive_front_left"), hardwareMap.dcMotor.get("drive_front_right"), hardwareMap.dcMotor.get("drive_back_left"), hardwareMap.dcMotor.get("drive_back_right"));
        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        waitForStart();

        driveForward();
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            telemetry.addData("Drive Forward for 1 second", runtime.seconds());
            telemetry.update();
            idle();
        }

        drivetrain.stop();
        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
        idle();
    }
    public void sidewaysLeft() { drivetrain.holonomicDrive(1, 0, 0); }
    public void sidewaysRight() {
        drivetrain.holonomicDrive(-1, 0, 0);
    }
    public void driveBackwards() {
        drivetrain.holonomicDrive(0, -1, 0);
    }
    public void driveForward() { drivetrain.holonomicDrive(0, 1, 0); }
}
