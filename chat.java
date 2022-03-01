import java.util.Scanner;

public class chat {
    public static void main(String []args){
        Scanner scan = new Scanner(System.in);
        dialog dia = new dialog();
        dia.initial();
        while(true){
            String question = scan.nextLine();
            if (question.equals("quit")){
                System.out.println("I'm glad to serve you. See you next time!");
                break;
            }
            String result = dia.answer(question);
            System.out.println(result);
        }
    }
}
