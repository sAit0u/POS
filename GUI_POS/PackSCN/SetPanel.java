package PackSCN;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SetPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    
    JLabel label;
    JLabel setLabel;
    String input;

    JTextField text;
    JButton get;
    JButton get1;
    String mgr_id[] = new String[15];
    MainWindow mainw = new MainWindow();


    SetPanel(){
        this.setLayout(null);
        this.setBackground(Color.red);

        BufferedReader br =null;
        
        try{
            br = new BufferedReader(new FileReader("text/mgrid.csv"));
            int i = 0;
            //csvの行を読みmenuリストに要素を入れている
            while(br.ready()){
                mgr_id[i]=br.readLine();
                i++;
            }
            br.close();
        }catch(FileNotFoundException e){
            System.out.println("ファイルが見つかりません");
            e.printStackTrace();
        }catch(IOException e) {
            System.out.println("入出力エラー");
            e.printStackTrace();
        }finally{
            if(br != null){
                try{
                    br.close();
                }catch(IOException e) {
                    System.out.println("入出力エラー");
                    e.printStackTrace();
                }
            }
        }
    }
    public void prepareComponents(){
        setLabel = new JLabel();
        setLabel.setText("管理者画面");
        setLabel.setBounds(200,30,100,30);
        this.add(setLabel);
        text = new JTextField("管理パス");
        get = new JButton("入力完了");
        text.setBounds(60,100,1000,30);
        this.add(text);
        get.setBounds(1060,100,200,30);
        this.add(get);
        get1 = new JButton();
        get1.setText("メインメニュー画面に切り替えます");
        get1.setBounds(540,350,200,40);
        get1.setVisible(false);
        this.add(get1);

        get.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                input = text.getText();;
                for(int i=0;i<mgr_id.length;i++){
                    String imgr = mgr_id[i];
                    if(input.equals(imgr)){
                        get.setVisible(false);
                        text.setVisible(false);
                        get1.setVisible(true);
                        i=mgr_id.length;
                    }else if((i==(mgr_id.length-1)) && (input!=mgr_id[i])){
                        get.setText("再度入力してください");
                    }else{

                    }
                }    
            }
        });

        get1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Sc_Main.mainWindow.ScreenFocus(ScreenMode.MENU);
            }
        });
        
        
    }
}
