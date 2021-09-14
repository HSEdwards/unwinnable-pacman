/*
 * Create board and board display for a pacman game 
 */
package pacmanish;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.*;
import java.lang.*;
import java.util.Set;
import javax.imageio.ImageIO;

/**
 *
 * @author hsedw
 */
public class Board extends JFrame{
    LittleGuy pacman = new LittleGuy();
    Ghosts inky, blinky, pinky, clyde;
    //set the board information
    final int w = 0;   //wall
    final int f = 1;    //food
    final int e = 2;    //empty space
    final int k = -2;   //ghost
    final int b = -3;   //ghost
    final int py = -4;   //ghost
    final int c = -5;   //ghost
    final int p = 3;    //pacman
    private int[][] boardInfo = {
        {w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w},
        {w,f,f,f,f,f,f,f,f,w,f,f,f,f,f,f,f,f,w},
        {w,f,w,w,f,w,w,w,f,w,f,w,w,w,f,w,w,f,w},
        {w,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,w},
        {w,f,w,w,f,w,f,w,w,w,w,w,f,w,f,w,w,f,w},
        {w,f,f,f,f,w,f,f,f,w,f,f,f,w,f,f,f,f,w},
        {w,w,w,w,f,w,w,w,e,w,e,w,w,w,f,w,w,w,w},
        {w,w,w,w,f,w,e,e,k,e,e,e,b,w,f,w,w,w,w},
        {w,w,w,w,f,w,e,w,w,w,w,w,e,w,f,w,w,w,w},
        {w,e,e,e,f,e,e,w,e,e,e,w,e,e,f,e,e,e,w},
        {w,w,w,w,f,w,py,w,w,w,w,w,e,w,f,w,w,w,w},
        {w,w,w,w,f,w,e,e,e,e,e,e,c,w,f,w,w,w,w},
        {w,w,w,w,f,w,e,w,w,w,w,w,e,w,f,w,w,w,w},
        {w,f,f,f,f,f,f,f,f,w,f,f,f,f,f,f,f,f,w},
        {w,f,w,w,f,w,w,w,f,w,f,w,w,w,f,w,w,f,w},
        {w,f,f,w,f,f,f,f,f,p,f,f,f,f,f,w,f,f,w},
        {w,w,f,w,f,w,f,w,w,w,w,w,f,w,f,w,f,w,w},
        {w,f,f,f,f,w,f,f,f,w,f,f,f,w,f,f,f,f,w},
        {w,f,w,w,w,w,w,w,f,w,f,w,w,w,w,w,w,f,w},
        {w,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,w},
        {w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w}            
    };
    
    //the frame
    private JPanel backing, buttonSpace;
    private JButton up, down, left, right;
    
    public Board(){ 
        //initilize ghosts
        inky = new Ghosts(8,7, -2, "Blue"); 
        blinky = new Ghosts(12,7, -3, "Orange");
        pinky = new Ghosts(6,10, -4, "Pink");
        clyde = new Ghosts(12,11, -5, "Red");
        createBoard();        
    }   
    
