package sleutelbarricade;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Tom Spek, Colin Werkhoven, Vedat Yilmaz
 */
public class Wall extends GameObject{
    
    private final String Wall_IMG_PATH = "src/images/Muur.jpeg";    
    private BufferedImage Wall = null;
    
    public Wall(String objectName, int x, int y) {
        super(objectName, x, y);
    }
    
    @Override
    public void initializeImages(){
        try{
            Wall = ImageIO.read(new File(Wall_IMG_PATH));
        }catch(IOException exc){
            exc.printStackTrace();
        }
    }  
        
    @Override
    public void render(Graphics g) {
        g.drawImage(Wall, x, y, null);
    }
}
