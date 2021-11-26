public class BoostArray
{
    private String boostType;
    private int boostValue;

    public BoostArray()
    {
        this.boostType = "";
        this.boostValue = 0;
    }

    public BoostArray(String boostType, int boostValue)
    {
        this.boostType = boostType;
        this.boostValue = boostValue;
    }

    public String getBoostType()
    {
        return boostType;
    }

    public int getBoostValue()
    {
        return boostValue;
    }

    public void setBoostType(String boostType)
    {
        this.boostType = boostType;
    }

    public void setBoostValue(int boostValue)
    {
        this.boostValue = boostValue;
    }

    public String toString()
    {
        return "\nBoost Type: " + this.boostType + "\nBoost Value: " + this.boostValue;
    }

}
