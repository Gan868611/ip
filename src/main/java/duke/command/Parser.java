package duke.command;

import duke.Storage.Storage;
import duke.command.actionCommands.DeleteTask;
import duke.command.actionCommands.ListTasks;
import duke.command.actionCommands.MarkTask;
import duke.command.actionCommands.UnmarkTask;
import duke.command.taskCommands.DeadlineTask;
import duke.command.taskCommands.EventTask;
import duke.command.taskCommands.TodoTask;
import duke.exception.InvalidTaskException;
import duke.tasks.TaskList;

import java.io.IOException;

import static duke.Storage.Storage.updateFile;
import static duke.main.Duke.printHorizontalLine;

public class Parser {
    private boolean isExit = false;

    public boolean isExit() {
        return this.isExit;
    }

    public void processLine(String line, TaskList tasks, Storage storage) {
        String[] args = line.split(" ", 2);
        Command newCommand;
        try {
            switch (args[0]) {
            case "bye":
                isExit = true;
                return;
            case "list":
                newCommand = new ListTasks();
                newCommand.processCommand(tasks, line);
                break;
            case "delete":
                newCommand = new DeleteTask();
                newCommand.processCommand(tasks, args[1]);
                break;
            case "mark":
                newCommand = new MarkTask();
                newCommand.processCommand(tasks, args[1]);
                break;
            case "unmark":
                newCommand = new UnmarkTask();
                newCommand.processCommand(tasks, args[1]);
                break;
            case "todo":
                newCommand = new TodoTask();
                newCommand.processCommand(tasks, args[1]);
                break;
            case "deadline":
                newCommand = new DeadlineTask();
                newCommand.processCommand(tasks, args[1]);
                break;
            case "event":
                newCommand = new EventTask();
                newCommand.processCommand(tasks, args[1]);
                break;
            default:
                throw new InvalidTaskException();
            }
        } catch (InvalidTaskException e) {
            printHorizontalLine();
            System.out.println(e.getMessage());
            printHorizontalLine();
        } catch (ArrayIndexOutOfBoundsException e) {
            printHorizontalLine();
            System.out.println("Please enter all command parameter!");
            printHorizontalLine();
        }

        try {
            updateFile(tasks);
        } catch (IOException e) {
            System.out.println("I/O Error!");
        }
    }
}
