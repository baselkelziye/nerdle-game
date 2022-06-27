package frontend;

import frontend.Hucre;

import java.util.ArrayList;
//bu sinifta oyunu kayit ederken lazim olan bilgileri tutan  bir sinif olusturduk vve biz
// oyunun bilgilerini bu objeye saveliyoruz
public class GameSave implements java.io.Serializable {
    private ArrayList<Hucre> birDenklemSatri;
    private String denklem;
    private int denklemUzunlugu;
    private int dakika;
    private int saniye;
    private int tahminEtSayisi;

    public GameSave(ArrayList<Hucre> birDenklemSatri, String denklem, int denklemUzunlugu,int dakika, int saniye, int tahminEtSayisi){
        this.birDenklemSatri = birDenklemSatri;
        this.denklem = denklem;
        this.denklemUzunlugu = denklemUzunlugu;
       this.dakika = dakika;
       this.saniye = saniye;
       this.tahminEtSayisi = tahminEtSayisi;
    }


    public ArrayList<Hucre> getBirDenklemSatri() {
        return birDenklemSatri;
    }

    public String getDenklem() {
        return denklem;
    }

    public int getDenklemUzunlugu() {
        return denklemUzunlugu;
    }

    public int getDakika() {
        return dakika;
    }

    public int getSaniye() {
        return saniye;
    }

    public int getTahminEtSayisi(){
        return tahminEtSayisi;
    }
}
