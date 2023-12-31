#!/bin/bash

cd /home/ubuntu/PAW/proiect-paw-tadam


# Check if there is a newer version of the code on GitHub
git fetch
if [[ $(git rev-parse HEAD) != $(git rev-parse @{u}) ]]; then
  # Stop the two systemd services
  sudo systemctl stop proiectPAWBackend.service
  sudo systemctl stop proiectPAWFrontend.service

  # Perform pull and get the latest code
  git pull

  # Execute permission over script
  chmod +x script.sh
  chmod +x script_front.sh

  # Start the two systemd services again
  sudo systemctl start proiectPAWBackend.service
  sudo systemctl start proiectPAWFrontend.service
fi