    //build board GUI
    private void createBoard(){
        Container contentPane = getContentPane();    
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setLayout(null);

        
        //setup game field pannel
        backing = new JPanel();
        backing.setLayout(null);
        backing.setBorder(new BevelBorder(BevelBorder.LOWERED));
        backing.setBounds(10, 20, 856, 950);
        backing.setBackground(Color.DARK_GRAY);
        contentPane.add(backing);  
        
        int fromLeft, fromTop;        
        //add cubes
        for (int i = 0; i < boardInfo.length; i++) {
            for (int j = 0; j < boardInfo[i].length; j++){
                fromTop =(i*45) + 1;
                fromLeft =(j*45) + 1;
                
                JPanel space = new JPanel();
                space.setBounds(fromLeft, fromTop, 45, 45);        
                space.setName(i+","+j);     //name so updating/finding it later is eaiser
                space.setBackground(Color.black);
                
                //fill in board based on array
                switch(boardInfo[i][j]){
                    case w:     //wall
                        space.setBackground(Color.blue);
                        break;
                        
                    case f:     //food
                        try{
                            Image food = ImageIO.read(new File("C:\\Users\\hsedw\\Pictures\\pacmanImg\\food.png"));
                            food = food.getScaledInstance(45, 45, Image.SCALE_DEFAULT);
                            JLabel label = new JLabel(new ImageIcon(food));
                            space.add(label);
                        }catch(IOException e){
                            e.printStackTrace();
                        } 
                        break;
                    case k:
                        try{
                            Image food = ImageIO.read(new File("C:\\Users\\hsedw\\Pictures\\pacmanImg\\BlueRight.png"));
                            food = food.getScaledInstance(45, 45, Image.SCALE_DEFAULT);
                            JLabel label = new JLabel(new ImageIcon(food));
                            space.add(label);
                        }catch(IOException e){
                            e.printStackTrace();
                        } 
                        break;
                    case b:
                        try{
                            Image food = ImageIO.read(new File("C:\\Users\\hsedw\\Pictures\\pacmanImg\\OrangeRight.png"));
                            food = food.getScaledInstance(45, 45, Image.SCALE_DEFAULT);
                            JLabel label = new JLabel(new ImageIcon(food));
                            space.add(label);
                        }catch(IOException e){
                            e.printStackTrace();
                        } 
                        break;
                    case py:
                        try{
                            Image food = ImageIO.read(new File("C:\\Users\\hsedw\\Pictures\\pacmanImg\\PinkRight.png"));
                            food = food.getScaledInstance(45, 45, Image.SCALE_DEFAULT);
                            JLabel label = new JLabel(new ImageIcon(food));
                            space.add(label);
                        }catch(IOException e){
                            e.printStackTrace();
                        } 
                        break;
                    case c:                    
                        try{
                            Image food = ImageIO.read(new File("C:\\Users\\hsedw\\Pictures\\pacmanImg\\RedLeft.png"));
                            food = food.getScaledInstance(45, 45, Image.SCALE_DEFAULT);
                            JLabel label = new JLabel(new ImageIcon(food));
                            space.add(label);
                        }catch(IOException e){
                            e.printStackTrace();
                        } 
                        break;
                    case p:     //packman
                        try{
                            Image pac = ImageIO.read(new File("C:\\Users\\hsedw\\Pictures\\pacmanImg\\man.png"));
                            pac = pac.getScaledInstance(45, 45, Image.SCALE_DEFAULT);
                            JLabel label = new JLabel(new ImageIcon(pac));
                            space.add(label);
                        }catch(IOException e){
                            e.printStackTrace();
                        } 
                        break;
                }            
                
                backing.add(space);                
            }
        }
        
        //set up buttons
        //set up button pannel
        //setup game field pannel
        buttonSpace = new JPanel();
        buttonSpace.setLayout(null);
        buttonSpace.setBorder(new BevelBorder(BevelBorder.LOWERED));
        buttonSpace.setBounds((45*20), 500, 270, 130);
        buttonSpace.setBackground(Color.DARK_GRAY);
        contentPane.add(buttonSpace);  
        
        //setup directional buttons
        up = new JButton();
        up.setBounds(92, 5, 85, 60);
        up.setFont(new Font("SansSerif", Font.PLAIN, 18));
        up.setText("^");
        up.setBackground(Color.yellow);
        buttonSpace.add(up);
        up.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                direction('u');
            }
        });
        
        down = new JButton();
        down.setBounds(92, 66, 85, 60);
        down.setFont(new Font("SansSerif", Font.PLAIN, 18));
        down.setText("v");
        down.setBackground(Color.yellow);
        buttonSpace.add(down);
        down.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                direction('d');
            }
        });
        
        
        left = new JButton();
        left.setBounds(6, 66, 85, 60);
        left.setFont(new Font("SansSerif", Font.PLAIN, 18));
        left.setText("<");
        left.setBackground(Color.yellow);
        buttonSpace.add(left);
        left.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                direction('l');
            }
        });
        
        right = new JButton();
        right.setBounds(178, 66, 85, 60);
        right.setFont(new Font("SansSerif", Font.PLAIN, 18));
        right.setText(">");
        right.setBackground(Color.yellow);
        buttonSpace.add(right);
        right.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                direction('r');
            }
        });
        
        //application window properties
        setTitle("Pacman-ish");
        setSize(1200, 1030);
        setVisible(true);

    }
    
    void direction(char d){        
        int x = pacman.getX();
        int y = pacman.getY();
        
        switch(d){
            case 'u': 
                y -= 1;
                break;
            case 'd':
                y += 1;
                break;
            case 'l':
                x -= 1;
                break;
            case 'r':
                x += 1;
                break;
            default:                
        }
        
        
        //check to see if there is no wall to move there
        if(boardInfo[y][x] != w){            
            //movepac man to new location on board
            boardInfo[y][x] = p;
            
            //set pacman's previous location to empty
            boardInfo[pacman.getY()][pacman.getX()] = e;
            
            //set pacman's new coordinates
            pacman.setX(x);
            pacman.setY(y); 
            
            //cacllulate route for the ghosts and move them
            moveGhosts(inky);
            moveGhosts(blinky);
            moveGhosts(pinky);
            moveGhosts(clyde);
            
            //update board
            updateGraphic();
        }
        
    }
    
    //in hidsight this should be in the ghost class
    void moveGhosts(Ghosts gho){
        //calculate new best paths and get next direction
        char i = gho.calculatePath(boardInfo);
        System.out.println(i);
        
        
        //if the ghost is on food, place the food back
        if(gho.onFood()){
            boardInfo[gho.getY()][gho.getX()] = f;
        }
        else
            boardInfo[gho.getY()][gho.getX()] = e;
        
        //check to see if they are going to run over food
        switch(i){
            case 'u': 
                //if they are going to run over food, update it
                if(boardInfo[gho.getY() -1][gho.getX()] == f)
                    gho.setOnFood(true);
                else
                    gho.setOnFood(false);
                
                //update the ghost in object
                gho.setY(gho.getY()-1);
                
                //move ghost on board
                boardInfo[gho.getY()][gho.getX()] = gho.getNum();
                break;
            case 'd':
                if(boardInfo[gho.getY() +1][gho.getX()] == f)
                    gho.setOnFood(true);
                else
                    gho.setOnFood(false);
                
                gho.setY(gho.getY()+1);
                boardInfo[gho.getY()][gho.getX()] = gho.getNum();
                break;
            case 'l':
                if(boardInfo[gho.getY()][gho.getX()-1] == f)
                    gho.setOnFood(true);
                else
                    gho.setOnFood(false);
                
                gho.setX(gho.getX()-1);
                boardInfo[gho.getY()][gho.getX()] = gho.getNum();
                break;
            case 'r':
                if(boardInfo[gho.getY()][gho.getX()+1] == f)
                    gho.setOnFood(true);
                else
                    gho.setOnFood(false);
                
                gho.setX(gho.getX()+1);
                boardInfo[gho.getY()][gho.getX()] = gho.getNum();
                break;
            default:         
        }       
    }   
    
    
   //reload the board
   void updateGraphic(){
       backing.removeAll();       
       
       int fromLeft, fromTop; 
       for (int i = 0; i < boardInfo.length; i++) {
            for (int j = 0; j < boardInfo[i].length; j++){
                fromTop =(i*45) + 1;
                fromLeft =(j*45) + 1;
                
                JPanel space = new JPanel();
                space.setBounds(fromLeft, fromTop, 45, 45);        
                space.setName(i+","+j);     //name so updating/finding it later is eaiser
                space.setBackground(Color.black);
                
                switch(boardInfo[i][j]){
                    case w:     //wall
                        space.setBackground(Color.blue);
                        break;
                        
                    case f:     //food
                        try{
                            Image food = ImageIO.read(new File("C:\\Users\\hsedw\\Pictures\\pacmanImg\\food.png"));
                            food = food.getScaledInstance(45, 45, Image.SCALE_DEFAULT);
                            JLabel label = new JLabel(new ImageIcon(food));
                            space.add(label);
                        }catch(IOException e){
                            e.printStackTrace();
                        } 
                        break;
                    case k:
                        try{
                            Image food = ImageIO.read(new File("C:\\Users\\hsedw\\Pictures\\pacmanImg\\BlueRight.png"));
                            food = food.getScaledInstance(45, 45, Image.SCALE_DEFAULT);
                            JLabel label = new JLabel(new ImageIcon(food));
                            space.add(label);
                        }catch(IOException e){
                            e.printStackTrace();
                        } 
                        break;
                    case b:
                        try{
                            Image food = ImageIO.read(new File("C:\\Users\\hsedw\\Pictures\\pacmanImg\\OrangeRight.png"));
                            food = food.getScaledInstance(45, 45, Image.SCALE_DEFAULT);
                            JLabel label = new JLabel(new ImageIcon(food));
                            space.add(label);
                        }catch(IOException e){
                            e.printStackTrace();
                        } 
                        break;
                    case py:
                        try{
                            Image food = ImageIO.read(new File("C:\\Users\\hsedw\\Pictures\\pacmanImg\\PinkRight.png"));
                            food = food.getScaledInstance(45, 45, Image.SCALE_DEFAULT);
                            JLabel label = new JLabel(new ImageIcon(food));
                            space.add(label);
                        }catch(IOException e){
                            e.printStackTrace();
                        } 
                        break;
                    case c:                    
                        try{
                            Image food = ImageIO.read(new File("C:\\Users\\hsedw\\Pictures\\pacmanImg\\RedLeft.png"));
                            food = food.getScaledInstance(45, 45, Image.SCALE_DEFAULT);
                            JLabel label = new JLabel(new ImageIcon(food));
                            space.add(label);
                        }catch(IOException e){
                            e.printStackTrace();
                        } 
                        break;
                    case p:     //pacman
                        try{
                            Image pac = ImageIO.read(new File("C:\\Users\\hsedw\\Pictures\\pacmanImg\\man.png"));
                            pac = pac.getScaledInstance(45, 45, Image.SCALE_DEFAULT);
                            JLabel label = new JLabel(new ImageIcon(pac));
                            space.add(label);
                        }catch(IOException e){
                            e.printStackTrace();
                        } 
                        break;
                    case e:
                        space.setBackground(Color.black);
                        
                }              
                backing.add(space);                
            }
        } 
        backing.repaint();
        backing.revalidate();
    }
}
