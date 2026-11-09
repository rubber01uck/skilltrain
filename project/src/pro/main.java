package pro;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.imageio.ImageIO;

public class main extends JFrame{
	
	JLabel p1=new JLabel();
	JLabel back=new JLabel(new ImageIcon(new ImageIcon("./datafiles/main.png").getImage().getScaledInstance(780, 460, Image.SCALE_SMOOTH)));
	JLabel icon=new JLabel();
	JLabel pp=new JLabel();
	JLabel time=new JLabel();
	JLabel start=new JLabel("출발지");
	JLabel end=new JLabel("도착지");
	JTextField tf1=new JTextField();
	JTextField tf2=new JTextField();
	JPanel p=new JPanel(new GridLayout(2,2,-80,20));
	JButton bt1=new JButton();
	
	JButton bt2=new JButton();
	JButton bt3=new JButton();
	
	Timer t;
	
	ui u=new ui();
	ui2 u2=new ui2();
	
	public main () {
		setLayout(null);
		p.setOpaque(false);
		
		p1=new JLabel() {
			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
				Graphics2D g2=(Graphics2D)g.create();
				g2.setColor(new Color(255,255,255,150));
				g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
				g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
			}
		};
		
		pp=new JLabel() {
			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
				Graphics2D g2=(Graphics2D)g.create();
				g2.setColor(new Color(255,255,255,200));
				g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
				g2.setColor(Color.lightGray);
				g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
			}
		};
		
		bt1=new JButton("경로검색") {
			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				Graphics2D g2=(Graphics2D)g.create();
				g2.setColor(Color.blue);
				g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
				g2.setColor(Color.black);
				g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
				super.paintComponent(g);
			}
		};
		bt1.setForeground(Color.white);
		bt1.setContentAreaFilled(false);
		bt1.setBorder(null);
		
		ImageIcon img=new ImageIcon("./datafiles/icon/login.png");
		Image icon1=img.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon log=new ImageIcon(icon1);
		
		bt2=new JButton("로그인", log) {
			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				Graphics2D g2=(Graphics2D)g.create();
				g2.setColor(Color.blue);
				g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
				g2.setColor(Color.black);
				g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
				super.paintComponent(g);
			}
		};
		bt2.setBorder(null);
		bt2.setContentAreaFilled(false);
		bt2.setForeground(Color.white);
		
		ImageIcon img1=new ImageIcon("./datafiles/icon/user.png");
		Image icon2=img1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon log2=new ImageIcon(icon2);
		
		bt3=new JButton("마이페이지", log2) {
			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				Graphics2D g2=(Graphics2D)g.create();
				g2.setColor(Color.blue);
				g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
				g2.setColor(Color.black);
				g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
				super.paintComponent(g);
			}
		};
		bt3.setBorder(null);
		bt3.setContentAreaFilled(false);
		bt3.setForeground(Color.white);
		
		icon.setIcon(new ImageIcon(colorimage(msg.logo, 170, 50)));
		time.setFont(new Font(null,1,15));
		
		back.setBounds(0, 0, 790, 460);
		p1.setBounds(70, 100, 640, 280);
		icon.setBounds(20, 10, 170, 50);
		pp.setBounds(20, 70, 250, 200);
		time.setBounds(20, 10, 250, 30);
		p.setBounds(30, 50, 190, 80);
		bt1.setBounds(30, 140, 190, 40);
		bt2.setBounds(320, 70, 140, 200);
		bt3.setBounds(480, 70, 140, 200);
		
		set();
		time();
		
		p1.add(icon);
		p1.add(pp);
		p1.add(bt2);
		p1.add(bt3);
		pp.add(time);
		pp.add(p);
		pp.add(bt1);
		p.add(start);
		p.add(tf1);
		p.add(end);
		p.add(tf2);
		back.add(p1);
		add(back);
		
		setVisible(true);
		setTitle("메인");
		setIconImage(logo.img());
		setSize(800,500);
		
		bt1.addActionListener((e) -> {
			if(login.uno==0) {
				msg.err("로그인이 되어있지 않습니다.");
				dispose();
				new login();
				return;
			}
			
			if(tf1.getText().equals("")||tf2.getText().equals("")) {
				dispose();
//				new search();
				return;
			}
			
			try {
				u.rs=u.st.executeQuery("select sno from station where name='"+tf1.getText()+"';");
				boolean text1=u.rs.next();
				u2.rs=u2.st.executeQuery("select sno from station where name='"+tf2.getText()+"';");
				boolean text2=u2.rs.next();
				
				if(text1&&text2) {
					dispose();
//					new search();
				}else {
					msg.err("역명을 확인해주세요.");
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		});
		
		bt2.addActionListener((e) -> {
			if(login.uno==0) {
				dispose();
				new login();
			}else {
				msg.info("로그아웃되었습니다.");
				login.uno=0;
				set();
			}
		});
		
		bt3.addActionListener((e) -> {
			dispose();
//			new my();
		});
		
	}
	
	void time() {
		Timer t=new Timer(1000,e -> {
			SimpleDateFormat s=new SimpleDateFormat("HH:mm:ss");
			time.setText("현재시간 :"+s.format(new Date()));
		}); t.start();
	}
	
	void set() {
		if(login.uno==0) {
			bt2.setText("로그인");
			bt3.setEnabled(false);
		}else {
			bt2.setText("로그아웃");
			bt3.setEnabled(true);
		}
	}
	
	BufferedImage colorimage (String path, int w ,int h) {
		try {
			BufferedImage img=new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2=img.createGraphics();
			g2.drawImage(ImageIO.read(new File(path)), 0, 0, w, h, this);
			g2.dispose();
			
			for (int y = 0; y < h; y++) {
				for (int x = 0; x < w; x++) {
					int c=img.getRGB(x, y);
					if((c&0x00F0F0F0)==0x00F0F0F0) {
						img.setRGB(x, y, 0);
					}
				}
			}
			return img;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new main();
	}

}
