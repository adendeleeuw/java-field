import java.io.PrintWriter;

public class PlayerStats
{
    private int coins;
    private int defence;
    private int gridSpacesCaptured;
    private int gridSpacesLost;
    private int hearts;
    private int strength;

    public PlayerStats()
    {
        this.coins = 0;
        this.defence = 0;
        this.gridSpacesCaptured = 0;
        this.gridSpacesLost = 0;
        this.hearts = 0;
        this.strength = 0;
    }

    public PlayerStats(int strength, int defence, int coins, int hearts)
    {
        this.coins = coins;
        this.defence = defence;
        this.gridSpacesCaptured = 0;
        this.gridSpacesLost = 0;
        this.hearts = hearts;
        this.strength = strength;
    }

    public String heartsIcon(int hearts)
    {
        String heartsIcon = "";
        for (int i = 0; i < hearts; i++ )
        {
            heartsIcon += "♥ ";
        }
        return heartsIcon;
    }

    public int getTotalStrength(int diceValue, String name)
    {
        System.out.println("\n" + name + " total strength is " + (strength + diceValue));
        return (strength + diceValue);
    }

    public int getTotalDefence(int diceValue, String name)
    {
        System.out.println("\n" + name + " total defence is " + (defence + diceValue));
        return (defence + diceValue);
    }

    public void writeFinalOutcome(int round)
    {
        String gameStatus = "";
        if (this.hearts == 0)
        {
            gameStatus = "won";
        }
        else
        {
            gameStatus = "lost";
        }

        try
        {
            PrintWriter writer = new PrintWriter("outcome.txt");
            writer.println("Rounds played: " + round );
            writer.println("You " + gameStatus + " the match");
            writer.println("Attack: " + this.getStrength());
            writer.println("Defence: " + this.getDefence());
            writer.println("Number of grid squares captured: " + this.getGridSpacesCaptured());
            writer.println("Number of grid squares lost: " + this.getGridSpacesLost());
            writer.close();
        }
        catch (Exception e)
        {
            System.out.println("An error occured saving the final game outcome");
        }
    }

    public int getStrength()
    {
        return strength;
    }

    public int getDefence()
    {
        return defence;
    }
    
    public int getCoins()
    {
        return coins;
    }

    public int getHearts(){
        return hearts;
    }

    public void setStrength(int strength)
    {
        this.strength = strength;
    }

    public void setDefence(int defence)
    {
        this.defence = defence;
    }

    public void decrementStrength(int decrement)
    {
        this.strength = (this.strength - decrement);
    }

    public void decrementDefence(int decrement)
    {
        this.defence = (this.defence - decrement);
    }

    public void setCoins(int coins)
    {
        this.coins = coins;
    }

    public void setHearts(int hearts)
    {
        this.hearts = hearts;
    }

    public int randomCoins(int min, int max)
    {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public int getGridSpacesCaptured()
    {
        return gridSpacesCaptured;
    }

    public int getGridSpacesLost()
    {
        return gridSpacesLost;
    }

    public void incrementGridSpacesCaptured()
    {
        gridSpacesCaptured++;
    }

    public void incrementGridSpacesLost()
    {
        gridSpacesLost++;
    }

    public void setGridSpacesCaptured(int gridSpacesCaptured)
    {
        this.gridSpacesCaptured = gridSpacesCaptured;
    }

    public void setGridSpacesLost(int gridSpacesLost)
    {
        this.gridSpacesLost = gridSpacesLost;
    }

    public String toString()
    {
        return ("\n\nGrid Spaces Won: " + gridSpacesCaptured + " Grid Spaces Lost: " + gridSpacesLost + " Strength: " + strength + " Defence: "
                + defence + " Coins: " + coins + " Hearts: " + this.heartsIcon(hearts));
    }
    
}
