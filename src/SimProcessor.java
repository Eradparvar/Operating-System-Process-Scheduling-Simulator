
public class SimProcessor implements IProcessor {
	IRandomValueGenerator iRandomValueGenerator;
	IProcess currentIProcess;
	int[] registers = new int[4];
	int currentInstruction;

	public SimProcessor(IRandomValueGenerator iRandomValueGenerator) {
		this.iRandomValueGenerator = iRandomValueGenerator;
		
	}

	@Override
	public IProcess getIProcess() {
		return this.currentIProcess;
	}

	@Override
	public void setIProcess(IProcess iProcess) {
		this.currentIProcess = iProcess;
	}

	@Override
	public int getCurrentInstruction() {
		return currentInstruction;
	}

	@Override
	public void setCurrentInstruction(int currentInstruction) {
		this.currentInstruction = currentInstruction;

	}

	@Override
	public ProcessState executeNextInstruction() {
		return currentIProcess.execute(currentInstruction++);
		
	}

	@Override
	public void setRegisterValue(int i, int val) {
		registers[i] = val;

	}

	@Override
	public int getRegisterValue(int i) {
		return iRandomValueGenerator.getNextInt();
	}

}
