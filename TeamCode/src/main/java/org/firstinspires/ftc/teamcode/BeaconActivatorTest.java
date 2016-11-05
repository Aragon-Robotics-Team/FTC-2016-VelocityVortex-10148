package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.subsystems.BeaconActivator;

@Autonomous(name="Unnamed10148: Beacon Activator Test", group="unnamed10148")

public class BeaconActivatorTest extends OpMode{

    BeaconActivator beaconActivator;

    public void init() {
        beaconActivator = new BeaconActivator(hardwareMap.colorSensor.get("beacon_sensor"), hardwareMap.deviceInterfaceModule.get("cdim"), hardwareMap.led.get("led"), hardwareMap.servo.get("button_servo"));
    }
    int i = 0;

    @Override
    public void loop() {
        telemetry.addData("RED", beaconActivator.getRawColors()[1]);
        telemetry.addData("BLUE", beaconActivator.getRawColors()[3]);

        beaconActivator.activateButton(true);
    }
}
