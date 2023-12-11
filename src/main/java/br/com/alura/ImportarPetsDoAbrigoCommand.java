package br.com.alura;

import br.com.alura.client.ClienHttpRequest;
import br.com.alura.service.AbrigoService;
import br.com.alura.service.PetService;

import java.io.IOException;

public class ImportarPetsDoAbrigoCommand implements Command{
    @Override
    public void Execute() {
        try {
            ClienHttpRequest request = new ClienHttpRequest();
            PetService petService = new PetService(request);
            petService.importarPetsDoAbrigo();
        }catch (IOException | InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
