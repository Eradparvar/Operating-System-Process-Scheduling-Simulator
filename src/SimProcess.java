
public class SimProcess implements IProcess {

	int pid;
	String procName;
	int totalInstructions;
	IRandomValueGenerator randomValueGenerator;

	public SimProcess(int pid, String procName, int totalInstructions, IRandomValueGenerator randomValueGenerator) {
		this.pid = pid;
		this.procName = procName;
		this.totalInstructions = totalInstructions;
		this.randomValueGenerator = randomValueGenerator;
	}

	@Override
	public int getPid() {

		return pid;
	}

	@Override
	public String getProcName() {
		// TODO Auto-generated method stub
		return procName;
	}

	@Override
	public ProcessState execute(int i) {

		System.out.println(
				"Process Name: " + getProcName() + " - " + "PID: " + getPid() + " - " + "Executing instruction: " + i);

		if (i == totalInstructions) {
			return ProcessState.FINISHED;
		} else if (randomValueGenerator.getTrueWithProbability(.15)) {
			return ProcessState.BLOCKED;
		}
		return ProcessState.READY;
	}
}
