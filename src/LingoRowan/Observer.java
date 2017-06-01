/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LingoRowan;

/**
 *
 * @author student
 */
public class Observer implements ObserverInterface {

    public void update(int tijd) {
        System.out.println(this.toString()+" tijd = "+tijd);
    }

}
