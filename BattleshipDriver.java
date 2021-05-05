//This is the driver portion of the program, in this part of the program, the player is asked to setup their battleships and grenades.
// The program will detect if the coordinates that are input are invalid or already taken, and after the coordinates of the ships and grenades
// are set, the computer will set its ships and grenades. It is then up to the player and computer to shoot rockets on different places of the board
// until one of them gets all of their opponent's ships. That person is then declared the winner and the program is done.

import java.util.Scanner;

public class BattleshipDriver {

   public static void main(String[] args) {
       Battleship grid = new Battleship(); //sets the grid to a 8x8 blank grid (every spot has '_')
       Scanner user = new Scanner(System.in);
       System.out.println("Hi, let's play Battleship!");
       System.out.println();
       //setting users ships and grenades
       for (int s=0; s<6; s++) { //asks for ships 1 through 6
           System.out.print("Enter the coordinates of your ship #" + (s+1) + ": "); 
           String s_coordinate = user.next();
           s_coordinate.toLowerCase();
           boolean inside = grid.out(s_coordinate); // checking if the number they enter is out of the grid
           if (inside == false) {// grid.out returns true if its inside the grid, so false means its outside
               System.out.println("sorry, coordinates outside of the grid. try again.");
               s--;
               continue;
           } 
           else {// if its inside the grid and not too long, proceed 
               grid.isRow(s_coordinate); 
               grid.isColumn(s_coordinate); 
               boolean occupied = grid.occupied_spot();
               if (occupied == true) { //checks if location is taken already
                   System.out.println("sorry, coordinates already used. try again.");
                   s--;
                   continue;
               } 
               else {
                   grid.setShip(); //sets ship to coordinate on grid
               }
           }
       }
       System.out.println();
       for (int g=0; g<4; g++) { //asks for grenades 1 through 4
           System.out.print("Enter the coordinates of your grenade #" + (g+1) + ": ");
           String g_coordinate = user.next(); 
           g_coordinate.toLowerCase();
           boolean inside = grid.out(g_coordinate);
           if (inside == false) {
               System.out.println("sorry, coordinates outside of the grid. try again.");
               g--;
               continue; 
           }
           else { 
               grid.isRow(g_coordinate);
               grid.isColumn(g_coordinate);
               boolean occupied = grid.occupied_spot();
               if (occupied == true) {
                   System.out.println("sorry, coordinates already used. try again.");
                   g--;
                   continue;
               } else {
                   grid.setGrenade(); //sets grenade to coordinate on grid
               }
           }
       }
       System.out.println();
       //now setting computers ships and grenades
       for (int cs=0; cs<6; cs++) { //setting ships
           grid.randomRow();//selects random row
           grid.randomColumn();//selects random column
           //because of the random method, we know the coordinate is inside the grid we don't need to call 'out' method
           boolean occupied = grid.occupied_spot(); 
           if (occupied == true) {
        	   cs--;
               continue;
           } else {
               grid.setComputerShip();
           }
       }

       for (int cg=0; cg<6; cg++) { //setting grenades
           grid.randomRow();
           grid.randomColumn();
           boolean occupied = grid.occupied_spot();
           if (occupied == true) {
        	   cg--;
               continue;
           } 
           else {
               grid.setComputerGrenade();
           }
       }
       Battleship filled_grid = new Battleship(grid); //copy current grid with ships and grenades
       grid.reset_grid(); //reset initial grid for grid filled with '_'
       System.out.println();
       System.out.println("OK, the computer placed its ships and grenades at random. Let's play.");
       System.out.println();
       for (int turn=0; turn < 100; turn++) { //The number that the turns go to doesn't matter as the system will 
    	   //exit when a winner is determined
           if (turn%2<1) {
    	   System.out.print("position of your rocket: "); //asks for rocket position
           String rocket_coordinate = user.next();
           boolean inside = grid.out(rocket_coordinate); //checks if rocket is inside grid
           if (inside == false) {
               System.out.println("sorry, coordinates outside of the grid. try again.");
               turn--;
               continue;
           } 
           else { //set the coordinates 
               grid.isRow(rocket_coordinate);
               grid.isColumn(rocket_coordinate);
               boolean took = grid.taken(); // checking if the user already shot a rocket there
               if (took == true) {
                   System.out.println("position already called.");
                   grid.board();
                   turn--;
                   continue;
               } 
               else { // otherwise, launch a rocket
                   grid.rocket(filled_grid);
                   boolean hit_grenade = grid.hitGrenade(filled_grid);
                   if(hit_grenade == true) {
                       grid.randomTurn(filled_grid);//extra turn from computer
                       grid.rocket(filled_grid);
                   }
               }
               }
           }
           if (turn%2>0) {
               grid.randomTurn(filled_grid); // now its the computer's turn
               grid.rocket(filled_grid);
               boolean computerhit_grenade = grid.hitGrenade(filled_grid);
               if (computerhit_grenade == true) { // if the computer hits a grenade, its turn must be skipped, player
            	   //enters two coordinates, this will make the player have two turns in a row.
                   turn=-2;
                   }
               }
           }
           } 
       }
   
   
