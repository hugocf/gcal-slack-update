[![status](https://img.shields.io/badge/status-dev%20(wip)-red.svg)](#)
[![version](https://img.shields.io/badge/version-%C2%AF%5C__(%E3%83%84)__%2F%C2%AF-red.svg)](../../releases)

# Gcal ➔ Slack

`gcal-slack-update` automatically changes your status in multiple Slack teams, depending on the _current_ event in your Google Calendar.

It is designed to run periodically from a `cron` job or triggered by a Scheduled Event in AWS Lambda.

During each execution, it checks whether the current event *Title* in Google Calendar contains the `match-text` defined in the set of configuration rules received as input.

Example of an input `rules.conf` file:

```
rules = [
  {
    match-text = "testing this"
    status-emoji = ":smile:"
    status-text = "It works"
  }, 
  {
    match-text = "another test"
    status-emoji = ":+1:"
    status-text = "Count me in"
  },
]
```



| Configurations | Description                              | Maps to…                     |
| -------------- | ---------------------------------------- | ---------------------------- |
| `match-text`   | Case insensitive string which can appear anywhere in the searched field | **Google Calendar**: Event Title |
| `status-emoji` | The symbol to display as the status      | **Slack**: Status Emoji          |
| `status-text`  | The status’ description (can also contain emojis) | **Slack**: Status Text           |



## Local Installation

* [ ] Where’s the latest release?
* [ ] Set ENV_VAR for Google Calendar access
* [ ] Set ENV_VAR for Slack access…



## Run in AWS Lambda

* [ ] TBD




## Ideas 💡

- [ ] Change status in multiples Slack teams simultaneously!
- [ ] Support multiple Google Calendars?
- [ ] Search for matches in other event fields? (e.g. description, location)
- [ ] Support regex patterns to match events?


