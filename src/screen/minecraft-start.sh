#!/bin/bash
#
#/*
# * Copyright (c) 2023 - present | LuciferMorningstarDev <contact@lucifer-morningstar.xyz>
# * Copyright (c) 2023 - present | whackdevelopment.com <contact@whackdevelopment.com>
# * Copyright (c) 2023 - present | whackdevelopment.com team and contributors
# *
# * This program is free software: you can redistribute it and/or modify
# * it under the terms of the GNU General Public License as published by
# * the Free Software Foundation, either version 3 of the License, or
# * (at your option) any later version.
# *
# * This program is distributed in the hope that it will be useful,
# * but WITHOUT ANY WARRANTY; without even the implied warranty of
# * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# * GNU General Public License for more details.
# *
# * You should have received a copy of the GNU General Public License
# * along with this program.  If not, see <https://www.gnu.org/licenses/>.
# */

SCREEN="$(cat .servername || echo "server-fallback-1")"
SERVICE="FakeServer.jar"

INITMEM="1M"
MAXMEM="8G"

JAVA_FLAGS="-XX:+UseG1GC -XX:+ParallelRefProcEnabled -XX:MaxGCPauseMillis=200 -XX:+UnlockExperimentalVMOptions -XX:+DisableExplicitGC -XX:+AlwaysPreTouch -XX:G1NewSizePercent=30 -XX:G1MaxNewSizePercent=40 -XX:G1HeapRegionSize=8M -XX:G1ReservePercent=20 -XX:G1HeapWastePercent=5 -XX:G1MixedGCCountTarget=4 -XX:InitiatingHeapOccupancyPercent=15 -XX:G1MixedGCLiveThresholdPercent=90 -XX:G1RSetUpdatingPauseTimePercent=5 -XX:SurvivorRatio=32 -XX:+PerfDisableSharedMem -XX:MaxTenuringThreshold=1 -Dusing.aikars.flags=https://mcflags.emc.gs -Daikars.new.flags=true"
JAVA_ARGS="-Xmx$MAXMEM -Xms$INITMEM $JAVA_FLAGS"

if [ "$#" -eq 1 ]; then
    if [ "$1" == "inscreen" ]; then
        while true; do
            java -server $JAVA_ARGS -jar $SERVICE nogui
            echo "To abort the restart press Ctrl+C!"
            echo "Restart in:"
            for i in 10 9 8 7 6 5 4 3 2 1; do
                echo "$i seconds..."
                sleep 1
            done
            echo "-- Restarting Process --"
        done
    fi
else
    screen -S $SCREEN bash $0 inscreen
fi
