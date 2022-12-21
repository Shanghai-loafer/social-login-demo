# このリポジトリの方針

参考文献

https://www.hypertextcandy.com/create-react-app-on-spring-boot

## ReactとSpring
フロントエンドをReact、バックエンドをSpringBootで構成する

## 手順
### Springを立てる
イニシャライザーとかを使用してなんやかんや良い感じのものを作る。

### Reactを立てる
```bash
$ npx create-react-app --template typescript frontend
```

### 動かないビルドファイル
Create React App には出力先をカスタマイズするオプションはありません。
そこで、build コマンド実行後にビルド結果を Spring Boot から配信できる位置に移動させるスクリプトを書いてしまいます。
package.json の scripts に、postbuild タスクを追加します。
npm の機能で、postxxx というタスクを定義すると、xxx タスクの完了後に自動的に実行されます。
(なぜjsかというと、nodeにすれば、bashの様にサーバのOSに依存しないから。あとコンパイル不要)
src/main/resources/public はビルド結果なので .gitignore に含めていいでしょう。

```json
  "proxy": "http://localhost:8080",

    "homepage": "./",

      "scripts": {
    "start": "react-scripts start",
    "build": "react-scripts build",
    "postbuild": "node ./postbuild.js",
    "test": "react-scripts test",
    "eject": "react-scripts eject"
    },
```

## 特徴
* ReactをTypeScriptで実装し、クロスコンパイルして、Springの所定のディレクトリにビルド結果を格納
* Gradleのビルドの中にReactのコンパイルも入れたい。
* gitignoreなんかも丁寧に挿入していきたい。
* 認証関連もサクッと入れておきたい。
* ログもAOPを使って、効率的にログを生成していきたい。
