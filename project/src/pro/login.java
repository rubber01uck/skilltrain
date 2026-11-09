package pro;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class login extends JFrame{
	
	static int uno=0;
	static int my=0;

	JPanel p=new JPanel();
	JLabel lb=new JLabel("LOGIN", JLabel.CENTER);
	
	JLabel user=new JLabel(new ImageIcon(new ImageIcon("./datafiles/icon/user.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH)));
	JLabel lock=new JLabel(new ImageIcon(new ImageIcon("./datafiles/icon/lock.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH)));
	
	JTextField tf1=new JTextField();
	JPasswordField pw1=new JPasswordField();
	
	JButton bt1=new JButton();
	
	ui u=new ui();
	
	public login () {
		setLayout(null);
		lb.setOpaque(true);
		
		tf1.setText("ID");
		tf1.setForeground(Color.LIGHT_GRAY);
		
		tf1.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(tf1.getText().equals("")) {
					tf1.setText("ID");
					tf1.setForeground(Color.LIGHT_GRAY);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(tf1.getText().equals("ID")) {
					tf1.setText("");
					tf1.setForeground(Color.black);
				}
			}
		});
		
		pw1.setText("PW");
		pw1.setForeground(Color.lightGray);
		pw1.setEchoChar((char)0);
		
		pw1.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(new String(pw1.getPassword()).equals("")) {
					pw1.setEchoChar((char)0);
					pw1.setText("PW");
					pw1.setForeground(Color.LIGHT_GRAY);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(new String(pw1.getPassword()).equals("PW")) {
					pw1.setEchoChar(' ');
					pw1.setText("");
					pw1.setForeground(Color.black);
				}
			}
		});
		
		bt1=new JButton("로그인") {
			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				g.setColor(Color.blue);
				g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
				g.setColor(Color.black);
				g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
				super.paintComponent(g);
			}
		};
		bt1.setForeground(Color.white);
		bt1.setBorder(null);
		bt1.setFocusPainted(false);
		bt1.setContentAreaFilled(false);
		
		p.setBackground(Color.black);
		lb.setFont(new Font(null,1,25));
		lb.setBackground(Color.white);
		
		lb.setBounds(200, 20, 80, 30);
		p.setBounds(10, 30, 465, 2);
		user.setBounds(50, 80, 35,35);
		lock.setBounds(50, 140, 35, 35);
		tf1.setBounds(110, 80, 320, 35);
		pw1.setBounds(110, 140, 320, 35);
		bt1.setBounds(110, 200, 320, 35);
		
		add(lb);
		add(p);
		add(user);
		add(lock);
		add(tf1);
		add(pw1);
		add(bt1);
		
		setVisible(true);
		setTitle("로그인");
		setSize(500,300);
		getContentPane().setBackground(Color.white);
		setIconImage(new ImageIcon(logo.img()).getImage());
		
		bt1.addActionListener((e) -> {
			String pwd=new String(pw1.getPassword());
			try {
				u.rs=u.st.executeQuery("select uno, name from user where id='"+tf1.getText()+"' and pw='"+pwd+"';");
				if(tf1.getText().equals("")||pwd.equals("")) {
					msg.err("빈칸이 존재합니다.");
					return;
				}
				
				if(tf1.getText().equals("admin")&&pwd.equals("1234")){
					msg.info("관리자님 환영합니다.");
					dispose();
					return;
				}
				
				if(u.rs.next()) {
					msg.info(u.rs.getString(2)+"님 환영합니다.");
					if(my==0) {
						uno=u.rs.getInt(1);
						dispose();
						new main();
					}else if(my==1) {
						dispose();
//						new search();
					}
				}else {
					msg.err("해당 유저가 존재하지 않습니다.");
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				login.uno=0;
				new main();
			}
		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new login();
	}
}
