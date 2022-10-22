package denemeTermProject;
//COPY OF THATTTTT
//COPY OF THAT
//COPY OF THAT
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;



public class TermProjectFrame extends JFrame{
	
	private Timer controllertimer = new Timer(5, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (gamepanel.getTimer().isRunning()) {
				scorepanel.getPointLabel().setText(String.valueOf(gamepanel.getPoint()));
				scorepanel.getProgressBar().setValue(gamepanel.getTimeWithoutDivided1000());
				scorepanel.dealWithLives(gamepanel.getLives());
				
				if (gamepanel.isGameFinishedReturner()) {
					//controllertimer.stop();
					restart = true;
					pausebutton.setText("Start");
					scorepanel.getGamestatus().setText("Game status: FINISHED");
				}
				
			}
			else {
				if (gamepanel.isGameFinishedReturner()) {
					//controllertimer.stop();
					restart = true;
					pausebutton.setText("Start");
					scorepanel.getGamestatus().setText("Game status: FINISHED");
				}
				pausebutton.setText("Start");
			}
		}
		
	}
	);
	
	
	private TermProjectPanel gamepanel;
	private TermProjectScorePanel scorepanel;
	private JPanel bottompanel;
	
	
	private JButton pausebutton;
	private JComboBox ballColorComboBox;
	private JComboBox paddleColorComboBox;
	private JLabel ballColor;
	private JLabel paddleColor;
	private JButton replay;
	private boolean restart = false;
	
	/*
	 * GETTER AND SETTER
	 */

	public JButton getPausebutton() {
		return pausebutton;
	}


	public void setPausebutton(JButton pausebutton) {
		this.pausebutton = pausebutton;
	}


	/*
	 * CONSTRUCTOR
	 */
	
	public TermProjectFrame() {
		super("Space Pong");
		setLayout(null);
		
		
		//GamePanel
		gamepanel = new TermProjectPanel();
		gamepanel.setSize(1024,568);
		gamepanel.requestFocus();
		gamepanel.setFocusable(true);
		gamepanel.setFocusTraversalKeysEnabled(false);
		add(gamepanel);
		gamepanel.setLocation(0,100 );
		
		
		//ScorePanel
		scorepanel = new TermProjectScorePanel();
		scorepanel.setSize(1024,100);
		scorepanel.setLocation(0,0 );
		add(scorepanel);
		
		
		//BottomPanel
		bottompanel = new JPanel();
		bottompanel.setSize(1024,100);
		bottompanel.setLocation(0,668);
		add(bottompanel);
		
		bottompanel.setLayout(null);
		pausebutton = new JButton("Start");
		pausebutton.setSize(100,25);
		pausebutton.setLocation(462,25);
		bottompanel.add(pausebutton);
		
		
		replay = new JButton("Replay");
		replay.setSize(100, 25);
		replay.setLocation(900, 25);
		bottompanel.add(replay);
		
		
		String[] ballColors = {"red", "blue", "black", "green"};
		ballColorComboBox = new JComboBox(ballColors);
		ballColorComboBox.setSize(100, 25);
		ballColorComboBox.setLocation(322, 25);
		bottompanel.add(ballColorComboBox);
		
		
		String[] paddleColors = {"black", "blue", "red", "green"};
		paddleColorComboBox = new JComboBox(paddleColors);
		paddleColorComboBox.setSize(100, 25);
		paddleColorComboBox.setLocation(662, 25);
		bottompanel.add(paddleColorComboBox);
		
		
		ballColor = new JLabel("Ball Color");
		ballColor.setSize(100,25);
		ballColor.setLocation(262,25);
		bottompanel.add(ballColor);
		
		paddleColor = new JLabel("Paddle Color");
		paddleColor.setSize(100,25);
		paddleColor.setLocation(582,25);
		bottompanel.add(paddleColor);
		
		//Handler
		KeyListenerHandler handler = new KeyListenerHandler();
		gamepanel.addKeyListener(handler);//Dikkat
		pausebutton.addActionListener(handler);
		pausebutton.setFocusable(false);
		ballColorComboBox.addActionListener(handler);
		ballColorComboBox.setFocusable(false);
		paddleColorComboBox.addActionListener(handler);
		paddleColorComboBox.setFocusable(false);
		replay.addActionListener(handler);
		replay.setFocusable(false);
		
		//System.out.println("Scorepanel " + scorepanel.getSize());
		//System.out.println("Gamepanel " + gamepanel.getSize());
		//System.out.println("Bottompanel " + bottompanel.getSize());
		
	}
	
	

	private class KeyListenerHandler implements ActionListener, KeyListener {
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			int c = e.getKeyCode();
			
			if (c == KeyEvent.VK_RIGHT && pausebutton.getText().equals("Pause")) {
				if (gamepanel.getPaddleX() >= 1024- 130) {
					gamepanel.setPaddleX(gamepanel.getPaddleX());
				}
				else {
					gamepanel.setPaddleX(gamepanel.getPaddleX() + 30);
				}
			}
			else if (c == KeyEvent.VK_LEFT && pausebutton.getText().equals("Pause")) {
				if (gamepanel.getPaddleX() <= 10) {
					gamepanel.setPaddleX(gamepanel.getPaddleX());
				}
				else {
					gamepanel.setPaddleX(gamepanel.getPaddleX() - 30);
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == pausebutton && pausebutton.getText().equals("Pause")) {
				gamepanel.getTimer().stop();
				pausebutton.setText("Start");
			}
			else if (e.getSource() == pausebutton && pausebutton.getText().equals("Start") ) {
				gamepanel.getTimer().restart();
				pausebutton.setText("Pause");
				controllertimer.start();
			}
			else if (e.getSource() == ballColorComboBox) {
				if(ballColorComboBox.getItemAt(ballColorComboBox.getSelectedIndex()).equals("red"))
					gamepanel.setBallColor(Color.red);
				if(ballColorComboBox.getItemAt(ballColorComboBox.getSelectedIndex()).equals("blue"))
					gamepanel.setBallColor(Color.blue);
				if(ballColorComboBox.getItemAt(ballColorComboBox.getSelectedIndex()).equals("black"))
					gamepanel.setBallColor(Color.black);
				if(ballColorComboBox.getItemAt(ballColorComboBox.getSelectedIndex()).equals("green"))
					gamepanel.setBallColor(Color.green);
			}
			else if (e.getSource() == paddleColorComboBox) {
				if(paddleColorComboBox.getItemAt(paddleColorComboBox.getSelectedIndex()).equals("red"))
					gamepanel.setPaddleColor(Color.red);
				if(paddleColorComboBox.getItemAt(paddleColorComboBox.getSelectedIndex()).equals("blue"))
					gamepanel.setPaddleColor(Color.blue);
				if(paddleColorComboBox.getItemAt(paddleColorComboBox.getSelectedIndex()).equals("black"))
					gamepanel.setPaddleColor(Color.black);
				if(paddleColorComboBox.getItemAt(paddleColorComboBox.getSelectedIndex()).equals("green"))
					gamepanel.setPaddleColor(Color.green);
			}
			
			else if (e.getSource() == replay && restart) {
				pausebutton.setText("Pause");
				scorepanel.getGamestatus().setText("Game status: CONTINUE");
				scorepanel.getHighscore().setText("Highscore: " + gamepanel.getHighscore());
				restart = false;
				gamepanel.setPaddleX(60);
				gamepanel.setBallX(10);
				gamepanel.setBallXVel(4);
				gamepanel.setBallY(10);
				gamepanel.setBallYVel(1);
				gamepanel.setTime(0);
				gamepanel.setLives(3);
				gamepanel.setPoint(0);
				gamepanel.setLevelPassed(false);
				gamepanel.setCrashTimeHeart(0);
				gamepanel.getTimer().start();
			}
		}

		
		
	}

	
}
