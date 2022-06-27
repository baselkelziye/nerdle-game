package frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import backend.Denklem;

// oyun arayuzu oyun burda donuyor ve oyun paneli olusyor.
public class OyunAraYuzu implements ActionListener {
    private int oyunHucresineTiklayis = 1;
    private static int  tahminSayisi;

    private int denklemUzunlugu;
    private Hucre oyunGozu = new Hucre();
    private JButton secimGozu =  new JButton();
    private String finalEqutaion;
    private static ArrayList<Hucre> birDenklemSatri;

    // GUI Components

    // Properties of Program.
    private static byte centiseconds = 0;
    private static byte seconds;
    private static short minutes;

    private DecimalFormat timeFormatter;

    private static Timer timer;


    public static ArrayList<Hucre> getBirDenklemSatri() {
        return birDenklemSatri;
    }

    public static byte getSeconds() {
        return seconds;
    }

    public static short getMinutes() {
        return minutes;
    }

    public static int getTahminSayisi() {
        return tahminSayisi;
    }


    private static JFrame frame;
    private static boolean SonraBitirecek;
private     JLabel clocktext;


    public static void setSonraBitirecek(boolean sonraBitirecek) {
        SonraBitirecek = sonraBitirecek;
    }

    public OyunAraYuzu(boolean x) { // yeni oyun olusturulunca yeni denkelm uretiliyor!
//    	Random rand = new Random();
//        int lengthOfTheEquation = rand.nextInt(3) + 7;
//        Denklem denklem1 = new Denklem();
//        denklem1.infix2Postfix(denklem1.getDenklem());
//        while(lengthOfTheEquation != denklem1.isAllowed(lengthOfTheEquation)){
//            denklem1 = new Denklem();
//            denklem1.infix2Postfix(denklem1.getDenklem());
//        }
//        finalEqutaion = Denklem.getFinalEquation();
        generateEquation();
        //System.out.println("Denklem -> "+ finalEqutaion);
        setSonraBitirecek(true);

        initComponents(); // oyun panelini olusturur
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width/2 + 350, screenSize.height/2 +  100);
        seconds = 0;
        minutes = 0;
        tahminSayisi = 0;
    }
