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

public class Idler extends Thread {

	private FastFakeServer server;
	private boolean shouldClose = false;

	Idler(FastFakeServer server) {
		super("FastFakeServer-Idler");
		this.server = server;
	}

	@Override
	public void run() {
		while (!shouldClose) {
			try {
				Thread.currentThread();
				Thread.sleep(200);
			} catch (Exception e) {
			}
		}
	}

	public FastFakeServer getServer() {
		return server;
	}
	
	public void setShouldClose(boolean shouldClose) {
		this.shouldClose = shouldClose;
		if(shouldClose) {
			this.interrupt();
		}
	}

}