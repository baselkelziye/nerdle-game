package backend;

import java.util.Stack;
import java.util.Random;

public class Denklem{
    private String equation;
    private String postfixDenklem = "";// intialiaze etmeyince null oluyor string ve bozulyor
    Stack<Character> karakterStack;
    static Random randomGenerator = new Random();
    static String operators = "+-*/";
    private static String finalEquation;

    private static int finalEquationUzunlugu;









    public Denklem(){
        karakterStack = new Stack<Character>();
        this.equation = createEquation();
    }


    public static char getRandomOperator() {
        return operators.charAt(randomGenerator.nextInt(operators.length()));
    }

    public static int getRandomNumber() {
        return randomGenerator.nextInt(20);
    }

    public void setDenklem(String equation) {
        this.equation = equation;
    }

    public String getDenklem() {
        return equation;
    }


    public static String getFinalEquation() {
        return finalEquation;
    }

    public  static int  getFinalEquationUzunlugu() {
        return finalEquationUzunlugu;
    }

    public String getPostfixDenklem() {
        return this.postfixDenklem;
    }


    public static String createEquation() { // denklemi uretir sayi islem sayi islem diye gidiyor islem sayimiz 1,2 ve 3 arasinda rastgele seciliyor

        int tmpRandomInt;
        char tmpRandomOperator;
        String equation = "";
        //int numOfOperators = 2;

        Random rand = new Random();
        int randomArray[] = new int [6];
        randomArray[0] = 1;
        randomArray[1] = 2;
        randomArray[2] = 3;
        randomArray[3] = 2;
        randomArray[4] = 1;
        randomArray[5] = 2;

        int numOfOperators =randomArray[ rand.nextInt(6)] ;

        equation += tmpRandomInt = getRandomNumber();

        for (int i = 0; i < numOfOperators; i++){
            equation +=tmpRandomOperator= getRandomOperator();
            equation += ' ';
            if(tmpRandomOperator == '/'){
                equation+= divisibleNumberGenerator(tmpRandomInt);
            }
            else{
                equation += tmpRandomInt = getRandomNumber();
            }
        }
        return equation;
    }
    public static int divisibleNumberGenerator(int number){ // denklemi uretirken "/" gelirse boldugumuz sayiya bolunen sayi uretip donderir
        //bolmek istedigimiz sayi alir parametresine. hem boylece divide by zero yu onlemis olduk.
        int array[] = new int [number];

        int index = 0;
        for(int i = 1; i <= number; i++){
            if(number%i == 0){
                array[index] = i;
                index++; // index yerine array.length den faydalanabilir
            }
        }
        if(number == 0){
            int rand = new Random().nextInt(19) + 1; // 0/ bisey geldiginde 0 disinda bir sayi uret
            return rand;
        }
        if(index == 0){
            return 1;
        }

        if(index == 1){
            return array[0];
        }

        int rnd = new Random().nextInt(index-1);// what happens if index is 0?
        return array[rnd];

    }
    public int getOperatorWeight(char a){
        if(a == '+' || a == '-'){
            return 1;
        }
        else if( a == '*' || a == '/'){
            return 2;
        }

        else{
            System.out.println("Unkown Operator");
            return -1;
        }
    }

