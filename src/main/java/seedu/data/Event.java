package seedu.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
    private final String name;
    private final Date date;
    private final String description;

    public Event(String name, Date date, String description) {
        this.name = name;
        this.date = date;
        this.description = description;
    }

    public Date getDate() {
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return name + " | " + sdf.format(date) + " | " + description;
    }
}
