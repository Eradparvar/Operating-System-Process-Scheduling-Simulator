import java.util.LinkedList;
import java.util.Queue;

public class main {

	public static void main(String[] args) {
		LinkedList<ProcessControlBlock> readyProcesses = new LinkedList<>();
		LinkedList<ProcessControlBlock> blockedProcesses = new LinkedList<>();
		IRandomValueGenerator randomValueGenerator = new RandomValueGenerator();
		IProcessor processor = new SimProcessor(randomValueGenerator);
		final int QUANTUM = 5;

		IProcess process01 = new SimProcess(1001, "Word", 300, randomValueGenerator);
		ProcessControlBlock pcb01 = new ProcessControlBlock(process01);
		readyProcesses.add(pcb01);
		IProcess process02 = new SimProcess(1002, "Excel", 350, randomValueGenerator);
		ProcessControlBlock pcb02 = new ProcessControlBlock(process02);
		readyProcesses.add(pcb02);
		IProcess process03 = new SimProcess(1003, "Firefox", 200, randomValueGenerator);
		ProcessControlBlock pcb03 = new ProcessControlBlock(process03);
		readyProcesses.add(pcb03);
		IProcess process04 = new SimProcess(1004, "Powerpoint", 375, randomValueGenerator);
		ProcessControlBlock pcb04 = new ProcessControlBlock(process04);
		readyProcesses.add(pcb04);
		IProcess process05 = new SimProcess(1005, "Eclipse", 250, randomValueGenerator);
		ProcessControlBlock pcb05 = new ProcessControlBlock(process05);
		readyProcesses.add(pcb05);
		IProcess process06 = new SimProcess(1006, "Edge", 275, randomValueGenerator);
		ProcessControlBlock pcb06 = new ProcessControlBlock(process06);
		readyProcesses.add(pcb06);
		IProcess process07 = new SimProcess(1007, "File Explorer", 300, randomValueGenerator);
		ProcessControlBlock pcb07 = new ProcessControlBlock(process07);
		readyProcesses.add(pcb07);
		IProcess process08 = new SimProcess(1008, "Sticky notes", 200, randomValueGenerator);
		ProcessControlBlock pcb08 = new ProcessControlBlock(process08);
		readyProcesses.add(pcb08);
		IProcess process09 = new SimProcess(1009, "Alarm", 250, randomValueGenerator);
		ProcessControlBlock pcb09 = new ProcessControlBlock(process09);
		readyProcesses.add(pcb09);
		IProcess process10 = new SimProcess(1010, "Skype", 400, randomValueGenerator);
		ProcessControlBlock pcb10 = new ProcessControlBlock(process10);
		readyProcesses.add(pcb10);
		System.out.println("Start");
		/// -------------///
		ProcessControlBlock currentPCB = null;
		IProcess currentProcess;
		ProcessState state;
		currentPCB = contextSwitch02(currentPCB, processor, readyProcesses);
		int currentNumberOfIterations = 0;
		for (int i = 0; i < 3000; i++) {

			state = processor.executeNextInstruction();
			currentNumberOfIterations++;
			if (state == ProcessState.FINISHED) {
				System.out.println("*** Process has completed ***");
				contextSwitch01(currentPCB, processor);
				currentPCB = contextSwitch02(currentPCB, processor, readyProcesses);
			} else if (state == ProcessState.BLOCKED) {
				System.out.println("*** Process was blocked ***");
				blockedProcesses.add(currentPCB);
				contextSwitch01(currentPCB, processor);
				currentPCB = contextSwitch02(currentPCB, processor, readyProcesses);
			} else if (currentNumberOfIterations >= QUANTUM) {
				System.out.println("*** Quantum expired *** ");
				readyProcesses.add(currentPCB);
				contextSwitch01(currentPCB, processor);
				currentPCB = contextSwitch02(currentPCB, processor, readyProcesses);
				currentNumberOfIterations = 0;
			} else {
				state = processor.executeNextInstruction();
			}

			for (int index = 0; index < blockedProcesses.size(); i++) {
				if (randomValueGenerator.getTrueWithProbability(.30)) {
					readyProcesses.add(blockedProcesses.remove(index));
				}
			}

		}
		System.out.println("$$**  Done  **$$");

	}

	// *** Take a process off the processor ***
	public static void contextSwitch01(ProcessControlBlock currentPCB, IProcessor processor) {

		// A)Save all register values of the processor to the processes PCB
		currentPCB.setRegister00(processor.getRegisterValue(0));
		currentPCB.setRegister01(processor.getRegisterValue(1));
		currentPCB.setRegister02(processor.getRegisterValue(2));
		currentPCB.setRegister03(processor.getRegisterValue(3));

		// B)Get the current instruction from the processor and store it in the
		// processes PCB
		currentPCB.setCurrentInstruction(processor.getCurrentInstruction());

		
		System.out.println("Context switch: Saving process: " + processor.getIProcess().getProcName());
		System.out.println("\t Instruction: " + processor.getCurrentInstruction() + "   "
				+ currentPCB.getRegister00() + " " + currentPCB.getRegister01() + " "
				+ currentPCB.getRegister02() + " " + currentPCB.getRegister03());
	}

	// *** Put the next ready processes on the processor ***
	public static ProcessControlBlock contextSwitch02(ProcessControlBlock currentPCB, IProcessor processor,
			Queue<ProcessControlBlock> readyProcesses) {

		// Gets the next ready processes
		currentPCB = readyProcesses.poll();

		System.out.println("Context switch: Restoring  process: " + currentPCB.getIProcess().getProcName());
		System.out.println("\t Instruction: " + currentPCB.getCurrentInstruction() + " " + currentPCB.getRegister00()
				+ " " + currentPCB.getRegister01() + " " + currentPCB.getRegister02() + " "
				+ currentPCB.getRegister03());

		// A)Set all the processor’s register values to the values of the next ready
		// process PCB
		processor.setRegisterValue(0, currentPCB.getRegister00());
		processor.setRegisterValue(1, currentPCB.getRegister01());
		processor.setRegisterValue(2, currentPCB.getRegister02());
		processor.setRegisterValue(3, currentPCB.getRegister03());

		// B)Set the processor’s next instruction to the next instruction in next ready
		// process PCB
		processor.setCurrentInstruction(currentPCB.getCurrentInstruction());

		// C) puts the next ready processes on the processor
		processor.setIProcess(currentPCB.getIProcess());

		return currentPCB;
	}
}