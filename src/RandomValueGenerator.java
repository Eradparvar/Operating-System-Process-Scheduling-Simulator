import java.util.Random;

public class RandomValueGenerator implements IRandomValueGenerator {
	Random rnd = new Random();

	@Override
	public int getNextInt() {
		// creates a 6 digit random number
		return rnd.nextInt((999999 - 100000) + 1) + 100000;
	}

	// Implementing probability in Java
	@Override
	public boolean getTrueWithProbability(double p) {
		double randomNumber = rnd.nextDouble();
		if (randomNumber < p) {
			return true;
		}
		return false;
	}

}
