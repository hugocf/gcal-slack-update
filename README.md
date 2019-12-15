[![status](https://img.shields.io/badge/status-dev%20(wip)-red.svg)](#)
[![version](https://img.shields.io/badge/version-%C2%AF%5C__(%E3%83%84)__%2F%C2%AF-red.svg)](../../releases)

# Gcal ➔ Slack

`gcal-slack-update` automatically changes your status in multiple Slack teams, depending on the _current_ event in your Google Calendar.

It is designed to run periodically from a `cron` job and, during each execution, it checks whether the current event *Title* in Google Calendar contains the `match-text` defined in the set of configuration rules received as input.

Example of an input file in `rules-example.conf`:

```ini
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

Authentication is read from a file named `auth.conf` (see example in `auth-example.conf`):

```ini
slack-token = "xoxp-legacy-token"
```

To generate such a token, go to the [legacy tokens](https://api.slack.com/custom-integrations/legacy-tokens#legacy-info) Slack page of the workspace where you wish to update the status.



## Local Installation

* [ ] Get latest release from the repo [releases page](https://github.com/hugocf/gcal-slack-update/releases) and unzip it to some location
* [ ] Make sure the execution script has execute permissions: `chmod a+x bin/gcal-slack-update`
* [ ] Copy file [`auth-example.conf`](https://github.com/hugocf/gcal-slack-update/blob/master/auth-example.conf) into `auth.conf` and update with your [token for Slack](https://api.slack.com/custom-integrations/legacy-tokens#legacy-info)
* [ ] Copy file  to [`rules.conf`](https://github.com/hugocf/gcal-slack-update/blob/master/rules-example.conf) and customise it to your needs, as you prefer
* [ ] Periodically run the command `./bin/gcal-slack-update rules.conf` with the frequency you wish to update status



## Ideas 💡

- [ ] Change status in multiples Slack teams simultaneously!
- [ ] Support multiple Google Calendars?
- [ ] Search for matches in other event fields? (e.g. description, location)
- [ ] Support regex patterns to match events?
- [ ] Configure `sbt-native-packager` to ensure that the universal script files in `./bin` have execute permissions


