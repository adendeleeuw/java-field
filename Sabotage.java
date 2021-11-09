import java.util.Random;
import java.util.ArrayList;

public class Sabotage
{
    private Input input;


    public Sabotage()
    {
        this.input = new Input();
    }

    public void playSabotageComputer(Player player1, Player player2, Board board, Boolean needStrike)
    {
        Random r = new Random();
        int choice = r.nextInt(5) + 1;
        if (needStrike)
        {
            choice = 5;
        }
        switch(choice)
            {
                case 1: 
                    sabotageStrengthDefenceComputer(player1, player2, 500, 1500, true, false);  
                    break;
                case 2:
                    sabotageStrengthDefenceComputer(player1, player2, 500, 1500, true, false);  
                    break;
                case 3:
                    sabotageStrengthDefenceComputer(player1, player2, 500, 1500, false, true);
                    break;
                case 4:
                    sabotageStrengthDefenceComputer(player1, player2, 500, 1500, false, true);
                    break;
                case 5:
                    sabotageGridComputer(player1, player2, 1000, 2500, board);
                    break;
            } 
    }



    public void playSabotage(Player player1, Player player2, Board board, boolean isComputer)
    {
       int choice = this.getInput().acceptInt("\nPress 1 to decrement the opponents attack by 2"
                                            + "\nPress 2 to decrement the opponents defence by 2"
                                            + "\nPress 3 to sabotage an opponent's grid square");
        switch (choice)
        {
            case 1:
                sabotageStrengthDefence(player1, player2, 500, 1500, true, false);              
                break;
            case 2:
                sabotageStrengthDefence(player1, player2, 500, 1500, false, true);
                break;
            case 3: 
                sabotageGrid(player1, player2, 1000, 2500, board);
                break;
            default:
                System.out.println("Invalid choice selected");
                break;  
        }
    }

    private void sabotageGridComputer(Player player1, Player player2, int coinsMin, int coinsMax,
                                        Board board)
    {
        int randomCoins = (int)(((Math.random() * (coinsMax - coinsMin)) + coinsMin));
        GridSpace[] gridArray = board.getGrid().getGridArray();
        int columns = board.getGrid().getColumns();
        int arrayIndex = 0;
        ArrayList<Integer> canSabotageGrid = new ArrayList<Integer>();
        for (int i = 0; i < gridArray.length; i++ )
            {
                if (gridArray[i].getGridValue().equals(player1.getPlayerToken()))
                {
                    canSabotageGrid.add(i);
                }
            }
        if (!canSabotageGrid.isEmpty())
        {
        Random r = new Random();
        int choice = r.nextInt(canSabotageGrid.size());
        board.getGrid().setGridArrayValue(canSabotageGrid.get(choice), player2.getPlayerToken());
        board.renderBoard();
        System.out.println("\n" + player2.getName() + " sabotaged a grid spot.");
        }
    }



    private void sabotageGrid(Player player1, Player player2, int coinsMin, int coinsMax, Board board)
    {
        GridSpace[] gridArray = board.getGrid().getGridArray();
        int randomCoins = (int)(((Math.random() * (coinsMax - coinsMin)) + coinsMin)),
        columns = board.getGrid().getColumns(),
        x = this.getInput().acceptInt("\nPlease enter X coordinate"),
        y = this.getInput().acceptInt("\nPlease enter Y coordinate"),
        arrayIndex = ( columns * ( y - 1 ) + ( x - 1 ));
        
        if (gridArray[arrayIndex].getGridValue().equals(player2.getPlayerToken()))
        {
            while (true)
            {
                int choice = this.getInput().acceptInt("Cost is " + randomCoins + 
                                                    ". \n\nPress 1 to accept\nPress 2 to decline");
                if (choice == 1)
                {
                    board.getGrid().setGridArrayValue(arrayIndex, player1.getPlayerToken());
                    board.renderBoard();
                    break;
                }
                else if (choice == 2)
                {
                    System.out.println("Player declined to sabotage");
                    break;
                }
            }
        } 
        else 
        {
            System.out.println("invalid coordinate provided");
        }

    }

    private void sabotageStrengthDefenceComputer(Player player1, Player player2, int coinsMin,
                            int coinsMax, boolean isStrength, boolean isDefence)
    {
        int randomCoins = (int)(((Math.random() * (coinsMax - coinsMin)) + coinsMin));

            if (isStrength)
            {
                    player1.getPlayerStats().decrementStrength(2);
                    player2.getPlayerStats().setCoins((player2.getPlayerStats().getCoins() - randomCoins)); 
                    System.out.println("\n" + player2.getName() + " reduced " + player1.getName() 
                                        +"'s Strength to " + player1.getPlayerStats().getStrength()); 
                } 
                else if (isDefence)
                {
                    player1.getPlayerStats().decrementDefence(2);
                    player2.getPlayerStats().setCoins((player2.getPlayerStats().getCoins() - randomCoins)); 
                    System.out.println("\n" + player2.getName() + " reduced " + player1.getName() 
                                        +"'s Defence to " + player1.getPlayerStats().getDefence());
                }    
        }
    
  
  private void sabotageStrengthDefence(Player player1, Player player2, int coinsMin,
                        int coinsMax, boolean isStrength, boolean isDefence)
  {
      int randomCoins = (int)(((Math.random() * (coinsMax - coinsMin)) + coinsMin));
      while (true)
      {
      
      int choice = this.getInput().acceptInt("Cost is " + randomCoins + ". \n\nPress 1 to accept\nPress 2 to decline");

        switch (choice)
       {
         case 1:
           
           if (isStrength)
           {
                player2.getPlayerStats().decrementStrength(2);
                player1.getPlayerStats().setCoins((player1.getPlayerStats().getCoins() - randomCoins)); 
                System.out.println("\n" + player1.getName() + " reduced " + player2.getName() 
                                    +"'s Strength to " + player2.getPlayerStats().getStrength()); 
           } 
           else if (isDefence)
            {
                player2.getPlayerStats().decrementDefence(2);
                player1.getPlayerStats().setCoins((player1.getPlayerStats().getCoins() - randomCoins)); 
                System.out.println("\n" + player1.getName() + " reduced " + player2.getName() 
                                    +"'s Defence to " + player2.getPlayerStats().getDefence());
            }
           break;
        case 2:
            System.out.println(player1.getName() + " declined to sabotage");
            break;
        default:
            System.out.println("Invalid choice selected");
            break;
        }

        if (choice == 1 || choice == 2)
        {
            break;
        }
    }
  }

    public Input getInput()
    {
        return input;
    }



}
