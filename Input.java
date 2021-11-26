import java.util.Scanner;

public class Input{

    public Input()
    {

    }

    public int acceptInt(String string)
    {
        int x = 0;
        while(true)
        {
            try
            {
                Scanner scanner = new Scanner(System.in);
                System.out.println(string);
                x = Integer.parseInt(scanner.nextLine());
                break;
            }
            catch (Exception e)
            {
                System.out.println("Please enter a valid number");
            }
        }
        return x;
    }

    public String acceptString(String string)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(string);
        String x = scanner.nextLine();
        return x;
    }

    public void pressAnyKeyToContinue()
    { 
        System.out.println("\n\nPress Enter key to continue...");
        try 
        {
            System.in.read(); 
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public String inputName()
    {
        String name = "";
        while(true)
        {
            name = this.acceptString("\nPlease enter your name");
            if ( name.length() >= 3 && name.length() <= 12 )
            {   
                break;
            } 
            else
            {
                System.out.println("\nPlease enter name between 3 and 12 characters");
            }
        }
        return name;
    }


    public int[] inputBoard()
    {
        int rows = 0;
        int columns = 0;
        int[] size = new int[2];
        while (true)
        {
            columns = this.acceptInt("\nSet map width (between 3 and 10)");
            rows = this.acceptInt("\nSet map height (between 3 and 10)");
            if ( rows >= 3 && rows <= 10 && columns >= 3 && columns <= 10 )
            {
                 size[0] = rows;
                 size[1] = columns;
                 break;
            }
                System.out.println("\nPlease make sure the number is between 3 and 10");
        }
           return size;
    }

}
