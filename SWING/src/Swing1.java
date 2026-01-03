
import javax.swing.*; //Importing Java Swing
import java.awt.FlowLayout; //Importing to access flowlayout

public class Swing1 {
    public static void main(String[] args) {

        //Creating a JFrame object using JFrame class.
        JFrame jf=new JFrame("User Form");

        //Using FlowLayout
        jf.setLayout(new FlowLayout()); //It is basically a center aligned layout in java.

        //Swing by default puts everything invisible
        jf.setVisible(true); //as a result it is used to make it visible. (false) will make it invisible.

        //To set size of Frame
        jf.setSize(800,400); //Without this the frame will use minimum space.

        //To create a label
        JLabel L1=new JLabel("Username:"); //L1

        //To add a component in the frame
        jf.add(L1); //It adds the label component in the frame

        //Creating and Adding a text field a Text field
        JTextField T1=new JTextField(20); //Here 20 represents size of characters that can be used.[T1]
        jf.add(T1);


        //Creating and Adding Label and textField for password in Frame.
        JLabel L2=new JLabel("Password:"); //L2
        jf.add(L2); //It adds the label component in the frame
        JTextField T2=new JTextField(20); //Here 20 represents size of characters that can be used.[T2]
        jf.add(T2);//Adds T2


        //Creating and adding a button
        JButton B=new JButton("Submit");
        jf.add(B);





    }
}
