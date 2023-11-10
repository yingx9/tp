# Joanne Ang - Project Portfolio Page

## Overview

SysLib is a command-line (CLI) library management software tailored for librarians, especially those who are fast typists. 
It offers a wide range of features to manage library resources and events, from adding, editing, viewing, searching, deleting, to saving data file. 


### Summary of Contributions

#### Code Contributed

View the code I contributed [here](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?search=joannejo&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos&tabOpen=true&tabType=authorship&tabAuthor=JoanneJo&tabRepo=AY2324S1-CS2113T-W11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).

#### Enhancements Implemented

**Enhancements**:
1. Resource classes
2. Add feature for resources
3. Validation for adding resources
4. Exception class
5. 

**Details**:

**1. Resource classes**

Implementation: `Book`, `EBook`, `CD`, `Magazine`, `EMagazine`, `Newspaper`, `ENewspaper` classes

Implemented the different resource classes for each resource has different attributes.

**2. Add feature for resources**

Implementation: `AddCommand`, `CreateResource` class

Implemented the **add** feature to add resources into a list of all the resources in the library.

Add feature supports the addition of:
- Book
  - Attributes: Title, ISBN, Status, Author,Genres
- EBook
  - Attributes: Title, ISBN, Status, Author, Genres, Link
- CD
  - Attributes: Title, ISBN, Status, Creator, Type
- Magazine
  - Attributes: Title, ISBN, Status, Brand, Issue
- EMagazine
  - Attributes: Title, ISBN, Status, Brand, Issue, Link
- Newspaper
  - Attributes: Title, ISBN, Status, Publisher, Edition
- ENewspaper
  - Attributes: Title, ISBN, Status, Publisher, Edition, Link

**3. Validation for adding resources**

Implementation: `ParseResource`, `ParseBook`, `ParseEBook`, `ParseCD`, `ParseMagazine`, `ParseEMagazine`, `ParseNewspaper`, `ParseENewspaper` classes

Implemented the validation and error handling of user input for the **add** feature.
Checks the user input for all attributes in all resources. Returns error messages to suggest valid input.

**4. Exception class**

Implementation: `SysLibException` class

Catches exceptions related to SysLib and prevents program from exiting due to exceptions. 
Returns error messages to user so that the exception can be prevented with the right user input.

#### Contributions to the UG

Sections contributed:
- Add Command
- 

#### Contributions to the DG

Sections contributed:
- Setting Up & Getting Started
- Add Resource Feature
- Use Case: Add a book
- Instructions for Manual Testing
- 

#### Contributions to Team-Based Tasks

- Reviewed multiple peer pull request
- Manage issue tracker
- 

#### Reviewing / Mentoring Contributions

- Peer reviewed other team's project
- Test other team's project during PE-D

#### Contributions Beyond the Project Team
