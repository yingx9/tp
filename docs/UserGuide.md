# User guide
SysLib CLI is a program designed specially for system libarians to manage their work and responsibilities. Using intuitive commands, view, add, delete, and find books from the library inventory without any hassle.

## Contents
* [Quick start](#quick-start--return-to-contents)
* [Features](#features--return-to-contents)
    * [Get help: `help`](#viewing-help--help--return-to-contents)
    * [Add a listing: `add`](#add-a-listing-add--return-to-contents)
    * [Delete a listing: `delete`](#delete-a-listing-delete--return-to-contents)
    * [List items: `list`](#list-all-items-list--return-to-contents)
    * [Find listing by search parameter: `find`](#find-specific-listings-find--return-to-contents)
    * [Exiting the program : `exit`](#exiting-the-program--exit--return-to-contents)
* [FAQ](#faq--return-to-contents)
* [Known Issues](#known-issues--return-to-contents)
* [Command summary](#command-summary--return-to-contents)


## Quick start | [Return to contents](#Contents)
- Make sure that you have Java 11 or above installed on your computer.
    - [How do I check the verison of my Java](https://www.java.com/en/download/help/version_manual.html)

## Features | [Return to contents](#Contents)

> Note:
> - Items in square brackets `[]` are optional arguments.
> - Items with `...` after them can be used multiple times
    >    * e.g `[/g GENRE]` ... can be used as `/g Horror /g Fantasy`

## Viewing help : `help` | [Return to contents](#Contents)
Displays a list of available commands and their syntax format

Format: `help`

## Add a listing: `add` | [Return to contents](#Contents)

Adds a new book to the library inventory.

#### Types of tags:
`[B]` - Books
`[CD]` - CDs
`[N]` - Newspapers
`[M]` - Magazines

> Note:
> - Anything with `e` prior is an electronic version
    > i.e. `[eB]` is an eBook, `[eM]` is an eMagazine



Format: `add /n NAME /a AUTHOR /t TAG /IBSN IBSN [/g GENRE]...`

**Example**
```
add /n Moby Dick /a Herman Melville /t B /IBSN 9780763630188 /g Adventure Fiction
add /n Harry Squatter /a J.K. /t M /IBSN 9780763630187 /g History
```

## Delete a listing: `delete` | [Return to contents](#Contents)

Deletes the book with the specified ID from the library inventory.

Format: `delete /id ID`

**Example**
```
delete /id 123456789
```

## List all items: `list` | [Return to contents](#Contents)
You can list all books OR from certain authors, tags, or genre.

Format: `list [/tag TAG /genre GENRE /author AUTHOR ]`

**Example**
```
list
list /tag B /genre Fiction /author J. K. Rowling
list /genre Thrill
```

## Find specific listings: `find` | [Return to contents](#Contents)
Find a book by name, author, ISBN or given id.

Format: `find [/name NAME OR /ISBN ISBN OR /a AUTHOR OR /id ID]`

**Example**
```
find /n Moby Dick
find /ISBN 9780763630188
find /a J. K. Rowling
find /id 123456789
```

## Exiting the program : `exit` | [Return to contents](#Contents)
Displays a farewell message and exits the program

Format: `exit`


## FAQ | [Return to contents](#Contents)

...

## Known Issues | [Return to contents](#Contents)

...

## Command summary | [Return to contents](#Contents)

| Action                | Command                                                                                                              |
|-----------------------|----------------------------------------------------------------------------------------------------------------------|
| Add listing           | `add /name <name of listing> /author <author of item> /tag <type of item> /IBSN <IBSN of item> [/g <genre of item>]` |
| Delete listing        | `delete /id <id of listing>`                                                                                         |
| Listing all items     | `list [/tag <type of item> /genre <genre of book> /author <author of book> ]`                                        |
| Find specific listing | `find [/name NAME OR /isbn ISBN OR /a AUTHOR OR /id ID]`                                                             |
| Exit                  | `exit`                                                                                                               |
| View help             | `help`                                                                                                               |
