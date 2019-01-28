package Pattern;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;


public class Draw extends JPanel {
	private static final long serialVersionUID = -5258995676212660595L;
	private int width;
	private int height;
	private int blocksize;
	private int[][] lattices;
	public Draw(int width, int height, int blocksize) {
		super();
		this.width = width;
		this.height = height;
		this.blocksize = blocksize;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int currx_pixel = 0;
		int curry_pixel = 0;
		for(int i=0;i<height;i++) {
			currx_pixel = 0;
			for(int j=0;j<width;j++) {
				g.drawRect(currx_pixel, curry_pixel, blocksize, blocksize);
				if(lattices[i][j]==1) {
					g.setColor(new Color(255,0,0));
					g.fillRect(currx_pixel, curry_pixel, blocksize, blocksize);
					g.setColor(new Color(0,0,0));
				}
				else if(lattices[i][j]==2) {
					g.setColor(new Color(128,128,128));
					g.fillRect(currx_pixel, curry_pixel, blocksize, blocksize);
					g.setColor(new Color(0,0,0));
				}
				currx_pixel += blocksize;
			}
			curry_pixel += blocksize;
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width*blocksize+1, height*blocksize+1);
	}
	public void update(int[][] a) {
		lattices = a;
	}
}
