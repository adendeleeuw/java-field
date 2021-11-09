import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

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

    public Field() 
    {
        this.board = new Board();
        this.boosts = new Boosts();
        this.capture = new Capture();
        this.input = new Input();
        this.player1 = new Player();
        this.player2 = new Player("ℙlayer 2", new PlayerStats(5, 7, 10000));
        this.sabotage = new Sabotage();
        this.strike = new Strike();
        new StartScreen();
    }

    public static void main(String[] args)
    {
        //reading boosts from Field
        Field field = new Field();

        //creating players
        field.setPlayer1(new Player(field.inputName(), new PlayerStats(20, 20, 3000)));

        //Getting board size from player and rendering board
        ArrayList<Integer> size = field.createBoard();
        field.setBoard(new Board(new Grid(size.get(0), size.get(1))));
        field.board.initiateArray(field.getBoosts().getBoostArray());
        field.board.renderBoard();

        //gameplay starts until 1 player hearts = 0

          while(field.player1.getPlayerStats().getHearts() > 0 &&
                 field.player2.getPlayerStats().getHearts() > 0)
          {
              //player 1 turn
            while (true)
            {
                int choice = field.getInput().acceptInt("\n1. Capture Grid Spot \n2. Sabotage Enemy" 
                                                      + "\n3. Direct Strike at Heart \n4. Show player stats");            
                if (choice == 1) // Play Capture
                {
                    field.capture.playCapture(field.getPlayer1(), field.getPlayer2(),
                                              field.getBoard(), false);
                    field.board.renderBoard();
                    break;
                }
                else if (choice == 2) // Play Sabotage
                {
                    field.sabotage.playSabotage(field.getPlayer1(), field.getPlayer2(), 
                                                field.getBoard(), false);
                    break;
                }
                else if (choice == 3) // Play Strike
                {
                    field.strike.playStrike(field.getBoard().getGrid(), field.getPlayer1(),
                                            field.getPlayer2(), false);
                    break;
                }
                else if (choice == 4) // Check Player stats
                {
                    System.out.println(field.getPlayer1().display());
                    break;
                }
                else
                {
                    System.out.println("Invalid choice selected");
                }
            }   

                field.getInput().pressAnyKeyToContinue(); //Player 2's turn

                System.out.println("\n\n" + field.getPlayer2().getName() + "'s turn");
                Random random = new Random();
                int choice = random.nextInt(2) + 1;
                boolean playerCanStrike = field.strike.canPlayStrike(field.getBoard().getGrid(),
                                                                field.getPlayer1(), field.getPlayer2(), true);
                switch (choice)
                {
                    case 1:
                        field.capture.playCapture(field.getPlayer1(), field.getPlayer2(),
                                                  field.getBoard(), true);
                        field.board.renderBoard();
                        break;
                    case 2:
                        field.sabotage.playSabotageComputer(field.getPlayer1(), field.getPlayer2(),
                                                            field.getBoard(), playerCanStrike);
                        break;
                    default:
                        break;
                }
            }
        }         

    public String inputName()
    {
        String name = "";
        while(true)
    {
        name = this.getInput().acceptString("\nPlease enter your name");
        if ( name.length() >= 3 && name.length() <= 12 )
            {
            break;
            } 
        else
        {
            System.out.println("\nPlease enter name between 3 and 12 characters");
        }
        }
        return name;
    }

    public ArrayList<Integer> createBoard()
    {
        int rows = 0;
        int columns = 0;
        ArrayList<Integer> size = new ArrayList<Integer>();
        String choice = this.getInput().acceptString("\n\nWhat size board would you like to play on?" +
                            "\n1. Small (3x3) \n2. Medium (7x7) \n3. Large (10x10) \n4. Custom");
            switch(choice) 
            {
                case "1":  
                    rows = 3;
                    columns = 3;
                    System.out.println("Starting a small sized game.... \n\n");
                    size.add(rows);
                    size.add(columns);
                    break;
                case "2":
                    rows = 7;
                    columns = 7;
                    System.out.println("Starting a medium sized game.... \n\n");
                    size.add(rows);
                    size.add(columns);
                    break;
                case "3":
                    rows = 10;
                    columns = 10;
                    System.out.println("Starting a large sized game.... \n\n");
                    size.add(rows);
                    size.add(columns);
                    break;
                case "4":
                    while (true)
                    {
                    rows = this.getInput().acceptInt("\nSet map height (between 3 and 10)");
                    columns = this.getInput().acceptInt("\nSet map width (between 3 and 10)");
                    if ( rows >= 3 && rows <= 10 && columns >= 3 && columns <= 10 )
                    {
                        size.add(rows);
                        size.add(columns);
                        break;
                    }
                    System.out.println("\nPlease make sure the number is between 3 and 10");
                    }
                    break;
                default:
                    System.out.println("\nInvalid choice was selected, please try again");
                    size = createBoard();
                    break;
            }
            return size;
            }
            

    public Boosts getBoosts()
    {
        return boosts;
    }

    public Board getBoard()
    {
        return board;
    }

    public Player getPlayer1()
    {
        return player1;
    }

    public Player getPlayer2()
    {
        return player2;
    }

    public void setBoard(Board board)
    {
        this.board = board;
    }

    public void setPlayer1(Player player1)
    {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2)
    {
        this.player2 = player2;
    }

    public Input getInput()
    {
        return input;
    }


}
  
