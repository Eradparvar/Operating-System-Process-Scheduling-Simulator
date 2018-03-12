
public interface  IRandomValueGenerator {
	//have to overload one for 1-400 and another for any number
	public int getNextInt();
	public boolean getTrueWithProbability(double p);
	

}
