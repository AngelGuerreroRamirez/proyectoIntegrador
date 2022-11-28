package org.utl.dsm42d_optik;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerPrincipal implements Initializable {
    @FXML
    private TableColumn<Cliente, String> trApeMaterno;

    @FXML
    private TableColumn<Cliente, String> trApePaterno;

    @FXML
    private TableColumn<Cliente, String> trCliente;

    @FXML
    private TableColumn<Cliente, String> trGenero;

    @FXML
    private TableColumn<Cliente, String> trNombre;


    @FXML
    private TableColumn<Cliente, String> trTelCasa;

    @FXML
    private TableColumn<Cliente, String> trTelMovil;
    @FXML
    private TableColumn<Cliente, String> trCorreo;

    @FXML
    private TableColumn<Cliente, String> trRTC;
    @FXML
    private ToggleGroup genero;

    @FXML
    private RadioButton rFemenino;

    @FXML
    private RadioButton rMasculino;

    @FXML
    private RadioButton rOtro;

    @FXML
    private TableView<Cliente> tb;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtApellido1;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtRFC;

    @FXML
    private TextField txtTelCasa;

    @FXML
    private TextField txtTelMovil;


    //arrayList de tipo Empleado


    //saber que RadioButton esta seleccionado
    public String seleccionar() {
        if (rFemenino.isSelected()) {
            return "Femenino";
        }
        if (rMasculino.isSelected()) {
            return "Masculino";
        }
        return "Otro";
    }
    //inicializar mejor en el initialize
    ObservableList<Cliente> datos = FXCollections.observableArrayList();
    //contador para el id
    int contador = 0;
    //metodo para agregar empleados
    public void agregar() {
        Cliente cliente = new Cliente();
        cliente.setIdEmpleado(contador);
        cliente.setNombre(txtNombre.getText());
        cliente.setApellidoPaterno(txtApellido.getText());
        cliente.setApellidoMaterno(txtApellido1.getText());
        cliente.setGenero(seleccionar());
        cliente.setTelCasa(txtTelCasa.getText());
        cliente.setTelMovil(txtTelMovil.getText());
        cliente.setRfc(txtRFC.getText());
        cliente.setNumeroUnico("123456789");
        cliente.setEstatus(1);
        cliente.setCorreo(txtCorreo.getText());
        datos.add(cliente);
        contador++;
        tb.setItems(datos);
        limpiar();
    }

    public void limpiar() {
        txtApellido.clear();
        txtApellido1.clear();
        txtCorreo.clear();
        txtRFC.clear();
        txtNombre.clear();
        txtTelCasa.clear();
        txtTelMovil.clear();
        rMasculino.setSelected(true);
    }

    public void mostrar() {
        /*
        trCliente.setCellValueFactory(new PropertyValueFactory<>("idEmpleado"));
        trNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        trApePaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        trApeMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
        trGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        trTelCasa.setCellValueFactory(new PropertyValueFactory<>("telCasa"));
        trTelMovil.setCellValueFactory(new PropertyValueFactory<>("telMovil"));
        trCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        trRTC.setCellValueFactory(new PropertyValueFactory<>("rfc"));
        */


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mostrar();
    }


    //metodo para editar los datos de la tabla y del arrayList
    boolean subir = true;
    @FXML
    public void editarCliente(ActionEvent e) {
        int index = tb.getSelectionModel().getSelectedIndex();
        var genero = trGenero.getCellData(index);
        if (subir) {
            //poner los datos de la tabla en los textfield
            txtNombre.setText(trNombre.getCellData(index));
            txtApellido.setText(trApePaterno.getCellData(index));
            txtApellido1.setText(trApeMaterno.getCellData(index));
            txtRFC.setText(trRTC.getCellData(index));
            txtTelCasa.setText(trTelCasa.getCellData(index));
            txtTelMovil.setText(trTelMovil.getCellData(index));
            txtCorreo.setText(trCorreo.getCellData(index));
            if (genero.equals("Masculino")) {
                rMasculino.setSelected(true);
            }
            if (genero.equals("Femenino")) {
                rFemenino.setSelected(true);
            }
            if (genero.equals("Otro")) {
                rOtro.setSelected(true);
            }
            // cambiar el valor de la bandera
            subir = false;
            return;
        }
        //actualizar los datos del observableList
        datos.get(index).setNombre(txtNombre.getText());
        datos.get(index).setApellidoPaterno(txtApellido.getText());
        datos.get(index).setApellidoMaterno(txtApellido1.getText());
        datos.get(index).setGenero(seleccionar());
        datos.get(index).setTelCasa(txtTelCasa.getText());
        datos.get(index).setTelMovil(txtTelMovil.getText());
        datos.get(index).setRfc(txtRFC.getText());
        datos.get(index).setCorreo(txtCorreo.getText());
        limpiar();
        //actualizar la tabla
        tb.refresh();
        tb.setItems(datos);
        //cambiar el valor de la bandera
        subir = true;
    }

    public void eliminarCliente(){
        int index = tb.getSelectionModel().getSelectedIndex();
        tb.getItems().remove(index);
    }
}

