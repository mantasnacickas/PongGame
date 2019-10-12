import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.Timer;


public class Pong implements ActionListener, KeyListener {
	
	static Pong pong;
	
	private int width=700, height=700; 
	
	private Renderer renderer;
	
	private Paddle player1;
	
	private Paddle player2;
	
	boolean w,s,up,down;
	
	private int gameStatus=0; //0-pradeti game, 1-pauze, 2 - play, 3-game over
	
	private Ball ball;
	
	public static void main(String[] args) {
		pong=new Pong();
	}
	
	public Pong() {
		Timer timer=new Timer(10, this);
		JFrame jframe=new JFrame("Pong");
		
		renderer=new Renderer();
		jframe.setSize(width+17, height+40);
		jframe.setVisible(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.addKeyListener(this);
		jframe.add(renderer);
		
		timer.start();
	}
	
	public void start() {
		player1=new Paddle(this, 2);
		player2=new Paddle(this, 1);
		ball=new Ball(this);
	}
		

	public void render(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		
		
		
		if(gameStatus==0) {
			g.setFont(new Font("Arial", 1, 50));
			g.setColor(Color.WHITE);
			g.drawString("Pong Player vs Player", 100, 70);
			
			g.setFont(new Font("Arial", 1, 30));
			g.setColor(Color.WHITE);
			g.drawString("Press Space To Play", width/2-130, height/2);
		}
		
		if(gameStatus==1) {
			g.setFont(new Font("Arial", 1, 30));
			g.setColor(Color.WHITE);
			g.drawString("PAUSED", width/2-60, height/2);
		}
		
		
		
		if (gameStatus==1 || gameStatus==2) {
		g.setColor(Color.white);
		g.setStroke(new BasicStroke(5f));
		g.drawLine(width/2, 0, width/2, height);
		
		g.setColor(Color.white);
		g.drawOval(width/2-75, height/2-75, 150, 150);
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 1, 30));
		g.drawString(player1.getScore()+"", 175, 50);
		g.drawString(player2.getScore()+"", 525, 50);
		
		player1.render(g);
		player2.render(g);
		ball.render(g);
		}
		
		
		if( gameStatus==4) {
			g.setFont(new Font("Arial", 1, 50));
			g.setColor(Color.WHITE);
			g.drawString("Game over", 280, 340);
			if(player1.getScore()==7) {
				g.drawString("Player1 Won!", 250, 400);
			}
			else {
				g.drawString("Player2 Won!", 250, 400);
			}
		}
	}
	
	
	//Moving
	public void update() {
		if (player1.getScore()==7 || player2.getScore()==7) {
			gameStatus=4;
		}
		if(w) {
			player2.move(this,true);
		}
		if(s) {
			player2.move(this,false);
		}
		
		
		if(up) {
			player1.move(this,true);
		}
		if(down) {
			player1.move(this,false);
		}
		
		ball.update(player1, player2, this);
	}
	
	//Action declaration
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(gameStatus==2) {
		update();
		}
		renderer.repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int id=e.getKeyCode();
		if(id==e.VK_UP) {
			up=true;
		}
		if(id==e.VK_DOWN) {
			down=true;
		}
		
		
		if(id==e.VK_W) {
			w=true;
		}
		if(id==e.VK_S) {
			s=true;
		}
		
		
		
		if(id==e.VK_SPACE) {
			if (gameStatus==0) {
				gameStatus=2;
				start();	
			}
			else if (gameStatus==2) {
				gameStatus=1;
			}
			else if (gameStatus==1) {
				gameStatus=2;
			}
			if(gameStatus==4) {
				gameStatus=0;
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int id=e.getKeyCode();
		if(id==e.VK_UP) {
			up=false;
		}
		if(id==e.VK_DOWN) {
			down=false;
		}
		
		
		if(id==e.VK_W) {
			w=false;
		}
		if(id==e.VK_S) {
			s=false;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
			}


	
	
	
	//getters and setters

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
