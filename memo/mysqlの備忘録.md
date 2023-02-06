# mysqlをインストールする
```bash
mysql --version

brew install mysql
```

# mysqlをインストールしたら
1. mysqlを起動する 
mysqlを起動するだけなら
```bash
sudo mysql.server start

sudo mysql.server restart
```
こんなかんじのエラーメッセージがでることがある
```bash
Error: Can't connect to local MySQL server through socket '/tmp/mysql.sock' (2)

ERROR! The server quit without updating PID file (/usr/local/var/mysql/****.pid).
```
そしたら、これ
```bash
# 何やらソケットファイルなるものが必要らしい
sudo touch /tmp/mysql.sock

# PIDファイルの作成
touch /usr/local/var/mysql/*****.local.pid

uname -n
```

# 初期設定を実行
```bash
 mysql_secure_installation
```
```bash
# mysql_secure_installation

Securing the MySQL server deployment.

Connecting to MySQL using a blank password.

VALIDATE PASSWORD COMPONENT can be used to test passwords
and improve security. It checks the strength of password
and allows the users to set only those passwords which are
secure enough. Would you like to setup VALIDATE PASSWORD component?
                                            #  VALIDATE PASSWORD プラグイン の利用確認
Press y|Y for Yes, any other key for No: y　←　「y」を押下

There are three levels of password validation policy:

LOW    Length >= 8
MEDIUM Length >= 8, numeric, mixed case, and special characters
STRONG Length >= 8, numeric, mixed case, special characters and dictionary file

Please enter 0 = LOW, 1 = MEDIUM and 2 = STRONG: 0　←　「0」を入力　セキュリティレベルを設定
　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　今回は「LOW」を選択
Please set the password for root here.

New password:　←　rootの「新パスワード」を入力

Re-enter new password:　←　再度「パスワード」を入力

Estimated strength of the password: 50
Do you wish to continue with the password provided?
(Press y|Y for Yes, any other key for No) : y　←　「y」を押下
By default, a MySQL installation has an anonymous user,
allowing anyone to log into MySQL without having to have
a user account created for them. This is intended only for
testing, and to make the installation go a bit smoother.
You should remove them before moving into a production
environment.
                                                                   # 不要なanonymousユーザーを削除
Remove anonymous users? (Press y|Y for Yes, any other key for No) : y　←　「y」を押下
Success.


Normally, root should only be allowed to connect from
'localhost'. This ensures that someone cannot guess at
the root password from the network.
 　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　# rootのリモート接続を不許可に設定
Disallow root login remotely? (Press y|Y for Yes, any other key for No) : y　←　「y」を押下
Success.

By default, MySQL comes with a database named 'test' that
anyone can access. This is also intended only for testing,
and should be removed before moving into a production
environment.

　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　# 不要なtestデータベースを削除
Remove test database and access to it? (Press y|Y for Yes, any other key for No) : y　←　「y」を押下
 - Dropping test database...
Success.

 - Removing privileges on test database...
Success.

Reloading the privilege tables will ensure that all changes
made so far will take effect immediately.

Reload privilege tables now? (Press y|Y for Yes, any other key for No) : y　←　「y」を押下
Success.

```

# データベースにログイン
```bash
ユーザログイン
mysql -u root -p password! -P 3306

mysql -u ユーザー名 -pパスワード データベース名
または
mysql --user=ユーザー名 --password=パスワード データベース名
mysql --user=root --password=password! -P 3306

データベース切り替え
USE データベース名
```

### 閲覧系
```sql
# ユーザ一覧
SELECT Host, User FROM mysql.user;

show databaes;
show tables;
show columns;
```

#### 権限確認
```sql
show grants for 'ユーザ名'@'アドレス';
```

### 権限付与
```sql
grant [allとか] on [*.*] to 'hoge_user'@'localhost';

参考
https://www.dbonline.jp/mysql/user/index5.html
```

### データベースの作成
```sql
create database *** default character set utf8;
```

### ユーザの作成
```sql
create database *** default character set utf8;
  
  grant all on app.* to admin@localhost;
grant all on auth.* to admin@localhost;
```
