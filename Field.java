import java.util.Random;

   /** Main class which contains the main method and gameplay loop
    *@author Aden de Leeuw
    *@version 1.0 */

public class Field
{
    private Board board;
    private Boosts boosts;
    private Capture capture;
    private Input input;
    private Player player1;
    private Player player2;
    private Sabotage sabotage;
    private Strike strike;

    /** Default constructor which creates the object of the class Field. 
     */
    public Field() 
    {
        this.board = new Board();
        this.boosts = new Boosts();
        this.capture = new Capture();
        this.input = new Input();
        this.player1 = new Player();
        this.player2 = new Player("ℙlayer 2", new PlayerStats(5, 7, 10000, 3));
        this.sabotage = new Sabotage();
        this.strike = new Strike();
        new StartScreen();
    }

    /** Accessor method to get the boosts available
     * @return              An object of type Boost */
    public Boosts getBoosts()
    {
        return boosts;
    }

    /** Accessor method to get the game board
     * @return              An object of type Board
     */
    public Board getBoard()
    {
        return board;
    }

    /** Accessor method to get the capture game functionality
     * @return              An object of type Capture
     */
    public Capture getCapture()
    {
        return capture;
    }

    /** Accessor method to get the Input class
     * @return              An object of type Input
     */
    public Input getInput()
    {
        return input;
    }

    /** Accessor method to get player1
     * @return              An object of type Player
     */
    public Player getPlayer1()
    {
        return player1;
    }

    /** Accessor method to get player2
     * @return              An object of type Player that belongs to the computer
     */
    public Player getPlayer2()
    {
        return player2;
    }

    /** Accessor method to get sabotage game functionality
     * @return              An object of type Sabotage
     */
    public Sabotage getSabotage()
    {
        return sabotage;
    }

    /** Accessor method to get strike game functionality
     * @return              An object of type Strike
     */
    public Strike getStrike()
    {
        return strike;
    }

    /** Method to begin the program.
     * @param args          An array of Strings representing command line arguments.
     */
    public static void main(String[] args)
    {
        //initialising the game     
        int round = 1; 
        Field field = new Field();
        field.setPlayer1(new Player(field.input.inputName(), new PlayerStats(5, 7, 3000, 3)));

        //Getting board size input from user and rendering board
        int[] size = field.input.inputBoard();
        field.setBoard(new Board(new Grid(size[0], size[1])));
        field.board.initiateArray(field.getBoosts().getBoostArray());
        field.board.renderBoard();

        while(true) //gameplay loop starts
        {
            field.player1Turn(round); //player 1 turn
            if (field.player2.getPlayerStats().getHearts() == 0) // checking if player 1 turn ended in a victory
            {
                System.out.println("You win!");
                break;
            }
            System.out.println(field.getPlayer1().getPlayerStats());

            field.player2Turn(round); //player 2 turn
            if (field.player1.getPlayerStats().getHearts() == 0) // checking in player 2 turn ended in a loss
            {
                System.out.println("You lost!");
                break;
            }

            System.out.println(field.getPlayer1().getPlayerStats()); //display player1 stats for that round
            round++;
        }
    
        field.getPlayer1().getPlayerStats().writeFinalOutcome(round); //write the final outcome of the match to a txt file
    }

    /** Method that controls player1's turn
     * @param   round        The current round as an integer.
     */
    private void player1Turn(int round)
    {
        while (true)
        {
            System.out.println("\n\n Round " + round);
            int choice = this.input.acceptInt("\n\n1. Capture Grid Spot \n2. Sabotage Enemy" 
                                                      + "\n3. Direct Strike at Heart \n");            
            if (choice == 1) // Play Capture
            {
                this.capture.playCapture(this.player1, this.player2,
                                        this.board);
                this.board.renderBoard();
                break;
            }
            else if (choice == 2) // Play Sabotage
            {
                this.sabotage.playSabotage(this.player1, this.player2, 
                                            this.board, false);
                break;
            }
            else if (choice == 3) // Play Strike
            {
                this.strike.playStrike(this.board.getGrid(), this.player1,
                                        this.player2, false);
                break;
            }
            else
            {
                System.out.println("Invalid choice selected");
            }    
        }
    }

    /** Method that controls player2's turn
     * @param   round        The current round as an integer.
     */  
    private void player2Turn(int round)
    {
        System.out.println("\n\n" + this.player2.getName() + "'s turn");
        this.getInput().pressAnyKeyToContinue(); //ask user input to start player 2's turn
        boolean playerCanStrike = this.strike.canPlayStrike(this.board.getGrid(),
                                                            this.player1, this.player2, false);
        boolean computerCanStrike = this.strike.canPlayStrike(this.board.getGrid(),
                                                                this.player1, this.player2, true);
        int choice = 0;
        Random random = new Random(); /**random integers are generated to determine the move of the computer.
                                        If Player 1 can strike, or if it is the first round, the choices are
                                        weighted/set accordingly */
        if (round == 1)
        {
            choice = 1;
        } 
        else if (computerCanStrike)
        {
            choice = random.nextInt(3) + 1;
        }
        else
        {
            choice = random.nextInt(2) + 1;
        }

        switch (choice) // this is the execution of the actual gameplay functions for the computer's turn.
        {
            case 1: //play capture
                this.capture.playCaptureComputer(this.player1, this.player2,
                                                    this.board);
                this.board.renderBoard();
                break;
            case 2: //play sabotage
                this.sabotage.playSabotageComputer(this.player1, this.player2,
                                                    this.board, playerCanStrike);
                break;
            case 3: //play strike
                this.strike.playStrike(this.board.getGrid(), this.player1, this.player2, true);
                break;
            default:
                break;
        }
    } 

    /** Mutator method to set the game board
     * @param   board        An object of type Board
     */      
    public void setBoard(Board board)
    {
        this.board = board;
    }

    /** Mutator method to set the game boosts
     * @param   boosts        An object of type Boost
     */      
    public void setBoosts(Boosts boosts)
    {
        this.boosts = boosts;
    }
    
    /** Mutator method to set the first player
     * @param   player1        an object of type Player
     */   
    public void setPlayer1(Player player1)
    {
        this.player1 = player1;
    }

    /** Mutator method to set the second player
     * @param   player2        An object of type Player
     */   
    public void setPlayer2(Player player2)
    {
        this.player2 = player2;
    }

    /**
     * Mutator method to set the Sabotage game mode
     * @param   sabotage        An object of type Sabotage
     */   
    public void setSabotage(Sabotage sabotage)
    {
        this.sabotage = sabotage;
    }

    /** toString method to return the state of the object
     * @return                  The state of the object as a string
     */   
    public String toString()
    {
        return ("Board: " + board + "\nBoosts: " + boosts + "Player 1: " + player1
                + "Player 2: " + player2);
    }

}
  
