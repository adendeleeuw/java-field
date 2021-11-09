public class GridSpace
{
    private String gridValue;
    private BoostArray boostArray;

    public GridSpace()
    {
        this.gridValue = " - ";
        this.boostArray = new BoostArray();
        
    }

    public GridSpace(String gridValue)
    {
        this.gridValue = gridValue;
        this.boostArray = new BoostArray();
    }

    public GridSpace(String gridValue, BoostArray boostArray)
    {
        this.gridValue = gridValue;
        this.boostArray = boostArray;
    }

    public String toString()
    {
        return "\nGrid Value: " + this.gridValue + this.boostArray.toString();
    }

    public String getGridValue()
    {
        return gridValue;
    }

    public BoostArray getBoostArray()
    {
        return boostArray;
    }

    public void setGridValue(String gridValue)
    {
        this.gridValue = gridValue;
    }

    public void setBoostArray(BoostArray boostArray)
    {
        this.boostArray = boostArray;
    }

}
