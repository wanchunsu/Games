
import java.util.Random;
import java.util.Scanner;

//A program that allows a user to play Tic Tac Toe against an AI
public class TicTacToe {
    
    public static void main(String[] args) {
        play(); //Calling the play method in order to play the game
    }
    
    /*Part A: A method that creates a board with dimension n by n
    It takes int n as input and returns an n by n array of characters*/
    public static char[][] createBoard(int n) {
        char[][] board = new char[n][n]; //creating an array of type char
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[i].length; j++) {
                board[i][j] = ' '; //intializing the entries with the space character
            }
        }
        return board; //returning the empty board
    }
    
    /*Helper method 1: Prints a line of alternating plus and minus signs. It takes an integer n as input and
    prints a total of n*2+1 signs */
    public static void printAlternatingSigns(int n) {
        //Using a for loop to print out the signs
        for(int i=0; i<=(n*2); i++) {
            if(i%2==0) { //printing '+' sign if the column is even
                System.out.print('+');
            } else {
                System.out.print('-'); //printing '-' sign if the column is not even
            }
            
        }
        System.out.println();//Starting at a new line for each row    
    }
    
    //Part B: A method that takes a 2D array of characters and prints out the board
    public static void displayBoard(char[][] board) {
        //Declaring and initializing variables for the total number of rows and columns of the board
        int rows = (board.length)*2; 
        int columns = (board[0].length)*2;
        
        //Using a for loop to contruct the board
        for(int i=0; i<=rows; i++) {
            if(i%2==0) {
                //Using the printAlternatingSigns method to print the even rows
                printAlternatingSigns(board.length); 
            } else { //Printing the odd rows
                for(int j=0; j<=columns; j++) {
                    //Printing alternating vertical lines and the entries of the arrays
                    if(j%2==0) {
                        System.out.print('|');
                    } else {
                        System.out.print(board[i/2][j/2]);//Dividing by 2 to get the actual entries of the array
                    }
                }
                System.out.println(); //Printing each row on a new line
            }
        }
    }
    
    //Part C: A method that writes on the board
    /* This method takes as input a 2D character array, representing the board, a character,
    and two integers, representing the location of the character input */
    public static void writeOnBoard(char[][] board, char c, int x, int y) {
        int n = board.length; //Declaring and initializing variable n for the dimension of the board
        //Throwing exceptions for invalid inputs and printing error messages 
        if(x>n-1 || y>n-1 || x<0 || y<0) {
            throw new IllegalArgumentException("Invalid input! This cell is not on the board.");
        } else if(board[x][y] != ' ') {
            throw new IllegalArgumentException("Invalid input! This cell has a character other than the space character.");
        }
        //If the input is valid, the character is added to the position(x,y) on the board
        board[x][y]=c;
    }
    
    //Part D: A method that takes a board as input and uses Scanner to get the move of the user 
    public static void getUserMove(char[][] board) {
        Scanner move = new Scanner(System.in); //Declaring and initializing the Scanner
        //Declaring the variables to store the input from the user
        int x;
        int y;
        int n = board.length; //Declaring and intitializing variable n for the board dimensions
        //Intializing the variables x and y to represent the position on the board
        x = move.nextInt();
        y = move.nextInt();
       
        //Using a while loop to check for invalid inputs and to ask the user for a new move after each invalid input
        while((x>n-1 || y>n-1 || x<0 || y<0) || board[x][y] != ' ') {
            if(x>n-1 || y>n-1 || x<0 || y<0) {
                System.out.println("This cell is not on the board. Please enter a new move.");
                x = move.nextInt();
                y = move.nextInt(); //reinitializing the variables according to the new user input
            } else if(board[x][y] != ' ') {
                System.out.println("This cell is already occupied. Please enter a new move.");
                x = move.nextInt();
                y = move.nextInt();//reinitializing the variables according to the new user input
            }
        }
        //Once a valid input is received, writeOnBoard is called with the appropriate inputs
        writeOnBoard(board,'x',x,y);
    }
    
    //Helper method 2: A method that takes a board and an int as input and returns the column of that int value
    public static char[] columnOfBoard(char[][] board, int i) {
        char[] column = new char[board.length];
        for(int j=0; j<board.length; j++) {
            column[j] = board[j][i]; //initializing the entries of the column 
        }
        return column;
    }
    
    /*Helper method 3: A method that takes a board and an int as input and returns one of the two diagonals 
    depending on the String that describes the diagonal*/
    public static char[] diagonalOfBoard(char[][] board, String s) {
        char[] diagonal = new char[board.length];
        if (s.equals("descending")){
            for(int i=0; i<board.length; i++) {
                diagonal[i]=board[i][i]; //initializing the entries of the descending diagonal 
            } 
        } else if (s.equals("ascending")) {
            for(int i=0; i<board.length; i++) {
                diagonal[i]=board[board.length-i-1][i]; //initializing the entries of the ascending diagonal
            }
        }
        return diagonal;
    }
    
    /*Helper method 4: A method that takes a 1 dimensional array of characters and a char c as input and
    counts how many c's are in the array */
    public static int countNumCharacters(char[] arr, char c) {
        int numCharacters = 0;
        for(int i=0; i<arr.length; i++) {
            if(arr[i]==c) {
                numCharacters++;
            }
        }
        return numCharacters;
    }
    
    /* Helper method 5: A method that takes a one dimensional array and a character as input 
    and returns an int representing the index of the entry of the array that is a space character */
    public static int indexWithSpace(char[] arr){
        int index=0; //declaring and initializing the index
        //Using a for loop to find the array entry that is a space character and storing it into index
        for(int i=0; i<arr.length; i++) {
            if(arr[i]==' ') {
                index += i;
            }    
        }
        return index; //returning the index
    }
    
    //Helper method 6: A method that checks for a space character on a row, column, or diagonal
    public static boolean hasSpaceCharacter(char[] array) {
        //Using a for loop to go through the entries of the array and returning true if a space character is found
        for(int i=0; i<array.length; i++) {
            if(array[i]==' ') {
                return true; 
            }
        }
        return false; //returning false if no space characters were found
    }
          
    //Helper method 7: A method that checks if the AI is winning on a row
    public static boolean AIWinningOnRow(char[][] board) {
        //Using a for loop to go through each row
        for(int i= 0; i<board.length; i++) {
            //Calling countNumCharacters and hasSpaceCharacter to find out if there is an empty space
            //and if all the other cells contain 'o's
            if(board.length-countNumCharacters(board[i],'o')==1 && hasSpaceCharacter(board[i])) {
                return true;//AI is winning on a row
            }
        }
        return false;//returning false if the AI is not winning on a row
    }
    
    //Helper method 8: A method that checks if the AI is winning on a column
    public static boolean AIWinningOnColumn(char[][] board) {
        //Using a for loop to go through each column
        for(int i=0; i<board.length; i++) {
            //Calling countNumCharacters and hasSpaceCharacter to find out if there is an empty space
            //and if all the other cells contain 'o's
            if(board.length-countNumCharacters(columnOfBoard(board,i),'o')==1 && 
            hasSpaceCharacter(columnOfBoard(board,i))) { 
                return true;//AI is winning on a column
            }      
        }
        return false; //returning false if the AI is not winning on a column   
    }
    
    //Helper method 9: A method that checks if the AI is winning on a diagonal
    public static boolean AIWinningOnDiagonal(char[][] board) {
        //Initializing and declaring the two arrays to store the ascending and descending diagonals
        char[] diagonal1 = diagonalOfBoard(board,"ascending");
        char[] diagonal2 = diagonalOfBoard(board,"descending"); 
        if(board.length-countNumCharacters(diagonal1,'o')==1 && hasSpaceCharacter(diagonal1) ||
        board.length-countNumCharacters(diagonal2,'o')==1 && hasSpaceCharacter(diagonal2)) { 
            return true;//AI is winning on a diagonal
        }
        return false;//returning false if the AI is not winning on a column 
    }
    
    //Helper method 10: A method that checks if the AI is losing on a row
    public static boolean AILosingOnRow(char[][] board) {
        //Using a for loop to go through each row
        for(int i= 0; i<board.length; i++) {
            //Calling countNumCharacters and hasSpaceCharacter to find out if there is an empty space
            //and if all the other cells contain 'x's
            if(board.length-countNumCharacters(board[i],'x')==1 && hasSpaceCharacter(board[i])) {
                return true;//AI is losing on a row
            }
        }
        return false;//returning false if the AI is not losing on a row
    }
    
    //Helper method 11: A method that checks if the AI is losing on a column
    public static boolean AILosingOnColumn(char[][] board) {
        
        for(int i=0; i<board.length; i++) {
            //Calling countNumCharacters and hasSpaceCharacter to find out if there is an empty space
            //and if all the other cells contain 'x's
            if(board.length-countNumCharacters(columnOfBoard(board,i),'x')==1 && 
            hasSpaceCharacter(columnOfBoard(board,i))) { 
                return true;//AI is losing on a column
            }      
        }
        return false; //returning false if the AI is not losing on a column   
    }
    
    //Helper method 12: A method that checks if the AI is losing on a diagonal
    public static boolean AILosingOnDiagonal(char[][] board) {
        //Initializing and declaring the two arrays to store the ascending and descending diagonals
        char[] diagonal1 = diagonalOfBoard(board,"ascending");
        char[] diagonal2 = diagonalOfBoard(board,"descending"); 
        //Calling countNumCharacters and hasSpaceCharacter to find out if there is an empty space
        //and if all the other cells contain 'x's
        if(board.length-countNumCharacters(diagonal1,'x')==1 && hasSpaceCharacter(diagonal1) ||
        board.length-countNumCharacters(diagonal2,'x')==1 && hasSpaceCharacter(diagonal2)) { 
            return true;//AI is losing on a diagonal
        }
        return false;//returning false if the AI is not losing on a column 
    }
    
    
    /*Part E: A method that checks for an obvious move that the AI should perform either
    to win or to block the user from winning*/
    public static boolean checkForObviousMove(char[][] board) {
        
        //Using if statements to go through each scenario of a possible obvious move where the AI is winning
        
        if (AIWinningOnRow(board)) {
            //Using a for loop to find the row in which the AI is winning 
            for(int i= 0; i<board.length; i++) { 
                //Ensuring that the row is occupied by all 'o's and one space character 
                if(board.length-countNumCharacters(board[i],'o')==1 && hasSpaceCharacter(board[i])) {
                    //Using indexWithSpace and writeOnBoard to to add an 'o' to the respective
                    //location to ensure the win
                    writeOnBoard(board, 'o', i, indexWithSpace(board[i]));
                }
            }
            return true; //returning true for an obvious AI move
            
        } else if (AIWinningOnColumn(board)) {
            //Using a for loop to find the column in which the AI is winning 
            for(int i= 0; i<board.length; i++) {
                //Ensuring that the column is occupied by all 'o's and one space character 
                if(board.length-countNumCharacters(columnOfBoard(board,i),'o')==1 && 
                hasSpaceCharacter(columnOfBoard(board,i))) {
                    //Using indexWithSpace and writeOnBoard to to add an 'o' 
                    //to the respective location to ensure the win
                    writeOnBoard(board, 'o', indexWithSpace(columnOfBoard(board,i)),i);
                }                            
            }
            return true; //returning true for an obvious AI move
        } else if (AIWinningOnDiagonal(board)) {
            //Declaring and initializing variables to store the diagonals
            char[] diagonal1 = diagonalOfBoard(board,"ascending");
            char[] diagonal2 = diagonalOfBoard(board,"descending");
            
            //Ensuring that diagonal1 is occupied by all 'o's and one space character 
            if(board.length-countNumCharacters(diagonal1,'o')==1 && hasSpaceCharacter(diagonal1)){
                //Storing the index with the space character into a variable
                int spaceOnFirstDiagonal = indexWithSpace(diagonal1);
                
                //Using indexWithSpace and writeOnBoard to to add an 'o' 
                //to the respective location to ensure the win
                writeOnBoard(board, 'o', (board.length-1-spaceOnFirstDiagonal),spaceOnFirstDiagonal); 
                
            //Ensuring that diagonal2 is occupied by all 'o's and one space character    
            } else if (board.length-countNumCharacters(diagonal2,'o')==1 && hasSpaceCharacter(diagonal2)) {
                //Storing the index with the space character into a variable
                int spaceOnSecondDiagonal = indexWithSpace(diagonal2); 
                
                //Using indexWithSpace and writeOnBoard to to add an 'o' 
                //to the respective location to ensure the win
                writeOnBoard(board, 'o', spaceOnSecondDiagonal,spaceOnSecondDiagonal);
            }
            return true; //returning true for an obvious AI move
            
        } else if (AILosingOnRow(board)) {
            //Using a for loop to find the row in which the AI is losing 
            for(int i= 0; i<board.length; i++) { 
                //Ensuring that the row is occupied by all 'x's and one space character 
                if(board.length-countNumCharacters(board[i],'x')==1 && hasSpaceCharacter(board[i])) {
                    //Using indexWithSpace and writeOnBoard to to add an 'o' 
                    //to the respective location to prevent the user from winning
                    writeOnBoard(board, 'o', i, indexWithSpace(board[i]));
                }
            }
            return true;//returning true for an obvious AI move
            
        } else if (AILosingOnColumn(board)) {
            //Using a for loop to find the column in which the AI is losing 
            for(int i= 0; i<board.length; i++) {
                //Ensuring that the column is occupied by all 'x's and one space character 
                if(board.length-countNumCharacters(columnOfBoard(board,i),'x')==1 && 
                hasSpaceCharacter(columnOfBoard(board,i))) {
                    //Using indexWithSpace and writeOnBoard to to add an 'o' 
                    //to the respective location to prevent the user from winning
                    writeOnBoard(board, 'o', indexWithSpace(columnOfBoard(board,i)),i);
                }                            
            }
            return true; //returning true for an obvious AI move
            
        } else if (AILosingOnDiagonal(board)) {
            //Declaring and initializing the variables to store the two diagonals of the board
            char[] diagonal1 = diagonalOfBoard(board,"ascending");
            char[] diagonal2 = diagonalOfBoard(board,"descending");
            
            //Ensuring that diagonal1 is occupied by all 'x's and one space character 
            if(board.length-countNumCharacters(diagonal1,'x')==1 && hasSpaceCharacter(diagonal1)){
                //Storing the index with the space character into a variable
                int spaceOnFirstDiagonal = indexWithSpace(diagonal1);
                
                //Using indexWithSpace and writeOnBoard to to add an 'o' 
                //to the respective location to prevent the user from winning
                writeOnBoard(board, 'o', (board.length-1-spaceOnFirstDiagonal),spaceOnFirstDiagonal);
                
            //Ensuring that diagonal2 is occupied by all 'x's and one space character    
            } else if (board.length-countNumCharacters(diagonal2,'x')==1 && hasSpaceCharacter(diagonal2)) {
                //Storing the index with the space character into a variable
                int spaceOnSecondDiagonal = indexWithSpace(diagonal2);
                
                //Using indexWithSpace and writeOnBoard to to add an 'o' 
                //to the respective location to prevent the user from winning
                writeOnBoard(board, 'o', spaceOnSecondDiagonal,spaceOnSecondDiagonal);
            }
            return true; //returning true for an obvious AI move    
        }
        return false; //returning false if there is not an obvious move that 
        //allows the AI to win or to block the user from winning
       
    }

    //Part F: A method that generates the AI's move. It takes the board as input and returns no value
    public static void getAIMove(char[][] board) {
        //Using an if statement to check for an obvious move by calling the checkForObviousMove method
        if (checkForObviousMove(board)) {
        } else { //Using the Random class to generate an AI move if no obvious move is available   
            //Declaring and initializing two variables of type Random, one for the row, the other for the column
            Random AIMoveRow = new Random();
            Random AIMoveColumn = new Random();
            //Declaring and initializing the random integers representing the row and column
            int row = AIMoveRow.nextInt(board.length);
            int column = AIMoveColumn.nextInt(board.length);
            while(board[row][column] != ' ') { 
            //Generating a new move if the cell is already occupied by a character other than the space character
                row = AIMoveRow.nextInt(board.length);
                column = AIMoveColumn.nextInt(board.length);
            }
            writeOnBoard(board,'o',row,column); //Once a valid move if generated, 
            //writeOnBoard is called to plot the generated move
        }
    }
    
    /*Helper method 13: A method that checks for a whole row, column, or diagonal of 'o's, 
    indicating a win for the AI */
    public static boolean AIWins(char[][] board) {
        for(int i=0;i<board.length; i++) {
            //Using an if statement, countNumCharacters, diagonalOfBoard, and columnOfBoard methods to count the
            //number of 'o's in a each row, diagonal, and column 
            //and checking if that ammount is equal to the length of the board
            if((countNumCharacters((diagonalOfBoard(board, "ascending")),'o'))==board.length || 
            (countNumCharacters((diagonalOfBoard(board, "descending")) ,'o'))==board.length ||
            (countNumCharacters((columnOfBoard(board,i)),'o'))==board.length||
            (countNumCharacters(board[i],'o'))==board.length) {
                return true; //if true, the AI won
            }
        }
        return false;//false otherwise    
    }
    
    /*Helper method 14: A method that checks for a whole row, column, or diagonal of 'x's, 
    indicating a win for the user*/
    public static boolean UserWins (char[][] board) {
        for(int i=0;i<board.length; i++) {
            //Using an if statement, countNumCharacters, diagonalOfBoard, and columnOfBoard methods to count the
            //number of 'x's in a each row, diagonal, and column 
            //and checking if that ammount is equal to the length of the board
            if ((countNumCharacters((diagonalOfBoard(board, "ascending")),'x'))==board.length || 
            (countNumCharacters((diagonalOfBoard(board, "descending")) ,'x'))==board.length ||
            (countNumCharacters((columnOfBoard(board,i)),'x'))==board.length||
            (countNumCharacters(board[i],'x'))==board.length) {
                return true;//if true, the user won
            }
        }
        return false;//false otherwise
    }
    
    //Part G: A method that checks for the winner of the game. It takes the board as input and 
    //returns a character indicating the winner, 'x' for the user, and 'o' for the AI
    public static char checkForWinner (char[][] board) {
        char winner; //declaring the variable for the winner
        //Using an if statements to initialize winner
        if(AIWins(board)) {
            winner = 'o'; //winner is'o' if the AI wins
        } else if (UserWins(board)) {
            winner = 'x';//winner is 'x' if the user wins
        } else {
            winner = ' ';//winner is ' ' if no one wins, 
        }
        return winner;//returning the char value for winner
    }
    
    /*Part H: A method that implements a game of Tic Tac Toe between the user and the 
    AI using previously defined methods. It takes no inputs and returns no value */
    public static void play() {
        //Declaring and intializing a scanner variable for the user's input
        Scanner userInput = new Scanner(System.in);
        //Printing out messages and asking the user for his/her name and preferred dimension to play in
        System.out.println("Please enter your name:");
        String userName = userInput.nextLine();//Storing the user's name
        System.out.println("Welcome, " + userName + "!" + " Are you ready to play?");
        System.out.print("Please choose the dimension of your board: ");
        int dimension; //Declaring the variable to store the dimension of the board
        int moves = 0; /*Declaring and initializing the variable to store the total amount 
        of moves made by the user and AI*/
        
        /*Using a while loop and the scanner to continue asking the user for an input if he/she
        inputs a non-integer value for the dimension*/
        while (!userInput.hasNextInt()) {
           System.out.println("Please enter an integer for the dimension of your board.");
           userInput.nextLine();
        }
        dimension = userInput.nextInt();//Initializing the variable dimension with the appropriate integer
        char[][] board = createBoard(dimension);//Using createBoard to declare and initialize the variable for the board
        int coin; //declaring the variable for the coin toss
        coin = (int)(Math.random()*2);//initializing the variable for the coin toss using Math.random()
        System.out.println("The result of the coin toss is: " + coin);
        
        //Setting conditional statements to determine the order of the players in the game
        if(coin == 0) { //a coin toss value of 0 indicates that the user gets the first move
            System.out.println("You have the first move.");
            /*Using getUserMove and displayBoard to implement the user's first move
            then updating the amount of moves and the coin toss value, so that the AI moves next */ 
            getUserMove(board);
            displayBoard(board);
            moves++;
            coin = coin + 1;
            
        } else { //if the coin toss value is 1, the AI moves first
            System.out.println("The AI has the first move.");
            /*Using getAIMove and displayBoard to generate the AI's first move
            then updating the amount of moves and the coin toss value, so that the user moves next */ 
            getAIMove(board);
            System.out.println("The AI has made its move: ");
            displayBoard(board);
            moves++;
            coin = coin - 1;    
        }
        
        //Using a while loop to continue the game until a winner is decided or the board is full
        while(moves < (dimension*dimension)) {
            if(coin == 1) { //the AI moves
                /*Using getAIMove and displayBoard to generate the AI's first move
                then updating the amount of moves and the coin toss value, so that the user moves next */ 
                getAIMove(board);
                System.out.println("The AI has made its move: ");
                displayBoard(board);
                moves++;
                coin = coin-1;    
            } else { //the user moves
                System.out.print("Please enter your move: ");
                /*Using getUserMove and displayBoard to implement the user's first move
                then updating the amount of moves and the coin toss value, so that the AI moves next */ 
                getUserMove(board);
                displayBoard(board);
                moves++;
                coin = coin+1;
            }
            //Checking for a winner after each move is made, then ending the game by breaking the while loop
            if(checkForWinner(board)=='x') { //User won
                System.out.println();
                System.out.println("Congratulations " + userName + "! You won!");
                break;
            } else if(checkForWinner(board)=='o') { //AI won
                System.out.println();
                System.out.println("GAME OVER!" + "\n" + "You lost.");
                break;
            }
        } 
        //Printing out that it is a tie if the board is full and there is no winner
        if(checkForWinner(board)==' ') {
            System.out.println();
            System.out.println("It's a tie!");
        }
    }
}
