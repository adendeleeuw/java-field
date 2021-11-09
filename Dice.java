public class Dice
{

    private int diceSides;

    public Dice()
    {
        this.diceSides = 0;
    }

    public Dice(int diceSides){
        this.diceSides = diceSides;
    }

     public int getDiceSides(){
        return diceSides;
    }

    public int rollDice(int times, String name)
    {
        int dice1;
        int dice2;
        int dice3;

    if (times == 2)
    {
        dice1=(int)(Math.random()*this.diceSides+1);
        dice2=(int)(Math.random()*this.diceSides+1);
        System.out.println("\n" + name + " rolled a " + ( dice1 + dice2 ));
        return dice1 + dice2;
    } else if (times == 3)
    {
        dice1=(int)(Math.random()*this.diceSides+1);
        dice2=(int)(Math.random()*this.diceSides+1);
        dice3=(int)(Math.random()*this.diceSides+1);
        System.out.println(name + " rolled a " + ( dice1 + dice2 + dice3));
        return dice1 + dice2 + dice3;
    }
    return 0;
    }

   

}
