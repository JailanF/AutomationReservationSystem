
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Organize/design and developed by ExteamDev team
 * Date: 10-09-2019
 * @author ExteamDev
 * More info: techexteam@gmail.com
 */
public class FlightGUI extends JFrame implements ActionListener {

    //Instance variables
    private static final int FIRST_CLASS_SEATS = 6;
    private static final int PREMIUM_SEATS = 40;
    private String[] combobox = null;
    private JComboBox flights = null;
    private JTextField firstname = null;
    private JTextField lastname = null;
    private JTextField address_text = null;
    private JTextField city_text = null;
    private JTextField state_text = null;
    private JTextField phone_text = null;
    private JButton[] seats = null;
    private JButton[] seats1 = null;
    private JButton[] seats2 = null;
    private JButton[] seats3 = null;
    private JButton confirm = null;
    private JButton access = null;
    private JPanel container = null;
    private String user_seats = "", flight1_seats = "", flight2_seats = "", flight3_seats = "";
    private int seat_counter = 0;
    private List<Boarding> boardingList = null;
    private List<Flight> allflights = null;
    private int selected_index = 0;

    //Default Constructor
    public FlightGUI() {

        boardingList = new ArrayList<>();
        allflights = new ArrayList<>();
    }

    //Argument Constructor
    public FlightGUI(List<Flight> airline_flights, List<Boarding> all_boarding) {
        this.allflights = airline_flights;
        this.boardingList = all_boarding;
    }

