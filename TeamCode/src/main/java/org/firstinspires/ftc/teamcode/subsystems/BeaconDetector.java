package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.LED;

public class BeaconDetector {

    private com.qualcomm.robotcore.hardware.ColorSensor beacon;
    private DeviceInterfaceModule dim1;
    private LED led1;
    private boolean isLedLit;
    private int ledChannel;

    public BeaconDetector(ColorSensor beacon, DeviceInterfaceModule dim1, LED led1) {
        this.dim1 = dim1;
        this.beacon = beacon;
        this.led1 = led1;

        isLedLit = false;

        led1.enable(false);

    }

    public boolean isRed() {
        return beacon.red() > beacon.blue();
    }

    public void setLed(boolean lit) {
        isLedLit = lit;
        dim1.setDigitalChannelState(ledChannel, isLedLit);
    }
    public boolean isLedLit() {
        return isLedLit;
    }
    public boolean toggleLed() {
        led1.enable(!isLedLit);
        isLedLit = !isLedLit;
        return isLedLit;
    }
    public int[] getRawColors() {
        return new int[]{
                beacon.alpha() * 255 / 800,
                beacon.red() * 255 / 800,
                beacon.green() * 255 / 800,
                beacon.blue() * 255 / 800
        };
    }
}
