package seedu.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import seedu.data.GenericList;
import seedu.data.resources.Book;
import seedu.data.resources.EBook;
import seedu.data.resources.EMagazine;
import seedu.data.resources.Magazine;
import seedu.data.resources.ENewspaper;
import seedu.data.resources.Newspaper;
import seedu.data.resources.CD;
import seedu.data.resources.Resource;
import seedu.data.Status;
import seedu.exception.SysLibException;
import seedu.data.events.Event;

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
    public static final int TWELFTH_INDEX = 11;

    protected File dataFile;
    protected String filePath;
    protected GenericList<Resource, Event> container;

    public Storage(String filePath, GenericList<Resource, Event> container) {
        this.filePath = filePath;
        this.dataFile = new File(filePath);
        this.container = container;
    }

    public void load(List<Resource> resources, List<Event> events) throws SysLibException {
        try {
            if (this.dataFile.createNewFile()) {
                System.out.println("Data file not found @ " + this.filePath +
                        "\nCreating new data file @ " + this.filePath);
            } else {
                Scanner dataScanner = new Scanner(dataFile);
                while (dataScanner.hasNext()) {
                    String dataLine = dataScanner.nextLine();
                    String[] splitLineArguments = dataLine.split(" \\| ");

                    if (splitLineArguments[FIRST_INDEX].equals("R")) {
                        String title = splitLineArguments[SECOND_INDEX];
                        boolean isBorrowed = Boolean.parseBoolean(splitLineArguments[THIRD_INDEX]);
                        String isbn = splitLineArguments[FOURTH_INDEX];
                        int copies = Integer.parseInt(splitLineArguments[FIFTH_INDEX]);
                        String tag = splitLineArguments[SIXTH_INDEX];
                        int id = Integer.parseInt(splitLineArguments[SEVENTH_INDEX]);
                        Status status = Status.valueOf(splitLineArguments[EIGHTH_INDEX]);
                        LocalDateTime ldt = LocalDateTime.parse(splitLineArguments[NINTH_INDEX]);

                        switch(tag){
                        case "B":
                            String author = splitLineArguments[TENTH_INDEX];
                            String[] genres = splitLineArguments[ELEVENTH_INDEX].split(",");
                            Book bookToAdd = new Book(title, isbn, author, genres, id, status);
                            bookToAdd.setCopies(copies);
                            bookToAdd.setBorrowed(isBorrowed);
                            bookToAdd.setReceivedDateCustom(ldt);
                            resources.add(bookToAdd);
                            break;

                        case "EB":
                            String eauthor = splitLineArguments[TENTH_INDEX];
                            String[] egenres = splitLineArguments[ELEVENTH_INDEX].split(",");
                            String blink = splitLineArguments[11];
                            EBook ebookToAdd = new EBook(title, isbn, eauthor, egenres, id, status, blink);
                            ebookToAdd.setCopies(copies);
                            ebookToAdd.setBorrowed(isBorrowed);
                            ebookToAdd.setReceivedDateCustom(ldt);
                            resources.add(ebookToAdd);
                            break;

                        case "N":
                            String publisher = splitLineArguments[TENTH_INDEX];
                            String edition = splitLineArguments[ELEVENTH_INDEX];
                            Newspaper newspaperToAdd = new Newspaper(title, isbn, publisher, edition, id, status);
                            newspaperToAdd.setCopies(copies);
                            newspaperToAdd.setBorrowed(isBorrowed);
                            newspaperToAdd.setReceivedDateCustom(ldt);
                            resources.add(newspaperToAdd);
                            break;

                        case "EN":
                            String epublisher = splitLineArguments[TENTH_INDEX];
                            String eedition = splitLineArguments[ELEVENTH_INDEX];
                            String nlink = splitLineArguments[11];
                            ENewspaper enewspaperToAdd = new ENewspaper(title, isbn, epublisher, eedition,
                                    id, status, nlink);
                            enewspaperToAdd.setCopies(copies);
                            enewspaperToAdd.setBorrowed(isBorrowed);
                            enewspaperToAdd.setReceivedDateCustom(ldt);
                            resources.add(enewspaperToAdd);
                            break;

                        case "M":
                            String brand = splitLineArguments[TENTH_INDEX];
                            String issue = splitLineArguments[ELEVENTH_INDEX];
                            Magazine magazineToAdd = new Magazine(title, isbn, brand, issue, id, status);
                            magazineToAdd.setCopies(copies);
                            magazineToAdd.setBorrowed(isBorrowed);
                            magazineToAdd.setReceivedDateCustom(ldt);
                            resources.add(magazineToAdd);
                            break;

                        case "EM":
                            String ebrand = splitLineArguments[TENTH_INDEX];
                            String eissue = splitLineArguments[ELEVENTH_INDEX];
                            String mlink = splitLineArguments[TWELFTH_INDEX];
                            EMagazine emagazineToAdd = new EMagazine(title, isbn, ebrand, eissue, id,
                                    status, mlink);
                            emagazineToAdd.setCopies(copies);
                            emagazineToAdd.setBorrowed(isBorrowed);
                            emagazineToAdd.setReceivedDateCustom(ldt);
                            resources.add(emagazineToAdd);
                            break;

                        case "CD":
                            String creator = splitLineArguments[TENTH_INDEX];
                            String type = splitLineArguments[ELEVENTH_INDEX];
                            CD cdToAdd = new CD(title, isbn, creator, type, id, status);
                            cdToAdd.setCopies(copies);
                            cdToAdd.setBorrowed(isBorrowed);
                            cdToAdd.setReceivedDateCustom(ldt);
                            resources.add(cdToAdd);
                            break;

                        default:
                            throw new SysLibException("Unknown resource type found, data corrupted.");
                        }
                    }else if (splitLineArguments[FIRST_INDEX].equals("E")) {
                        String name = splitLineArguments[SECOND_INDEX];
                        String description = splitLineArguments[THIRD_INDEX];
                        LocalDate eventld = LocalDate.parse(splitLineArguments[FOURTH_INDEX]);
                        Event eventToAdd = new Event(name, eventld, description);
                        events.add(eventToAdd);
                    } else {
                        throw new SysLibException("Corrupted data found, unable to load.");
                    }
                }
            }
        } catch (IOException IOEx) {
            throw new SysLibException("Unable to create storage file. Please try to run with admin permissions.");
        } catch (IllegalArgumentException | DateTimeParseException | ArrayIndexOutOfBoundsException IAEx){
            throw new SysLibException("Corrupted data found, unable to load.");
        }
    }

    public void save() throws SysLibException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            List<Resource> resourcelist = container.getResourceList();
            List<Event> eventlist = container.getEventList();
            for (Resource resourceToSave : resourcelist){
                String resourceSaveFormat = "";
                switch (resourceToSave.getTag()) {
                case "B": // Book
                    Book book = (Book) resourceToSave;
                    resourceSaveFormat = String.format("R | %s | %b | %s | %d | %s | %s | %s | %s | %s | %s%n",
                            book.getTitle(),
                            book.isBorrowed(),
                            book.getISBN(),
                            book.getCopies(),
                            book.getTag(),
                            book.getId(),
                            book.getStatus(),
                            book.getDateReceivedUnparsed(),
                            book.getAuthor(),
                            String.join(",", book.getGenre()));
                    break;
                case "EB": // eBook
                    EBook ebook = (EBook) resourceToSave;
                    resourceSaveFormat = String.format("R | %s | %b | %s | %d | %s | %s | %s | %s | %s | %s | %s%n",
                            ebook.getTitle(),
                            ebook.isBorrowed(),
                            ebook.getISBN(),
                            ebook.getCopies(),
                            ebook.getTag(),
                            ebook.getId(),
                            ebook.getStatus(),
                            ebook.getDateReceivedUnparsed(),
                            ebook.getAuthor(),
                            String.join(",", ebook.getGenre()),
                            ebook.getLink());
                    break;
                case "CD": // CD
                    CD cd = (CD) resourceToSave;
                    resourceSaveFormat = String.format("R | %s | %b | %s | %d | %s | %s | %s | %s | %s | %s%n",
                            cd.getTitle(),
                            cd.isBorrowed(),
                            cd.getISBN(),
                            cd.getCopies(),
                            cd.getTag(),
                            cd.getId(),
                            cd.getStatus(),
                            cd.getDateReceivedUnparsed(),
                            cd.getCreator(),
                            cd.getType());
                    break;
                case "M": // Magazine
                    Magazine magazine = (Magazine) resourceToSave;
                    resourceSaveFormat = String.format("R | %s | %b | %s | %d | %s | %s | %s | %s | %s | %s%n",
                            magazine.getTitle(),
                            magazine.isBorrowed(),
                            magazine.getISBN(),
                            magazine.getCopies(),
                            magazine.getTag(),
                            magazine.getId(),
                            magazine.getStatus(),
                            magazine.getDateReceivedUnparsed(),
                            magazine.getBrand(),
                            magazine.getIssue());
                    break;
                case "EM": // eMagazine
                    EMagazine emagazine = (EMagazine) resourceToSave;
                    resourceSaveFormat = String.format("R | %s | %b | %s | %d | %s | %s | %s | %s | %s%n",
                            emagazine.getTitle(),
                            emagazine.isBorrowed(),
                            emagazine.getISBN(),
                            emagazine.getCopies(),
                            emagazine.getTag(),
                            emagazine.getId(),
                            emagazine.getStatus(),
                            emagazine.getDateReceivedUnparsed(),
                            emagazine.getLink());
                    break;
                case "N": // Newspaper
                    Newspaper newspaper = (Newspaper) resourceToSave;
                    resourceSaveFormat = String.format("R | %s | %b | %s | %d | %s | %s | %s | %s | %s | %s%n",
                            newspaper.getTitle(),
                            newspaper.isBorrowed(),
                            newspaper.getISBN(),
                            newspaper.getCopies(),
                            newspaper.getTag(),
                            newspaper.getId(),
                            newspaper.getStatus(),
                            newspaper.getDateReceivedUnparsed(),
                            newspaper.getPublisher(),
                            newspaper.getEdition());
                    break;
                case "EN": // eNewspaper
                    ENewspaper enewspaper = (ENewspaper) resourceToSave;
                    resourceSaveFormat = String.format("R | %s | %b | %s | %d | %s | %s | %s | %s | %s%n",
                            enewspaper.getTitle(),
                            enewspaper.isBorrowed(),
                            enewspaper.getISBN(),
                            enewspaper.getCopies(),
                            enewspaper.getTag(),
                            enewspaper.getId(),
                            enewspaper.getStatus(),
                            enewspaper.getDateReceivedUnparsed(),
                            enewspaper.getLink());
                    break;
                default:
                    throw new SysLibException("Unknown data type in list. Can't store it in file.");
                }
                fw.write(resourceSaveFormat);
            }
            for (Event eventToSave : eventlist){
                String eventSaveFormat = String.format("E | %s | %s | %s%n",
                        eventToSave.getName(),
                        eventToSave.getDescription(),
                        eventToSave.getDate());
                fw.write(eventSaveFormat);
            }
            fw.close();
        } catch (IOException IOex){
            throw new SysLibException("Unable to save content to: " + filePath);
        }

    }

}
