import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Renderer extends JPanel {

	private static final long serialVersionUIOD=1l;
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Pong.pong.render((Graphics2D) g);
	}
	

	
}
