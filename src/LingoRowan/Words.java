/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LingoRowan;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author student
 */
public class Words {

private String[] wordsArr = {
    "aanbod",
    "auteur",
    "bakpan",
    "balkon",
    "bedrag",
    "cactus",
    "claxon",
    "dwazen",
    "deuren",
    "ernstig",
    "effect",
    "figuur",
    "filmer",
    "gitaar",
    "geluid",
    "hartje",
    "handen",
    "idioot",
    "inkoop",
    "kapper",
    "kelder",
    "joggen",
    "jampot",
    "krekel",
    "kapsel",
};

public String getWord(){

 return wordsArr[(int)(Math.random() * wordsArr.length)];
}

  
}
