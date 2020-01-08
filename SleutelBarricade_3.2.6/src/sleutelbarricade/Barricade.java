package sleutelbarricade;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author Tom Spek, Colin Werkhoven, Vedat Yilmaz
 */
public class Barricade extends GameObject{
    
    private final String Barricade_IMG_PATH = "src/images/Barricade.jpg";    
    private BufferedImage Barricade = null;
    private final Object[] options = {"Yes", "No"};
    private boolean possible;
    
    public Barricade(String objectName, int x, int y, int passCode) {
        super(objectName, x, y, passCode);
    }
    
    @Override
    public void initializeImages(){
        try{
            Barricade = ImageIO.read(new File(Barricade_IMG_PATH));                 
        }catch(IOException exc){
            exc.printStackTrace();
        }
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(Barricade, x, y, null);
    }
    
    public boolean unlock(GameObject inventory, GameObject objectPos, ArrayList<GameObject> objectArray){
        int j = JOptionPane.showOptionDialog(null, "Would you like to open the barricade with code: " + objectPos.getPassCode(),
        "Barricade pop-up", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
        null, options, "Yes");
        
        if (j == 0 && inventory.getPassCode() == objectPos.getPassCode()) {
            objectArray.remove(objectArray.indexOf(objectPos));
            objectPos = null;
            possible = true;
        } else if (j == 0 && inventory.getPassCode() != objectPos.getPassCode()) {
            JOptionPane.showMessageDialog(null, "Can't open barricade, you don't have the right key");
            possible = false;
        }else{
            possible = false;
        }
        return possible;
    }
}
