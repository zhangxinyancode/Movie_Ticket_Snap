# Movie_Ticket_Snap
Movie_Ticket
环境配置   

1、Linux(Ubuntu)+windows   
2、首先在Linux下搭建好redis的环境，用作缓存数据库。       
3、windows下利用jedis对Linux下的redis进行操作。 

功能描述:  
1、用户在主页面进行登录,后台自动判断是否为新用户，如果是就自动写入数据库进行注册  
2、登陆后跳转到信息展示页面，也就是电影票抢购的页面  
3、用户可以点击抢购，参与活动。  
4、当用户抢购成功之后，缓存在redis中的数据会实时进行更新,并会给用户的邮箱发送邮件进行通知。  
5、活动页面的显示会3秒定时更新，每3秒刷新一下页面。  

具体实现:  
1、采用redis来实现数据的缓存。考虑到高并发情况下，对数据库频繁的读写造成的负担，使用缓存来实抢购功能。    
2、利用redis来实现消息队列。当用户抢购成功之后，将邮件的内容放入到消息队列中，然后进行统一处理。  
3、邮件的发送用到的是JavaMail的Jar包。使用QQ邮件服务器。  
4、页面使用Ajax实现3秒刷新，从redis中读取数据。  

运行效果如图所示：
![image](https://github.com/zhangxinyancode/Movie_Ticket_Snap/raw/master/image/1.png)
![image](https://github.com/zhangxinyancode/Movie_Ticket_Snap/raw/master/image/2.png)
抢票结果如图:
![image](https://github.com/zhangxinyancode/Movie_Ticket_Snap/raw/master/image/3.jpg)
