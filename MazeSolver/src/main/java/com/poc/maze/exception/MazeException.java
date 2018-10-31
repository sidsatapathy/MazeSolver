package com.poc.maze.exception;

@SuppressWarnings("serial")
public class MazeException extends Exception {
	
	public static final String INVALID_START = "The start point is not defined!";
	public static final String INVALID_END = "The final point is not defined in the maze!";
	public static final String INVALID_MAZE = "The maze cannot be solved!";
	
	public MazeException(String message) {
        super(message);
    }
}