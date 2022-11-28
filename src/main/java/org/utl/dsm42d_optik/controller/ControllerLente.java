package org.utl.dsm42d_optik.controller;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.utl.dsm42d_optik.model.LenteContacto;
import org.utl.dsm42d_optik.model.Producto;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Base64;
import java.util.ResourceBundle;

public class ControllerLente implements Initializable {

    @FXML
    private ImageView imgLente;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtExistencias;

    @FXML
    private TextField txtPrecioCompra;

    @FXML
    private TextField txtTipo;

    @FXML
    private TextField txtPrecioVenta;

    @FXML
    private TextField txtMarca;

    @FXML
    private Button btnImg;

    @FXML
    private TextField txtKeratometria;

    @FXML
    private TableView<LenteContacto> tbLente;

    @FXML
    private TableColumn<LenteContacto, String> colNombre;

    @FXML
    private TableColumn<LenteContacto, String> colBarras;

    @FXML
    private TableColumn<LenteContacto, String> colMarca;

    @FXML
    private TableColumn<LenteContacto, Double> colPrecioCompra;

    @FXML
    private TableColumn<LenteContacto, Double> colPrecioVenta;

    @FXML
    private TableColumn<LenteContacto, Integer>colExistencias;

    @FXML
    private TableColumn<LenteContacto, String>colKerotometria;

    @FXML
    private TableColumn<LenteContacto, String> colTipo;

    @FXML
    private TableColumn<LenteContacto, String> colFotografia;

    @FXML
    private TableColumn<LenteContacto, Integer> colEstatus;

    String imgString = null;
    public void seleccionarImagen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            File file = new File(selectedFile.getAbsolutePath());
            // combertir el archivo a base64
            byte[] fileContent = null;
            try {
                fileContent = java.nio.file.Files.readAllBytes(file.toPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
            imgString = Base64.getEncoder().encodeToString(fileContent);
        } else {
            System.out.println("Archivo no válido");
        }
        assert selectedFile != null;
        imgLente.setImage(new javafx.scene.image.Image(selectedFile.toURI().toString()));
    }

    public void limpiarCampos() {
        imgString = null;
        txtNombre.setText("");
        txtMarca.setText("");
        txtPrecioCompra.setText("");
        txtPrecioVenta.setText("");
        txtExistencias.setText("");
        txtKeratometria.setText("");
        // en la imagen poner una imagen por defecto que esta en  resources/img/default.png
        imgLente.setImage(new javafx.scene.image.Image("C:/DAPPs/proyectoIntegrador/src/main/resources/img/default.png"));
    }

    public void guardarLente() {
        Producto producto = new Producto();
        LenteContacto lenteContacto = new LenteContacto();
        producto.setNombre(txtNombre.getText());
        producto.setMarca(txtMarca.getText());
        producto.setPrecioCompra(Double.parseDouble(txtPrecioCompra.getText()));
        producto.setPrecioVenta(Double.parseDouble(txtPrecioVenta.getText()));
        producto.setExistencias(Integer.parseInt(txtExistencias.getText()));
        lenteContacto.setKeratometria(txtKeratometria.getText());
        lenteContacto.setTipo(txtTipo.getText());
        lenteContacto.setFotografia(imgString);
        lenteContacto.setProducto(producto);
        // convertir objeto a json con Gson
        Gson gson = new Gson();
        String json = gson.toJson(lenteContacto);

        try{
            System.out.println(json);
            Unirest.post("http://localhost:8080/Optik/api/lenteContacto/guardar")
                    .field("datosLenteContacto", json)
                    .asJson();
            getLentes();
            limpiarCampos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<LenteContacto> getLentes(){
        try{
            HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/Optik/api/lenteContacto/getalllente")
                    .field("estatus", "1")
                    .asJson();
            Gson gson = new Gson();
            LenteContacto[] lentes = gson.fromJson(response.getBody().toString(), LenteContacto[].class);
            listaLentes.addAll(Arrays.asList(lentes));
            //actualizar la tabla
            tbLente.setItems(listaLentes);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return listaLentes;
    }

    ObservableList<LenteContacto> listaLentes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colNombre.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getProducto().getNombre()));
        colBarras.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getProducto().getCodigoBarras()));
        colMarca.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getProducto().getMarca()));
        colExistencias.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getProducto().getExistencias()));
        colPrecioCompra.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getProducto().getPrecioCompra()));
        colPrecioVenta.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getProducto().getPrecioVenta()));
        colKerotometria.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getKeratometria()));
        colTipo.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getTipo()));
        //en la columna de estatus poner un checkbox
        colEstatus.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getProducto().getEstatus()));
        listaLentes  = FXCollections.observableArrayList();
        listaLentes = getLentes();
        tbLente.setItems(listaLentes);

    }

    public void cargarForm(){
        //cargar el formulario con los datos del objeto seleccionado
        txtKeratometria.setText(tbLente.getSelectionModel().getSelectedItem().getKeratometria());
        txtTipo.setText(tbLente.getSelectionModel().getSelectedItem().getTipo());
        txtNombre.setText(tbLente.getSelectionModel().getSelectedItem().getProducto().getNombre());
        txtMarca.setText(tbLente.getSelectionModel().getSelectedItem().getProducto().getMarca());
        txtPrecioCompra.setText(String.valueOf(tbLente.getSelectionModel().getSelectedItem().getProducto().getPrecioCompra()));
        txtPrecioVenta.setText(String.valueOf(tbLente.getSelectionModel().getSelectedItem().getProducto().getPrecioVenta()));
        txtExistencias.setText(String.valueOf(tbLente.getSelectionModel().getSelectedItem().getProducto().getExistencias()));
        imgLente.setImage(new javafx.scene.image.Image(tbLente.getSelectionModel().getSelectedItem().getFotografia()));
    }
}
