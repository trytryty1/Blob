package main;

public class World {
	int[][] worldGrid;
	Blob blob;

	int width = 20, height = 20, gridSize = 50;

	public World(int width, int height, int gridSize) {
		this.width = width;
		this.height = height;
		this.gridSize = gridSize;

		worldGrid = new int[this.width][this.height];
		genRandomWorld();
		
		blob = new Blob();
	}
	
	public void update() {
		blob.update();
	}

	public int getTile(int x, int y) {
		return worldGrid[x][y];
	}

	public void genRandomWorld() {
		for (int x = 0; x < this.width; ++x) {
			for (int y = 0; y < this.height; ++y) {
				worldGrid[x][y] = Math.random() > 0.5 ? 0 : 1;
			}
		}
	}
}