# 図書館検索
[カーリルAPI](https://calil.jp/doc/api.html)を使用して図書館を検索します。  

## 環境
- Kotlin: `1.2.51` 
- Spring Boot: `2.0.4.RELEASE`

## 使い方
1. 公式サイトからアプリケーションキーを取得します。  
https://calil.jp/api/dashboard/
1. 取得したアプリケーションキーを `application.yml` の `calil.app-key` に設定します。
1. Spring Bootアプリケーションとして起動して、 http://localhost:8080/ にアクセスします。

以上
