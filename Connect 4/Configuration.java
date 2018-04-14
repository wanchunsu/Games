package assignment4Game;

public class Configuration {
	
	public int[][] board;
	public int[] available;
	boolean spaceLeft;
	
	public Configuration(){
		board = new int[7][6];
		available = new int[7];
		spaceLeft = true;
	}
	
	public void print(){
		System.out.println("| 0 | 1 | 2 | 3 | 4 | 5 | 6 |");
		System.out.println("+---+---+---+---+---+---+---+");
		for (int i = 0; i < 6; i++){
			System.out.print("|");
			for (int j = 0; j < 7; j++){
				if (board[j][5-i] == 0){
					System.out.print("   |");
				}
				else{
					System.out.print(" "+ board[j][5-i]+" |");
				}
			}
			System.out.println();
		}
	}
	
	public void addDisk (int index, int player){
		// ADD YOUR CODE HERE
		//Finding the row to add the disk in
		int rowToAddIn = this.available[index];
		//updating the board with the added disk
		this.board[index][rowToAddIn] = player;
		//updating other attributes
		this.available[index]=rowToAddIn+1;
		if(!isThereSpaceLeft()) {
			this.spaceLeft=false;
		}
		
	}
	
	//Helper method for addDisk to find if the spaceLeft attribute should be updated to false after a disk is added
	public boolean isThereSpaceLeft() {
		for(int i=0; i<this.board.length; i++) {
			for(int j=0; j<this.board[i].length; j++) {
				if(this.board[i][j]==0) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isWinning (int lastColumnPlayed, int player){
		// ADD YOUR CODE HERE
		//finding the row where the last player played
		int lastRowPlayed=this.available[lastColumnPlayed]-1;
		//Creating 2D arrays to store the coordinates of the diagonals
		int[][] ascendingDiagonal = this.findAscendingDiagonal(lastColumnPlayed,lastRowPlayed);
		int[][] descendingDiagonal = this.findDescendingDiagonal(lastColumnPlayed,lastRowPlayed);
		/*Creating int arrays to store the player positions in the diagonals, column and row
		that correspond to the last position played*/
		int[] ascendingDiagonalElements = new int[6];
		int[] descendingDiagonalElements = new int[6];
		int[] columnElements = new int[6];
		int[] rowElements = new int[7];
		
		//populating the array elements with the respective player move that is present in that position
		//If there is no element, it is left at 0 (0 is not a player, i.e. empty)
		for(int i=0; i<ascendingDiagonalElements.length; i++) {
			if(ascendingDiagonal[i][0]!=-1) {
				ascendingDiagonalElements[i] = this.board[ascendingDiagonal[i][0]][ascendingDiagonal[i][1]];
			}
			if(descendingDiagonal[i][0]!=-1) {
				descendingDiagonalElements[i] = this.board[descendingDiagonal[i][0]][descendingDiagonal[i][1]];
			}
		}
		//Populating the column and row elements with the player positions
		for(int i=0; i<columnElements.length; i++) {
			columnElements[i] = board[lastColumnPlayed][i];
		}
		
		for(int i=0; i<rowElements.length; i++) {
			rowElements[i] = board[i][lastRowPlayed];
		}
		
		//Calling the fourInARow method to find if the player is winning on a diagonal, row, or column
		//returning true if it is the case
		if(this.fourInARow(ascendingDiagonalElements,player)||this.fourInARow(descendingDiagonalElements,player)
			||this.fourInARow(columnElements,player) || this.fourInARow(rowElements,player)) {
			return true;
		}
		
		return false; // DON'T FORGET TO CHANGE THE RETURN
	}
	/*A helper method for isWinning which looks four consecutive numbers in a row, column
     or diagonal that correspond to the player that is passed as input*/
	public boolean fourInARow(int[] rowColumnOrDiagonal, int player) {
		int num=0;
		//Starting num at zero and incrementing by one each time the player is found 
		//but resetting to zero whenever the player is not found along the row, diagonal, or column
		for(int i =0; i<rowColumnOrDiagonal.length; i++) {
			if(rowColumnOrDiagonal[i]==player) {
				num++;
				if(num==4) {
					return true;
				}
			} else {
				num=0;
			}
		}
		
		return false;
	}
	//A helper method to find the ascending diagonal corresponding to a position on the board
	public int[][] findAscendingDiagonal(int column, int row) {
		//Using a 2D array to store the coordinates of the ascending diagonal corresponding to the position given as input
		int[][] ascendingDiagonal = new int[6][2];
		//starting from the position that is passed as input and backtracking to the lowest position
		while(column>=0 && row>=0) {
			if(column==0 || row==0) {
				break;
			}
			column--;
			row--;
		}
		//Storing the ascending diagonal by starting at the lowest position and incrementing both the column and row by one each time
		//Storing -1 if there are no more elements left in the diagonal
		for(int i=0; i<ascendingDiagonal.length; i++) {
			if(column<=6&&row<=5) {
				
				ascendingDiagonal[i][0] = column;
				ascendingDiagonal[i][1] = row;
				column++;
				row++;
			} else {
				ascendingDiagonal[i][0]=-1;
				ascendingDiagonal[i][1]=-1;
			}
		}
		return ascendingDiagonal;
	}
	//A helper method to find the descending diagonal corresponding to a given position on the board
	public int[][] findDescendingDiagonal(int column, int row) {
		//Use a 2D array to store the positions of the descending diagonal
		int[][] descendingDiagonal = new int[6][2];
		//Bactracking to the first element of that diagonal by increasing column and decreasing row by 1 each time
		while(column<=6 && row>=0) {
			if(column==6||row==0) {
				break;
			}
			column++;
			row--;
		}	
		//Storing the elements of the descending diagonal by decreasing column and increasing row
		//Storing -1 if there are no more elements left in the diagonal
		for(int i=0; i<descendingDiagonal.length; i++) {
			if(column>=0 && row<=5) {
				descendingDiagonal[i][0] = column;
				descendingDiagonal[i][1] = row;
				column--;
				row++;
			} else {
				descendingDiagonal[i][0]=-1;
				descendingDiagonal[i][1]=-1;
			}
		}
		return descendingDiagonal;
	}
	
	public int canWinNextRound (int player){
		// ADD YOUR CODE HERE
		//Running through the columns and adding disk, calling isWinning and finding out if the player can win in that position, before removing the disk
		//Returns the column where the player can win in, if none, return -1
		for(int i=0; i<7; i++) {
			if(this.available[i]<6) {
				this.addDisk(i,player);
				if(this.isWinning(i,player)) {
					this.removeDisk(i);
					return i;
				} else {
					this.removeDisk(i);
				}
			}
						
		}
		return -1; // DON'T FORGET TO CHANGE THE RETURN
	}
	//helper method for canWinNextRound and canWinTwoTurns
	//Removes a disk from the specified column
	public void removeDisk( int column) {
		if(this.available[column]!=0) {
			int rowToRemoveFrom = this.available[column]-1;
			this.board[column][rowToRemoveFrom] = 0;
			this.available[column]=rowToRemoveFrom;
		}
	}

	public int canWinTwoTurns (int player){
		// ADD YOUR CODE HERE
		//Finding the number corresponding to the opponent and storing it into a variable
		int opponent;
		if(player==1) {
			opponent=2;
		} else {
			opponent=1;
		}
		//Running through the columns and adding a disk 
		for(int i=0; i<7; i++) {
			if(this.available[i]<6) {
				this.addDisk(i, player);
				//Ensuring the opponent can't win
				if(this.canWinNextRound(opponent)==-1) {
					//Creating an array to store the columns the player can/can't win in
					int[] possibleWinsArray = this.possibleWins(player);
					//A variable to store the total number of ways the player can win
					int numPossibleWins = this.findNumPossibleWins(possibleWinsArray);
			
					if(numPossibleWins>1) { //means that no matter where the opponent adds a disk, the player will still win
						this.removeDisk(i); //remove disk and return the column
						return i;
					} else if(numPossibleWins==1) { //there is one way for the player to win next before the opponenet adds their disk
						int columnWinning = this.findWinningColumn(possibleWinsArray); //find the winning column
						this.addDisk(columnWinning, opponent);//add the opponent in that winning column
						if(this.canWinNextRound(player)!=-1) {//if the player can still win after the opponent blocks the first win
							//remove the opponent's and player's disks and return the column
							this.removeDisk(columnWinning);
							this.removeDisk(i);
							return i;
						} else {
							//the opponent has successfully blocked the potential win, i.e.player cannot win in two turns
							this.removeDisk(columnWinning); // opponent and player's disks
							this.removeDisk(i);
						}
					} else { //there are no ways for the player to win, so remove disk
						this.removeDisk(i);
					}
				} else { //the opponent is able to win once the disk is added, remove disk
					this.removeDisk(i);
				}
			}
		}
		//return -1 if there is no such move
		return -1; // DON'T FORGET TO CHANGE THE RETURN
	}
	//A helper method for canWinTwoTurns to find the winning column
	//It takes a possibleWins array as input and finds the column that is not -1
	public int findWinningColumn(int[] possibleWins) {
		int winningColumn=-1;
		for(int i=0; i<possibleWins.length; i++) {
			if(possibleWins[i]!=-1) {
				winningColumn=possibleWins[i];
				return winningColumn;
			}
		}
		return winningColumn;
	}
	//A helper method for canWinTwoTurns that counts the number of possible wins, by taking a possibleWins array as input
	public int findNumPossibleWins(int[] possibleWins) {
		int numPossibleWins=0;
		for(int j=0; j<7; j++) {
			if(possibleWins[j]!=-1) {
				numPossibleWins++;
			}
		}
		return numPossibleWins;
	}
	//A helper method for canWinTwoTurns that generates an array of the columns of the board
	//Positions corresponding to columns that will allow the player to win once a disk is added in will be
	//set to equal that column, else, the rest are initialized to -1
	public int[] possibleWins( int player) {
		
		int[] possibleWinsArray = new int[7];
		for(int i=0; i<possibleWinsArray.length; i++) {
			possibleWinsArray[i]=-1;
		}
		//Checks if adding a disk to that column will allow the player to win, if yes, 
		//change the element at that column position to be the column
		for(int i=0; i<7; i++) {
			if(this.available[i]<6) {
				this.addDisk(i,player);
				if(this.isWinning(i,player)) {
					this.removeDisk(i);
					possibleWinsArray[i] = i;
				} else {
					this.removeDisk(i);
				}
			}
		}
		return possibleWinsArray;
	
	}
	
}
