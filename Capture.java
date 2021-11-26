import java.util.Random;

public class Capture
{
    private Input input;
    private Dice dice;
    
    public Capture()
    {
        this.input = new Input();
        this.dice = new Dice(6);
    }

    public void playCaptureComputer(Player player1, Player player2, Board board)
    {
        int rows = board.getGrid().getRows();
        int columns = board.getGrid().getColumns();
        Random r = new Random();
        int x = r.nextInt(columns) + 1;
        int y = r.nextInt(rows) + 1;
        int arrayIndex = ( columns * ( y - 1 ) + ( x - 1 ));
        boolean result = calculateStats(player1, player2, columns, true);
        String arrayValue = board.getGrid().getGridArray()[arrayIndex].getGridValue();
        if (result)
        {
            board.getGrid().setGridArrayValue(arrayIndex, player2.getPlayerToken());
            player1.getPlayerStats().incrementGridSpacesLost();
            String boostType = board.getGrid().getGridArray()[arrayIndex].getBoostArray().getBoostType();
            if (!boostType.equals(""))
            {
                if (arrayValue.equals(player1.getPlayerToken()))
                {
                    captureBoost(player1, 
                    board.getGrid().getGridArray()[arrayIndex].getBoostArray().getBoostType(), 
                    board.getGrid().getGridArray()[arrayIndex].getBoostArray().getBoostValue(), board, true); 
                }
                captureBoost(player2, 
                board.getGrid().getGridArray()[arrayIndex].getBoostArray().getBoostType(), 
                board.getGrid().getGridArray()[arrayIndex].getBoostArray().getBoostValue(), board, false);
                if (boostType.equals("Coins"))
                {
                    board.getGrid().getGridArray()[arrayIndex].getBoostArray().setBoostType("");
                    board.getGrid().getGridArray()[arrayIndex].getBoostArray().setBoostValue(0);
                }
            }
        }   
    }

    private boolean calculateStats(Player player1, Player player2, int columns, boolean isComputer)
    {
        //calculate attack and defence
        if (isComputer)
        {
            int player1DefenceValue = (player1.getPlayerStats().getTotalDefence(this.dice.rollDice(3,
                                                                player1.getName()), player1.getName()));
            int player2AttackValue = (player2.getPlayerStats().getTotalStrength(this.dice.rollDice(2,
                                                                player2.getName()), player2.getName()));
            if (player2AttackValue > player1DefenceValue)
            {
                System.out.println("\n" + player2.getName() + " wins\n"); 
                return true;
            } 
            else 
            {
                System.out.println("\n" + player1.getName() + " wins\n");
                return false; 
            }
        } 
        else 
        {
            int player1AttackValue = (player1.getPlayerStats().getTotalStrength(this.dice.rollDice(3,
                                                                player1.getName()), player1.getName()));
            int player2DefenceValue = (player2.getPlayerStats().getTotalDefence(this.dice.rollDice(2,
                                                                player2.getName()), player2.getName()));
            if ( player1AttackValue > player2DefenceValue )
            {
                System.out.println("\n" + player1.getName() + " wins\n");
                return true;
            } 
            else 
            {
                System.out.println("\n" + player2.getName() + " wins \n");
                return false;
            }
        }
    }

    public void captureBoost(Player player, String boostType, int boostValue, Board board, boolean reverse)
    {
        if ( boostType == "Strength")
        {
            if(reverse)
            {
                player.getPlayerStats().setStrength(player.getPlayerStats().getStrength() - boostValue);
            }
            else
            {
                player.getPlayerStats().setStrength(player.getPlayerStats().getStrength() + boostValue);
                System.out.println("\n" + player.getName() + " acquired a strength bonus of " + boostValue);
            }
        }
        else if ( boostType == "Defence")
        {
            if (reverse)
            {
                player.getPlayerStats().setDefence(player.getPlayerStats().getDefence() - boostValue);
            }
            else
            {
                player.getPlayerStats().setDefence(player.getPlayerStats().getDefence() + boostValue);
                System.out.println("\n" + player.getName() + " acquired a Defence bonus of " + boostValue);
            }
        }
        else if ( boostType == "Coins")
        {
            player.getPlayerStats().setCoins( player.getPlayerStats().getCoins() + boostValue);
            System.out.println("\n" + player.getName() + " acquired " + boostValue + " coins" );
        }
    }

    public void playCapture(Player player1, Player player2, Board board) 
    {
        int x = 0;
        int y = 0;
        int rows = board.getGrid().getRows();
        int columns = board.getGrid().getColumns();
        while (true)
        {
            x = this.input.acceptInt("\nPlease enter X coordinate");
            y = this.input.acceptInt("\nPlease enter Y coordinate");

            if ( x <= columns && x > 0 && y <= rows && y > 0)
            {
                int arrayIndex = ( columns * ( y - 1 ) + ( x - 1 ));
                String gridValue = board.getGrid().getGridArray()[arrayIndex].getGridValue();
                if (!gridValue.equals(player1.getPlayerToken()))
                    {
                        boolean result = this.calculateStats(player1, player2, columns, false);
                        if (result)
                        {
                            board.getGrid().setGridArrayValue(arrayIndex, player1.getPlayerToken());
                            player1.getPlayerStats().incrementGridSpacesCaptured();
                            String boostType = board.getGrid().getGridArray()[arrayIndex].getBoostArray().getBoostType();
                            if (!boostType.equals(""))
                            {
                                if (gridValue.equals(player2.getPlayerToken()))
                                {
                                    captureBoost(player2,
                                    board.getGrid().getGridArray()[arrayIndex].getBoostArray().getBoostType(), 
                                    board.getGrid().getGridArray()[arrayIndex].getBoostArray().getBoostValue(), board, true);
                                }

                                captureBoost(player1,
                                board.getGrid().getGridArray()[arrayIndex].getBoostArray().getBoostType(), 
                                board.getGrid().getGridArray()[arrayIndex].getBoostArray().getBoostValue(), board, false);
                                
                                if (boostType.equals("Coins"))
                                {
                                    board.getGrid().getGridArray()[arrayIndex].getBoostArray().setBoostType("");
                                    board.getGrid().getGridArray()[arrayIndex].getBoostArray().setBoostValue(0);
                                }

                            }   
                         }
                        break;
                    } 
                    else 
                    {
                        System.out.println("You cannot capture a grid space you have already captured");
                    }
            } 
            else 
            {
                System.out.println("\nNot valid for this map size");
            }
        } 
    }

    public String toString()
    {
        return "Dice: " + dice;
    }

}
