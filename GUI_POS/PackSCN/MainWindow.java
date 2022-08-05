package PackSCN;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.plaf.DimensionUIResource;

import java.awt.CardLayout;
import java.awt.Color;

public class MainWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    ScreenMode screenMode = ScreenMode.STEETING;
    final int WIDTH = 1280;
    final int HEIGHT = 720;

    CardLayout layout = new CardLayout();

    MenuPanel menuPanel;
    SetPanel setPanel;
    CashPanel cashPanel;

    //
    MainWindow(){        
        this.setTitle("POS_DEMO");
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("PackSCN/image/sm.png"));
        this.setIconImage(icon.getImage());

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);//背景サイズの変更を無効
        this.getContentPane().setBackground(Color.green);//背景色
        this.setLayout(layout);//
        this.setPreferredSize(new DimensionUIResource(WIDTH, HEIGHT));//サイズ設定
        this.pack();//自動サイズ調整
        this.setLocationRelativeTo(null); //スクリーンの位置中央
    }
    //各パネルを一気にインスタンス化
    public void preparePanels(){
        setPanel = new SetPanel();
        this.add(setPanel,"設定画面");
        cashPanel = new CashPanel();
        this.add(cashPanel,"会計画面");
        menuPanel = new MenuPanel();
        this.add(menuPanel,"メニュー画面");
        this.pack();
    }
    //各パネルごとの設定を一気に読み込む
    public void prepareComponents(){
        setPanel.prepareComponents();
        cashPanel.prepareComponents();
        menuPanel.prepareComponents();
    }
    //スクリーンモードを選んだ場合の分岐それによって切り替えるパネルを選ぶ
    public void ScreenFocus(ScreenMode s){
        screenMode = s;
        switch(screenMode){
            case STEETING:{
                layout.show(this.getContentPane(), "設定画面");
                setPanel.requestFocus();
                break;
            }
            case MENU:{
                layout.show(this.getContentPane(), "メニュー画面");
                menuPanel.requestFocus();
                break;
            }
            case CASH:{
                layout.show(this.getContentPane(), "会計画面");
                cashPanel.requestFocus();
                break;
            }
        }
    }

    
}
