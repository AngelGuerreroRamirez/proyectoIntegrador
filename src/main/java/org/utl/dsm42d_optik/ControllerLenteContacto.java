package org.utl.dsm42d_optik;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.utl.dsm42d_optik.model.LenteContacto;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLenteContacto implements Initializable {
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
    private TableColumn<?, ?> colBarras;

    @FXML
    private TableColumn<?, ?> colMarca;

    @FXML
    private TableColumn<?, ?> colPrecioCompra;

    @FXML
    private TableColumn<?, ?> colPrecioVenta;

    @FXML
    private TableColumn<?, ?> colExistencias;

    @FXML
    private TableColumn<?, ?> txtKerotometria;

    @FXML
    private TableColumn<?, ?> colTipo;

    @FXML
    private TableColumn<?, ?> colFotografia;

    @FXML
    private TableColumn<?, ?> colEstatus;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colNombre.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getKeratometria()));

    }
}
