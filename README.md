# Split The Bill
## Project Proposal

My project is called **Split The Bill**. This program will help users by keeping track of shared costs amongst a group 
of individuals by:
- Creating a list of people
- Creating a list of expenses
- Tracking the cost of each expense
- Tracking who paid for an expense
- Tracking who an expense should be split between

The program will help manage shared bills by calculating the total amount paid by each individual, the total share of 
spending for each individual, and outstanding balance for each individual.
This program can be used by friends and families that engage in any type of activities together that involves shared
costs among 2 or more individuals. Examples include hosting a home party, visiting an amusement park, 
seeing a movie, and dining out. This program will be especially useful to manage bills shared when travelling together
with a group of people as there will be various types of bills paid by different individuals with the cost intended to 
be split in different ways, which can make tracking complex.

This project is of interest to me because it solves problems that I encounter in my everyday life.
I often go out with friends where cost sharing is required but in most cases, it's not feasible to rely on the venue to 
split the bill accurately, while some venues don't offer this service at all.
Money is a sensitive topic for many individuals, and I believe that maintaining proper records of shared expenses is 
crucial for preserving long-term relationships with friends and family.
For example, when I eat out with my friends, I always want to make sure that I am paying my fair share of the bill. 
At the same time, I don't want to be seen as the person that drills deep into the 
receipt every time to ensure I wasn't charged for the beer that my friend ordered. This isn't a huge
problem if it was a one-time thing, but I will probably hang out with these friends again and the same thing is likely 
going to happen because I don't drink alcohol whereas many of my friends do. Over time, this can lead to tension and 
unnecessary negative feelings. The *Split The Bill* program intends to prevent these issues by making it easy and 
convenient to manage cost splitting amongst friends and families. 

## User Stories
- As a user, I want to be able to add a new **event** to a **billbook**.
- As a user, I want to be able to add a new **person** to an **event**.
- As a user, I want to be able to add a new **expense** to an **event**.
- As a user, I want to be able to view the *list of all expenses* for an **event**. 
- As a user, I want to be able to view the *list of all people* in an **event**.
- As a user, I want to be able to select an **event** and view the *total cost* of the event and the 
*outstanding balance* for each individual.
- As a user, when I select the quit option from the application menu, I want to be *reminded* to save my bill book to
file and have the *option* to do so or not. 
- As a user, when I start the application, I want to be given the *option* to load my saved bill book from file.

The following user stories are available in the console-based ui only.
- As a user, I want to be able to select an **expense** and update its *amount*, the *person who paid for it*, and the
  list of *people who should share this cost*.

## Citations
The data-persistence design of this project is adapted from the persistence package in the JsonSerializationDemo
project which can be accessed at this link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git


 ## Instructions for Grader
> - You can generate the first required action related to adding Xs to a Y by doing the following:
>> 1. Run the program from ui/gui/MainGUI.java.
>> 2. (optional) Click "Load billbook from file" if you wish to load an example billbook.
>> 3. Click "Open an event" to see what events are currently inside this billbook. 
Close the current window to go back to the main menu.
>> 4. Click "Create a new event" and enter the name of a new event. Click "ADD", and you will be taken back to the main 
screen.
>> 5. Click "Open an event" to see that the event (X) you've just added is now in this billbook (Y).
Close the current window to go back to the main menu.
>> 6. Repeat steps 4 & 5 as many times as you want to confirm that multiple events can be added to the billbook. This 
should satisfy the requirement of being able to add multiple X's to Y.
>> 7. Click "Remove an event". If you loaded the example billbook initially, you see a button for "This is the event!!" 
and a button for "Empty Event". If you created new events in the previous steps, then you should see buttons for these 
events. Click on one of these buttons to remove it from the billbook. 
>> 8. Back in the main screen, Click "Open an event" to see that the event (X) you've just removed is now no longer 
in this billbook (Y). This should satisfy the requirement of implementing the first related action.
> - You can generate the second required action related to adding Xs to a Y by doing the following:
>> 1. Follow steps 1-6 in the section above if you haven't already.
>> 2. Click "Remove an event". At the bottom of this pop-up window, you should see a button for "RESET BILLBOOK". 
Click on this button to remove ALL events from the current billbook.
>> 3. Click "Open an event" to see that the billbook is now empty as you have cleared all events (X) from this billbook 
(Y). This should satisfy the requirement of implementing the second related action.
> - You can locate my visual component by doing the following:
>> 1. Run the program from ui/gui/MainGUI.java.
>> 2. The visual component is the image located at the bottom portion of the main screen.
> - You can save the state of my application by doing the following:
>> 1. Run the program from ui/gui/MainGUI.java.
>> 2. You may click the "Save billbook" button on the main screen to manually save the file.
>> 3. Alternatively, you may click the "quit" button from the main screen. Then there will be a pop-up window asking if 
you would like to save your data. Click "Yes" to save.
> - You can reload the state of my application by doing the following:
>> 1. Run the program from ui/gui/MainGUI.java.
>> 2. You may click the "Load billbook from file" button on the main screen to load the saved file.
