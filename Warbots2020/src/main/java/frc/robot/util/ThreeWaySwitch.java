package frc.robot.util;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;

/**
 * Class to read a Three way switch. This class will read two digital inputs and
 * returns a number representing its position.
 * 
 * @author Michael Combs
 *
 */
public class ThreeWaySwitch implements Sendable, AutoCloseable {
    private DigitalInput p1, p2;

    /**
     * Create an instance of a Three-Way Switch class. Creates a Three-Way Switch given
     * two channels.
     * 
     * @param channel1 the DIO channel for the first digital input 0-9 are on-board,
     *                 10-25 are on the MXP
     * @param channel2 the DIO channel for the second digital input 0-9 are
     *                 on-board, 10-25 are on the MXP
     */
    public ThreeWaySwitch(int channel1, int channel2) {
        p1 = new DigitalInput(channel1);
        p2 = new DigitalInput(channel2);
    }

    /**
     * Get the value of the switch position from 0-2. Warning 3 is a possible output
     * if both signals are high.
     * 
     * @return Switch's position from (0-2)
     */
    public int getPosition() {
        return asBinary(new boolean[] { p1.get(), p2.get() });
    }

    /**
     * Takes an array of booleans and parses them into a integer. Where each index
     * in the array of booleans represents a power of 2. IE [false,true,true] would
     * parse to 6
     * 
     * @param values boolean value bits
     * @return int the parsed value of the booleans
     */
    private int asBinary(boolean[] values) {
        int sum = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i]) {
                sum += Math.pow(2, i);

            }
        }
        return sum;
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("Three-way Switch");
        builder.addDoubleProperty("Position", this::getPosition, null);

    }

    @Override
    public void close() throws Exception {
        SendableRegistry.remove(this);
        if (this.p1 != null) {
            this.p1.close();
        }
        if (this.p2 != null) {
            this.p2.close();
        }

    }

}
