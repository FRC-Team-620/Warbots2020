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

    public boolean endgame = false;
    public boolean slowDrive = false;
    public boolean ballLoaded = false;
    public boolean flywheelOn = false;
    public boolean armsUp = false;

    public Bling() {
        led.setLength(buffer.getLength());
        led.setData(buffer);
        led.start();
        r = 255;
        g = 0;
        b = 0;
    }

    @Override
    public void periodic() {
        //selectColor();
        for (var i = 0; i < buffer.getLength(); i++) {
            if(i % 2 == 0) buffer.setRGB(i, r, g, b);
            else buffer.setRGB(i, 255, 255, 255);
        }

        led.setData(buffer);
    }

    public void setSlowDrive()
    {
        endgame = false;
        slowDrive = true;
        ballLoaded = false;
        flywheelOn = false;
        armsUp = false;
    }

    public void setBallLoaded()
    {
        endgame = false;
        slowDrive = false;
        ballLoaded = true;
        flywheelOn = false;
        armsUp = false;
    }

    public void setFlyWheelOn()
    {
        endgame = false;
        slowDrive = false;
        ballLoaded = false;
        flywheelOn = true;
        armsUp = false;    
    }

    public void setArmsUp()
    {
        endgame = false;
        slowDrive = false;
        ballLoaded = false;
        flywheelOn = false;
        armsUp = true;
    }

    public void setEndGame()
    {
        endgame = true;
        slowDrive = false;
        ballLoaded = false;
        flywheelOn = false;
        armsUp = false;
    }

    public void setDefault()
    {
        endgame = false;
        slowDrive = false;
        ballLoaded = false;
        flywheelOn = false;
        armsUp = false;
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
