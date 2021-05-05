//This is the battleship program for assignment 4, made by me (Patrick Keenan). This assignment is being submitted on December 6th 2020. 
// The purpose of this program was to have a battleship game where the user is the player and they are versus a computer. The game is 
// set on an 8x8 'board' where both the computer and the player have 6 ships and 4 grenades each. Each spot on the board is filled with '_',
// and the spots are slowly revealed as rockets are shot by the player and computer around the board. The console will inform the player and computer
// if their rocket misses, hits a ship, or hits a grenade. The player's ships and grenades are represented by 's' and 'g', and the computer's
// ships and grenades are represented by 'S' and 'G'. If a grenade is hit, the person who hit the grenade has their next turn skipped. Once
// one side gets all six ships of their opponent, they win the game. This section contains the different methods used in the program. Every method 
// has comments so that the purpose of the method is clear.


	public class Battleship {
	   private char grid[][] = new char[8][8]; //8x8 array
	   private int row;
	   private int column;
	   private int player;
	   private int computer;

	   public Battleship() { //blank grid
	       row = 0;
	       column = 0;
	       for (int i = 0; i < grid.length; i++) {
	           for (int j = 0; j < grid[i].length; j++) {
	               grid[i][j] = '_';
	           }
	       }
	   }

	   public Battleship(Battleship filled_grid) {//grid that is updated with grenade and ship spots
	       for (int i = 0; i < grid.length; i++) {
	           for (int j = 0; j < grid[i].length; j++) {
	               this.grid[i][j] = filled_grid.grid[i][j];
	           }
	       }
	   }

	   public int isRow(String coordinate) {//sets the letter to its row
	       switch (coordinate.charAt(0)) {
	       case 'A':
	       case 'a':
	           row = 0;
	           break;
	       case 'B':
	       case 'b':
	           row = 1;
	           break;
	       case 'C':
	       case 'c':
	           row = 2;
	           break;
	       case 'D':
	       case 'd':
	           row = 3;
	           break;
	       case 'E':
	       case 'e':
	           row = 4;
	           break;
	       case 'F':
	       case 'f':
	           row = 5;
	           break;
	       case 'G':
	       case 'g':
	           row = 6;
	           break;
	       case 'H':
	       case 'h':
	           row = 7;
	           break;
	       }
	       return (row);
	   }

	   public int isColumn(String coordinate) {//sets the number to its column
	       switch (coordinate.charAt(1)) {
	       case '1':
	           column = 0;
	           break;
	       case '2':
	           column = 1;
	           break;
	       case '3':
	           column = 2;
	           break;
	       case '4':
	           column = 3;
	           break;
	       case '5':
	           column = 4;
	           break;
	       case '6':
	           column = 5;
	           break;
	       case '7':
	           column = 6;
	           break;
	       case '8':
	           column = 7;
	           break;
	       }
	       return (column);
	   }

	   public void setShip() {//setter for player's ships, uses lower case s
	       grid[this.column][this.row] = 's';
	   }

	   public void setComputerShip() {//setter for computer's ships, uses upper case S
	       grid[this.column][this.row] = 'S';
	   }

	   public void setGrenade() {//setter for player's grenades, uses lower case g
	       grid[this.column][this.row] = 'g';
	   }

	   public void setComputerGrenade() {//setter for computer's grenades, uses upper case G
	       grid[this.column][this.row] = 'G';
	   }

	   public boolean out(String coordinate) {//returns a true value if the coordinate is inside the grid
	       char row_char = coordinate.charAt(0);
	       char column_char = coordinate.charAt(1);
	       return (row_char >= 'A' && row_char <= 'H' && column_char >= '1' && column_char < '9');

	   }

	   public boolean occupied_spot() {//returns true if there is already a ship or grenade in the coordinate
	       return (grid[this.column][this.row] == 'G' || grid[this.column][this.row] == 'S' 
	    		   || grid[this.column][this.row] == 'g'|| grid[this.column][this.row] == 's');
	   }
	   
	   public void reset_grid() {//sets grid to '_' in every coordinate
	       for (int i = 0; i < grid.length; i++) {
	           for (int j = 0; j < grid[i].length; j++) {
	               grid[i][j] = '_';
	           }
	       }
	   }
	   public int randomRow() {//generates random row #
	       this.row = (int) (Math.random() * (7));
	       return row;
	   }

	   public int randomColumn() {//generates random column #
	       this.column = (int) (Math.random() * (7));
	       return column;
	   }

	   public void randomTurn(Battleship filled_grid) {//computers turn, uses random row and column
	       randomRow();
	       randomColumn();
	       boolean took = taken();
	       if (took == true) {
	           randomTurn(filled_grid); //check to see if coordinate selected has already been hit
	           return;
	       }
	       if (filled_grid.grid[this.column][this.row] == 'S' || filled_grid.grid[this.column][this.row] == 'G') {
	           randomRow(); //makes sure the coordinate chosen is not one of the computer's boats or ships.
	           randomColumn();
	       }
	       switch (this.row) { //states the coordinate of the computer's rocket, up until the letter character of coordinate
	       case 0: {
	           System.out.print("position of my rocket: A");
	           break;
	       }
	       case 1: {
	           System.out.print("position of my rocket: B");
	           break;
	       }
	       case 2: {
	           System.out.print("position of my rocket: C");
	           break;
	       }
	       case 3: {
	           System.out.print("position of my rocket: D");
	           break;
	       }
	       case 4: {
	           System.out.print("position of my rocket: E");
	           break;
	       }
	       case 5: {
	           System.out.print("position of my rocket: F");
	           break;
	       }
	       case 6: {
	           System.out.print("position of my rocket: G");
	           break;
	       }
	       case 7: {
	           System.out.print("position of my rocket: H");
	           break;
	       }
	       }

	       switch (this.column) {//writes the number character of the coordinate
	       case 0: {
	           System.out.println("1");
	           break;
	       }
	       case 1: {
	           System.out.println("2");
	           break;
	       }
	       case 2: {
	           System.out.println("3");
	           break;
	       }
	       case 3: {
	           System.out.println("4");
	           break;
	       }
	       case 4: {
	           System.out.println("5");
	           break;
	       }
	       case 5: {
	           System.out.println("6");
	           break;
	       }
	       case 6: {
	           System.out.println("7");
	           break;
	       }
	       case 7: {
	           System.out.println("8");
	           break;
	       }
	       }

	   }


	   public void board() {//first board
	       for (int i = 0; i < grid.length; i++) {
	           for (int j = 0; j < grid[i].length; j++) {
	               System.out.print(grid[i][j] + " ");
	           }
	           System.out.println();
	       }
	   }

	   public void board(Battleship live_grid) {//active board, that updates grid with spots hit on filled_grid
	       for (int i = 0; i < grid.length; i++) {
	           for (int j = 0; j < live_grid.grid[i].length; j++) {
	               System.out.print(live_grid.grid[i][j] + " ");
	           }
	           System.out.println();
	       }
	   }

	   public boolean taken() { //returns true if the spot on the grid is taken by a grenade, ship, or already been shot at by a rocket
	       return (grid[this.column][this.row] == '*' || grid[this.column][this.row] == 'G' 
	    		   || grid[this.column][this.row] == 'g'|| grid[this.column][this.row] == 'S' 
	    		   || grid[this.column][this.row] == 's');
	   }

	   public void rocket(Battleship live_grid) {//checks if rocket is hitting a blank or ship, and let's player know the result
	       if (live_grid.grid[this.column][this.row] == '_') {//checks if the rocket hit a blank
	           System.out.println("nothing.");
	           grid[this.column][this.row] = '*'; //marks an asterisk in the spot where the rocket missed
	           board();
	       } else if (live_grid.grid[this.column][this.row] == 'S' || live_grid.grid[this.column][this.row] == 's') {
	           System.out.println("ship hit."); //checks if rocket hit a ship
	           this.grid[this.column][this.row] = live_grid.grid[this.column][this.row];
	           if (live_grid.grid[this.column][this.row] == 'S') {//checks if computer ship is hit
	               this.player++; //adds a point to player 1
	               if (this.player == 6) { //if the player has 6 points they won, so this checks that and exits if true
	                   System.out.println("You win!");
	                   board(live_grid);
	                   System.exit(0);
	               }

	           } else if (live_grid.grid[this.column][this.row] == 's') {//checks if player ship is hit
	               this.computer++; //adds a point to computer
	               if (this.computer == 6) {//if the computer has 6 points, the player lost
	                   System.out.println("You lose!");
	                   board(live_grid);
	                   System.exit(0);
	               }
	           }
	           board();
	       }

	       else if (live_grid.grid[this.column][this.row] == 'G' || live_grid.grid[this.column][this.row] == 'g') {
	           System.out.println("boom! grenade."); //checks if the rocket hit a grenade
	           this.grid[this.column][this.row] = live_grid.grid[this.column][this.row];
	           board();
	       }
	   }


	   public boolean hitGrenade(Battleship filled_grid) {//checks if rocket hit grenade, only used for boolean value
	       return (filled_grid.grid[this.column][this.row] == 'G' || filled_grid.grid[this.column][this.row] == 'g');

	   }
	}


