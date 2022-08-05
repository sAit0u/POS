package PackSCN;
import javax.swing.*;
import javax.swing.text.AttributeSet.ColorAttribute;

import java.awt.event.*;
import PackBTN.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.io.*;
import java.awt.Insets;

import java.awt.Color;
public class MenuPanel extends JPanel{
    private static final long serialVersionUID = 1L;

    int totalcash;//合計金額
    int totalqty;//合計個数
    JList list;//購入リスト
    DefaultListModel model = new DefaultListModel();

    int menuqty;//メニューボタンメソッド配置用変数
    int sideqty;
    int drinkqty;

    int smode = 0 ;//ボタン処理用変数
    int mmode = 0 ;
    int lmode = 0 ;
    int setmode = 0;

    List<String> setStrings;
    int btnqty;
    

    

    String errmsg;//問題のある場合のメッセージ
    JPanel centrcosole;
    JButton errcl;

    //注文系
    JButton qty1;
    JButton qty2;
    JButton qty3;
    JButton qty4;
    JButton qty5;
    JButton qty6;
    JButton qty7;
    JButton qty8;
    JButton qty9;
    JButton qty10;
    JButton setbtn;
    JButton delButton;
    JButton jButton1;
    JButton jButton2;
    JButton jButton3;
    JButton jButton4;
    JButton jButton5;
    JButton jButton6;
    JButton jButton7;
    JButton jButton8;
    JButton jButton9;
    JButton jButton10;
    JButton jButton11;
    JButton jButton12;
    JButton jButton13;
    JButton jButton14;
    JButton jButton15;
    JButton jButton16;
    JButton jButton17;
    JButton jButton18;
    JButton jButton19;
    
    JLabel qrylLabel;

    //会計
    JButton cashbtn;

    MenuBtn mp = new MenuBtn();

    //セット，サイズ，まとめ買いボタンの有効を確認する変数
    int qtymenu = 0;
    int qtymode = 0;
    int setsidemode =0;
    int setdrinkmode =0;
    public List<List<String>> delList = new ArrayList<List<String>>();

