#!/usr/bin/bash
echo Pausing for 10 seconds to shut down the Artifactory Repo...
python ~/sandbox/scripts/tomdown.py
sleep 10s

echo Running CloudBees container locally.
bees app:run ~/sandbox/dsi/workbench/eclipse/workspace/dummylandapp/gen/dist/dummylandapp.war