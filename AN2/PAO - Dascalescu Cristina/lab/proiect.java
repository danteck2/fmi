//Laborant Grosu Andrei
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SimpleCalculator {

	public static void main(String[] args) {

		// 1. Create a panel
		JPanel windowContent= new JPanel();
		
		// 2. Set a layout manager for this panel
		FlowLayout fl = new FlowLayout(); 
		windowContent.setLayout(fl);
		
		// 3. Create controls in memory 
		JLabel label1 = new JLabel("Number 1:");
		JTextField field1 = new JTextField(10);
		JLabel label2 = new JLabel("Number 2:");
		JTextField field2 = new JTextField(10);
		JLabel label3 = new JLabel("Sum:");
		JTextField result = new JTextField(10);
		JButton go = new JButton("Add");
		 
		// 4. Add controls to the panel
		windowContent.add(label1); 
		windowContent.add(field1);
		windowContent.add(label2);
		windowContent.add(field2);
		windowContent.add(label3);
		windowContent.add(result);
		windowContent.add(go);
		
		//5. Create the frame and add the panel to it
		JFrame frame = new JFrame("My First Calculator");
		
		frame.setResizable(false);
		// 6. Add the panel to top-level container
		frame.setContentPane(windowContent);
		
		// 7. set the size and make the window visible!
		frame.setSize(400,100);
		frame.setVisible(true);
	}

}
