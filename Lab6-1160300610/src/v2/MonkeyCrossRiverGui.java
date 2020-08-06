package v2;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MonkeyCrossRiverGui extends JFrame {

  static final int WIDTH = 800;// 界面的宽度
  static final int HEIGHT = 600;// 界面的高度
  static private Dialog d;
  static private Label lab;
  static private Button okBut;
  static  public Dialog d1;
  static public  Label lab1;
  static private Button okBut1;

  public MonkeyCrossRiverGui() {

    d = new Dialog(this, "你准备好了吗！", true);// 弹出的对话框
    d.setBounds(400, 200, 350, 150);// 设置弹出对话框的位置和大小
    d.setLayout(new FlowLayout());// 设置弹出对话框的布局为流式布局
    lab = new Label();// 创建lab标签填写提示内容
    okBut = new Button("ok");// 创建确定按钮
    d.add(lab);// 将标签添加到弹出的对话框内
    d.add(okBut);// 将确定按钮添加到弹出的对话框内。
    d1 = new Dialog(this, "猴子过河完毕！", true);// 弹出的对话框
    d1.setBounds(400, 200, 350, 150);// 设置弹出对话框的位置和大小
    d1.setLayout(new FlowLayout());// 设置弹出对话框的布局为流式布局
    lab1 = new Label();// 创建lab标签填写提示内容
    okBut1 = new Button("ok");// 创建确定按钮
    d1.add(lab1);// 将标签添加到弹出的对话框内
    d1.add(okBut1);// 将确定按钮添加到弹出的对话框内。
    myEvent();// 加载事件处理
    this.Show();
    this.setSize(WIDTH, HEIGHT);
    this.setTitle("猴子过河");
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  static public void myEvent() {
    // 确定按钮监听器
    okBut.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        d.setVisible(false);
      }
    });
    // 对话框监听器
    d.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        d.setVisible(false);// 设置对话框不可见
      }
    });
    // 确定按钮监听器
    okBut1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        d1.setVisible(false);
        System.exit(0);
      }
    });
    // 对话框监听器
    d1.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        d1.setVisible(false);// 设置对话框不可见
      }
    });
  }

  /**
   * 
   */
  private void Show() {
    GuiPanel panel = new GuiPanel();// 得到面板对象

//    String info = "Are you ready?go?";
//    lab.setText(info);// 显示文本错误提示信息
//    d.setVisible(true);// 设置对话框可见。

    Thread t = new Thread(panel);// 启动面板的动画线程
    t.start();
    this.add(panel);// 将面板加载到Frame主窗口里
  }
}


class GuiPanel extends JPanel implements Runnable {

  MLGraph graph = MonkeyGenerator.graph; // 所要图形化展示的图

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    this.setBackground(Color.yellow);
    g.setColor(Color.BLACK); // 设置背景颜色

    // 展示图的参数信息
    g.drawString("参数情况：", 40, 40);
    g.drawString("n: " + String.valueOf(MonkeyGenerator.n), 100, 40);
    g.drawString("h: " + String.valueOf(MonkeyGenerator.h), 150, 40);
    g.drawString("t: " + String.valueOf(MonkeyGenerator.t), 200, 40);
    g.drawString("N: " + String.valueOf(MonkeyGenerator.N), 250, 40);
    g.drawString("k: " + String.valueOf(MonkeyGenerator.k), 300, 40);
    g.drawString("MV: " + String.valueOf(MonkeyGenerator.MV), 350, 40);
    g.drawString("时间: " + String.valueOf(MonkeyGenerator.time), 435, 40);
    g.drawString("吞吐率: ", 525, 40);
    if (Thread.activeCount() == 3) { // 当猴子进程都结束的时候，计算吞吐率
      MonkeyGenerator.ttrate=(double) MonkeyGenerator.N / (double) (MonkeyGenerator.time);
      g.drawString(String.valueOf((double) MonkeyGenerator.N / (double) (MonkeyGenerator.time)),
          568, 40);
    }
    g.drawString("公平性: ", 525, 63);
    double sum1 = 0;
    double sum2 = (MonkeyGenerator.N * (MonkeyGenerator.N - 1)) / 2.0;
    for (Monkey m1 : graph.monkeys()) {
      for (Monkey m2 : graph.monkeys()) {
        if (!m1.equals(m2)) {
          if ((m1.bornTime - m2.bornTime) * (m1.reachTime - m2.reachTime) >= 0) {
            sum1 += 1;
          } else {
            sum1 -= 1;
          }
        }
      }
    }
    if (Thread.activeCount() == 3) { // 当猴子进程都结束的时候，输出计算的公平性
      MonkeyGenerator.gprate=(sum1 / sum2) / 2.0;
      g.drawString(String.valueOf((sum1 / sum2) / 2.0), 568, 63);
    }
    if (Thread.activeCount() == 3) { // 当猴子进程都结束的时候，输出计算的吞吐率
      String info1 = "thank you for zhujiao!";
      MonkeyCrossRiverGui.lab1.setText(info1);// 显示文本错误提示信息
      MonkeyCrossRiverGui.d1.setVisible(true);// 设置对话框可见。
    }

