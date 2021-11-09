public class Player{

    private PlayerStats playerStats;
    private String name;
    private String playerToken;

    public Player()
    {
        this.playerStats = new PlayerStats();
        this.name = "";
        this.playerToken = "";
    }

    public Player(String name, PlayerStats playerStats)
    {
        this.name = name;
        this.playerStats = playerStats;
        this.playerToken = (" " + name.substring(0, 1) + " ");
        
    }

    public String display()
    {
        String display = ("\nName: " + name + this.getPlayerStats().display());
        return display;
    }

    public PlayerStats getPlayerStats()
    {
        return playerStats;
    }

    public void setPlayerStats(PlayerStats playerStats)
    {
        this.playerStats = playerStats;
    }

    public String getName()
    {
        return name;
    }

    public String getPlayerToken()
    {
        return playerToken;
    }

    public void setPlayerToken(String playerToken)
    {
        this.playerToken = playerToken;
    }

}
