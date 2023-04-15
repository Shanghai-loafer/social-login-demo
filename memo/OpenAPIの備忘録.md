## 概要
基本的な流れとしてはSwagger Editorからファイルを作成して
generatorで生成するって感じか。
んでもプロジェクトに根ざした形で利用しないと差分とかがとりにくいな。

## 代表的なツール
| メリット                   | 代表的なツール               |
|------------------------|-----------------------|
| API仕様を決まったフォーマットで作成できる | OpenAPI Specification |
| APIドキュメントになる           | Swagger UI            |
| API仕様からコード生成ができる       | OpenAPI generator     |

## SwaggerとopenAPIは何が違うのか
openAPIの前身がSwaggerらしい。
だから基本的にはopenAPIを利用すればいいのだろう。

## SwaggerEditor
https://editor-next.swagger.io/

## 構成要素
* Opeapi
  * openapi自体の書式バージョン
* info
  * API に関する一般的な情報を記載
* externalDocs
  * 外部ドキュメントを記述する(API仕様書等)。
* servers
  * サーバ情報
* tags
  * pathを分類するためのもの
  * どうやらURLが途中まで一緒でも同じものにカテゴライズされる訳ではないってことか
* paths
  * 各種 API のエンドポイントを記載
* components
  * paths 等から使えるコンポーネントを記載

## メモ
生成されるのはアプリケーションのベースごとなので、skipオプションを利用して不要なものまで作成されないように制御しないといけないわけか。

## 参考
* [OpenAPI Specificationの紹介](https://www.alpha.co.jp/blog/202208_02)
* [OpenAPI Generatorのコード生成とSpring Frameworkのカスタムデータバインディングを共存させる
  ](https://techblog.zozo.com/entry/coexistence-of-openapi-and-spring)
* [OpenAPI generatorを試してみる](https://qiita.com/amuyikam/items/e8a45daae59c68be0fc8)
* [作って理解する OpenAPI 3.0 / connexion](https://qiita.com/halhorn/items/e47673eecc4a01ffb3a0)
* [OpenAPI (Swagger) 超入門](https://qiita.com/teinen_qiita/items/e440ca7b1b52ec918f1b)
* [YAML 入門: サンプルから学べる初心者向けガイド](https://circleci.com/ja/blog/what-is-yaml-a-beginner-s-guide/)
* [平静を保ち、コードを生成せよ 〜 OpenAPI Generator誕生の背景と軌跡 〜](https://speakerdeck.com/akihito_nakano/gunmaweb34)
