# Velusamy Sathiakumar Ashok Balaji - Project Portfolio Page

## Overview

SysLib is a CLI Library Management software for librarians especially those who are fast typists.

From viewing, adding, searching, editing, deleting and saving, SysLib provides all the features needed to manage library resources and events.

### Summary of Contributions

#### Code contributed

View the code I contributed via the tp Code Dashboard link [here](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?search=000verflow&breakdown=true).


#### Enhancements implemented

**Enhancements**:
1. Classes and methods to format display and messages to users.
2. Find command to search and return resources that match all given arguments.
3. Storage class that can save and load resources and events.
4. GenericList class to act as a container for both Resource lists and Event lists.


**Details:**

**1. Classes and methods to format display and messages to users**

Main classes and methods implemented:
`Syslib` class
`UI` class

Created the Syslib and UI class's to ensure proper OOP used for running the software, taking inputs from users, and displaying output through the UI class.

**2. Find feature for resources**

Main implementation: `FindCommand` class

Implemented the **Find** feature that will return resources that match given filters such as `author`, `title`, `id` and `isbn`.
Only returns results that match `ALL` the given filters not `ANY`.

**3. Storage Class**

Main implementation: `Storage` class

Implemented the Storage class which allows users to `save` resources and events that have been added to the list. The resources and events will be loaded back into the appropriate lists, when the program is run again.
The storage class is filled with exception catching mechanisms to ensure minimal bugs in case of corrupted data.

**4. GenericList Class**

Main Implementation: `GenericList` class

Implemented the GenericList class which allows us to store 2 lists of Resources and Events separately. This allows us to handle loading, storing and command handling efficiently. 
GenericList currently is just used as a container for both types of lists.

#### Contributions to the UG:

Sections contributed:
- Overall structure including Contents, Quick start, Features.
- Add command
- Find Command
- Command Summary
- Save your work section
- Finding bugs in peer's sections

#### Contributions to the DG:

Sections contributed:
- Storage Class section and Storage class diagram
- Find Command section and FindCommand sequence diagram
- Manual testing for data saving
- Structural changes
- Introduction and getting started

#### Contributions to team-based tasks

- Reviewed multiple peer pull request
- Help troubleshoot when checks fail on GitHub
- Bug tested 2.0 and 2.1 Jar's

#### Review/mentoring contributions:

- Helped test other group's project during PE-D, creating 5 bug issues
- Peer reviewed other team's developers guide in their [PR]("https://github.com/nus-cs2113-AY2324S1/tp/pull/8")

