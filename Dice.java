public class Dice
{
    private int diceSides;

    public Dice()
    {
        this.diceSides = 0;
    }

    public Dice(int diceSides)
    {
        this.diceSides = diceSides;
    }

    public int getDiceSides()
    {
        return diceSides;
    }

    public int rollDice(int times, String name)
    {
        int sum = 0;
        for (int i = 0; i < times; i++ )
        {
            sum+= (Math.random() * this.diceSides) + 1;
        }

        System.out.println("\n" + name + " rolled a " + sum);
        return sum;
    }

    public void setDiceSides(int diceSides)
    {
        this.diceSides = diceSides;
    }

    public String toString()
    {
        return "Dice Sides: " + diceSides;
    }

}
