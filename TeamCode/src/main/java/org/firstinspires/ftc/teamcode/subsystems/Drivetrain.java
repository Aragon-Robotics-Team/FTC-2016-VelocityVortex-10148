package org.firstinspires.ftc.teamcode.subsystems;

import android.text.method.MovementMethod;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //set to motors to run using encoders for even speed
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //set wheel direction (reverse if on the right)
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
    }

    //Holonomic Drive Methods
    public void topDownHolonomicDrive(double movX, double movY, double rotX){
        // angles in radians

        double movementStrength = Math.pow((movX * movY),2);
        double movementAngle = Math.atan2(movY,movX);
        double frontLeftAngle = relativeAngle(0.25*Math.PI, movementAngle, frontLeft.getCurrentPosition());
        double frontRightAngle = relativeAngle(-0.25*Math.PI, movementAngle, frontRight.getCurrentPosition());
        double backLeftAngle = relativeAngle(0.75*Math.PI, movementAngle, backLeft.getCurrentPosition());
        double backRightAngle = relativeAngle(-0.75*Math.PI, movementAngle, backRight.getCurrentPosition());

        double forwardLeftPower = Range.clip( Math.sin(frontLeftAngle) * movementStrength + rotX, -1, 1);
        double forwardRightPower = Range.clip( Math.sin(frontRightAngle) * movementStrength - rotX, -1, 1);
        double backLeftPower = Range.clip( Math.sin(backLeftAngle) * movementStrength + rotX, -1, 1);
        double backRightPower = Range.clip( Math.sin(backRightAngle) * movementStrength - rotX, -1, 1);

        frontLeft.setPower(forwardLeftPower);
        frontRight.setPower(forwardRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);

    }

    //finds the angle of each motor relative to the direction of movement
    public double relativeAngle (double motorAngle, double movementAngle, int motorPosition){
        //1440: number of ticks per rotation on the encoder
        //3.75: diameter of wheels
        //18.75: approximation of the diameter of the bot's path
        return
                motorAngle + movementAngle - (2*Math.PI * ((motorPosition/1440)*(Math.PI*3.75))/Math.PI*18.75);
    }

    public void holonomicDrive(double movX, double movY, double rotation) {

        //assign motor powers their inputs based on position on the robot
        double forwardLeftPower = Range.clip((movX + movY), -1, 1) - rotation;
        double forwardRightPower = Range.clip((-1* movX + movY), -1, 1) + rotation;
        double backLeftPower = Range.clip((-1 * movX + movY), -1, 1) - rotation;
        double backRightPower = Range.clip((movX + movY), -1, 1) + rotation;

        //balance movement and rotation
        /*if (forwardLeftPower > 1) {
            backRightPower -= (forwardLeftPower - 1);
            forwardLeftPower = 1.0;
        }
        if (forwardLeftPower < -1) {
            backRightPower += (forwardLeftPower + 1);
            forwardLeftPower = -1.0;
        }
        if (forwardRightPower > 1) {
            backLeftPower -= (forwardRightPower - 1);
            forwardRightPower = 1.0;
        }
        if (forwardRightPower < -1) {
            backLeftPower += (forwardRightPower + 1);
            forwardRightPower = -1.0;
        }
        if (backLeftPower > 1) {
            forwardRightPower -= (backLeftPower - 1);
            backLeftPower = 1.0;
        }
        if (backLeftPower < -1) {
            forwardRightPower += (backLeftPower + 1);
            backLeftPower = -1.0;
        }
        if (backRightPower > 1) {
            forwardLeftPower -= (backRightPower - 1);
            backRightPower = 1.0;
        }
        if (backRightPower < -1) {
            forwardLeftPower += (backRightPower + 1);
            backRightPower = -1.0;
        } */

        frontLeft.setPower(forwardLeftPower);
        frontRight.setPower(forwardRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
    }

    public void mechanumDrive(double movX, double movY, double rotation) {

        //assign motor powers their inputs based on position on the robot
        double forwardLeftPower = Range.clip((-1*movX - movY), -1, 1) - rotation;
        double forwardRightPower = -1*(Range.clip((-1*movX + movY), -1, 1) - rotation);
        double backLeftPower = Range.clip((movX - movY), -1, 1) - rotation;
        double backRightPower = -1*(Range.clip((movX + movY), -1, 1) - rotation);

        frontLeft.setPower(forwardLeftPower);
        frontRight.setPower(forwardRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
    }
    public void mecanumDrive2(double movX, double movY, double rotation) {
        double forwardRightPower = Range.clip((movY + movX - rotation), -1, 1);
        double forwardLeftPower = Range.clip((movY - movX + rotation), -1, 1);
        double backRightPower = Range.clip((movY - movX - rotation), -1, 1);
        double backLeftPower = Range.clip((movY + movX + rotation), -1, 1);

        frontLeft.setPower(forwardLeftPower);
        frontRight.setPower(forwardRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
    }

    public void stop() {
        holonomicDrive(0,0,0);
        mecanumDrive2(0, 0, 0);
        mechanumDrive(0, 0, 0);
    }
}