// 1- Pacote
package petstoremike;
// 2- Bibliotecas

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
// 3- Classe
public class Pet {
    // 3.1 - Atributos
  String uri = "https://petstore.swagger.io/v2/pet"; //endereco da entidade Pet

    // 3.2 - Metodos e funções

    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    // incluir - create - Post
    @Test (priority = 1)// identifica o método ou função como um teste para o TestNG
    public void incluirPet() throws IOException {
        String jsonBody = lerJson( "db/pet1.json");

        // Sintase Gherkin
        // Dado - Quando - Então
        // Given - When - Then

        given()
                .contentType("application/json") //comum em API Rest - antiogos era text/xml
                .log().all()
                .body(jsonBody)
                .when()
                .post(uri)
                .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Mike Wesley"))
                .body("status", is ("available"))
                .body("category.name", is ("dog"))
                .body("tags.name", contains("TesteApi"));


    }

 @Test (priority = 2)
    public void consultarPet(){
        String petId = "160120232225";

        given()

                .contentType("application/json")
                .log().all()
                .when()
                .get(uri + "/" + petId)
                .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Mike Wesley"))
                .body("category.name", is ("dog"))
                .body("status", is ("available"))

        ;





    }



}
