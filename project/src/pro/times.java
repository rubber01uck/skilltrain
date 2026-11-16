package pro;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.*;

public class times extends JDialog {

    public static LocalTime selectedTime = null;
    public static LocalDate selectedDate = null;

    JSpinner sh, sm, sy, smo, sd;
    JButton b1;
    JLabel lp;
    
    class p extends JPanel {
        protected void paintComponent(Graphics g) {
        	Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(245,248,255));
            g2.fillRoundRect(0,0, getWidth(), getHeight(), 30, 30);
            g2.setColor(new Color(200, 210, 230));
            g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 30, 30);
        }
    }

    LocalTime near() {
        LocalTime n = LocalTime.now();
        int h = n.getHour();
        // 0분, 15분, 30분, 45분의 경우에는 해당 시각 이후
        // 1분 이후, 16분 이후, 31분 이후, 46분 이후에는 다음 간격 시각 이후
        int m = ((n.getMinute() / 15) + (n.getMinute() % 15==0?0:1))*15;
        if (m >= 60) { m = 0; h++; }
        if (h > 23) { h = 23; m = 0; }
        if (h < 6) { h = 6; m = 0; }
        return LocalTime.of(h, m);
    }

    int maxDay(int y, int mo) {
        return LocalDate.of(y, mo, 1).lengthOfMonth();
    }

    public times() {
        setLayout(null);
        setTitle("날짜/시간 설정");
        setSize(416, 480);
        setIconImage(logo.img());
        getContentPane().setBackground(Color.white);
        setVisible(true);
        
        // 조건에는 없지만 스케쥴 폼 옆에 띄우기
        setLocation(getParent().getX()+getWidth()+30, getParent().getY());

        JLabel t1 = new JLabel("출발 날짜 / 시간 설정", JLabel.CENTER);
        t1.setFont(new Font("", Font.BOLD, 17));
        t1.setForeground(new Color(30, 90, 200));
        t1.setBounds(0, 18, 416, 28);
        add(t1);

        JLabel t2 = new JLabel("조회할 날짜와 출발 시간을 선택하세요", JLabel.CENTER);
        t2.setFont(new Font("", Font.PLAIN, 12));
        t2.setForeground(Color.GRAY);
        t2.setBounds(0, 50, 416, 20);
        add(t2);

        JPanel sep = new JPanel();
        sep.setBackground(Color.LIGHT_GRAY);
        sep.setBounds(20, 75, 376, 1);
        add(sep);

        JPanel p1=new p();
        p1.setOpaque(false);
        p1.setLayout(null);
        p1.setBounds(30, 85, 356, 110);
        add(p1);
        
        JLabel yl = new JLabel("년", JLabel.CENTER);
        yl.setFont(new Font("", Font.BOLD, 13));
        yl.setForeground(new Color(30, 90, 200));
        yl.setBounds(10, 28, 110, 18);
        p1.add(yl);

        JLabel ml = new JLabel("월", JLabel.CENTER);
        ml.setFont(new Font("", Font.BOLD, 13));
        ml.setForeground(new Color(30, 90, 200));
        ml.setBounds(130, 28, 90, 18);
        p1.add(ml);

        JLabel dl = new JLabel("일", JLabel.CENTER);
        dl.setFont(new Font("", Font.BOLD, 13));
        dl.setForeground(new Color(30, 90, 200));
        dl.setBounds(235, 28, 90, 18);
        p1.add(dl);

        LocalDate id = (selectedDate != null) ? selectedDate : LocalDate.now();

//        new SpinnerNumberModel(초기값, 최소값, 최대값, 증가값(간격))
        sy = new sp(new SpinnerNumberModel(id.getYear(), 2024, 2030, 1));
        sy.setBounds(10, 48, 110, 50);
        p1.add(sy);

        smo = new sp(new SpinnerNumberModel(id.getMonthValue(), 1, 12, 1));
        smo.setBounds(130, 48, 90, 50);
        p1.add(smo);

        int md = maxDay(id.getYear(), id.getMonthValue());
        sd = new sp(new SpinnerNumberModel(Math.min(id.getDayOfMonth(), md), 1, md, 1));
        sd.setBounds(235, 48, 90, 50);
        p1.add(sd);

        JPanel p2=new p();
        p2.setOpaque(false);
        p2.setLayout(null);
        p2.setBounds(30, 205, 356, 110);
        add(p2);

        JLabel hl = new JLabel("시", JLabel.CENTER);
        hl.setFont(new Font("", Font.BOLD, 13));
        hl.setForeground(new Color(30, 90, 200));
        hl.setBounds(60, 28, 80, 18);
        p2.add(hl);

        JLabel cl = new JLabel(":", JLabel.CENTER);
        cl.setFont(new Font("", Font.BOLD, 36));
        cl.setForeground(new Color(30, 90, 200));
        cl.setBounds(158, 38, 40, 50);
        p2.add(cl);

        JLabel mnl = new JLabel("분", JLabel.CENTER);
        mnl.setFont(new Font("", Font.BOLD, 13));
        mnl.setForeground(new Color(30, 90, 200));
        mnl.setBounds(216, 28, 80, 18);
        p2.add(mnl);

        LocalTime it = (selectedTime != null) ? selectedTime : near();
        int ih=it.getHour();
        int im=it.getMinute();

        sh = new sp(new SpinnerNumberModel(ih, 6, 23, 1));
        sh.setBounds(30, 45, 120, 55);
        p2.add(sh);

        // 조건에는 없는 것 같긴 한데 15분 간격으로만 조정 가능하게
        sm=new sp(new SpinnerNumberModel(im,0,45,15));
        sm.setBounds(186, 45, 120, 55);
        p2.add(sm);

        lp = new JLabel(preview(), JLabel.CENTER);
        lp.setFont(new Font("", Font.BOLD, 13));
        lp.setForeground(new Color(50, 50, 50));
        lp.setBounds(0, 325, 416, 25);
        add(lp);

        javax.swing.event.ChangeListener ch = e -> {
            lp.setText(preview());
        };
        sy.addChangeListener(ch); smo.addChangeListener(ch); sd.addChangeListener(ch);
        sh.addChangeListener(ch); sm.addChangeListener(ch);

        b1 = new JButton("확인");
        b1.setBounds(100, 360, 160, 40);
        add(b1);

        b1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	LocalDate td = LocalDate.now();
            	LocalTime nt = near();
            	sy.setValue(td.getYear());
            	smo.setValue(td.getMonthValue());
            	sd.setValue(td.getDayOfMonth());
            	sh.setValue(Math.max(6, Math.min(23, nt.getHour())));
            	sm.setValue(Math.min((nt.getMinute() / 15) * 15, 45));
            	lp.setText(preview());
            	
                selectedDate = LocalDate.of((Integer) sy.getValue(), (Integer) smo.getValue(), (Integer) sd.getValue());
                selectedTime = LocalTime.of((Integer) sh.getValue(), (Integer) sm.getValue());
                dispose();
            }
        });

        JLabel note = new JLabel("시간은 06:00 ~ 23:00 범위에서 선택 가능합니다", JLabel.CENTER);
        note.setFont(new Font("", Font.PLAIN, 11));
        note.setForeground(new Color(160, 160, 160));
        note.setBounds(0, 410, 416, 20);
        add(note);
        
        revalidate();
        repaint();
    }

    String preview() {
        try {
            return String.format("%04d-%02d-%02d / %02d:%02d 이후 열차 조회",
                    (Integer) sy.getValue(), (Integer) smo.getValue(), (Integer) sd.getValue(),
                    (Integer) sh.getValue(), (Integer) sm.getValue());
        } catch (Exception e) {
            return "";
        }
    }
    public static void main(String[] args) {
    	// TODO Auto-generated method stub
    	new schedule();
    }
}