package backend;

import frontend.MenuPage;
import frontend.WinPage;
import frontend.Istatistikler;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WinPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WinPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WinPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WinPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        File f = new File("IstatistiklerDosyasi");
        if(f.exists()){
            MenuPage menuPage = new MenuPage(new Istatistikler());
            menuPage.setVisible(true);
        }
        else{
            MenuPage menuPage =  new MenuPage();
            menuPage.setVisible(true);
        }





    }

}


