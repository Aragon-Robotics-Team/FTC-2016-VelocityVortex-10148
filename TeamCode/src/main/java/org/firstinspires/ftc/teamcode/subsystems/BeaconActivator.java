package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.hardware.Servo;

/*
 * IMPORTANT TO REMEMBER FOR COLOR SENSOR:
 * -I2C must be plugged in so that the black cable is aligned with black mark on CDIM
 * -LED cable should be plugged into the pin furthest from the black mark on the CDIM
 * -Must be registered as an "Adafruit Color Sensor" in hardware map
 */


public class BeaconActivator {

    private final double SERVO_LEFT_POSITION = 0, SERVO_RIGHT_POSITION = 1;

    private ColorSensor rgbSensor;
    private LED onboardLed;
    private DeviceInterfaceModule cdim;
    private Servo buttonPusher;

    private int ledChannel;
    private boolean isLedLit;

    public BeaconActivator(ColorSensor rgbSensor, DeviceInterfaceModule cdim, LED onboardLed, Servo buttonPusher) {
        this.cdim = cdim;
        this.rgbSensor = rgbSensor;
        this.onboardLed = onboardLed;
        this.buttonPusher = buttonPusher;

        isLedLit = false;

        onboardLed.enable(false);

        buttonPusher.scaleRange(SERVO_LEFT_POSITION, SERVO_RIGHT_POSITION);
    }

    public void activateButton(boolean isTeamRed) {
        if(isRed()) {
            setLeft();
        }
        else {
            setRight();
        }
    }

    public void setLeft(){
        buttonPusher.setPosition(0);
    }
    public void setRight(){
        buttonPusher.setPosition(1);
    }
    public void setCenter(){
        buttonPusher.setPosition(0.5);
    }

    public boolean isRed() {
        return rgbSensor.red() > rgbSensor.blue();
    }

    public void setLed(boolean lit) {
        isLedLit = lit;
        cdim.setDigitalChannelState(ledChannel, isLedLit);
    }

    public boolean isLedLit() {
        return isLedLit;
    }

    public boolean toggleLed() {
        onboardLed.enable(!isLedLit);
        isLedLit = !isLedLit;
        return isLedLit;
    }

    public int[] getRawColors() {
        return new int[]{
                rgbSensor.alpha() * 255 / 800,
                rgbSensor.red() * 255 / 800,
                rgbSensor.green() * 255 / 800,
                rgbSensor.blue() * 255 / 800
        };
    }
}
