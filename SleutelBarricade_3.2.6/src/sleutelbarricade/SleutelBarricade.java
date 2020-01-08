package sleutelbarricade;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JComponent;
import javax.swing.Timer;

/**
 *
 * @author Tom Spek, Colin Werkhoven, Vedat Yilmaz
 */
public class SleutelBarricade extends JComponent implements KeyListener, ActionListener{
    
    private final int walls = 15;
    private final int barricades = 15;
    private final int keys = 5;
    private final ArrayList<GameObject> objectArray = new ArrayList<>();
    private final GameObject[][] playField = new GameObject[10][10];
    private Graphics g;    
    private final Player player = new Player("Player", 37, 37, this);
    private final EndPoint endPoint = new EndPoint("EndPoint", 487, 487);
    private final Timer t;

    public SleutelBarricade(){
        this.t = new Timer(5, this);
        t.start();
        addKeyListener(this);
        setFocusable(true);
    }    
    
    //Convert Array Position to Pixels, !!Unit Testen
    public int positionToPixel(int pos){
        int position = pos * 50+37;
        return position;
    }
    
    //Convert X Pixels to Array Position
    public int pixelToPositionX(int pixels){
        int xPosition = (pixels - 37) / 50;
        return xPosition;
    }
    
    //Convert Y Pixels to Array Position
    public int pixelToPositionY(int pixels){
        int yPosition = (pixels - 37)/50;
        return yPosition;
    }
    
    //Randomize All Objects To The ArrayList
    public void randomizeField(){
        objectArray.clear();
        for (GameObject[] playField1 : playField) {
            for (int j = 0; j < playField1.length; j++) {
                playField1[j] = null;
            }
        }
        player.setInventory(new Key("Key", 0, 0, 0));
        objectArray.add(player);
        objectArray.add(endPoint);
        player.setX(positionToPixel(0));
        player.setY(positionToPixel(0));
        playField[0][0] = player;
        playField[9][9] = endPoint;
        
        //Add Keys to ArrayList
        for(int  i = 0 ; i< keys ; i++){
            int x = 0 ;
            int y = 0;
            int passCode = ThreadLocalRandom.current().nextInt(1,4)*100;
            while(playField[x][y] != null){
                x = new Random().nextInt(playField.length);
                switch (x) {
                    case 0:
                        y = ThreadLocalRandom.current().nextInt(1, 10);
                        break;
                    case 9:
                        y = ThreadLocalRandom.current().nextInt(0, 9);
                        break;
                    default:
                        y = ThreadLocalRandom.current().nextInt(0, 10);
                        break;
                }
            }
            objectArray.add(new Key("Key", positionToPixel(x), positionToPixel(y), passCode));             
            playField[x][y] = objectArray.get(i+2);
        }
        
        //Add Barricades to ArrayList
        for(int i = 0; i<barricades; i++){
            int x = 0;
            int y = 0;
            int passCode = ThreadLocalRandom.current().nextInt(1,4)*100;
            while(playField[x][y] != null){
                x = new Random().nextInt(playField.length);
                switch (x) {
                    case 0:
                        y = ThreadLocalRandom.current().nextInt(1, 10);
                        break;
                    case 9:
                        y = ThreadLocalRandom.current().nextInt(0, 9);
                        break;
                    default:
                        y = ThreadLocalRandom.current().nextInt(0, 10);
                        break;
                }
            }
            objectArray.add(new Barricade("Barricade", positionToPixel(x), positionToPixel(y),passCode));
            playField[x][y] = objectArray.get(i+keys+2);
        }
        
        //Add Walls to ArrayList
        for(int i = 0; i<walls; i++){
            int x = 0;
            int y = 0;
            while(playField[x][y] != null){
                x = new Random().nextInt(playField.length);
                switch (x) {
                    case 0:
                        y = ThreadLocalRandom.current().nextInt(1, 10);
                        break;
                    case 9:
                        y = ThreadLocalRandom.current().nextInt(0, 9);
                        break;
                    default:
                        y = ThreadLocalRandom.current().nextInt(0, 10);
                        break;
                }
            }
            objectArray.add(new Wall("Wall", positionToPixel(x), positionToPixel(y)));
            playField[x][y] = objectArray.get(i+keys+barricades+2);
        }
    }
           
    //Paint playField
    public void paintComponent(Graphics g){
        for(int rows = 0; rows<playField.length; rows++){
            int rowPosition = rows*50;
            for(int columns = 0; columns<playField[rows].length; columns++){
                int columnPosition = columns*50;
                g.setColor(Color.BLACK);
                g.drawRect(30+columnPosition, 30+rowPosition, 50, 50);
                g.setColor(Color.decode("#F7F7F7"));
                g.fillRect(31+columnPosition, 31+rowPosition, 48, 48);
            }
        }
        //Add All Objects to GameField + passCode if possible
        for(int i = 1; i<objectArray.size(); i++){
            objectArray.get(i).initializeImages();
            objectArray.get(i).render(g);
            
            g.setColor(Color.black);
            if(objectArray.get(i).getObjectName().equals("Key") || objectArray.get(i).getObjectName().equals("Barricade")){
                if(objectArray.get(i).getObjectName().equals("Key")){
                    g.drawString(Integer.toString(objectArray.get(i).getPassCode()), objectArray.get(i).getX(), objectArray.get(i).getY()+40);
                }else{
                    g.drawString(Integer.toString(objectArray.get(i).getPassCode()), objectArray.get(i).getX()+7, objectArray.get(i).getY()+40);
                }
            }
        }
        objectArray.get(0).initializeImages();
        objectArray.get(0).render(g);
    }
    
    //KeyListener
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        
        if(keyCode == KeyEvent.VK_UP) {
            player.move(playField, objectArray, "UP");
        }
        
        if(keyCode == KeyEvent.VK_DOWN) {
            player.move(playField, objectArray, "DOWN");
        }

        if(keyCode == KeyEvent.VK_LEFT) {
            player.move(playField, objectArray, "LEFT");
        }
        
        if(keyCode == KeyEvent.VK_RIGHT) {
            player.move(playField, objectArray, "RIGHT");
        }
    }
    
    public void actionPerformed(ActionEvent e){
        repaint();
    }  
    
    public void keyReleased(KeyEvent e) {
        
    }
    
    public void keyTyped(KeyEvent e) {
        
    }
    
    public Key getPlayerInventory(){
        return player.getInventory();
    }
}
