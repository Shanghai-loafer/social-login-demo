const path = require('path');
const fs = require('fs-extra');

const BUILD_DIR = path.join(__dirname, './build');
const PUBLIC_DIR = path.join(__dirname, '../src/main/resources/templates/public');

const DELETE_STATIC_DIR = path.join(__dirname, '../src/main/resources/templates/public/static');

const STATIC_DIR_SRC = path.join(__dirname, './build/static');
const STATIC_DIR_DEST = path.join(__dirname, '../src/main/resources/static');

fs.emptyDirSync(PUBLIC_DIR);
fs.copySync(BUILD_DIR, PUBLIC_DIR);
fs.remove(DELETE_STATIC_DIR);

fs.emptyDirSync(STATIC_DIR_DEST);
fs.copySync(STATIC_DIR_SRC, STATIC_DIR_DEST);

/**
 * emptyDirSync()関数は、emptyDir()関数の同期版です。
 * この関数は、与えられたディレクトリが空であることを確認します。
 * ディレクトリが空でなければ、そのディレクトリに存在するすべてのコンテンツを削除します。
 * ディレクトリ自体は削除されません。 ディレクトリが存在しない場合は、この関数自身によって作成されます。
 */