    public boolean isOperand(char a){

        if(Character.isDigit(a)){
            return true;
        }
        else{
            return false;
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

    public String infix2Postfix(String inputString){ // alinan bir stringi postfix notasyonuna cevirir ve sonra degerini hesaplama icin baska fonksiyona yolluyoruz.
        // input olarak bizim olusturdugumuz denklemi alir
        for(int i = 0; i < inputString.length(); i++){

            if(isOperand(inputString.charAt(i)) ){

                postfixDenklem +=  inputString.charAt(i);
            }

            if( inputString.charAt(i) == ' '){
                postfixDenklem += ' ';
            }
            if(isOperator(inputString.charAt(i))){
                while(!karakterStack.isEmpty() && getOperatorWeight(karakterStack.peek()) >= getOperatorWeight(inputString.charAt(i)) ){
                    postfixDenklem +=  karakterStack.pop();
                }
                karakterStack.push(inputString.charAt(i));
            }


        }

        while(!karakterStack.isEmpty()){
            postfixDenklem +=  karakterStack.pop();
        }
        return postfixDenklem;
    }

    public int postfixEvaluation(String postfixDenklem){
        //postfix notasyonundaki bir denklemin degerini hesaplar.
        //buraya bir denklemin degerini hesaplariz ve urettigimiz denklemin sonuna "=" koyup burdan hesapladigimiz sonuca esitleriz.
        int value = 0;

        karakterStack.empty();

        for(int i =0; i < postfixDenklem.length(); i++){
            if(isOperand(postfixDenklem.charAt(i)) || postfixDenklem.charAt(i) == ' '){
                karakterStack.push(postfixDenklem.charAt(i));
            }
            else if(isOperator(postfixDenklem.charAt(i))){
                int tabanCevirici = 1;
                int op2 = 0,op1 = 0;
                int buSayiEksi = 0;
                if(!karakterStack.isEmpty() && karakterStack.peek() == '-'){
                    karakterStack.pop();
                    buSayiEksi = 1;
                }
                while((!karakterStack.isEmpty()) && karakterStack.peek() != ' ')
                {
                    op2 = op2 + tabanCevirici * (karakterStack.pop() - '0');
                    tabanCevirici = tabanCevirici *10;
                }
                if(buSayiEksi == 1){
                    op2 = -op2;
                    buSayiEksi = 0;
                }

                if(!karakterStack.isEmpty()){

                    karakterStack.pop(); // stacktaki ' ' i temizlemek icin.

                }

                if(!karakterStack.isEmpty() && karakterStack.peek() == '-'){
                    karakterStack.pop();
                    buSayiEksi = 1;
                }

                tabanCevirici = 1;

                while((!karakterStack.isEmpty()) && karakterStack.peek() != ' ')
                {

                    op1 = op1 + tabanCevirici* (karakterStack.pop() - '0');
                    tabanCevirici = tabanCevirici *10;

                }
                if(buSayiEksi == 1){
                    op1 = -op1;
                    buSayiEksi = 0;
                }

                switch(postfixDenklem.charAt(i)){
                    case '+':
                        value = op1 + op2;
                        break;
                    case '-':
                        value =  op1 - op2;
                        break;
                    case '*':
                        value = op1 * op2;
                        break;
                    case '/':
                        value = op1/op2;
                        break;
                    default:
                        System.out.println("Unkown Operation");
                        break;
                }
                String tmp;
                if(value < 0){
                    value = -value;
                    tmp = Integer.toString(value);
                    tmp = tmp + '-';
                }
                else{
                    tmp = Integer.toString(value);
                }

                for(int x = 0; x < tmp.length(); x++){
                    karakterStack.push(tmp.charAt(x));
                }

            }
            else{
                System.out.println("unvalid expression!");
            }
        }
        int tabanCevirici = 1;
        int buSayiEksi = 0;
        if(karakterStack.peek() == '-'){
            buSayiEksi = 1;
            karakterStack.pop();
        }
        value = 0;
        while(!karakterStack.isEmpty()){
            value = value + (karakterStack.pop()-'0') * tabanCevirici;
            tabanCevirici = tabanCevirici*10;
        }

        if(buSayiEksi == 1){
            value  = -value;
        }

        return value;

    }


    public int isAllowed(int allowedLength) { // burdaki denklemin son hali istenilen uzunlukta olup olmadigi kontrol edilir. (7-9)
        String finalEq = "";
        String denklem = getDenklem();
        Integer y = postfixEvaluation(postfixDenklem);
        int i = 0;
        while(i < denklem.length()) {
            if(denklem.charAt(i) != ' ') {
                finalEq += denklem.charAt(i);
            }
            i++;
        }
        finalEq += '=';
        finalEq += y.toString();
        finalEquation = finalEq;
        finalEquationUzunlugu = finalEquation.length();
        return finalEq.length();
    }




}