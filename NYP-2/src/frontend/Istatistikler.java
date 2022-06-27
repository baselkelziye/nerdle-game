package frontend;
// istatistikleri savelemek adina olusturulan bir sinif ve dogrudan bu obje save edilir.
public class Istatistikler implements java.io.Serializable{

    private int yaridaBirakilan;
    private int basarisiz;
    private int basarili;
    private int tahminSayisi;
    private int toplamSure;
    private int toplamSaniye;

    private  int toplamDakika;

    private Double ortalamaSure;

    public Istatistikler(int yaridaBirakilan, int basarisiz, int basarili, int tahminSayisi, int toplamDakika, int toplamSaniye) {

        this.yaridaBirakilan = yaridaBirakilan;
        this.basarisiz = basarisiz;
        this.basarili = basarili;
        this.tahminSayisi = tahminSayisi;
        this.toplamSaniye = toplamSaniye;
        this.toplamDakika = toplamDakika;
    }

    public Istatistikler(){

    }

    public int getToplamSure() {
        return toplamSure;
    }

    public void setToplamSure(int toplamSure) {
        this.toplamSure = toplamSure;
    }

    public int getYaridaBirakilan() {
        return yaridaBirakilan;
    }
    public void setYaridaBirakilan(int yaridaBirakilan) {
        this.yaridaBirakilan = yaridaBirakilan;
    }
    public int getBasarisiz() {
        return basarisiz;
    }
    public void setBasarisiz(int basarisiz) {
        this.basarisiz = basarisiz;
    }
    public int getBasarili() {
        return basarili;
    }
    public void setBasarili(int basarili) {
        this.basarili = basarili;
    }
    public int getTahminSayisi() {
        return tahminSayisi;
    }
    public void setTahminSayisi(int tahminSayisi) {
        this.tahminSayisi = tahminSayisi;
    }
    public int getToplamSaniye() {

        return toplamSaniye;
    }
    public void setToplamSaniye(int toplamSaniye) {
        if(toplamSaniye + this.toplamSaniye > 59){
            toplamSaniye = toplamSaniye%60;
            toplamDakika = toplamDakika+1;
        }
        else{
            this.toplamSaniye = toplamSaniye;
            }

    }

    public int getToplamDakika() {
        return toplamDakika;
    }

    public void setToplamDakika(int toplamDakika) {
        this.toplamDakika = toplamDakika;
    }

    public String ortalamSure(){

        double toplamSure = getToplamDakika()*60 + getToplamSaniye();

        if(getBasarili() == 0){
            return "00:00";
        }
        toplamSure = toplamSure/getBasarili();
         int ortSaniye = (int) toplamSure%60;
         int ortDakika = (int) toplamSure/60;
         String str = ortDakika+":"+ortSaniye;
         return  str;
    }

    public double getTahminSayisiOrtalamasi(){
        if(getBasarili() == 0){
            return 0;
        }
        double ortalama = (double) (getTahminSayisi())/getBasarili();
        return ortalama;
    }


}