
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * Organize/design and developed by ExteamDev team
 * Date: 10-09-2019
 * @author ExteamDev
 * More info: techexteam@gmail.com
 */
public class UpdateGUI extends JFrame implements ActionListener {

    //Instance variable
    private static final int FIRST_CLASS_SEATS = 6;
    private static final int PREMIUM_SEATS = 40;
    private String[] combobox = null;
    private JComboBox flights = null;
    private List<Boarding> boarding = null;
    private Boarding user = null;
    private List<Flight> flightsList = null;
    private Flight current_flight = null;
    private JPanel panel = null;
    private JButton[] airplanSelectedSeats = null;
    private JButton update = null;
    private JButton pass = null;
    private JButton back = null;
    private JLabel label = null;
    private int selected_index = 0;
    private String user_seats = "", flight1_seats = "", flight2_seats = "", flight3_seats = "";
    private String choosen_flight = "", selected = "", booked_seats = "";
    
    public UpdateGUI() {
    }

    //Constructor
    public UpdateGUI(List<Boarding> b, List<Flight> list, Boarding u) {
        this.boarding = b;
        this.flightsList = list;
        this.user = u;
        airplanSelectedSeats = new JButton[46];//button array
        combobox = new String[]{flightsList.get(0).getName(), flightsList.get(1).getName(),
            flightsList.get(2).getName()};
        flights = new JComboBox(combobox);
        update = new JButton("Update");
        pass = new JButton("Get Boarding Pass");
        back = new JButton("Main Menu");
        
    }
    
