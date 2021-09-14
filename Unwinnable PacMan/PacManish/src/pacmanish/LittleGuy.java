/*
 * Holds all pacman information
 */
package pacmanish;

/**
 *
 * @author hsedw
 */
public class LittleGuy {
    //initilize co-ordinates
    private int locationX, locationY;
    
    //constructor to set starting location
    LittleGuy(){
        locationX = 9;
        locationY = 15;
    }
    
    //gets and sets to move pacman
    int getX(){
        return locationX;
    }
    
    int getY(){
        return locationY;
    }
    
    void setX(int x){
        locationX = x;
    }
    
    void setY(int y){
        locationY = y;
    }
}
