//Q3 a)
/**
 * Checks the population of two animals over 10 weeks and calculates the how many
 * animals die in each colony per week.
 *
 * @author Charlie
 * @version 07/01/2023
 */
public class PopulationModel
{
    //Q3 b)
    // adding the following fields
    public int[] numA;
    public int[] numB;

    // adding the following constants
    private static final int INITIALA = 50;
    private static final int INITIALB = 60;
    private static final double KILL_RATEA = 0.2;
    private static final double KILL_RATEB = 0.1;
    private static final int NUM_WEEKS = 10;

    //Q3 c)
    /**
     * initialises numA & numB with the lenght of NUM_WEEKS
     * initialises index 0 of numA to INITIALA & numB to INITIALB
     */
    public PopulationModel()
    {
        numA = new int[NUM_WEEKS];
        numB = new int[NUM_WEEKS];

        numA[0] = INITIALA;
        numB[0] = INITIALB;
    }

    //Q3 d)
    /**
     * Calculates the number of animals left in colony A in the given week
     * 
     * @param int for the current animals in colony A
     * @param int for the current animals in colony B
     * @return int of the number of animals left rounded down.
     */
    public int newNumA(int currentA, int currentB)
    {
        double animalsLeftA = currentA - KILL_RATEB * currentB;

        if (animalsLeftA > 0) {
            return (int) Math.floor(animalsLeftA);
        } else {
            return 0;
        }
    }

    //Q3 e)
    /**
     * Calculates the number of animals left in colony B in the given week
     * 
     * @param int for the current animals in colony A
     * @param int for the current animals in colony B
     * @return int of the number of animals left rounded down.
     */
    public int newNumB(int currentA, int currentB)
    {
        double animalsLeftB = currentB - KILL_RATEA * currentA;

        if (animalsLeftB > 0) {
            return (int) Math.floor(animalsLeftB);
        } else {
            return 0;
        }
    }

    //Q3 f)
    /**
     * Finds the populations of colony A and colony B for the next week
     * 
     * @param current week as an int
     */
    public void storeNewPopulations(int thisWeek)
    {
        int a = numA[thisWeek];
        int b = numB[thisWeek];
        numA[thisWeek + 1] = newNumA(a, b);
        numB[thisWeek + 1] = newNumB(a, b);
    }

    //Q3 g)
    /**
     * Calls the storeNewPopulation method to populate the arrays with data
     */
    public void createData()
    {
        int count = 0;
        while(count < NUM_WEEKS - 1) {
            storeNewPopulations(count);
            count++;
        }
    }

    //Q3 h)
    /**
     * Creates a bar chart of * for the populations of colony A 
     * and colony B
     */
    public void printBarChart()
    {
        for(int i = 0; i < 10; i++) {
            System.out.print("Week " + i + " A :");
            int countA = numA[i];
            while(countA > 0) {
                System.out.print("*");
                countA--;
            }
            System.out.println("(" + numA[i] + ")");

            System.out.print("Week " + i + " B :");
            int countB = numB[i];
            while(countB > 0) {
                System.out.print("*");
                countB--;
            }
            System.out.println("(" + numB[i] + ")" + "\n");
        }
    }   
}
