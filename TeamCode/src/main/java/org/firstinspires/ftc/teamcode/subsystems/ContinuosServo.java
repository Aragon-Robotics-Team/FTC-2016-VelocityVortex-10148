package org.firstinspires.ftc.teamcode.subsystems;
import com.qualcomm.robotcore.hardware.Servo;

public class ContinuosServo {
    private Servo cservo;
    private double ServoSpeed = 0;
    private double Neutral = 0;

    public ContinuosServo(Servo cservo, double NeutralSpeed){
        this.cservo = cservo;
        this.Neutral = NeutralSpeed;
        stop();
    }

    public void stop(){
        cservo.setPosition(Neutral);
        ServoSpeed = Neutral;
    }

    public void setSpeed(double speed){
        cservo.setPosition(speed);
        ServoSpeed = speed;
    }

    public void changeSpeed(double speed){
        ServoSpeed += speed;
        cservo.setPosition(speed);
    }
}