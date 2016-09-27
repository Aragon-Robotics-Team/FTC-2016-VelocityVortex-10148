package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import java.lang.Math;

/**
 * This is NOT an opmode.
 * <p>
 * This class can be used to define all the hardware.
 * <p>
 * MOTOR NAMING ---- HELLA IMPORTANT, MAJOR KEY!!!
 * For FLywheel:
 * Flywheel Motor: "FlyWheel"
 * <p>
 * <p>
 * FOR HOLONOMIC:
 * FRONT LEFT  MOTOR:   "frontLeft"
 * FRONT RIGHT MOTOR:   "frontRight"
 * BACK  LEFT  MOTOR:   "backLeft"
 * BACK  RIGHT MOTOR:   "backRight"
 * <p>
 * FOR STANDARD 2-MOTOR DRIVE:
 * Left  Motor: "left"
 * Right Motor: "right"
 */
public class DriveModules {
    //Declaration of motors for Holonomic Drive
    public DcMotor frontLeft = null,
        frontRight = null,
        backLeft = null,
        backRight = null,
        flyWheel = null,
    //Declaration of motors for Standard Drive
        left = null,
        right = null;
    //Declare and Initialise Holonomic variables
    private double flPwr, frPwr, blPwr, brPwr;
    /* local OpMode members. */
    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    /* Constructor */
    public DriveModules() {

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;
        // Initialize Flywheel Motor
        flyWheel = hwMap.dcMotor.get("FlyWheel");


        // Initialize Holonomic Motors
        frontLeft = hwMap.dcMotor.get("frontLeft");
        frontRight = hwMap.dcMotor.get("frontRight");
        backLeft = hwMap.dcMotor.get("backLeft");
        backRight = hwMap.dcMotor.get("backRight");

        //Initialize Standard Drive Motors
        left = hwMap.dcMotor.get("Left");
        right = hwMap.dcMotor.get("Right");

        //set wheel direction (reverse if on the right)
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        left.setDirection(DcMotor.Direction.FORWARD);
        right.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to zero power
        flyWheel.setPower(0);

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

        left.setPower(0);
        right.setPower(0);
    }

    //Tank Drive Method
    public void Tank(double leftPwr, double rightPwr) {
        //Balance inputs greater than 1 for both motors.
        //Note: does not account for when (leftPwr + rightPwr) is greater 2 or less than -2
        if (leftPwr > 1) {
            rightPwr -= (leftPwr - 1);
            leftPwr = 1;
        }
        if (leftPwr < -1) {
            rightPwr += (leftPwr + 1);
            leftPwr = -1;
        }
        if (rightPwr > 1) {
            leftPwr -= (rightPwr - 1);
            rightPwr = 1;
        }
        if (rightPwr < -1) {
            leftPwr -= (rightPwr + 1);
            rightPwr = -1;
        }


        left.setPower(leftPwr);
        right.setPower(rightPwr);
    }

    //Holonomic Drive Method
    public void Holonomic(double movX, double movY, double rot) {

        //assign motor powers their inputs based on position on the robot
        flPwr = Range.clip((+movX + movY), -1, 1) + rot;
        frPwr = Range.clip((-movX + movY), -1, 1) - rot;
        blPwr = Range.clip((-movX + movY), -1, 1) + rot;
        brPwr = Range.clip((+movX + movY), -1, 1) - rot;

        //balance movement and rotation
        brPwr -= Math.max(flPwr-1, 0) - Math.min(flPwr+1, 0);
        blPwr -= Math.max(frPwr-1, 0) - Math.min(frPwr+1, 0);
        frPwr -= Math.max(blPwr-1, 0) - Math.min(blPwr+1, 0);
        flPwr -= Math.max(brPwr-1, 0) - Math.min(brPwr+1, 0);

        frontLeft.setPower(flPwr);
        frontRight.setPower(frPwr);
        backLeft.setPower(blPwr);
        backRight.setPower(brPwr);
    }

    /***
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs Length of wait cycle in mSec.
     * @throws InterruptedException
     */
    public void waitForTick(long periodMs) throws InterruptedException {

        long remaining = periodMs - (long) period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0)
            Thread.sleep(remaining);

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}

