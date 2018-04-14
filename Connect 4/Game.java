

import java.io.*;

public class Game {
	
	public static int play(InputStreamReader input){
		BufferedReader keyboard = new BufferedReader(input);
		Configuration c = new Configuration();
		int columnPlayed = 3; int player;
		
		// first move for player 1 (played by computer) : in the middle of the grid
		c.addDisk(firstMovePlayer1(), 1);
		int nbTurn = 1;
		
		while (nbTurn < 42){ // maximum of turns allowed by the size of the grid
			player = nbTurn %2 + 1;
			if (player == 2){
				columnPlayed = getNextMove(keyboard, c, 2);
			}
			if (player == 1){
				columnPlayed = movePlayer1(columnPlayed, c);
			}
			System.out.println(columnPlayed);
			c.addDisk(columnPlayed, player);
			if (c.isWinning(columnPlayed, player)){
				c.print();
				System.out.println("Congrats to player " + player + " !");
				return(player);
			}
			nbTurn++;
		}
		return -1;
	}
	
	public static int getNextMove(BufferedReader keyboard, Configuration c, int player){
		
		//Using a try catch block to account for possible exceptions
		try{
		//Letting the user know that AI has made its move, printing out the board, and prompting the user to enter their move
		System.out.println("AI made its move:");
		c.print();
		System.out.println("It's your turn Player 2. Please enter a column");
		//Storing the user input into a variable
		String move= keyboard.readLine();
		//If the user input is not an String between 0 and 6(inclusive), continue asking for a new input
		while(!move.equals("0") && !move.equals("1") && !move.equals("2") && !move.equals("3") && !move.equals("4") && !move.equals("5") && !move.equals("6")) {
			System.out.println("Please enter an integer between (and including) 0 and 6");
			move = keyboard.readLine();
		}
		//Once the input string is an existing column, check if the column is available, if not, ask for a new input
		while(c.available[Integer.parseInt(move)]>5) {
			System.out.println("This column is full, please enter another column");
			move = keyboard.readLine();
			//Ensuring that the new input is an existing column
			while(!move.equals("0") && !move.equals("1") && !move.equals("2") && !move.equals("3") && !move.equals("4") && !move.equals("5") && !move.equals("6")) {
				System.out.println("Please enter an integer between (and including) 0 and 6");
				move = keyboard.readLine();
			}
		}
		//converting the user input to an int
		int moveColumn = Integer.parseInt(move);
		
		//returning the column where the disk is to be added
		return moveColumn;
		
		} catch(IOException e) {//catch any exceptions and returning -1
			System.out.println("There's been an error");
			return -1;
		}
		 
	}
	
	public static int firstMovePlayer1 (){
		return 3;
	}
	
	public static int movePlayer1 (int columnPlayed2, Configuration c){
		
		//Using variables to store the columns where the player1 could win in for the next round and for two turns
		int column1 = c.canWinNextRound(1);
		int column2 = c.canWinTwoTurns(1);
		if(column1!=-1) {//if this column is not -1(i.e. there is a column to win next), return that column
			return column1;
		} else if(column2!=-1) {//if this column is not -1(i.e. there is a column where player 1 can win in two turns), return that column
			return column2;
		} else { //if none of the above are possible
			if(c.available[columnPlayed2]<6) { //if possible, player1 adds to the same column as player 2
				return columnPlayed2; //return that column
				
			} else { //the last column played by player 2 is full
				
				for(int i=1; i<7; i++) { //Starting at 1, running through the possible increments(decrements)
					if(columnPlayed2-i>=0 && c.available[columnPlayed2-i]<6) { //if adding at columnPlayed2-i is possible, returning that column
						return columnPlayed2-i;
					} 
					if(columnPlayed2+i<=6 && c.available[columnPlayed2+i]<6) { //if adding at columnPlayed2+i is possible, returning that column
						return columnPlayed2+i;
					}
				}
			}
		}
		//returning -1 if there is no where on the board to add a disk
		return -1;
	}
	
}
