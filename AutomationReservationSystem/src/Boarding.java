
/**
 * Organize/design and developed by ExteamDev team
 * Date: 10-09-2019
 * @author ExteamDev
 * More info: techexteam@gmail.com
 */

public class Boarding {

    //Instance variables
    private String airline;
    private String flight_number;
    private String date;
    private String seats;
    private String confirmation_number;
    private String last_name;

    //Constructor
    public Boarding(String a, String fn, String date, String seats, String c_number, String lname) {

        this.airline = a;
        this.flight_number = fn;
        this.date = date;
        this.seats = seats;
        this.confirmation_number = c_number;
        this.last_name = lname;
    }

    //Setter and Getter methods
    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFlight_number() {
        return flight_number;
    }

    public void setFlight_number(String flight_number) {
        this.flight_number = flight_number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getConfirmation_number() {
        return confirmation_number;
    }

    public void setConfirmation_number(String confirmation_number) {
        this.confirmation_number = confirmation_number;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Airline : ").append(this.getAirline()).append("\n");
        sb.append("Flight Number : ").append(this.getFlight_number()).append("\n");
        sb.append("Date : ").append(this.getDate()).append("\n");
        sb.append("First Class Seat : ").append("\n");
        sb.append("...").append("\n");
        String[] parse = this.getSeats().split("\\s+");
        String first_class = "", premium = "";
        //Parse seats
        for (String str : parse) { //iterate
            int seat = Integer.parseInt(str);
            if (seat >= 1 && seat <= 6) { //for first class seats
                first_class = first_class + str + " ";
            } else {
                premium = premium + str;
            }
        }
        sb.append(first_class).append("\n");
        sb.append("Premium : ").append("\n");
        sb.append("...").append("\n");
        sb.append(premium).append("\n");
        sb.append("Confirmation Number : ").append(this.getConfirmation_number()).append("\n");
        sb.append("Last Name: ").append(this.getLast_name()).append("\n");

        return sb.toString();

    }
}
