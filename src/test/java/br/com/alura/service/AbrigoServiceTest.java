package br.com.alura.service;

import br.com.alura.client.ClienHttpRequest;
import br.com.alura.domain.Abrigo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AbrigoServiceTest {

    @Mock
    private ClienHttpRequest client;

    @Mock
    private HttpResponse<String> response;

    @InjectMocks
    private AbrigoService abrigoService;

    @Test
    @DisplayName("Deve verificar quando ha abrigos cadastrados")
    void listarAbrigosCadastradosSucces() throws IOException, InterruptedException {
        Abrigo abrigo = buildMockAbrigo();
        abrigo.setId(0L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        when(response.body()).thenReturn("[{"+abrigo.toString()+"}]");
        when(client.getHttp(anyString())).thenReturn(response);

        abrigoService.ListarAbrigosCadastrados();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actualAbrigosCadastrados = lines[0];
        String actualIdENome = lines[1];

        Assertions.assertEquals("Abrigos cadastrados:",actualAbrigosCadastrados);
        Assertions.assertEquals("0 - Abrigo", actualIdENome);
    }

    @Test
    @DisplayName("Deve verificar quando nao ha abrigos cadastrados")
    void listarAbrigosCadastradosFail() throws IOException, InterruptedException {
        Abrigo abrigo = buildMockAbrigo();
        abrigo.setId(0L);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        when(response.body()).thenReturn("[]");
        when(client.getHttp(anyString())).thenReturn(response);

        abrigoService.ListarAbrigosCadastrados();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actualAbrigosCadastrados = lines[0];

        Assertions.assertEquals("Nenhum abrigo cadastrado",actualAbrigosCadastrados);

    }


    private Abrigo buildMockAbrigo(){
        Abrigo abrigo = new Abrigo();
        abrigo.setNome("Abrigo");
        abrigo.setEmail("teste@gmail.com");
        abrigo.setTelefone("61891880392");
        return abrigo;
    }


}