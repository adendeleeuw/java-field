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

    public Input getInput()
    {
        return input;
    }


    public void playCapture(Player player1, Player player2,
                            Board board, boolean isComputer) 
        {
            String name = "";
            int x = 0;
            int y = 0;
            int rows = board.getGrid().getRows();
            int columns = board.getGrid().getColumns();
            while (true){
                if (!isComputer)
                {
                name = player1.getName();
                x = this.getInput().acceptInt("\nPlease enter X coordinate");
                y = this.getInput().acceptInt("\nPlease enter Y coordinate");
                } 
                else 
                {
                name = player2.getName();
                Random r = new Random();
                x = r.nextInt(columns) + 1;
                y = r.nextInt(rows) + 1;
                }
                
                if ( x <= columns && x > 0 && y <= rows && y > 0)
                {
                    boolean result = this.calculateStats(player1, player2, columns, isComputer);
                    if (result)
                    {
                    int arrayIndex = ( columns * ( y - 1 ) + ( x - 1 ));
                    String arrayValue = (" " + name.substring(0, 1) + " ");
                    board.getGrid().setGridArrayValue(arrayIndex, arrayValue);
                    }
                    break;
                } else {
                    System.out.println("\nNot valid for this map size");
                }
            } 
        }


    private boolean calculateStats(Player player1, Player player2, int columns, boolean isComputer)
    {

            //calculate attack and defence
            if (isComputer)
            {
                int player1DefenceValue = (player1.getPlayerStats().getTotalDefence(this.dice.rollDice(3, player1.getName()), player1.getName()));
                int player2AttackValue = (player2.getPlayerStats().getTotalStrength(this.dice.rollDice(2, player2.getName()), player2.getName()));
                if (player2AttackValue > player1DefenceValue)
                {
                    System.out.println("\n" + player2.getName() + " wins\n"); 
                    return true;
                } else 
                {
                    System.out.println("\n" + player1.getName() + " wins\n");
                    return false; 
                }
            } 
            else 
            {
            int player1AttackValue = (player1.getPlayerStats().getTotalStrength(this.dice.rollDice(3, player1.getName()), player1.getName()));
            int player2DefenceValue = (player2.getPlayerStats().getTotalDefence(this.dice.rollDice(2, player2.getName()), player2.getName()));

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


    }
