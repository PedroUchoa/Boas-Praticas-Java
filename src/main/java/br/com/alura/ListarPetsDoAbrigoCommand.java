package br.com.alura;

import br.com.alura.client.ClienHttpRequest;
import br.com.alura.service.PetService;

import java.io.IOException;

public class ListarPetsDoAbrigoCommand implements Command{
    @Override
    public void Execute() {
        try {
            ClienHttpRequest request = new ClienHttpRequest();
            PetService petService = new PetService(request);
            petService.listarPetsDoAbrigo();
        }catch (IOException | InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
