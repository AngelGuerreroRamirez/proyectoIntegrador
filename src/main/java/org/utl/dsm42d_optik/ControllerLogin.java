package org.utl.dsm42d_optik;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControllerLogin implements Initializable {
    @FXML
    private Button btnExit;
    @FXML
    private Button btnLogin;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtUsername;
    public void validar() throws IOException {

        String user = txtUsername.getText();
        String pass = txtPassword.getText();
        if(user.equals("admin")){
            if(pass.equals("1234")){
               // Alert alerta = new Alert(Alert.AlertType.INFORMATION,"Correct Access");
                // alerta.show();
                Parent principal = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("Principal.fxml")));
                Scene scene = new Scene(principal);
                Stage ventaPrincipal = new Stage();
                ventaPrincipal.setScene(scene);
                ventaPrincipal.show();

                Stage ventana = (Stage) txtPassword.getScene().getWindow();
                ventana.close();
            }
            else{
                Alert alerta = new Alert(Alert.AlertType.ERROR,"Incorrect Password");
                alerta.show();
            }
        }else{
            Alert alerta = new Alert(Alert.AlertType.ERROR,"Incorrect User");
            alerta.show();
        }

    }
    public void cerrar(){
        System.exit(0);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtUsername.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){
                txtPassword.requestFocus();
            }
        });
        txtPassword.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){
                try {
                    this.validar();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    public void probarRest(){
        HttpResponse<JsonNode> apiResponse = null;
        Alert alerta = null;
        try {
            apiResponse = Unirest.get("http://localhost:8080/PrimerRest/api/saludo/saludar").asJson();
            alerta = new Alert(Alert.AlertType.INFORMATION,"Mensaje: "+apiResponse.getBody().getObject().getString("result"));
            //convertir json a objeto
            //Persona persona = new Gson().fromJson(apiResponse.getBody().getObject().getString("result"),Persona.class);
            alerta.show();

        } catch (UnirestException e) {
            alerta = new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
    }
}
