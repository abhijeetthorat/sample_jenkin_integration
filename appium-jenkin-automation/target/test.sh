#!/bin/bash
# My first script
echo "Stoping Existing Chrome Browsers"
sudo pkill -f "google-chrome"
export DISPLAY=:10
echo "Starting Display Server"
Xvfb :10 -screen 0 1366x768x24 -ac &
echo "Running the Automation Test"
java -jar sample.jar
echo "Sending Test Execution Mail"
java -jar auto_email.jar
echo "Test Execution Completed!!!"
echo "Stopping Headless Tests"
sudo pkill -f "Xvfb"
echo "Stopping Chrome Tests"
sudo pkill -f "google-chrome"
