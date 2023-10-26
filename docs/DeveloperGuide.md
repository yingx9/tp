# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Setting Up & Getting Started

1. Fork the repo at https://github.com/AY2324S1-CS2113T-W11-1/tp.
2. Clone the fork into your computer.

## Design & Implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Architecture

<img src="images/ArchitectureDiagram.png" />


**Main components of SysLib Architecture**

SysLib currently consists of four main components:

- `UI`: User Interaction
- `Parser`: Parsing User Response 
- `Command`: Command Executor
- `Data`: Holds the data of SysLib


   
### UI Component

### Parser Component

### Command Component


### Data Component

## Implementation 
This section provides details on how certain features are implemented. 

### Add Resource Feature

The `add` feature is responsible for processing user commands to add a new book to SysLib. It is facilitated by 
the `AddCommand` component. It works with `Parser` and `Command` components to parse and validate the user input. 
The new book is stored internally in `resourceList` as a `Book`. 

`add` has six options:
- add /id [id] /t [title] /a [author] /tag [tag] /i [isbn]
- add /id [id] /t [title] /a [author] /tag [tag] /i [isbn] _/g [genre]_

#### Implementation

It implements the following operations:

- `ADDCOMMAND#parseArgument(statement: String)` -- Parses the input command to extract relevant information.
- `ADDCOMMAND#validate(statement: String, values: String[])` -- Validates the input statement to ensure that it is valid.
- `ADDCOMMAND#createBook(values: String[])` -- Creates a new book based on the parsed and validated values.

#### Example Usage Scenario

Step 1. The user inputs the command: `add /id 0005 /t Frankenstein /a Mary Shelley /i FKS0001 /tag B /g Gothic, Fiction`

Step 2. The `UI` component forwards the input to `SYSLIB`, which in turn passes it to the `PARSER`.

Step 3. The `PARSER` processes the command and determines that it contains a valid key (`add`). It then calls 
`ADDCOMMAND#execute(statement: String, this: Parser)` with the input command.

Step 4. The `ADDCOMMMAND` component receives the command and performs the following operations:
- Calls `ADDCOMMAND#parseArgument(statement: String)` to extract values for ID, title, author, ISBN, tag, and genres.
- Calls `ADDCOMMAND#validate(statement: String, values: String[])` to ensure the validity of the input command.

Step 5. The `COMMAND` component processes the input command to ensure that it meets the required format and constraints.
It prepares the argument values for further processing.

Step 6. Since the `tag` argument in the input command indicates that it is a book, the `ADDCOMMAND` determines that the
key is equal to `b` (ignoring case). It then creates a new `Book` object using the parsed values (title, ISBN, author, 
genres, ID).

Step 7. The newly created book is forwarded to the `PARSER` to be added to the `resourceList`.

#### Sequence Diagram
The following sequence diagram shows how the add function works:
<img src="images/AddSequenceDiagram.png"/>

### Listing Resources Feature

The `list` command works with the `Parser` and `Command` component to execute the correct action. 

`list` has four options:
- list
- list _/tag [tag]_
- list _/g [genre]_
- list _/tag [tag]_ _/g [genre]_

When `list` is specified with both `tag` and `genre` filters, it is `AND` inclusive, listing only 
Resources with the same tag and genre. 

Sequence Diagram:

<img src="images/ListSequenceDiagram.png" />

When a user enters `list /tag B`, the Parser retrieves the parameters from the input and
calls the `execute` function of ListCommand.

ListCommand then calls `parseArg` and `validate` from `Command`, which checks if the parameters are valid. If it passes
the checks, `setListFilters` is called to check if the user selected any filters `[tag/genre/both]` or none. It will
filter the `resourceList` with the given keywords, if any, and display the details of the resources.

## Product scope

### Target user profile

All librarians, not just system librarian!

- Needs to manage inventory with significant number of resources e.g. books
- Is a fast typist

### Value Proposition

To provide a platform to help librarians to quickly find the information they need to assist patrons.

## User Stories

|Version| As a ...  | I want to ...                                                                              | So that I can ...                                           |
|--------|-----------|--------------------------------------------------------------------------------------------|-------------------------------------------------------------|
|v1.0| librarian | view a list of books that the library has                                                  | have an overview of all the books                           |
|v1.0| librarian | add new books to our inventory by entering their title, author, ISBN, and publication year | keep our collection up-to-date                              |
|v1.0| librarian | delete books from the database                                                             | let the patrons know our library no longer carries it       |
|v1.0| librarian | tag a book as physical medium like newspapers, audio cds, books or online like eJournals   | patrons can know what type are available                    |
|v1.0| librarian | have a help function                                                                       | know the commands of this programme                         |
|v1.0| librarian | quickly find out how many books we have of a particular author and the names of the books  | know how many books are related to the author               |
|v2.0| user      | find a to-do item by name                                                                  | locate a to-do without having to go through the entire list |


## Use Cases

(For all use cases below, the System is the SysLib and the Actor is the user, unless specified otherwise)

### Use case: Add a book

#### MSS
1. User requests to add a book
2. AddressBook adds the book

    Use case ends.

#### Extensions
- 1a. The given ID is invalid.
  - 1a1. SysLib shows an error message.
    
    Use case ends.
  
- 1b. Insufficient data given.
  - 1b1. SysLib shows an error message.

    Use case ends.

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for Manual Testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

### Launch and Shutdown

1. Initial launch
   1. Download the jar file and copy it into an empty folder.
   2. Open the command prompt and run `java -jar SysLib.jar`.


### Adding a Book
1. Add a book
   1. Test case: `add /id 0005 /t Frankenstein /a Mary Shelley /i FKS0001 /tag B`

       Expected: A book with ID: 0005, Title: Frankenstein, Author: Mary Shelley, and ISBN: FKS0001 is created and added 
       into the list. A message is shown to acknowledge that the book has been added successfully.
   
   2. Test case: `add /id 0005 /t Frankenstein /a Mary Shelley /i FKS0001 /tag B /g Gothic, Fiction`

       Expected: A book with ID: 0005, Title: Frankenstein, Author: Mary Shelley, ISBN: FKS0001, and 
       Genres: Gothic, Fiction is created and added into the list. A message is shown to acknowledge that the book 
       has been added successfully.
   
   3. Test case: `add /id abcd /t Frankenstein /a Mary Shelley /i FKS0001 /tag B /g Gothic, Fiction`
        
       Expected: No book is added. An error message is shown to indicate that the id is invalid.

   4. Test case: `add /id 0005 /t Frankenstein /a Mary Shelley /i FKS0001 /tag A /g Gothic, Fiction`

      Expected: No book is added. An error message is shown to indicate that the tag is invalid.

   5. Test case: `add /id 0005 /t Frankenstein`
   
      Expected: No book is added. An error message is shown to indicate that the input is incomplete.