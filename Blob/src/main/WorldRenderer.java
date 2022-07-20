package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class WorldRenderer extends JPanel {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		World world = Main.world;
		
		int[][] worldGrid = world.worldGrid;
		int width = world.width;
		int height = world.height;
		int gridSize = world.gridSize;
		
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				g.setColor(worldGrid[x][y] == 1 ? Color.BLACK : Color.WHITE);
				//g.fillRect(x * gridSize, y * gridSize, gridSize, gridSize);
			}
		}
		
		BlobSegment[] segments = world.blob.segments;
		int segmentCount = world.blob.segmentCount;
		for(int i = 0; i < segmentCount; ++i) {
			int size = (int) segments[i].size;
			g.setColor(segments[i].type.color);
			g.fillOval((int)segments[i].position.x - size, (int)segments[i].position.y - size,
					size*2, size*2);
		}
		
		for (Connection connection1 : Main.world.blob.connectionsys.connections.values()) {
			BlobSegment obj1 = (BlobSegment) connection1.parent;
			if (obj1 != null) {
				for (int i = 0; i < connection1.maxConnections; ++i) {
					if (connection1.connections[i] != null) {
						Connection connection2 = connection1.connections[i];
						BlobSegment obj2 = (BlobSegment) connection2.parent;
						g.drawLine((int)obj1.position.x, (int)obj1.position.y, (int)obj2.position.x, (int)obj2.position.y);
					}
				}
			}
		}
	}
	
}