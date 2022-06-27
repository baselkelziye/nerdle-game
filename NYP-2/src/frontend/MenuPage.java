package frontend;

import javax.swing.*;
import java.awt.*;
import java.io.*;
// menu page in 2 constructor u vardir biri program ilk calistiginda
//burda istatistiklerin  butun degerleri sifir olur.
// digeri ise istatistik degerleri guncellendikten sonra cagrilir
//istatistikleri guncelledikten sonra cagrilinca ordaki bilgileri dosyadan okur gunceller ve yazar.

public class MenuPage extends javax.swing.JFrame {
    private static String denklem;

    public static String getDenklem(){
        return denklem;
    }


    /**
     * Creates new form MenuPage
     */
    public MenuPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width/2, screenSize.height/2);
        setLocation(screenSize.width/2 - getWidth()/2, screenSize.height/2- getHeight()/2);
        initComponents();
        setTitle("Giriş Menü");
        yaridaBirakilanOyunSayisiInfo.setText("0");
        kaybedilenOyunSayisiInfo.setText("0");
        kazanilanOyunlardaTahminSayisiInfo.setText("0");
        sureOrtalamasiInfo.setText("00:00");
        kazanilanOyunSayisiInfo.setText("0");
        Istatistikler istatistikler = new Istatistikler(0,0,0,0,0,0);
        try{
            FileOutputStream fileout = new FileOutputStream("IstatistiklerDosyasi");
            ObjectOutputStream out = new ObjectOutputStream(fileout);
            out.writeObject(istatistikler);
            System.out.println("Istatistikler Basarili Bir Sekilde Kaydedildi!");

        }catch (IOException i){
            i.printStackTrace();
        }


    }

    public MenuPage(Istatistikler istatistikler){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width/2, screenSize.height/2);
        setLocation(screenSize.width/2 - getWidth()/2, screenSize.height/2- getHeight()/2);


        initComponents();
        setTitle("Giriş Menü");
        File f = new File("IstatistiklerDosyasi");
        if(!f.exists()){
            JOptionPane.showMessageDialog(null,"Istatistikleri Hesaplarken Hata Oldu!","Hata", JOptionPane.ERROR_MESSAGE);
        }
        else{
            try{
                Istatistikler e = null;
                FileInputStream fileIn = new FileInputStream("IstatistiklerDosyasi");
                ObjectInputStream in =  new ObjectInputStream(fileIn);
                e = (Istatistikler) in.readObject();
                in.close();
                fileIn.close();
                
                yaridaBirakilanOyunSayisiInfo.setText(Integer.toString(e.getYaridaBirakilan()));
                kaybedilenOyunSayisiInfo.setText(Integer.toString(e.getBasarisiz()));
                if(Double.toString(e.getTahminSayisiOrtalamasi()).length()>=4) {
                	String s = Double.toString(e.getTahminSayisiOrtalamasi()).charAt(0) + "."
                			 + Double.toString(e.getTahminSayisiOrtalamasi()).charAt(2)
                			 + Double.toString(e.getTahminSayisiOrtalamasi()).charAt(3);
                	kazanilanOyunlardaTahminSayisiInfo.setText(s);
                }else {
                	kazanilanOyunlardaTahminSayisiInfo.setText(Double.toString(e.getTahminSayisiOrtalamasi()));
                }
                sureOrtalamasiInfo.setText(e.ortalamSure());
                kazanilanOyunSayisiInfo.setText(Integer.toString(e.getBasarili()));
            }catch (IOException i){
                i.printStackTrace();
            }
            catch (ClassNotFoundException c){
                System.out.println("Oyunun Son Haline Erişmeye Çalışılırken Hata Oldu Lütfen Tekrar Edin.");
                c.printStackTrace();
            }
        }
    }

    public void setKaybedilanOyunSayisiLabel(JLabel kaybedilanOyunSayisiLabel) {
        this.kaybedilanOyunSayisiLabel = kaybedilanOyunSayisiLabel;
    }


    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        istatistiklerLabel = new javax.swing.JLabel();
        yaridaBirakilanOyunSayisiLabel = new javax.swing.JLabel();
        yaridaBirakilanOyunSayisiInfo = new javax.swing.JLabel();
        kaybedilanOyunSayisiLabel = new javax.swing.JLabel();
        kaybedilenOyunSayisiInfo = new javax.swing.JLabel();
        kazanilanOyunSayisiLabel = new javax.swing.JLabel();
        kazanilanOyunSayisiInfo = new javax.swing.JLabel();
        yaridaBirakilanOyunSayisiLabel3 = new javax.swing.JLabel();
        kazanilanOyunlardaSureOrtalamasi = new javax.swing.JLabel();
        sureOrtalamasiInfo = new javax.swing.JLabel();
        kazanilanOyunlardaTahminSayisiLabel = new javax.swing.JLabel();
        kazanilanOyunlardaTahminSayisiInfo = new javax.swing.JLabel();
        yeniOyunButonu = new javax.swing.JButton();
        devamEtButonu = new javax.swing.JButton();
        testButonu = new javax.swing.JButton();
        footerText = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(236, 254, 255));

        jPanel2.setBackground(new java.awt.Color(246, 250, 255));

        istatistiklerLabel.setFont(new java.awt.Font("SansSerif", Font.BOLD, 14)); // NOI18N
        istatistiklerLabel.setForeground(new java.awt.Color(66, 76, 97));
        istatistiklerLabel.setText("İstatistikler:");

        yaridaBirakilanOyunSayisiLabel.setFont(new java.awt.Font("SansSerif", Font.BOLD, 14)); // NOI18N
        yaridaBirakilanOyunSayisiLabel.setForeground(new java.awt.Color(66, 76, 97));
        yaridaBirakilanOyunSayisiLabel.setText("Yarıda Bırakılan Oyun Sayısı:");



        yaridaBirakilanOyunSayisiInfo.setFont(new java.awt.Font("SansSerif", Font.BOLD, 14)); // NOI18N

        kaybedilanOyunSayisiLabel.setFont(new java.awt.Font("SansSerif", Font.BOLD, 14)); // NOI18N
        kaybedilanOyunSayisiLabel.setForeground(new java.awt.Color(66, 76, 97));
        kaybedilanOyunSayisiLabel.setText("Kaybedilen Oyun Sayısı:");

        kaybedilenOyunSayisiInfo.setFont(new java.awt.Font("SansSerif", Font.BOLD, 14)); // NOI18N

        kazanilanOyunSayisiLabel.setFont(new java.awt.Font("SansSerif", Font.BOLD, 14)); // NOI18N
        kazanilanOyunSayisiLabel.setForeground(new java.awt.Color(66, 76, 97));
        kazanilanOyunSayisiLabel.setText("Kazanılan Oyun Sayısı:");

        kazanilanOyunSayisiInfo.setFont(new java.awt.Font("SansSerif", Font.BOLD, 14)); // NOI18N


        yaridaBirakilanOyunSayisiLabel3.setFont(new java.awt.Font("SansSerif", Font.BOLD, 14)); // NOI18N
        yaridaBirakilanOyunSayisiLabel3.setForeground(new java.awt.Color(66, 76, 97));

        kazanilanOyunlardaSureOrtalamasi.setFont(new java.awt.Font("SansSerif", Font.BOLD, 14)); // NOI18N
        kazanilanOyunlardaSureOrtalamasi.setForeground(new java.awt.Color(66, 76, 97));
        kazanilanOyunlardaSureOrtalamasi.setText("Kazanılan Oyunlarda Geçen Sürenin Ortalaması:");

        sureOrtalamasiInfo.setFont(new java.awt.Font("SansSerif", Font.BOLD, 14)); // NOI18N


        kazanilanOyunlardaTahminSayisiLabel.setFont(new java.awt.Font("SansSerif", Font.BOLD, 14)); // NOI18N
        kazanilanOyunlardaTahminSayisiLabel.setForeground(new java.awt.Color(66, 76, 97));
        kazanilanOyunlardaTahminSayisiLabel.setText("Kazanılan Oyunlarda Tahmin Sayısının Ortalaması:");

        kazanilanOyunlardaTahminSayisiInfo.setFont(new java.awt.Font("SansSerif", Font.BOLD, 14)); // NOI18N
        kazanilanOyunlardaTahminSayisiInfo.setText(".");

        yeniOyunButonu.setBackground(new java.awt.Color(51, 67, 100));
        yeniOyunButonu.setFont(new java.awt.Font("SansSerif", Font.BOLD, 16)); // NOI18N
        yeniOyunButonu.setForeground(new java.awt.Color(244, 244, 244));
        yeniOyunButonu.setText("Yeni Oyun");
        yeniOyunButonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                YeniOyunButonuActionPerformed(evt);
            }
        });

        devamEtButonu.setBackground(new java.awt.Color(51, 67, 100));
        devamEtButonu.setFont(new java.awt.Font("SansSerif", Font.BOLD, 16)); // NOI18N
        devamEtButonu.setForeground(new java.awt.Color(244, 244, 244));
        devamEtButonu.setText("Devam Et");
        devamEtButonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                devamEtButonuActionPerformed(evt);
            }
        });

    //    testButonu.setOpaque(true);
     //   testButonu.setBorderPainted(false);
        testButonu.setBackground(new java.awt.Color(51, 67, 100));
        testButonu.setFont(new java.awt.Font("SansSerif", Font.BOLD, 16)); // NOI18N
        testButonu.setForeground(new java.awt.Color(244, 244, 244));
        testButonu.setText("Test");
        testButonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testButonuActionPerformed(evt);
            }
        });

        footerText.setFont(new java.awt.Font("SansSerif", Font.BOLD, 16)); // NOI18N
        footerText.setText("2- 20011629 Barış Bakım - 20011906 Basel Kelziye");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(footerText)
                .addGap(85, 85, 85))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(kazanilanOyunSayisiLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(kazanilanOyunSayisiInfo))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(kaybedilanOyunSayisiLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(kaybedilenOyunSayisiInfo))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(yaridaBirakilanOyunSayisiLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(yaridaBirakilanOyunSayisiInfo))
                            .addComponent(istatistiklerLabel)
                            .addComponent(yaridaBirakilanOyunSayisiLabel3)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(kazanilanOyunlardaSureOrtalamasi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sureOrtalamasiInfo)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(testButonu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(devamEtButonu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(21, 21, 21))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(yeniOyunButonu, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(kazanilanOyunlardaTahminSayisiLabel)
                                .addGap(18, 18, 18)
                                .addComponent(kazanilanOyunlardaTahminSayisiInfo)))
                        .addGap(0, 21, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(istatistiklerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(yaridaBirakilanOyunSayisiInfo)
                    .addComponent(yaridaBirakilanOyunSayisiLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kaybedilanOyunSayisiLabel)
                    .addComponent(kaybedilenOyunSayisiInfo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kazanilanOyunSayisiLabel)
                    .addComponent(kazanilanOyunSayisiInfo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kazanilanOyunlardaTahminSayisiLabel)
                    .addComponent(kazanilanOyunlardaTahminSayisiInfo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(yaridaBirakilanOyunSayisiLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kazanilanOyunlardaSureOrtalamasi)
                    .addComponent(sureOrtalamasiInfo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(yeniOyunButonu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(devamEtButonu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(testButonu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(footerText)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void YeniOyunButonuActionPerformed(java.awt.event.ActionEvent evt) {

       OyunAraYuzu oyunAraYuzu = new OyunAraYuzu(true);
       oyunAraYuzu.display();
       this.dispose();
    }

    private void devamEtButonuActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        File f = new File("NerdleSave");
        if(!f.exists()){
            JOptionPane.showMessageDialog(null,"Devam Ettirelecek Oyun Bulunmamaktadır!","Hata", JOptionPane.ERROR_MESSAGE);
        }
        else{
            try{
                GameSave e = null;
                FileInputStream fileIn = new FileInputStream("NerdleSave");
                ObjectInputStream in =  new ObjectInputStream(fileIn);
                 e = (GameSave) in.readObject();
                 denklem = e.getDenklem();
                 in.close();
                 fileIn.close();

                 OyunAraYuzu aOyunAraYuzu = new OyunAraYuzu(e);
                 aOyunAraYuzu.display();
                 dispose();
                f.delete();
            }catch (IOException i){
                i.printStackTrace();
            }
            catch (ClassNotFoundException c){
                System.out.println("Oyunun Son Haline Erişmeye Çalışırken Hata Oldu Lütfen Tekrar Edin.");
                c.printStackTrace();
            }


        }

    }
    private void testButonuActionPerformed(java.awt.event.ActionEvent evt) {
            dispose();
            TestPage aTestPage = new TestPage();
            aTestPage.setVisible(true);
    }

    private javax.swing.JButton yeniOyunButonu;
    private javax.swing.JButton devamEtButonu;
    private javax.swing.JLabel footerText;
    private javax.swing.JLabel istatistiklerLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel kaybedilanOyunSayisiLabel;
    private javax.swing.JLabel kaybedilenOyunSayisiInfo;
    private javax.swing.JLabel kazanilanOyunSayisiInfo;
    private javax.swing.JLabel kazanilanOyunSayisiLabel;
    private javax.swing.JLabel kazanilanOyunlardaSureOrtalamasi;
    private javax.swing.JLabel kazanilanOyunlardaTahminSayisiInfo;
    private javax.swing.JLabel kazanilanOyunlardaTahminSayisiLabel;
    private javax.swing.JLabel sureOrtalamasiInfo;
    private javax.swing.JButton testButonu;
    private javax.swing.JLabel yaridaBirakilanOyunSayisiInfo;
    private javax.swing.JLabel yaridaBirakilanOyunSayisiLabel;
    private javax.swing.JLabel yaridaBirakilanOyunSayisiLabel3;
    // End of variables declaration//GEN-END:variables
}
