package frc.robot.util;

import edu.wpi.first.wpilibj.DigitalInput;

public class ThreeWaySwitch {
    DigitalInput p1,p2;
    public ThreeWaySwitch(int port1, int port2) {
       p1 = new DigitalInput(port1);
       p2 = new DigitalInput(port2);
    }
    //Returns Switch's position from (0-2)
    public int getPosition() {
        return asBinary(new boolean[] {p1.get(),p2.get()});
    }
    

    private int asBinary(boolean[] values) {
        int sum = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i]) {
                sum += Math.pow( 2,i);
    
             }
         }
         return sum;
     }

}
