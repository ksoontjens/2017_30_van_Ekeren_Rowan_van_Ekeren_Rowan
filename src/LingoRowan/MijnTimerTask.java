/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LingoRowan;

import java.util.TimerTask;

/**
 *
 * @author student
 */
public class MijnTimerTask extends TimerTask {
    Subject su=null;  int tijd=0;   
    public MijnTimerTask(Subject s)
    {      su=s;       }

    public void run() {
       tijd++;
     //  if (su!=null) //su.update_observers(tijd);
       //  System.out.println("dit is een test" + tijd);

    }
    
}
