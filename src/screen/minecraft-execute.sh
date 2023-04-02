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

echo "Applying command to '$SCREEN' '$1'"
screen -x $SCREEN -X stuff "$1^M"
echo "Sent command to server!"
