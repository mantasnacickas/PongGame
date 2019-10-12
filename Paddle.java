import java.awt.Color;
import java.awt.Graphics;

public class Paddle {
	
	private int score;
	
	private int paddleNumber;
	
	private int x,y,width=30,height=150;
	
	public Paddle(Pong pong, int paddleNumber) {
		this.paddleNumber=paddleNumber;
		if(paddleNumber==1) {
			x=0;
		}
		else {
			x=pong.getWidth()-width;
		}
		y=pong.getHeight()/2 - height/2;
	}
	
	public void move(Pong pong, boolean direction) {
		int movingSpeed=20;
		if (direction) {
			if (y>0) {
			y=y-movingSpeed;		
			}
			else {
				y=0;
			}
		}
		else {
			if (y<pong.getHeight()-height) {
				y=y+movingSpeed;
			}
			else {
				y=pong.getHeight()-height;
			}
		}
		
	}
	
	
	
	
	public int getScore() {
		return score;
	}

	public void setScore() {
		score++;
	}

	public int getPaddleNumber() {
		return paddleNumber;
	}

	public void setPaddleNumber(int paddleNumber) {
		this.paddleNumber = paddleNumber;
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
	}
	
	//getters and setters
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	
	
	
}
