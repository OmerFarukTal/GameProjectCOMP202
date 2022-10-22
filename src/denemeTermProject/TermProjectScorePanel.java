package denemeTermProject;
//COPY OF THATTTTT
//COPY OF THAT
//COPY OF THAT
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class TermProjectScorePanel extends JPanel{
	private JLabel pointLabel;
	private JProgressBar progressBar;
	private JLabel gamestatus;
	private JLabel highscore;
	private JLabel timeLabel;
	
	private JLabel points;

	private JLabel lives;
	private int live1X = 200;
	private int live1Y = 45;
	private int live2X = 220;
	private int live2Y = 45;
	private int live3X = 240;
	private int live3Y = 45;
	private int liveRadius = 10;
	
	
	/*
	 * CONSTRUCTOR
	 */
	public TermProjectScorePanel () {
		setLayout(null);
		
		points = new JLabel("POINTS:");
		points.setSize(50,50);
		points.setLocation(50, 25);
		add(points);
		
		lives = new JLabel("LIVES:");
		lives.setSize(50,50);
		lives.setLocation(150, 25);
		add(lives);
		
		gamestatus = new JLabel("Game Status: CONTINUE");
		gamestatus.setSize(150,50);
		gamestatus.setLocation(400, 25);
		add(gamestatus);
		
		highscore = new JLabel("Highscore: 0");
		highscore.setSize(150,50);
		highscore.setLocation(270, 25);
		add(highscore);
		
		setBackground(Color.WHITE);
		pointLabel = new JLabel("0");
		pointLabel.setSize(200,50);
		pointLabel.setLocation(100,25);
		add(pointLabel, BorderLayout.EAST);
		
		timeLabel = new JLabel("Time Progress:");
		timeLabel.setSize(100,50);
		timeLabel.setLocation(724,25);
		add(timeLabel);
		
		progressBar = new JProgressBar();
		progressBar.setLocation(824, 35);
		progressBar.setSize(100,30);
		progressBar.setValue(0);
		progressBar.setMaximum(18000);
		add(progressBar);
		
	}
	
	/*
	 * PAINT AND REPAINT
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.red);
		g.fillOval(live1X, live1Y, liveRadius, liveRadius);
		g.fillOval(live2X, live2Y, liveRadius, liveRadius);
		g.fillOval(live3X, live3Y, liveRadius, liveRadius);
	}
	
	@Override
	public void repaint() {
		super.repaint();
	}
	
	/*
	 * DEAL WITH LIVES
	 */
	
	public void dealWithLives(int totalLives) {
		if (totalLives == 3) {
			live1X = 200;
			live1Y = 45;
			live2X = 220;
			live2Y = 45;
			live3X = 240;
			live3Y = 45;
		}
		else if (totalLives == 2) {
			live1X = 200;
			live1Y = 45;
			live2X = 220;
			live2Y = 45;
			live3X = 1000;
			live3Y = 1000;
		}
		else if (totalLives == 1) {
			live1X = 200;
			live1Y = 45;
			live2X = 1020;
			live2Y = 1000;
			live3X = 1000;
			live3Y = 1000;
		}
		else if (totalLives == 0) {
			live1X = 1040;
			live1Y = 1000;
			live2X = 1020;
			live2Y = 1000;
			live3X = 1000;
			live3Y = 1000;
		}
	}
	
	/*
	 * GETTER AND SETTER
	 */
	
	public JLabel getPointLabel() {
		return pointLabel;
	}

	public void setPointLabel(JLabel pointLabel) {
		this.pointLabel = pointLabel;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public JLabel getGamestatus() {
		return gamestatus;
	}

	public void setGamestatus(JLabel gamestatus) {
		this.gamestatus = gamestatus;
	}

	public JLabel getHighscore() {
		return highscore;
	}

	public void setHighscore(JLabel highscore) {
		this.highscore = highscore;
	}
	
}
