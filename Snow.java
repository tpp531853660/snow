package com.langsin.gui.snow;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.awt.AWTUtilities;

public class Snow {
	
	
	public static void main(String[] args) throws Exception {
		final Image image = ImageIO.read(new File("snow.png"));
		final JFrame jf = new JFrame();
		jf.setSize(200, 300);
		jf.setAlwaysOnTop(true);
		jf.setUndecorated(true);
		jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AWTUtilities.setWindowOpaque(jf, false);
		
		final JPanel pane = new JPanel(){
			Random r = new Random();
			int count = 30;
			int[] snowX = null;
			int[] snowY = null;
			int[] angels = null;
			@Override
			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				Rectangle bounds = jf.getBounds();
				if(snowX == null){
					snowX = new int[count];
					for (int i = 0; i < count; i++) {
						snowX[i] = r.nextInt(bounds.width);
					}
					
					snowY = new int[count];
					for (int i = 0; i < count; i++) {
						snowY[i] = r.nextInt(bounds.height);
					}
					
					angels = new int[count];
					for (int i = 0; i < count; i++) {
						angels[i] = r.nextInt(360);
					}
				}
				
				//改变雪花的坐标和角度  
				for (int i = 0; i < count; i++) {
					snowX[i]+=r.nextInt(7)-3;
					snowY[i]+=r.nextInt(7)-3;
					angels[i] += i/5;
					int x = snowX[i];
					int y = snowY[i];
					int angel = angels[i];
					double radian = Math.toRadians(angel);
					
					g2.translate(x, y);
					g2.rotate(radian);
					g2.drawImage(image, 0, 0, null);
					g2.rotate(-radian);
					g2.translate(-x, -y);
				}
			}
		};
		
		jf.setContentPane(pane);
		jf.setVisible(true);
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					pane.repaint();
				}
			}
		}).start();
		
	}

}