    public void viewGUI() {
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//set frame to exit on close
        setResizable(false);//don't resize frame
        setTitle("Update Window");//GUI title
        label = createLable("", 12);
        JLabel first_class = createLable("First-Class Seats ", 14);
        JLabel choose_flight = createLable("Select Flight: ", 14);
        JLabel premium = createLable("Premium Seats ", 14);
        flights.setSelectedIndex(getSelectedSeatIndex(user.getFlight_number()));
        choosen_flight = user.getFlight_number();
        selected = user.getFlight_number();
        airplanSelectedSeats = getSelectedSeat(choosen_flight);
        user_seats = user.getSeats();//reset seats
        // displayUserSeats(user_seats); 
        current_flight = getFlight(choosen_flight);
        panel = new JPanel();
        panel.setLayout(null);
        label.setBounds(220, 10, 700, 35);
        back.setBounds(10, 10, 130, 25);
        choose_flight.setBounds(140, 70, 200, 25);
        flights.setBounds(240, 70, 170, 25);
        first_class.setBounds(290, 320, 150, 25);
        premium.setBounds(290, 480, 150, 25);
        update.setBounds(540, 655, 150, 25);
        pass.setBounds(15, 655, 180, 25);
        panel.add(label);
        panel.add(choose_flight);
        panel.add(first_class);
        panel.add(premium);
        panel.add(update);
        panel.add(flights);
        panel.add(back);
        panel.add(pass);

        //Add action listener
        clear();
        addArrayListeners(airplanSelectedSeats);
        update.addActionListener(this);
        pass.addActionListener(this);
        back.addActionListener(this);
        createFirstClassSeats(airplanSelectedSeats);
        createPrimiumSeats(airplanSelectedSeats);
        booked_seats = current_flight.getBooked_seat();
        updateSeats(booked_seats);
        updateUserSeats(user_seats);
        // displayUserSeats(user.getSeats());

        //combobox action listener
        flights.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JComboBox comboBox = (JComboBox) event.getSource();
                selected = (String) comboBox.getSelectedItem();

                //Update user and flight seats
                user_seats = user.getSeats();
                selected_index = getSelectedSeatIndex(selected);
                //displayUserSeats(user_seats);
                booked_seats = flightsList.get(selected_index).getBooked_seat();
                switch (selected_index) {
                    case 0:
                        flight1_seats = flightsList.get(0).getBooked_seat();
                        if (choosen_flight.equals(selected)) {
                            updateSeats(flight1_seats);
                            updateUserSeats(user.getSeats());
                        } else {
                            updateSeats(flight1_seats);
                        }
                        
                        break;
                    
                    case 1:
                        flight3_seats = flightsList.get(1).getBooked_seat();
                        if (choosen_flight.equals(selected)) {
                            updateSeats(flight3_seats);
                            updateUserSeats(user.getSeats());
                        } else {
                            updateSeats(flight1_seats);
                        }
                        break;
                    case 2:
                        flight2_seats = flightsList.get(2).getBooked_seat();
                        if (choosen_flight.equals(selected)) {
                            updateSeats(flight2_seats);
                            updateUserSeats(user.getSeats());
                        } else {
                            updateSeats(flight1_seats);
                        }
                        break;
                    default:
                        break;
                }
                
            }
        });

        //button colors
        pass.setBackground(Color.gray);
        back.setBackground(Color.gray);
        update.setBackground(Color.gray);
        panel.setBackground(Color.ORANGE);
        add(panel);
        setSize(730, 720); //size of frame
        setVisible(true);//visibility true

    }

    //Clear all seats
    public void clear() {
        for (JButton btn : airplanSelectedSeats) {
            for (ActionListener al : btn.getActionListeners()) {
                btn.removeActionListener(al);
            }
        }
    }

    //Search user
    public int searchUser() {
        int index = 0;
        for (int i = 0; i < boarding.size(); i++) {
            
            if (boarding.get(i).getLast_name().equals(user.getLast_name())) {
                index = i;
                break;
            }
        }
        
        return index;
    }


    //Get current flight from flight list
    public Flight getFlight(String str) {
        Flight flight = null;
        for (Flight f : flightsList) {
            if (f.getName().equals(str)) {
                flight = f;
                break;
            }
        }
        
        return flight;
    }

    //Method used to check total seats selected by user
    public int totalSeats() {
        
        return user_seats.split("\\s+").length;
    }

    //Check for update seats
    public boolean isUpdate(String u_seat, String str) {
        boolean update = false;
        
        for (String s : u_seat.split("\\s+")) { //iterate over selected seats
            if (s.equals(str)) {
                update = true;
                break;
            }
        }
        
        return update;
    }

    //Method used to display all flight seats
    public void addArrayListeners(JButton[] mySeats) {
        //Action Lister for Button Array
        for (int i = 0; i < mySeats.length; i++) { //iterate

            mySeats[i].addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent ap) {
                    
                    if (totalSeats() == 5) { //maximum we can select 5 seats at a time
                        JOptionPane.showMessageDialog(null, "Sorry you already select five seats!");
                    } else {
                        JButton button = (JButton) ap.getSource();
                        
                        if (choosen_flight.equals(selected)) { //if same flight

                            //Check selected seat with user seats
                            if (isUpdate(user_seats, button.getText())) {
                                
                                int option = JOptionPane.showConfirmDialog(null, "Do you want to remove this seat?", "Remove Seats", JOptionPane.YES_NO_OPTION);
                                if (option == JOptionPane.YES_OPTION) { //select seats
                                    System.out.println("Users seat : " + user_seats + ", Booked Seat: " + booked_seats);
                                    user_seats = removeUserSeat(button.getText());
                                    booked_seats = removeFlightSeat(button.getText());
                                    System.out.println("Users seat : " + user_seats + ", Booked Seat: " + booked_seats);
                                    JOptionPane.showMessageDialog(null, "" + button.getText() + " is removed");
                                    //Update seats
                                    airplanSelectedSeats[Integer.parseInt(button.getText()) - 1].setBackground(Color.GREEN);
                                    airplanSelectedSeats[Integer.parseInt(button.getText()) - 1].setEnabled(true);
                                    //updateSeats(booked_seats);
                                    //updateUserSeats(user_seats);
                                } else {
                                    System.out.println("No!!!");
                                }
                            } else {

                                //Select new seats for current flight number
                               
                                int opt = JOptionPane.showConfirmDialog(null, "Do you want to select this seat?", "Seats", JOptionPane.YES_NO_OPTION);
                                if (opt == JOptionPane.YES_OPTION) { //select seats
                                    JOptionPane.showMessageDialog(null, "" + button.getText() + " is selected");
                                    
                                    user_seats = user_seats + button.getText() + " ";//add user seats
                                    flightsList.get(selected_index).setBooked_seat(button.getText() + " ");//add flight seats
                                    
                                    booked_seats = flightsList.get(selected_index).getBooked_seat();
                                    updateSeats(booked_seats);
                                    updateUserSeats(user_seats);
                                    
                                } else {
                                    //...for cancel button
                                }
                            }
                        } else {
                            //For other flights
         
                            user_seats = "";//for new fligth seats
                            int opt = JOptionPane.showConfirmDialog(null, "Do you want to select this seat?", "Seats", JOptionPane.YES_NO_OPTION);
                            if (opt == JOptionPane.YES_OPTION) { //select seats
                                JOptionPane.showMessageDialog(null, "" + button.getText() + " is selected");
                                
                                user_seats = user_seats + button.getText() + " ";//add user seats
                                flightsList.get(selected_index).setBooked_seat(button.getText() + " ");//add flight seats
                                booked_seats = flightsList.get(selected_index).getBooked_seat();
                                updateSeats(booked_seats);
                                updateUserSeats(user_seats);
                                
                            } else {
                                //...for cancel button
                            }
                        }
                        
                    }
                    
                }
            });
        }
    }

    //Remove user seats
    public String removeUserSeat(String str) {
        String output = "";
        for (String out : user_seats.split("\\s+")) {
            if (out.equals(str)) {
                //..ignore the seat number
            } else {
                output = output + out + " ";
            }
        }
        
        return output;
    }

    //Remove flight seats
    public String removeFlightSeat(String str) {
        String output = "";
        for (String out : booked_seats.split("\\s+")) {
            if (out.equals(str)) {
                //..ignore the seat number
            } else {
                output = output + out + " ";
            }
        }
        
        return output;
    }

    //Button Action Listener
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getActionCommand().equals("Main Menu")) { //button listener
            
            FlightGUI flight_gui = new FlightGUI(flightsList, boarding);
            flight_gui.viewGUI();
            this.setVisible(false);
            //Back to main menu
        } else if (ae.getActionCommand().equals("Update")) {

            //Update seats & flights
            int index = searchUser();
            boarding.get(index).setFlight_number(selected);
            boarding.get(index).setSeats(user_seats);
            JOptionPane.showMessageDialog(null, "" + "Record update");
            
        } else if (ae.getActionCommand().equals("Get Boarding Pass")) {
            JOptionPane.showMessageDialog(null, user.toString());
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
            //seats[i].setBackground(Color.GREEN);
            panel.add(seats[i]);
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
            panel.add(seats[index]);
            X2 = X2 + 70;
            counter2++;
            
        }
    }

    //Method used to get seat plan from selected flight
    public JButton[] getSelectedSeat(String selected) {
        JButton[] array = null;
        
        for (Flight button : flightsList) {
            
            if (selected.trim().equals(button.getName())) {
                array = button.getSeats();
                break; //terminate loop
            }
        }
        
        return array;
        
    }

    //Method used to update seat color
    public void updateSeats(String b_seats) {
        
        String[] all_booked_seats = b_seats.split("\\s+");
        if (b_seats.isEmpty()) {
            refreshSeats();
        } else { //update seats
            for (int i = 0; i < all_booked_seats.length; i++) {
                
                int seat_numer = Integer.parseInt(all_booked_seats[i]);
                airplanSelectedSeats[seat_numer - 1].setBackground(Color.RED);
                airplanSelectedSeats[seat_numer - 1].setEnabled(false);
            }
        }
        
    }

    //Method used to update user seat with blue color
    public void updateUserSeats(String b_seats) {
        
        String[] all_user_seats = b_seats.split("\\s+");
        if (b_seats.isEmpty()) {
            refreshSeats();
        } else { //update seats
            for (int i = 0; i < all_user_seats.length; i++) {
                
                int seat_numer = Integer.parseInt(all_user_seats[i]);
                airplanSelectedSeats[seat_numer - 1].setBackground(Color.BLUE);
                airplanSelectedSeats[seat_numer - 1].setEnabled(true);
            }
        }
        
    }

    //Method used to refresh the seats
    public void refreshSeats() {
        for (int i = 0; i < airplanSelectedSeats.length; i++) {
            airplanSelectedSeats[i].setBackground(Color.GREEN);
            airplanSelectedSeats[i].setEnabled(true);
        }
    }

    //Get index of seat selection
    public int getSelectedSeatIndex(String selected) {
        int index = 0;
        
        for (int i = 0; i < flightsList.size(); i++) {
            
            if (flightsList.get(i).getName().equals(selected.trim())) {
                index = i;
                break; //terminate loop
            }
        }
        
        return index;
    }

    //Method used to create label with font
    public static JLabel createLable(String text, int size) {
        
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, size));
        
        return label;
    }
    
}
