package duke.command;

import duke.exception.InvalidTaskException;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

public abstract class Command {
    public void processCommand(TaskList tasksList, String line) throws InvalidTaskException, ParseException, DataFormatException {

    }
}
