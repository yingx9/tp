# User Guide
<img src="images/SysLib Logo.png" /> 

## Contents
* [Introduction](#introduction--return-to-contents)
* [Quick start](#quick-start--return-to-contents)
* [Features](#features--return-to-contents)
    * [Save your work](#save-your-work--return-to-contents)
    * [Add a listing: `add`](#add-a-listing-add--return-to-contents)
    * [Delete a listing: `delete`](#delete-a-listing-delete--return-to-contents)
    * [List items: `list`](#list-all-items-list--return-to-contents)
    * [Find listing by search parameter: `find`](#find-specific-listings-find--return-to-contents)
    * [Edit a listing: `edit`](#edit-a-listing-edit--return-to-contents)
    * [Events](#events--return-to-contents)
      * [Event adding: `eventadd`](#event-adding-eventadd--return-to-contents)
      * [Event listing: `eventlist`](#event-listing-eventlist--return-to-contents)
      * [Event delete: `eventdelete`](#event-delete-eventdelete--return-to-contents)
      * [Event edit: `eventedit`](#event-edit-eventedit--return-to-contents)
    * [Summary of resources & events: `summary`](#summary-summary--return-to-contents)
    * [Exiting the program : `exit`](#exiting-the-program--exit--return-to-contents)
    * [Get help: `help`](#viewing-help--help--return-to-contents)
* [FAQ](#faq--return-to-contents)
* [Known Issues](#known-issues--return-to-contents)
* [Command summary](#command-summary--return-to-contents)

## Introduction | [Return to contents](#contents)

Welcome to the SysLib User Guide: your all-in-one document to learn how to use SysLib to manage your work and responsibilities as a librarian. 

Our user guide is for every librarian whether you're a beginner, novice, or expert in using a CLI library management software. 

From viewing, adding, searching, and many more, SysLib provides all the features you need to optimize your work from hours to seconds. In no time, you will be typing intuitive commands to manage your library resources and upcoming events!

Without further ado, let's get started with how to navigate the guide!

### How to use the User Guide 

Information about how to use
the guide (e.g. how to navigate
the document, meaning of
icons and formatting used)


## Quick start | [Return to contents](#contents)
1. Make sure that you have Java 11 or above installed on your computer.
    - Open terminal and type `java --version`
    - [How do I check the version of my Java](https://www.java.com/en/download/help/version_manual.html)
2. [Download the latest release](https://github.com/AY2324S1-CS2113T-W11-1/tp/releases/tag/v1.0) of `Syslib.jar`
3. Copy the `Syslib.jar` file into a folder on its own.
4. Open a command terminal, type `cd <FILE_DIRECTORY>`, where <FILE_DIRECTORY> refers to the directory to the `Syslib.jar` file.
5. Run the following command: `java -jar Syslib.jar`. You should see the following welcome screen.
```
____________________________________________________________
 ____            _     _ _        ____ _     ___ 
/ ___| _   _ ___| |   (_) |__    / ___| |   |_ _|
\___ \| | | / __| |   | | '_ \  | |   | |    | | 
 ___) | |_| \__ \ |___| | |_) | | |___| |___ | | 
|____/ \__, |___/_____|_|_.__/   \____|_____|___|
       |___/                                     
What would you like to do?
____________________________________________________________
```


## Features | [Return to contents](#contents)

> Note:
> - Items in square brackets `[]` are optional arguments.
> - Items with `...` after them can be used multiple times
    >    * e.g `[/g GENRE]` ... can be used as `/g Horror /g Fantasy`

## Save your work | [Return to contents](#contents)
> - All resources and events in the current list will automatically be saved into an offline file `storage.txt` on exit.
> - Resources and events from `storage.txt` will be loaded into the program at every start.
> - The `storage.txt` file will be located in the same directory as the jar file.
> - On start:
> ```
> ____________________________________________________________
> Storage file found @ .\storage.txt
> Loaded 2 resources and 1 events!
> ____________________________________________________________
> >```
> - On exit: 
> ```
> > exit
> Thanks for using SysLib CLI! We have saved the current resources and events created.
> Hope to see you again soon!
> ```

## Add a listing: `add` | [Return to contents](#contents)

Adds a new resource to the library inventory.

#### Types of tags:
`[B]` - Books
`[CD]` - CDs
`[M]` - Magazines
`[N]` - Newspapers

> Note:
> - Anything with `e` prior is an electronic version 
> i.e. `[eB]` is an eBook, `[eM]` is an eMagazine, `[eN]` is an eNewspaper
> - Anything with `e` prior is an electronic version
> i.e. `[eB]` is an eBook, `[eM]` is an eMagazine

#### Types of Status:
- `AVAILABLE`
- `BORROWED`
- `LOST`
> Note:
> - Resource will default to `AVAILABLE` if no status is input

### Add Book
>Notes about genre:
> - Multiple genres are allowed for a single resource. You can separate the different genres using comma followed by a space `, `.

Format: `add /i ISBN /t TITLE /a AUTHOR /tag TAG [/g GENRE /s STATUS]...`

**Example input:**
```
add /i 9780763630188 /t Moby Dick /a Herman Melville /tag B /g Adventure, Fiction
add /i 9780763630187 /t Harry Squatter /a J.K. /tag B /g History /s lost
```
**Example response:**
```
This book is added:
[B]  ID: 5 Title: Moby Dick ISBN: 9780763630188 Author: Herman Melville Genre: Adventure, Fiction Status: AVAILABLE Received Date: 08 Nov 2023
____________________________________________________________
```

### Add eBook
>Notes about genre:
> - Multiple genres are allowed for a single resource. You can separate the different genres using comma followed by a space `, `.

Format: `add /i ISBN /t TITLE /a AUTHOR /tag TAG /l LINK [/g GENRE /s STATUS]`

**Example input:**
```
add /i 9780763630188 /t Moby Dick /a Herman Melville /tag eB /l www.abc.com /g Adventure, Fiction
```
**Example response:**
```
This eBook is added:
[EB]  ID: 7 Title: Moby Dick ISBN: 9780763630188 Author: Herman Melville Genre: Adventure, Fiction Link: www.abc.com
____________________________________________________________
```

### Add CD
Format: `add /i ISBN /t TITLE /c CREATOR /ty TYPE /tag TAG [/s STATUS]`

**Example input:**
```
add /i 9780763630188 /t Moby Dick /c Herman Melville /ty Audio Book /tag CD
```
**Example response:**
```
This CD is added:
[CD]  ID: 8 Title: Moby Dick ISBN: 9780763630188 Creator: Herman Melville Type: Audio Book Status: AVAILABLE
____________________________________________________________
```

### Add Magazine
Format: `add /i ISBN /t TITLE /b BRAND /is ISSUE /tag TAG [/s STATUS]`

**Example input:**
```
add /i 9780763630188 /t 2023 Hottest Trends /b Vogue /is Volume 32, Issue 5, May 2023 /tag M
```
**Example response:**
```
This magazine is added: 
[M]  ID: 4 Title: 2023 Hottest Trends ISBN: 9780763630188 Brand: Vogue Issue: Volume 32, Issue 5, May 2023 Status: AVAILABLE
____________________________________________________________
```

### Add eMagazine
Format: `add /i ISBN /t TITLE /b BRAND /is ISSUE /tag TAG /l LINK [/s STATUS]`

**Example input:**
```
add /i 9780763630188 /t 2023 Hottest Trends /b Vogue /is Volume 32, Issue 5, May 2023 /tag eM /l www.abc.com
```
**Example response:**
```
This eMagazine is added:
[EM]  ID: 10 Title: 2023 Hottest Trends ISBN: 9780763630188 Brand: Vogue Issue: Volume 32, Issue 5, May 2023 Link: www.abc.com
____________________________________________________________
```

### Add Newspaper
Format: `add /i ISBN /t TITLE /p PUBLISHER /ed EDITION /tag TAG [/s STATUS]`
**Example input:**
```
add /i 9780763630188 /t Forbes 30 Under 30 2023 /p The Straits Times /ed Entrepreneurs /tag N
```
**Example response:**
```
This newspaper is added:
[N]  ID: 11 Title: Forbes 30 Under 30 2023 ISBN: 9780763630188 Publisher: The Straits Times Edition: Entrepreneurs Status: AVAILABLE
____________________________________________________________
```

### Add eNewspaper
Format: `add /i ISBN /t TITLE /p PUBLISHER /ed EDITION /tag TAG /l LINK [/s STATUS]`

**Example input:**
```
add /i 9780763630188 /t Forbes 30 Under 30 2023 /p The Straits Times /ed Entrepreneurs /tag eN /l www.abc.com
```
**Example response:**
```
This eNewspaper is added:
[EN]  ID: 12 Title: Forbes 30 Under 30 2023 ISBN: 9780763630188 Publisher: The Straits Times Edition: Entrepreneurs Link: www.abc.com
____________________________________________________________
```

## Delete a listing: `delete` | [Return to contents](#contents)

Deletes the resource with the specified ID from the library inventory. You can find the ID using the `list` command.

Format: `delete /id ID`

**Example input:**
```
delete /id 8
```
**Example input:**
```
Looking for ID: 8...
This resource is removed: 
[B]  ID: 123456789 Title: Moby Dick ISBN: 9780763630188 Author: Herman Melville Genre: Adventure, Fiction Status: LOST
____________________________________________________________
```

## List all items: `list` | [Return to contents](#contents)


The `list` command displays every resource in the library along with their details and categorized by their type `Book`, `Magazine`,`CD`, or `Newspaper`, giving you a quick and neat overview of all the resources in one place. 

Looking for a more specific list? `list` also offers you the capability to **filter** for a specific **tag**, **genre**, or **status**, generating a tailored list for your needs. 

**Format:** `list [/tag TAG /g GENRE /s STATUS]`

**Notes:**
- Including more than one filter will list resources that satisfy **ALL** given filters. 
- For example, `list /tag B /g Horror` will list Books with Horror genre.

**Example input:**
```
list
list /tag B
list /tag B /g Fiction
list /g Thrill
list /s Available
```

**Example output:**
```
Listing all resources in the Library:

                                                                       [BOOKS]
------------------------------------------------------------------------------------------------------------------------------------------------
ID     Tag  Title                   ISBN          Author                   Genre                        Link           Status    Received Date  
------------------------------------------------------------------------------------------------------------------------------------------------
1      B    Moby Dick               9780763630188 Herman Melville          Adventure, Fiction           null           AVAILABLE 08 Nov 2023    
2      B    Harry Squatter          9780763630187 J.K.                     History                      null           LOST      08 Nov 2023    
3      EB   Moby Dick               9780763630188 Herman Melville          Adventure, Fiction           www.abc.com    AVAILABLE 08 Nov 2023    

                                                                       [MAGAZINES]
------------------------------------------------------------------------------------------------------------------------------------------------
ID     Tag  Title                   ISBN          Brand                    Issue                        Link           Status    Received Date  
------------------------------------------------------------------------------------------------------------------------------------------------
5      M    2023 Hottest Trends     9780763630188 Vogue                    Volume 32, Issue 5, May 2023 null           AVAILABLE 08 Nov 2023    
6      EM   2023 Hottest Trends     9780763630188 Vogue                    Volume 32, Issue 5, May 2023 www.abc.com    AVAILABLE 08 Nov 2023    

                                                                        [CDS]
------------------------------------------------------------------------------------------------------------------------------------------------
ID     Tag  Title                   ISBN          Creator                  Type                         Link           Status    Received Date  
------------------------------------------------------------------------------------------------------------------------------------------------
4      CD   Moby Dick               9780763630188 Herman Melville          Audio Book                   null           AVAILABLE 08 Nov 2023    

                                                                      [NEWSPAPERS]
------------------------------------------------------------------------------------------------------------------------------------------------
ID     Tag  Title                   ISBN          Publisher                Edition                      Link           Status    Received Date  
------------------------------------------------------------------------------------------------------------------------------------------------
7      N    Forbes 30 Under 30 2023 9780763630188 The Straits Times        Entrepreneurs                null           AVAILABLE 08 Nov 2023    
8      EN   Forbes 30 Under 30 2023 9780763630188 The Straits Times        Entrepreneurs                www.abc.com    AVAILABLE 08 Nov 2023    


There are currently 8 resource(s).
____________________________________________________________
```

## Find specific listings: `find` | [Return to contents](#contents)
Find a resource by title, author, ISBN or given id.

You can use multiple filters, and only results that match ALL the given criteria will be returned.

> For non-book resources, `author` refers to `publisher`,`creator` and `brand` for Newspapers, CD's and Magazines 
> respectively.

Format: `find [/t TITLE OR /i ISBN OR /a AUTHOR/PUBLISHER/BRAND/CREATOR OR /id ID]`

**Example input:**
```
find /t Moby Dick
find /i 9780763630188
find /a J. K. Rowling
find /id 123456789
find /id 123456789 /i 9780763630188 
find /a Vogue
```

**Example output:**
```
Here are resources that matched the given filters:
                                                                                [MAGAZINES]
----------------------------------------------------------------------------------------------------------------------------------------------------
ID             Tag  Title               ISBN          Brand                    Issue                        Link           Status    Received Date  
----------------------------------------------------------------------------------------------------------------------------------------------------
5              M    2023 Hottest Trends 9780763630188 Vogue                    Volume 32, Issue 5, May 2023 null           AVAILABLE 07 Nov 2023    


There are currently 1 resource(s).
____________________________________________________________
```

## Edit a listing: `edit` | [Return to contents](#contents)

We all know the horror of making a typo and having to delete and add a resource again—absolutely _dreadful_. 

Fear not! Update a resource's details using the `edit` command and fix your typos in a flash. 

**Format:** `edit /id ID /argumentname ARGUMENT [/argumentname2 ARGUMENT2..]`

**Notes:**

- If you have forgotten the **ID**, execute `list` to locate your target resource and ID.
- At least **one** argument to edit must be given. 
- You can edit multiple details in one go by specifying multiple arguments. E.g `edit /id 1 /t NEWTITLE /a NEWAUTHOR` updates title and author. 

**Argument Names:**

Argument names differ based on resource type. The table below shows the argument names you can enter for each resource type. 

| Type                     | Argument Names                                                                   | Notes                                                                                                                                                                             |
|--------------------------|----------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Book<br/>eBook           | /t TITLE <br/>/a AUTHOR<br/>/g GENRES<br/>/s STATUS<br/>/l LINK<br/>/i ISBN      | **/g GENRES:** <br/>If you're inputting **multiple genres**, separate them by  with comma `,`. <br/><br/>For example:  `/g Horror, Fantasy` <br/>**/l LINK:**<br/>For eBook only. |
| Magazine<br/>eMagazine   | /t TITLE <br/>/b BRAND<br/>/is ISSUE<br/>/s STATUS<br/>/l LINK<br/>/i ISBN       | **/l LINK:**<br/>For eMagazine only.                                                                                                                                              |
| Newspaper<br/>eNewspaper | /t TITLE <br/>/p PUBLISHER<br/>/ed EDITION<br/>/s STATUS<br/>/l LINK<br/>/i ISBN | **/l LINK:**<br/>For eNewspaper only.                                                                                                                                             |
| CD                       | /t TITLE <br/>/c CREATOR<br/>/ty TYPE<br/>/s STATUS<br/>/i ISBN                  |                                                                                                                                                                                   |

For example, if you would like to update a eBook, you can edit the attributes given under the **Argument Names** column, that is the title, author, genre, status, link, and isbn.

**Example input:**
```
edit /id 1 /t NEW_TITLE
edit /id 2 /t NEW_TITLE /a NEW_AUTHOR
edit /id 2 /g Horror, Adventure
edit /id 3 /s lost
```

**Example output:**
```
Successfully updated! Your updated resource: 

[B]  ID: 3 Title: Mary ISBN: 123 Author: John Genre: Horror, Adventure Status: LOST
____________________________________________________________
```

## Events | [Return to contents](#contents)
>Note:
> - Events are stored separately from resources
> - They are stored in chronological order(events that are happening sooner are closer to index 0)

## Event adding: `eventadd` | [Return to contents](#contents)
>Notes about description:
> - `desc` is optional for all events
> - Event without descriptions with have it as `null`

Add an event to the eventList

Format: `eventadd /t TITLE /date DATE [/desc DESCRIPTION]`

**Example input:**
```
eventadd /t Fan meetup for xxx /date 11-11-2001
eventadd /t Meet and Greet for xxx /date 10-11-2010 /desc buffet style
```

**Example output:**
```
Event inserted at: 0
____________________________________________________________
```

## Event listing: `eventlist` | [Return to contents](#contents)

Display all events in the eventList

Format: `eventlist`

**Example input:**
```
eventlist
```

**Example output:**
```
This is the current event list:
0: Fan meetup for xxx | 11-11-2001 | null
1: Meet and Greet for xxx | 10-11-2010 | buffet style
____________________________________________________________
```

## Event delete: `eventdelete` | [Return to contents](#contents)
>Notes about INDEX:
> - INDEX starts from 0 and can be viewed by calling `eventlist`
> - INDEX might change as those with earlier dates are sorted first

Delete an event to the eventList

Format: `eventdelete /i INDEX`

**Example input:**
```
eventdelete /i 0
```

**Example output:**
```
This event is removed:
Fan meetup for xxx | 11-11-2001 | null
____________________________________________________________
```

## Event edit: `eventedit` | [Return to contents](#contents)
>Notes about INDEX:
> - INDEX starts from 0 and can be viewed by calling `eventlist`
> - INDEX might change as those with earlier dates are sorted first

Delete an event to the eventList

Format: `eventedit /i INDEX [/t TITLE /date DATE /desc DESCRIPTION]`

**Example input:**
```
eventedit /i 0 /t NEW TITLE
eventedit /i 2 /t NEW TITLE /date 23 Jan 2024 /desc NEW DESCRIPTION
```

**Example output:**
```
Event edited successfully. New event details:
0: NEW TITLE | 23-01-2024 | NEW DESCRIPTION
____________________________________________________________
```

## Summary: `summary` | [Return to contents](#contents)
Provide a summary of resources added and upcoming 3 events (e.g. summary)

Format: `summary`

**Example input:**
```
eventedit /i 0 /t NEW TITLE
```

**Example output**
```
Total Resources: 26
Total Books: [████████████████] 12
Total CDs: [███] 2
Total Magazines: [██████] 4
Total E-Books: [█] 1
Total E-Magazines: [████] 3
Total Newspapers: [███] 2
Total E-Newspapers: [███] 2

Summary of Events:
Total Events: 7
Upcoming Events (Next 3):
1. Storey telling session | 21 Dec 2023 | null
2. Maintenance | 21 Dec 2023 | null
3. New Year | 01 Jan 2024 | null
____________________________________________________________
```
## Exiting the Program : `exit` | [Return to contents](#contents)
Exits the program, all data in resource list and event list will be saved to a storage file.

Format: `Exit`

Example:
```
> exit
Thanks for using SysLib CLI! We have saved the current resources and events created.
Hope to see you again soon!
```

## Viewing help : `help` | [Return to contents](#contents)
Displays a list of available commands with examples and their syntax format.

Format: `help`

Example:
```
Commands available:
add: adds a new resource to the library inventory.(e.g. add /id ID /t TITLE /a AUTHOR /tag TAG /i ISBN [/g GENRE])
delete: deletes the resource with the specified ID from the library inventory. (e.g. delete /id 123456789)
list: list all resources OR filter by certain tags or genre.(e.g. list /tag B /g Fiction
find: find a resource by title, author(same as publisher/creator/brand), ISBN or given id. (e.g. find /i 9780763630188)
edit: Edit a listing by entering its isbn to update its details. (e.g. edit /i 123 /t NEW_TITLE /a NEW_AUTHOR)
eventadd: Add an event to the event list (e.g. eventadd /t TITLE /date DATE [/desc DESCRIPTION])
eventlist: List out all the event list (e.g. eventlist)
eventdelete: Delete an event in the event list based on the index (e.g. eventdelete /i INDEX)
eventedit: Edit an event in the event based (e.g eventedit /i INDEX [/t TITLE /date DATE /desc DESCRIPTION])
summary: Provide a summary of resources added and upcoming 3 events (e.g. summary)
exit: displays a farewell message and exits the program (e.g. exit)

For more information, please refer to our user guide at:https://ay2324s1-cs2113t-w11-1.github.io/tp/UserGuide.html
____________________________________________________________
```


## FAQ | [Return to contents](#contents)

...

## Known Issues | [Return to contents](#contents)

...

## Command summary | [Return to contents](#contents)

| Action                | Command                                                                                                                        |
|-----------------------|--------------------------------------------------------------------------------------------------------------------------------|
| Add listing           | `add /i <ISBN of item> /t <title of listing> /a <author of item> /tag <type of item> [/g <genre of item> /s <status of item>]` |
| Delete listing        | `delete /id <id of listing>`                                                                                                   |
| Listing all items     | `list [/tag <type of item> /g <genre of item> /s <status of item>]`                                                            |
| Find specific listing | `find [/t <title of listing> OR /i <ISBN of item> OR /a AUTHOR OR /id ID]`                                                     |
| Edit a listing        | `edit /id ID /argument1 <ARGUMENT1> [/argument2 <ARGUMENT2>]...`                                                               |
| Exit                  | `exit`                                                                                                                         |
| Event Add             | `eventadd /t TITLE /date DATE [/desc DESCRIPTION]` (Format for date DD-MM-YYYY)                                                |
| Event Delete          | `eventdelete /i INDEX`                                                                                                         |
| Event Listing         | `eventlist`                                                                                                                    |
| View help             | `help`                                                                                                                         |
| Exit                  | `exit`                                                                                                                         |



