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
public class EndPoint extends GameObject{
    
    private final String EndPoint_IMG_PATH = "src/images/EndPoint.jpg";    
    private BufferedImage EndPoint = null;
    
    public EndPoint(String objectName, int x, int y) {
        super(objectName, x, y);
    }
    
    @Override
    public void initializeImages(){
        try{
            EndPoint = ImageIO.read(new File(EndPoint_IMG_PATH));
        }catch(IOException exc){
            exc.printStackTrace();
        }
    }
   
    @Override
    public void render(Graphics g) {
        g.drawImage(EndPoint, x, y, null);
    }
}
