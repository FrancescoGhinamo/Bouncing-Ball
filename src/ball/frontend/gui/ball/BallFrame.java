package ball.frontend.gui.ball;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BallFrame extends JFrame {

	
	private static final long serialVersionUID = 710053970307246652L;
	
	private ArrayList<Ball> balls;
	private JPanel cont;
	
	public BallFrame() {
		super("Bouncing ball");
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(300, 200));
		balls = new ArrayList<Ball>();
		initComponents();
	}

	private void initComponents() {
		cont = new JPanel();
		cont.setBackground(Color.CYAN);
		cont.setLayout(null);
		cont.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 1) {
					for(Ball b: balls) {
						new Thread(b).start();
					}
					balls.add(new Ball(cont, e.getX(), e.getY(), 30));
					cont.add(balls.get(balls.size() - 1));
					new Thread(balls.get(balls.size() - 1)).start();
				}
				if(e.getClickCount() == 2) {
					for(Ball b: balls) {
						b.setStopped(true);
					}

				}
				
			}
		});
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = gbc.gridy = 0;
		gbc.weightx = gbc.weighty = 10;
		add(cont, gbc);
		
	}

	public static void main(String[] args) {
		new BallFrame().setVisible(true);

	}

}
