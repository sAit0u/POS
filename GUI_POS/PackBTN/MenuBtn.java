package PackBTN;
//メニューを注文するためのボタンクラス
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class MenuBtn extends Btn {
    public List<List<String>> totllogList = new ArrayList<List<String>>();//注文ログのリスト二次元配列で注文ごとのデータを保持
    public String menuname;//名前
    public int btnprice;//値段
    public int menutype;//メニューのタイプ
    public int totalqty;//個数
    public int total = 0;//合計金額
    public int s;//セットサイドサイズ
    public int d;//セットドリンクサイズ
    public List<String> btnv;//セットの値を保持用リスト
    public List<String> sidev;//セットの値を保持用リスト
    public List<String> drinkv;//セットの値を保持用リスト

    //メニュー読み込むための変数
    String menu[] = new String[19];
    public List<String> menu1;
    public List<String> menu2;
    public List<String> menu3;
    public List<String> menu4;
    public List<String> menu5;
    public List<String> menu6;
    public List<String> menu7;
    public List<String> menu8;
    public List<String> menu9;
    public List<String> menu10;
    public List<String> menu11;
    public List<String> menu12;
    public List<String> menu13;
    public List<String> menu14;
    public List<String> menu15;
    public List<String> menu16;
    public List<String> menu17;
    public List<String> menu18;
    public List<String> menu19;
    public List<String> menu20;
    public List<String> menu21;
    
    public MenuBtn(){
        //販売するメニュー情報csvファイル読み込み
        BufferedReader br =null;
        
        try{
            br = new BufferedReader(new FileReader("text/menu.csv"));
            int i = 0;
            //csvの行を読みmenuリストに要素を入れている
            while(br.ready()){
                String line = br.readLine();
                menu[i]=line;
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
        //メニュー情報
        menu1 = Arrays.asList(menu[1].split(","));
        menu2 = Arrays.asList(menu[2].split(","));
        menu3 = Arrays.asList(menu[3].split(","));
        menu4 = Arrays.asList(menu[4].split(","));
        menu5 = Arrays.asList(menu[5].split(","));
        menu6 = Arrays.asList(menu[6].split(","));
        menu7 = Arrays.asList(menu[7].split(","));
        menu8 = Arrays.asList(menu[8].split(","));
        menu9 = Arrays.asList(menu[9].split(","));
        menu10 = Arrays.asList(menu[10].split(","));
        menu11 = Arrays.asList(menu[11].split(","));
        menu12 = Arrays.asList(menu[12].split(","));
        menu13 = Arrays.asList(menu[13].split(","));
        menu14 = Arrays.asList(menu[14].split(","));
        try{
            menu15 = Arrays.asList(menu[15].split(","));
        }catch(Exception e){

        }
        try{
            menu16 = Arrays.asList(menu[16].split(","));
        }catch(Exception e){

        }
        try{
            menu17 = Arrays.asList(menu[17].split(","));
        }catch(Exception e){

        }
        try{
            menu18 = Arrays.asList(menu[18].split(","));
        }catch(Exception e){

        }
        try{
            menu19 = Arrays.asList(menu[19].split(","));
        }catch(Exception e){

        }
        //二次元配列最初の二つは消せないのでからの値を入れる
        List<String> nullset = new ArrayList<>();
        nullset.add(""); 
        nullset.add(""); 
        totllogList.add(nullset);
        totllogList.add(nullset);
    }
    //配列からボタンを押したときの処理を作るメソッド　
    public void menubtn(List<String> vals){
        btnprice = Integer.parseInt(vals.get(1));
        totllogList.add(vals); 
        total+=btnprice;
        totalqty++;
    }
    //個数ボタンを押したときの処理を作るメソッド　
    public void putmenu(List<String> valsqty,int iq){
        for(int i=0; i<iq;i++){
            menubtn(valsqty);
        }
    }
    //セットでの注文ボタン　メインメニューの名前を受け取り新しいリストに保管
    public void setbtn(List<String> vals){
        msg = ("サイドを選択してください。");
        btnv = new ArrayList<String>(Arrays.asList(vals.get(0)+"セット",""));
    }
    //セットのサイドが選択された後呼び出すメソッド,ポテトならサイズによって変更が加えられる処理、値段をsetに書き換える
    public void setside(List<String> vals ,int sv){
        
        msg = ("ドリンクを選択してください");
        //サイドメニューを追加する処理ナゲットとサラダにはサイズがないので強制的にMに統一ポテトならそのままサイズをとる
        if(vals.get(0).equals("チキンナゲット")){
            sidev = new ArrayList<String>(Arrays.asList("セット"+vals.get(0),""));
            s = 2;
            sidev.set(1,"set");//セットのためメニュー情報をセットに変える
            totllogList.add(sidev);
            totalqty++;
        }else if(vals.get(0).equals("サラダ")){
            sidev = new ArrayList<String>(Arrays.asList("セット"+vals.get(0),""));
            s = 2;
            sidev.set(1,"set");
            totllogList.add(sidev);
            totalqty++;
        }else{
            if(sv==1){
                sidev = new ArrayList<String>(Arrays.asList("Sセット"+vals.get(0),""));
                s = 1;
                sidev.set(1,"set");
                totllogList.add(sidev);
                totalqty++;
            }else if(sv==3){
                sidev = new ArrayList<String>(Arrays.asList("Lセット"+vals.get(0),""));
                s = 3;
                sidev.set(1,"set");//セットのためメニュー情報をセットに変える
                totllogList.add(sidev);
                totalqty++;
            }else{
                sidev = new ArrayList<String>(Arrays.asList("セット"+vals.get(0),""));
                s = 2;
                sidev.set(1,"set");//セットのためメニュー情報をセットに変える
                totllogList.add(sidev);
                totalqty++;
            }
        }
    }

    //セットのドリンクが選択された後呼び出すメソッドMLで変わる処理値段をsetに書き換える
    public void setdrink(List<String> vals,int ds){
        msg = (btnv.get(0)+"が追加されました");
    
        if(ds == 2){
            drinkv = new ArrayList<String>(Arrays.asList("Lセット"+vals.get(0),""));
            d = ds;
            drinkv.set(1,"set");
            totllogList.add(drinkv);
            totalqty++;
        }else{
            d = 1;
            drinkv = new ArrayList<String>(Arrays.asList("Mセット"+vals.get(0),""));
            drinkv.set(1,"set");
            totllogList.add(drinkv);
            totalqty++;
        }
    }
    //セットの会計メソッドさっき保持したリストを元に値段を書き替える、サイドsml、ドリンクsmlで変わる処理
    public void setmas(List<String> vals){
        if(s==2){
            if(d==1){
                btnprice = Integer.parseInt(vals.get(1));
                btnprice+=300;
                btnv.set(1,Integer.toString(btnprice));
                totllogList.add(btnv);//二次元配列に追加
                total+=btnprice;//会計に追加
                System.out.println("現在の会計"+total);
                totalqty++;
            }else{
                btnprice = Integer.parseInt(vals.get(1));
                btnprice+=320;
                btnv.set(1,Integer.toString(btnprice));
                totllogList.add(btnv);
                total+=btnprice;
                totalqty++;
            }
        }else if(s==1){
            if(d==1){
                btnprice = Integer.parseInt(vals.get(1));
                btnprice+=250;
                btnv.set(1,Integer.toString(btnprice));
                totllogList.add(btnv);
                total+=btnprice;
                totalqty++;
            }else{
                btnprice = Integer.parseInt(vals.get(1));
                btnprice+=270;
                btnv.set(1,Integer.toString(btnprice));
                totllogList.add(btnv);
                total+=btnprice;
                totalqty++;
            }
        }else if(s==3){
            if(d==1){
                btnprice = Integer.parseInt(vals.get(1));
                btnprice+=350;
                btnv.set(1,Integer.toString(btnprice));
                totllogList.add(btnv);
                total+=btnprice;
                totalqty++;
            }else{
                btnprice = Integer.parseInt(vals.get(1));
                btnprice+=370;
                btnv.set(1,Integer.toString(btnprice));
                totllogList.add(btnv);
                total+=btnprice;
                totalqty++;
            }
        } 
    }     
        
}
