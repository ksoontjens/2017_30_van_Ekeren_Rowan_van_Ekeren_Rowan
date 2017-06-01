/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LingoRowan;

import org.bluray.ui.event.HRcEvent;
import javax.tv.xlet.*;
import java.awt.event.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import org.dvb.event.UserEvent;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;
import org.havi.ui.event.HActionListener;
import java.util.Timer;
import java.util.TimerTask;
import org.dvb.event.EventManager;
import org.dvb.event.UserEventListener;
import org.dvb.event.UserEventRepository;
//import java.util.concurrent.TimeUnit;
/**
 *
 * @author student
 */
public class Game implements HActionListener,UserEventListener {
     HScene scene=HSceneFactory.getInstance().getDefaultHScene();
     HTextButton[] inputLetters = new HTextButton[26];
     HTextButton[][] playGridFields = new HTextButton[6][6];
     HTextButton[] userWordField = new HTextButton[6];
     String concatUserWord = "";
      int gameWidth = 726;
      int gameHeight = 520;
      int userCounter = 0;
      int currentVerticalRow = 0;
      int canRun = 0;
      int resetCounter=0;
       int ammountRight = 0;
       int allowNewGame = 0;
      String currWord;
public void newGame() {
    makeKeyboard();
    makePlayGrid();
    makeUserWordGrid();
    resetGameGrid();
    startRound();
}
    public void startRound(){
        Words word = new Words();
        currWord = word.getWord();  
        playGridFields[0][0].setTextContent(Character.toString(currWord.charAt(0)), 7);
    }
    public void makeKeyboard(){

      int widthButton = gameWidth / 13;
      int heightButton = gameWidth / 13;
      System.out.println("width button = " + widthButton);
    
      for(int i = 0; i < inputLetters.length; i++ ){
          if( i < 13){
              inputLetters[i] = new HTextButton(""+ ((char)(65 + i)),widthButton * i, gameHeight - heightButton,widthButton,heightButton);
              
             
          }else{
              inputLetters[i] = new HTextButton(""+ ((char)(65 + i)),widthButton * (i - 13) ,gameHeight,widthButton,heightButton);
          }
          
          scene.add(inputLetters[i]);
          System.out.println("button activated" + i);
          inputLetters[0].requestFocus();
      }
      
      //add focus points and event listeners
      for(int i = 0; i < inputLetters.length; i++ ){
           if( i < 13){
               if(i == 0){
                   inputLetters[i].setFocusTraversal(null, inputLetters[i + 13],null,  inputLetters[i + 1]);
               }else if( i == 12){
                   inputLetters[i].setFocusTraversal(null, inputLetters[i + 13],inputLetters[i - 1],  null);
               }else{
                   inputLetters[i].setFocusTraversal(null, inputLetters[i + 13],inputLetters[i - 1],  inputLetters[i + 1]);
               }
           }else{
                  if(i == 13){
                   inputLetters[i].setFocusTraversal(inputLetters[i - 13], null,null,  inputLetters[i + 1]);
               }else if( i == 25){
                   inputLetters[i].setFocusTraversal(inputLetters[i - 13], null,inputLetters[i - 1],  null);
               } else{
                   inputLetters[i].setFocusTraversal(inputLetters[i - 13],null ,inputLetters[i - 1],  inputLetters[i + 1]); 
               }
           }
          inputLetters[i].addHActionListener(this);
          inputLetters[i].setActionCommand(""+ (char) (65 + i));
      }

    }
    
    public void makePlayGrid(){
           
            int devider = 6;
            int horRows = 6;
            int verRows = 6;
            int widthField = gameWidth / 13;
            int heightField = gameWidth / 13;
            int heightOffset = 20;
            int widthOffset = 20;        
           
            for(int i = 0; i < verRows; i++ ){
                for(int a = 0; a < horRows; a++ ){
                playGridFields[i][a] = new HTextButton("", widthOffset + (widthField * a) , heightOffset + (heightField * i), widthField, heightField);
                playGridFields[i][a].setBackground(Color.BLUE);
                playGridFields[i][a].setBackgroundMode(HVisible.BACKGROUND_FILL);
                scene.add(playGridFields[i][a]);
                }
           }
    }
    public void registerLetter(String letter){
             
     
      
    int maxCount = 6;
        if(allowNewGame == 1){
                     resetGameGrid();
        startRound();
        currentVerticalRow = 0;
        allowNewGame = 0;
        }
        if(currentVerticalRow < 7){
        if(userCounter < maxCount){
        if(currentVerticalRow < 6){
            userWordField[userCounter].setTextContent(letter, 7);
        userWordField[userCounter].repaint();
        concatUserWord = concatUserWord + letter;
        userCounter++;
        }

        if(userCounter == 6){
        if(currentVerticalRow < 6){
        processWord(currWord,currentVerticalRow);
         System.out.println("process ai word now" + currWord );
          System.out.println("process user word now" + concatUserWord );
        if(currWord.equalsIgnoreCase(concatUserWord)){
            allowNewGame =1;
              System.out.println("win" + concatUserWord );
        
        }
        }
         //startRound();
        System.out.println("process user word now" + concatUserWord );
        currentVerticalRow++;
        


        }else if(currentVerticalRow == 6){
                  
        resetGameGrid();
        startRound();
        currentVerticalRow = 0;
       
        
        
        }
        }else{
        resetUserFields();
        }    
    }else{
    
    }
    }
    public void resetUserFields(){
        for(int i = 0; i < userWordField.length; i++ ){
            userWordField[i].setTextContent("", 7);
            userWordField[i].repaint();
        } 
        concatUserWord = "";
        userCounter = 0;
    }

