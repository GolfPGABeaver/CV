//Q1 a)
import java.util.ArrayList;
import java.util.Random;
/**
 * Simulate 10,000 meetings with a specified number of attendees and work out
 * the chances of them having a duplicate birthday.
 *
 * @author Charlie Beavers
 * @version 08/12/2022
 */
public class BirthdaySimulation
{
    //Q1 b) 
    //An ArrayList for storing the birthdays of the attendees
    public ArrayList<String> bdays;

    /**
     * Create a BirthdaySimulation
     */
    public BirthdaySimulation()
    {
        bdays = new ArrayList<>();
    }

    /** Q1 c)
     * Generate a random month
     * 
     * @return A string containing a random month
     */
    public String ranMonth()
    {
        String month = "";

        // Create a random number between 0 and 11
        Random random = new Random();
        int randomNum = random.nextInt(11 - 0) + 0;

        //Create an ArrayList to store the months as strings
        ArrayList<String> months = new ArrayList<String>();
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("October");
        months.add("November");
        months.add("December");

        return month = months.get(randomNum);
    }

    /** Q1 d)
     * Generate a random day of the month
     * 
     * @return A int that will be specific to the month
     * @param A String that should be a month of the year e.g., January
     */
    public int ranDateInMonth(String aMonth) {
        int randomDate = 0;
        int maxMonth = 0;
        String month = "";

        Random random = new Random();
        aMonth = aMonth.toLowerCase().trim();

        if(aMonth.equals("february")) {
            int randomFebNum = random.nextInt(3 - 0) + 0;
            if(randomFebNum == 0) {
                maxMonth = 29;
            } else {
                maxMonth = 28;
            }
        } else if (aMonth.equals("april") ||
        aMonth.equals("june") || 
        aMonth.equals("september") ||
        aMonth.equals("november")) {
            maxMonth = 30;
        } else if (aMonth.equals("january") ||
        aMonth.equals("march") || 
        aMonth.equals("may") ||
        aMonth.equals("july") ||
        aMonth.equals("august") ||
        aMonth.equals("october") ||
        aMonth.equals("december")) {
            maxMonth = 31;
        } else {
            System.out.println("Please enter a vaild month");
        }

        int randomDay = random.nextInt(maxMonth - 1) + 1;

        return randomDate = randomDay;
    }

    /** Q1 e)
     * Clear the arrayList bdays and add a random birthday to bdays 
     * for the specified number of attendees
     * 
     * @param A int for the number of attendees
     */
    public void createMeeting(int numAttendees)
    {
        bdays.clear();

        int i = 0;
        while(i < numAttendees) {            
            bdays.add(ranDateInMonth(ranMonth()) + " " + ranMonth());
            i++;
        }
    }

    /** Q1 f)
     * Loop through the ArrayList bdays and look for repeating birthdays
     * 
     * @return true if there is least one repeating birthday otherwise false
     */
    public boolean foundMatch() 
    {
        boolean check = false;

        for(int i = 0; i < bdays.size(); i++) {
            for(int j = i + 1; j < bdays.size(); j++) {
                if(bdays.get(i).equals(bdays.get(j))) {
                    check = true;
                }    
            }
        }
        return check;
    }

    /** Q1 g)
     * Calculate the chances of the number of attendees having a matching birthday
     * in 10,000 meetings
     * 
     * @param A int for the number of attendees within each meeting
     * @return A double representing the percentage chance
     */
    public double runSimulation(int numAttendees)
    {
        int count = 0;
        double percentage = 0.0;

        bdays = new ArrayList<>();

        for(int i = 0; i < 10000; i++){
            createMeeting(numAttendees);
            boolean check = foundMatch();
            if(check == true) {
                count++;
            }
            bdays.clear();
        }

        return percentage = (count / 10000.0) * 100;
    }
}
