/* File: CheckerBoard.java
 *
 * Write a method called putAndMoveOnce and
 * putAndMoveTwice that makes Karel put a 
 * beeper on the ground and move once and
 * twice, respectively. Use these methods to
 * build a checker board pattern.
 */

import stanford.karel.*;

public class CheckerBoard extends Karel {

	public void run() {
		putAndMoveTwice();
		putAndMoveOnce();
		turnLeft();
		move();
		turnLeft();
		putAndMoveTwice();
		putAndMoveOnce();
		turnLeft();
		turnLeft();
		turnLeft();
		move();
		turnLeft();
		turnLeft();
		turnLeft();
		putAndMoveTwice();
		putAndMoveOnce();
		turnLeft();
		move();
		turnLeft();
		putAndMoveTwice();
		putAndMoveOnce();
	}
	
	public void putAndMoveOnce() {
		putBeeper();
		move();
	}
	
	public void putAndMoveTwice() {
		putAndMoveOnce();
		move();
	}
}