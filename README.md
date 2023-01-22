# 概要
## 特徴
* ソーシャルログインをSpring側で実装
* ReactをTypeScriptで実装し、ビルド後にSpring側でテンプレートエンジンで機能
  * ただし、テンプレートエンジンを利用するのはログインページのみ

# 詳細
## 環境構築手順
### 1. Springを立てる
イニシャライザーとかを使用してなんやかんや良い感じのものを作る。

### 2. Reactを立てる
```bash
$ npx create-react-app --template typescript ui
```

### 3. package.jsonにプロキシーを設定
```json
  "scripts": {
    "start": "react-scripts start",
    "build": "react-scripts build",
    "test": "react-scripts test",
    "eject": "react-scripts eject"
    },
"proxy": "http://localhost:8080",
"eslintConfig": {
"extends": [
    "react-app",
    "react-app/jest"
    ]
},
```

### 4. build.gradle.ktsにui側の情報を設定
Create React App には出力先をカスタマイズするオプションはありません。
そこで、build コマンド実行後にビルド結果を Spring Boot から配信できる位置に移動させるスクリプトを書いてしまいます。
src/main/resources/public はビルド結果なので .gitignore に含めていいでしょう。

詳しくは、そっちのファイル参照のこと

# 参考文献
* https://www.hypertextcandy.com/create-react-app-on-spring-boot
