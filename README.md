# Android_SQLite_Demo

## 注意事项

### 这只是一个简单的demo

### 需要对照博客相应内容去看

### 按钮点击顺序为，创建数据库=>插入数据=>查询数据=>删除数据=>修改数据=>查询数据=>删除数据库

### 谨慎点击更新数据库，容易引发系统奔溃，原因是onUpgrade默认将数据库版本提高，但是其他操作均在低版本，就会拒绝操作

### 如果点击了onUpgrade,解决办法为，删除数据库，然后重新进入程序，数据库位置在Device File Explorer/data/data/com.example.*你的用户名*/database
