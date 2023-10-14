package seedu.duke;

import commands.Command;
import commands.DeleteCommand;
import commands.FindCommand;
import commands.HelpCommand;
import commands.ListCommand;
import commands.AddCommand;
import data.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Parser {
    public List<Resource> taskList = new ArrayList<>();
    public HashMap<String, Command> commandProcessor = new HashMap<>() {
        {
            put("list", new ListCommand());
            put("delete", new DeleteCommand());
            put("find", new FindCommand());
            put("help", new HelpCommand());
            put("add", new AddCommand());
        }
    };
    public void process(String response) {
        String command = response.split(" ")[0];
        if (commandProcessor.containsKey(command)) {
            String statement = removeFirstWord(response);
            try {
                commandProcessor.get(command).execute(statement, this);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("no commands found");
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
