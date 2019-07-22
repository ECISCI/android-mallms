package com.mincat.sample.utils;

/**
 * @author Gin
 */
public interface FlingListener {

	void onFling(Movement movement);

	enum Movement {
		UP, LEFT, RIGHT, DOWN, TOUCH
	}
}
