package PackSCN;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import PackBTN.*;
import java.io.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
public class CashPanel extends JPanel{
    private static final long serialVersionUID = 1L;
    
    
    //ボタンのメソッド利用のため
    FucBtn fp = new FucBtn();

    //表示用
    JLabel ltotl;
    JLabel lkeep;
    JLabel lback;
    String cashtext;
    JLabel cashLabel;

    //数値うけ取り変数
    int total;
    String keep;
    int keepcount;
    //会計
    JButton cash;
    JButton jastcash;
    JButton cash1000;
    //スクリーン遷移
    JButton back;
    //数字入力用ボタン
    JButton num1;
    JButton num2;
    JButton num3;
    JButton num4;
    JButton num5;
    JButton num6;
    JButton num7;
    JButton num8;
    JButton num9;
    JButton num0;
    JButton num00;
    JButton del;
    //エラーや読み込み処理用
    JPanel centrcosole;
    JButton errcl;
    JButton lord;
    //ファイル読み込み
    String log;
    File file;
    FileReader fileReader;
    BufferedReader bufferedReader;

    CashPanel(){
        this.setLayout(null);
        this.setBackground(Color.white);
    }
    public void prepareComponents(){

        //想定の範囲内で問題が起きた時表示するパネル
        centrcosole = new JPanel();
        centrcosole.setBounds(500,310,280,100);
        this.add(centrcosole);

        //想定の範囲内で問題が起きた時表示するボタン
        errcl = new JButton();
        errcl.setBounds(125, 70, 100, 30);
        errcl.setVisible(false);
        centrcosole.add(errcl);
        errcl.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(fp.msg.equals("預り金を入力してください")){
                    centrcosole.setVisible(false);
                }else{
                    Sc_Main.mainWindow.preparePanels();
                    Sc_Main.mainWindow.prepareComponents();
                    Sc_Main.mainWindow.ScreenFocus(ScreenMode.MENU);
                    errcl.setVisible(false);
                    lord.setVisible(true);
                }
            }
        });

        //画面遷移が行われたら一番最初に押すことで最新の合計金額と購入リストを読み込む
        lord = new JButton();
        lord.setText("会計画面に変わりました");
        lord.setBounds(125, 70, 100, 30);
        centrcosole.add(lord);
        lord.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                file = new File("text/totalcash.txt");
                fileReader = new FileReader(file);
                
                bufferedReader = new BufferedReader(fileReader);
                cashtext = bufferedReader.readLine();
                bufferedReader.close();
                lord.setVisible(false);
                centrcosole.setVisible(false);
                ltotl.setText("合計金額"+cashtext);
                ltotl.setVisible(true);
                }catch(IOException ew){

                }
                try{
                    file = new File("text/reci.csv");
                    fileReader = new FileReader(file);
                    
                    bufferedReader = new BufferedReader(fileReader);
                    int i = 0;
                    while(bufferedReader.ready()){
                        if(i==0){
                            log =bufferedReader.readLine()+"\n";
                            i++;
                        }else{
                            log += bufferedReader.readLine()+"\n";
                        }
                    }
                    bufferedReader.close();
                    System.out.println(log);
                }catch(IOException ew){

                }
            }
        });

        //合計金額ラベル
        ltotl= new JLabel(); 
        
        ltotl.setBounds(600,100,200,70);;
        ltotl.setFont((new Font("メイリオ",Font.BOLD,25)));;
        ltotl.setOpaque(true);
        ltotl.setBackground(Color.GRAY);
        this.add(ltotl);

        //お預かり金額ラベル

        lkeep = new JLabel();

        lkeep.setBounds(600,200,200,70);;
        lkeep.setFont((new Font("メイリオ",Font.BOLD,25)));;
        lkeep.setOpaque(true);
        lkeep.setBackground(Color.GRAY);
        lkeep.setText(keep);
        this.add(lkeep);

        //会計ボタン終了時画面遷移その後読み込みボタンを表示次の遷移に備える＋レシートのように外部ファイルに会計情報を書き込む

        cash = new JButton("会計");
        cash.setBackground(Color.GREEN);
        cash.setBounds(600, 600, 200, 70);
        this.add(cash);
        cash.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int total = Integer.parseInt(cashtext);
                fp.cash(total,Integer.parseInt(keep));
                errcl.setText(fp.msg);
                errcl.setVisible(true);
                centrcosole.setVisible(true);
                try{
                    FileWriter csvfile = new FileWriter("text/recit.csv",false);
                    PrintWriter cw = new PrintWriter(new BufferedWriter(csvfile));
                    cw.println(log);
                    cw.print("合計金額,");
                    cw.println(cashtext);
                    cw.print("お預かり,");
                    cw.println(keep);
                    cw.print("お釣り,");
                    cw.println(fp.backmoney);
                    cw.close();
                } catch (IOException ex){
                    System.out.println(ex);
                }

            }
        });

        //お預かり金額が等しいとき会計と同様

        jastcash = new JButton("ちょうど");
        jastcash.setBackground(Color.GREEN);
        jastcash.setBounds(600, 520, 200, 70);
        this.add(jastcash);
        jastcash.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int total = Integer.parseInt(cashtext);
                fp.cash(total,total);
                errcl.setText(fp.msg);
                errcl.setVisible(true);
                centrcosole.setVisible(true);
                try{
                    FileWriter csvfile = new FileWriter("text/recit.csv",true);
                    PrintWriter cw = new PrintWriter(new BufferedWriter(csvfile));
                    cw.println(log);
                    cw.print("合計金額,");
                    cw.println(cashtext);
                    cw.print("お預かり,");
                    cw.println(cashtext);
                    cw.print("お釣り,");
                    cw.println(fp.backmoney);
                    cw.println("---------------------------------------------------");
                    cw.close();
                } catch (IOException ex){
                    System.out.println(ex);
                }
            }
        });
        //お預かり金額が会計以上のとき会計と同様
        cash1000 = new JButton("1000円");
        cash1000.setBackground(Color.GREEN);
        cash1000.setBounds(600, 440, 200, 70);
        this.add(cash1000);
        cash1000.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int c = 1000;
                int total = Integer.parseInt(cashtext);
                fp.cash(total,c);
                errcl.setText(fp.msg);
                errcl.setVisible(true);
                centrcosole.setVisible(true);
                if (c>=total){
                    try{
                        FileWriter csvfile = new FileWriter("text/recit.csv",false);
                        PrintWriter cw = new PrintWriter(new BufferedWriter(csvfile));
                        cw.println(log);
                        cw.print("合計金額,");
                        cw.println(cashtext);
                        cw.print("お預かり,");
                        cw.println(cashtext);
                        cw.print("お釣り,");
                        cw.println(fp.backmoney);
                        cw.println("---------------------------------------------------");
                        cw.close();
                    } catch (IOException ex){
                        System.out.println(ex);
                    }
                }else{

                }
            }
        });

        //情報を変えず画面遷移可能その後変更がある場合のために読み込みボタンを表示して画面遷移
        back = new JButton("戻る");
        back.setBounds(10, 600, 120, 70);
        this.add(back);

        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                centrcosole.setVisible(true);
                lord.setVisible(true);
                ltotl.setVisible(false);
                Sc_Main.mainWindow.ScreenFocus(ScreenMode.MENU);
            }
        });
        //お預かり数値をリセット
        del = new JButton();
        del = new JButton("削除");
        del.setBounds(140, 550, 100, 100);
        this.add(del);
        del.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                keep = null;
                lkeep.setText(keep);
                keepcount = 0;
            }
        });

        numbtn(lkeep,num1, 1,140,220);
        numbtn(lkeep,num2, 2,250,220);
        numbtn(lkeep,num3, 3,360,220);
        numbtn(lkeep,num4, 4,140,330);
        numbtn(lkeep,num5, 5,250,330);
        numbtn(lkeep,num6, 6,360,330);
        numbtn(lkeep,num7, 7,140,440);
        numbtn(lkeep,num8, 8,250,440);
        numbtn(lkeep,num9, 9,360,440);
        numbtn(lkeep,num0, 0,250,550);
        
        num00 = new JButton();
        num00.setBounds(360,550,100,100);
        this.add(num00);
        num00.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(keepcount == 0){

                }else{
                    keep+=0;
                    keep+=0;
                    lkeep.setText(keep);
                    keepcount++;
                }
            }
        });

    }
    //ボタン作成メソッド
    void numbtn(JLabel Jl ,JButton Ji,int i ,int x,int y){
        Ji = new JButton(Integer.toString(i));
        Ji.setBounds(x, y, 100, 100);
        this.add(Ji);
        Ji.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(keepcount > 0){
                    keep+=i;
                    System.out.println(keep);
                    Jl.setText(keep);
                    keepcount++;
                }else if(i != 0){
                    keep=Integer.toString(i);
                    System.out.println(keep);
                    Jl.setText(keep);
                    keepcount++;
                }
            }
        });
    }
}
