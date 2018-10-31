package com.poc.maze;

/**
 * This class is the entry point to maze solver application
 * @author Sid
 * @param inputArgs
 * @return
 * @throws 
 */

public class Main {

	public static void main(String[] args) {
		MazeSolverAppImpl app = new MazeSolverAppImpl();

		try {
			app.mazeOrchestrator(args[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
