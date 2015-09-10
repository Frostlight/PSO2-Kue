# PSO2 Kue
PSO2 Kue will be a event tracker Android app for the Japanese PC MMO Phantasy Star Online. It will time upcoming events (emergency quests) and keep the player notified about when they will occur.

##  Quests
> There are two forms of Emergency Quests, ones pre-scheduled by SEGA themselves, and ones randomly chosen by your server. Due to this, the calendar is unable to predict whenever random Emergency Quests are going to occur. However, to get around this, premium users with PSO2es (the mobile app associated with this MMO) installed  will be given announcements to Emergency Quests 1 hour in advance. By pulling these push notifications, the community is able to check if an Emergency Quest is going to occur 1 hour before they happen.

Source: http://www.bumped.org/psublog/pso2-jp-extended-maintenance-172015/

The goal of this app is to aggregate both types of emergency quests into a list display by:

1. periodically scanning certain Twitter bots for pulled random emergency quest notifications (in Japanese) and
2. updating the scheduled emergency quest timetable using an already-translated schedule when necessary.

The timetable will be kept track of by a backend database.  

A translation table will be used for the Japanese Twitter updates. Alternatively, Hablaa translate if no translation is available, or just use the original Japanese updates.  

### Version
0.0

### Tech
PSO2 Kue will use a number of freely available technologies to work properly:
- [SQLite] - A lightweight backend database for storing the emergency quest schedule and translation table. Emergency quests that have already passed will be wiped from the database
- [Twitter4j] - An unofficial Java library for working with the Twitter API
- [Joda-Time] - A quality replacement for the Java Time and Date classes which fixes much of the unnecessary convolution in the way Java handles time (if Android development moves to Java SE 8, migrate to java.time instead)
- [Microsoft Translator Java API] - A java wrapper used for Bing translator

###APIs
PSO2 will use the following APIs:
- ~~[Twitter Streaming API] - Used to keep an open HTTP connection with Twitter to receive new emergency quest notifications as they are posted~~ [Will be unavailable during initial development]
- [Twitter REST API] - Used when Streaming API isn't available to periodically fetch emergency quest notifications
- [Google Calender] - Used to access and import a translated version of the emergency quest schedule posted at https://www.google.com/calendar/embed?src=pso2emgquest%40gmail.com
- [Google Spreadsheet API] - Used to update a translation table for interpreting the Japanese Twitter updates
- [Bing Translation API] - Used if there is no translation available

###Updates
- 6/15/2015: Basic ListView display implemented and functioning, refresh buttons to gather new data for now
- 5/31/2015: Implemented translation of Twitter text with Bing Translate
- 5/30/2015: Google Calendar integration with database completed, Joda-Time added
- 5/30/2015: Google Calendar fetching/parsing and related tests implemented
- 5/29/2015: Databases and related tests implemented
- 5/26/2015: Twitter fetching and related tests implemented, Twitter4j added

### Todo's
- Main activity
- Preferences activity
- Twitter integration with database
- Translation Table and integration with database

[SQLite]: https://www.sqlite.org/
[Twitter Streaming API]: https://dev.twitter.com/overview/documentation
[Twitter REST API]: https://dev.twitter.com/rest/public
[Google Calender]: https://developers.google.com/google-apps/calendar/
[Google Spreadsheet API]: https://developers.google.com/google-apps/spreadsheets/
[Bing Translation API]: http://www.bing.com/dev/translator
[Microsoft Translator Java API]: https://code.google.com/p/microsoft-translator-java-api/
[Twitter4j]: http://twitter4j.org/en/index.html
[Joda-Time]: http://www.joda.org/joda-time/