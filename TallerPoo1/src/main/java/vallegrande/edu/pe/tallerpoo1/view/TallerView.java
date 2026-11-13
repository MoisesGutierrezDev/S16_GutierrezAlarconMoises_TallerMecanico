package vallegrande.edu.pe.tallerpoo1.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vallegrande.edu.pe.tallerpoo1.model.Vehiculo;
import vallegrande.edu.pe.tallerpoo1.model.VehiculoDAO;

public class TallerView {

    private TextField txtPlaca = new TextField();
    private TextField txtMarca = new TextField();
    private TextField txtPropietario = new TextField();
    private TextField txtProblema = new TextField();
    private TableView<Vehiculo> tablaVehiculos = new TableView<>();

    private VehiculoDAO dao = new VehiculoDAO();
    private Vehiculo vehiculoSeleccionado = null;

    public Scene crearEscena() {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));

        Label titulo = new Label("Gestión de Taller Mecánico");
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Formulario
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        txtPlaca.setPromptText("Ej. ABC-123");
        txtMarca.setPromptText("Ej. Toyota");
        txtPropietario.setPromptText("Nombre del cliente");
        txtProblema.setPromptText("Falla reportada");

        grid.add(new Label("Placa:"), 0, 0);
        grid.add(txtPlaca, 1, 0);
        grid.add(new Label("Marca:"), 2, 0);
        grid.add(txtMarca, 3, 0);
        grid.add(new Label("Propietario:"), 0, 1);
        grid.add(txtPropietario, 1, 1);
        grid.add(new Label("Problema:"), 2, 1);
        grid.add(txtProblema, 3, 1);

        // Botones
        Button btnRegistrar = new Button("Registrar");
        btnRegistrar.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");
        btnRegistrar.setOnAction(e -> registrar());

        Button btnActualizar = new Button("Actualizar");
        btnActualizar.setStyle("-fx-background-color: #ffc107;");
        btnActualizar.setOnAction(e -> actualizar());

        Button btnEliminar = new Button("Eliminar");
        btnEliminar.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white;");
        btnEliminar.setOnAction(e -> eliminar());

        Button btnLimpiar = new Button("Limpiar Campos");
        btnLimpiar.setOnAction(e -> limpiar());

        HBox botones = new HBox(10, btnRegistrar, btnActualizar, btnEliminar, btnLimpiar);

        // Configurar Tabla
        TableColumn<Vehiculo, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colId.setPrefWidth(50);

        TableColumn<Vehiculo, String> colPlaca = new TableColumn<>("Placa");
        colPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        colPlaca.setPrefWidth(100);

        TableColumn<Vehiculo, String> colMarca = new TableColumn<>("Marca");
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colMarca.setPrefWidth(120);

        TableColumn<Vehiculo, String> colPropietario = new TableColumn<>("Propietario");
        colPropietario.setCellValueFactory(new PropertyValueFactory<>("propietario"));
        colPropietario.setPrefWidth(180);

        TableColumn<Vehiculo, String> colProblema = new TableColumn<>("Problema");
        colProblema.setCellValueFactory(new PropertyValueFactory<>("problema"));
        colProblema.setPrefWidth(250);

        tablaVehiculos.getColumns().addAll(colId, colPlaca, colMarca, colPropietario, colProblema);

        tablaVehiculos.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                vehiculoSeleccionado = newSel;
                txtPlaca.setText(newSel.getPlaca());
                txtMarca.setText(newSel.getMarca());
                txtPropietario.setText(newSel.getPropietario());
                txtProblema.setText(newSel.getProblema());
            }
        });

        cargarTabla();

        root.getChildren().addAll(titulo, grid, botones, tablaVehiculos);
        return new Scene(root, 850, 500);
    }

    private void cargarTabla() {
        ObservableList<Vehiculo> lista = FXCollections.observableArrayList(dao.obtenerVehiculos());
        tablaVehiculos.setItems(lista);
    }

    private void registrar() {
        Vehiculo v = new Vehiculo();
        v.setPlaca(txtPlaca.getText());
        v.setMarca(txtMarca.getText());
        v.setPropietario(txtPropietario.getText());
        v.setProblema(txtProblema.getText());
        dao.registrarVehiculo(v);
        cargarTabla();
        limpiar();
    }

    private void actualizar() {
        if (vehiculoSeleccionado != null) {
            vehiculoSeleccionado.setPlaca(txtPlaca.getText());
            vehiculoSeleccionado.setMarca(txtMarca.getText());
            vehiculoSeleccionado.setPropietario(txtPropietario.getText());
            vehiculoSeleccionado.setProblema(txtProblema.getText());
            dao.actualizarVehiculo(vehiculoSeleccionado);
            cargarTabla();
            limpiar();
        }
    }

    private void eliminar() {
        if (vehiculoSeleccionado != null) {
            dao.eliminarVehiculo(vehiculoSeleccionado.getId());
            cargarTabla();
            limpiar();
        }
    }

    private void limpiar() {
        txtPlaca.clear();
        txtMarca.clear();
        txtPropietario.clear();
        txtProblema.clear();
        vehiculoSeleccionado = null;
        tablaVehiculos.getSelectionModel().clearSelection();
    }
}