package org.firstinspires.ftc.teamcode.subsystems;
import com.qualcomm.robotcore.hardware.Servo;

public class BasicServo {
    private Servo Bservo;
    private double ServoPosition = 0;
    private double Neutral = 0;

    public BasicServo(Servo Bservo, double BasePosition){
        this.Bservo = Bservo;
        this.Neutral = BasePosition;
        stop();
    }

    public void stop(){
        Bservo.setPosition(Neutral);
        ServoPosition = Neutral;
    }

    public void setPosition(double Position){
        Bservo.setPosition(Position);
        ServoPosition = Position;
    }

    public void changePosition(double Position){
        ServoPosition += Position;
        Bservo.setPosition(ServoPosition);
    }
}