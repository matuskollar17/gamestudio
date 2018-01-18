package gamestudio.game.slider.core;

import java.util.Random;

import gamestudio.game.puzzle.core.Coordinate;

public class Field {
	private final int rowCount;
	private final int columnCount;
	private final int[][] tiles;
	public static final int EMPTY_TILE = 0;
	private long startMillis;
	private long currentDateTime;
	public long tcount;
	
	
	public long getTcount() {
		return tcount;
	}

	public void setTcount(long tcount) {
		this.tcount = tcount;
	}

	public Field(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		tiles = new int[rowCount][columnCount];
		startMillis = System.currentTimeMillis();
		generate();
		
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public int getTile(int row, int column) {
		return tiles[row][column];
	}

	private void generate() {
		
		int tile = 1;
		for (int row = 0; row < rowCount; row++) {
			for (int column = 0; column < columnCount; column++) {
				tiles[row][column] = tile;
				tile++;
			
			}
		}
		tiles[rowCount - 1][columnCount - 1] = EMPTY_TILE;
		shuffle();
		//cas
		currentDateTime = System.currentTimeMillis();

	}

	private void shuffle() {
		Random random = new Random();
		for (int i = 0; i < rowCount * columnCount * 100; i++) {
			moveTile(random.nextInt(rowCount * columnCount - 1) + 1);
		}
	}

	private Coordinate getTileCoordinate(int tile) {
		for (int row = 0; row < rowCount; row++) {
			for (int column = 0; column < columnCount; column++) {
				if (tiles[row][column] == tile) {
					return new Coordinate(row, column);
				}
			}
		}
		return null;
	}

	public boolean isSolved() {
		int tile = 1;
		for (int row = 0; row < rowCount; row++) {
			for (int column = 0; column < columnCount; column++) {
				if (tiles[row][column] != EMPTY_TILE)
					if (tiles[row][column] != tile)
						return false;
				tile++;
			}
		}
		tcount = (int) ((System.currentTimeMillis() - currentDateTime) / 1000);
		return true;
		//cas
		
		
		//long timeTillSolved = System.currentTimeMillis();
		
		//long timeTillSolved = ConsoleUI.this.gametime - System.currentTimeMillis();
		//System.out.println(timeTillSolved);
		//ConsoleUI.this.gametime.compareTo(timeTillSolved);
		//ConsoleUI.this.gametime .compare
		//zle.gametime.compareTo timeTillSolved;
		
	}

	public boolean moveTile(int tile) {
		if (tile <= 0 || tile >= rowCount * columnCount)
			return false;

		Coordinate tileCoordinate = getTileCoordinate(tile);
		Coordinate emptyCoordinate = getTileCoordinate(EMPTY_TILE);

		if ((tileCoordinate.getRow() == emptyCoordinate.getRow()
				&& Math.abs(tileCoordinate.getColumn() - emptyCoordinate.getColumn()) == 1)
				|| (tileCoordinate.getColumn() == emptyCoordinate.getColumn()
						&& Math.abs(tileCoordinate.getRow() - emptyCoordinate.getRow()) == 1)) {
			tiles[emptyCoordinate.getRow()][emptyCoordinate.getColumn()] = tile;
			tiles[tileCoordinate.getRow()][tileCoordinate.getColumn()] = EMPTY_TILE;
			return true;
		}

		return false;
	}
}
