package duke.fileOperation;


import duke.command.Command;
import duke.command.taskCommands.TodoTask;
import duke.exception.InvalidTaskException;
import duke.tasks.*;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.main.Duke.taskCount;

public class FileOperation {
    public static void initFile() throws IOException {
        File newFile = new File("./taskslist.csv");
        if (!newFile.exists()) {
            newFile.createNewFile();
        }
    }

    public static void loadFile(Task[] tasksList) throws FileNotFoundException {
        File file = new File("./taskslist.csv");
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String input = s.nextLine();
            String args[] = input.split(",", 5);
            String type = args[0];
            Task newTask;
            switch (type) {
            case "T":
                newTask = new Todo(args[2]);
                tasksList[taskCount] = newTask;
                taskCount++;
                if (!args[1].equals(" ")) {
                    newTask.markAsDone();
                }
                break;
            case "D":
                newTask = new Deadline(args[2], args[3]);
                tasksList[taskCount] = newTask;
                taskCount++;
                if (!args[1].equals(" ")) {
                    newTask.markAsDone();
                }
                break;
            case "E":
                newTask = new Event(args[2], args[3], args[4]);
                tasksList[taskCount] = newTask;
                taskCount++;
                if (!args[1].equals(" ")) {
                    newTask.markAsDone();
                }
                break;
            }
        }


    }

    public static void updateFile(Task[] tasksList) throws IOException {
        FileWriter overwriteFile = new FileWriter("./taskslist.csv");
        for (int i = 0; i < taskCount; i++) {
            Task task = tasksList[i];
            String type = task.getType();
            String taskName = task.getTaskName();
            String status = task.getStatusIcon();
            switch (type) {
            case "T":
                overwriteFile.write(type + "," + status + "," + taskName + "\n");
                break;
            case "D":
                Deadline taskD = (Deadline) task;
                overwriteFile.write(type + "," + status + "," + taskName + "," + taskD.getDeadline() + "\n");
                break;
            case "E":
                Event taskE = (Event) task;
                overwriteFile.write(
                        type + "," + status + "," + taskName + "," + taskE.getStart() + "," + taskE.getEnd() + "\n");
                break;
            default:
            }
        }
        overwriteFile.close();
    }
}
