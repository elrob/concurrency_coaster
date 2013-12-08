/**
 * Controller.java
 * @author Robert Speller
 * Concurrent Programming CW 2: Coaster
 */


/**
 * Complete the implementation of this class in line with the FSP model
 */

import display.*;

public class Controller {

	public static int Max = 9;
	protected NumberCanvas passengers;

	// declarations required
	protected PlatformAccess pa;
	protected CoasterCar car2;
	protected CoasterCar car3;

	protected boolean buttonPress = false; 
	protected boolean waitingCar = false;
	protected int platformPassengers = 0; 

	public Controller(NumberCanvas nc) {
		passengers = nc;
		car2 = new CoasterCar(2,this,pa,passengers);
		car3 = new CoasterCar(3,this,pa,passengers);
	}

	public synchronized void newPassenger() throws InterruptedException {
		// complete implementation
		// use "passengers.setValue(integer value)" to update diplay
		while (platformPassengers >= Max) wait();
		++platformPassengers;
		passengers.setValue(platformPassengers);
		notifyAll();
	}

	public synchronized int getPassengers(int mcar) throws InterruptedException {
		// complete implementation for part I
		// update for part II
		// use "passengers.setValue(integer value)" to update display
		waitingCar = true;
		while (platformPassengers < mcar && !buttonPress) wait(); 
		int boardingPassengers = (platformPassengers >= mcar ? mcar : platformPassengers);
		platformPassengers -= boardingPassengers;
		passengers.setValue(platformPassengers);
		buttonPress = false;
		waitingCar = false;
		notifyAll();
		return boardingPassengers; 
	}

	public synchronized void goNow() {
		// complete implementation for part II
		// The button has no effect if there are no passengers waiting 
		// and/or there is no car at the platform
		if (platformPassengers > 0 && waitingCar) { 
			buttonPress = true;		
			notifyAll();
		}
	}

}