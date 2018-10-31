package com.poc.maze;

import com.poc.maze.exception.MazeException;

public interface MazeSolverApp {

	public String mazeOrchestrator(String fileLocation) throws MazeException;
	
	public void requestProcessor(String fileLocation) throws MazeException;
	
	public boolean mazeSolver(int startX, int startY) throws MazeException;
	
	public void printMaze();
}
