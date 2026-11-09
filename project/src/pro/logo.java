package pro;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class logo {
	
	public static Image img() {
		try {
			BufferedImage bi=ImageIO.read(new File(msg.logo));
			return bi.getSubimage(0, 0, bi.getWidth()/3, bi.getHeight());
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
}
