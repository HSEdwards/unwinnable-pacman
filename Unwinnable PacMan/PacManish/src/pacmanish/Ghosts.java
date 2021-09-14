package pacmanish;

import java.util.*;

/**
 *
 * @author hsedw
 */
public class Ghosts {
    //initilize varaibles
    int locationX, locationY;
    boolean onFood = false;
    int num;    //which ghost are they
    String name;
    
    //initilize thier starting information
    public Ghosts(int x, int y, int n, String nm){
        locationX = x;
        locationY = y;
        num = n;
        name = nm;
    }
    
    int getNum(){
        return num;
    }
            
    //gets and sets for location
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
    
    //is on food currently
    //when they move we know to re-load food in that square
    boolean onFood(){
        return onFood;
    }    
    void setOnFood(boolean f){
        onFood = f;
    }
    
    
    
    
    //A* path finder
    char calculatePath(int[][] board){
        //find end node (where pacman is)
        int finalX = 0;
        int finalY = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                //if it's pacman
                if(board[i][j] == 3){
                    finalX = j;
                    finalY = i;
                }
            }
        }
        
        //create open and closed lists (been to and not been to)     
        List<Node> open = new ArrayList<>();
        List<Node> closed = new ArrayList<>();
        
        //set starting node
        Node startNode = new Node(locationX, locationY);
        
        //caclulate f cost
        //g (node to start) + h (node to end), here g is going to be 0 so calculate h only
        double h = calculateDistance(startNode.getX(), startNode.getY(), finalX,finalY);
        startNode.setG(0);
        startNode.setH(h);
        startNode.setF();
        startNode.setParent(null);
        
        //add start node to open
        open.add(startNode);
        
        //loop
        boolean run = true;
        while(run){            
            //find node in open list with lowest final cost
            int smallest = 0;
            for (int i = 0; i < open.size(); i++) {
                if(open.get(i).getF() < open.get(smallest).getF())
                    smallest = i;
                
                else if(open.get(i).getF() == open.get(smallest).getF())
                    //compare h cost, if h is lower, change them
                    if(open.get(i).getH() < open.get(smallest).getH())
                        smallest = i; 
                
            }
                
            //set current node
            Node current = open.get(smallest);
            
            //move current node to closed
            closed.add(current);
            open.remove(smallest);
            
            //check to see if current node is where we end
            if(current.getX() == finalX && current.getY() == finalY){                
                run = false;                
            }
            
            //else check all neighbors of current node
            else{
                //coordinates for up, right, left and down
                int[] holdx = { 0,  1, 0, -1};
                int[] holdy = {-1, 0,  1,  0};

                //for each neighbor of current
                for (int i = 0; i < holdx.length; i++) {
                    //calculate the neighbor's coordinates
                    int neighborX = current.getX() + holdx[i];
                    int neighborY = current.getY() + holdy[i]; 
                    
                    //if the neighbor is a wall or has already been checked
                    if(board[neighborY][neighborX] == 0 || 
                            inList(closed, neighborX, neighborY)){
                        //nothing
                    }

                    //if not in open then do this
                    else if(!inList(open, neighborX, neighborY)
                            && board[neighborY][neighborX] != 0){
                        
                        //set f cost of neighbor
                        Node neighbor = new Node(neighborX, neighborY);
                        //find g (distance from start)
                        neighbor.setG(calculateDistance(neighborX, neighborY, locationX, locationY));
                        //find h (distance from end)
                        neighbor.setH(calculateDistance(neighborX, neighborY, finalX,finalY));
                        //set f
                        neighbor.setF();
                        
                        //set the parent of the neighbor to current
                        neighbor.setParent(current);

                        //add neighbor to open list if not there
                        if(!inList(open, neighbor.getX(), neighbor.getY())){                            
                            neighbor.printCoords();
                            open.add(neighbor);                            
                        }
                    }
                } 
            }            
        }
        
        //see all coords
//        for (int i = closed.size()-1; i >= 0; i--) {
//            closed.get(i).printCoords();
//        }
        
        
        
        //trace back parent
        Node n = closed.get(closed.size()-1);
        n.printCoords();
        //get the node after the parent/start node
        System.out.println("The path for " + name);
        while(n.getParent().getParent() != null){
            n = n.getParent();
            System.out.println(n.printCoords());
        }
        
        //get direction of movement
        char d = getDirection(n);   
        
        //find next move and return it
        return d;
    }    
    
    //starting square x and y, ending square x and y
    double calculateDistance(int sx, int sy, int ex, int ey){
        int xlength, ylength;
        if(sx < ex)
            xlength = sx - ex;        
        else
            xlength = ex - sx;
        
        if(sy < ey)
            ylength = sy - ey;
        else
            ylength = ey-sy;
        
        //calculate distance
        return Math.sqrt(Math.pow(xlength, 2) + Math.pow(ylength, 2));
    
    }
    
    char getDirection(Node n){        
        int ex = n.getX();
        int ey = n.getY();
        
        if(locationX > ex)
            return 'l';
        else if(locationX < ex)
            return 'r';
        else if(locationY > ey)
            return 'u';
        else //if(sy < ey)
            return 'd';
    }
    
    //checks to see if something in a list
    boolean inList(List<Node> list, int x, int y){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getX() == x && list.get(i).getY() == y){
                return true;
            }
        }
        return false;
    }
    
    
    
}
