package seedu.data;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Event {
    private final String name;
    private final LocalDate date;
    private final String description;

    public Event(String name, LocalDate date, String description) {
        this.name = name;
        this.date = date;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return name + " | " + date.format(formatter) + " | " + description;
    }
}
