import java.util.Random;
import java.util.ArrayList;

public class Sabotage
{

    public Sabotage()
    {
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
        Input input = new Input();
       int choice = input.acceptInt("\nPress 1 to decrement the opponents attack by 2"
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
        if (player2.getPlayerStats().getCoins() > randomCoins)
        {
            GridSpace[] gridArray = board.getGrid().getGridArray();
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
                player1.getPlayerStats().incrementGridSpacesLost();
                player2.getPlayerStats().setCoins((player2.getPlayerStats().getCoins() - randomCoins));
                //apply bonus to player 2
                new Capture().captureBoost(player2, 
                board.getGrid().getGridArray()[canSabotageGrid.get(choice)].getBoostArray().getBoostType(), 
                board.getGrid().getGridArray()[canSabotageGrid.get(choice)].getBoostArray().getBoostValue(), board, false);
                // decrement bonus to player 1
                new Capture().captureBoost(player1, 
                board.getGrid().getGridArray()[canSabotageGrid.get(choice)].getBoostArray().getBoostType(), 
                board.getGrid().getGridArray()[canSabotageGrid.get(choice)].getBoostArray().getBoostValue(), board, true);

                board.renderBoard();
                System.out.println("\n" + player2.getName() + " sabotaged a grid spot.");
            }
        } 
        else
        {
            System.out.println("Computer did not have enough coins to sabotage");
        }
   
    }

    private void sabotageGrid(Player player1, Player player2, int coinsMin, int coinsMax, Board board)
    {
        Input input = new Input();
        GridSpace[] gridArray = board.getGrid().getGridArray();
        int randomCoins = (int)(((Math.random() * (coinsMax - coinsMin)) + coinsMin)),
        columns = board.getGrid().getColumns(),
        x = input.acceptInt("\nPlease enter X coordinate"),
        y = input.acceptInt("\nPlease enter Y coordinate"),
        arrayIndex = ( columns * ( y - 1 ) + ( x - 1 ));
        
        if (gridArray[arrayIndex].getGridValue().equals(player2.getPlayerToken()))
        {
            while (true)
            {
                int choice = input.acceptInt("Cost is " + randomCoins + 
                                                    ". \n\nPress 1 to accept\nPress 2 to decline");
                if (choice == 1)
                {
                    if (player1.getPlayerStats().getCoins() >= randomCoins)
                    {
                        player1.getPlayerStats().incrementGridSpacesCaptured();
                        board.getGrid().setGridArrayValue(arrayIndex, player1.getPlayerToken());
                        //apply bonus to player 1
                        new Capture().captureBoost(player1, 
                        board.getGrid().getGridArray()[arrayIndex].getBoostArray().getBoostType(), 
                        board.getGrid().getGridArray()[arrayIndex].getBoostArray().getBoostValue(), board, false);
                        
                        //remove bonus to player 2
                        new Capture().captureBoost(player2, 
                        board.getGrid().getGridArray()[arrayIndex].getBoostArray().getBoostType(), 
                        board.getGrid().getGridArray()[arrayIndex].getBoostArray().getBoostValue(), board, true);

                        player1.getPlayerStats().setCoins((player1.getPlayerStats().getCoins() - randomCoins));
                        board.renderBoard();
                        break;
                    }
                    else
                    {
                        System.out.println("You do not have enough coins to sabotage.");
                    }
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
        if (randomCoins < player2.getPlayerStats().getCoins())
        {
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
        else
        {
            System.out.println("Computer did not have enough coins to sabotage");
        }   
    }
    
  
  private void sabotageStrengthDefence(Player player1, Player player2, int coinsMin,
                        int coinsMax, boolean isStrength, boolean isDefence)
  {
      Input input = new Input();
      int randomCoins = (int)(((Math.random() * (coinsMax - coinsMin)) + coinsMin));
      while (true)
      {
      
      int choice = input.acceptInt("Cost is " + randomCoins + ". \n\nPress 1 to accept\nPress 2 to decline");

        switch (choice)
       {
         case 1:
           
           if (player1.getPlayerStats().getCoins() >= randomCoins)
            {
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
            } 
            else 
            {
                System.out.println("You do not have enough coins to sabotage.");
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

}
