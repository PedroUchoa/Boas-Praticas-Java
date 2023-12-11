package br.com.alura.service;

import br.com.alura.client.ClienHttpRequest;
import br.com.alura.domain.Abrigo;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AbrigoService {

    ClienHttpRequest request;

    public AbrigoService(ClienHttpRequest request) {
        this.request = request;
    }

    public void ListarAbrigosCadastrados() throws IOException, InterruptedException {
        String uri = "http://localhost:8080/abrigos";
        HttpResponse<String> response = request.getHttp(uri);
        String responseBody = response.body();
        List<Abrigo> abrigos = Arrays.stream(new ObjectMapper().readValue(responseBody,Abrigo[].class)).toList();
        if (!abrigos.isEmpty()){
            System.out.println("Abrigos cadastrados:");
            for (Abrigo abrigo : abrigos) {
                long id = abrigo.getId();
                String nome = abrigo.getNome();
                System.out.println(id +" - " +nome);
            }
            return;
        }
        System.out.println("Nenhum abrigo cadastrado");
    }

    public void CadastrarNovoAbrigo() throws IOException, InterruptedException {
        System.out.println("Digite o nome do abrigo:");
        String nome = new Scanner(System.in).nextLine();
        System.out.println("Digite o telefone do abrigo:");
        String telefone = new Scanner(System.in).nextLine();
        System.out.println("Digite o email do abrigo:");
        String email = new Scanner(System.in).nextLine();

        Abrigo abrigo = new Abrigo(nome,telefone,email);

        String uri = "http://localhost:8080/abrigos";
        HttpResponse<String> response = request.postHttp(uri,abrigo);

        int statusCode = response.statusCode();
        String responseBody = response.body();
        if (statusCode == 200) {
            System.out.println("Abrigo cadastrado com sucesso!");
            System.out.println(responseBody);
        } else if (statusCode == 400 || statusCode == 500) {
            System.out.println("Erro ao cadastrar o abrigo:");
            System.out.println(responseBody);
        }
    }


}
