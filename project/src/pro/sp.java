package pro;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

public class sp extends JSpinner{
	public sp(SpinnerNumberModel snm) {
		try {
			setModel(snm);
		} catch (Exception e) {
			// TODO: handle exception
		}
		setEditor(new JSpinner.NumberEditor(this,"#"));
		JTextField tf = ((DefaultEditor) this.getEditor()).getTextField();
		tf.setFont(new Font("", Font.BOLD, 20));
		tf.setHorizontalAlignment(JTextField.CENTER);
		tf.setForeground(new Color(30, 90, 200));
		tf.setBackground(Color.white);
		tf.setEditable(false);
		setBorder(new LineBorder(Color.blue));
	}
}
