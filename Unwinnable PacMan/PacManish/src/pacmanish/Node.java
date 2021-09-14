/*
 * This class creates the nodes for the A* algorithm to work
 */
package pacmanish;

/**
 *
 * @author hsedw
 */
public class Node {
    //initilize node inofrmation
    private Node parent;
    private int x, y;   //co-ordinates
    //textbook variable naming...
    private double g, h, f;
    
    Node(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    //get x and y
    int getX(){
        return x;
    }
    
    int getY(){
        return y;
    }
    
    //get and set f - cost
    void setF(){
        f = g + h;
    }
    
    double getF(){
        return f;
    }
    
    //get/set end node h
    void setH(double h){
        this.h = h;
    }
    double getH(){
        return h;
    }    
    
    //get/set for start node g
    void setG(double g){
        this.g = g;
    }
    double getG(){
        return g;
    }
    
    
    //set parent
    void setParent(Node p){
        parent = p;
    }
    
    //get parent
    Node getParent(){
        return parent;
    }
    
    String printCoords(){
        return x +", " + y;
    }
    
    
    
}
