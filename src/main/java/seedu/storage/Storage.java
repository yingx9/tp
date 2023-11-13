package seedu.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import seedu.data.GenericList;
import seedu.data.Status;
import seedu.data.events.Event;
import seedu.data.resources.Book;
import seedu.data.resources.EBook;
import seedu.data.resources.EMagazine;
import seedu.data.resources.Magazine;
import seedu.data.resources.ENewspaper;
import seedu.data.resources.Newspaper;
import seedu.data.resources.CD;
import seedu.data.resources.Resource;
import seedu.exception.SysLibException;
import seedu.ui.UI;

public class Storage {
    public static final int FIRST_INDEX = 0;
    public static final int SECOND_INDEX = 1;
    public static final int THIRD_INDEX = 2;
    public static final int FOURTH_INDEX = 3;
    public static final int FIFTH_INDEX = 4;
    public static final int SIXTH_INDEX = 5;
    public static final int SEVENTH_INDEX = 6;
    public static final int EIGHTH_INDEX = 7;
    public static final int NINTH_INDEX = 8;
    public static final int TENTH_INDEX = 9;
    public static final int ELEVENTH_INDEX = 10;
    private static final String RESOURCE_PREFIX = "R";
    private static final String EVENT_PREFIX = "E";
    private static final Logger LOGGER = Logger.getLogger(Storage.class.getName());

    private final File dataFile;
    private final String filePath;
    private final GenericList<Resource, Event> container;
    private final UI ui = new UI();

    static {
        setupLogger();
    }

    /**
     * Constructs a Storage object for handling data persistence.
     *
     * @param filePath  The path to the data file.
     * @param container The container holding resources and events.
     */
    public Storage(String filePath, GenericList<Resource, Event> container) {
        this.filePath = filePath;
        this.dataFile = new File(filePath);
        this.container = container;
        ensureFileExists();
    }

    /**
     * Sets up the logger for this class.
     */
    private static void setupLogger() {
        try {
            // remove logs from showing in stdout
            Logger rootLogger = Logger.getLogger("");
            for (java.util.logging.Handler handler : rootLogger.getHandlers()) {
                if (handler instanceof java.util.logging.ConsoleHandler) {
                    rootLogger.removeHandler(handler);
                }
            }
            FileHandler fileHandler = new FileHandler("logs/Storage.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.INFO);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to set up log file handler", e);
        }
    }

    /**
     * Ensures the data file exists, creating it if necessary.
     */
    private void ensureFileExists() {
        try {
            if (!dataFile.exists()) {
                LOGGER.info("Storage file not found, creating now.");
                ui.showNoFileFoundMessage(filePath);
                Files.createFile(Paths.get(filePath));
            } else {
                LOGGER.info("Storage file found.");
                ui.showFileFoundMessage(filePath);
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + filePath);
        }
    }

    /**
     * Loads resources and events from the data file.
     *
     * @param resources The list to store loaded resources.
     * @param events    The list to store loaded events.
     * @throws SysLibException If there is an issue reading from the file or parsing data.
     */
    public void load(List<Resource> resources, List<Event> events) throws SysLibException {
        LOGGER.info("Starting load from storage file.");
        try (Scanner dataScanner = new Scanner(dataFile)) {
            int id = 0;
            while (dataScanner.hasNext()) {
                String dataLine = dataScanner.nextLine();
                String[] splitLineArguments = dataLine.split(" \\| ");

                if (splitLineArguments.length == 0) {
                    throw new SysLibException("Empty line in data file");
                }

                String dataType = splitLineArguments[FIRST_INDEX];
                switch (dataType) {
                case RESOURCE_PREFIX:
                    Resource resource = createResource(splitLineArguments, ++id);
                    resources.add(resource);
                    break;
                case EVENT_PREFIX:
                    Event event = createEvent(splitLineArguments);
                    events.add(event);
                    break;
                default:
                    throw new SysLibException("Unknown data type: " + dataType);
                }
            }
        } catch (IOException e) {
            LOGGER.info("Unable to read storage file.");
            throw new SysLibException("Error reading file: " + filePath);
        }
        LOGGER.info("Successfully loaded data.");
    }

