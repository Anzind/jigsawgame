package com.aoke.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    int[][] data = new int[4][4];
    //记录空白方块的位置
    int x = 0;
    int y = 0;

    String path = "..\\jigsawgame\\src\\image\\girl\\girl8\\";

    int[][] win= {
        {1,2,3,4},
        {5,6,7,8},
        {9,10,11,12},
        {13,14,15,0}
    };

    int step = 0;

    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reloginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");

    JMenuItem girls = new JMenuItem("美女");
    JMenuItem animals = new JMenuItem("动物");
    JMenuItem sports = new JMenuItem("运动");

    JMenuItem accountItem = new JMenuItem("公众号");

    public void initJFrame(){
        //初始化框架
        this.setSize(603,680);
        this.setLocationRelativeTo(null);
        this.setTitle("拼图单机版 v0.01");
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(3);
        //取消默认居中放置
        this.setLayout(null);
        this.addKeyListener(this);
    }

    public void initJMenu(){
        //初始化菜单
        JMenuBar jMenuBar = new JMenuBar();
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");

        JMenu changeImg = new JMenu("更换图片");

        functionJMenu.add(changeImg);
        functionJMenu.add(replayItem);
        functionJMenu.add(reloginItem);
        functionJMenu.add(closeItem);

        changeImg.add(girls);
        changeImg.add(animals);
        changeImg.add(sports);

        aboutJMenu.add(accountItem);

        replayItem.addActionListener(this);
        reloginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        this.setJMenuBar(jMenuBar);
    }

    public void initData(){

        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }

        for (int i = 0; i < tempArr.length; i++) {
            if(tempArr[i] == 0){
                x = i/4;
                y = i%4;
            }
            data[i/4][i%4] = tempArr[i];
        }
    }

    private void initImage() {

        this.getContentPane().removeAll();

        if(victory()){
            JLabel winJLable = new JLabel(new ImageIcon("..\\jigsawgame\\src\\image\\win.png"));
            winJLable.setBounds(203,283,197,73);
            this.getContentPane().add(winJLable);
        }

        JLabel stepCount = new JLabel("步数：" + step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);

        for(int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++) {
                int num = data[j][i];
                JLabel jLabel = new JLabel(new ImageIcon(path + num + ".jpg"));
                jLabel.setBounds(i * 105 + 83,j * 105 + 134,105,105);
                jLabel.setBorder(new BevelBorder(1));
                this.getContentPane().add(jLabel);
            }
        }

        JLabel background = new JLabel(new ImageIcon("..\\jigsawgame\\src\\image\\background.png"));
        background.setBounds(40, 40,508, 560);
        this.getContentPane().add(background);

        this.getContentPane().repaint();
    }

    public GameJFrame(){
        initJFrame();
        initJMenu();
        initData();
        initImage();

        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(victory()){
            return;
        }

        int code = keyEvent.getKeyCode();
        if(code ==65){
            System.out.println("查看原图");
            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);

            JLabel background = new JLabel(new ImageIcon("..\\jigsawgame\\src\\image\\background.png"));
            background.setBounds(40, 40,508, 560);
            this.getContentPane().add(background);

            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if(victory()){
            return;
        }

        int code = keyEvent.getKeyCode();
        if(code == 37){
            System.out.println("向左移动");

            if (y < 3) {
                data[x][y] = data[x][y + 1];
                data[x][y + 1] = 0;
                y++;
                step++;
            }
            initImage();
        } else if (code == 38) {
            System.out.println("向上移动");

            if (x < 3) {
                data[x][y] = data[x + 1][y];
                data[x + 1][y] = 0;
                x++;
                step++;
            }
            initImage();
        } else if (code == 39) {
            System.out.println("向右移动");

            if (y > 0) {
                data[x][y] = data[x][y - 1];
                data[x][y - 1] = 0;
                y--;
                step++;
            }
            initImage();
        } else if (code == 40) {
            System.out.println("向下移动");

            if (x > 0) {
                data[x][y] = data[x - 1][y];
                data[x - 1][y] = 0;
                x--;
                step++;
            }
            initImage();
        } else if (code == 65) {
            System.out.println("回归");
            initImage();
        } else if (code == 87) {
            data = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            x = 3;
            y = 3;
            initImage();
        }
    }

    public boolean victory(){
        for(int i = 0; i < data.length; i++){
            for (int j = 0; j < data[i].length; j++) {
                if(data[i][j] != win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        Object obj = actionEvent.getSource();

        if(obj ==replayItem){
            System.out.println("重进游戏");

            step = 0;
            initData();
            initImage();
        } else if (obj == reloginItem) {
            System.out.println("重新登陆");

            this.setVisible(false);
            new LoginJFrame();
        } else if (obj == closeItem) {
            System.out.println("关闭游戏");
            System.exit(0);
        } else if (obj == accountItem) {
            System.out.println("公众号");

            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("..\\jigsawgame\\src\\image\\Sonia258.jpg"));
            jLabel.setBounds(0,0,258,258);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(344,344);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);

            jDialog.setModal(true);
            jDialog.setVisible(true);
        }
    }
}
