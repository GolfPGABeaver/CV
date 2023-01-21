// Q2 a)
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
/**
 * A simple bus route application that allows a user to search for their required
 * bus number and find where that bus stops.
 *
 * @author Charlie Beavers
 * @version 13/12/2022
 */
public class BusRoutes
{
    // Q2 b)
    public HashMap <Integer, ArrayList<String>> routes;
    private InputReader reader;

    // Q2 c)
    /**
     * Initialise routes as a new instance of HashMap
     * Initialise reader as a new instance of InputReader
     * invoke the method populate Routes
     */
    public BusRoutes() 
    {
        routes = new HashMap<>();
        reader = new InputReader();
        populateRoutes();
    }

    /**
     * Provided a method to populate routes with test data
     */    
    public void populateRoutes()
    {
        ArrayList<String> stops = new ArrayList<>();
        stops.add("High Street");
        stops.add("Acacia Avenue");
        stops.add("Brown Street");
        stops.add("Broadway");
        stops.add("Station");
        routes.put(32, stops);

        ArrayList<String> stops1 = new ArrayList<>();
        stops1.add("High Street");
        stops1.add("Hospital");
        stops1.add("Brown Street");
        stops1.add("School");
        stops1.add("Station");
        routes.put(13, stops1);

        ArrayList<String> stops2 = new ArrayList<>();
        stops2.add("Bank");
        stops2.add("Hospital");
        stops2.add("Brown Street");
        stops2.add("School");
        stops2.add("Terminus");
        routes.put(22, stops2);
    }

    // Q2 d)
    /**
     * Prints "Bus number xxx route:" where xxx is the bus number
     * Then underneath lists all the stops associated with that bus
     * 
     * @param A int to specify the bus number
     */
    public void printRoute(int busNum) 
    {
        System.out.println("Bus number " + busNum + " route:");

        for(String item : routes.get(busNum)) {
            System.out.println(item);
        }
    }

    // Q2 e)
    /**
     * Uses the printRoute method to print all the buses and their stops
     */
    public void printAllRoutes() 
    {
        for(Integer item : routes.keySet()) {
            printRoute(item);
        }
    }

    // Q2 f)
    /**
     * Lists all the buses that stop at a specific bus stop
     * 
     * @param A String to specify the bus stop
     * @return A HashSet of bus number Integers
     */
    public HashSet<Integer> getBusesStoppingHere(String aStop) 
    {
        HashSet<Integer> buses = new HashSet<>();
        for(Integer bus : routes.keySet()) {
            for(String stops : routes.get(bus)) {
                if(stops.equals(aStop)) {
                    buses.add(bus);                   
                }
            }
        }
        return buses;
    }

    //Q2 g)
    /**
     * Creates a map of bus stops as the keys and their corrosponding values as
     * the buses 
     * 
     * @return A HashMap of keys that are Strings and values that are a HashSet
     * of Integers
     */
    public HashMap<String, HashSet<Integer>> getStopsAndBusNumbers() 
    {
        HashMap <String, HashSet<Integer>> stopBuses = new HashMap<>();
        HashSet <String> stops = new HashSet<>();

        for(ArrayList <String> items : routes.values()) {
            stops.addAll(items);
        }
        for(String item : stops) {
            stopBuses.put(item, getBusesStoppingHere(item));
        }
        return stopBuses;
    }

    //Q2 h)
    /**
     * Prompts for a bus stop, and then checks whether a bus actually stops
     * at that bus stop. If it dose it prints the list of buses and if it 
     * dose'nt prints Sorry, no buses go there.
     */
    public void accessInfoService()
    {
        System.out.println("\n" + "\n");
        System.out.println("Welcome to the Bus Information Service.");
        System.out.print("Type where you want to go to -> ");

        String userInput = reader.getInput();

        if(!getStopsAndBusNumbers().containsKey(userInput)){
            System.out.print("Sorry, no buses go there");
        } else {
            System.out.println("\n" + "Here is a list of buses:" + "\n");
            for(Integer item : getBusesStoppingHere(userInput)) {
                System.out.println(item);
            }  
            System.out.println("\n" + "Enter bus number to get a full route -> " + "\n");

            userInput = reader.getInput();
            printRoute(Integer.parseInt(userInput));
        }
    }
}

----------------------------------------------------------------------------------------------

import java.util.Scanner;

/**
 * InputReader reads text input from the Terminal window.
 * 
 * @author M250 Module Team
 * @version 13 Sept 2021
 */
public class InputReader
{
    private Scanner reader;

    /**
     * Create a new InputReader that reads text from the Terminal window.
     */
    public InputReader()
    {
        reader = new Scanner(System.in);
    }

    /**
     * Read a line of text from standard input (the text terminal),
     * and return it as a String.
     *
     * @return  A String typed by the user. 
     */
    public String getInput() 
    {     
      String word = reader.nextLine();
      return word;                    
    }
}
