
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Organize/design and developed by ExteamDev team
 * Date: 10-09-2019
 * @author ExteamDev
 * More info: techexteam@gmail.com
 */
public class UserGUI extends JFrame implements ActionListener {

    //Instance variables
    private JButton button;
    private JTextField confirm = null;
    private JTextField lastname = null;
    private JLabel output = null;
    private List<Boarding> boarding = null;
    private List<Flight> flights = null;

    //Default Constructor
    public UserGUI(List<Boarding> b, List<Flight> list) {
        this.boarding = b;
        this.flights = list;
    }

    public void createGUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//set frame to exit on close
        setResizable(false);//don't resize frame
        setTitle("Access Reservation");//GUI title
        JLabel label_confirm = new JLabel("Confirmation# :");
        JLabel last_name = new JLabel("LastName :");
        output = new JLabel("");
        label_confirm.setFont(new Font("SansSerif", Font.BOLD, 13));
        last_name.setFont(new Font("SansSerif", Font.BOLD, 13));
        output.setFont(new Font("SansSerif", Font.BOLD, 13));
        output.setBackground(Color.red);
        Container container = getContentPane();
        container.setLayout(null);
        button = new JButton("Submit");
        confirm = new JTextField();
        lastname = new JTextField();
        label_confirm.setBounds(20, 30, 260, 35);
        confirm.setBounds(140, 35, 180, 25);
        last_name.setBounds(20, 75, 260, 35);
        lastname.setBounds(140, 80, 180, 25);
        output.setBounds(140, 190, 250, 25);
        button.setBounds(239, 130, 80, 25);
        button.addActionListener(this);
        container.add(label_confirm);
        container.add(confirm);
        container.add(last_name);
        container.add(lastname);
        container.add(button);
        container.add(output);
        setSize(450, 300); //size of frame
        setVisible(true);//visibility true
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Submit")) { //button listener

            //Submit confirmation and lastname
            Boarding b = isValidUser(confirm.getText(), lastname.getText());
            if (b != null) {

                UpdateGUI gui = new UpdateGUI(boarding, flights, b);
                gui.viewGUI();
                this.setVisible(false);

            } else {
                output.setText("");
                output.setText("Invalid confirmation & lastname");
            }
        }
    }

    public Boarding isValidUser(String confirmation, String lastname) {
        Boarding user = null;
        for (Boarding b : boarding) {

            if (b.getConfirmation_number().equals(confirmation) && b.getLast_name().equals(lastname)) {
                user = b;
                break;
            }
        }

        return user;
    }

}
