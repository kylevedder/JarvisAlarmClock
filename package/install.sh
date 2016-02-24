#!/bin/bash
sudo cp {jarvisAlarmStop.sh,jarvisAlarmStart.sh} /usr/local/bin/
sudo cp jarvis /etc/init.d/
sudo update-rc.d jarvis defaults
