package spring;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;

public class TestApplication {

	public static void main(String[] args) {
//		ProcessHandle.allProcesses().forEach(process -> System.out.println(processDetails(process)));
		String[] strArr = new String[1];
		strArr[0] = "C:\\Program Files\\Notepad++\\notepad++.exe";

		runProcess(strArr);
//		ProcessHandle.allProcesses().forEach(process -> {
//			try {
//				String filename = new File(process.info().command().get()).getName();
//				if (filename.equals("notepad++.exe")) {
//					processKill(process);
//				}
//			} catch (Exception e) {
////				e.printStackTrace();
//			}
//		});
	}

	private static void killProcess(ProcessHandle process) {
		Long parentProcessId = process.parent().map(ProcessHandle::pid).get();
		ProcessHandle parentProcess = ProcessHandle.of(parentProcessId).get();
		String parentProcessCommand = parentProcess.info().command().get();
		String processCommand = process.info().command().get();

//		System.out.println(processCommand);
//		System.out.println(parentProcessCommand);
		if (process.info().command().get().equals(parentProcessCommand)) {
			parentProcess.destroy();
		} else {
			process.destroy();
		}
	}

	private static void runProcess(String[] command) {
		try {
			Process process = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static boolean findProcess(String name) {
		ProcessHandle.allProcesses().filter(process -> {
			if (name.equals(new File(process.info().command().get()).getName()) {
				return true;
			} else {
				return false;
			}
		})
	}

	private static String processDetails(ProcessHandle process) {
		return String.format("%8d %8s %10s %26s %-40s",
	            process.pid(),
	            text(process.parent().map(ProcessHandle::pid)),
	            text(process.info().user()),
	            text(process.info().startInstant()),
	            text(process.info().command())
			);
	}

	private static String text(Optional<?> optional) {
	    return optional.map(Object::toString).orElse("-");
	}

}
