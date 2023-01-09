#!/bin/bash

# Check if there is a newer version of the code on GitHub
git fetch
if [[ $(git rev-parse HEAD) != $(git rev-parse @{u}) ]]; then
  # Stop the two systemd services
  systemctl stop proiectPAWBackend.service
  #systemctl stop proiectPAWFrontend.service

  # Perform pull and get the latest code
  git pull

  # Start the two systemd services again
  systemctl start proiectPAWBackend.service
  #systemctl start proiectPAWFrontend.service
fi
