import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Boosts
{

  public final static String FILE_NAME = "boosts.txt";
  private ArrayList<BoostArray> boostArray;

  public Boosts()
  {
    this.boostArray = new ArrayList<BoostArray>();
    this.parseFile(FILE_NAME);
  }

  public ArrayList<BoostArray> getBoostArray()
  {
    return boostArray;
  }

  
  public void parseFile(String FILE_NAME)
  {
      try
      {
          FileReader reader = new FileReader(FILE_NAME);
          Scanner fileInput = new Scanner(reader);
          while (fileInput.hasNextLine())
          {
            String[] lineContents = fileInput.nextLine().split(",");
            for (int i = 0; i < lineContents.length; i++ )
            {
              if (i == 0 && !lineContents[0].equals("0"))
              {
                this.boostArray.add(new BoostArray("Strength", Integer.parseInt(lineContents[0])));
              }
              else if (i == 1 && !lineContents[1].equals("0"))
              {
                this.boostArray.add(new BoostArray("Defence", Integer.parseInt(lineContents[1])));
              }
              else if (i == 2 && !lineContents[2].equals("0"))
              {
                this.boostArray.add(new BoostArray("Coins", Integer.parseInt(lineContents[2])));
              }
            }
          }
          reader.close();
          fileInput.close(); 
      }
      catch (Exception e)
      {
        System.out.println("An error occured while trying to read the file");
      }
  }

  public String toString()
  {
    return "BoostArray: " + boostArray;
  }

}
