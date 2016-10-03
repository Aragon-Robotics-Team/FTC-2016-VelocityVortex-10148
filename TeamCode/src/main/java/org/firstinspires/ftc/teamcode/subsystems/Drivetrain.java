package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * This is NOT an opmode.
 *
 */
public class Drivetrain {
    // Declaration of motors for Holonomic Drive
    private DcMotor frontLeft, frontRight, backLeft, backRight;


    /* Constructor */
    public Drivetrain(DcMotor frontLeft, DcMotor frontRight, DcMotor backLeft, DcMotor backRight) {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;

        //set wheel direction (reverse if on the right)
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to zero pow

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.

    }



    //Tank Drive Method

    //Holonomic Drive Method
    public void HolonomicDrive(double movX, double movY, double rot) {

        //assign motor powers their inputs based on position on the robot
        double FLPwr = Range.clip((+movX + movY), -1, 1) + rot;
        double FRPwr = Range.clip((-movX + movY), -1, 1) - rot;
        double BLPwr = Range.clip((-movX + movY), -1, 1) + rot;
        double BRPwr = Range.clip((+movX + movY), -1, 1) - rot;

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

            if (BRPwr > 1) {
                FLPwr -= (BRPwr - 1);
                BRPwr = 1.0;
            }
            if (BRPwr < -1) {
                FLPwr += (BRPwr + 1);
                BRPwr = -1.0;
            }

            frontLeft.setPower(FLPwr);
            frontRight.setPower(FRPwr);
            backLeft.setPower(BLPwr);
            backRight.setPower(BRPwr);
        }
    }

    public void stop(){HolonomicDrive(0,0,0);
    }
}

