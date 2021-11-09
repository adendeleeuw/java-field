import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Board
{

private Grid grid;

public Board(){
    this.grid = new Grid();
}


public Board(Grid grid)
{
    this.grid = grid;
    System.out.println("\ngenerating map...\n");
}

public Grid getGrid(){
    return grid;
}

    public void initiateArray(ArrayList<BoostArray> boostArray )
    {
        GridSpace[] gridArray = this.getGrid().getGridArray();
        ArrayList<Integer> gridSize = new ArrayList<Integer>();
        for (int i = 0; i < gridArray.length; i++)
        {
            //this.getGrid().setGridArrayValue(i, " - ");
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
    
        //to generate the column headers
    System.out.print("  ");
    for (int i = 1; i < columns + 1; i++ )
     {
            System.out.print(" " + i + " ");
     } 
     System.out.println();

        // for the main board

    for (int i = 0; i < gridArray.length ; i++)
    {
        if (i == 0)
        {
            System.out.print(" "+ (i+1));
        }
        System.out.print(gridArray[i].getGridValue());
        
        if ((i + 1) % columns == 0)
        {
            if ((i + 1) % (rows * columns) != 0)
            {
                System.out.print("\n " + ((i / columns)+2));
            }
        }
    }

    }

}