    public void processWord(String aiWord, int verticalRow){
                char[] correct = new char[6];
        char[] wrongPlace = new char[6];
        String s = aiWord.toUpperCase();
     System.out.println("aiWord = " + s);
        char[] wrong = new char[6];
        
        char[] indexCharsAI = new char[6];
        int charsInWord = 6;
       
            System.out.println(s.charAt(0));
            System.out.println(concatUserWord.charAt(0));

            for(int i = 0; i < charsInWord; i++){
               if(s.charAt(i) == concatUserWord.charAt(i)){
                       System.out.println("CHECK");
                   correct[i] = s.charAt(i);
 
                   

               }else{
                    wrong[i] = concatUserWord.charAt(i);
               }
            }

            for(int i = 0; i < charsInWord; i++){
                if (wrong[i] != 0){
                    indexCharsAI[i] = s.charAt(i);
                    System.out.println("indexCharsAI " + indexCharsAI[i]);
                    System.out.println("wrong " + wrong[i]);
                }
            }
           for(int i = 0; i < charsInWord; i++){
                if (indexCharsAI[i] != 0 && checkIfStringArrayHasValue(indexCharsAI, wrong[i]) == 1){
                   wrongPlace[i] = wrong[i];
                   System.out.println("wrongplace " + wrongPlace[i]);
                }
            }
           
          // if(verticalRow == 0){
          //     resetGameGrid();
          // } 
            
            
           for(int i = 0; i < charsInWord; i++){
            
               playGridFields[verticalRow][i].setTextContent(Character.toString(concatUserWord.charAt(i)), 7);
               
            }
                       for(int i = 0; i< charsInWord; i++){
               if(wrongPlace[i] != 0){
                 playGridFields[verticalRow][i].setBackground(Color.orange);
               }
           }
           for(int i = 0; i< charsInWord; i++){
               if(correct[i] != 0){
                 playGridFields[verticalRow][i].setBackground(Color.green);
               }
           }


    }
    public void resetGameGrid(){
        int verRow = 6; 
        int horRow = 6;
        
        for(int i = 0; i < verRow; i++){
            for(int a = 0; a < horRow; a++){
                  playGridFields[i][a].setTextContent("", 7);
                  playGridFields[i][a].setBackground(Color.blue);
            }
        }
    }
    
    public int checkIfStringArrayHasValue(char[] charArray, char letter){
        for(int i = 0; i < 6 ; i++){
    
            if(charArray[i] == letter){
            System.out.println("letter " + letter);
            System.out.println("chararray " + charArray[i]);
                  return 1;
            }
        }
        return 0;
    }
    
    public void makeUserWordGrid(){
            int widthField = gameWidth / 13;
            int heightField = gameWidth / 13;
            int heightOffset = 160;
            int widthOffset = 370;   
        for(int i = 0; i < userWordField.length; i++ ){
            userWordField[i] =  userWordField[i] = new HTextButton("", widthOffset + (widthField * i) , heightOffset, widthField, heightField);
                userWordField[i].setBackground(Color.BLACK);
                userWordField[i].setBackgroundMode(HVisible.BACKGROUND_FILL);
                scene.add(userWordField[i]);
        }
    }
   
    public void actionPerformed(ActionEvent arg0) {

        //System.out.println("button pressed " + arg0.getActionCommand());
        registerLetter(arg0.getActionCommand());
       
        
    }

    public void userEventReceived(UserEvent e) {
          
        throw new UnsupportedOperationException("Not supported yet.");
    }
}



