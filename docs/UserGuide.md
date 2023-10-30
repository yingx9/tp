# Syslib User guide
SysLib is a program designed specially for system librarians to manage their work and responsibilities. Using intuitive commands, view, add, delete, and find books from the library inventory without any hassle. 
This user guide is for System Librarians to ensure optimal use of Syslib

## Contents
* [Quick start](#quick-start--return-to-contents)
* [Features](#features--return-to-contents)
    * [Add a listing: `add`](#add-a-listing-add--return-to-contents)
    * [Delete a listing: `delete`](#delete-a-listing-delete--return-to-contents)
    * [List items: `list`](#list-all-items-list--return-to-contents)
    * [Find listing by search parameter: `find`](#find-specific-listings-find--return-to-contents)
    * [Edit a listing: `edit`](#edit-a-listing-edit--return-to-contents)
    * [Exiting the program : `exit`](#exiting-the-program--exit--return-to-contents)
    * [Get help: `help`](#viewing-help--help--return-to-contents)
* [FAQ](#faq--return-to-contents)
* [Known Issues](#known-issues--return-to-contents)
* [Command summary](#command-summary--return-to-contents)


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

## Add a listing: `add` | [Return to contents](#contents)

Adds a new resource to the library inventory.

#### Types of tags:
`[B]` - Books
`[CD]` - CDs
`[N]` - Newspapers
`[M]` - Magazines

> Note:
> - Anything with `e` prior is an electronic version 
>> i.e. `[eB]` is an eBook, `[eM]` is an eMagazine

#### Notes about genre:
Multiple genres are allowed for a single resource. You can separate the different genres using comma followed by a space `, `.

Format: `add /id ID /t TITLE /a AUTHOR /tag TAG /i ISBN [/g GENRE /s STATUS]...`

**Example input:**
```
add /id 123456789 /t Moby Dick /a Herman Melville /tag B /i 9780763630188 /g Adventure, Fiction
add /id 123456789 /t Harry Squatter /a J.K. /tag M /i 9780763630187 /g History /s lost
```
**Example response:**
```
This book is added: Moby Dick
____________________________________________________________
```

## Delete a listing: `delete` | [Return to contents](#contents)

Deletes the resource with the specified ID from the library inventory. You can find the ID using the `list` command.

Format: `delete /id ID`

**Example input:**
```
delete /id 123456789
```
**Example input:**
```
Looking for ID: 123456789...
This resource is removed: 
[B]  ID: 123456789 Title: Moby Dick ISBN: 9780763630188 Author: Herman Melville Genre: Adventure, Fiction Status: LOST
____________________________________________________________
```

## List all items: `list` | [Return to contents](#contents)
List all resources OR filter by certain tags or genre.

Format: `list [/tag TAG /g GENRE ]`
- Including both filters `tag` and `genre` will only list resources satisfying both criteria:
  - `list /tag B /g Horror` will list Books with Horror genre. 

**Example input:**
```
list
list /tag B
list /tag B /g Fiction
list /g Thrill
```

**Example output:**
```
Listing all resources in the Library:

1. [B]  ID: 1 Title: Moby Dick ISBN: 9780763630188 Author: Herman Melville Genre: Adventure, Fiction Status: AVAILABLE
2. [M]  ID: 2 Title: Harry Squatter ISBN: 9780763630187 Author: J.K. Genre: History Status: AVAILABLE
3. [B]  ID: 3 Title: Frankenstein ISBN: FKS0001 Author: Mary Shelley Genre: - Status: BORROWED
4. [B]  ID: 4 Title: The Great Gatsby ISBN: 9780023381201 Author: F. Scott Fitzgerald Genre: Novel, Fiction, Tragedy Status: AVAILABLE
5. [B]  ID: 5 Title: To Kill a Mockingbird ISBN: 9780061120084 Author: Harper Lee Genre: Novel, Bildungsroman, Southern Gothic, Domestic Fiction, Thriller, Legal Story Status: LOST

There are currently 5 resource(s).
____________________________________________________________
```

## Find specific listings: `find` | [Return to contents](#contents)
Find a resource by title, author, ISBN or given id.

Format: `find [/t TITLE OR /i ISBN OR /a AUTHOR OR /id ID]`

**Example input:**
```
find /t Moby Dick
find /i 9780763630188
find /a J. K. Rowling
find /id 123456789
```
**Example output:**
```
Here are resources that matched the given filters:
[B]  ID: 123456789 Title: Moby Dick ISBN: 9780763630188 Author: Herman Melville Genre: Adventure, Fiction
____________________________________________________________
```

## Edit a listing: `edit` | [Return to contents](#contents)
Edit a listing by entering its `isbn` to update its details. 

Format: `edit /i ISBN /argumentname ARGUMENT`

Argument Names:
  - `/t TITLE `
  - `/a AUTHOR `
  - `/tag TAG`
  - `/g GENRES`
    - If you're inputting **multiple genres**, separate them by  with comma `,`. For example: 
     `/g Horror, Fantasy`
  - `/s STATUS`

  
**Example input:**
```
edit /i 123 /t NEW_TITLE
edit /i 123 /t NEW_TITLE /a NEW_AUTHOR
edit /i 123 /g Horror, Adventure
edit /i 123 /s lost
```

**Example output:**
```
Successfully updated! Your updated resource: 

[B]  ID: 12312 Title: Mary ISBN: 123 Author: John Genre: Horror, Adventure Status: LOST
____________________________________________________________
```

## Exiting the program : `exit` | [Return to contents](#contents)
Displays a farewell message and exits the program.

Format: `exit`

**Example**
```
Bye, hope to see you again soon!
____________________________________________________________
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
find: find a resource by title, author, ISBN or given id. (e.g. find /i 9780763630188)
exit: displays a farewell message and exits the program (e.g. exit)
For more information, please refer to our user guide at:https://ay2324s1-cs2113t-w11-1.github.io/tp/UserGuide.html
____________________________________________________________
```


## FAQ | [Return to contents](#contents)

...

## Known Issues | [Return to contents](#contents)

...

## Command summary | [Return to contents](#contents)

| Action                | Command                                                                                                    |
|-----------------------|------------------------------------------------------------------------------------------------------------|
| Add listing           | `add /t <title of listing> /a <author of item> /tag <type of item> /i <ISBN of item> [/g <genre of item>]` |
| Delete listing        | `delete /id <id of listing>`                                                                               |
| Listing all items     | `list [/tag <type of item> /g <genre of book>]`                                                            |
| Find specific listing | `find [/t <title of listing> OR /i <ISBN of item> OR /a AUTHOR OR /id ID]`                                 |
| Exit                  | `exit`                                                                                                     |
| View help             | `help`                                                                                                     |

