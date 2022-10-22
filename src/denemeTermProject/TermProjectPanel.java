package denemeTermProject;
//COPY OF THATTTTT
//COPY OF THAT
//COPY OF THAT
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TermProjectPanel extends JPanel {
	
	private BufferedImage ufoImage;
	private BufferedImage starImage;
	private BufferedImage meteoriteImage;
	private BufferedImage heartImage;
	
	private SecureRandom securerandom = new SecureRandom();
	private int ufoImageX = securerandom.nextInt(225) + 225;
	private int ufoImageY = securerandom.nextInt(300);
	private int starImageX = securerandom.nextInt(200) ;
	private int starImageY = securerandom.nextInt(300) ;
	private int meteoriteImageX = securerandom.nextInt(200) + 450;
	private int meteoriteImageY = securerandom.nextInt(300);
	private int heartImageX = securerandom.nextInt(200) + 675;
	private int heartImageY = securerandom.nextInt(300);
	
	private int crashTimeHeart = 0;
	
	
	private int time = 0;
	private int lives = 3;
	private int point = 0;
	private boolean levelPassed = false;
	private int highscore = 0;
	
	private int ballRadius = 10;
	private double ratio = 1/9.8;
	private int paddleLength = 120;
	private int paddleWidth = 30;
	
	private int ballX = 10; 
	private int ballXVel = 4;
	
	private int ballY = 10;
	private int ballYVel = 1;
	private double ballYacc = 9.8* ratio;
	
	private int paddleX = 60;
	private int paddleY = 500;
	
	private Color ballColor = Color.red;
	private Color paddleColor = Color.black;
	
	private Timer timer = new Timer(5 , new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				time += 5;
				
				ballYVel += ballYacc;
				ballY += ballYVel;
				
				ballX += ballXVel;
				
				if (ballX >= 1014) {
					ballXVel = - ballXVel;
				}
				if (ballX < 5) {
					ballXVel = -ballXVel;
				}
				if (ballY < 10) {
					ballYVel = - ballYVel -1; // minus 1 is responsible for conservation of energy
					//System.out.println(ballYVel); // It was for testing
				}
				// This should change
				// new Rectangle(currates.getX(), currates.getY(),10 ,10).intersects( new Rectangle(topX, 0, 10, 20))
				if (new Rectangle(ballX, ballY, ballRadius, ballRadius).intersects( new Rectangle(paddleX, paddleY, paddleLength, paddleWidth))) {
				
					
					ballYVel = - ballYVel -1; // minus 1 is responsible for conservation of energy
					//System.out.println(ballYVel); // IT was for testing
					point += 1; // Bounced so we added 1 point
				}
				
				
				crashingUFO();
				crashingStar();
				crashingHeart();
				crashingMeteorite(); 
				
				
				
				repaint();
			}
		
		
		} // End of the inner anonymous class
			
				
			);// End of timer

	
	
	
	
	/*
	 * CONSTRUCTOR
	 */
	public TermProjectPanel() {
		setBackground(Color.WHITE);
		try {
			ufoImage =ImageIO.read( new FileImageInputStream(new File("UFO2.png")) );
		}
		catch(IOException ex) {
			Logger.getLogger(TermProjectPanel.class.getName()).log(Level.SEVERE,null,ex);
		}
		
		try {
			starImage =ImageIO.read( new FileImageInputStream(new File("Star2.png")) );
		}
		catch(IOException ex) {
			Logger.getLogger(TermProjectPanel.class.getName()).log(Level.SEVERE,null,ex);
		}
		
		try {
			meteoriteImage =ImageIO.read( new FileImageInputStream(new File("Meteorite1.png")) );
		}
		catch(IOException ex) {
			Logger.getLogger(TermProjectPanel.class.getName()).log(Level.SEVERE,null,ex);
		}
		
		try {
			heartImage =ImageIO.read( new FileImageInputStream(new File("Heart2.png")) );
		}
		catch(IOException ex) {
			Logger.getLogger(TermProjectPanel.class.getName()).log(Level.SEVERE,null,ex);
		}
	}
	
	/*
	 * STATUS CONTROLLER FUNCTIONS
	 */
	
	private boolean isLevelPassed() {
		if (getTimeWithoutDivided1000() == 18000) {
			//System.out.println("Level geçtin");
			levelPassed = true;
			return true;
		}
		return false;
	}
	
	
	private boolean isLifeReduced() {// It check whether ball fall beneath the paddle
		if (ballY > paddleY +10) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	private boolean isGameFinished() { // It check if all lives become zero
		if (lives == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	private void crashingUFO() {
		if (new Rectangle(ballX, ballY, ballRadius, ballRadius).intersects( new Rectangle(ufoImageX, ufoImageY, ufoImage.getWidth()/15, ufoImage.getHeight()/9)) && 
			!new Rectangle(ballX -ballXVel, ballY- ballYVel, ballRadius, ballRadius).intersects( new Rectangle(ufoImageX, ufoImageY, ufoImage.getWidth()/10, ufoImage.getHeight()/6))) {
			lives -= 1;
			//System.out.println("Lýves = " + lives );
		}
		
	}
	//STAR GIVES 10 POINT PER COLLISON
	private void crashingStar() {
		if (new Rectangle(ballX, ballY, ballRadius, ballRadius).intersects( new Rectangle(starImageX, starImageY, starImage.getWidth()/15, starImage.getHeight()/12)) && 
			!new Rectangle(ballX -ballXVel, ballY- ballYVel, ballRadius, ballRadius).intersects( new Rectangle(starImageX, starImageY, starImage.getWidth()/10, starImage.getHeight()/8))) {
			point += 10;
			//System.out.println("Point = " + point);
		}
	}
	// IT only ýncreases X Velocity because it becomes impossible to play if Y Velocity becomes to high
	private void crashingMeteorite() { //(Increasing the Vel of y component is really PROBLEMATIC)
		if (new Rectangle(ballX, ballY, ballRadius, ballRadius).intersects( new Rectangle(meteoriteImageX, meteoriteImageY, meteoriteImage.getWidth()/15, meteoriteImage.getHeight()/12)) && 
			!new Rectangle(ballX -ballXVel, ballY- ballYVel, ballRadius, ballRadius).intersects( new Rectangle(meteoriteImageX, meteoriteImageY, meteoriteImage.getWidth()/10, meteoriteImage.getHeight()/8))) {
			
			if (ballXVel > 0) {
				ballXVel += 1; 
			}
			else {
				ballXVel -=1;
			}
			// IT do
			//System.out.println("Çarptým");
			//System.out.println("ballXVel = " +ballXVel + " ballYVel = " + ballYVel);
		}
	}
	
	//Heart is new object 
	// IT give lives to player (so games become much longer) but tou should wait at least 0.3 s to get second live!
	private void crashingHeart() {
		if (new Rectangle(ballX, ballY, ballRadius, ballRadius).intersects( new Rectangle(heartImageX, heartImageY, heartImage.getWidth()/20, heartImage.getHeight()/16)) && 
			!new Rectangle(ballX -ballXVel, ballY- ballYVel, ballRadius, ballRadius).intersects( new Rectangle(heartImageX, heartImageY, heartImage.getWidth()/20, heartImage.getHeight()/16))) {
			if (lives <3 && time > crashTimeHeart+300) {
				lives+=1;
				crashTimeHeart = time;
				//System.out.println(lives);
				//System.out.println("Time = " + time);
			}
			
		}
	}
	
	
	/*
	 * GETTER AND SETTERS
	 */
	
	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
	public int getPaddleX() {
		return paddleX;
	}

	public void setPaddleX(int paddleX) {
		this.paddleX = paddleX;
	}

	public int getTime() {
		return time/1000;
	}

	public int getTimeWithoutDivided1000() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public Color getPaddleColor() {
		return paddleColor;
	}

	public void setPaddleColor(Color paddleColor) {
		this.paddleColor = paddleColor;
	}

	public int getBallX() {
		return ballX;
	}

	public void setBallX(int ballX) {
		this.ballX = ballX;
	}

	public int getBallXVel() {
		return ballXVel;
	}

	public void setBallXVel(int ballXVel) {
		this.ballXVel = ballXVel;
	}

	public int getBallY() {
		return ballY;
	}

	public void setBallY(int ballY) {
		this.ballY = ballY;
	}

	public int getBallYVel() {
		return ballYVel;
	}

	public void setBallYVel(int ballYVel) {
		this.ballYVel = ballYVel;
	}

	public int getCrashTimeHeart() {
		return crashTimeHeart;
	}

	public void setCrashTimeHeart(int crashTimeHeart) {
		this.crashTimeHeart = crashTimeHeart;
	}

	public void setLevelPassed(boolean levelPassed) {
		this.levelPassed = levelPassed;
	}

	public int getHighscore() {
		return highscore;
	}

	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}

	public Color getBallColor() {
		return ballColor;
	}

	public void setBallColor(Color ballColor) {
		this.ballColor = ballColor;
	}
	
	/*
	 * PRIVATE (HELPER) METHOD PUBLISHER
	 */

	public boolean isGameFinishedReturner() {
		return isGameFinished();
	}
	
	/*
	 * PAINT AND REPAINT
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(ballColor);
		g.fillOval(ballX, ballY, ballRadius, ballRadius);
		
		g.setColor(paddleColor);
		g.fillRect(paddleX, paddleY, paddleLength, paddleWidth);
		
		g.drawImage(ufoImage,ufoImageX,ufoImageY,getWidth()/15,getHeight()/9,this);//Fin a proper position for them
		g.drawImage(starImage,starImageX,starImageY,getWidth()/15,getHeight()/12,this);//
		g.drawImage(meteoriteImage,meteoriteImageX,meteoriteImageY,getWidth()/15,getHeight()/12,this);//
		g.drawImage(heartImage,heartImageX,heartImageY,getWidth()/20,getHeight()/16,this);//
		
		if (isLifeReduced()) {// Everything is returned to it's beginning position and condition
			lives -= 1;
			timer.stop();
			
			
			
			ballX = 10; 
			ballXVel = 4;
			if ( levelPassed) {
				ballXVel =6; //I did not increase Y Velocity because it makes me impossible to play
			}
			
			
			ballY = 10;
			ballYVel = 1;
			
			paddleX = 60;
			paddleY = 500;
			
			
		}
		
		if(isLevelPassed()) {
			ballXVel += ballXVel/2;
			//System.out.println(ballXVel);
		}
		
		
		if(isGameFinished()) { //sHOULD BE UNCOMMENTED
			timer.stop();
			if (point > highscore) highscore = point;
		}
		
	}
	
	@Override
	public void repaint() {
		super.repaint();
	}
}
