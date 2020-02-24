/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.bling;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.Pin;

public class Bling extends SubsystemBase {
    // Please use the Addressable LED class. It'll make your life a lot easier. You
    // don't want to make a motor controller to control them. They plug directly
    // into
    // into the PWM port on the RoboRio.
    // See:
    // https://docs.wpilib.org/en/latest/docs/software/actuators/addressable-leds.html
    // - Andrew

    // Source code for Blinkin
    // Spark blinkin = new Spark(Pin.BlingLights.id);
    // blinkin.set(Constants.BlingConstants.OCEAN_COLORED_RAINBOW);

    private AddressableLED led = new AddressableLED(Pin.BlingLights.id);
    private AddressableLEDBuffer buffer = new AddressableLEDBuffer(70);

    private int r = 255;
    private int g = 255;
    private int b = 255;

    public boolean endgame;
    public boolean slowDrive;
    public boolean ballLoaded;
    public boolean flywheelOn;
    public boolean armsUp;

    public Bling() {
        led.setLength(buffer.getLength());
        led.setData(buffer);
        led.start();
    }

    @Override
    public void periodic() {
        selectColor();
        for (var i = 0; i < buffer.getLength(); i++) {
            buffer.setRGB(i, r, g, b);
        }

        led.setData(buffer);
    }

    private void selectColor() {
        if (ballLoaded)
            setColor(255, 197, 0);
        else if (flywheelOn)
            setColor(0, 191, 255);
        else if (armsUp)
            setColor(168, 0, 255);
        else if (slowDrive)
            setColor(109, 255, 233);
        else if (endgame)
            setColor(255, 0, 0);
        else
            setColor(255, 255, 255);
    }

    private void setColor(int rVal, int gVal, int bVal) {
        r = rVal;
        g = gVal;
        b = bVal;
    }
}
