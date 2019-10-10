package ball.frontend.gui.ball;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JLabel;

public class Ball extends JLabel implements Runnable {

	
	private static final long serialVersionUID = 109989082760202067L;
	
	private int movX, movY;
	private int maxX, maxY;
	private int diameter;
	private Container owner;
	
	private boolean stopped;
	
	public Ball(Container owner, int x, int y, int diameter) {
		this.setSize(new Dimension(diameter, diameter));
		this.setLocation(new Point(x - diameter / 2, y - diameter / 2));
		this.diameter = diameter;
		this.movX = (int) (Math.random() * 100) % 6 - 3;
		this.movY = (int) (Math.random() * 100) % 6 - 3;
		this.maxX = owner.getWidth();
		this.maxY = owner.getHeight();
		this.owner = owner;
		this.stopped = false;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillOval(0, 0, this.getWidth(), this.getHeight());
	}
	
	

	@Override
	public void run() {
		stopped = false;
		while(!stopped) {
			
			int currX = this.getLocation().x + diameter / 2;
			int currY = this.getLocation().y + diameter / 2;
			
			this.maxX = owner.getWidth();
			this.maxY = owner.getHeight();
//			System.out.println(maxX + "  " + maxY);

			if(currX + diameter / 2 >= maxX || currX - diameter / 2 <= 0) {
				movX *= -1;
			}
			
			if(currY + diameter / 2 >= maxY || currY - diameter / 2 <= 0) {
				movY *= -1;
			}
			
			this.setLocation(new Point(this.getLocation().x + movX, this.getLocation().y + movY));
			repaint();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		

	}

	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}

}
