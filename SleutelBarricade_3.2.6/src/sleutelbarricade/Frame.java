package sleutelbarricade;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Tom Spek, Colin Werkhoven, Vedat Yilmaz
 */
public class Frame extends JFrame {
    
    private Graphics g;
    private static SleutelBarricade sleutelBarricade = new SleutelBarricade();
    private final String invIcon_IMG_PATH = "src/images/Key.jpeg";
    private static JLabel inventory;
    
    public Frame(String title, int width, int height){           
        this.setTitle(title);
        
        //Set the sizes of the frame        
        this.setPreferredSize(new Dimension(width, height));
        this.setMinimumSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));
        
        //Add control buttons and other graphics to the field
        ImageIcon invIcon = new ImageIcon(invIcon_IMG_PATH);
        inventory = new JLabel(invIcon, sleutelBarricade.getPlayerInventory().getPassCode());
        inventory.setText(Integer.toString(sleutelBarricade.getPlayerInventory().getPassCode()));
        inventory.setBounds(600,31,200,100);
        inventory.setBorder(new TitledBorder("Inventory"));
        this.add(inventory);
        
        //Add Random Button
        JButton newLevel = new JButton("New Level!");
        newLevel.setBounds(600, 181, 200,40);
        newLevel.setEnabled(true);
        this.add(newLevel);
        newLevel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                sleutelBarricade.randomizeField();
                newLevel.setFocusable(false);
            }
        });
        
        //Add playField 2D array of Objects
        sleutelBarricade.setPreferredSize(new Dimension(width, height));
        sleutelBarricade.randomizeField();
        this.add(sleutelBarricade);
       
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        Frame gameFrame = new Frame("SleutelBarricade", 900, 700);
        while(gameFrame.isVisible()){
            inventory.setText(Integer.toString(sleutelBarricade.getPlayerInventory().getPassCode()));
        }
    }
}
