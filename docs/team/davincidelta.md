# Wu Xingyu - Project Portfolio Page

## Overview

SysLib is a CLI Library Management software for system librarians.

From viewing, adding, searching, editing, deleting and saving, SysLib provides all the features needed to manage library resources and events.
### Summary of Contributions

#### Code contributed

View the code I contributed via the tp Code Dashboard link [here](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?search=DavinciDelta&breakdown=true).

#### Features implemented
1. Command and Delete Command for resources
2. Parser and suggestParser Class
3. EventAdd, EventList, EventDelete Command for events

**Details:**

**1. Command and Delete Command for resources**

Main classes and methods implemented:
- `Command` class
- `DeleteCommand` class
- `execute()` in `Command` class
- `parseArugment()`, `getMatch` and `checkMatch` in `Command` class
- `validate()` and `checkDuplicate` in `Command` class
- `getReason()` in `Command` class
- `parseInt()` in `Command` class

Implemented a class `Command` which is an abstract class to `execute()` each command

User keys in a command followed by its arguments using "/". 
For each command they have their own required arguments, 
so this list, `args`, differs for each command, 
but the implementation is the same.

Thus `parseArgument()` function will be called to first to get these arguments,
which loops through the list of `args` that the command requires,
then `getMatch()` is called to check if the arguments exist in the user input,
which calls `checkMatch()` to ensure no issues with "/" in arguments.

After `validate()` is called to verify the user input against the arguments received,
this then calls `checkDuplicate()` to check for duplicate calls of the same argument.

At the end, `validate()` checks for additional random variables/commands. 
If there is, `getReason()` will be called to verify the reason for error to give more
informative feedback. 
If the additional command/variable resemble an argument, a suggested argument will be given.

`parseInt()` is by some commands to check for an input of a valid integer.

`DeleteCommand` class is an implementation of `Command` that get takes in the id of the resource
to be deleted, the id is parsed through the `parseInt()` function

**2. Parser and SuggestParser Class**

`Parser` class includes:

- `commandProcessor` hashmap
- `eventList` and `resourceList` ArrayList
- `processUserResponse` function
- `removeFirstWord` function
- `suggest` in `SuggestParser` class

`commandProcessor` stores the String variable of the command as a key and the Command variable as the value.
Thus by using `removeFirstWord` and taking this word, we can compare it to the keys in `commandProcessor` to get the right command to execute.

`eventList` and `resourceList` are the ArrayList to store the `event` and `resource` that is currently in the database.
It can be used by other commands to add/edit/delete informations.

`processUserResponse` makes use of the `commandProcessor` and `removeFirstWord` to call the right command.
If no valid command is found, the `suggest` function in `SuggestParser` will be called to try to find the nearest valid command that the user may be trying to call.

**3. EventAdd, EventList, EventDelete Command for events**

Using the `eventList` in Parser to store events, we can use these functions below to manipulate events:

- `EventAdd`
- `EventList`
- `EventDelete`

`EventAdd` takes in title, date and description(optional) of the event and store this in the eventList.
This event is then stored at the index where it fits chronologically(earlier dates first).

`EventList` list out all the existing events in chronological order.

`EventDelete` deletes the event based on the given index.

#### Contributions to the UG:

- Delete Command
- EventAdd Command
- EventList Command
- EventDelete Command
- FAQ

#### Contributions to the DG:

- User stories
- Parsing components and sequence diagram
- Command components and class diagram
- Events, including EventAdd, EventList and EventDelete
- EventAdd sequence diagram

#### Contributions to team-based tasks

- Reviewed multiple peer pull requests
- Debug multiple PR that has merge problems
- Added the foundation for tp project
- Bug tested for 2.1 jar

#### Review/mentoring contributions:

- PE-D, creating 4 bug issues for [ChessMaster](https://github.com/AY2324S1-CS2113-T18-1/tp) and discuss potential bug fixes
- Review other teams' tp project

