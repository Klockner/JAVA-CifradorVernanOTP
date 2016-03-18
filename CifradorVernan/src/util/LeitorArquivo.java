/**
 *
 */
package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Gabriel Klockner
 */
public class LeitorArquivo {
    
    
    public String lerArquivo() {
        Scanner ler = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        System.out.println("Informe o nome do arquivo de texto:");
        String nome = ler.nextLine();

        System.out.println("CONTEUDO DO ARQUIVO:\n");
        try {
            FileReader arq = new FileReader(nome);
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();

            while (linha != null) {
                sb.append(linha).append("\n");

                linha = lerArq.readLine();
            }
            
            System.out.println(sb.toString());
            
            arq.close();
            ler.close();
        } catch (IOException e) {
            System.err.printf("Erro ao abrir arquivo: %s. \n", e.getMessage());
        }
        
        return sb.toString();
    }
    
    public void criarArquivo(String conteudo, int opcao) {
        try {
            switch(opcao) {
                //Cifra
                case 0:
                    try (FileWriter fileWriter = new FileWriter("texto-fechado.txt", false)) {
                        fileWriter.write(conteudo);
                    }
                    break;
                //Decripto
                case 1:
                    try (FileWriter fileWriter = new FileWriter("texto-aberto.txt", false)) {
                        fileWriter.write(conteudo);
                    }
                    break;
                //Gerar arquivo de chave
                case 2:
                    try (FileWriter fileWriter = new FileWriter("chave.dat", false)) {
                        fileWriter.write(conteudo);
                    }
                    break;
                default:
            }
        } catch (IOException e) {
            System.out.println(e.getMessage()); 
       }
    }
}