    /**
     * Creates a Resource object from the parsed data line.
     *
     * @param data The parsed data line.
     * @param id   The ID for the resource.
     * @return The created Resource object.
     * @throws SysLibException If there is an issue parsing the data.
     */
    private Resource createResource(String[] data, int id) throws SysLibException {
        try {
            String title = data[SECOND_INDEX];
            boolean isBorrowed = Boolean.parseBoolean(data[THIRD_INDEX]);
            String isbn = data[FOURTH_INDEX];
            int copies = Integer.parseInt(data[FIFTH_INDEX]);
            String tag = data[SIXTH_INDEX];
            Status status = Status.valueOf(data[SEVENTH_INDEX]);
            LocalDateTime ldt = LocalDateTime.parse(data[EIGHTH_INDEX]);

            switch(tag) {
            case "B":
                String author = data[NINTH_INDEX];
                String[] genres = data[TENTH_INDEX].split(",");
                Book bookToAdd = new Book(title, isbn, author, genres, id, status);
                bookToAdd.setCopies(copies);
                bookToAdd.setBorrowed(isBorrowed);
                bookToAdd.setReceivedDateCustom(ldt);
                return bookToAdd;

            case "EB":
                String eauthor = data[NINTH_INDEX];
                String[] egenres = data[TENTH_INDEX].split(",");
                String blink = data[ELEVENTH_INDEX];
                EBook ebookToAdd = new EBook(title, isbn, eauthor, egenres, id, status, blink);
                ebookToAdd.setCopies(copies);
                ebookToAdd.setBorrowed(isBorrowed);
                ebookToAdd.setReceivedDateCustom(ldt);
                return ebookToAdd;

            case "N":
                String publisher = data[NINTH_INDEX];
                String edition = data[TENTH_INDEX];
                Newspaper newspaperToAdd = new Newspaper(title, isbn, publisher, edition, id, status);
                newspaperToAdd.setCopies(copies);
                newspaperToAdd.setBorrowed(isBorrowed);
                newspaperToAdd.setReceivedDateCustom(ldt);
                return newspaperToAdd;

            case "EN":
                String epublisher = data[NINTH_INDEX];
                String eedition = data[TENTH_INDEX];
                String nlink = data[ELEVENTH_INDEX];
                ENewspaper enewspaperToAdd = new ENewspaper(title, isbn, epublisher, eedition,
                        id, status, nlink);
                enewspaperToAdd.setCopies(copies);
                enewspaperToAdd.setBorrowed(isBorrowed);
                enewspaperToAdd.setReceivedDateCustom(ldt);
                return enewspaperToAdd;

            case "M":
                String brand = data[NINTH_INDEX];
                String issue = data[TENTH_INDEX];
                Magazine magazineToAdd = new Magazine(title, isbn, brand, issue, id, status);
                magazineToAdd.setCopies(copies);
                magazineToAdd.setBorrowed(isBorrowed);
                magazineToAdd.setReceivedDateCustom(ldt);
                return magazineToAdd;

            case "EM":
                String ebrand = data[NINTH_INDEX];
                String eissue = data[TENTH_INDEX];
                String mlink = data[ELEVENTH_INDEX];
                EMagazine emagazineToAdd = new EMagazine(title, isbn, ebrand, eissue, id,
                        status, mlink);
                emagazineToAdd.setCopies(copies);
                emagazineToAdd.setBorrowed(isBorrowed);
                emagazineToAdd.setReceivedDateCustom(ldt);
                return emagazineToAdd;

            case "CD":
                String creator = data[NINTH_INDEX];
                String type = data[TENTH_INDEX];
                CD cdToAdd = new CD(title, isbn, creator, type, id, status);
                cdToAdd.setCopies(copies);
                cdToAdd.setBorrowed(isBorrowed);
                cdToAdd.setReceivedDateCustom(ldt);
                return cdToAdd;

            default:
                LOGGER.info("Data corrupted, unable to load.");
                throw new SysLibException("Unknown resource type found, data corrupted.");
            }
        } catch (ClassCastException CCEx) {
            LOGGER.info("Unable to create resource.");
            throw new SysLibException("Error creating resource from data: " + CCEx.getMessage());
        }
    }

    /**
     * Creates an Event object from the parsed data line.
     *
     * @param data The parsed data line.
     * @return The created Event object.
     * @throws SysLibException If there is an issue parsing the data.
     */
    private Event createEvent(String[] data) throws SysLibException {
        try {
            String name = data[SECOND_INDEX];
            String description = data[THIRD_INDEX];
            LocalDate eventld = LocalDate.parse(data[FOURTH_INDEX]);
            Event eventToAdd = new Event(name, eventld, description);
            return eventToAdd;
        } catch (Exception e) {
            LOGGER.info("Unable to create event from data.");
            throw new SysLibException("Error creating event from data: " + e.getMessage());
        }
    }

    /**
     * Saves the current state of resources and events to the data file.
     *
     * @throws SysLibException If there is an issue writing to the file.
     */
    public void save() throws SysLibException {
        LOGGER.info("Starting to save data to storage file.");
        try (FileWriter fw = new FileWriter(this.filePath)) {
            for (Resource resource : container.getResourcesList()) {
                String resourceSaveFormat = getResourceSaveFormat(resource);
                fw.write(resourceSaveFormat);
            }
            for (Event event : container.getEventsList()) {
                String eventSaveFormat = getEventSaveFormat(event);
                fw.write(eventSaveFormat);
            }
        } catch (IOException e) {
            LOGGER.info("Unable to write to storage file.");
            throw new SysLibException("Error writing to file: " + filePath);
        }
        LOGGER.info("Successfully saved to storage file.");
    }