    public void viewGUI() {

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//set frame to exit on close
        setResizable(false);//don't resize frame
        setTitle("Siri Airlines");//GUI title

        //Initialize the seats for all flights
        firstname = new JTextField();
        seats = new JButton[46];//button array
        seats1 = new JButton[46];//button array
        seats2 = new JButton[46];//button array
        seats3 = new JButton[46];//button array
        createFlights();
        combobox = new String[]{"Choose Flight", allflights.get(0).getName(), allflights.get(1).getName(),
            allflights.get(2).getName()};
        flights = new JComboBox(combobox);
        lastname = new JTextField();
        address_text = new JTextField();
        city_text = new JTextField();
        state_text = new JTextField();
        phone_text = new JTextField();

        confirm = new JButton("Confirm");
        access = new JButton("Access Reservation");

        JLabel title = createLable("Automation Reservation System", 16);
        JLabel choose_flight = createLable("Select Flight: ", 14);
        JLabel fname = createLable("First Name: ", 14);
        JLabel lname = createLable("Last Name: ", 14);
        JLabel address = createLable("Address: ", 14);
        JLabel city = createLable("City: ", 14);
        JLabel state = createLable("State: ", 14);
        JLabel phone = createLable("Phone: ", 14);
        JLabel first_class = createLable("First-Class Seats ", 14);
        JLabel premium = createLable("Premium Seats ", 14);
        flights.setSelectedIndex(0);
        container = new JPanel();
        container.setLayout(null);

        title.setBounds(235, 10, 260, 35);

        choose_flight.setBounds(140, 70, 200, 25);
        flights.setBounds(240, 70, 170, 25);

        fname.setBounds(45, 150, 150, 25);
        firstname.setBounds(135, 150, 180, 25);

        lname.setBounds(340, 150, 100, 25);
        lastname.setBounds(420, 150, 180, 25);

        address.setBounds(45, 200, 150, 25);
        address_text.setBounds(135, 200, 180, 25);

        city.setBounds(340, 200, 150, 25);
        city_text.setBounds(420, 200, 180, 25);

        state.setBounds(45, 250, 150, 25);
        state_text.setBounds(135, 250, 180, 25);

        phone.setBounds(340, 250, 150, 25);
        phone_text.setBounds(420, 250, 180, 25);

        first_class.setBounds(290, 320, 150, 25);
        premium.setBounds(290, 480, 150, 25);

        confirm.setBounds(540, 655, 150, 25);
        access.setBounds(15, 655, 180, 25);

        //combobox action listener
        flights.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JComboBox comboBox = (JComboBox) event.getSource();
                String selected = (String) comboBox.getSelectedItem();

                if (selected.equals("Choose Flight")) {

                    //...nothing do
                } else {

                    user_seats = "";//reset seats

                    selected_index = getSelectedSeatIndex(selected);
                    System.out.println("Selected index : " + selected_index);
                    switch (selected_index) {
                        case 0:
                            flight1_seats = allflights.get(0).getBooked_seat();
                            System.out.println("Seats 1: " + flight1_seats);
                            updateSeats(flight1_seats);
                            break;

                        case 1:
                            flight2_seats = allflights.get(1).getBooked_seat();
                            System.out.println("Seats 2: " + flight2_seats);
                            updateSeats(flight2_seats);
                            break;
                        case 2:
                            flight3_seats = allflights.get(2).getBooked_seat();
                            System.out.println("Seats 3: " + flight3_seats);
                            updateSeats(flight3_seats);
                            break;
                        default:
                            break;
                    }
                }

            }
        });

        container.add(title);
        container.add(choose_flight);
        container.add(flights);
        container.add(fname);
        container.add(firstname);
        container.add(lname);
        container.add(lastname);
        container.add(address);
        container.add(address_text);
        container.add(city);
        container.add(city_text);
        container.add(state);
        container.add(state_text);
        container.add(phone);
        container.add(phone_text);
        container.add(first_class);
        container.add(premium);
        container.add(confirm);
        container.add(access);
        confirm.setBackground(Color.gray);
        access.setBackground(Color.gray);
        container.setBackground(Color.ORANGE);

        //Load default seats
        initializeSeats(seats);//method used to load all seat numbering
        initializeSeats(seats1);
        initializeSeats(seats2);
        initializeSeats(seats3);
        createFirstClassSeats(seats);
        createPrimiumSeats(seats);
        addActionListeners(seats);
        //Add action listener
        confirm.addActionListener(this);
        access.addActionListener(this);

        add(container);
        setSize(730, 720); //size of frame
        setVisible(true);//visibility true
    }

    //Method used to display all flight seats
    public void addActionListeners(JButton[] seats) {
        //Action Lister for Button Array
        for (int i = 0; i < seats.length; i++) { //iterate

            seats[i].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean isBooked = false;
                    int index = 0;
                    if (seat_counter == 5) { //maximum we can select 5 seats at a time
                        JOptionPane.showMessageDialog(null, "Sorry you already select five seats!");
                    } else {
                        JButton button = (JButton) e.getSource();
                        int opt = JOptionPane.showConfirmDialog(null, "Do you want to select this seat?", "Seats", JOptionPane.YES_NO_OPTION);
                        if (opt == JOptionPane.YES_OPTION) { //select seats
                            JOptionPane.showMessageDialog(null, "" + button.getText() + " is selected");
                            isBooked = true;
                            user_seats = user_seats + button.getText() + " ";//add user seats
                            allflights.get(selected_index).setBooked_seat(button.getText() + " ");//add flight seats
                            index = Integer.parseInt(button.getText());

                            seat_counter++;
                        } else {
                            //...for cancel button
                        }

                    }

                    if (isBooked) {
                        seats[index - 1].setBackground(Color.RED);//change seat color
                        seats[index - 1].setEnabled(false);
                    }
                }
            });
        }
    }

    //Method used to update seat color
    public void updateSeats(String b_seats) {

        String[] all_booked_seats = b_seats.split("\\s+");
        if (b_seats.isEmpty()) {
            refreshSeats();
        } else { //update seats
            for (int i = 0; i < all_booked_seats.length; i++) {

                int seat_numer = Integer.parseInt(all_booked_seats[i]);
                seats[seat_numer - 1].setBackground(Color.RED);
                seats[seat_numer - 1].setEnabled(false);
            }
        }

    }

    //Method used to refresh the seats
    public void refreshSeats() {
        for (int i = 0; i < seats.length; i++) {
            seats[i].setBackground(Color.GREEN);
            seats[i].setEnabled(true);
        }
    }

    //Get index of seat selection
    public int getSelectedSeatIndex(String selected) {
        int index = 0;

        for (int i = 0; i < allflights.size(); i++) {

            if (allflights.get(i).getName().equals(selected.trim())) {
                index = i;
                break; //terminate loop
            }
        }

        return index;
    }

    //Method used to create flight name and seats
    public void createFlights() {

        allflights.add(new Flight("SA1212", seats1, ""));
        allflights.add(new Flight("SA1216", seats2, ""));
        allflights.add(new Flight("SA1299", seats3, ""));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getActionCommand().equals("Confirm")) { //button listener

            //Get data
            String airline_flight = flights.getSelectedItem().toString();
            String fname = firstname.getText();
            String lname = lastname.getText();
            String address = address_text.getText();
            String city = city_text.getText();
            String state = state_text.getText();
            String phone = phone_text.getText();

            //Perform validations
            if (airline_flight.equals("Choose Flight")) {
                JOptionPane.showMessageDialog(null, "Please choose Flight first");
            } else if (airline_flight.isEmpty() || fname.isEmpty() || lname.isEmpty() || address.isEmpty()
                    || city.isEmpty() || state.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fields should not empty");

            } else {

                //Validate phone number
                if (!phone.matches("[0-9]+") || phone.length() != 10) {

                    JOptionPane.showMessageDialog(null, "Please enter valid phone number, 10 digit number");

                } else if (user_seats.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select seat(s)");
                } else {
                    //Create Boarding Pass
                    Random random = new Random();

                    int number = random.nextInt(1000000);
                    String confirmation_number = Integer.toString(number);//create confirmation number
                    //add new boarding pass
                    boardingList.add(new Boarding("Siri Airline", airline_flight, "25-05-2019", user_seats,
                            confirmation_number, lname));
                    //update selected flight seat
                    allflights.get(selected_index).setSeats(seats);//update seat
                    JOptionPane.showMessageDialog(null, "You successfully booked flight name " + airline_flight + "\n"
                            + "Seats : " + user_seats
                            + "\nYour Confirmation number is : " + confirmation_number);
                    flights.setSelectedIndex(0);
                    seat_counter = 0;
                    refreshSeats();
                    firstname.setText("");
                    lastname.setText("");
                    address_text.setText("");
                    city_text.setText("");
                    state_text.setText("");
                    phone_text.setText("");
                    initializeSeats(seats2);//reset fields
                }
            }
        } else if (ae.getActionCommand().equals("Access Reservation")) { //button listener

            //allflights.get(selected_index).setSeats(seats);//update seat
            UserGUI gui = new UserGUI(boardingList, allflights);
            gui.createGUI();//execute gui
            this.setVisible(false);
        }

    }

    //Method used to create First Class seats
    public void createFirstClassSeats(JButton[] seats) {
        //Add first class seats here
        int X1 = 0;
        int Y1 = 0;
        int counter = 1;
        for (int i = 0; i < FIRST_CLASS_SEATS; i++) {

            if (counter % 3 == 0) {
                Y1 = Y1 + 40;
                X1 = 0;
                counter = 1;
            }
            seats[i].setBounds(270 + X1, 370 + Y1, 50, 25);
            seats[i].setBackground(Color.GREEN);
            container.add(seats[i]);
            X1 = X1 + 70;
            counter++;
        }
    }

    //Method used to create premium seats
    public void createPrimiumSeats(JButton[] seats) {
        //Add premium seats here
        int X2 = 0;
        int Y2 = 0;
        int counter2 = 1;
        for (int j = 0; j < PREMIUM_SEATS; j++) {

            if (counter2 % 11 == 0) {
                Y2 = Y2 + 30;
                X2 = 0;
                counter2 = 1;
            }
            int index = j + FIRST_CLASS_SEATS;
            seats[index].setBounds(14 + X2, 510 + Y2, 50, 25);

            container.add(seats[index]);
            X2 = X2 + 70;
            counter2++;

        }
    }

    //Method used to create buttons
    public void initializeSeats(JButton[] seats) {
        for (int i = 0; i < seats.length; i++) {

            seats[i] = new JButton(i + 1 + "");//create buttons
            seats[i].setBackground(Color.GREEN);
        }
    }

    //Method used to create label with font
    public static JLabel createLable(String text, int size) {

        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, size));

        return label;
    }

    public static void main(String[] args) {
        FlightGUI gui = new FlightGUI();
        gui.viewGUI();
    }
}
