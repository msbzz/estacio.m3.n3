package util;

import java.io.IOException;
import java.util.Scanner;

public class Util {
    private String opcaoAcao;
    private String opcaoPessoa;
    private Scanner scanner = new Scanner(System.in);
    public Util(String opcaoPessoa, String opcaoAcao) {

        this.opcaoPessoa=opcaoPessoa;
        this.opcaoAcao=opcaoAcao;

    }

    public  boolean campoValido(String valor, String msg,int numLimite) {

        Boolean item1=false;
        Boolean item2=false;

        //verifica se a string passou o limite
        if(valor.trim().length()>numLimite){
            System.out.println(msg);
            clickMe();
            scanner.nextLine();
            item1= false;
        }else{
            item1=true;
        }

        //verifica se a string esta vazia
        if (valor.isEmpty()) {
            //caso seja alteração o valor original sera retornado
            item2= false;
            if (!opcaoAcao.equals("A")){
                System.out.println(msg);
                clickMe();
                scanner.nextLine();
             }
        }else{
            item2=true;
        }
        return (item1 && item2);
    }



    // mensagem de espera para apresentação de dados
    public void clickMe(){
        System.out.println("");
        System.out.println("tecle qualquer tecla para continuar.. ");
        try{
            System.in.read();

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public String inputDadosText(String msgTitulo,String msgErr,String valorOriginal,int numLimite) {
        boolean inloop = true;
        String sReturn="";
        while (inloop) {
            System.out.println(msgTitulo);
            sReturn = scanner.nextLine();
            if (!campoValido(sReturn, msgErr,numLimite)) {
                if (opcaoAcao.equals("A")) {
                    sReturn =valorOriginal;
                    return sReturn;
                }
            }else{
                inloop = false;
            }
        }
        return sReturn;
    }

}