    // 画梯子
    int yPerLadder = 100;
    int xPerPedal = 30;
    for (int i = 1; i <= MonkeyGenerator.n; i++) {
      g.drawLine(80, (yPerLadder * i - 25), 80 + 30 * MonkeyGenerator.h, (yPerLadder * i - 25));
      g.drawLine(80, (yPerLadder * i + 25), 80 + 30 * MonkeyGenerator.h, (yPerLadder * i + 25));

      for (int k = 0; k <= MonkeyGenerator.h; k++) {
        g.drawLine(80 + xPerPedal * k, (yPerLadder * i - 25), 80 + xPerPedal * k,
            (yPerLadder * i + 25));
      }
    }
    // 模拟猴子的移动
    for (Ladder l : graph.ladders()) {
      for (int i = 0; i < l.pedal.length; i++) {
        if (l.pedal[i].getID() != Integer.MAX_VALUE) {
          if ((l.pedal[i].getDirection().equals("L->R"))) {
            // 模拟向右行进的猴子
            Image picture = new ImageIcon("src/v2/猴子右.jpg").getImage();
            g.drawImage(picture, 80 + i * xPerPedal, yPerLadder * (l.getTitle() + 1) - 25, 30, 60,
                this);
            g.setColor(Color.RED);
            g.drawString(String.valueOf(l.pedal[i].getID()), 85 + i * xPerPedal,
                yPerLadder * (l.getTitle() + 1) - 30);
          } else {
            // 模拟向左行进的猴子
            Image picture = new ImageIcon("src/v2/猴子左.jpg").getImage();
            g.drawImage(picture, 80 + 30 * MonkeyGenerator.h - xPerPedal * i - 30,
                yPerLadder * (l.getTitle() + 1) - 25, 30, 60, this);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(l.pedal[i].getID()),
                80 + 30 * MonkeyGenerator.h - xPerPedal * i - 20,
                yPerLadder * (l.getTitle() + 1) - 30);
          }
        }
      }
    }
    g.setColor(Color.MAGENTA);
    g.drawString("到达左岸:↓", 10, 80);
    g.drawString("到达右岸:↓", 130 + 30 * MonkeyGenerator.h - 25, 80);
    int x = 0;
    int y = 0;
    for (Monkey m : graph.monkeys()) {
      if (m.State.equals("Reach")) {
        if (m.getDirection().equals("R->L")) { // 向左行进到达对岸的猴子
          g.setColor(Color.BLACK);
          g.drawString(String.valueOf(m.getID()), 40, y + 95);
          y += 40;
        } else {
          g.setColor(Color.RED);
          g.drawString(String.valueOf(m.getID()), 130 + 30 * MonkeyGenerator.h - 25, x + 95);
          x += 40;
        }
      }
    }


  }

  @Override
  public void run() {
    while (true) {// 月亮的位置一直向下移动 s
      try {
        Thread.sleep(500);// 暂停三秒
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      if (Thread.activeCount() == 2) {
        Thread.yield();
      }
      repaint();// 三秒后重新开始
    }

  }

}
