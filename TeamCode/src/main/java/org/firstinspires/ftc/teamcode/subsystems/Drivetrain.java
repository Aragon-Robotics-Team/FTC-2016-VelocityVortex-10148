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

        //set wheel direction (reverse if on the right)
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
    }

    //Holonomic Drive Methods
    public void topDownHolonomicDrive(double movX, double movY, double rotX, double encoderCount, double encoderTicks, double wheelDiameter, double botDiameter){
        // angles in radians
        double movementStrength = Math.abs(movX * movY);
        double movementAngle = Math.atan2(movY,movX);
        double botAngle = 2 * Math.PI * ((encoderCount/encoderTicks) * (Math.PI * wheelDiameter)) / Math.PI*botDiameter;
        double movementAngleRelative = movementAngle - botAngle;
        double forwardLeftAngle = 1/4 * Math.PI + movementAngleRelative;
        double forwardRightAngle = -1/4 * Math.PI + movementAngleRelative;
        double backLeftAngle = 3/4 * Math.PI +  movementAngleRelative;
        double backRightAngle = -3/4 * Math.PI + movementAngleRelative;
        double forwardLeftPower = Range.clip( Math.sin(forwardLeftAngle) * movementStrength + rotX, -1, 1);
        double forwardRightPower = Range.clip( Math.sin(forwardRightAngle) * movementStrength + rotX, -1, 1);
        double backLeftPower = Range.clip( Math.sin(backLeftAngle) * movementStrength + rotX, -1, 1);
        double backRightPower = Range.clip( Math.sin(backRightAngle) * movementStrength + rotX, -1, 1);
    };



    public void holonomicDrive(double movX, double movY, double rotation) {

        //assign motor powers their inputs based on position on the robot
        double forwardLeftPower = Range.clip((movX + movY), -1, 1) + rotation;
        double forwardRightPower = Range.clip((-1 * movX + movY), -1, 1) - rotation;
        double backLeftPower = Range.clip((-1 * movX - movY), -1, 1) + rotation;
        double backRightPower = Range.clip((movX - movY), -1, 1) - rotation;

        //balance movement and rotation
        if (forwardLeftPower > 1) {
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
        }

        frontLeft.setPower(forwardLeftPower);
        frontRight.setPower(forwardRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
    }

    public void stop() {
        holonomicDrive(0, 0, 0);
    }
}

