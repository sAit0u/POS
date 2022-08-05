package PackBTN;
import java.util.*;
public class FucBtn extends Btn{
    public int backmoney = 0;//おつり
    public int keep = 0;//お預かり
    Scanner sc = new Scanner(System.in);

    //会計ボタン
    public void cash(int c ,int k){
        keep = k;//ユーザー入力で金額を受け取る

        //値が間違っているとき正しくなるまでの繰り返し処理
        if(c>keep){
            msg = ("預り金を入力してください");
        }else if(keep == c){
            backmoney = 0;
            msg = ("おつりはありません");
        //おつりがある場合の処理
        }else if(keep > c){
            backmoney = (keep - c);//おつりを計算しておつり変数に代入
            msg = ("おつりは"+backmoney);
        }
    }

    //レシートボタン
    public static void receipt(List<List<String>> n,int qty,int rkeep,int rback){
        //ターミナル上にレシートのように表示
        System.out.println("_______________________________________");
        System.out.println("|             Smile burger            |");
        System.out.println("|         TEL 0120-XXXX-XXXX          |");
        String boder = ("|                                     |");
        System.out.println(boder);
        List<List<String>> reci = new ArrayList<>();
        int total = 0;//合計金額
        int ip=0;//i時点における値段の値

        //追加した商品の名前とリストを二次元配列として受け取る処理，qty(商品)個数分繰り返す，(一つ一つの変数化を避けるため）
        for (int i =0;i<qty;i++){
            
            String m = n.get(i).get(0); //iにおける商品名
            String l = n.get(i).get(1); //iにおける値段
            List<String> recil = new ArrayList<String>(Arrays.asList(m,l));
            //初期化して宣言することで参照した際に最後の実行結果に置き換わることを避けている
            reci.add(recil);
        }
        //作成した二次元配列から取り出しレシートのように記載するる処理，qty(商品)個数分繰り返す
        for (int i2 =0;i2<qty;i2++){
            int max_space = 31;//レシートのように表示した時の最大の長さから値段表示分の長さを抜いた値
            //i2毎初期化することで参照した際に最後の実行結果に置き換わることを避けている

            String ipr = reci.get(i2).get(1);//i2地点における値段
            String menu_name = reci.get(i2).get(0);//i2地点における名前
            System.out.print("|"+menu_name);
            int i_len = (menu_name.length()*2);//メニューの長さを参照しX2して一文字分の長さをとる
            max_space -=i_len;//さらにメニューの長さを引いた値
            String reci_space = " ";//規格を合わせるためのスペース
            //i2毎初期化することで参照した際に最後の実行結果に置き換わることを避けている

            //規格を合わせるためのスペースを文字の長さに応じて自動的に規格を合わせるための処理
            for (int i3 =0;i3<max_space;i3++){
                reci_space+=" ";
            }
            if(ipr == "set"){
                reci_space+="set";//セットの場合は３文字のため直接
            }else{
            }

            //リストから値段を数値として受け取りレシートのように表示する処理，もしintにできない場合は0とする
            try{
                ip = Integer.parseInt(ipr);
                System.out.println(reci_space+"￥"+ip+"|");
            }catch(Exception e){
                ip = 0;
                System.out.println("  "+reci_space+"|");
            }
            total+=ip;

        }
        System.out.println(boder);
        String totals = Integer.toString(total);//文字の長さをとるためstring型として作成
        String keeps = Integer.toString(rkeep);
        String backs = Integer.toString(rback);
        //規格を合わせるためのスペースを金額の長さに応じて自動的に規格を合わせるための処理
        if(totals.length()==4){
            System.out.println("|合計                           ￥"+total+"|");
        }else if(totals.length()==5){
            System.out.println("|合計                          ￥"+total+"|");
        }else if(totals.length()==3){
            System.out.println("|合計                            ￥"+total+"|");
        }
        if(keeps.length()==4){
            System.out.println("|お預かり                       ￥"+rkeep+"|");
        }else if(keeps.length()==5){
            System.out.println("|お預かり                      ￥"+rkeep+"|");
        }else if(keeps.length()==3){
            System.out.println("|お預かり                        ￥"+rkeep+"|");
        }
        if(backs.length()==4){
            System.out.println("|おつり                         ￥"+rback+"|");
        }else if(backs.length()==5){
            System.out.println("|おつり                        ￥"+rback+"|");
        }else if(backs.length()==3){
            System.out.println("|おつり                          ￥"+rback+"|");
        }
        System.out.println("|_____________________________________|");
    }
}