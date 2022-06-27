package backend;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DenklemTest {



    @Test
    void divisibleNumberGenerator() {

        int rand = new Random().nextInt(100);
        int BolunebilenSayi = Denklem.divisibleNumberGenerator(rand);
        assertEquals(0,rand%BolunebilenSayi);
    }


    @Test
    void getFinalEquation() {
        int esitSayisi = 0;
        Random rand = new Random();
        int lengthOfTheEquation = rand.nextInt(3) + 7;
        Denklem denklem1 = new Denklem();

        denklem1.infix2Postfix(denklem1.getDenklem());
        while(lengthOfTheEquation != denklem1.isAllowed(lengthOfTheEquation)){
            denklem1 = new Denklem();
            denklem1.infix2Postfix(denklem1.getDenklem());
        }
        String str = Denklem.getFinalEquation();
       // str = Denklem.getFinalEquation();
        for(int i =0; i < str.length(); i++){
          //  System.out.println(str.charAt(i));
            if(str.charAt(i) == '='){
                esitSayisi++;
            }
        }
        assertEquals(1,esitSayisi);

    }


    @Test
    void lengthIsValid(){
        Random rand = new Random();
        int lengthOfTheEquation = rand.nextInt(3) + 7;
        Denklem denklem1 = new Denklem();

        denklem1.infix2Postfix(denklem1.getDenklem());
        while(lengthOfTheEquation != denklem1.isAllowed(lengthOfTheEquation)){
            denklem1 = new Denklem();
            denklem1.infix2Postfix(denklem1.getDenklem());
        }
        boolean lengthValidation = false;
        if(Denklem.getFinalEquationUzunlugu()>=7 && Denklem.getFinalEquationUzunlugu() <= 9){
            lengthValidation = true;
        }
        assertTrue(lengthValidation);

    }

    @Test
    void isOperand() {
        Denklem aDenklem = new Denklem();
        boolean deger = aDenklem.isOperand('6');
        assertTrue(deger);
    }

    @Test
    void isOperator() {
        Denklem aDenklem = new Denklem();

        boolean deger = aDenklem.isOperator('+');
        assertTrue(deger);
        deger = aDenklem.isOperator('-');
        assertTrue(deger);

        deger = aDenklem.isOperator('/');
        assertTrue(deger);

        deger = aDenklem.isOperator('*');
        assertTrue(deger);
        deger = aDenklem.isOperator('4');
        assertFalse(false);
    }
}