# Loke Ying Xia - Project Portfolio Page

## Overview

SysLib is a CLI Library Management software for system librarians especially those who are fast typists. 

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

**2. List feature for resources**

Main implementation: `ListCommand` class. Implemented the **list** feature to show a list of all the resources in the library.
Further enhanced by implementing `filter` options to filter by `tag`, `genre`, and `status`, which shows resources that fit all given filters, to help librarians get a specific overview of resources in the library.

**3. Edit feature for resources**

Main implementation: `EditCommand` class. Implemented the **edit** feature for all resource type: Book, Magazine, Newspaper, CD, and their electronic versions.

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
- Instructions for manual testing: List and Edit resource sections
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