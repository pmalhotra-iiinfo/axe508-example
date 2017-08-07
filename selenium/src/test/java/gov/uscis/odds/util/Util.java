package gov.uscis.odds.util;

public class Util {
	public static void waitFor(int timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