    /**
     * Generates a formatted string for saving a resource to the file.
     *
     * @param resourceToSave The resource to format.
     * @return The formatted string.
     * @throws SysLibException If there is an issue formatting the resource.
     */
    private String getResourceSaveFormat(Resource resourceToSave) throws SysLibException {
        String resourceSaveFormat;
        try {
            switch (resourceToSave.getTag()) {
            case "B": // Book
                Book book = (Book) resourceToSave;
                resourceSaveFormat = String.format("R | %s | %b | %s | %d | %s | %s | %s | %s | %s%n",
                        book.getTitle(),
                        book.isBorrowed(),
                        book.getISBN(),
                        book.getCopies(),
                        book.getTag(),
                        book.getStatus(),
                        book.getDateReceivedUnparsed(),
                        book.getAuthor(),
                        String.join(",", book.getGenre()));
                break;
            case "EB": // eBook
                EBook ebook = (EBook) resourceToSave;
                resourceSaveFormat = String.format("R | %s | %b | %s | %d | %s | %s | %s | %s | %s | %s%n",
                        ebook.getTitle(),
                        ebook.isBorrowed(),
                        ebook.getISBN(),
                        ebook.getCopies(),
                        ebook.getTag(),
                        ebook.getStatus(),
                        ebook.getDateReceivedUnparsed(),
                        ebook.getAuthor(),
                        String.join(",", ebook.getGenre()),
                        ebook.getLink());
                break;

            case "CD": // CD
                CD cd = (CD) resourceToSave;
                resourceSaveFormat = String.format("R | %s | %b | %s | %d | %s | %s | %s | %s | %s%n",
                        cd.getTitle(),
                        cd.isBorrowed(),
                        cd.getISBN(),
                        cd.getCopies(),
                        cd.getTag(),
                        cd.getStatus(),
                        cd.getDateReceivedUnparsed(),
                        cd.getCreator(),
                        cd.getType());
                break;

            case "M": // Magazine
                Magazine magazine = (Magazine) resourceToSave;
                resourceSaveFormat = String.format("R | %s | %b | %s | %d | %s | %s | %s | %s | %s%n",
                        magazine.getTitle(),
                        magazine.isBorrowed(),
                        magazine.getISBN(),
                        magazine.getCopies(),
                        magazine.getTag(),
                        magazine.getStatus(),
                        magazine.getDateReceivedUnparsed(),
                        magazine.getBrand(),
                        magazine.getIssue());
                break;

            case "EM": // eMagazine
                EMagazine emagazine = (EMagazine) resourceToSave;
                resourceSaveFormat = String.format("R | %s | %b | %s | %d | %s | %s | %s | %s | %s | %s%n",
                        emagazine.getTitle(),
                        emagazine.isBorrowed(),
                        emagazine.getISBN(),
                        emagazine.getCopies(),
                        emagazine.getTag(),
                        emagazine.getStatus(),
                        emagazine.getDateReceivedUnparsed(),
                        emagazine.getBrand(),
                        emagazine.getIssue(),
                        emagazine.getLink());
                break;

            case "N": // Newspaper
                Newspaper newspaper = (Newspaper) resourceToSave;
                resourceSaveFormat = String.format("R | %s | %b | %s | %d | %s | %s | %s | %s | %s%n",
                        newspaper.getTitle(),
                        newspaper.isBorrowed(),
                        newspaper.getISBN(),
                        newspaper.getCopies(),
                        newspaper.getTag(),
                        newspaper.getStatus(),
                        newspaper.getDateReceivedUnparsed(),
                        newspaper.getPublisher(),
                        newspaper.getEdition());
                break;

            case "EN": // eNewspaper
                ENewspaper enewspaper = (ENewspaper) resourceToSave;
                resourceSaveFormat = String.format("R | %s | %b | %s | %d | %s | %s | %s | %s | %s | %s%n",
                        enewspaper.getTitle(),
                        enewspaper.isBorrowed(),
                        enewspaper.getISBN(),
                        enewspaper.getCopies(),
                        enewspaper.getTag(),
                        enewspaper.getStatus(),
                        enewspaper.getDateReceivedUnparsed(),
                        enewspaper.getPublisher(),
                        enewspaper.getEdition(),
                        enewspaper.getLink());
                break;
            default:
                LOGGER.info("Corrupted data in list. Can't save.");
                throw new SysLibException("Unknown data type in list. Can't store it in file.");
            }

        } catch (IllegalFormatException IFEx) {
            LOGGER.info("Formatting error when trying to save..");
            throw new SysLibException("Error formatting resource for save: " + IFEx.getMessage());
        }
        return resourceSaveFormat;
    }

    /**
     * Generates a formatted string for saving an event to the file.
     *
     * @param eventToSave The event to format.
     * @return The formatted string.
     */
    private String getEventSaveFormat(Event eventToSave) {
        String eventSaveFormat = String.format("E | %s | %s | %s%n",
                eventToSave.getName(),
                eventToSave.getDescription(),
                eventToSave.getDate());
        return eventSaveFormat;
    }
}
