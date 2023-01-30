# 防御的プログラミングと契約的プログラミング

以下の２つから理解した防御的プログラミングと契約的な設計をコードにしてみる

* https://wapa5pow.com/posts/2023-01-23--which-layer-validates
* https://twitter.com/yoshiki_shibata/status/955000080213319680

雰囲気で書いているのでコンパイルは通りますが動かないです。

## コメントを入れている部分

* コンストラクタでバリエーションするか
* SQLに絞り込み条件を入れるか
* usecase の処理をどこまで信じるか（契約的な設計だから信じられるという責任分解点）

### 契約的に設計されたイメージ

* @PutMapping("/customer")

### 契約的に設計されてないイメージ

* @PutMapping("/mutable-customer")

# h2

http://localhost:8080/h2-console/

SQLもカラム定義の認識合わせ程度で動作させていない適当なものです。