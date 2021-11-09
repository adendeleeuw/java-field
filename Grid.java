public class Grid
{
    
    private int rows;
    private int columns;
    private GridSpace[] gridArray;

    public Grid()
    {
        this.rows = 0;
        this.columns = 0;
        this.gridArray = new GridSpace[0];
    }

    public Grid(int rows, int columns)
    {
        this.rows = rows;
        this.columns = columns;
        this.gridArray = new GridSpace[rows*columns];
    }

    public String toString()
    {
        return "\nRows: " + this.rows + "\nColumns: " + this.columns + "\nGrid Array: " + this.gridArray.toString();
    }

    public int getRows() 
    {
        return rows;
    }

    public void setRows(int rows) 
    {
        this.rows = rows;
    }

    public int getColumns() 
    {
        return columns;
    }

    public void setColumns(int columns) 
    {
        this.columns = columns;
    }

    public GridSpace[] getGridArray() 
    {
        return gridArray;
    }

    public void setGridArray(GridSpace[] gridArray) 
    {
        this.gridArray = gridArray;
    }

    public void setGridArrayValue(int position, String value)
    {
        this.gridArray[position].setGridValue(value);
    }

}
