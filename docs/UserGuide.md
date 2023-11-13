# User Guide
<img src="images/SysLib Logo.png" /> 

## Contents
* [Introduction](#introduction--return-to-contents)
* [Quick Start](#quick-start--return-to-contents)
* [Features](#features--return-to-contents)
    * [Save your work](#managing-your-resources-and-events-with-ease)
    * [Resources](#resources)
      * [Add a Listing: `add`](#add-a-listing-add--return-to-contents)
      * [Delete a Listing: `delete`](#delete-a-listing-delete--return-to-contents)
      * [List Items: `list`](#list-all-items-list--return-to-contents)
      * [Find Listing by Search Parameter: `find`](#find-specific-listings-find--return-to-contents)
      * [Edit a Listing: `edit`](#edit-a-listing-edit--return-to-contents)
    * [Events](#events)
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

Our user guide is for every system librarian whether you're a beginner, novice, or expert in using a CLI library management software. 

From viewing, adding, searching, and many more, SysLib provides all the features you need to optimize your work from hours to seconds. In no time, you will be typing intuitive commands to manage your library resources and upcoming events!

Without further ado, let's get started with how to navigate the guide!

### How to Use the User Guide 

Information about how to use the guide (e.g. how to navigate the document, meaning of icons and formatting used)

**Important pointers to take note of:**

1. **Format for Commands**:
- Capital letters - placeholders for your input
- Small letters - exact commands to enter
- / - indicates the type of information you are entering 
- [] - optional arguments

2. **Recommended Terminals**

The following table lists down the operating systems and their respective terminals that Syslib CLI has been tested on to
work.

| Operating System  | Version                        | Recommended Terminal                                                                                                |
|:------------------|:-------------------------------|:--------------------------------------------------------------------------------------------------------------------|
| Microsoft Windows | Windows 10 2004 and above      | Windows Terminal ([User Guide](https://docs.microsoft.com/en-us/windows/terminal/))                                 |
| Apple macOS       | macOS 10.15 Catalina and above | Terminal ([User Guide](https://support.apple.com/en-sg/guide/terminal/apd5265185d-f365-44cb-8b09-71a064a42125/mac)) |
| Ubuntu Linux      | Ubuntu 20.04.3 (LTS) and above | Bash Terminal ([User Guide](https://ubuntu.com/tutorials/command-line-for-beginners#3-opening-a-terminal))          |

> If your operating system is not listed in the table above, it means our application has not been tested on it, and we cannot guarantee that the application will work as intended. We highly encourage you to use one of the recommended operating systems listed in the table above. We apologise for any inconvenience caused.

## Quick Start | [Return to Contents](#contents)
1. Make sure that you have Java 11 or above installed on your computer.
    - Open terminal and type `java --version`
    - [How do I check the version of my Java](https://www.java.com/en/download/help/version_manual.html)
2. [Download the latest release](https://github.com/AY2324S1-CS2113T-W11-1/tp/releases/tag/v2.1) of `Syslib.jar`
3. Copy the `Syslib.jar` file into a folder on its own.
4. Open a command terminal, type `cd <FILE_DIRECTORY>`, where <FILE_DIRECTORY> refers to the directory to the `Syslib.jar` file.
5. Run the following command: `java -jar Syslib.jar`. You should see the following welcome screen.

```
____________________________________________________________
Data directory does not exist. Creating now...
Storage file does not exist. Creating now...
Loaded 0 resources and 0 events!
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
> Syslib CLI allows System Librarians to create Resources (Books, Magazines, NewsPapers, CD's, eBooks, eMagazines, eNewspapers) as well as Events.

### Managing Your Resources and Events with Ease

Our intuitive system ensures that you never lose track of your valuable resources and events. Here's how it works:

#### 📁 Automatic Saving on Exit
- **Peace of Mind:** Every resource and event you add to the current list is automatically saved when you exit the program. Rest easy knowing your data is secure.

```
  > exit
  Thanks for using SysLib CLI! We have saved the current resources and events created.
  Hope to see you again soon!
```

#### 🔄 Seamless Loading on Startup
- **Instant Access:** Each time you start the program, we automatically load your resources and events from the last session. Your information is always at your fingertips.

```
____________________________________________________________
Storage file found @ .\data\storage.txt
Loaded 2 resources and 1 events!
____________________________________________________________
```


#### 📍 File Location
- **Find Your Data Easily:** Your saved data resides in a file named `storage.txt`, conveniently located in the `data` directory.
    - Path to your file: `data/storage.txt`
```
>   Syslib.jar
>   data/                              // Primary folder for storage
>   └── storage.txt                    // Text file containing a list of resources and events saved.
```
Happy organizing!



## Resources
### Add a Listing: `add` | [Return to Contents](#contents)

Adding New Resources to Your Library Inventory

Easily expand your library's collection with our streamlined process for adding new resources. Here's everything you need to know:

**Resource Tags - Identify Your Resources**

Choose the right tag to classify each new addition:
- `[B]` for **Books**
- `[EB]` for **eBooks**
- `[CD]` for **CDs**
- `[M]` for **Magazines**
- `[EM]` for **eMagazines**
- `[N]` for **Newspapers**
- `[EN]` for **eNewspapers**


**Electronic Versions**
- Easy Identification: Anything with an `E` is an electronic version.
- For example, `[EB]` is an eBook, `[EM]` is an eMagazine, and `[EN]` is an eNewspaper.

**Status Types - Keep Track of Availability**
Set the status to keep your inventory organized:
- `AVAILABLE` for items ready to be checked out.
- `BORROWED` for items currently with users.
- `LOST` for items that are missing.

> **📚 Note:**
> 
> Default Status
> - **Automatic Setting:** If you don't specify a status, we'll automatically set it to `AVAILABLE`.
> 
> ISBN requirements
> - **13-Digit Requirement:** Ensure the ISBN is exactly 13 digits for proper cataloging
>
> Use of Slash ('/')
> - Slash ('/') can only be used in two situations.
>  1. To indicate the type of information you are entering. 
>     The required indications are in the 'Format' part of the commands.  
>     e.g. /i for ISBN, /t for Title, /tag for Tag, etc.
>  2. When it is wrapped with words.  
>     e.g. www.abc.com/def, Frankenstein/the Modern Prometheus 
> - Examples of invalid use of slash ('/'): 
>   - /isbn
>   - www.abc.com/
>   - Frankenstein/ the Modern Prometheus
>   - Frankenstein /the Modern Prometheus
>   - Frankenstein / the Modern Prometheus

**Quick Tips**
- 🌟 **Double-check your tags and ISBN** for accurate categorization.
- 💡 **Regularly update the status** of your resources to reflect their current state.

The specific commands for each resource types can be seen below: 

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

> **📚 Note:**
> - Multiple genres are allowed for a single resource. You can separate the different genres using comma `,`.  
> e.g. Sci-Fi, Fantasy, Comedy
> - '[' and ']' are not allowed in genres.

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

> **📚 Note:**
> - Multiple genres are allowed for a single resource. You can separate the different genres using comma `,`.
> - e.g. Sci-Fi, Fantasy, Comedy
> - '[' and ']' are not allowed in genres.

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

### Delete a Listing: `delete` | [Return to Contents](#contents)

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

### List All Items: `list` | [Return to Contents](#contents)


The `list` command displays every resource in the library along with their details and categorized by their type `Book`, `Magazine`,`CD`, or `Newspaper`, giving you a quick and neat overview of all the resources in one place. 

Looking for a more specific list? `list` also offers you the capability to **filter** for a specific **tag**, **genre**, or **status**, generating a tailored list for your needs. 

**Format:** `list [/tag TAG /g GENRE /s STATUS]`

> **📚 Note:**
>- Including more than one filter will list resources that satisfy **ALL** given filters. 
>- For example, `list /tag B /g Horror` will list Books with Horror genre.
>- You can only specify one keyword per filter. 
>  - **ALLOWED:** /g Horror 
>  - **NOT ALLOWED:** /g Horror, Fiction

**Potential Issues:**

You may face an issue where you are unable to see the list, or it's difficult to read the table due to the display alignment. Kindly click [here](#list-table-looks-messy-or-unable-to-see-the-full-details-return-to-list-feature) to jump to the Known Issues section to solve any issues regarding the list. 

**Example input:**
```
list
list /tag B
list /g Thrill
list /s Available
list /tag B /g Fiction
list /tag B /g Fiction /s Available
```

**Example output:**

![ExampleOutput](images\List Screenshots\listexampleoutput.png)

### Find Specific Listings: `find` | [Return to Contents](#contents)

**Find What You Need, Fast!**

Our advanced search capabilities make it easy to locate the resources you need. Here's how you can make the most out of our search tool:

- **Flexible Options:** Find resources using a variety of identifiers:
    - **Title:** Pinpoint resources by their titles.
    - **Author:** Search for books by their authors.
    - **ISBN:** Use this unique identifier for precise book searches.
    - **ID:** Every resource has an ID for quick identification.
  

- **Targeted Results:** Combine multiple filters in your search. We'll show you results that match **ALL** your specified criteria for pinpoint precision.

> **📚 Note:**
> - `AUTHOR` also refers to the following:
>     - For **Newspapers**, `publisher` will be used.
>     - For **CDs**, `creator` will be used.
>     - For **Magazines**, `brand` will be used.

**Quick Tips**
- 💡 **Familiarize yourself with the search terms** for different resource types for efficient searching.


Discover exactly what you're looking for, effortlessly!

**Format:** `find [/t TITLE OR /i ISBN OR /a AUTHOR/PUBLISHER/BRAND/CREATOR OR /id ID]`

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

### Edit a Listing: `edit` | [Return to Contents](#contents)

We all know the horror of making a typo and having to delete and add a resource again—it's absolutely _dreadful_. 

Fear not! Update a resource's details using the `edit` command and fix your typos in a flash. 

**Format:** `edit /id ID /argumentname ARGUMENT [/argumentname2 ARGUMENT2..]`

> **📚 Note:**
> 
> - If you have forgotten the **ID**, execute `list` to locate your target resource and ID.
> - At least **one** argument to edit must be given. 
> - You can edit multiple details in one go by specifying multiple arguments. E.g `edit /id 1 /t NEWTITLE /a NEWAUTHOR` updates title and author. 

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
edit /id 1 /t NEW TITLE /a NEW AUTHOR /g Horror, Fiction /s LOST /i 1231231231234
edit /id 2 /c NEW CREATOR /ty NEW TYPE
edit /id 3 /b NEW BRAND /is NEW ISSUE
edit /id 4 /p NEW PUSBLISHER /ed NEW EDITION
```

**Example output:**
```
Successfully updated! Your updated resource: 

[B]  ID: 3 Title: Mary ISBN: 123 Author: John Genre: Horror, Adventure Status: LOST
____________________________________________________________
```

## Events
>**📚 Note:**
> - Events are stored separately from resources
> - They are stored in chronological order(events that are happening sooner are closer to index 0)

### Event Adding: `eventadd` | [Return to Contents](#contents)
>**📚 Note:**
> - `desc` is optional for all events
> - Any event without description will be shown as `null`

Adds an event to the database.

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

### Event Listing: `eventlist` | [Return to Contents](#contents)

Displays all events in the database.

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

### Event Delete: `eventdelete` | [Return to Contents](#contents)
>**📚 Note:**
> - INDEX starts from 0 and can be viewed by calling `eventlist`
> - INDEX might change as those with earlier dates are sorted first

Deletes an event from the database based on the index provided.

Format: `eventdelete /id INDEX`

**Example input:**
```
eventdelete /id 0
```

**Example output:**
```
This event is removed:
Fan meetup for xxx | 11-11-2001 | null
____________________________________________________________
```

### Event Edit: `eventedit` | [Return to Contents](#contents)
>**📚 Note:**
> - INDEX starts from 0 and can be viewed by calling `eventlist`
> - INDEX might change as those with earlier dates are sorted first

Edits attributes of an event based on information provided.

Format: `eventedit /id INDEX [/t TITLE /date DATE /desc DESCRIPTION]`

**Example input:**
```
eventedit /id 0 /t NEW TITLE
eventedit /id 2 /t NEW TITLE /date 23 Jan 2024 /desc NEW DESCRIPTION
```

**Example output:**
```
Event edited successfully. New event details:
0: NEW TITLE | 23-01-2024 | NEW DESCRIPTION
____________________________________________________________
```

### Summary: `summary` | [Return to Contents](#contents)
Provide a summary of resources added and upcoming 3 events

Format: `summary`

**Example input:**
```
summary
```

**Example output**
```
Summary of Resources:
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
### Exiting the Program : `exit` | [Return to Contents](#contents)
Exits the program, all data in resource list and event list will be saved to a storage file.

Format: `exit`

Example:
```
> exit
Thanks for using SysLib CLI! We have saved the current resources and events created.
Hope to see you again soon!
```

### Viewing Help : `help` | [Return to Contents](#contents)
Displays a list of available commands with examples and their syntax format.

Format: `help`

Example:
```
Commands available:
[add] adds a new resource to the library inventory. (e.g. add /i ISBN /t TITLE /a AUTHOR /tag TAG [/g GENRE /s STATUS])
[delete] deletes the resource with the specified ID from the library inventory. (e.g. delete /id 123456789)
[list] lists all resources OR filter by certain tags, genre, or status. (e.g. list /tag B /g Fiction /s AVAILABLE)
[find] finds a resource by title, author, ISBN or given id. (e.g. find /i 9780763630188 /a AUTHOR)
[edit] edits a listing by entering its id to update its details. (e.g. edit /id 123 /t NEW_TITLE /a NEW_AUTHOR)
[eventadd] adds an event to the database. (e.g. eventadd /t TITLE /date 23 Dec 2023 [/desc DESCRIPTION])
[eventlist] lists out all events in the database. (e.g. eventlist)
[eventdelete] deletes an event from the database based on the index. (e.g. eventdelete /i INDEX)
[eventedit] edits an event in the event list based on the information given. (e.g. eventedit /i INDEX [/t TITLE /date DATE /desc DESCRIPTION])
[summary] shows a summary of all resources and the next 3 events. (e.g. summary)
[exit] displays a farewell message and exits the program. (e.g. exit)

For more information, please refer to our user guide at: https://bit.ly/SyslibUserGuide
____________________________________________________________
```


## FAQ | [Return to Contents](#contents)

Q: How do I download Java 11 on my computer?  
A: Follow the guide [here](https://www.codejava.net/java-se/download-and-install-java-11-openjdk-and-oracle-jdk#:~:text=Head%20to%20Java%20SE%20Development,download%20the%20file%20jdk%2D11.0.)!

Q: How do I open command terminal?  
A: For **Windows** users, click **Start** and search for **Command Prompt**. For **Mac** users, click the **Launchpad** 
icon in the **Dock**, type **Terminal** in the search field, then click **Terminal**.

## Known Issues | [Return to Contents](#contents)

### List Table looks messy or unable to see the full details: [[Return to list feature]](#list-all-items-list--return-to-contents)

Upon executing `list`, you  may encounter an issue where the table is out of alignment: 

![ListProblem.png](images/List%20Screenshots/listproblemimg.png)

This issue is due to the **window size** of your command line terminal and occurs when you have long details in your resources. 

**Solutions:**

You can try any of the following solutions to fix this issue:
- Make your terminal **full screen** by clicking the square on the top right.

  ![ListProblem.png](images/List%20Screenshots/listimage.png)

- **Resize** your window by:
  1. Move your cursor to bottom right corner of your terminal window until you see an icon with double arrows like:   ![resizeicon.png](images/List Screenshots/resizeicon.png)
  2. Drag your cursor down until you see the full table 


- Decrease your **font size** by:

  - Right-click your terminal and click "Properties"
  
    ![img_2.png](images/List%20Screenshots/terminalimg.png)
  
  - Click on "Font" tab and select a smaller font size that suits your display.
  
    ![img_3.png](images/List%20Screenshots/fontsize.png)


## Command summary | [Return to Contents](#contents)

| Action                | Command                                                                          |
|-----------------------|----------------------------------------------------------------------------------|
| Add Book              | `add /i ISBN /t TITLE /a AUTHOR /tag b [/g GENRE /s STATUS]`                     |
| Add eBook             | `add /i ISBN /t TITLE /a AUTHOR /tag eb /l LINK [/g GENRE /s STATUS]`            |
| Add CD                | `add /i ISBN /t TITLE /c CREATOR /ty TYPE /tag cd [/s STATUS]`                   |
| Add Magazine          | `add /i ISBN /t TITLE /b BRAND /is ISSUE /tag m [/s STATUS]`                     |
| Add eMagazine         | `add /i ISBN /t TITLE /b BRAND /is ISSUE /tag em /l LINK [/s STATUS]`            |
| Add Newspaper         | `add /i ISBN /t TITLE /p PUBLISHER /ed EDITION /tag n [/s STATUS]`               |
| Add eNewspaper        | `add /i ISBN /t TITLE /p PUBLISHER /ed EDITION /tag en /l LINK [/s STATUS]`      |
| Delete Listing        | `delete /id <id of listing>`                                                     |
| Listing All Items     | `list [/tag <type of item> /g <genre of item> /s <status of item>]`              |
| Find Specific Listing | `find [/t <title of listing> OR /i <ISBN of item> OR /a AUTHOR OR /id ID]`       |
| Edit a Listing        | `edit /id ID /argument1 <ARGUMENT1> [/argument2 <ARGUMENT2>]...`                 |
| Event Add             | `eventadd /t TITLE /date DATE [/desc DESCRIPTION]` (Format for date DD-MM-YYYY)  |
| Event Delete          | `eventdelete /id INDEX`                                                          |
| Event Listing         | `eventlist`                                                                      |
| Event Edit            | `eventedit /id INDEX [/date DATE /desc DESCRIPTION]`(Format for date DD-MM-YYYY) |
| Summary               | `summary`                                                                        |
| View Help             | `help`                                                                           |
| Exit                  | `exit`                                                                           |



