# Osake Market

## 概要
お酒のECサイトを想定したWebアプリケーションです。
会員登録、ログイン、商品検索、カート、注文、お気に入り機能などを実装しています。

## 開発目的
JavaによるWebアプリケーション開発の学習を目的として制作しました。
MVCモデルを意識し、Servlet/JSP、JDBCを使用しています。

## 使用技術
### バックエンド
- Java 21
- Servlet
- JSP
- JDBC

### フロントエンド
- HTML
- CSS
- JavaScript

### データベース
- Oracle Database

### サーバー
- Apache Tomcat 10

### テスト
- Selenium
- JUnit5

### バージョン管理
- Git
- GitHub

## 主な機能
・会員登録
・パスワード再設定
・ログイン・ログアウト
・退会
・商品一覧
・商品詳細
・カテゴリー検索
・キーワード検索
・並び替え
・カート
・注文
・お気に入り
・通知
・問い合わせ
・マイページ
・会員情報管理
・注文キャンセル

## セットアップ

### 1. リポジトリを取得
git clone https://github.com/hasegawa-ryo-uniserve/osake-market.git

### 2. Oracle Database
リポジトリ内の「各種ドキュメント.zip」を展開し、「ポートフォリオ/4. 実装/SQL/osake_market.txt」を実行してデータベースを作成してください。

### 3. JDBCドライバ
ojdbcをTomcatまたはプロジェクトへ追加してください。

### 4. Tomcat
Apache Tomcat 10を設定してください。

### 5. 実行
http://localhost:8080/osake-market/
へアクセスしてください。

## テスト
JUnit5とSeleniumによる画面テストを実装しています。

## 工夫した点
・カテゴリー検索とキーワード検索を組み合わせた複合検索
・ログイン状態をFilterで管理し、未ログインユーザーのアクセスを制御
・注文時は在庫・購入情報をまとめて登録するトランザクション処理を実装
・SeleniumとJUnit5による画面自動テストを作成し、主要機能を検証

## 画面ショット
画面イメージは `screenshots/こちらをみてください` フォルダに格納しています。

## 今後の改善
・Spring Bootへの移行
・REST API化
・AWSへのデプロイ
・Docker対応

## ライセンス
学習目的で作成しています。
