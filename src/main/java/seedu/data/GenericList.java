package seedu.data;

import java.util.List;

public class GenericList<R, E> {
    private List<R> resourceList;
    private List<E> eventList;

    public GenericList(List<R> resourceList, List<E> eventList) {
        this.resourceList = resourceList;
        this.eventList = eventList;
    }

    public List<R> getResourcesList() {
        return resourceList;
    }

    public List<E> getEventsList() {
        return eventList;
    }

    public void setResourceList(List<R> resourceList) {
        this.resourceList = resourceList;
    }

    public void setEventList(List<E> eventList) {
        this.eventList = eventList;
    }
}