    MenuPanel(){
        this.setLayout(null);
        this.setBackground(Color.WHITE);
    }
    public void prepareComponents(){
        //想定の範囲内で問題が起きた時表示するパネル
        centrcosole = new JPanel();
        centrcosole.setVisible(false);
        centrcosole.setBounds(500,310,280,100);
        this.add(centrcosole);

        errcl = new JButton();
        errcl.setBounds(125, 70, 100, 30);
        centrcosole.add(errcl);
        errcl.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                centrcosole.setVisible(false);
            }
        });
        //注文リストを表示するパネル
        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(null);
        totalPanel.setBounds(1000,100,280,620);
        totalPanel.setBackground(Color.BLACK);
        this.add(totalPanel);

        //注文リスト，削除できない初期値回避のためタイトルと改行を挿入
        model.addElement("注文履歴");
        model.addElement(" ");
        list = new JList(model);
        list.setBounds(10, 20, 240, 600);
        totalPanel.add(list);


        //-----ここからボタン作成処理----------

        makeBtn(mp, jButton1, mp.menu1);
        makeBtn(mp, jButton2, mp.menu2);
        makeBtn(mp, jButton3, mp.menu3);
        makeBtn(mp, jButton4, mp.menu4);
        makeBtn(mp, jButton5, mp.menu5);
        makeBtn(mp, jButton6, mp.menu6);
        makeBtn(mp, jButton7, mp.menu7);
        makeBtn(mp, jButton8, mp.menu8);
        makeBtn(mp, jButton9, mp.menu9);
        makeBtn(mp, jButton10, mp.menu10);
        makeBtn(mp, jButton11, mp.menu11);
        makeBtn(mp, jButton12, mp.menu12);
        makeBtn(mp, jButton13, mp.menu13);
        makeBtn(mp, jButton14, mp.menu14);   
        trymakebtn(jButton15, mp.menu15);
        trymakebtn(jButton16, mp.menu16);
        trymakebtn(jButton17, mp.menu17);
        trymakebtn(jButton18, mp.menu18);
        trymakebtn(jButton19, mp.menu19);

        mekeqtybtn(mp, qty1, 1);
        mekeqtybtn(mp, qty2, 2);
        mekeqtybtn(mp, qty3, 3);
        mekeqtybtn(mp, qty4, 4);
        mekeqtybtn(mp, qty5, 5);
        mekeqtybtn(mp, qty6, 6);
        mekeqtybtn(mp, qty7, 7);
        mekeqtybtn(mp, qty8, 8);
        mekeqtybtn(mp, qty9, 9);

        //買おうとしてる個数
        qrylLabel = new JLabel();
        qrylLabel.setHorizontalAlignment(JLabel.CENTER);
        qrylLabel.setText("1");
        qrylLabel.setBounds(920,10,80,80);
        qrylLabel.setFont((new Font("メイリオ",Font.BOLD,25)));
        qrylLabel.setBackground(Color.gray);
        qrylLabel.setOpaque(true);
        this.add(qrylLabel);

        //会計画面遷移ボタン1つ以上購入個数があれば遷移先でも使えるよう外部ファイルに情報を書き出し画面遷移する
        cashbtn = new JButton();
        cashbtn.setText("会計");
        cashbtn.setBounds(920,600,80,80);
        cashbtn.setFont(new Font("メイリオ",Font.BOLD,10));
        this.add(cashbtn);
        cashbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                totalcash = mp.total;
                totalqty = mp.totalqty;
                if(totalcash != 0){
                    try{
                        FileWriter totalfile = new FileWriter("text/totalcash.txt");
                        PrintWriter pw = new PrintWriter(new BufferedWriter(totalfile));

    
                        pw.println(mp.total);
                        pw.close();
                        Sc_Main.mainWindow.ScreenFocus(ScreenMode.CASH);
                    } catch (IOException ew) {
                        ew.printStackTrace();
                    }
                    try{
                        FileWriter csvfile = new FileWriter("text/reci.csv",false);
                        PrintWriter cw = new PrintWriter(new BufferedWriter(csvfile));

                        for(int i =0;i<mp.totalqty+2;i++){
                            if(i<=1){

                            }else{
                                cw.print(mp.totllogList.get(i).get(0));
                                cw.print(",");
                                cw.println(mp.totllogList.get(i).get(1));
                            }
                            
                        }
                        cw.close();



                    } catch (IOException ex){
                        System.out.println(ex);
                    }
                }else{
                    err(errcl,"0円では会計できません") ;
                }
            }
        });

        //セットボタンを入力したら変数が+1され通常のメニューを選択した際の処理が変わるようにしている
        setbtn = new JButton();
        setbtn.setText("SET");
        setbtn.setBounds(1,500,70,70);
        setbtn.setFont(new Font("メイリオ",Font.BOLD,10));
        this.add(setbtn);
        setbtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(setmode == 0){
                    setmode++;
                    setbtn.setContentAreaFilled(false);
                }else{
                    setmode = 0;
                    setbtn.setContentAreaFilled(true);
                }
            }
        });

        //商品削除ボタン選択しているリストのインデックス番号を参照し商品を取り消す。
        delButton = new JButton();
        delButton.setText("削除");
        delButton.setBounds(1,100,70,70);
        delButton.setFont(new Font("メイリオ",Font.BOLD,10));
        this.add(delButton);
        delButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int delqty = list.getSelectedIndex();
                System.out.println(delqty);
                if(delqty>1){
                    try{
                        System.out.println(mp.totllogList);
                        if((mp.totllogList.get(delqty-1).get(1)).equals("set")&&(mp.totllogList.get(delqty-2).get(1)).equals("set")){
                            int delset = delqty-2;
                            for(int i = 3;i!=0;i--){
                                try{
                                    mp.total -= Integer.parseInt(mp.totllogList.get(delset).get(1)); 
                                }catch(Exception ee){

                                }
                                mp.totllogList.remove(mp.totllogList.get(delset));
                                model.remove(delset);
                                mp.totalqty--;
                            }
                            System.out.println(mp.totllogList);
                        }else{
                            mp.total -= Integer.parseInt(mp.totllogList.get(delqty).get(1));
                            mp.totllogList.remove(mp.totllogList.get(delqty));
                            model.remove(delqty);
                            mp.totalqty--;
                        }
                    }catch (Exception er){
                        err(errcl,"メニューORsetのメインを選択してください") ;
                    }
                    
                }else{
                    err(errcl,"削除したい番号を選択してください．");
                }
                qtymenu = 0;
                qtymode = 0;
            }
        });

        //SMLが選択された際に処理が変わるように＋1している．
        JButton small = new JButton();
        JButton medium = new JButton();
        JButton large = new JButton();
        small.setText("S");
        small.setBounds(1,200,80,80);
        small.setFont(new Font("メイリオ",Font.BOLD,20));
        this.add(small);
        small.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(smode == 0){
                    smode++;
                    small.setContentAreaFilled(false);
                    mmode = 0;
                    medium.setContentAreaFilled(true);
                    lmode = 0;
                    large.setContentAreaFilled(true);
                }else{
                    smode = 0;
                    small.setContentAreaFilled(true);
                }
            }
        });
        medium.setText("M");
        medium.setBounds(1,300,80,80);
        medium.setFont(new Font("メイリオ",Font.BOLD,20));
        this.add(medium);
        medium.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(mmode == 0){
                    mmode++;
                    medium.setContentAreaFilled(false);
                    smode = 0;
                    small.setContentAreaFilled(true);
                    lmode = 0;
                    large.setContentAreaFilled(true);
                }else{
                    mmode = 0;
                    medium.setContentAreaFilled(true);
                }
            }
        });
        large.setText("L");
        large.setBounds(1,400,80,80);
        large.setFont(new Font("メイリオ",Font.BOLD,20));
        this.add(large);
        large.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(lmode == 0){
                    lmode++;
                    large.setContentAreaFilled(false);
                    mmode = 0;
                    medium.setContentAreaFilled(true);
                    smode = 0;
                    small.setContentAreaFilled(true);
                }else{
                    lmode = 0;
                    large.setContentAreaFilled(true);
                }
            }
        });

    }

    //まとめ買いボタン作成メソッド

    void mekeqtybtn(MenuBtn t ,JButton x,int i){
        int xq = 50;
        xq*=i;
        x = new JButton();
        x.setBounds(xq,1,40,40);
        x.setText(Integer.toString(i));
        x.setFont(new Font("メイリオ",Font.PLAIN,10));
        this.add(x);
        x.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(qtymode!=0){
                    qtymode = 0;
                    qtymenu = 0;
                    qrylLabel.setText("1");
                    
                }else{
                    qtymenu = i;
                    qtymode++;
                    qrylLabel.setText(Integer.toString(i));
                }
            }
        });
    }


    //通常のボタン作成メソッドSMLの入力やセットの入力で処理が変わる処理最後に注文履歴リストに追加され削除する際に選べるようにしている
    void makeBtn(MenuBtn t ,JButton x ,List<String> y){
        int b = 0;
        int a =0;
        try{
            if (Integer.parseInt(y.get(2))==1){
                b=100;
                menuqty++;
                a = 100*menuqty;
            }else if(Integer.parseInt(y.get(2))==2){
                b=300;
                sideqty++;
                a = 100*sideqty;
            }else{
                b=500;
                drinkqty++;
                a = 100*drinkqty;
            }
            x = new JButton();
            x.setBounds(a,b,80,80);
            x.setText(y.get(0));
            x.setMargin(new Insets(0,0,0,0));
            x.setFont(new Font("メイリオ",Font.BOLD,8));
            this.add(x);
            x.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if((setmode==1)&&(Integer.parseInt(y.get(3))==1)&&(Integer.parseInt(y.get(2))==1)){//セットかつセットにできるかつバーガー
                        setStrings = Arrays.asList();
                        setStrings = y;
                        t.setbtn(y);
                        setsidemode = 1;
                        
                    }else if((setsidemode==1)&&(setmode==1)&&(Integer.parseInt(y.get(3))==1)&&(Integer.parseInt(y.get(2))==2)){//セットかつセットにできるかつサイド
                        if(smode == 1){
                            t.setside(y,1);
                            model.addElement(t.totllogList.get(t.totalqty+1).get(0));
                        }else if(lmode == 1){
                            t.setside(y,3);
                            model.addElement(t.totllogList.get(t.totalqty+1).get(0));
                        }else{
                            t.setside(y,2);
                            model.addElement(t.totllogList.get(t.totalqty+1).get(0));
                        }
                        setsidemode = 0;
                        setdrinkmode = 1;   
                    }else if((setdrinkmode == 1)&&(setmode==1)&&(Integer.parseInt(y.get(3))==1)&&(Integer.parseInt(y.get(2))==3)){//セットかつセットにできるかつドリンク
                        if(lmode == 1){
                            t.setdrink(y,2);
                            model.addElement(t.totllogList.get(t.totalqty+1).get(0));
                            t.setmas(setStrings);
                            model.addElement(t.totllogList.get(t.totalqty+1).get(0));
                            setmode = 0;
                            setbtn.setContentAreaFilled(true);
                        }else{
                            t.setdrink(y, 1);
                            model.addElement(t.totllogList.get(t.totalqty+1).get(0));
                            t.setmas(setStrings);
                            model.addElement(t.totllogList.get(t.totalqty+1).get(0));
                            setmode = 0;
                            setbtn.setContentAreaFilled(true);
                        }
                        setdrinkmode = 0;
                    }else if((setmode==0)&&(qtymode == 1)){
                        int iw = t.totalqty;
                        t.putmenu(y,qtymenu);
                        for(int i=0;i<qtymenu;i++){
                            iw++;
                            model.addElement(t.totllogList.get(t.totalqty+1).get(0));
                        }
                        qtymode = 0;
                        qrylLabel.setText("1");
                        //サイズ処理
                    }else if((setmode==0)&&(Integer.parseInt(y.get(4))==1)){
                        if((Integer.parseInt(y.get(2))==2)&&(smode==1)){
                            String n = y.get(0);
                            String mn = y.get(1);
                            y.set(1,"150");
                            y.set(0, "S"+y.get(0));
                            t.menubtn(y);
                            model.addElement(t.totllogList.get(t.totalqty+1).get(0));
                            y.set(0,n);
                            y.set(1,mn);
                        }else if((Integer.parseInt(y.get(2))==2)&&(lmode==1)){
                            String n = y.get(0);
                            String mn = y.get(1);
                            y.set(1,"330");
                            y.set(0, "L"+y.get(0));
                            t.menubtn(y);
                            model.addElement(t.totllogList.get(t.totalqty+1).get(0));
                            y.set(0,n);
                            y.set(1,mn);
                        }else if((Integer.parseInt(y.get(2))==3)&&(smode==1)){
                            if(Integer.parseInt(y.get(1))==220){
                                String n = y.get(0);
                                String mn = y.get(1);
                                y.set(1, "100");
                                y.set(0, "S"+y.get(0));
                                t.menubtn(y);
                                model.addElement(t.totllogList.get(t.totalqty+1).get(0));
                                y.set(0,n);
                                y.set(1,mn);
                            }else{
                                String n = y.get(0);
                                String mn = y.get(1);
                                y.set(1, "150");
                                y.set(0, "S"+y.get(0));
                                t.menubtn(y);
                                model.addElement(t.totllogList.get(t.totalqty+1).get(0));
                                y.set(0,n);
                                y.set(1,mn);
                            }
                        }else if((Integer.parseInt(y.get(2))==3)&&(lmode==1)){
                            if(Integer.parseInt(y.get(1))==220){
                                String n = y.get(0);
                                String mn = y.get(1);
                                y.set(1, "250");
                                y.set(0, "L"+y.get(0));
                                t.menubtn(y);
                                model.addElement(t.totllogList.get(t.totalqty+1).get(0));
                                y.set(0,n);
                                y.set(1,mn);
                            }else{
                                String n = y.get(0);
                                String mn = y.get(1);
                                y.set(1, "270");
                                y.set(0, "L"+y.get(0));
                                t.menubtn(y);
                                model.addElement(t.totllogList.get(t.totalqty+1).get(0));
                                y.set(0,n);
                                y.set(1,mn);
                            }
                        }else{
                            t.menubtn(y);
                            model.addElement(t.totllogList.get(t.totalqty+1).get(0));
                        }
                        //サイズごとに変更
                    }else if(setmode==0){
                        t.menubtn(y);
                        model.addElement(t.totllogList.get(t.totalqty+1).get(0));
                    }else{
                        errcl.setText("セットボタンが選択されています");
                        centrcosole.setVisible(true);
                    }
                }
            });
        }catch(Exception e){

        }
        
    }
    //画面にエラー表示のためのメソッド
    void err(JButton er,String msg){
        er.setText(msg);
        centrcosole.setVisible(true);
    }
    //常設メニュー以外を読み込みエラー回避処理
    void trymakebtn(JButton j, List<String> l){
        j = new JButton();
        try{
            makeBtn(mp, j, l);
        }catch(Exception e){
        }
    }
}