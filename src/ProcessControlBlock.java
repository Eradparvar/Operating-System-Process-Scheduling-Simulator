
public class ProcessControlBlock {
	private IProcess iProcess;
	private int currentInstruction;
	private int register00;
	private int register01;
	private int register02;
	private int register03;

	public ProcessControlBlock(IProcess iProcessRef) {
		this.iProcess = iProcessRef;
	}

	public IProcess getIProcess() {
		return this.iProcess;
	}

	public int getCurrentInstruction() {
		return currentInstruction;
	}

	public void setCurrentInstruction(int currentInstruction) {
		this.currentInstruction = currentInstruction;
	}

	public int getRegister00() {
		return register00;
	}

	public void setRegister00(int register00) {
		this.register00 = register00;
	}

	public int getRegister01() {
		return register01;
	}

	public void setRegister01(int register01) {
		this.register01 = register01;
	}

	public int getRegister02() {
		return register02;
	}

	public void setRegister02(int register02) {
		this.register02 = register02;
	}

	public int getRegister03() {
		return register03;
	}

	public void setRegister03(int register03) {
		this.register03 = register03;
	}

	public String toString() {
		return "PCB for processes: " + this.getIProcess().getProcName();
		
	}
}
