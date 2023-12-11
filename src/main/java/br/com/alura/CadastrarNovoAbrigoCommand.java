package br.com.alura;

import br.com.alura.client.ClienHttpRequest;
import br.com.alura.service.AbrigoService;

import java.io.IOException;

public class CadastrarNovoAbrigoCommand implements Command{
    @Override
    public void Execute() {
        try {
            ClienHttpRequest request = new ClienHttpRequest();
            AbrigoService abrigoService = new AbrigoService(request);

            abrigoService.CadastrarNovoAbrigo();
        }catch (IOException | InterruptedException e){
            System.out.println(e.getMessage());
        }

    }
}
