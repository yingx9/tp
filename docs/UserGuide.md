# User Guide
<img src="images/SysLib Logo.png" /> 

## Contents
* [Introduction](#introduction--return-to-contents)
* [Quick Start](#quick-start--return-to-contents)
* [Features](#features--return-to-contents)
    * [Save Your Work](#save-your-work--return-to-contents)
    * [Add a Listing: `add`](#add-a-listing-add--return-to-contents)
    * [Delete a Listing: `delete`](#delete-a-listing-delete--return-to-contents)
    * [List Items: `list`](#list-all-items-list--return-to-contents)
    * [Find Listing by Search Parameter: `find`](#find-specific-listings-find--return-to-contents)
    * [Edit a Listing: `edit`](#edit-a-listing-edit--return-to-contents)
    * [Events](#events--return-to-contents)
      * [Event Adding: `eventadd`](#event-adding-eventadd--return-to-contents)
      * [Event Listing: `eventlist`](#event-listing-eventlist--return-to-contents)
      * [Event Delete: `eventdelete`](#event-delete-eventdelete--return-to-contents)
      * [Event Edit: `eventedit`](#event-edit-eventedit--return-to-contents)
    * [Summary of Resources & Events: `summary`](#summary-summary--return-to-contents)
    * [Exiting the Program : `exit`](#exiting-the-program--exit--return-to-contents)
    * [Get Help: `help`](#viewing-help--help--return-to-contents)
* [FAQ](#faq--return-to-contents)
* [Known Issues](#known-issues--return-to-contents)
* [Command Summary](#command-summary--return-to-contents)

## Introduction | [Return to Contents](#contents)

Welcome to the SysLib User Guide: your all-in-one document to learn how to use SysLib to manage your work and responsibilities as a librarian. 

Our user guide is for every librarian whether you're a beginner, novice, or expert in using a CLI library management software. 

From viewing, adding, searching, and many more, SysLib provides all the features you need to optimize your work from hours to seconds. In no time, you will be typing intuitive commands to manage your library resources and upcoming events!

Without further ado, let's get started with how to navigate the guide!

### How to Use the User Guide 

Information about how to use
the guide (e.g. how to navigate
the document, meaning of
icons and formatting used)


**Note**: Important pointers to take note of

**Format**:
- Capital letters - placeholders for your input
- Small letters - exact commands to enter
- / - indicates the type of information you are entering 
- [] - optional arguments


## Quick Start | [Return to Contents](#contents)
1. Make sure that you have Java 11 or above installed on your computer.
    - Open terminal and type `java --version`
    - [How do I check the version of my Java](https://www.java.com/en/download/help/version_manual.html)
2. [Download the latest release](https://github.com/AY2324S1-CS2113T-W11-1/tp/releases/tag/v1.0) of `Syslib.jar`
3. Copy the `Syslib.jar` file into a folder on its own.
4. Open a command terminal, type `cd <FILE_DIRECTORY>`, where <FILE_DIRECTORY> refers to the directory to the `Syslib.jar` file.
5. Run the following command: `java -jar Syslib.jar`. You should see the following welcome screen.

```
____________________________________________________________
             .....................                  
          -##@*+*@*++++++++++#@++##                 
         .@. @-=%=            *#-+%                 
         :@  @+-  :----------. .=#%                 
         :@  @.  *%----------@-  =%                 
         :@  @.  #*          @=  =%                 
         :@  @.  #*          *:  :+                 
         :@  @.  *%-----.  .=+****+-.               
         :@  @.   :-----.-#*-.   .:-*#-             
         :@  @.        .%+.     .@*#+.*%.           
         :@  @:        %=       %*  +@.=%           
         :@  @*#*.    -@      *###***+. @-          
         :@ .@:.=@... -@ .+*#*####      @-          
         :@#*++++++++. %=.%+  +#       +%           
         :@. =++++++++-.%*.+%*@.      *%.           
          %+  ........   =#*-::   .-*%=             
           =*************. .=+****+-.               
 ____            _     _ _        ____ _     ___    
/ ___| _   _ ___| |   (_) |__    / ___| |   |_ _|   
\___ \| | | / __| |   | | '_ \  | |   | |    | | 
 ___) | |_| \__ \ |___| | |_) | | |___| |___ | |  
|____/ \__, |___/_____|_|_.__/   \____|_____|___| 
       |___/                                        

Hello! What would you like to do?
____________________________________________________________
```


## Features | [Return to Contents](#contents)

> **Note**:
> - Items in square brackets `[]` are optional arguments.
> - Items with `...` after them can be used multiple times
    >    * e.g `[/g GENRE]` ... can be used as `/g Horror /g Fantasy`

## Save Your Work | [Return to Contents](#contents)
> - All resources and events in the current list will automatically be saved into an offline file `storage.txt` on exit.
> - Resources and events from `storage.txt` will be loaded into the program at every start.
> - The `storage.txt` file will be located in the same directory as the jar file.
> - On start:
> ```
> ____________________________________________________________
> Storage file found @ .\storage.txt
> Loaded 2 resources and 1 events!
> ____________________________________________________________
> ```
> - On exit: 
> ```
> > exit
> Thanks for using SysLib CLI! We have saved the current resources and events created.
> Hope to see you again soon!
> ```

## Add a Listing: `add` | [Return to Contents](#contents)

Adds a new resource to the library inventory.

#### Types of Tags:
`[B]` - Books
`[EB]` - eBooks
`[CD]` - CDs
`[EB]` - Magazines
`[M]` - eMagazines
`[N]` - Newspapers
`[EN]` - eNewspapers

> **Note**:
> - Anything with `E` prior is an electronic version  
> i.e. `[EB]` is an eBook, `[EM]` is an eMagazine, `[EN]` is an eNewspaper

#### Types of Status:
- `AVAILABLE`
- `BORROWED`
- `LOST`

> **Note**:
> - Resource will default to `AVAILABLE` if no status is given

#### ISBN:

> **Note**:
> ISBN should be 13 digits.


### Add Book

Format: `add /i ISBN /t TITLE /a AUTHOR /tag b [/g GENRE /s STATUS]`

**Example input:**
```
add /i 9780763630189 /t Frankenstein /a Mary Shelley /tag b
add /i 9780763630188 /t Moby Dick /a Herman Melville /tag b /g Adventure, Fiction
add /i 9780763630187 /t Harry Squatter /a J.K. /tag b /g History /s lost
```
**Example output:**
```
This book is added:
[B]  ID: 3 Title: Harry Squatter ISBN: 9780763630187 Author: J.K. Genre: History Status: LOST Received Date: 11 Nov 2023
____________________________________________________________
```

> **Note**:
> - Multiple genres are allowed for a single resource. You can separate the different genres using comma followed by a space `, `.  
> e.g. Sci-Fi, Fantasy, Comedy

### Add eBook

Format: `add /i ISBN /t TITLE /a AUTHOR /tag eb /l LINK [/g GENRE /s STATUS]`

**Example input:**
```
add /i 9780763630189 /t Frankenstein /a Mary Shelley /tag eb /l frankenstein.com
add /i 9780763630188 /t Moby Dick /a Herman Melville /tag eb /l www.mobyd.com /g Adventure, Fiction
add /i 9780763630187 /t Harry Squatter /a J.K. /tag eb /l www.jk.com/harrysquatter /g History /s lost
```
**Example output:**
```
This e-book is added:
[EB]  ID: 6 Title: Harry Squatter ISBN: 9780763630187 Author: J.K. Genre: History Link: www.jk.com/harrysquatter
____________________________________________________________
```

> **Note**:
> - Multiple genres are allowed for a single resource. You can separate the different genres using comma followed by a space `, `.
> - e.g. Sci-Fi, Fantasy, Comedy

### Add CD
Format: `add /i ISBN /t TITLE /c CREATOR /ty TYPE /tag cd [/s STATUS]`

**Example input:**
```
add /i 9780763630189 /t Frankenstein /c Mary Shelley /ty Audio Book /tag cd
add /i 9770763630236 /t Mayday /c Kim Bondi /ty Video Recording /tag cd /s borrowed
add /i 9760763630369 /t Performing Arts in Singapore /c Evelyn Lim /ty Oral Interview /tag cd /s available
```
**Example output:**
```
This CD is added:
[CD]  ID: 8 Title: Mayday ISBN: 9770763630236 Creator: Kim Bondi Type: Video Recording Status: BORROWED
____________________________________________________________
```

### Add Magazine
Format: `add /i ISBN /t TITLE /b BRAND /is ISSUE /tag m [/s STATUS]`

**Example input:**
```
add /i 9781234567913 /t Tech Trends /b Wired Tech /is Volume 22, Issue 3 /tag m
add /i 9781234567944 /t Cozy Living /b Better Homes Publishing /is Home Edition, May 2023 /tag m /s LOST
add /i 9781234567951 /t Market Movers /b Forbes Publications /is Quarterly Report, Q2 2023 /tag m /s BORROWED
```
**Example output:**
```
This magazine is added:
[M]  ID: 11 Title: Cozy Living ISBN: 9781234567944 Brand: Better Homes Publishing Issue: Home Edition, May 2023 Status: LOST
____________________________________________________________
```

### Add eMagazine
Format: `add /i ISBN /t TITLE /b BRAND /is ISSUE /tag em /l LINK [/s STATUS]`

**Example input:**
```
add /i 9781234567913 /t Tech Trends /b Wired Tech /is Volume 22, Issue 3 /tag em /l www.wiredtech.com/techtrends
add /i 9781234567944 /t Cozy Living /b Better Homes Publishing /is Home Edition, May 2023 /tag em /l www.cozyliving.net /s lost
add /i 9781234567951 /t Market Movers /b Forbes Publications /is Quarterly Report, Q2 2023 /tag em /l forbes.com /s available
```
**Example output:**
```
This e-magazine is added:
[EM]  ID: 15 Title: Market Movers ISBN: 9781234567951 Brand: Forbes Publications Issue: Quarterly Report, Q2 2023 Link: forbes.com
____________________________________________________________
```

### Add Newspaper
Format: `add /i ISBN /t TITLE /p PUBLISHER /ed EDITION /tag n [/s STATUS]`
**Example input:**
```
add /i 9730763630288 /t City Herald /p Metro Media Group /ed Morning Edition, March 15 2023 /tag n
add /i 9730763630277 /t Sports Daily /p Sports Press International /ed Daily Sports Wrap, March 15 2023 /tag n /s lost
add /i 9730763630266 /t Community Chronicle /p Local News Network /ed Weekly Community News, March 13, 2023 /tag n /s available

```
**Example output:**
```
Attention: Status is not stated. Status set to default: AVAILABLE.
This newspaper is added:
[N]  ID: 16 Title: City Herald ISBN: 9730763630288 Publisher: Metro Media Group Edition: Morning Edition, March 15 2023 Status: AVAILABLE
```

### Add eNewspaper
Format: `add /i ISBN /t TITLE /p PUBLISHER /ed EDITION /tag en /l LINK [/s STATUS]`

**Example input:**
```
add /i 9730763630288 /t City Herald /p Metro Media Group /ed Morning Edition, March 15 2023 /tag en /l https://www.cityherald.com/march15-2023
add /i 9730763630277 /t Sports Daily /p Sports Press International /ed Daily Sports Wrap, March 15 2023 /tag en /l dailysports.com /s available
add /i 9730763630266 /t Community Chronicle /p Local News Network /ed Weekly Community News, March 13, 2023 /tag en /l www.lcn.com/news/031323 /s available
```

**Example output:**
```
This e-newspaper is added:
[EN]  ID: 20 Title: Sports Daily ISBN: 9730763630277 Publisher: Sports Press International Edition: Daily Sports Wrap, March 15 2023 Link: dailysports.com
____________________________________________________________
```

## Delete a Listing: `delete` | [Return to Contents](#contents)

Deletes the resource with the specified ID from the library inventory. You can find the ID using the `list` command.

Format: `delete /id ID`

**Example input:**
```
delete /id 1234567890123
```
**Example input:**
```
Looking for ID: 1234567890123...
This resource is removed: 
[B]  ID: 1234567890123 Title: Moby Dick ISBN: 9780763630188 Author: Herman Melville Genre: Adventure, Fiction Status: LOST
____________________________________________________________
```

## List All Items: `list` | [Return to Contents](#contents)


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

## Find Specific Listings: `find` | [Return to Contents](#contents)
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

## Edit a Listing: `edit` | [Return to Contents](#contents)

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

## Events | [Return to Contents](#contents)
>Note:
> - Events are stored separately from resources
> - They are stored in chronological order(events that are happening sooner are closer to index 0)

## Event Adding: `eventadd` | [Return to Contents](#contents)
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

## Event Listing: `eventlist` | [Return to Contents](#contents)

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

## Event Delete: `eventdelete` | [Return to Contents](#contents)
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

## Event Edit: `eventedit` | [Return to Contents](#contents)
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

## Summary: `summary` | [Return to Contents](#contents)
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
## Exiting the Program : `exit` | [Return to Contents](#contents)
Exits the program, all data in resource list and event list will be saved to a storage file.

Format: `Exit`

Example:
```
> exit
Thanks for using SysLib CLI! We have saved the current resources and events created.
Hope to see you again soon!
```

## Viewing Help : `help` | [Return to Contents](#contents)
Displays a list of available commands with examples and their syntax format.

Format: `help`

Example:
```
Commands available:
add: adds a new resource to the library inventory.(e.g. add /i ISBN /t TITLE /a AUTHOR /tag TAG [/g GENRE /s STATUS])
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


## FAQ | [Return to Contents](#contents)

Q: How do I download Java 11 on my computer?  
A: Follow the guide [here](https://www.codejava.net/java-se/download-and-install-java-11-openjdk-and-oracle-jdk#:~:text=Head%20to%20Java%20SE%20Development,download%20the%20file%20jdk%2D11.0.)!

Q: How do I open command terminal?  
A: For **Windows** users, click **Start** and search for **Command Prompt**. For **Mac** users, click the **Launchpad** 
icon in the **Dock**, type **Terminal** in the search field, then click **Terminal**.

## Known Issues | [Return to Contents](#contents)

...

## Command summary | [Return to Contents](#contents)

| Action                | Command                                                                         |
|-----------------------|---------------------------------------------------------------------------------|
| Add Book              | `add /i ISBN /t TITLE /a AUTHOR /tag b [/g GENRE /s STATUS]`                    |
| Add eBook             | `add /i ISBN /t TITLE /a AUTHOR /tag eb /l LINK [/g GENRE /s STATUS]`           |
| Add CD                | `add /i ISBN /t TITLE /c CREATOR /ty TYPE /tag cd [/s STATUS]`                  |
| Add Magazine          | `add /i ISBN /t TITLE /b BRAND /is ISSUE /tag m [/s STATUS]`                    |
| Add eMagazine         | `add /i ISBN /t TITLE /b BRAND /is ISSUE /tag em /l LINK [/s STATUS]`           |
| Add Newspaper         | `add /i ISBN /t TITLE /p PUBLISHER /ed EDITION /tag n [/s STATUS]`              |
| Add eNewspaper        | `add /i ISBN /t TITLE /p PUBLISHER /ed EDITION /tag en /l LINK [/s STATUS]`     |
| Delete Listing        | `delete /id <id of listing>`                                                    |
| Listing All Items     | `list [/tag <type of item> /g <genre of item> /s <status of item>]`             |
| Find Specific Listing | `find [/t <title of listing> OR /i <ISBN of item> OR /a AUTHOR OR /id ID]`      |
| Edit a Listing        | `edit /id ID /argument1 <ARGUMENT1> [/argument2 <ARGUMENT2>]...`                |
| Event Add             | `eventadd /t TITLE /date DATE [/desc DESCRIPTION]` (Format for date DD-MM-YYYY) |
| Event Delete          | `eventdelete /i INDEX`                                                          |
| Event Listing         | `eventlist`                                                                     |
| View Help             | `help`                                                                          |
| Exit                  | `exit`                                                                          |



