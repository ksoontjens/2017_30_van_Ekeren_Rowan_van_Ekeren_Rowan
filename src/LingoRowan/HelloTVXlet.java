package LingoRowan;

import org.bluray.ui.event.HRcEvent;
import javax.tv.xlet.*;
import java.awt.event.*;
import java.awt.Color;
import java.util.ArrayList;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;
import org.havi.ui.event.HActionListener;
import java.util.Timer;
import org.dvb.event.EventManager;
import org.dvb.event.UserEventListener;
import org.dvb.event.UserEventRepository;


public class HelloTVXlet implements Xlet,HActionListener,UserEventListener
{
      Game game = new Game();
      HScene scene=HSceneFactory.getInstance().getDefaultHScene();
      int gameWidth = 726;
      int gameHeight = 520;
      
    public void destroyXlet(boolean unconditional) throws XletStateChangeException {
    }

    public void initXlet(XletContext ctx) throws XletStateChangeException {
        Observer ob1=new Observer();
        Observer ob2=new Observer();
        Observer ob3=new Observer();
        Subject sub=new Subject();
        sub.register(ob1); sub.register(ob2); sub.register(ob3);
        
        MijnTimerTask mtt=new MijnTimerTask(sub);
        Timer tim=new Timer();
        tim.scheduleAtFixedRate(mtt, 0  , 10); // start op 0 elke 1000ms
        
    System.out.println("Xlet gestart");
    // EventManager aanvragen
    EventManager manager = EventManager.getInstance();
    // Repository
    UserEventRepository repository = new UserEventRepository("Voorbeeld");
    // Events toevoegen
    repository.addKey(org.havi.ui.event.HRcEvent.VK_UP);
    repository.addKey(org.havi.ui.event.HRcEvent.VK_RIGHT);
    repository.addKey(org.havi.ui.event.HRcEvent.VK_DOWN);
    repository.addKey(org.havi.ui.event.HRcEvent.VK_LEFT);
    // Bekend maken b i j EventManager
    manager.addUserEventListener(this, repository);

     //knop1.setBackground(Color.RED);

    //makeKeyboard();
   // makePlayGrid();
 
    game.newGame();
    scene.validate(); scene.setVisible(true);
      
    }
    public void run(){
       
    }

    public void pauseXlet() {
    }

    public void startXlet() throws XletStateChangeException {
    }
       
    
   public void userEventReceived(org.dvb.event.UserEvent e) {
        if (e.getType() == KeyEvent.KEY_PRESSED) {
           // System.out.println ( "Pushed Button" );
            switch( e.getCode ()) {
                case HRcEvent.VK_UP:
                   // System.out.println("VK_UP is PRESSED");
                    break;
                case HRcEvent.VK_DOWN:
                   // System.out.println("VK_DOWN is PRESSED");
                    break;
                case HRcEvent.VK_LEFT:
                  //  System.out.println("VK_LEFT is PRESSED");
                    break;
                case HRcEvent.VK_RIGHT:
                  //  System.out.println("VK_RIGHT is PRESSED");
                    break;
                case HRcEvent.VK_ENTER:
                  //  System.out.println("VK_ENTER is PRESSED");
                    break;
            }
        }
    }

    public void actionPerformed(ActionEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}