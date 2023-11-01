package seedu.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import seedu.data.*;
import seedu.parser.Parser;

public class Storage {
    protected File dataFile;
    protected String filePath;
    protected Parser parser;
    public static int FIRST_INDEX=0;

    public Storage(String filePath, Parser parser) {
        this.filePath = filePath;
        this.dataFile = new File(filePath);
        this.parser = parser;

    }

    public List<Resource> load() throws SysLibException {
        List<Resource> resources = new ArrayList<>();
        try {
            if (this.dataFile.createNewFile()) {
                System.out.println("Data file not found @ " + this.filePath +
                        "\nCreating new data file @ " + this.filePath);
            } else {
                Scanner dataScanner = new Scanner(dataFile);
                while (dataScanner.hasNext()) {
                    String dataLine = dataScanner.nextLine();
                    String[] splitLineArguments = dataLine.split(" \\| ");
                    String title = splitLineArguments[1].trim();
                    boolean isBorrowed = Boolean.parseBoolean(splitLineArguments[2].trim());
                    String isbn = splitLineArguments[3].trim();
                    int copies = Integer.parseInt(splitLineArguments[4].trim());
                    String tag = splitLineArguments[5].trim();
                    int id = Integer.parseInt(splitLineArguments[6].trim());
                    Status status = Status.valueOf(splitLineArguments[7].trim());
                    LocalDateTime LDT = LocalDateTime.parse(splitLineArguments[8].trim());

                    if (splitLineArguments[FIRST_INDEX].equals("R")) {
                        switch(tag){
                            case "B":
                                String author = splitLineArguments[9].trim();
                                String[] genres = splitLineArguments[10].split(",");
                                Book bookToAdd = new Book(title, isbn, author, genres, id, status);
                                bookToAdd.setCopies(copies);
                                bookToAdd.setBorrowed(isBorrowed);
                                bookToAdd.setReceivedDateCustom(LDT);
                                resources.add(bookToAdd);
                                break;

                            case "EB":
                                String e_author = splitLineArguments[9].trim();
                                String[] e_genres = splitLineArguments[10].split(",");
                                String b_link = splitLineArguments[11];
                                eBook e_bookToAdd = new eBook(title, isbn, e_author, e_genres, id, status, b_link);
                                e_bookToAdd.setCopies(copies);
                                e_bookToAdd.setBorrowed(isBorrowed);
                                e_bookToAdd.setReceivedDateCustom(LDT);
                                resources.add(e_bookToAdd);
                                break;
                                
                            case "N":
                                String publisher = splitLineArguments[9].trim();
                                String edition = splitLineArguments[10].trim();
                                Newspaper newspaperToAdd = new Newspaper(title, isbn, publisher, edition, id, status);
                                newspaperToAdd.setCopies(copies);
                                newspaperToAdd.setBorrowed(isBorrowed);
                                newspaperToAdd.setReceivedDateCustom(LDT);
                                resources.add(newspaperToAdd);
                                break;
                                
                            case "EN":
                                String e_publisher = splitLineArguments[9].trim();
                                String e_edition = splitLineArguments[10].trim();
                                String n_link = splitLineArguments[11];
                                eNewspaper e_newspaperToAdd = new eNewspaper(title, isbn, e_publisher, e_edition, 
                                        id, status, n_link);
                                e_newspaperToAdd.setCopies(copies);
                                e_newspaperToAdd.setBorrowed(isBorrowed);
                                e_newspaperToAdd.setReceivedDateCustom(LDT);
                                resources.add(e_newspaperToAdd);
                                break;

                            case "M":
                                String brand = splitLineArguments[9].trim();
                                String issue = splitLineArguments[10].trim();
                                Magazine magazineToAdd = new Magazine(title, isbn, brand, issue, id, status);
                                magazineToAdd.setCopies(copies);
                                magazineToAdd.setBorrowed(isBorrowed);
                                magazineToAdd.setReceivedDateCustom(LDT);
                                resources.add(magazineToAdd);
                                break;

                            case "EM":
                                String e_brand = splitLineArguments[9].trim();
                                String e_issue = splitLineArguments[10].trim();
                                String m_link = splitLineArguments[11];
                                eMagazine e_magazineToAdd = new eMagazine(title, isbn, e_brand, e_issue, id,
                                        status, m_link);
                                e_magazineToAdd.setCopies(copies);
                                e_magazineToAdd.setBorrowed(isBorrowed);
                                e_magazineToAdd.setReceivedDateCustom(LDT);
                                resources.add(e_magazineToAdd);
                                break;

                            case "CD":
                                String creator = splitLineArguments[9].trim();
                                String type = splitLineArguments[10].trim();
                                CD cdToAdd = new CD(title, isbn, creator, type, id, status);
                                cdToAdd.setCopies(copies);
                                cdToAdd.setBorrowed(isBorrowed);
                                cdToAdd.setReceivedDateCustom(LDT);
                                resources.add(cdToAdd);
                                break;

                            default:
                                throw new SysLibException("Unknown resource type found, data corrupted.");

                        }
                    }else if (splitLineArguments[FIRST_INDEX].equals("E")) {
                        System.out.println("event");
                    } else {
                        throw new SysLibException("Unknown data type found, data corrupted.");
                    }
                }
            }
        }
        catch (IOException IOEx) {
            throw new SysLibException("Unable to create storage file. Please try to run with admin permissions.");
        }
        return resources;
    }

    public void save() throws SysLibException {
        try {
            FileWriter FW = new FileWriter(this.filePath);
            List<Resource> resourcelist = parser.getResourceList();
            List<Event> eventlist = parser.getEventList();
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
                                String.join(", ", book.getGenre()));
                        break;
                    case "EB": // eBook
                        eBook ebook = (eBook) resourceToSave;
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
                                String.join(", ", ebook.getGenre()),
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
                        eMagazine emagazine = (eMagazine) resourceToSave;
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
                        eNewspaper enewspaper = (eNewspaper) resourceToSave;
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
                }
                FW.write(resourceSaveFormat);
            }
            for (Event eventToSave : eventlist){
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String eventSaveFormat = String.format("E | %s | %s | %s%n",
                        eventToSave.getName(),
                        eventToSave.getDescription(),
                        sdf.format(eventToSave.getDate()));
                FW.write(eventSaveFormat);
            }
            FW.close();
        } catch (IOException IOex){
            throw new SysLibException("Unable to save to find @ ./storage.txt");
        }

    }

}