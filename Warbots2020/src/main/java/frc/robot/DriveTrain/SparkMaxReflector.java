/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.DriveTrain;

import com.revrobotics.SparkMax;
/**
 * Add your docs here.
 */
public class SparkMaxReflector 
{
    public static SparkMax CreateSparkMax(int channel)
    {
        try
        {
            var smClazz = Class.forName("com.revrobotics.SparkMax");
            var constructor = smClazz.getDeclaredConstructor(int.class);
            constructor.setAccessible(true); 
            return (SparkMax)constructor.newInstance(channel);
        }
        catch(Exception e)
        {
            return null;
        }
        
    }
}
