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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FastFakeServer {
	private static FastFakeServer instance;

	private final ExecutorService scheduler = Executors.newScheduledThreadPool(2000);

	private final Console console;
	private final Idler idler;

	public FastFakeServer() {
		instance = this;
		this.idler = new Idler(this);
		this.console = new Console(this);

		this.idler.start();
		this.console.start();
	}

	public static void main(String[] args) {
		new FastFakeServer();

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println("[SHUTDOWN] » Shutting down the fake Server");
			try {
				Thread.currentThread();
				Thread.sleep(50);
			} catch (Exception e) {
			}
			System.out.println("[SHUTDOWN] » Saving Data...");
			for (int i = 0; i < 5; i++) {
				System.out.println("[DEBUG] » Saving Data: 'data-" + i + "'.");
				try {
					Thread.currentThread();
					Thread.sleep(100);
				} catch (Exception e) {
				}
				for (int n = 0; n < 5; n++) {
					System.out.println("[DEBUG] »   - Saving OtherData: 'data-" + i + "- " + n + "'.");
					try {
						Thread.currentThread();
						Thread.sleep(100);
					} catch (Exception e) {
					}
				}
			}
			System.out.println("[SHUTDOWN] » Saved all Data...");
			
			try {
				Thread.currentThread();
				Thread.sleep(150);
			} catch (Exception e) {
			}
			
			System.out.println("[SHUTDOWN] » Shutdown Complete... Habe a Great Day(?)!");
		}, "FastFakeServer-Shutdowm"));
	}

	public ExecutorService getScheduler() {
		return scheduler;
	}

	public static FastFakeServer getInstance() {
		return instance;
	}

	public Console getConsole() {
		return console;
	}

	public Idler getIdler() {
		return idler;
	}

	public void stop() {
		this.idler.setShouldClose(true);
		this.console.setShouldClose(true);
	}

}
