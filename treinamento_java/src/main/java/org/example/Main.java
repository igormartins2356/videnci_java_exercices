package org.example;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static void firstExercise() {
        System.out.println("Digite a uma string: ");
        Scanner teclado = new Scanner(System.in);
        String str = teclado.nextLine();
        String r_str = new StringBuilder(str).reverse().toString();
        System.out.println(r_str);
    }
    static void secondExercise() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Digite a primeira string: ");
        String str = teclado.nextLine();
        System.out.println("Digite a segunda string: ");
        String str2 = teclado.nextLine();
        StringBuffer str_r = new StringBuffer(str);
        str_r.append(str2);
        System.out.println(str_r.toString());
    }
    static void thirdExercise(){
        int option = 1;
        List<String> saladaDeFrutas = new ArrayList<>();
        System.out.println("Salada de frutas!");
        while(option > 0){
            System.out.println("Escolha uma opção: ");
            System.out.println("1: Adicionar uma fruta ");
            System.out.println("2: Remover uma fruta ");
            System.out.println("3: Listar frutas ");
            System.out.println("-1: Sair ");
            Scanner teclado = new Scanner(System.in);
            option = teclado.nextInt();
            switch(option) {
                case 1:
                    System.out.println("Adicione uma fruta a nossa salada de frutas: ");
                    Scanner teclado2 = new Scanner(System.in);
                    String str = teclado2.nextLine();
                    saladaDeFrutas.add(str);
                    break;
                case 2:
                    System.out.println("Remova uma fruta a nossa salada de frutas: ");
                    Scanner teclado3 = new Scanner(System.in);
                    String str2 = teclado3.nextLine();
                    int idx = saladaDeFrutas.indexOf(str2);
                    if(idx > -1){
                        saladaDeFrutas.remove(idx);
                    }else{
                        System.out.println("Fruta não encontrada!");
                    }
                    break;
                case 3:
                    System.out.println(saladaDeFrutas);
                    break;
                default:
                    // code block
            }
        }
    }
    public static void main(String[] args) {
        int option = 1;
        while(option > 0){
            System.out.println("Escolha um exercício do 1 ao 4: ");
            Scanner teclado = new Scanner(System.in);
            option = teclado.nextInt();
            switch(option) {
                case 1:
                    firstExercise();
                    break;
                case 2:
                    secondExercise();
                    break;
                case 3:
                    thirdExercise();
                    break;
                case 4:
                    // code block
                    break;
                default:
                    // code block
            }
        }
    }
}