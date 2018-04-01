/* 
 Wan-Chun Su
 260729936
 */

public class Mexico {
    
    public static void main(String[] args) {
       
        double buyIn = Double.parseDouble(args[0]);
        double bet = Double.parseDouble(args[1]);
        playMexico(buyIn, bet);
    }
    
    // 1a. A method to simulate a dice roll
    public static int diceRoll() {
        //Declaring and initializing the variable for the value of the dice roll, then returning that value
        int dice = (int)(1 + Math.random()*(6));
        return dice;
    }
    
    // 1b. A method to compute the score of a Player
    public static int getScore(int roll1, int roll2) {
        // Declaring the variable for the score
        int score;
        //Using conditional statements to obtain the score and returning the score
        if (roll1 > roll2) {
            score = Integer.parseInt("" + roll1 + roll2);
        } else {
            score = Integer.parseInt("" + roll2 + roll1);
        }
        return score; 
    }
    
    // 1c. A method to simulate one round of Mexico
    public static int playOneRound(String name) {
        //Declaring and initializing the variables for the two dice rolls using the diceRoll method
        int roll1 = diceRoll();
        int roll2 = diceRoll();
        //Declaring and intializing the variable for the score using the getScore method
        int score = getScore(roll1, roll2);
        //Printing the output of the two dice rolls and the score
        System.out.println(name + " rolled: " + roll1 + " " + roll2);
        System.out.println(name + "'s score is: " + score);
        //Returning the score
        return score;
    }
    
    // 1d. A method to determine the winner of one round
    public static String getWinner(int giuliaScore, int davidScore) {
        //Declaring the variable to store the name of the winner
        String winner;
        //Using conditional statements to go through all possible outcomes to assign the variable for the winner
        if(giuliaScore == davidScore) {
            winner = "tie";
        } else if (giuliaScore == 21) {
            winner = "Giulia";
        } else if (davidScore == 21) {
            winner = "David";
        } else if (giuliaScore%11 == 0 && davidScore%11 == 0) {
            if (giuliaScore > davidScore) {
                winner = "Giulia";
            } else {
                winner = "David";
            }
        } else if (giuliaScore%11 == 0 && davidScore%11 != 0) {
            winner = "Giulia";
        } else if (giuliaScore%11 != 0 && davidScore%11 == 0) {
            winner = "David";
        } else {
            if (giuliaScore > davidScore) {
                winner = "Giulia";
            } else {
                winner = "David";
            }
        }
        //returning the name of the winner or tie if the scores are equal
        return winner;
    }
    
    // 1e. A method to check if the buy in and the base bet are set correctly
    public static boolean canPlay(double buyIn, double bet) {
        //Using conditional statements to return true if the game can be played and false otherwise
        if(bet <= buyIn && buyIn%bet==0.0 && bet>0.0 && buyIn>0.0) {
            return true;
        } else {
            return false;
        }
    }
    
    // 1f. A method to simulate a game of Mexico
    public static void playMexico(double buyIn, double bet) {
        //Setting a conditional statement to find whether the game can be played
        if ((canPlay(buyIn , bet) == false)) {
            //Letting the user know that the game cannot be played due to insufficient funds   
            System.out.println("Insufficient funds. The game cannot be played.");
        } else {
        //The game can be played
        //Declaring and intializing variables for the round and the amount of money each player has at the beginning
            int round = 1;
            double giuliaMoney = buyIn;
            double davidMoney = buyIn;
            
            //Using a loop to iterate the rounds
            while(giuliaMoney!=0.0 && davidMoney!=0.0) {
                //Printing out which round is being simulated
                System.out.println("Round " + round);
                System.out.println();
                //Declaring and intializing the scores of each player and the outcome of each round
                int giuliaScore = playOneRound("Giulia");
                int davidScore = playOneRound("David");
                String outcome = getWinner(giuliaScore, davidScore);
                
                /*Using conditional statements to print out the outcome of each round
                  and modifying the amount of money each player has after each round*/
                if(outcome.equals("tie")){
                    System.out.println("It's a tie. Roll again!");
                    System.out.println();
                } else if (outcome.equals("Giulia")){
                    davidMoney = davidMoney - bet;
                    System.out.println("Giulia wins this round");
                    System.out.println();
                } else {
                    giuliaMoney = giuliaMoney - bet;
                    System.out.println("David wins this round");
                    System.out.println();
                }
                //Ending the game if one player runs out of money and printing out the winner
                if (giuliaMoney == 0.0){
                    System.out.println("David won the game!");
                } else if (davidMoney == 0.0) {
                    System.out.println("Giulia won the game!");
                } else {
                    //Starting another round if both players still have money
                    round++;
                }
            } 
        }      
    } 
    
}