//
//    public OyunAraYuzu(){
//
//        finalEqutaion = Denklem.getFinalEquation();
//        System.out.println("Denklem -> "+ finalEqutaion);
//        setSonraBitirecek(true);
//
//        initComponents();
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        frame.setSize(screenSize.width/2 + 350, screenSize.height/2 +  100);
//        seconds = 0;
//        minutes = 0;
//        tahminSayisi = 0;
//    }

    public OyunAraYuzu(GameSave aGameSave){ // dosyadan oyunun son durumunu save le
        birDenklemSatri = aGameSave.getBirDenklemSatri();
        seconds =(byte) aGameSave.getSaniye();
        minutes = (byte) aGameSave.getDakika();
        finalEqutaion=  aGameSave.getDenklem();
        denklemUzunlugu = aGameSave.getDenklemUzunlugu();
        //System.out.println("Denklem -> "+ finalEqutaion);
        tahminSayisi = aGameSave.getTahminEtSayisi();
        initLoadedComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width/2 + 350, screenSize.height/2 + 100);
    }

    public static void frameiKapat(){
        frame.setVisible(false);
    }
    public Hucre birHucreOlustur(){
        Hucre btn  =  new Hucre(); // hucre buton olarak kullandim bazi ozellikleri ekleyip.
        btn.setBackground(new Color(246,250,255));
        btn.setFont(new Font("SansSerif", Font.BOLD,16));
            btn.addActionListener(this);
        return btn;
    }

    public JButton birSecimHucresiOlustur(){// secim hucresi (sayilari ve islemleri iceren hucreler) dogrudan jButton
 // boylece oyun hucreleri ile secim hucrelerini ayirt etmis oldum
        JButton btn = new JButton();
        btn.setFont(new Font("SansSerif", Font.BOLD,14));
        btn.setBackground(new Color(51,67,100));
        btn.setBorderPainted(false);
        btn.addActionListener(this);
        return btn;
    }
    public JPanel secimHucreleriOlustur(){// secim hucrelerin sayisi fix oldugu icin 2x9 luk butonlar olusturdum
        JPanel panel = new JPanel(new GridLayout(2,9));
        panel.setBackground(new Color(246,250,255));

        for(int i = 0; i < 10; i++) {
            JButton btn = birSecimHucresiOlustur();
            btn.setText("" + i);
            btn.setFont(new Font("SansSerif", Font.BOLD,16));
            btn.setForeground(new Color(246,250,255));
            panel.add(btn);
        }
        JButton btn = birSecimHucresiOlustur();
        btn.setText("+");
        btn.setFont(new Font("SansSerif", Font.BOLD, 16));
        btn.setForeground(new Color(246,250,255));
        panel.add(btn);

        btn = birSecimHucresiOlustur();
        btn.setText("-");
        btn.setFont(new Font("SansSerif", Font.BOLD, 16));
        btn.setForeground(new Color(246,250,255));
        panel.add(btn);

        btn = birSecimHucresiOlustur();
        btn.setText("*");
        btn.setFont(new Font("SansSerif", Font.BOLD, 16));
        btn.setForeground(new Color(246,250,255));
        panel.add(btn);

        btn = birSecimHucresiOlustur();
        btn.setText("/");
        btn.setFont(new Font("SansSerif", Font.BOLD, 16));
        btn.setForeground(new Color(246,250,255));
        panel.add(btn);

        btn = birSecimHucresiOlustur();
        btn.setText("=");
        btn.setFont(new Font("SansSerif", Font.BOLD, 16));
        btn.setForeground(new Color(246,250,255));
        panel.add(btn);

        btn = birSecimHucresiOlustur();
        btn.setText("Tahmin Et");
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setForeground(new Color(246,250,255));
        panel.add(btn);

        btn = birSecimHucresiOlustur();
        btn.setText("Sil");
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setForeground(new Color(246,250,255));
        panel.add(btn);

        btn = birSecimHucresiOlustur();
        btn.setText("Sonra Bitir");
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setForeground(new Color(246,250,255));
        panel.add(btn);

        return  panel;//Denklem.getfi
    }

    public JPanel tahminMatrixiOlustur(int denklemUzunlugu){ // tahmin matrixi oyunun hucreleridir.
        // ve onlari hucre arraylisti olarak tuttum ama gosteriste matrix olarak gosteriliyor
        // sutun sayisini dinamik olarak denkelmin sayisi olarak olusturdum. burda gridlayouttan cok faydalandik.
        JPanel panel = new JPanel(new GridLayout(6,denklemUzunlugu));
        panel.setBackground(new Color(246,250,255));
        ArrayList<Hucre> birDenklemSatri = new ArrayList<>();
        for(int i =0; i < 6; i++){
            for(int j=0; j < denklemUzunlugu; j++){
                Hucre btn = birHucreOlustur();
                btn.setId(i*denklemUzunlugu + j); // olusturulan her hucreye unique bir id atadik!! bu cok onemli hucre hangi//
                // satirda oldugunu bulmak icin.
                btn.setIndex(j);
                birDenklemSatri.add(btn);
                panel.add(btn);
            }
        }
        this.denklemUzunlugu = denklemUzunlugu;
        this.birDenklemSatri = birDenklemSatri;
        
        return  panel;
    }

    public JPanel tahminMatrixiLoad(int denklemUzunlugu){// burda dosyadan olusturulan matrix i load eder
        JPanel panel = new JPanel(new GridLayout(6,denklemUzunlugu));
        panel.setBackground(new Color(246,250,255));

        for(int i = 0 ; i < 6;i++){
            for(int j = 0; j < denklemUzunlugu;j++){
                birDenklemSatri.get(i*denklemUzunlugu+j).addActionListener(this);
                panel.add(birDenklemSatri.get(i*denklemUzunlugu+j));
            }
        }
        return panel;
    }

    public boolean birSatirDoldurulduKontrolu(int satirBilgisi){ // burda inputtan alinan bilgiler bir satiri doldurdu mu bilgisi
        //eger satir tam doldurulmamissa ona hata mesaji cikmaktadir!
        int baslangicNoktasi = satirBilgisi*denklemUzunlugu;
        for(int i = baslangicNoktasi; i < denklemUzunlugu*tahminSayisi + denklemUzunlugu; i++){
            if(birDenklemSatri.get(i).getText().equals("")){
            JOptionPane.showMessageDialog(null,"Lütfen Boşluk Kalmayacak Şekilde Denklemi Doldurunuz!","Hata", JOptionPane.ERROR_MESSAGE);
            return false;
            }
        }
        return true;
    }


    public void display(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       frame.setLocation(screenSize.width/2 - frame.getWidth()/2, screenSize.height/2- frame.getHeight()/2);//acilan pencerenin ortada acilmasini saglar
        frame.setVisible(true);
    }

    private void initComponents(){// oyunun componentleri olsuturur.
        frame = new JFrame();
        JPanel anaPanel = new JPanel();
        JLabel groupNumarasi = new JLabel("2- 20011629 Barış Bakım - 20011906 Basel Kelziye",JLabel.CENTER);
        groupNumarasi.setFont(new Font("SansSerif",Font.BOLD,16));
        groupNumarasi.setForeground(new Color(66, 76, 97));
        frame.setTitle("Nerdle");
        JPanel ustPanel = tahminMatrixiOlustur(finalEqutaion.length());
        ustPanel.setBackground(new Color(246,250,255));
        JPanel altPanel = secimHucreleriOlustur();
        altPanel.setBackground(new Color(246,250,255));


        clocktext =  new JLabel("Süre: ");
        clocktext.setBorder(new EmptyBorder(0,100,0,0));
        clocktext.setFont(new Font("SansSerif", Font.BOLD,16));
        clocktext.setForeground(new Color(66,76,97));
        stopWatch();
        startTimer();

        JPanel footerPanel =  new JPanel(new BorderLayout());
        footerPanel.add(groupNumarasi,BorderLayout.CENTER);
        footerPanel.add(clocktext,BorderLayout.WEST);
        footerPanel.setBackground(new Color(246,250,255));


        anaPanel.setLayout(new GridLayout(3,1));

        anaPanel.add(ustPanel);
        anaPanel.add(altPanel);
        anaPanel.add(footerPanel,BorderLayout.PAGE_END);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(anaPanel);

    }


    public void initLoadedComponents(){ // dosyadan okunan objeleri load eder ve oyun paneline ona gore olusturur!
        frame = new JFrame();
        JPanel anaPanel = new JPanel();
        JLabel groupNumarasi = new JLabel("2- 20011629 Barış Bakım - 20011906 Basel Kelziye",JLabel.CENTER);
        groupNumarasi.setFont(new Font("SansSerif",Font.BOLD,16));
        groupNumarasi.setForeground(new Color(66, 76, 97));
        frame.setTitle("Nerdle");

        JPanel ustPanel = tahminMatrixiLoad(denklemUzunlugu);
        JPanel altPanel = secimHucreleriOlustur();
        altPanel.setBackground(new Color(246,250,255));

        clocktext =  new JLabel("Süre: ");
        clocktext.setFont(new Font("SansSerif", Font.BOLD,16));
        clocktext.setForeground(new Color(66,76,97));
        clocktext.setBorder(new EmptyBorder(0,100,0,0));
        stopWatch();
        startTimer();

        JPanel footerPanel =  new JPanel(new BorderLayout());
        footerPanel.add(groupNumarasi,BorderLayout.CENTER);
        footerPanel.add(clocktext,BorderLayout.WEST);
        footerPanel.setBackground(new Color(246,250,255));



        anaPanel.setLayout(new GridLayout(3,1));
        anaPanel.add(ustPanel);
        anaPanel.add(altPanel);
        anaPanel.add(footerPanel,BorderLayout.PAGE_END);
        frame.add(anaPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
// oyunun dondugu kisim burasidir!.
        
        if (e.getSource().getClass().equals(secimGozu.getClass())) {
            String tmpTE = "Tahmin Et";
            String tmpSB = "Sonra Bitir";
            String tahminEdilenDenklem = "";
            secimGozu = (JButton) e.getSource(); // buraya tahmin et basildi
            if (secimGozu.getText().equals(tmpTE)) {
                // burda Tahmin Et butonuna tiklandi
                if(birSatirDoldurulduKontrolu(tahminSayisi)){// bu fonksiyon tahminSayisi'a denk gelen satrin doldurup doldurul
                    //madigini kontrol eder eger doldurulmussa true donderir.
                	tahminEdilenDenklem = isValid();
                    
                    if(!(tahminEdilenDenklem == null)) {
                        //System.out.println("Denklem -> " + finalEqutaion);
                    	//buraya dogru bir denklem girildi, renklendirme yapilacak
                    	colorise(tahminEdilenDenklem);
                    //    displayColor(tahminSayisi);

                    	if(victoryCheck()) { // oyun kazanildi mi kontrolu
                            // eger oyun kazanilmissa istatsitik bilgilerini dosyada ceker
                            //bu oyunun bilgileri ile yeni bilgileri bulur ve saveler diger dosyaya

                            try{
                                Istatistikler istatistikler;
                                FileInputStream fileIn = new FileInputStream("IstatistiklerDosyasi");
                                ObjectInputStream in =  new ObjectInputStream(fileIn);
                                istatistikler = (Istatistikler) in.readObject();
                                in.close();
                                fileIn.close();

                                istatistikler.setBasarili(istatistikler.getBasarili()+1);
                                istatistikler.setTahminSayisi(istatistikler.getTahminSayisi()+ this.tahminSayisi +1);
                                istatistikler.setToplamDakika(istatistikler.getToplamDakika() + minutes);
                                istatistikler.setToplamSaniye(istatistikler.getToplamSaniye() + seconds);


                                FileOutputStream fileout = new FileOutputStream("IstatistiklerDosyasi");
                                ObjectOutputStream out = new ObjectOutputStream(fileout);
                                out.writeObject(istatistikler);
                                out.close();
                                fileout.close();

                            }catch (IOException i){
                                i.printStackTrace();
                            }
                            catch (ClassNotFoundException c){
                                JOptionPane.showMessageDialog(null,"Oyunun Son Haline Erişmeye Çalışırken Hata Oldu Lütfen Tekrar Deneyiniz!","Başarısız Okuma!", JOptionPane.ERROR_MESSAGE);
                                c.printStackTrace();
                            }
                        WinPage aWinPage = new WinPage(); // kazandin frame i aciliyor
                            aWinPage.setVisible(true);

                    	}
                        else if(tahminSayisi == 5 && !victoryCheck()){ // oyunu kaybetme durumu

                            try{ // istatistikler cekilir ve gereken duzeltmeler yapilir. ve tekrar savelenir
                                Istatistikler istatistikler = null;
                                FileInputStream fileIn = new FileInputStream("IstatistiklerDosyasi");
                                ObjectInputStream in =  new ObjectInputStream(fileIn);
                                istatistikler = (Istatistikler) in.readObject();
                                istatistikler.setBasarisiz(istatistikler.getBasarisiz()+1);
                                in.close();
                                fileIn.close();

                                FileOutputStream fileout = new FileOutputStream("IstatistiklerDosyasi");
                                ObjectOutputStream out = new ObjectOutputStream(fileout);
                                out.writeObject(istatistikler);
                                out.close();
                                fileout.close();

                            }catch (IOException i){
                                i.printStackTrace();
                            }
                            catch (ClassNotFoundException c){
                                System.out.println("Oyunun Son Haline Erişmeye Çalışırken Hata Oldu Lütfen Tekrar Edin.");
                                c.printStackTrace();
                            }
                            LosePage aLosePage = new LosePage(); // yenildin bilgisi verilir.
                            aLosePage.setVisible(true);

                        }
                    	tahminSayisi++; // oyun ddevam ediyorsa tahmin sayisi 1 artar.

                    }
                }
            }

            else if (secimGozu.getText().equals(tmpSB)) { // sonra bitire tiklandiginda!
                stopTimer();
                SonraBitir aSonraBitir =  new SonraBitir(frame);
                // sonra bitirmek istediginize dair bir kucuk pencere acilir ve sure dondurulur.

            }
        }

        if (e.getSource().getClass().equals(oyunGozu.getClass())) {
            oyunGozu = (Hucre) e.getSource();
            if(oyunGozu.getId() >= tahminSayisi*denklemUzunlugu && oyunGozu.getId() < tahminSayisi*denklemUzunlugu + denklemUzunlugu){
            oyunHucresineTiklayis = 2;
            }
            // burda oyun hucresine tiklanmis demek ve bu asagida bir butona tiklarsak tikladigimiz
            //butonun degeri oraya atanmasini saglar!
        }
        else if (oyunHucresineTiklayis == 2 && e.getSource().getClass().equals(secimGozu.getClass())) {
            oyunHucresineTiklayis = 1;
            secimGozu = (JButton) e.getSource();

            if (!(secimGozu.getText().equals("Tahmin Et")) && !(secimGozu.getText().equals("Sil")) && !(secimGozu.getText().equals("Sonra Bitir" ))) {
                Integer sonGozDegeri = tahminSayisi*denklemUzunlugu + denklemUzunlugu -1;
                if(sonGozDegeri.equals(oyunGozu.getId())){
                    birDenklemSatri.get(oyunGozu.getId()).setText(secimGozu.getText());
                }
               else if(oyunGozu.getId() >= tahminSayisi*denklemUzunlugu && oyunGozu.getId() < tahminSayisi*denklemUzunlugu + denklemUzunlugu){
                    oyunGozu.setText(secimGozu.getText());
                    birDenklemSatri.get(oyunGozu.getId()+1).doClick();
                }

            }

            else if (secimGozu.getText().equals("Sil")) {

                //burda Sil butonuna tiklandi!
                Integer birinciGozDegeri = denklemUzunlugu*tahminSayisi;
                 if(birinciGozDegeri.equals(oyunGozu.getId())){// eger en soldaki gozu silmek istersek sadece deger silinecek!
                    birDenklemSatri.get(oyunGozu.getId()).setText("");
                }
              else  if(oyunGozu.getText().equals("") && !(birinciGozDegeri.equals(oyunGozu.getId()))){//sil e bastigimizda Hucre gozu bos ve birinci gozde degil ise
                        birDenklemSatri.get(oyunGozu.getId()-1).doClick();//(bir sol a tiklar)
                }
                else{
                    birDenklemSatri.get(oyunGozu.getId()).setText("");
                    birDenklemSatri.get(oyunGozu.getId()-1).doClick();
                     }
            }
        }

    }
    
    public boolean isOperator(char a){
        if(a == '+' || a == '-' || a == '*' || a == '/'){
            return true;
        }
        else{
            return false;
        }
    }
    
    public String isValid() { // bu metodu kullanicin girdigi denklemin
    	
    	int i = tahminSayisi*denklemUzunlugu, k = 0;
    	String denklem = "";
    	String donusDenklemi = "";
    	String sonuc = "";
    	String yeniDenklem = "";
    	int sonucInt, denklemSonucu;
        boolean buDenklemOlmaz = false;

        if( birDenklemSatri.get(i).getText().charAt(0) == '+' ||birDenklemSatri.get(i).getText().charAt(0) == '/' || birDenklemSatri.get(i).getText().charAt(0) == '*'|| birDenklemSatri.get(i).getText().charAt(0) == '='){// denklemin ilk gozune operator gelirsa hata vermesi
        // ilk goze islem girilirse ("-" disinda) hata vermesi
            JOptionPane.showMessageDialog(null,"Lütfen Denklemin Başlangıcına İşlem Koymayınız!","Hatalı Giriş", JOptionPane.ERROR_MESSAGE);
            return null;
        }
            int esittirSayisi = 0;
            for(int j =0;  j < denklemUzunlugu; j++){
                if(birDenklemSatri.get(i + j).getText().charAt(0) == '='){
                    esittirSayisi++;
                }
            }

            if(esittirSayisi != 1){ // denklemde sadece ve sadece 1 tane "=" bulunmuyorsa hata ver
                JOptionPane.showMessageDialog(null,"Lütfen Denklemin Formatını Düzgün Giriniz!","Hatalı Giriş", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            for(int j = 0; j < denklemUzunlugu-1;j++){ // iki operator yan yana gelince hata vermesi
                    if( isOperator(birDenklemSatri.get(i+j).getText().charAt(0)) && isOperator(birDenklemSatri.get(i+j +1).getText().charAt(0))) {
                        JOptionPane.showMessageDialog(null,"Lütfen iki İşlemi Yan Yana Koymayınız!","Hatalı Giriş", JOptionPane.ERROR_MESSAGE);
                        return null;
                    }
                }


                    for(int j = 0; j < denklemUzunlugu-1; j++){// divide by zero!
                        if(birDenklemSatri.get(i+j).getText().charAt(0) == '/' && birDenklemSatri.get(i+j+1).getText().charAt(0) == '0'){
                            JOptionPane.showMessageDialog(null,"Lütfen Matematiksel Bir Denklem Giriniz!","Hatalı Giriş", JOptionPane.ERROR_MESSAGE);
                        return null;
                        }
                    }

                    for(int j = 0; j < denklemUzunlugu-1; j++){ // = yanina islem gelmesi
                        if(isOperator(birDenklemSatri.get(i+j).getText().charAt(0)) && birDenklemSatri.get(i+j+1).getText().charAt(0) == '='){
                            JOptionPane.showMessageDialog(null,"Lütfen Denklemin Formatını Düzgün Giriniz!","Hatalı Giriş", JOptionPane.ERROR_MESSAGE);
                            return null;
                        }
                    }

        for(int j = 0; j < denklemUzunlugu-1; j++){
            if( birDenklemSatri.get(i+j).getText().charAt(0) == '=' && ((birDenklemSatri.get(i+j+1).getText().charAt(0) == '+') ||(birDenklemSatri.get(i+j+1).getText().charAt(0) == '/') || (birDenklemSatri.get(i+j+1).getText().charAt(0) == '*')) ){
                JOptionPane.showMessageDialog(null,"Lütfen Denklemin Formatını Düzgün Giriniz!","Hatalı Giriş", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }

        if(birDenklemSatri.get(i + denklemUzunlugu-1).getText().charAt(0) == '='){
            JOptionPane.showMessageDialog(null,"Lütfen Denklemin Formatını Düzgün Giriniz!","Hatalı Giriş", JOptionPane.ERROR_MESSAGE);
            return null;
        }

                    while(!birDenklemSatri.get(i).getText().equals("=")) {
                        denklem += birDenklemSatri.get(i).getText();
                        donusDenklemi += birDenklemSatri.get(i).getText();
                        if(isOperator(denklem.charAt(k))) {
                            denklem += " ";
                            k++;
                        }
                        i++;
                        k++;
                    }
                    donusDenklemi += birDenklemSatri.get(i).getText();
                    for(int j = i+1; j<(tahminSayisi+1)*denklemUzunlugu; j++) {
                        donusDenklemi += birDenklemSatri.get(j).getText();
                        sonuc += birDenklemSatri.get(j).getText();
                    }

                    sonucInt = Integer.parseInt(sonuc);

                    Denklem denklemTmp =  new Denklem();
                    yeniDenklem = denklemTmp.infix2Postfix(denklem);
                    denklemSonucu = denklemTmp.postfixEvaluation(yeniDenklem);

                    if(denklemSonucu != sonucInt) {
                        JOptionPane.showMessageDialog(null,"Lütfen Denklemin Sonucunu Kontrol Ediniz!","Hatalı Sonuç", JOptionPane.ERROR_MESSAGE);
                        return null;
                    }
                    return donusDenklemi;









    }
    
    public void colorise(String tahminEdilenDenklem){
    	boolean yerDogru=false, denklemdeVar=false;
    	int tmpIndex = 0;
    	String denklem;
        denklem= finalEqutaion;
    	for(int i = 0; i < denklemUzunlugu; i++) {
			tmpIndex = denklemUzunlugu*tahminSayisi + i;
			for(int j = 0; j<denklemUzunlugu; j++) {
    			if(tahminEdilenDenklem.charAt(i) == denklem.charAt(j)) {
    				birDenklemSatri.get(tmpIndex).setColor("Yellow");
    				denklemdeVar = true;
    				if(!yerDogru && i == j) {
    					yerDogru = true;
    				}
    			}
    		}
    		if(!denklemdeVar) {
				birDenklemSatri.get(tmpIndex).setColor("Red");
    		} else if(yerDogru) {
				birDenklemSatri.get(tmpIndex).setColor("Green");
				birDenklemSatri.get(tmpIndex).setSelected(true);
    		}
    		yerDogru = false;
    		denklemdeVar = false;

    	}

    	
    	for(int i = 0; i < denklemUzunlugu; i++) {
			tmpIndex = denklemUzunlugu*tahminSayisi + i;
			for(int j = 0; j<denklemUzunlugu; j++) {
				int kontrolIndex = denklemUzunlugu*tahminSayisi + j;
    			if((tahminEdilenDenklem.charAt(i) == denklem.charAt(j)) && !birDenklemSatri.get(kontrolIndex).isSelected()) {

    				birDenklemSatri.get(tmpIndex).setColor("Yellow");
    				birDenklemSatri.get(tmpIndex).setBackground(Color.yellow);
    				denklemdeVar = true;
    				if(!yerDogru && i == j) {
    					yerDogru = true;
    				}
    			}
    		}
    		if(tahminEdilenDenklem.charAt(i) == denklem.charAt(i)) {
				birDenklemSatri.get(tmpIndex).setColor("Green");
				birDenklemSatri.get(tmpIndex).setBackground(Color.green);
    		} else if(yerDogru) {

				birDenklemSatri.get(tmpIndex).setColor("Green");
				birDenklemSatri.get(tmpIndex).setBackground(Color.green);
    		} else if(!denklemdeVar) {

    			birDenklemSatri.get(tmpIndex).setColor("Red");
    			birDenklemSatri.get(tmpIndex).setBackground(Color.red);
    		}
    		yerDogru = false;
    		denklemdeVar = false;

    		
    	}
    }
    
    public boolean victoryCheck() {
    	int sayac = 0,tmpIndex;
    	for(int i = 0; i < denklemUzunlugu; i++) {
    		tmpIndex = denklemUzunlugu*tahminSayisi + i;
    		if(!birDenklemSatri.get(tmpIndex).getColor().equals("Green")) {
    			return false;
    		}
    	}
        return true;
    }


    public void stopWatch(){
        timer = new Timer(10, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (centiseconds < 100) {
                    centiseconds++;
                } else {
                    if (seconds == 0 && minutes == 15) {
                        timer.stop();
                    } else if (seconds < 59) {
                        seconds++;
                        centiseconds = 0;
                    } else if (minutes < 60) {
                        minutes++;
                        seconds = 0;
                        centiseconds = 0;
                    }
                }
                clocktext.setText("Süre: " + timeFormatter.format(minutes) + ":"
                        + timeFormatter.format(seconds));


            }
        });
        timeFormatter = new DecimalFormat("00");
    }

    public static void startTimer(){
        timer.start();
    }

    public static void stopTimer(){
        timer.stop();
    }

    public static  void freezeFreame(){
        frame.setEnabled(false);
    }

    void displayColor(int tahminSayisi){
        Integer startingIndex = tahminSayisi*denklemUzunlugu;
        for(int i = startingIndex; i < (tahminSayisi+1)*denklemUzunlugu; i++){
            switch (birDenklemSatri.get(i).getColor()){
                case "Green":
                    birDenklemSatri.get(i).setBackground(new Color(0,255,0));
                    break;


                case "Yellow":
                    birDenklemSatri.get(i).setBackground(new Color(255,255,0));
                    break;

                case "Red":
                    birDenklemSatri.get(i).setBackground(new Color(255,0,0));
                    break;
            }
        }
    }

    public void generateEquation() {
        Random rand = new Random();
        int lengthOfTheEquation = rand.nextInt(3) + 7;
        Denklem denklem1 = new Denklem();
        denklem1.infix2Postfix(denklem1.getDenklem());
        while(lengthOfTheEquation != denklem1.isAllowed(lengthOfTheEquation)){
            denklem1 = new Denklem();
            denklem1.infix2Postfix(denklem1.getDenklem());
        }
        finalEqutaion = Denklem.getFinalEquation();
    }
}