import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChangingColorGrid extends JFrame {
	final int APPLICATION_SIZE = 500;
	final int GRID_DIMENSION = 5;
	public ChangingColorGrid() {
		/** Frame settings */
		setSize(APPLICATION_SIZE, APPLICATION_SIZE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Test Changing Color Grid");
		setResizable(false);
		setVisible(true);
		/** Grid Settings */
		JPanel pane = new JPanel();
		pane.setLayout(new GridLayout(GRID_DIMENSION, GRID_DIMENSION));
		JPanel[][] grid = new JPanel[GRID_DIMENSION][GRID_DIMENSION];
		pane.setSize(APPLICATION_SIZE, APPLICATION_SIZE);
		for(int row = 0; row < GRID_DIMENSION; row++){
			for(int col = 0; col < GRID_DIMENSION; col++){
				JPanel tile = new JPanel();
				tile.setBackground(Color.WHITE);
				tile.setSize(APPLICATION_SIZE/GRID_DIMENSION, APPLICATION_SIZE/GRID_DIMENSION);
				tile.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(tile.getBackground() == Color.WHITE){
							tile.setBackground(Color.BLACK);
						}
						else{
							tile.setBackground(Color.WHITE);
						}
					}
				});
				tile.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				grid[col][row] = tile;
				pane.add(tile);
			}
		}
		add(pane);

	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				new ChangingColorGrid();
			}
		});
	}
}


