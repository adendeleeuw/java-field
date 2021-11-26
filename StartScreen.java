public class StartScreen
{

    public final static String welcome = ("Welcome to\n" +
    "     ____.                     ___________.__       .__       .___\n" +
    "    |    |____ ___  _______    \\_   _____/|__| ____ |  |    __| _/\n" +
    "    |    \\__  \\\\  \\/ /\\__  \\    |    __)  |  |/ __ \\|  |   / __ |\n" + 
    "/\\__|    |/ __ \\\\   /  / __ \\_  |     \\   |  \\  ___/|  |__/ /_/ |\n" + 
    "\\________(____  /\\_/  (____  /  \\___  /   |__|\\___  >____/\\____ |\n" +
    "              \\/           \\/       \\/            \\/           \\/" + "\nBy Aden de Leeuw - 2021"
    + "\n\nInstructions - The objective of the game is to reduce the opponents hearts to 0. This can be done by" 
    + "\ncapturing a path of grid squares from one side of the board to the other, which will then allow you to strike"
    + "\nthe opponent, reducing their health by 1." );

    public StartScreen()
    {
        System.out.println(welcome);
    }

}
