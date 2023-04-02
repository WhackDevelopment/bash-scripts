/**
 * Copyright (c) 2023 - present | LuciferMorningstarDev <contact@lucifer-morningstar.xyz>
 * Copyright (c) 2023 - present | whackdevelopment.com <contact@whackdevelopment.com>
 * Copyright (c) 2023 - present | whackdevelopment.com team and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */


package com.whackdevelopment.ffs;

import java.util.List;
import java.util.Scanner;

public class Console extends Thread {

	private FastFakeServer server;
	private boolean shouldClose = false;

	private Scanner scanner;

	Console(FastFakeServer server) {
		super("FastFakeServer-Console");
		this.server = server;
		this.scanner = new Scanner(System.in);
	}

	@Override
	public void run() {
		while (!shouldClose) {
			try {
				// prompt for the user's name
				System.out.print(">");
				System.out.flush();
				String commandData = scanner.nextLine();

				List<String> commandArgs = List.of(commandData.split("\\s+"));
				if (commandArgs.size() > 0) {
					switch (commandArgs.get(0).toLowerCase()) {
					case "kill":
					case "stop": {
						server.stop();
						break;
					}
					case "error": {
						String str = null;
						String lowerStr = str.toLowerCase();
						break;
					}
					default:
						break;
					}
				}

				System.out.println(
						"[COMMAND] » Command '" + commandArgs.get(0) + "' with Args '" + commandArgs + "' executed");

				System.out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public FastFakeServer getServer() {
		return server;
	}

	public void setShouldClose(boolean shouldClose) {
		this.shouldClose = shouldClose;
		if (shouldClose) {
			this.interrupt();
		}
	}

}
