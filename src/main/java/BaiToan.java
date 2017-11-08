import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;

public class BaiToan {
    ArrayList<Luat> RULE = new ArrayList<Luat>();
    ArrayList<String> GT = new ArrayList<String>();
    ArrayList<String> KL = new ArrayList<String>();
    ArrayList<Luat> SAT = new ArrayList<Luat>();
    ArrayList<String> TG = new ArrayList<String>();

    ArrayList<Clause> clauses = new ArrayList<Clause>();


    public void readClause() {
        InputStream file = this.getClass().getClassLoader().getResourceAsStream("mende.txt");

        InputStreamReader inputStreamReader = new InputStreamReader(file);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str = "";
        String[] arr;

        try {
            while ((str = bufferedReader.readLine()) != null) {
                arr = str.split(",");
                this.clauses.add(new Clause(arr[0], arr[1], Integer.parseInt(arr[2].trim())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //docluat tu file
    public void docLuat() {
        try {

            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("luat2.txt").getFile());

            FileInputStream input = new FileInputStream(file);
            InputStreamReader isR = new InputStreamReader(input);
            BufferedReader bfR = new BufferedReader(isR);
            String str = "";
            int i = 0;
            String[] arr;
            while ((str = bfR.readLine()) != null) {
                arr = str.split("->");
                RULE.add(new Luat(arr[0], arr[1]));
                i++;
            }
            bfR.close();
            isR.close();
            input.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void thuatToanSuyDienTien() {
        docLuat();
        Luat r = new Luat();
        TG = (ArrayList<String>) GT.clone();//gan TG =GT
        loc();//lay tap SAT tuong ung voi buoc SAT=loc(RULE,TG) trong giai thuat

        int i = 0;
        while (SAT.size() != 0) {
            r = SAT.get(0);// lay mot  luat trong SAT
            if (!kiemTraTrung(r.vp))//kiem tra xem vp cua r da thuoc TG chua neu chua thi them vp vao tg
            {
                TG.add(r.vp);//tuong ung voi buoc TG->TG V vp cua r
            }
//            System.out.println("Tap TG o buoc " + i + ": " + TG);
            loatLuatKhoiRULE(r);//tuong ung voi buoc RULE=RULE\{r} trong giai thuat
            loc();//lay tap SAT tuong ung voi buoc SAT=loc(RULE,TG) trong giai thuat
//            System.out.println(RULE.size());
//            for (Luat luat : RULE) {
//                System.out.println(luat.toString());
//            }
            i++;
        }
    }

    public void loatLuatKhoiRULE(Luat r) {
        int dem = -1;
        for (int i = 0; i < RULE.size(); i++)
            if (r.vp.equals(RULE.get(i).vp) && r.vt.equals(RULE.get(i).vt)) {
                dem = i;
                break;
            }
        if (dem != -1) {
            RULE.remove(dem);
        }
    }

    //---------ay tap SAT
    public void loc() {
        SAT.clear();
        for (int j = 0; j < RULE.size(); j++) {
            //if(TG.get(i).equals(RULE.get(j).vt))
            if (kiemtraTHVa(RULE.get(j).vt)) {
                SAT.add(RULE.get(j));
            }
        }
    }

    //------------------------------------
    // vi du neu a va b da thuoc Tg thi a.b cung phai thuoc tg
    public boolean kiemtraTHVa(String str) {
        String arr[] = str.split(",");
        int dem = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < TG.size(); j++) {
                if (arr[i].equals(TG.get(j))) {
                    dem++;
                }
            }
        }
        if (dem == arr.length)
            return true;
        return false;
    }

    //kiem tra xem trong TG da co str chua neu co thi tra ve true
    public boolean kiemTraTrung(String str) {
        for (int i = 0; i < TG.size(); i++) {
            if (TG.get(i).equals(str)) {
                return true;
            }
        }
        return false;
    }

    //--Kiem tra xem KL co thuoc TG khong
    public boolean kt_KL_Thuoc_TG() {
        //neu dem bang do dai cua tap ket luan thi KL thuoc TG
        int dem = 0;
        for (int i = 0; i < KL.size(); i++) {
            for (int j = 0; j < TG.size(); j++) {
                if (KL.get(i).equals(TG.get(j))) {
                    dem++;
                }
            }
        }
        if (dem == KL.size())
            return true;
        return false;
    }
}
