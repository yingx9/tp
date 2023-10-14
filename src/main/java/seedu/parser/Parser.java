package seedu.parser;

import data.Resource;

import data.SysLibException;
import seedu.commands.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Parser {
    public List<Resource> resourceList = new ArrayList<>();
    public HashMap<String, Command> commandProcessor = new HashMap<>() {
        {
            put("list", new ListCommand());
            put("delete", new DeleteCommand());
            put("find", new FindCommand());
            put("help", new HelpCommand());
            put("exit", new ExitCommand());
            put("add", new AddCommand());
        }
    };
    public void process(String response) {
        String command = response.split(" ")[0];
        if (commandProcessor.containsKey(command)) {
            String statement = removeFirstWord(response);
            try {
                commandProcessor.get(command).execute(statement, this);
            } catch (IllegalArgumentException | IllegalStateException | SysLibException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("no commands found. Enter \"help\" for a list of commands.\n");
        }

    }
    public static String removeFirstWord(String response) {
        int index = response.indexOf(" ");
        if (index == -1) {
            return "";
        }
        return response.substring(index + 1);
    }
}
