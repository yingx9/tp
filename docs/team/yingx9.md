# Loke Ying Xia - Project Portfolio Page

## Overview

SysLib is a CLI Library Management software for librarians especially those who are fast typists. 

From viewing, adding, searching, editing, deleting and saving, SysLib provides all the features needed to manage library resources and events.
### Summary of Contributions

#### Code contributed

View the code I contributed via the tp Code Dashboard link [here](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?search=yingx9&breakdown=true).


#### Enhancements implemented 

**Enhancements**: 
1. Classes and methods to format display and messages to users
2. List feature for resources
3. Edit feature for resources
4. Received Date attribute for resources


**Details:** 

**1. Classes and methods to format display and messages to users**

Main classes and methods implemented: 
`ResourceDisplayFormatter` class, 
`showResouces()` in `UI` class.
`checkColumnWidths()`, `toTableFormat()` in all resource class. 

Implemented a class `ResourceDisplayFormatter` which formats a table to neatly display all the resources and their details. 

SysLib contains several types of resources (Books, EBooks, Magazines, EMagazines, CD...et. cetera) and each type has their own attributes to display. 

The approach taken was to list the resources by type so users easily follow and understand the table. If the library does not contain any resource for a resource type, e.g No CDs, its headers are omitted from the table to reduce useless clutter. 

`ResourceDisplayFormatter` also dynamically checks the length of each variable and adjust the width and length of the headers automatically. Hence, even if the user enters a long input, the alignment stays neat and does not cut off any information. 

This is implemented by creating methods in the `Resource` class, which all subclasses (resource types) inherit and override to check the length of their own attributes.

`showResources()` was implemented in the UI class to easily display the resources and make it accessible for use in other classes, for example in `ListCommand`. 


**2. List feature for resources**

Main implementation: `ListCommand` class

Implemented the **list** feature to show a list of all the resources in the library, sorted by the type of Resource : Book, Magazine, CD, Newspapers and their electronic versions. All details are shown in the list and formatted neatly using `ResourceDisplayFormatter`.

Further enhanced by implementing `filter` options to filter by `tag`, `genre`, and `status`, which shows resources that fit all given filters, to help librarians get a specific overview of resources in the library. 


**3. Edit feature for resources**

Main implementation: `EditCommand` class

Implemented the **edit** feature for all resource type: Book, Magazine, Newspaper, CD, and their electronic versions.

Edit feature supports editing:
- **For all resources:** Title, ISBN, Status 
- In addition, Specific Attributes for: 
  - **Books/EBooks:** Author, Genres, Link
  - **Magazines/EMagazines:** Brand, Issue, Link
  - **Newspaper/ENewspaper:** Publisher, Edition, Link 
  - **CD:** Creator, Type

Implemented validation and error checking for EditCommand, which prevents the user from entering wrong arguments for the resource type they would like to edit and error messages that remind users the correct arguments. 


**4. Received Date attribute for resources**

Main implementation: `getReceivedDate()`, `setReceivedDate()` in `Resource` class

Implemented methods in Resource class which sets the date received to the current system time when executing `addCommand`. This attribute indicates the date entered the resource was entered into the system, hence it is not set by the user and instead takes the current system time. 



#### Contributions to the UG: 

Sections contributed: 
- Introduction (First part before How to use User Guide section)
- List Command
- Edit Command 
- Known Issues: List issues section

#### Contributions to the DG:

Sections contributed:
- Acknowledgement
- Overall architecture diagram and Component Diagram
- Architecture section (The section before UI Component)
- List Command section and ListCommand sequence diagram
- Edit Command section and Edit activity diagram
- Show Resources feature section and Show Resources sequence diagram
- Instructions for manual testing: 
  - List and Edit resource sections
- Glossary: Resource definition
- Use Case: Edit a resource


#### Contributions to team-based tasks

- Setup Team organization and repo 
- Helped managed some issues by opening and closing when done
- Add on to Value proposition and Target Audience in Developer's Guide
- Bug test 2.0 jar file and create issues based on bugs found

#### Review/mentoring contributions: 

- Reviewed multiple peer pull request such as commenting on sequence diagram. Example: [#166](https://github.com/AY2324S1-CS2113T-W11-1/tp/pull/166)
- Help troubleshoot when checks fail on GitHub  Example: [#22](https://github.com/AY2324S1-CS2113T-W11-1/tp/pull/22), [#38](https://github.com/AY2324S1-CS2113T-W11-1/tp/pull/38)

#### Contributions beyond the project team:

- Helped test other group's project during PE-D, creating 9 bug issues
- Peer reviewed other team's developers guide in their [PR](https://github.com/nus-cs2113-AY2324S1/tp/pull/18/files)