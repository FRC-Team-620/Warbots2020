package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.subsystems.Shooter;

public class FireShooter extends SequentialCommandGroup {

    /*
     * Runs one ball past the shooter's ball loaded sensor into the flywheel.
     */
    public FireShooter(Shooter shooter) {
        addCommands(
                new LoadShooter(shooter).withTimeout(5), //Try to load ball is not already loaded
                new InstantCommand(shooter::forward,shooter), //Move Ball out of loaded position.
                new WaitUntilCommand(shooter::isLoaded).withTimeout(5), //Stop motor when next ball is loaded or within 5 seconds
                new InstantCommand(shooter::stop,shooter)
                );
        
    }


}
