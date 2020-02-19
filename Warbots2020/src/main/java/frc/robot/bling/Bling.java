/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.bling;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.Constants;
import frc.robot.util.Pin;

import java.sql.Driver;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DriverStation;

public class Bling extends SubsystemBase 
{
    // Please use the Addressable LED class. It'll make your life a lot easier. You
    // don't
    // want to make a motor controller to control them. They plug directly into the
    // PWM
    // port on the RoboRio.
    // See:
    // https://docs.wpilib.org/en/latest/docs/software/actuators/addressable-leds.html
    // - Andrew
    //Spark blinkin = new Spark(Pin.BlingLights.id);

    private AddressableLED led = new AddressableLED(Pin.BlingLights.id);
    private AddressableLEDBuffer buffer = new AddressableLEDBuffer(70);
    private DriverStation driverStation;

    public Bling(DriverStation ds) 
    {
        driverStation = ds;

        led.setLength(buffer.getLength());
        led.setData(buffer);
        led.start();

        //blinkin.set(Constants.BlingConstants.OCEAN_COLORED_RAINBOW);
    }

    @Override
    public void periodic() 
    {
        for(var i = 0; i < buffer.getLength(); i++)
        {
            buffer.setRGB(i, 0, 190, 20);
        }

        led.setData(buffer);
    }
}
