/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifrador;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author Gabriel
 */
public final class Cifrador {
    private final Map tabelaFrequencias = new HashMap<>();
    private final List listaFrequencias = new ArrayList();
    private Map novaTabelaFrequencia;
    private List novaListaFrequencias;
    private List novaListaComValuesOrdenados;
    private char[] chave;
    
    private final char[] universo = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
    'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
    'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7',
    '8', '9'};
    
    public Cifrador() {
        gerarTabelaFrequencia();
    }
    
    public char[] gerarVetorChave(String mensagem) {
        //Faz a chave ser do mesmo tamanho que o tanto de caractere que tem pra cifrar
        chave = new char[mensagem.length()];
        Random random = new Random();
        for (int i = 0; i < chave.length; i++) {
            chave[i] = (char) random.nextInt(61);
        }
        return chave;
    }
    
    public String cifraOuDecifra(String mensagem, char[] chave, boolean isCifra) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < mensagem.length(); i++) {
            char cifrado = (char) (mensagem.charAt(i) ^ universo[chave[i]]);
            sb.append(cifrado);
        }
        
        if(isCifra) {
            System.out.println("\nMENSAGEM CIFRADA:");
        } else {
            System.out.println("\nMENSAGEM DECIFRADA:");
        }
        
        System.out.println(sb.toString());
        return sb.toString();
    }
    
    public String trataMensagem(String mensagem) {
        StringBuilder sb = new StringBuilder();
        
        StringBuilder mensagemTratada = new StringBuilder();
        
        for (int i = 0; i < mensagem.length(); i++) {
            switch (mensagem.charAt(i)) {
                //ESPAÇO
                case 32:
                    sb.append(mensagem.charAt(i));
                    break;
                //PONTO .
                case 46:
                    sb.append(mensagem.charAt(i));
                    break;
                //VIRGULA ,
                case 44:
                    sb.append(mensagem.charAt(i));
                    break;
                //ENTER \n
                case 10:
                    sb.append("\r\n");
                    break;
                default:
                    mensagemTratada.append(mensagem.charAt(i));
                    sb.append(mensagem.charAt(i));
            }
        }
        System.out.println(sb.toString());
        System.out.println("MENSAGEM TRATADA PARA USAR NA CHAVE: ");
        System.out.println(mensagemTratada.toString());
        
        return mensagemTratada.toString();
    }
    
    public void analisarCifra(String mensagem) {
        novaTabelaFrequencia = new HashMap<>();
        novaListaFrequencias = new ArrayList();
        novaListaComValuesOrdenados = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int totalApareceu = 0;
        double porcentagemApareceu = 0;
        int totalCaracteres = 0;
        BigDecimal porcentagemFormatada;
        
        char[] caracteresMensagem = mensagem.toCharArray();
        
        for (int i = 0; i < caracteresMensagem.length; i++) {
            
            switch (caracteresMensagem[i]) {
                //ESPAÇO
                case 32:
                    sb.append(caracteresMensagem[i]);
                    break;
                //PONTO .
                case 46:
                    sb.append(caracteresMensagem[i]);
                    break;
                //VIRGULA ,
                case 44:
                    sb.append(caracteresMensagem[i]);
                    break;
                //ENTER \n
                case 10:
                    sb.append(caracteresMensagem[i]);
                    break;
                default:
                    //Se ja existe no hashmap, remove e coloca de novo e soma um ao valor
                    if (novaTabelaFrequencia.containsKey(Character.toUpperCase(caracteresMensagem[i]))) {
                        totalApareceu = (int) novaTabelaFrequencia.get(Character.toUpperCase(caracteresMensagem[i])) + 1;
                        novaTabelaFrequencia.remove(caracteresMensagem[i]);
                        novaTabelaFrequencia.put(Character.toUpperCase(caracteresMensagem[i]), totalApareceu);
                        totalCaracteres++;
                        //Se nao, só coloca no hashmap com valor 1
                    } else {
                        novaTabelaFrequencia.put(Character.toUpperCase(caracteresMensagem[i]), 1);
                        totalCaracteres++;
                    }
                    break;
            }
        }
        
        //Lista de ocorrencias ordenadas para comparar
        novaListaComValuesOrdenados.addAll(novaTabelaFrequencia.values());
        Collections.sort(novaListaComValuesOrdenados, Collections.reverseOrder());
        
        //Calcular a porcentagem
        Iterator it = novaTabelaFrequencia.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            int frequenciaLetra = (int) pairs.getValue();
            porcentagemApareceu = (double) Math.round(frequenciaLetra * 100) / totalCaracteres;
            porcentagemFormatada = new BigDecimal(porcentagemApareceu);
            porcentagemApareceu = porcentagemFormatada.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            novaListaFrequencias.add(porcentagemApareceu);
        }
        Collections.sort(novaListaFrequencias, Collections.reverseOrder());
            
        System.out.println("\nLISTA DE FREQUENCIA TEXTO FECHADO: (OCORRENCIAS)");
        System.out.println(novaTabelaFrequencia);
    }
    
    //To chamando no construtor, entao sempre vai ter
    public void gerarTabelaFrequencia() {
        tabelaFrequencias.put('A', 14.63);
        tabelaFrequencias.put('B', 1.04);
        tabelaFrequencias.put('C', 3.88);
        tabelaFrequencias.put('D', 4.99);
        tabelaFrequencias.put('E', 12.57);
        tabelaFrequencias.put('F', 1.02);
        tabelaFrequencias.put('G', 1.30);
        tabelaFrequencias.put('H', 1.28);
        tabelaFrequencias.put('I', 6.18);
        tabelaFrequencias.put('J', 0.40);
        tabelaFrequencias.put('K', 0.02);
        tabelaFrequencias.put('L', 2.78);
        tabelaFrequencias.put('M', 4.74);
        tabelaFrequencias.put('N', 5.05);
        tabelaFrequencias.put('O', 10.73);
        tabelaFrequencias.put('P', 2.52);
        tabelaFrequencias.put('Q', 1.20);
        tabelaFrequencias.put('R', 6.53);
        tabelaFrequencias.put('S', 7.81);
        tabelaFrequencias.put('T', 4.34);
        tabelaFrequencias.put('U', 4.63);
        tabelaFrequencias.put('V', 1.67);
        tabelaFrequencias.put('W', 0.01);
        tabelaFrequencias.put('X', 0.21);
        tabelaFrequencias.put('Y', 0.01);
        tabelaFrequencias.put('Z', 0.47);
        
        System.out.println("\nLISTA DE FREQUENCIA REFERENCIA");
        System.out.println(tabelaFrequencias);
        
        listaFrequencias.addAll(tabelaFrequencias.values());
        Collections.sort(listaFrequencias, Collections.reverseOrder());
    }
    
    public void imprimirListaReferencia() {
        System.out.println("\nLISTA DE FREQUENCIA REFERENCIA ORDENADA: ");
        System.out.println(listaFrequencias);
    }
    
    public void imprimirListaTextoFechado() {
        System.out.println("\nLISTA DE FREQUENCIA TEXTO FECHADO ORDENADA: ");
        System.out.println(novaListaFrequencias);
    }
    
    //Pegar a chave através do valor
    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}

