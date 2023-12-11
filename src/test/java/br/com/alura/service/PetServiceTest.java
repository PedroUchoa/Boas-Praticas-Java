package br.com.alura.service;

import br.com.alura.client.ClienHttpRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @Mock
    private ClienHttpRequest client;

    @Mock
    private HttpResponse<String> response;

    @InjectMocks
    private PetService petService;


    @Test
    void listarPetsDoAbrigo() {
    }

    @Test
    @DisplayName("Deve fazer uma requisicao POST com sucesso")
    void importarPetsDoAbrigoSuccess() throws IOException, InterruptedException {
        String userInput = String.format("Abrigo%spets.csv",
                System.lineSeparator());
        System.out.println(userInput);
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        when(client.postHttp(anyString(), any())).thenReturn(response);

        petService.importarPetsDoAbrigo();
        verify(client.postHttp(anyString(), anyString()), atLeast(1));


    }
}