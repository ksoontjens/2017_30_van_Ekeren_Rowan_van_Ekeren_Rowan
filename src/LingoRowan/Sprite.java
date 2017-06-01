/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LingoRowan;

import java.awt.Color;
import java.awt.Image;
import java.awt.MediaTracker;
import org.havi.ui.HIcon;
import org.havi.ui.HStaticText;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;

/**
 *
 * @author student
 */
public class Sprite extends HIcon implements ObserverInterface {
    int x,y;
    Image img;
    int richting=10;
    public Sprite(String bitmap_naam, int x, int y)
    {
        super();
        this.x=x; this.y=y;
        img=this.getToolkit().getImage(bitmap_naam);
        MediaTracker mt=new MediaTracker(this);
        mt.addImage(img,1);
        try {
            mt.waitForAll();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.setGraphicContent(img, HVisible.NORMAL_STATE);
        this.setBounds(x, y, img.getWidth(this), img.getHeight(this));
    }
    public void update(int tijd) {
        x+=richting; if (x>700 || x < 1) richting=-richting;
        this.setBounds(x, y, 32, 32);
        this.repaint();
    }
    

}
