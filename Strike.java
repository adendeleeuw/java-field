import java.util.ArrayList;

public class Strike
{

    public Strike()
    {

    }

    public void playStrike(Grid grid, Player player1, Player player2, Boolean isComputer)
    {
        boolean canStrike = this.canPlayStrike(grid, player1, player2, isComputer);
        if (canStrike)
        {
            if (isComputer)
            {
                player1.getPlayerStats().setHearts(player1.getPlayerStats().getHearts() - 1);
                System.out.println("Player 2 reduced " + player1.getName() 
                                    + "'s hearts to " + player1.getPlayerStats().getHearts() );
            }
            else
            {
                player2.getPlayerStats().setHearts(player2.getPlayerStats().getHearts() - 1);
                System.out.println( player1.getName() + " reduced Player 2's hearts to " 
                                  + player2.getPlayerStats().getHearts() );
            }

        } else 
        {
            System.out.println("You cannot strike at this time");
        }
    }

    public boolean canPlayStrike(Grid grid, Player player1, Player player2, Boolean isComputer)
    {
        String token = "";
        if (isComputer)
        {
            token = player2.getPlayerToken();
        } 
        else
        {
            token = player1.getPlayerToken();
        }

        int columns = grid.getColumns();
        int rows = grid.getRows();
        GridSpace[] gridArray = grid.getGridArray();

        for (int y = 0; y < columns; y++)
        {
            ArrayList<Boolean> canStrike = new ArrayList<Boolean>();
            for (int x = 0; x < rows; x++)
            {
                if ( gridArray[(x * columns) + y].getGridValue().equals(token))
                {
                    canStrike.add(true);
                } 
                else 
                {
                    canStrike.add(false);
                }
            }
            if (!canStrike.contains(false))
            {
                return true;
            } 
        }
        return false;
    }

    
}
