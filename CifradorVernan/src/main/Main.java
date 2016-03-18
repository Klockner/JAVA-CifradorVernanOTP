/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import cifrador.Cifrador;
import util.LeitorArquivo;

/**
 *
 * @author Gabriel
 */
public class Main {
    public static void main(String[] args) {
        LeitorArquivo leitorArquivo = new LeitorArquivo();
        String mensagem = leitorArquivo.lerArquivo();
        
        Cifrador cifrador = new Cifrador();
//        String mensagemTratada = cifrador.trataMensagem(mensagem);
        
        char[] chave = cifrador.gerarVetorChave(mensagem);
        //2 para criar Chave.dat
        leitorArquivo.criarArquivo(String.valueOf(chave), 2);
        
        //True para cifrar
        String mensagemCifrada = cifrador.cifraOuDecifra(mensagem, chave, true);
        //0 para criar arquivo com mensagem cifrada
        leitorArquivo.criarArquivo(mensagemCifrada, 0);
        //False para decifrar
        cifrador.cifraOuDecifra(mensagemCifrada, chave, false);
    }
}
