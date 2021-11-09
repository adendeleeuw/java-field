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
            catch(Exception e) 
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
        System.out.println("\nPress Enter key to continue...");
        try 
        {
            System.in.read(); 
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }



}
