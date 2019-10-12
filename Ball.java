import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball {

	private int x,y;
	
	private int width=25,height=25;
	
	private int ballSpeed=3;
	
	private int motionX, motionY;
	
	private Random random;
	
	public Ball(Pong pong) {
		random=new Random();
		
		x=pong.getWidth()/2-width/2;
		y=pong.getHeight()/2-height/2;
		
		randomDirectionX();
		randomDirectionY();
	}
	
	public void update(Paddle player1, Paddle player2, Pong pong) {
		x=x+motionX*ballSpeed;
		y=y+motionY*ballSpeed;
		
		if(checkHitPaddle(player1, pong)) {
			motionX=-1;
			ballSpeed=ballSpeed+1;

		}
		if(checkHitPaddle(player2, pong)) {
			motionX=1;
			ballSpeed=ballSpeed+1;
			
		}
		
		if(checkHitWallTop(pong)) {
			motionY=motionY*(-1);
		}
		if(checkHitWallBottom(pong)) {
			motionY=motionY*(-1);
		}
		
		if(checkPointRight(pong, player1)) {
			player1.setScore();
			spawn(pong);
			ballSpeed=1;
		}
		if(checkPointLeft(pong, player2)) {
			player2.setScore();
			spawn(pong);
			ballSpeed=1;
		}
	}
	
	public void spawn(Pong pong) {
		x=pong.getWidth()/2-width/2;
		y=pong.getHeight()/2-height/2;
		randomDirectionX();
		randomDirectionY();
	}
	
	
	public boolean checkPointLeft(Pong pong, Paddle player2) {
		if(x+30==player2.getX()+player2.getWidth() || x+30<player2.getX()+player2.getWidth()) {
			return true;
		}
		return false;
	}
	
	public boolean checkPointRight(Pong pong, Paddle player1) {
		if(x==player1.getX() || x>player1.getX()) {
			return true;
		}
		return false;
	}
	
	
	public boolean checkHitWallTop(Pong pong) {
		if (y==0 || y<0) {
			return true;
		}
		return false;
	}
	
	public boolean checkHitWallBottom(Pong pong) {
		if (y==pong.getHeight()-20 || y>pong.getHeight()-20) {
			return true;
		}
		return false;
	}
	
	public boolean checkHitPaddle(Paddle player, Pong pong) {
		if (player.getY()+player.getHeight()+20 > y && y > player.getY()-20) {
			if(player.getPaddleNumber()==2) {
				if (x+22>player.getX()) {
					return true;
				}
			}
			if(player.getPaddleNumber()==1) {
				if (x<player.getX()+player.getWidth()) {
					return true;
				}
			}
		}
		return false;
	}

	public void randomDirectionX() {
		motionX=random.nextInt(2);
		if(motionX==0) {
			motionX=1;
		}
		else if(motionX==1) {
			motionX=-1;
		}
	}
	
	public void randomDirectionY() {
		motionY=random.nextInt(2);
		if(motionY==0) {
			motionY=1;
		}
		else if(motionY==1 ) {
			motionY=-1;
		}
	}
	
	
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(x,y,width,height);
	}
}
