## 課題
* ログアウトはapiからしてあげるひつようあるかも
  * ページ遷移が効かないので、Apiを叩いてあげる必要がある
* どうにかして、ファビコンを参照できないかな。
* ログイン情報をUserにどう保存するか。
  * すくなくともソーシャルログインの都合上、どうするか
  * どこ経由のログインなのかと一意になる条件からユーザー情報を取得？
  * まず何をもって、ログインが成功しているかどうか認証してるんだろう。
  * JESSONIDとかいうものらしい
* ユーザの新規登録をつくらないと
* フォームログインをJWTとかにして、一応セキュリティも向上させつつユーザサービスを改変できるような形にしておくと。
* Domaを含めたgradleのいい感じの設定をつくりたい。
* Swaggerなんかを利用してAPIをさくっとドキュメント化したいな。

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
イメージとしては以下の感じ
```json
{
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
  }
}
```

### 4. build.gradle.ktsにui側の情報を設定
Create React App には出力先をカスタマイズするオプションはありません。
そこで、build コマンド実行後にビルド結果を Spring Boot から配信できる位置に移動させるスクリプトを書いてしまいます。
src/main/resources/public はビルド結果なので .gitignore に含めていいでしょう。

詳しくは、そっちのファイル参照のこと

# 参考文献
* https://www.hypertextcandy.com/create-react-app-on-spring-boot
* https://baubaubau.hatenablog.com/entry/2021/01/05/190040
* https://springdoc.org/v2/
* https://ksby.hatenablog.com/entry/2021/03/25/072126
* https://qiita.com/teinen_qiita/items/e440ca7b1b52ec918f1b
* https://zenn.dev/beijaflor/articles/3d554e51bb20c6
* https://springdoc.org/
* https://techblog.zozo.com/entry/coexistence-of-openapi-and-spring

### Domaのインストールガイド
