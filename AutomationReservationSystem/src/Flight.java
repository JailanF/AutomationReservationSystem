
import javax.swing.JButton;


/**
 * Organize/design and developed by ExteamDev team
 * Date: 10-09-2019
 * @author ExteamDev
 * More info: techexteam@gmail.com
 */
public class Flight {

    //Instance variables
    private String name;
    private JButton[] seats;
    private String booked_seat;

    //Constructor
    public Flight(String n, JButton[] button, String b_seat) {
        this.name = n;
        this.seats = button;
        this.booked_seat = b_seat;
    }

    //Setter and Getter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JButton[] getSeats() {
        return seats;
    }

    public void setSeats(JButton[] seats) {
        this.seats = seats;
    }

    public String getBooked_seat() {
        return booked_seat;
    }

    public void setBooked_seat(String booked_seat) {
        this.booked_seat = this.booked_seat + booked_seat;
    }

}
