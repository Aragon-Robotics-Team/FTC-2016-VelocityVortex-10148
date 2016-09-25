package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

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
 * FRONT LEFT  MOTOR:   "FrontLeft"
 * FRONT RIGHT MOTOR:   "FrontRight"
 * BACK  LEFT  MOTOR:   "BackLeft"
 * BACK  RIGHT MOTOR:   "BackRight"
 * <p>
 * FOR STANDARD 2-MOTOR DRIVE:
 * Left  Motor: "Left"
 * Right Motor: "Right"
 */
public class DriveModules {
    // Declaration of motors for Holonomic Drive
    public DcMotor FrontLeft = null;
    public DcMotor FrontRight = null;
    public DcMotor BackLeft = null;
    public DcMotor BackRight = null;
    public DcMotor FlyWheel = null;
    // Declaration of motors for Standard Drive
    public DcMotor Left = null;
    public DcMotor Right = null;
    //Declare and Initialise Holonomic variables
    Double FLPwr;
    Double FRPwr;
    Double BLPwr;
    Double BRPwr;
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
        FlyWheel = hwMap.dcMotor.get("FlyWheel");


        // Initialize Holonomic Motors
        FrontLeft = hwMap.dcMotor.get("FrontLeft");
        FrontRight = hwMap.dcMotor.get("FrontRight");
        BackLeft = hwMap.dcMotor.get("BackLeft");
        BackRight = hwMap.dcMotor.get("BackRight");

        //Initialize Standard Drive Motors
        Left = hwMap.dcMotor.get("Left");
        Right = hwMap.dcMotor.get("Right");

        //set wheel direction (reverse if on the right)
        FrontLeft.setDirection(DcMotor.Direction.FORWARD);
        FrontRight.setDirection(DcMotor.Direction.REVERSE);
        BackLeft.setDirection(DcMotor.Direction.FORWARD);
        BackRight.setDirection(DcMotor.Direction.REVERSE);

        Left.setDirection(DcMotor.Direction.FORWARD);
        Right.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to zero power
        FlyWheel.setPower(0);

        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        BackLeft.setPower(0);
        BackRight.setPower(0);

        Left.setPower(0);
        Right.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.


    }

    //Tank Drive Method
    public void Tank(Double LeftPwr, Double RightPwr) {

        //Balance inputs greater than 1 for both motors.
        //Note: does not account for when (LeftPwr + RightPwr) is greater 2 or less than -2
        if (LeftPwr > 1) {
            RightPwr -= (LeftPwr - 1);
            LeftPwr = 1.0;
        }
        if (LeftPwr < -1) {
            RightPwr += (LeftPwr + 1);
            LeftPwr = -1.0;
        }
        if (RightPwr > 1) {
            LeftPwr -= (RightPwr - 1);
            RightPwr = 1.0;
        }
        if (RightPwr < -1) {
            LeftPwr -= (RightPwr + 1);
            RightPwr = -1.0;
        }


        Left.setPower(LeftPwr);
        Right.setPower(RightPwr);

    }

    //Holonomic Drive Method
    public void Holonomic(double movX, Double movY, Double rot) {

        //assign motor powers their inputs based on position on the robot
        FLPwr = Range.clip((+movX + movY), -1, 1) + rot;
        FRPwr = Range.clip((-movX + movY), -1, 1) - rot;
        BLPwr = Range.clip((-movX + movY), -1, 1) + rot;
        BRPwr = Range.clip((+movX + movY), -1, 1) - rot;

        //balance movement and rotation
        if (FLPwr > 1) {
            BRPwr -= (FLPwr - 1);
            FLPwr = 1.0;
        }
        if (FLPwr < -1) {
            BRPwr += (FLPwr + 1);
            FLPwr = -1.0;
        }
        if (FRPwr > 1) {
            BLPwr -= (FRPwr - 1);
            FRPwr = 1.0;
        }
        if (FRPwr < -1) {
            BLPwr += (FRPwr + 1);
            FRPwr = -1.0;
        }
        if (BLPwr > 1) {
            FRPwr -= (BLPwr - 1);
            BLPwr = 1.0;
        }
        if (BLPwr < -1) {
            FRPwr += (BLPwr + 1);
            BLPwr = -1.0;
        }
        if (BRPwr > 1) {
            FLPwr -= (BRPwr - 1);
            BRPwr = 1.0;
        }
        if (BRPwr < -1) {
            FLPwr += (BRPwr + 1);
            BRPwr = -1.0;
        }

        FrontLeft.setPower(FLPwr);
        FrontRight.setPower(FRPwr);
        BackLeft.setPower(BLPwr);
        BackRight.setPower(BRPwr);
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

