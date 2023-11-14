# Developer Guide

## Table of Contents
* [About This guide](#about-this-guide)
* [Acknowledgements](#acknowledgements)
* [Setting Up](#setting-up--getting-started)
* [Design and Implementation](#design--implementation)
    * [Architecture Overview](#architecture-overview--return-to-contents)
    * [Component Overview](#component-overview--return-to-contents)
        * [UI Component](#ui-component)
        * [Parser Component](#parser-component)
        * [Command Component](#command-component)
        * [Storage Component](#storage-component)
    * [Implementation](#implementation--return-to-contents)
        * [Find Resources](#find-resource-feature--return-to-contents)
        * [Add Resources](#add-resource-feature--return-to-contents)
        * [Show Resources](#show-resources-feature--return-to-contents)
        * [List Resources](#listing-resources-feature--return-to-contents)
        * [Edit Resources](#edit-command-feature--return-to-contents)
        * [Add Events](#event-add-feature--return-to-contents)
        * [List Events](#event-list-feature--return-to-contents)
        * [Delete Events](#event-delete-feature--return-to-contents)
* [Product Scope](#product-scope--return-to-contents)
* [User Stories](#user-stories--return-to-contents)
* [Use Cases](#use-cases--return-to-contents)
* [Non-Functional Requirements](#non-functional-requirements--return-to-contents)
* [Glossary](#glossary--return-to-contents)
* [Manual Testing](#instructions-for-manual-testing--return-to-contents)

<div style="page-break-after: always;"></div>

## About this guide
This developer guide serves as a documentation of the development of Syslib, an application that was created to help librarians to manage their work.

This technical document is meant for current and future developers of Syslib as a reference point on the design, implementation, and other technical and non-technical aspects of the application.

## Acknowledgements

1. Reused [Style.puml](https://github.com/se-edu/addressbook-level3/blob/master/docs/diagrams/style.puml) and [CommandResult](https://github.com/se-edu/addressbook-level3/blob/master/src/main/java/seedu/address/logic/commands/CommandResult.java) class from [AddressBook](https://github.com/se-edu/addressbook-level3) with slight modifications.
2. Adapted `Command` and `Parser` structure from teammate Wu Xingyu's [iP codebase](https://github.com/DavinciDelta/ip).

## Setting Up & Getting Started
1. Fork the repo at [GitHub](https://github.com/AY2324S1-CS2113T-W11-1/tp).
2. [Clone](https://docs.github.com/en/repositories/creating-and-managing-repositories/cloning-a-repository) the fork onto your computer.
3. Using [Intellij IDEA](https://www.jetbrains.com/idea/download/) (Recommended) or an IDE of your choice:
   - Ensure your IDE is configured to use **JDK 11** as described [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk). 
   - Import the project as a **Gradle project** as described [here](https://se-education.org/guides/tutorials/intellijImportGradleProject.html).
   - **Verify** the setup: 
     - Run `Syslib`. On IntelliJ, you can right-click Syslib class and `Run Syslib.main()` or `Shift` + `F10`. 
     - You should see the following greeting logo and message:

<div style="page-break-after: always;"></div>

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
> 
```

You are now ready to begin developing! If you would like to exit Syslib, type `exit` to exit the program.

<div style="page-break-after: always;"></div>

## Design & Implementation
## Architecture Overview | [Return to contents](#table-of-contents)

<img src="images/ArchitectureDiagram.png" />

The above diagram is a simplified overview of Syslib and its components. More details can be found in the following sections.  

**Main components of SysLib Architecture**

SysLib currently consists of five main components:

- `Syslib`: Initializes other components and acts as the "launching pad" by executing important methods
- `UI`: Handles User Interaction such as getting user input
- `Parser`: Parsing User Input  
- `Command`: Command Executor 
- `Storage`: Loads data from file in hard disk, and saves data to hard disk on program exit

<div style="page-break-after: always;"></div>

**How the architecture components interact with each other**

The following diagram shows an overview of how components in Syslib work and interact with each other. 

<img src="images/ComponentDiagram.png" />

1. After all components have been initialized, `Syslib` calls upon the `Storage` component to load any data from `storage.txt`.
2. `Storage` fills two lists, ResourcesList and EventsList, with the read data. Both lists are returned to Syslib.
3. Syslib creates a GenericList containing of these filled lists. 
4. Afterwhich, `UI` is called to get and return user input.
5. Syslib passes the returned user input to `Parser` for processing.
6. `Parser` calls the appropriate command based on the user input, for example `AddCommand`.
7. `Command` carries out necessary actions and returns a `CommandResult` object which holds the message to be displayed to user. `Parser` prints the feedback to user.
8. Once the user input has been processed and executed, Syslib calls `Storage` to save the current state of the lists to `storage.txt`.
9. `Storage` retrieves the lists in `Parser` and writes to `storage.txt`.

<div style="page-break-after: always;"></div>

## Component Overview | [Return to contents](#table-of-contents)

### UI Component

`UI` component consists of a single `UI` Class.
which manages interaction (prompting for user input and displaying results
of commands/methods being called) between the user and the application.

How the UI class works:
* Upon the initialization of `SysLib`, the `UI` class will call the `showWelcomeMessage` method to display the greeting messages to
  the user.
* Additionally, local files storing the data of previously saved work of `SysLib` will also be loaded into the program through the initialization of the `Storage` Class. Thereafter,
  the loading statuses of these files are also displayed by calling the `showLoadMessage()` method.
    * If `storage.txt` does not exist, it will also call the `showNoFileFoundMessage()` to let the user know a new `storage.txt` file will be created.
    * However, if `storage.txt` already exists, `showFileFoundMessage()` will be called instead.
* `UI` class is also responsible for getting the user input by calling the `readCommand()`
  method, which will then parse the input by sending it to the `Parser` class. The `Parser` class will process 
  the input and make use of the `Command` class to execute the command.
* UI also prints Resource details in a formatted table using the `showResourcesDetails` method.
* Help messages are also printed in the `UI` class by calling `showHelpMessage()` method.
* Lastly, when the user exits the program, the `showExitMessage()` method will be called to indicate that the
  user has successfully exited the program.

<div style="page-break-after: always;"></div>

### Parser Component

The parsing for a generic command can be seen here:

<img src="images/Parsing.png" />

For some commands that does not require arguments (etc: `help`, `exit`), `parseArgument()`
and `validateStatement()` will not be called

If an invalid command is given, the nearest command will be searched for via the `suggest` function in `suggestParser`.
If the nearest command is similar to what the user typed, it will be suggested

For more details on each Command, check them out [below](#implementation--return-to-contents)

<div style="page-break-after: always;"></div>

### Command Component
The `Command` component is linked to the `Parser` component. 
After the `Parser` filters out the correct command, that particular command will be executed.

Each `Command` uses a `GenericList` to get Events or Resources to manipulate. 
Then the commands will generate a `CommandResult` to give informative feedback to the user.

<img src="images/CommandClassDiagram.png" />

<div style="page-break-after: always;"></div>

### Storage Component

This component of Syslib is mainly responsible for reading and writing application data from and to files stored on the user’s filesystem. This is to allow the user to retain the data he/she has entered into Syslib and be able to continue using the data when he/she starts Syslib the next time.

The following class diagram shows how the storage component’s classes and how it interacts with some other components and classes in Syslib:

<img src="images/StorageDiagram.png" height="350"/>

On the user’s local filesystem, the organisation of the application files are as follows:

```
 data/        // Primary folder for storage
 └── storage.txt                    // Text file containing a list of resources and events saved.
 logs/        // Folder for logs of each command
 ├── findCommandLogs.log            // Log file containing logs created by the Find Command
 ├── addCommandLogs.log             // Log file containing logs created by the Add Command
 ├── eventCommandLogs.log           // Log file containing logs created by the Event Commands
 ├── helpCommandLogs.log            // Log file containing logs created by the Help Command
 ├── listCommandLogs.log            // Log file containing logs created by the List Command
 ├── Storage.log                    // Log file containing logs created by the Storage uses
 └── summaryCommandLogs.log         // Log file containing logs created by the Summary Command
```

<div style="page-break-after: always;"></div>

## Implementation | [Return to contents](#table-of-contents)
This section provides details on how certain features are implemented.

### Add Resource Feature | [Return to contents](#table-of-contents)

The `add` feature is responsible for processing user commands to add a new resource to SysLib. It is facilitated by 
the `AddCommand` component. It works with `Parser` and `Command` components to parse and validate the user input. 
The new resource is stored internally in `resourcesList` as a `Book`, `EBook`, `CD`, `Magazine`, `EMagazine`, `Newspaper`, 
or `ENewspaper` depending on the type of resources specified by `tag`. 

There are seven types of `Resource`:
- Book
- EBook
- CD
- Magazine
- EMagazine
- Newspaper
- ENewspaper

`add` has eighteen possible options:  

- **Book** has four options:
  - `add /i [isbn] /t [title] /a [author] /tag [tag] `
  - `add /i [isbn] /t [title] /a [author] /tag [tag] /g [genre]`
  - `add /i [isbn] /t [title] /a [author] /tag [tag] /s [status]`
  - `add /i [isbn] /t [title] /a [author] /tag [tag] /g [genre] /s [status]`
- **EBook** has four options:
    - `add /i [isbn] /t [title] /a [author] /tag [tag] /l [link]`
    - `add /i [isbn] /t [title] /a [author] /tag [tag] /l [link] /g [genre]`
    - `add /i [isbn] /t [title] /a [author] /tag [tag] /l [link] /s [status]`
    - `add /i [isbn] /t [title] /a [author] /tag [tag] /l [link] /g [genre] /s [status]`
- **CD** has two options:
    - `add /i [isbn] /t [title] /c [creator] /ty [type] /tag [tag]`
    - `add /i [isbn] /t [title] /c [creator] /ty [type] /tag [tag] /s [status]`
- **Magazine** has two options:
    - `add /i [isbn] /t [title] /b [brand] /is [issue] /tag [tag]`
    - `add /i [isbn] /t [title] /b [brand] /is [issue] /tag [tag] /s [status]`
- **EMagazine** has two options:
    - `add /i [isbn] /t [title] /b [brand] /is [issue] /tag [tag] /l [link]`
    - `add /i [isbn] /t [title] /b [brand] /is [issue] /tag [tag] /l [link] /s [status]`
- **Newspaper** has two options:
    - `add /i [isbn] /t [title] /p [publisher] /ed [edition] /tag [tag]`
    - `add /i [isbn] /t [title] /p [publisher] /ed [edition] /tag [tag] /s [status]`
- **ENewspaper** has two options:
    - `add /i [isbn] /t [title] /p [publisher] /ed [edition] /tag [tag] /l [link]`
    - `add /i [isbn] /t [title] /p [publisher] /ed [edition] /tag [tag] /l [link] /s [status]`
    - 
#### Implementation

It implements the following operations:

- `AddCommand#execute(statement: String, container: GenericList<Resource, Event>)` -- Validates and adds the new resource into the resource list.
- `Parser#parseAddCommand(statement: String)` -- Parses the input command to extract the tag indicating the type of resource.
- `Parser#checkFirstItem(statement: String)` -- Parses the input command to ensure that the first item in the input command is valid.
- `AddCommand#addResource*(statement: String, container: GenericList<Resource, Event>)` -- Adds a new resource to the resource list after validation.
- `ParseResource*#parseAddResource*(statement: String)` -- Parses the input command to ensure that it follows the right format for the particular resource.
- `ParseResource*#parseResource*Args(statement: String)` -- Parses the input command to ensure that all attributes required by the particular resource are parsed.
- `ParseResource#hasUnusedSlash(statement: String)` -- Parses the input command to ensure that there are no extra slashes.
- `ParseResource*#hasInvalidArgument(statement: String)` -- Parses the input command to ensure that there are no invalid arguments.
- `ParseAttribute#parseAttribute*(statement: String)`-- Parses the input command to ensure that each attribute follows the right format.
- `ParseResource#countDuplicate(statement: String, pattern: String)` -- Counts the number of matches found for the same argument. 
- `ParseResource*#checkEmptyResource*Args(args: String[])` -- Parses the attribute to ensure that all attributes are not empty.
- `ParseResource*#checkEmptyArg(args: String[])` -- Parses the arguments to ensure that every argument is valid.
- `CreateResource#createResource*(values: String[], resourceID: int)` -- Creates a new resource with the given information.
- `ParseResource*#resetResource*Args()` - Clears the previous attributes to receive new attributes for new resource.

#### Sequence Diagram
The following sequence diagram shows the interactions between objects for the add feature:

<img src="images/AddSequenceDiagram.png"/>

It shows the interaction between seven main classes
- Parser
  - Parser
  - ParseResource* (ParseBook / ParseEBook / ParseCD / ParseMagazine / ParseEMagazine / ParseNewspaper / ParseENewspaper)
  - ParseResource
  - ParseAttribute
- Command 
  - CommandResult
  - AddCommand
- CreateResource

#### Example Usage Scenario

Step 1. The user inputs the command: `add /i 9783161484100 /t Crime and Punishment /a Dostoevsky /tag B /g Fiction /s lost`.

Step 2. The `Parser` processes the command and calls `AddCommand#execute(statement: String, container: GenericList<Resource, Event>)` 
with `/i 9783161484100 /t Crime and Punishment /a Dostoevsky /tag B /g Fiction /s lost`.

Step 3. `AddCommand` receives the command and calls `Parser#parseAddCommand(statement: String)` to extract the tag.

Step 4. The first thing `Parser` does is to call `Parser#checkFirstItem(statement: String)` to ensure that there are no 
unaccepted components in the input.

Step 5. Since the `tag` argument in the input command indicates that it is a book, the `AddCommand` calls
`AddCommand#addBook(statement: String, container: GenericList<Resource, Event>)`.

Step 6. Before adding the book, validation needs to be done so `ParseBook#parseAddBook(statement: String)` was called 
to ensure that the overall input is in the correct format and the required data is provided.

Step 7. After `ParseBook` receives the command, it calls `ParseBook#parseBookArgs(statement: String)` to start
the process of parsing every attribute of the resource.

Step 8. `ParseBook` performs the following operations to validate the overall input:
- Calls `ParseResource#hasUnusedSlash(statement: String)` to ensure that there are no slashes that should not appear.
- Calls `ParseResource*#hasInvalidArgument(statement: String)` to ensure that all arguments given are valid.

Step 9. `ParseBook` performs the following operations to validate each data in the input:
- Calls `ParseAttribute#parseIsbn(statement: String)` to validate and extract ISBN.
- Calls `ParseAttribute#parseTitle(statement: String)` to validate and extract title.
- Calls `ParseAttribute#parseAuthor(statement: String)` to validate and extract author.
- Calls `ParseAttribute#parseTag(statement: String)` to validate and extract tag.
- Calls `ParseAttribute#parseGenre(statement: String)` to validate and extract genres.
- Calls `ParseAttribute#parseStatus(statement: String)` to validate and extract status.
The above calls will each call `ParseResource#countDuplicate(statement: String, pattern: String)` to ensure that the 
same argument does not appear more than once. These extracted attributes form `args: String[]`.

Step 10. After `ParseBook` checked if optional attributes were provided by the user, it checks whether there were empty 
attributes by calling `ParseResource*#checkEmptyResource*Args(args: String[])`. `ParseResource*#checkEmptyArg(args: String[])`
will then be called to check if every argument is valid. After that, all the data are forwarded to `AddCommand`.

Step 11. `AddCommand` receives the arguments and calls `CreateResource#createBook(values: String[], resourceID: int` to 
craft a new book with the validated data.

Step 12. The newly created book is then added to the `resourcesList`.

Step 13. Calls to `ParserResource#resetBookArgs()` prepares the arguments list for new processes.

Step 14. Feedback to users are then sent to `CommandResult` to be displayed to the users.

#### Activity Diagram
The following activity diagram shows an overview of how the activities flow when a user executes the add command:

<img src="images/AddActivityDiagram.png"/>

Step 1. First, it goes into `execute()` where all the functions for adding a resource occur.  
Step 2. An information is added to the log to record the start of execution.  
Step 3. Next, `parseAddCommand()` will identify the type of resource to add.
Step 4. If it is a book, it will call `addBook()` where validation and addition of book occurs. 
If it is none of the listed resources, the add function will stop.  
Step 5. `parseAddBook()` and `parseBookArgs()` will validate every part of the input.  
Step 6. If it manages to pass all the validation, it will proceed to the next step. Else, the add function will stop.  
Step 7. A resource is created by passing the parsed data to `createBook()`.  
Step 8. Finally, this newly created book will be added to the `resourceList`.  
Step 9. A message will be displayed to indicate the successful addition of resource.  
Step 10. Lastly, an information is added to the log to record the resource that has been added.

#### Note
- The word "Resource*" can be replaced by any of the resources 
  - For example, ParseResource* and CreateResource* can be interpreted as ParseBook and CreateBook for the process of adding a book
- The word "Attribute*" can be replaced by any of the attributes
  - For example, parseAttribute* can be interpreted as parseTag or parseIsbn or parseTitle, etc

<div style="page-break-after: always;"></div>

### Show Resources Feature | [Return to contents](#table-of-contents)

Show resources feature is facilitated by the `UI`  component. It makes use of a class `ResourceDisplayFormatter` in `UI` to show the details of all resources stored in `ResourcesList` of the `Parser` component, sorted by resource type. 

#### Implementation

Show Resources feature implements the following operations:
- `UI#showResourcesDetails(resourcesList: List<Resource>)` — Displays a table showing details of all resources sorted by resource type.
- `Resource#checkColumnsWidth(columnsWidth: List<Integer>)` — Checks the length of certain resource attributes against the width of columns and adjusts width if displaying the resource attribute would break the alignment
- `Resource#toTableFormat(formatString: String, tableFormatter: Formatter)` — Formats a string of aligned resource details 

Show Resources feature can be used when the user wants a list of resources and their details, such as when executing the `list` command or showing the results of `find` command. 

The following sequence diagram shows how the show resources feature works in a scenario where the ListCommand calls showResourcesDetails() method.

<img src="images/ShowResourcesSequenceDiagram.png" height="450"/>

<div style="page-break-after: always;"></div>

**ResourceDisplayFormatter class:** 

1. A List< Resource > resourcesList is passed in to `showResourcesDetails` method from the calling function. It can be the resourcesList retrieved from the GenericList in memory which contains the data of all resources, or a custom resourcesList containing filtered resources.
2. A new `ResourceDisplayFormatter` is instantiated and the constructor calls `buildDisplayHeader()` to create a table header. 
3. `buildDisplayHeader()` then calls `checkColumnsWidth()` for every resource in `resourcesList`. It passes an integer array containing the current column width for each resource attribute (Title...Author..et cetera).
4. `checkColumnsWidth()` adjusts the width as needed depending on the length of the resource attributes and returns to Formatter
5. A format specifier is created with the columns' width. Example: `"%-5s %-20s ..."`
6. Formatter objects are created for each resource type: Book, Newspapers, Magazine, CDs. 

**UI Class:** 
7. Now the UI loops through the resourcesList and calls `setDisplayFormatter(Resource)` to add the formatted string to its respective display formatter. 
8. A final call to `getFinalDisplayFormat()` returns the final formatted message of the table and all the resource details as `messageToDisplay`
9. `messageToDisplay` is returned to the calling function to be printed to user or for testing. 

<div style="page-break-after: always;"></div>

### Find Resource Feature | [Return to contents](#table-of-contents)

The `find` command allows users to search for resources based on specified filters such as author (`/a`), ISBN (`/i`), ID (`/id`), and title (`/t`). The results will show all resources that match any of the given filters.

> For non-book resources, `author` refers to `publisher`,`creator` and `brand` for Newspapers, CD's and Magazines
> respectively.

`find` has the following options:
- `find /id [ID]`
- `find /t [TITLE]`
- `find /a [AUTHOR/PUBLISHER/BRAND/CREATOR]`
- `find /i [ISBN]`

Multiple filters can also be combined:

- `find /t [TITLE] /a [AUTHOR/PUBLISHER/BRAND/CREATOR]`

#### Implementation

Upon receiving the `find` command, the system will:

1. Parse the filters and their associated values.
2. Filter the resources based on the given filters.
3. Display the matching resources.

#### Example Usage Scenario

**Step 1.** The user inputs the command: `find /a "F. Scott Fitzgerald"`

**Step 2.** The `UI` component forwards the input to `SYSLIB`, which then sends it to the `PARSER`.

**Step 3.** The `PARSER` processes the command, extracts the `author` filter, and retrieves all resources written by "F. Scott Fitzgerald".

**Step 4.** The matching resources are displayed to the user.

<div style="page-break-after: always;"></div>

#### Sequence Diagram

<img src="images/FindSequenceDiagram.png"/>

<div style="page-break-after: always;"></div>

### Examples for Testing

1. **Find by Author**
    - Test case: `find /a "F. Scott Fitzgerald"`

      Expected: All resources written by F. Scott Fitzgerald are displayed.

2. **Find by ISBN**
    - Test case: `find /i "9780061120084"`

      Expected: The resource with ISBN "9780061120084" is displayed, which should be "To Kill a Mockingbird" by Harper Lee.

3. **Find by ID**
    - Test case: `find /id 2`

      Expected: The resource with ID "2" is displayed, which should be "To Kill a Mockingbird" by Harper Lee.

4. **Find by Title**
    - Test case: `find /t "The Great Gatsby"`

      Expected: The resource titled "The Great Gatsby" is displayed.

5. **Combining Filters**
    - Test case: `find /a "F. Scott Fitzgerald" /t "The Great Gatsby"`

      Expected: Resources that match both the title "The Great Gatsby" and the author "F. Scott Fitzgerald" are displayed.

<div style="page-break-after: always;"></div>

### Listing Resources Feature | [Return to contents](#table-of-contents)

The `list` command is facilitated by `Parser` and `UI` component to show the details of all resources in `resourcesList`. Furthermore, **filter** options can be provided to only list specific resources that match the given filters. 

`list` has five possible options:
- `list`
- `list /tag [tag]`
- `list /g [genre]`
- `list /s [status]`
- `list /tag [tag] /g [genre] /s [status]`

Arguments in italics are filter options and are **optional**. 


#### Implementation 

ListCommand implements the following operations:
- `ListCommmand#execute(statement: String, container: GenericList<Resource, Event>)` — Executes and carries out list feature operations
- `ListCommand#filterResources(givenParameters: String[], resourcesList: List<Resource>>)` — Filters resources based on given filter values


Given below is an example usage scenario where a user enters the input `list /tag B` to list all Resources with tag `B` 
and the corresponding sequence diagram. 

<img src="images/ListSequenceDiagram.png" />

When a user enters `list /tag B`, the Parser retrieves the parameters from the input and
calls the `execute` function of ListCommand, passing the argument given: `/tag B`.

ListCommand then calls `parseArg` and `validate` from `Command`, which checks if the parameters are valid. If it passes
   the checks, `filterResources` is called to begin the filtering process. First it calls `hasFilters()` to check if the user
   selected any filters `[tag/genre/status]` or none. 

If hasFilters returns `true`, it filters the `resourcesList` with the given keywords. Resources matching the filters are added to a temporary list called `matchedResources`. After all resources has been checked, `matchedResources` list is passed to `showResourcesDetails()`, a method called from `UI` to display the details 
of the resources. 

If hasFilters returns `false`, it skips the filtering process and displays the details of all the resources.

Finally, `ListCommand` instantiates the `CommandResult` class with a string `feedbackToUser`, which is returned to the `Parser` which will `print(commandResult.feedbackToUser)` to show the resource details.

<div style="page-break-after: always;"></div>

### Edit Command Feature | [Return to contents](#table-of-contents)

The `edit` command is facilitated by `Parser` component to update the attributes of any resource type. Users can edit all attributes except ID, Tag, and Received Date, and must provide at least one argument to edit when calling the `edit` command. 


#### Implementation

EditCommand implements the following operations:
- `EditCommand#execute(statement: String, container: GenericList<Resource, Event>)` — Executes and handles editing of a resource
- `EditCommand#editResources(foundResource: Resource, givenParameters: String[], resourcesList: List<Resource>)` — Validates parameters and updates a resource details

#### Usage Scenario 

EditCommand may be used when the user would like to update a resource [Book/Newspaper/CDs/Magazines/...] et cetera. Below is an activity diagram showing the flow of SysLib when a user enters `"edit /id 1 /t NEWTITLE /a NEWAUTHOR"`. The diagram starts off with EditCommand's `execute()` method.

<img src="images/EditActivityDiagram.png" />

First, `EditCommand` checks if the user has given at least one argument to edit. For example, `/t` or `/a`. If true, it searches for the resource that has the ID matching the ID given by the user. In this case, it searches for `id == 1`. 

Once found, it moves on to the `editResouce()` method, which contains a switch case that further calls the appropriate edit function based on the resource type. For example, if the resource is a Book or EBook, `validateBookParameters()` and `editBook()` is called. Else, it checks if it's a `CD` and so on. 

The edit methods updates the resource with all the details the user has provided. In this case, `title` is updated to `NEWTITLE` and `author` is updated to `NEWAUTHOR`.

Finally, the resource list currently in memory is updated with the new resource details by calling `resourcesList.set(resourceIndex, updatedResource)`.

<div style="page-break-after: always;"></div>

### Event Add Feature | [Return to contents](#table-of-contents)

The `eventadd` feature is responsible for processing user commands to add an `Event` to SysLib. It is facilitated by
the `EventAddCommand` component. It works with `Parser` and `Command` components to parse and validate the user input.
The new book is stored internally in `eventList` as a `Event`.

`eventadd` has two options:
- `eventadd /t [title] /date [date]`
- `eventadd /t [title] /date [date] /desc [description]`

#### Implementation

It implements the following operations:

- `EVENTADDCOMMAND#parseArgument(statement: String)` -- Parses the input command to extract relevant information.
- `EVENTADDCOMMAND#validate(statement: String, values: String[])` -- Validates the input statement to ensure that it is valid.
- `EVENTADDCOMMAND#binarySearch(container: GenericList<Resource, Event>, date: Date)` -- Search for the correct index of event list to add the event.

#### Example Usage Scenario

Step 1. The user inputs the command: `eventadd /t birthday /date 10 Dec 2001`

Step 2. The `UI` component forwards the input to `SYSLIB`, which in turn passes it to the `PARSER`.

Step 3. The `PARSER` processes the command and determines that it contains a valid key (`eventadd`). It then calls
`EVENTADDCOMMAND#execute(statement: String, this: Parser)` with the input command.

Step 4. The `EVENTADDCOMMMAND` component receives the command and performs the following operations:
- Calls `EVENTADDCOMMAND#parseArgument(statement: String)` to extract values for title, date and description.
- Calls `EVENTADDCOMMAND#validate(statement: String, values: String[])` to ensure the validity of the input command.

Step 5. The `COMMAND` component processes the input command to ensure that it meets the required format and constraints.
It prepares the argument values for further processing.

Step 6. The `EVENTADDCOMMMAND` also calls the component:
- Calls `EVENTADDCOMMAND#binarySearch(container: GenericList<Resource, Event>, date: Date)` to find the correct index based on the date.
The whole eventList is sorted by date order.

Step 7. The newly created event is forwarded to the `PARSER` to be added to the `eventList`.

Sequence Diagram:
<img src="images/EventAdd.png"/>

<div style="page-break-after: always;"></div>

### Event List Feature | [Return to contents](#table-of-contents)

The `eventlist` command works with the `Parser` and `Command` component to execute the correct action. 
This feature is responsible for listing out the events in eventList. 
It is facilitated by the `EventListCommand` component. 

`eventlist` has one option.

#### Implementation

It implements the following operations:

- `EVENTLISTCOMMAND#isEmpty()` -- Check user input has only 'eventlist'

#### Example Usage Scenario

Step 1. The user inputs the command: `eventlist`

Step 2. The `UI` component forwards the input to `SYSLIB`, which in turn passes it to the `PARSER`.

Step 3. The `PARSER` processes the command and determines that it contains a valid key (`eventlist`). It then calls
`EVENTLISTCOMMAND#execute(statement: String, this: Parser)` with the input command.

Step 4. The `EVENTLISTCOMMMAND` component receives the command and performs the following operations:
- Calls `EVENTLISTCOMMAND#isEmpty()` to check if the user input any additional redundant arguments.

Step 5. The `EVENTLISTCOMMAND` then outputs the events in the eventList.

<div style="page-break-after: always;"></div>

<div style="page-break-after: always;"></div>

### Event Delete Feature | [Return to contents](#table-of-contents)

The `eventdelete` feature is responsible for processing user commands to delete an event to SysLib. 
It is facilitated by the `EventDeleteCommand` component. 
It works with `Parser` and `Command` components to parse and validate the input.
The `Event` is removed from `eventsList`.

`eventdelete` has one options:
- eventdelete /id [index]

#### Implementation

It implements the following operations:

- `EVENTDELETECOMMAND#parseArgument(statement: String)` -- Parses the input command to extract relevant information.
- `EVENTDELETECOMMAND#validate(statement: String, values: String[])` -- Validates the input statement.
- `EVENTDELETECOMMAND#parseCalendarInt(value: String, container: GenerticList)` -- Validate the index given.

#### Example Usage Scenario

Step 1. The user inputs the command: `eventdelete /id 0`

Step 2. The `UI` component forwards the input to `SYSLIB`, which in turn passes it to the `PARSER`.

Step 3. The `PARSER` processes the command and determines that it contains a valid key (`eventadd`). It then calls
`EVENTDELETECOMMAND#execute(statement: String, this: Parser)` with the input command.

Step 4. The `EVENTDELETECOMMAND` component receives the command and performs the following operations:
- Calls `EVENTDELETECOMMAND#parseArgument(statement: String)` to extract values for title, date and description.
- Calls `EVENTDELETECOMMAND#validate(statement: String, values: String[])` to ensure the validity of the input command.

Step 5. The `COMMAND` component processes the input command to ensure that it meets the required format and constraints.
It prepares the argument values for further processing.

Step 6. The `EVENTDELETECOMMAND` also calls the component:
- Calls `EVENTDELETECOMMAND#parseCalendarInt(value: String, container: GenerticList)` to see if the index is an integer and that
it is within range of eventsList

Step 7. The selected event at the index is then deleted from the eventsList.

<div style="page-break-after: always;"></div>

<div style="page-break-after: always;"></div>

### Event Edit Feature
The `eventedit` command with the `Parser` and `Command` component to execute the correct action.
This feature is responsible for editing the events in eventsList as given by the user.
It is facilitated by the `EventsListCommand` component.

`eventedit` has three option:
- `eventedit /t [title] /date [date]`
- `eventedit /t [title] /desc [description]`
- `eventedit /t [title] /date [date] /desc [description]`

#### Implementation

It implements the following operations:

- `EVENTEDITCOMMAND#parseArgument(statement: String)` -- Parses the input command to extract relevant information.
- `EVENTEDITCOMMAND#validate(statement: String, values: String[])` -- Validates the input statement to ensure that it is valid.
- `EVENTEDITCOMMAND#binarySearch(container: GenericList<Resource, Event>t, date: Date)` -- Search for the correct index of event list to add the event.

#### Example Usage Scenario

Step 1. The user inputs the command: `eventedit /i 0 /date 23 Dec 2023`

Step 2. The `UI` component forwards the input to `SYSLIB`, which in turn passes it to the `PARSER`.

Step 3. The `PARSER` processes the command and determines that it contains a valid key (`evenedit`). It then calls
`EVENTEDITCOMMAND#execute(statement: String, container: GenerticList)` with the input command.

Step 4. The `EVENTEDITCOMMMAND` component receives the command and performs the following operations:
- Calls `EVENTEDITCOMMAND#parseArgument(statement: String)` to extract values for the attributes to edit.
- Calls `EVENTEDITCOMMAND#validate(statement: String, values: String[])` to ensure the validity of the input command.

Step 5. The `COMMAND` component processes the input command to ensure that it meets the required format and constraints.
It prepares the argument values for further processing.

Step 6. The `EVENTEDITCOMMMAND` also calls the component:
- Calls `EVENTEDITCOMMAND#binarySearch(container: GenericList<Resource, Event>, date: Date)` to find the correct index based on the date.
  The whole eventsList is sorted by date order.

Step 7. The old event is removed from `eventsList`.

Step 8. The edited event is forwarded to the `PARSER` to be inserted to the `eventsList` in accordance to the date.

Sequence Diagram:
<img src="images/EventEditDiagram.png"/>

<div style="page-break-after: always;"></div>

### Summary Feature
The `summary` command with the `Parser` and `UI` component to execute the correct action.
This feature is responsible for showing a summary of the Resources with a visually helpful bar as well as the next 3 events.
It is facilitated by the `SummaryCommand` component.

`summary` has one option.

#### Implementation

It implements the following operations:

- `SUMMARYCOMMAND#isEmpty()` -- Check user input has only 'summary'

#### Example Usage Scenario

Step 1. The user inputs the command: `summary`

Step 2. The `UI` component forwards the input to `SYSLIB`, which in turn passes it to the `PARSER`.

Step 3. The `PARSER` processes the command and determines that it contains a valid key (`summary`). It then calls
`EVENTLISTCOMMAND#execute(statement: String, container: GenerticList)` with the input command.

Step 4. The `SUMMARYCOMMAND` component receives the command and performs the following operations:
- Calls `SUMMARYCOMMAND#isEmpty()` to check if the user input any additional redundant arguments.

Step 5. The `SUMMARYCOMMAND` then gets each Resource from the ResourcesList and checks to see if it is an `instanceof`:
- Book
- EBook
- CD
- Magazine
- EMagazine
- Newspaper
- ENewspaper

and counts as it does so.

Step 5. It then gets all Events from the EventsList.

Step 6. The `SUMMARYCOMMMAND` also calls the component:
- Calls `SUMMARYCOMMAND#getUpcomingEvents(events: List<Event>, 3: int)` to get the 3 upcoming events.

Step 7. The `SUMMARYCOMMMAND` also calls the component:
- Calls `SUMMARYCOMMAND#generateBar(:int)` to get the size of the bar for each Resource. 

Step 8. The SUMMARYCOMMAND then outputs the Resource with a bar to indicate count and the next 3 upcoming events.

Sequence Diagram:
<img src="images/SummaryDiagram.png"/>

<div style="page-break-after: always;"></div>

## Product Scope | [Return to contents](#table-of-contents)

### Target User Profile

System librarians who prefer CLI over GUI and are responsible for inventory and event management.

- Needs to manage events and inventory with significant number of resources e.g. books
- Is a fast typist

### Value Proposition

To provide a platform to help system librarians to quickly find the information they need in their everyday job.

SysLib CLI is a robust command-line tool designed for fast typists librarians to efficiently handle inventory and events. 

With quick command-based actions, they can manage library's resources and events seamlessly. Administrative tasks are simplified, so they can focus on serving patrons better.

<div style="page-break-after: always;"></div>

## User Stories | [Return to contents](#table-of-contents)

| Version | As a ...  | I want to ...                                                                              | So that I can ...                                                                                  |
|---------|-----------|--------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------|
| v1.0    | librarian | view a list of books that the library has                                                  | have an overview of all the books                                                                  |
| v1.0    | librarian | add new books to our inventory by entering their title, author, ISBN, and publication year | keep our collection up-to-date                                                                     |
| v1.0    | librarian | delete books from the database                                                             | let the patrons know our library no longer carries it                                              |
| v1.0    | librarian | tag a book as physical medium like newspapers, audio cds, books or online like eMagazines  | patrons can know what type are available                                                           |
| v1.0    | librarian | have a help function                                                                       | know the commands of this programme                                                                |
| v1.0    | librarian | quickly find out how many books we have of a particular author and the names of the books  | know how many books are related to the author                                                      |
| v2.0    | librarian | import/export our library's inventory data to a file format                                | easily share with other libraries to be used for backup and archival purposes.                     |
| v2.0    | librarian | find a to-do item by name                                                                  | locate a to-do without having to go through the entire list                                        |
| v2.0    | librarian | get the status of an item                                                                  | know if it is available                                                                            |
| v2.0    | librarian | update the details of a resource                                                           | fix any mistakes and maintain consistency                                                          |
| v2.1    | librarian | add in different types of resources                                                        | differentiate between resources such as books, cds, magazines, newspapers, and electronic versions |

<div style="page-break-after: always;"></div>

## Use Cases | [Return to contents](#table-of-contents)

(For all use cases below, the System is the SysLib and the Actor is the user, unless specified otherwise)

### Use case: Add a book

#### MSS
1. User requests to add a book
2. SysLib adds the book

    Use case ends.

#### Extensions
- 1a. The given `ISBN` is invalid.
  - 1ai. SysLib displays an error message.
    
    Use case ends.
  
- 1b. Insufficient data given.
  - 1bi. SysLib displays an error message.

    Use case ends.

<div style="page-break-after: always;"></div>

### Use case: Edit a resource

#### MSS

1. User requests to list resources
2. SysLib shows a table of resources and their details
3. User requests to edit a resource by specifying their ID shown in list.
4. SysLib updates resource with new given details. 

   Use case ends. 

#### Extensions

- 1a. List is empty 
  - SysLib shows a message there are no resources to list.
  - Use case ends.

- 3a. Provided ID does not exist
  - SysLib shows a message stating resource not found.
  - Use case ends.

- 3b. User does not provide any arguments to edit
  - SysLib shows a message prompting for at least one argument.
  - Use case ends.

- 3c. User provides the wrong arguments for the type of resource they specified
  - SysLib shows an invalid argument message and displays the right arguments for the resource type
  - Use case ends.

<div style="page-break-after: always;"></div>

### Use case: Delete a resource

#### MSS

1. User requests to list resources
2. SysLib shows a table of resources and their details
3. User requests to delete a resource by specifying their ID shown in list.
4. SysLib removes the specified resource from the list.

   Use case ends.

#### Extensions

- 1a. List is empty
    - SysLib shows a message there are no resources to list.
    - Use case ends.

- 3a. Provided ID does not exist
    - SysLib shows a message stating resource not found.
    - Use case ends.

- 3b. User does not provide any arguments to edit
    - SysLib shows a message prompting for at least one argument.
    - Use case ends.

- 3c. User provides the wrong arguments for the type of resource they specified
    - SysLib shows an invalid argument message and displays the right arguments for the resource type
    - Use case ends.

<div style="page-break-after: always;"></div>

### Use case: Find a resource

#### MSS

1. User requests to list resources
2. SysLib shows a table of resources and their details
3. User requests to Find a resource by specifying their ID shown in list.
4. SysLib returns the resource with the ID specified by the user.

   Use case ends.

#### Extensions

- 1a. List is empty
    - SysLib shows a message there are no resources to list.
    - Use case ends.

- 3a. Provided ID does not exist
    - SysLib shows a message stating resource not found.
    - Use case ends.

- 3b. User does not provide any arguments to edit
    - SysLib shows a message prompting for at least one argument.
    - Use case ends.

- 3c. User provides the wrong arguments for the type of resource they specified
    - SysLib shows an invalid argument message and displays the right arguments for the resource type
    - Use case ends.

<div style="page-break-after: always;"></div>

### Use case: Add an event

#### MSS
1. User requests to add a event
2. SysLib adds the event

   Use case ends.

#### Extensions
- 1a. The given `date` is invalid.
    - 1ai. SysLib displays an error message.
    - Use case ends.

- 1b. Insufficient data given.
    - 1bi. SysLib displays an error message.
    - Use case ends.

<div style="page-break-after: always;"></div>

### Use case: Delete an event

#### MSS
1. User requests to list the events with `eventlist`
2. SysLib shows the list of events
3. User requests to delete a specific event based on `id`
4. Syslib deletes the event

   Use case ends.

#### Extensions
- 2a. The list is empty.
    - 1ai. SysLib displays the message for empty list.
    - Use case ends.

- 3a. The given index is invalid
    - 1bi. SysLib displays an error message.
    - Use case resumes at step 2.

<div style="page-break-after: always;"></div>

### Use case: Edit an event

#### MSS
1. User requests to list the events with `eventlist`
2. SysLib shows the list of events
3. User requests to edit a specific event based on `id`
4. Syslib edits the event

   Use case ends.

#### Extensions
- 2a. The list is empty.
    - 1ai. SysLib displays the message for empty list.
    - Use case ends.

- 3a. The given index is invalid
    - 1bi. SysLib displays an error message.
    - Use case resumes at step 2.
  
- 3b. Insufficient data given.
    - 1bi. SysLib displays an error message.
    - Use case resumes at step 2.
      
<div style="page-break-after: always;"></div>

## Non-Functional Requirements | [Return to contents](#table-of-contents)

- Should work on any mainstream OS as long as it has Java 11 or above installed.
- Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
- The system should respond to user input within 3 seconds under normal operating conditions.
- A user with above-average typing speed for regular English text (i.e., not code, not system admin commands) 
should be able to accomplish most of the tasks faster using commands than using the mouse.
- All user data relating to resources and events will be stored in a .txt file in the same folder as this jar file.

## Glossary | [Return to contents](#table-of-contents)

* *Resource* - A generic term for items in library inventory.
  * Further divided into: Book, EBook, Magazines, EMagazines, Newspapers, ENewspapers, and CDs. 
  
<div style="page-break-after: always;"></div>

## Instructions for Manual Testing | [Return to contents](#table-of-contents)

> Note: These instructions only provide a starting point for testers to work on; Testers are expected to do more _exploratory_ testing.

### Launch & Shutdown

1. Initial launch
   1. Download the jar file and copy it into an empty folder.
   2. Open the command prompt / terminal and run `java -jar SysLib.jar`. You should see the following welcome message:

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

2. Shutdown
   1. Enter the command `exit` into the program.
   2. Close the command prompt / terminal.
   

Example response:

```
Thanks for using SysLib! We have saved the current resources and events.
See you next time!
____________________________________________________________
```

### Adding a Book
1. Add a book
   1. Test case: `add /i 9783161484100 /t Crime and Punishment /a Dostoevsky /tag B`

        Expected: A book with ISBN: 9783161484100, Title: Crime and Punishment, Author: Dostoevsky, and Status: AVAILABLE 
   is created and added into the list. A message with details of the added book is displayed to acknowledge that the 
   book has been added successfully. A warning is given to tell the user that the Status and Genre was not given.

    ```
    Attention: Status is not stated. Status set to default: AVAILABLE.
    Attention: Genre is not stated. Genre not set.
    This book is added:
    [B]  ID: 1 Title: Crime and Punishment ISBN: 9783161484100 Author: Dostoevsky Genre: - Status: AVAILABLE Received Date: 12 Nov 2023
    ____________________________________________________________
    ```
   
   2. Test case: `add /i 9783161484100 /t Crime and Punishment /a Dostoevsky /tag B /g Fiction /s lost`

       Expected: A book with ISBN: 9783161484100, Title: Crime and Punishment, Author: Dostoevsky, Genre: Fiction, and 
   Status: LOST is created and added into the list. A message with details of the added book is displayed to 
   acknowledge that the book has been added successfully.
   
    ```
    This book is added:
    [B]  ID: 2 Title: Crime and Punishment ISBN: 9783161484100 Author: Dostoevsky Genre: Fiction Status: LOST Received Date: 12 Nov 2023
    ____________________________________________________________
    ```
   
   3. Test case: `add /i CAP123 /t Crime and Punishment /a Dostoevsky /tag B`
      
       Expected: No book is added. An error message is displayed to indicate that the ISBN is invalid.
   
    ```
    Please enter a valid ISBN with 13 digits.
    ____________________________________________________________
    ```
   4. Test case: `add /i 9783161484100 /t Crime and Punishment /a Dostoevsky /tag A`

      Expected: No book is added. An error message is displayed to indicate that the tag is invalid.
   
    ```
    Please enter a valid tag.
    ____________________________________________________________
    ```
   5. Test case: `add /tag B`
   
      Expected: No book is added. An error message displayed to indicate that the input is incomplete.
   
    ```
    Please enter a valid ISBN with 13 digits.
    ____________________________________________________________
    ```
   6. Test case: `add /i 9783161484100 /t Crime and Punishment /t Crime and Punishment2 /a Dostoevsky /tag B`

      Expected: No book is added. An error message displayed to indicate that there are multiple titles.

    ```
    Please enter only 1 title.
    ____________________________________________________________
    ```

### Listing Resources

1. List all resources
    1. Prerequisites: At least one resource present in list   
      
    2. Test case: `list`

    Expected: A table showing details of current resources, in order of BOOKS, MAGAZINE, CDs, and NEWSPAPERS.


2. List when no resources are in list
    1. Prerequisites: No resources currently in SysLib

    2. Test case: `list`

    Expected: An error message saying "There are currently 0 resources."
    
    ```
    There are currently no Resources in Syslib!
    ____________________________________________________________
    ```
   
3. List resources with filter options

   1. Test case: `list /tag B `

       Expected: A table showing details of `Book` resources with tag `B`, or a message stating no resources found or empty list if applicable.
   
   2. Test case: `list /tag B /s AVAILABLE`

      Expected: A table showing details of `Book` resources with tag `B` and status `AVAILABLE`, or a message stating no resources found or empty list if applicable.



4. Other incorrect commands to try: list X, list /tag , ... 

   Expected: Invalid argument message. 

    ```
    Unknown variable/command: x, avoid using '/' in names/variables
    ____________________________________________________________
    ```

### Editing a Resource

1. Edit a resource 
   1. Prerequisite: A list containing at least one resource. `list` all the resources to see their `ids`. 
   2. Test case: `edit /id 1 /t NEWTILE /a AUTHOR`

      Expected: An edit success message displaying the new details of the edited resource, IF resource with `id 1` is a Book (Author is a Book argument). Else, error message saying wrong arguments and showing the right arguments.
      
        ```
        Successfully updated! Your updated resource:
      
        [B]  ID: 1 Title: NEWTILE ISBN: 9783161484100 Author: AUTHOR Genre: - Status: AVAILABLE Received Date: 13 Nov 2023
        ____________________________________________________________
      
        ```
      
   3. Test case: `edit /id 1 /c NEWCREATOR`

      Expected: An edit success message displaying the new details of the edited resource, IF resource with `id 1` is a CD (Creator is a CD argument). Else, error message saying wrong arguments and showing the right arguments.

2. Other incorrect commands to try: `edit /id 0 X`, `edit /t` , ...

   Expected: Invalid argument message.

    ```
    Please provide at least one detail to edit!
    For Books: /t TITLE /a AUTHOR /g GENRES /s STATUS /i ISBN
    For EBooks: /t TITLE /a AUTHOR /g GENRES /s STATUS /l LINK /i ISBN
    ____________________________________________________________
    For Magazines: /t TITLE /b BRAND /is ISSUE /s STATUS /i ISBN
    For EMagazines: /t TITLE /b BRAND /is ISSUE /s STATUS /l LINK /i ISBN
    ____________________________________________________________
    For CDs: /t TITLE /c CREATOR /ty TYPE /s STATUS /i ISBN
    ____________________________________________________________
    For Newspapers: /t TITLE /p PUBLISHER /ed EDITION /s STATUS /i ISBN
    For ENewspapers: /t TITLE /p PUBLISHER /ed EDITION /s STATUS /l LINK /i ISBN
    ____________________________________________________________
    ```

### Deleting Resources
1. Delete a resource
    1. Prerequisites: At least one resource present.
    2. Test case: `delete /id 1`

   Expected: Resource with ID 1 is removed
   
    ```
    Looking for ID: 1...
    This resource is removed:
    [B]  ID: 1 Title: Crime and Punishment ISBN: 9783161484100 Author: Dostoevsky Genre: null Status: AVAILABLE Received Date: 13 Nov 2023
    ____________________________________________________________
    ```
   
2. Delete a resource that is not there
    1. Prerequisites: Resource is non-existent
    2. Test case: `delete /id 10`

   Expected: Error message saying the resource with given ID is not found.

    ```
    Looking for ID: 10...
    No resources with id matching 10
    ____________________________________________________________
    ```

### Adding an Event
1. Add an Event
    1. Test case: `eventadd /t New Year /date 1 Jan 2024`

       Expected: An Event with Title: New Year, Date: 1 Jan 2024, Description: null is 
       created and added into the list. A message with details of the added event is displayed to acknowledge that the
       event has been added successfully.

    ```
    Event inserted at: 0
    0: New Year | 01 Jan 2024 | null
    ____________________________________________________________
    ```

    2. Test case: `eventadd /t Meeting /date 23 Dec 2023 /desc Board Meeting`

       Expected: An Event with Title: Meeting, Date: 23 Dec 2023, Description: Board Meeting is
       created and added into the list. A message with details of the added event is displayed to acknowledge that the
       event has been added successfully.
    
    ```
    Event inserted at: 0
    0: Meeting | 23 Dec 2023 | Board Meeting
    ____________________________________________________________
    ```

    3. Test case: `eventadd /date 23 Dec 2023`

       Expected: No event is added. An error message is displayed to indicate that the Title is missing.
    
    ```
    t is missing in the argument!
    ____________________________________________________________
    ```
    4. Test case: `eventadd /t Meeting`

       Expected: No event is added. An error message is displayed to indicate that the date is missing.
    
    ```
    date is missing in the argument!
    ____________________________________________________________
    ```

### Listing Events

1. List all events
    1. Prerequisites: At least one event present in list

    2. Test case: `eventlist`

   Expected: A list showing details of current events, in order of its date.

    ```
    This is the current event list:
    0: Meeting | 23 Dec 2023 | Board Meeting
    1: New Year | 01 Jan 2024 | null
    ____________________________________________________________
    ```

2. List when no resources are in list
    1. Prerequisites: No events currently in SysLib

    2. Test case: `eventlist`

   Expected: An error message saying "There event list is empty."

    ```
    There are currently no Events in Syslib!
    ____________________________________________________________
    ```

3. List with unexpected arguments
   1. Test case: `eventlist /t title`

   Expected: An error message saying "'eventlist' command does not require arguments!"

    ```
    'eventlist' command does not require arguments!
    ____________________________________________________________
    ```

### Editing an Event

1. Edit a resource
    1. Prerequisite: A list containing at least one event. Use `eventlist` to see their `ids`.
    2. Test case: `eventedit /id 0 /t Board Meeting`

       Expected: An edit success message displaying the new details of the edited event.
    
    ```
    Event edited successfully. New event details:
    0: Board Meeting | 23 Dec 2023 | Board Meeting
    ____________________________________________________________
    ```

   2. Test case: `eventedit /id 0 /t Meeting /date 22 Dec 2023 /desc Board Meeting with CEO`

       Expected: An edit success message displaying the new details of the edited event.
   
    ```
    Event edited successfully. New event details:
    0: Meeting | 22 Dec 2023 | Board Meeting with CEO
    ____________________________________________________________
    ```

2. Edit id given but no parameters were given
   1. Test case: `eventedit /id 0`
   
       Expected: An error message saying "Event was not edited as nothing was changed."
   
    ```
    Event was not edited as nothing was changed.
    ____________________________________________________________
    ```

3. Invalid id was given
    1. Test case: `eventedit /id 0`

       Expected: An error message saying "Event was not edited as nothing was changed."
    
    ```
    Invalid event index
    ____________________________________________________________
    ```

### Deleting Event
1. Delete an event
    1. Prerequisites: At least one resource present.
    2. Test case: `eventdelete /id 0`

   Expected: Event with ID 0 is removed

    ```
    This event is removed:
    Meeting | 22 Dec 2023 | Board Meeting with CEO
    ____________________________________________________________
    ```

2. Delete an event that is not there
    1. Prerequisites: Event is non-existent
    2. Test case: `eventdelete /id 10`

   Expected: Error message saying the event with given ID is not found.

    ```
    Index is out of range of the event list!
    ____________________________________________________________
    ```
   
### Saving data

The following are some test cases for you to try: 

> **Important!** These test cases are done on the assumption that storage.txt file is empty.
> If you have some data written into these files or modified storage.txt, please do the following prior to conducting the test cases mentioned below:
> 
>(1) If Syslib is running, exit the application.
> 
>(2) Backup your existing `storage.txt` file in the `data` directory.
> 
>(3) Delete the `data` directory (not your backup!)
> 
>(4) Start Syslib to generate a fresh `storage.txt` in `data` directory.

| Test Case                             | Command                                                                                                                                                                                                                                                          | Expected result                                                                                                                                                                                    |
|:--------------------------------------|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1. Write new data into `storage.txt`. | (a) `add /i 9783161484100 /t Crime and Punishment /a Dostoevsky /tag B `<br/>(b) `add /i 9783161484100 /t Crime and Punishment /a Dostoevsky /tag B /g Fiction /s lost`  <br/> (c) `add /i 9780763630189 /t Frankenstein /c Mary Shelley /ty Audio Book /tag cd` | The following two lines are added to `storage.txt` on exit:<br/><br/>![storage.txt](images/StorageNewResources.png)                                                                                |
| 2. Update data in `storage.txt`.      | `edit /id 1 /t NEWTITLE /a AUTHOR`                                                                                                                                                                                                                               | The book `Crime and Punishment` with id `1` has new title: `NEWTITLE`, new author `AUTHOR`. `storage.txt` should look something like this:<br/><br/>![storage.txt](images/StorageEditResource.png) |
| 3. Delete data from `storage.txt`.    | `delete /id 1`                                                                                                                                                                                                                                                   | The book `Crime and Punishment` with id `1` will be removed:<br/><br/>![storage.txt](images/StorageDeleteResource.png)                                                                             |