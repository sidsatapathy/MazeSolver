package com.poc.maze;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.poc.maze.constants.MazeConstants;
import com.poc.maze.exception.MazeException;

public class MazeSolverAppImpl implements MazeSolverApp{

	private char[][] maze = null;
	private BufferedReader br = null;
	private File f;
	private static Integer startX = null;
	private static Integer startY = null;
	private Integer endX = null;
	private Integer endY = null;
	private int mazeHeight = 0;
	private int width = 0;
	
	/**
	 * This method is used to orchestrate the request
	 * @param input file Location
	 * @return 
	 * @throws MazeException
	 */
	public String mazeOrchestrator(String fileLocation) throws MazeException{

		requestProcessor(fileLocation);
		if (mazeSolver(startX, startY)) {
			printMaze();
			return MazeConstants.SUCCESS_MSG;
		} else {
			throw new MazeException(MazeException.INVALID_MAZE);
		}
	}
	
	/**
	 * This recursive method checks the maze matrix for
	 * empty spaces around the current location and finally
	 * solves the maze.
	 * @param startpoint coordinates (X, Y)
	 * @return
	 * @throws MazeException
	 */
	public void requestProcessor(String fileLocation) throws MazeException{

		String line = null;
		int count = 1;
		int heightCounter = 0;
		try {
			mazeHeight = (int) Files.lines(Paths.get(new File(fileLocation).getPath())).count();

			f = new File(fileLocation);

			// Read each file line to populate necessary variables and maze coordinates
			br = new BufferedReader(new FileReader(fileLocation));
			while ((line = br.readLine()) != null) {

				if (width == 0)
					width = line.toString().length();
				if (null == maze) {
					maze = new char[mazeHeight][width];
				}

				if (startY == null || startY == -1) {
					startY = line.toString().indexOf('S');
					startX = count - 1;
				}
				if (endY == null || endY == -1) {
					endY = line.toString().indexOf('F');
					endX = count - 1;
				}

				for (int i = 0; i < width; i++) {
					maze[heightCounter][i] = line.charAt(i);
				}
				heightCounter++;
				count++;
			}
			if (startY == -1) {
				throw new MazeException(MazeException.INVALID_START);
			}
			if (endY == -1) {
				throw new MazeException(MazeException.INVALID_END);
			}
		} catch (FileNotFoundException ex) {
			System.out.println("The file : " + f.getName() + " does not exist");
			ex.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException ex) {
			ex.printStackTrace();
		}
	}
	

	/**
	 * This recursive method checks the maze matrix for
	 * empty spaces around the current location and finally
	 * solves the maze.
	 * @param startpoint coordinates (X, Y)
	 * @return
	 * @throws MazeException
	 */
	public boolean mazeSolver(int x, int y) throws MazeException{

		char posValue = maze[x][y];
		if (posValue == '#') {
			return false;
		}

		if (posValue == MazeConstants.END_POINT) {
			return true;
		}

		if (posValue == '*') {
			return false;
		}

		if (posValue != MazeConstants.START_POINT && posValue != MazeConstants.END_POINT)
			maze[x][y] = '*';

		// Check if SOUTH movement is possible
		if ((mazeSolver(x + 1, y)) == true) {
			return true;
		}
		// Check if EAST movement is possible
		if ((mazeSolver(x, y + 1)) == true) {
			return true;
		}
		// Check if WEST movement is possible
		if ((mazeSolver(x, y - 1)) == true) {
			return true;
		}
		// Check if NORTH movement is possible
		if ((mazeSolver(x - 1, y)) == true) {
			return true;
		}

		maze[x][y] = ' ';
		return false;
	}

	/**
	 * This method is used to display the maze 
	 * in its current form.
	 * @param 
	 * @return
	 * @throws 
	 */
	public void printMaze() {
		for (int i = 0; i < mazeHeight; i++) {
			System.out.println(maze[i]);
		}
	}
}
