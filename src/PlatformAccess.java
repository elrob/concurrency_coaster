/**
 * PlatformAccess.java
 * @author Robert Speller
 * Concurrent Programming CW 2: Coaster
 */

/**
 * Complete the implementation of this class in line with the FSP model
 */

public class PlatformAccess {

	/* declarations required */
	protected boolean platformOccupied = false;

	public synchronized void arrive() throws InterruptedException {
		// complete implementation
		while (platformOccupied) wait();
		platformOccupied = true;
		//notifyAll();
	}

	public synchronized void depart() throws InterruptedException {
		// complete implementation
		while (!platformOccupied) wait();
		platformOccupied = false;
		notifyAll();
	}

}