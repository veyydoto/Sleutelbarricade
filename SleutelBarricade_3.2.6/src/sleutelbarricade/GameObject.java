package sleutelbarricade;

import java.awt.Graphics;

/**
 *
 * @author Tom Spek, Colin Werkhoven, Vedat Yilmaz
 */
public abstract class GameObject {
    
    protected String objectName;
    protected int x;
    protected int y;
    protected int passCode;
    
    public GameObject(String objectName, int x, int y){
        setObjectName(objectName);
        setX(x);
        setY(y);
    }
    
    public GameObject(String objectName, int x, int y, int passCode){
        setObjectName(objectName);
        setX(x);
        setY(y);
        setPassCode(passCode);
    }
    
    public abstract void initializeImages();
    
    public abstract void render(Graphics g);

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPassCode() {
        return passCode;
    }

    public void setPassCode(int passCode) {
        this.passCode = passCode;
    } 
}
