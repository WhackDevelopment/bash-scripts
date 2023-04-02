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

const fs = require('node:fs');
const minifyBash = require('bash-minifier');

if (!fs.existsSync('./dist/')) {
    fs.mkdirSync('./dist/');
}

function isStatEqual(object1, object2) {
    return (
        object1.dev === object2.dev &&
        object1.mode === object2.mode &&
        object1.nlink === object2.nlink &&
        object1.uid === object2.uid &&
        object1.gid === object2.gid &&
        object1.rdev === object2.rdev &&
        object1.blksize === object2.blksize &&
        object1.size === object2.size &&
        object1.blocks === object2.blocks &&
        object1.atimeMs === object2.atimeMs &&
        object1.mtimeMs === object2.mtimeMs &&
        object1.ctimeMs === object2.ctimeMs &&
        object1.birthtimeMs === object2.birthtimeMs &&
        object1.atime === object2.atime &&
        object1.mtime === object2.mtime &&
        object1.ctime === object2.ctime &&
        object1.birthtime === object2.birthtime
    );
}

async function recursiveWalkAndCopy(ignore = [], path, dist = './dist') {
    console.log('Walking through' + path);

    let files = fs.readdirSync(`${path}`);

    for (let entity of files) {
        let currentPath = `${path}/${entity}`;
        let currentDest = currentPath.replace(path, dist);
        if (ignore.includes(currentPath) || ignore.includes(currentPath + '/')) continue;

        let statCurrent = fs.statSync(currentPath);

        if (statCurrent.isDirectory()) {
            recursiveWalkAndCopy(ignore, currentPath, currentDest);
        } else {
            try {
                let distArgs = currentDest.split('/');
                let filename = distArgs.pop();
                let dir = distArgs.join('/');

                fs.mkdirSync(dir, { recursive: true });

                // console.log('Processing ', { currentPath, to: { dir, filename } });
                // continue;

                if (currentPath.endsWith('.sh')) {
                    let newContent = '#!/bin/bash\n' + minifyBash(fs.readFileSync(currentPath, 'utf8'));
                    let realDist = currentDest.replace(filename, filename.slice(0, -3));
                    let oldContent = '';
                    try {
                        oldContent = fs.readFileSync(realDist, 'utf8');
                    } catch (err) {}
                    if (newContent === oldContent) {
                        console.log('Skipping copy of', currentPath, 'content match in' + realDist);
                        continue;
                    }
                    fs.writeFileSync(realDist, newContent, { encoding: 'utf8', flag: 'w' });
                    console.log('Copied&Minified ', currentPath, ' to ', currentDest);
                    continue;
                }

                if (fs.existsSync(currentDest)) {
                    console.log('Skipping copy of', currentPath, 'file exists' + currentDest);
                    continue;
                }
                // fs.createReadStream(currentPath).pipe(fs.createWriteStream(currentDist));
                fs.copyFile(currentPath, currentDest, (err) => {
                    if (err) throw err;
                    console.log('Copied', currentPath, 'to', currentDest);
                });
            } catch (error) {
                console.log('ERROR while Copying', currentPath, 'to', currentDest);

                console.error(error);
            }
        }
    }
}

recursiveWalkAndCopy(['./src/testing'], './src');
