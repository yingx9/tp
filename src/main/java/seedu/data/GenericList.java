package seedu.data;

import java.util.List;

public class GenericList<R, E> {
    private List<R> resourcesList;
    private List<E> eventList;

    public GenericList(List<R> resourcesList, List<E> eventList) {
        this.resourcesList = resourcesList;
        this.eventList = eventList;
    }

    public List<R> getResourcesList() {
        return resourcesList;
    }

    public List<E> getEventsList() {
        return eventList;
    }

    public void setResourcesList(List<R> resourcesList) {
        this.resourcesList = resourcesList;
    }

    public void setEventsList(List<E> eventList) {
        this.eventList = eventList;
    }
}
