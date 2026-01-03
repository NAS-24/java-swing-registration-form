import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class Registrationform extends JDialog {
    private JTextField tfName;
    private JTextField tfEmail;
    private JTextField tfPhone;
    private JTextField tfAddress;
    private JButton btnRegister;
    private JButton btncancel;
    private JPasswordField pfPassword;
    private JPasswordField pfConPassword;
    private JPanel RegPanel;

    public Registrationform(JFrame parent){
        super(parent);
        setTitle("Create a new account");
        setContentPane(RegPanel);
        setSize(450,474);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registeruser();
            }
        });
        btncancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    private void registeruser() {
        String Name = tfName.getText();
        String Email = tfEmail.getText();
        String Phone = tfPhone.getText();
        String Address = tfAddress.getText();
        String Password = String.valueOf(pfPassword.getPassword());
        String ConPassword = String.valueOf(pfConPassword.getPassword());

        if (Name.isEmpty() || Email.isEmpty() || Phone.isEmpty() || Address.isEmpty() || Password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "please enter all fields", "Try again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Password.equals(ConPassword)){
            JOptionPane.showMessageDialog(this, "Password does not match", "Try Again", JOptionPane.ERROR_MESSAGE);
        return;

    }

        user=addUserToDatabase(Name,Email,Phone,Address,Password);
        if(user!=null){
            dispose();
        }
        else{
            JOptionPane.showMessageDialog(this,"Failed to register new user","Try Again",JOptionPane.ERROR_MESSAGE);
        }
    }
    public User user;
    private User addUserToDatabase(String name, String email, String phone, String address, String password) {
        User user=null;
        final String DB_URL="jdbc:mysql://localhost:3306/myshop";
        final String USERNAME="root";
        final String Password="ARYAN";

        try{
            Connection con= DriverManager.getConnection(DB_URL,USERNAME,Password);

            Statement stmt=con.createStatement();
            String sql="INSERT INTO users(name,email,phone,address,password)"+"VALUES(?,?,?,?,?)";

            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,email);
            ps.setString(3,phone);
            ps.setString(4,address);
            ps.setString(5,password);

            int addedRows=ps.executeUpdate();
            if(addedRows>0){
                user=new User();
                user.Name=name;
                user.Email=email;
                user.Phone=phone;
                user.Address=address;
                user.Password=password;
            }

            stmt.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Try Again", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + e.getMessage(), "Try Again", JOptionPane.ERROR_MESSAGE);
        }


        return user;
   }

    public static void main(String[] args) {
        Registrationform reg=new Registrationform(null);
        User user=reg.user;
        if(user!=null){
            System.out.println("Successful Registration: "+user.Name);
        } else{
            System.out.println("Registration Canceled");
        }
    }
}
