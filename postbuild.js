const path = require('path');
const fs = require('fs-extra');

const BUILD_DIR = path.join(__dirname, './build');
const PUBLIC_DIR = path.join(__dirname, '../src/main/resources/templates/build');

fs.emptyDirSync(PUBLIC_DIR);
fs.copySync(BUILD_DIR, PUBLIC_DIR);

/**
 * emptyDirSync()関数は、emptyDir()関数の同期版です。
 * この関数は、与えられたディレクトリが空であることを確認します。
 * ディレクトリが空でなければ、そのディレクトリに存在するすべてのコンテンツを削除します。
 * ディレクトリ自体は削除されません。 ディレクトリが存在しない場合は、この関数自身によって作成されます。
 */
