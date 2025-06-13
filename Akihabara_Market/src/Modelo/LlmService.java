package Modelo;

import java.io.InputStream;
import java.net.URI;
import java.net.http.*;
import java.util.*;

import com.google.gson.*;

public class LlmService {
    private final String apiKey;

    public LlmService() {
        this.apiKey = cargarApiKey();
        if (this.apiKey == null || this.apiKey.isEmpty()) {
            System.err.println(" ERROR: No se pudo cargar la API Key. Asegúrate de que 'config.properties' está en /src y contiene OPENROUTER_API_KEY.");
        } else {
            System.out.println(" API Key cargada correctamente.");
        }
    }

    private String cargarApiKey() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.err.println(" config.properties no encontrado en el classpath.");
                return null;
            }
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty("OPENROUTER_API_KEY");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String consultarLLM(String prompt) {
        if (apiKey == null || apiKey.isEmpty()) {
            return " No se ha configurado la API Key correctamente.";
        }

        try {
            HttpClient client = HttpClient.newHttpClient();

            String json = new Gson().toJson(Map.of(
                "model", "mistralai/mistral-7b-instruct:free",
                "messages", List.of(Map.of("role", "user", "content", prompt))
            ));

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://openrouter.ai/api/v1/chat/completions"))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Imprimir la respuesta (para depurar si es necesario)
            System.out.println(" Respuesta completa:\n" + response.body());

            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonArray choices = jsonObject.getAsJsonArray("choices");

            if (choices == null || choices.size() == 0) {
                return " La respuesta del modelo no contiene sugerencias válidas.";
            }

            return choices.get(0).getAsJsonObject()
                          .getAsJsonObject("message")
                          .get("content").getAsString();

        } catch (Exception e) {
            e.printStackTrace();
            return " Error al comunicarse con el modelo LLM.";
        }
    }
}
