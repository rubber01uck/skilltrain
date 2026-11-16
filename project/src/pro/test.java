package pro;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class test extends JFrame{
	
	sp sp1=new sp(null);
	
	public test () {
		setLayout(null);
		
	 JPanel p1=new JPanel(null) {
        	@Override
        	public void paint(Graphics g) {
        		// TODO Auto-generated method stub
        		super.paint(g);
        		Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//	                g2.setColor(new Color(245, 248, 255));
//	                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2.setColor(new Color(200, 210, 230));
                g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 30, 30);
                
                Area area = new Area(
                        new Rectangle2D.Double(0, 0, 356, 110));
                area.subtract(new Area(new RoundRectangle2D.Double(0, 0, 356, 110, 30, 30)));
                g.setColor(Color.white);
                ((Graphics2D) g).fill(area);
        	}
        };
        p1.setBackground(new Color(245, 248, 255));
        p1.setBounds(30, 85, 356, 110);
        add(p1);
        
        sp1=new sp(new SpinnerNumberModel(LocalDate.now().getYear(), 2024, 2030, 1));
		sp1.setBounds(20,20,100,30);
		p1.add(sp1);
		
		setVisible(true);
		setTitle("로그인");
		setSize(500,300);
		getContentPane().setBackground(Color.white);
		setIconImage(new ImageIcon(logo.img()).getImage());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new test();
	}
}
