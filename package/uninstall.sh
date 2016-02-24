#!/bin/bash
sudo update-rc.d -f jarvis remove
sudo rm /etc/init.d/jarvis
sudo rm /usr/local/bin/{jarvisAlarmStop.sh,jarvisAlarmStart.sh}
