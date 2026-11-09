package pro;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;

public class schedule extends JFrame{

	static int start=43;
	static int end=9;
	static int take=48;
	String sname="";
	String ename="";
	
	JLabel lb1=new JLabel(new ImageIcon(new ImageIcon("./datafiles/logo.png").getImage().getScaledInstance(100, 40, 4)),JLabel.LEFT);
	
	JButton bt1=new JButton();
	
	static String time="0900";
	static String date="2026-11-08";

	JLabel lb2=new JLabel(date+"/"+time);
	JLabel lb3=new JLabel("<html>서구청 > 가정 > 가정중앙시장 > 석남 > [I2-I7환승] > 석남 > 신곡 > 부평구청 > [I7-I1환승] > 푸평구청 > 갈산 > 작전 > 경인교대입구");
	
	JScrollPane jsp=new JScrollPane();
	JPanel all=new JPanel(null);
	JPanel[] p1=new JPanel[999];
	JLabel[] a1=new JLabel[999];
	JLabel[] a2=new JLabel[999];
	JLabel[] a3=new JLabel[999];
	
	ui u=new ui();
	
	public schedule() {
		setLayout(null);
		
		try {
			u.rs=u.st.executeQuery("select name from station where sno='"+start+"';");
			u.rs.next();
			sname=u.rs.getString(1);
			u.rs=u.st.executeQuery("select name from station where sno='"+end+"';");
			u.rs.next();
			ename=u.rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lb1.setText(sname+" → "+ename);
		lb1.setFont(lb1.getFont().deriveFont((float) 15.0));
		
		bt1=new JButton("노션변경") {
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
		
		jsp.setBorder(null);
		jsp.setBorder(new MatteBorder(1,0,0,0,Color.LIGHT_GRAY));
		
		lb1.setBounds(0,10,385,40);
		bt1.setBounds(0,50,100,20);
		lb2.setBounds(110,50,300,20);
		lb3.setBounds(10,70,360,50);
		jsp.setBounds(5,120,425,450);
		
		set();
		
		add(lb1);
		add(bt1);
		add(lb2);
		add(lb3);
		add(jsp);
		
		setVisible(true);
		setTitle("노선 스케줄");
		setSize(450,600);
		setIconImage(logo.img());
		getContentPane().setBackground(Color.white);
	}
	
	void set() {
		int hour=Integer.parseInt(time.substring(0,2));
		String tt=time;
		all.removeAll();
		all.setLayout(null);
		all.setBackground(Color.white);
		// 1시간에 4번, 23시까지 운행 => (23-hour) * 4 => 22시 45분 출발까지 
		// 23시에는 1번만 운행하므로 => 1
		for (int i=0; i < (23-hour)*4+1; i++) {
			p1[i]=new JPanel(null) {
				@Override
				public void paint(Graphics g) {
					// TODO Auto-generated method stub
					super.paint(g);
					g.setColor(Color.lightGray);
					g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
					g.setClip(new java.awt.geom.RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 18, 18));
					g.setColor(Color.blue);
					g.fillRect(0, 0, 5, getHeight());
				}
			};
			p1[i].setBackground(Color.white);
			
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
			DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");
			LocalTime lt = LocalTime.parse(tt, timeFormatter);
			LocalTime nt = lt.plusMinutes(15);
			tt=nt.format(timeFormatter);
			
			a1[i]=new JLabel("<html>"+sname+"<br><font color=blue>"+lt.format(tf)+"</font>");
			a1[i].setIcon(new ImageIcon(new ImageIcon("./datafiles/icon/trains.png").getImage().getScaledInstance(60, 40, 4)));
			a1[i].setFont(a1[i].getFont().deriveFont((float)18.0));
			a1[i].setHorizontalTextPosition(JLabel.CENTER);
			a1[i].setVerticalTextPosition(JLabel.TOP);
			
			a2[i]=new JLabel("<html><center><p style='background-color:orange; color:white;'>[I2→I7]→[I7→I1]</p><br><font color=blue>→</font><br>"+take+"분 소요</center>");
			
			LocalTime arrive = lt.plusMinutes(take);
			a3[i]=new JLabel("<html>"+ename+"<br><font color=blue>"+arrive.format(tf)+"</font>");
			a3[i].setFont(a3[i].getFont().deriveFont((float)18.0));
			a3[i].setVerticalAlignment(JLabel.TOP);
			
			p1[i].setBounds(5,i*110, 415, 100);
			a1[i].setBounds(30,0,130, 100);
			a2[i].setBounds(160,0,100, 100);
			a3[i].setBounds(260,0,180, 100);
			
			all.add(p1[i]);
			p1[i].add(a1[i]);
			p1[i].add(a2[i]);
			p1[i].add(a3[i]);
		}
		jsp.setViewportView(all);
		all.setPreferredSize(new Dimension(0,((23-hour)*4+1)*110));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new schedule();
	}
}
