# jigsawgame
1. 该项目是一个拼图的小游戏，跟随黑马程序员的指导和引领，由我自己研究各种功能和需求的可行性、敲代码、排除bug，并且在最后总结仍需解决的各种问题和版本更新的方案；
2. 项目要点：
（1）游戏主要界面分为三个窗口，登录界面、注册界面以及游戏界面，我使用三个类GameJFrame、LoginJFrame、RegisterJFrame来实现三个界面，都继承了JFrame类，GameJFrame另外实现了KeyListener和ActionListener接口用于实现键盘和鼠标的操作；
（2）GameJFrame的主要内容，首先完成最外层窗体的搭建，再把菜单添加到窗体当中，把小图片添加到窗体当中，其次打乱数字图片的顺序，并让数字图片可以通过键盘控制移动起来，最后进行通关之后的胜利判断；
（3）主要克服的点是几种动作监听的实现和图片的打乱操作，用到了接口KeyListener和ActionListener的方法，所以直接让窗口类实现这两个接口可以大幅缩减代码；
3. 项目待改进的地方：登录界面已经实现各组件的部署，但是没有配套的监听和操作，注册界面仍待实现，后续或许可以通过JDBC接口，存储用户的账号信息和存档。菜单栏的更换图片仍待实现；
4. 按“a”可以查看完整图片，按“w”可以一键胜利！
