
public interface IProcessor {

	public IProcess getIProcess();
	public void setIProcess(IProcess iProcess);
	public int getCurrentInstruction();
	public void setCurrentInstruction(int currentInstruction);
	public ProcessState executeNextInstruction();
	public void setRegisterValue(int i, int val);
	public int getRegisterValue(int i);
}
