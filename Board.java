import java.util.ArrayList;
import java.util.Collections;

public class Board
{
    private Grid grid;

    public Board()
    {
        this.grid = new Grid();
    }

    public Board(Grid grid)
    {
        this.grid = grid;
    }

    public Grid getGrid()
    {
        return grid;
    }

    public void initiateArray(ArrayList<BoostArray> boostArray)
    {
        GridSpace[] gridArray = this.getGrid().getGridArray();
        ArrayList<Integer> gridSize = new ArrayList<Integer>();
        for (int i = 0; i < gridArray.length; i++)
        {
            gridArray[i] = new GridSpace();
            gridSize.add(i);
        }

        for (int j = 0; j < boostArray.size(); j++) //placing boosts on map
        {
            Collections.shuffle(gridSize);
            this.getGrid().setGridArrayValue(gridSize.get(0), " B ");
            this.getGrid().getGridArray()[gridSize.get(0)].setBoostArray(boostArray.get(j));
            gridSize.remove(0);
        }
    }


    public void renderBoard()
    {
        int columns = grid.getColumns();
        int rows = grid.getRows();
        GridSpace[] gridArray = grid.getGridArray();
        System.out.println("\n");
        System.out.print("  ");  
        for (int i = 1; i < columns + 1; i++ ) //to generate the column headers
        {
            System.out.print(" " + i + " ");
        } 
        System.out.println();

        for (int i = 0; i < gridArray.length ; i++)  // for the main board
        {
            if (i == 0)
            {
                System.out.print(" " + (i + 1));
            }
            System.out.print(gridArray[i].getGridValue());
            
            if ((i + 1) % columns == 0)
            {
                if ((i + 1) % (rows * columns) != 0)
                {
                    System.out.print("\n " + ((i / columns) + 2));
                }
            }
        }
    }

    public void setGrid(Grid grid)
    {
        this.grid = grid;
    }

    public String toString()
    {
        return "Grid: " + grid;
    }

}
