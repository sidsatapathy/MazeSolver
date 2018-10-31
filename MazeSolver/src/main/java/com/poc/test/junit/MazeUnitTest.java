package com.poc.test.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import com.poc.maze.MazeSolverAppImpl;
import com.poc.maze.constants.MazeConstants;
import com.poc.maze.exception.MazeException;

public class MazeUnitTest {

	MazeSolverAppImpl mazeSolver;
	String fileLocation = "/Users/sid/git/repository/MazeSolver/src/main/java/com/poc/test/junit/";
	
	@Before 
	public void initialize() {
		mazeSolver = new MazeSolverAppImpl();
	}

	/**
	 * Test invalid Start point scenario
	 * @throws Exception
	 */
	@Test
	public void testInvalidStart() throws Exception {
			try {
				mazeSolver.mazeOrchestrator(fileLocation+"Maze_INVALID_START.txt");
			} catch (MazeException e) {
				assertEquals(MazeException.INVALID_START, e.getMessage());
			}
	}

	/**
	 * Test invalid final point scenario
	 * @throws Exception
	 */
	@Test 
	public void testInvalidFinal() throws Exception {
			try {
				mazeSolver.mazeOrchestrator(fileLocation+"Maze_INVALID_END.txt");
			} catch (MazeException e) {
				assertEquals(MazeException.INVALID_END, e.getMessage());
			}
	}
	
	/**
	 * Test invalid final point scenario
	 * @throws Exception
	 */
	@Test 
	@Ignore
	public void testInvalidMaze() throws Exception {
			try {
				mazeSolver.mazeOrchestrator(fileLocation+"Maze_CANNOT_SOLVE.txt");
			} catch (MazeException e) {
				assertEquals(MazeException.INVALID_MAZE, e.getMessage());
			}
	}
	
	/**
	 * Test invalid Start point scenario
	 * @throws MazeException 
	 * @throws Exception
	 */
	@Test
	public void testValidMaze() throws MazeException  {
		String output = mazeSolver.mazeOrchestrator(fileLocation + "Maze_VALID_INPUT.txt");
		assertEquals(output, MazeConstants.SUCCESS_MSG);
	}
	
}