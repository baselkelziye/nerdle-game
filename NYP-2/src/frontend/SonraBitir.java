package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import backend.Denklem;


public class SonraBitir implements  ActionListener, java.io.Serializable {
    JFrame frame1 =  new JFrame();



    public SonraBitir(JFrame frameFromOyunArayuzu){
        initComponents();
    }

    public void initComponents(){

        frame1.setTitle("Sonra Bitir");
        JPanel panelleriToplayanPanel =  new JPanel();
        panelleriToplayanPanel.setBackground(new Color(236,254,255));

        JPanel anaPanel =  new JPanel(new GridLayout(2,1));


        JPanel ustPanel = new JPanel();
        JLabel sonraDevamBitirMesaji = new JLabel("Sonra Bitirmek İstediğinize Emin Misiniz?");
        sonraDevamBitirMesaji.setFont(new Font("SansSerif", Font.BOLD,18));
        ustPanel.add(sonraDevamBitirMesaji);
        ustPanel.setBackground(new Color(246,250,255));

        JPanel altPanel = new JPanel();
        altPanel.setBackground(new Color(246,250,255));

        JButton evetButonu = new JButton("Evet");


        evetButonu.setForeground(new Color(244,244,244));
        evetButonu.setBackground(new Color(51,67,100));
        evetButonu.setFont( new Font("SansSerif",Font.BOLD,14));
        evetButonu.setSelected(false);
        evetButonu.addActionListener(this);


        JButton hayirButonu = new JButton("Hayir");
        hayirButonu.setForeground(new Color(244,244,244));
        hayirButonu.setBackground(new Color(51,67,100));
        hayirButonu.setFont(new Font("SansSerif",Font.BOLD,14));
        hayirButonu.setSelected(false);
        hayirButonu.addActionListener(this);


        altPanel.add(evetButonu);
        altPanel.add(hayirButonu);

        anaPanel.add(ustPanel);
        anaPanel.add(altPanel);
        panelleriToplayanPanel.add(anaPanel);

        frame1.add(panelleriToplayanPanel);

        frame1.pack();
        frame1.setLocationRelativeTo(null);

        frame1.setVisible(true);
    }


    public void display(){
        initComponents();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
            JButton tmpButon =  (JButton) e.getSource();
            if(tmpButon.getText().equals("Evet")){
                        if(Denklem.getFinalEquation() == null){
                            try{
                                GameSave aGameSave = new GameSave(OyunAraYuzu.getBirDenklemSatri(), MenuPage.getDenklem(),MenuPage.getDenklem().length(),OyunAraYuzu.getMinutes(), OyunAraYuzu.getSeconds(), OyunAraYuzu.getTahminSayisi());
                                FileOutputStream fileout = new FileOutputStream("NerdleSave");
                                ObjectOutputStream out = new ObjectOutputStream(fileout);
                                out.writeObject(aGameSave);

                            }catch (IOException i){
                                i.printStackTrace();
                            }

                        }

                        else{


                try{
                    GameSave aGameSave = new GameSave(OyunAraYuzu.getBirDenklemSatri(), Denklem.getFinalEquation(),Denklem.getFinalEquationUzunlugu(),OyunAraYuzu.getMinutes(), OyunAraYuzu.getSeconds(), OyunAraYuzu.getTahminSayisi());
                    FileOutputStream fileout = new FileOutputStream("NerdleSave");
                    ObjectOutputStream out = new ObjectOutputStream(fileout);
                    out.writeObject(aGameSave);
                    System.out.println("Game Saved Successfuly!");

                }catch (IOException i){
                    i.printStackTrace();
                }
                        }

                frame1.dispose();
                OyunAraYuzu.frameiKapat();


                try{
                    Istatistikler istatistikler;
                    FileInputStream fileIn = new FileInputStream("IstatistiklerDosyasi");
                    ObjectInputStream in =  new ObjectInputStream(fileIn);
                    istatistikler = (Istatistikler) in.readObject();
                    //istatistikler.setBasarisiz(istatistikler.getBasarisiz()+1);
                    istatistikler.setYaridaBirakilan(istatistikler.getYaridaBirakilan()+1);

                    FileOutputStream fileout = new FileOutputStream("IstatistiklerDosyasi");
                    ObjectOutputStream out = new ObjectOutputStream(fileout);
                    out.writeObject(istatistikler);

                }catch (IOException i){
                    i.printStackTrace();
                }
                catch (ClassNotFoundException c){
                    System.out.println("Oyunun Son Haline Erişmeye Çalışırken Hata Oldu Lütfen Tekrar Edin.");
                    c.printStackTrace();
                }


                MenuPage menuPage =  new MenuPage(new Istatistikler());
                menuPage.setVisible(true);
            }
            else{
                OyunAraYuzu.startTimer();
                frame1.dispose();

            }
    }


}
