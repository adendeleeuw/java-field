public class PlayerStats
{
    private int strength;
    private int defence;
    private int coins;
    private int hearts;

    public PlayerStats(){
        this.strength = 0;
        this.defence = 0;
        this.coins = 0;
        this.hearts = 3;
    }

    public PlayerStats(int strength, int defence, int coins)
    {
        this.strength = strength;
        this.defence = defence;
        this.coins = coins;
        this.hearts = 3;
    }

    public String display()
    {
        String display = ("\nStrength: " + strength + "\nDefence: " + defence + 
        "\nCoins: " + coins + "\nHearts: " + hearts);
        return display;
    }

    public int getTotalStrength(int diceValue, String name){
        System.out.println("\n" + name + " total strength is " + (strength + diceValue));
        return (strength + diceValue);
    }

     public int getTotalDefence(int diceValue, String name){
        System.out.println("\n" + name + " total defence is " + (defence + diceValue));
        return (defence + diceValue);
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





}
