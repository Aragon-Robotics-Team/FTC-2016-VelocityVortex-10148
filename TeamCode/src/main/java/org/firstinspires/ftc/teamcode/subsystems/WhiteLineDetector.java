package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.LED;

public class WhiteLineDetector {
    private ColorSensor line;
    private DeviceInterfaceModule dim1;
    private LED led2;
    private boolean isLedLit;
    private int ledChannel;

    public WhiteLineDetector (ColorSensor line, DeviceInterfaceModule dim1, LED led2) {
        this.dim1 = dim1;
        this.line = line;
        this.led2 = led2;

        isLedLit = true;

        led2.enable(true);
    }

    public void setLed(boolean lit) {
        isLedLit = lit;
        dim1.setDigitalChannelState(ledChannel, isLedLit);
    }
    public boolean isLedLit() {
        return isLedLit;
    }
    public boolean toggleLed() {
        led2.enable(!isLedLit);
        isLedLit = !isLedLit;
        return isLedLit;
    }

    public int[] getRawColors() {
        return new int[]{
                line.alpha() * 255 / 800,
        };
    }
}
