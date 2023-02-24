import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ventanaCrud {
    private JLabel JFarmacia;
    private JLabel JCedula;
    private JLabel JCiudad;
    private JTextField txtCedula;
    private JTextField txtCantidad;
    private JComboBox comboBoxCiudad;
    private JTextField txtNombreC;
    private JLabel JNombre;
    private JLabel JProducto;
    private JComboBox comboBoxProducto;
    private JLabel JDireccion;
    private JTextField txtDireccion;
    private JLabel JWilson;
    private JButton botonBuscar;
    private JButton botonBorrar;
    private JButton botonActualizar;
    private JButton botonCrear;
    private JPanel panel;
    private JButton limpiarPantallaButton;

    PreparedStatement ps;
    Statement st;



    public ventanaCrud(){

        // Mostrar el contenido de los combo box
        // select  farmacia.productos
        try {
            Connection con = getConection();
            String sql = "SELECT * FROM ciudad;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nombreProducto = rs.getString("Nom_Ciu");
                comboBoxCiudad.addItem(nombreProducto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // select  farmacia.productos
        try {
            Connection con = getConection();
            String sql = "SELECT * FROM nombreproductos;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nombreProducto = rs.getString("Prod_nom");
                comboBoxProducto.addItem(nombreProducto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        botonActualizar.setEnabled(false);
        //limpiarPantallaButton.setEnabled(false);


        botonCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botonActualizar.setEnabled(true);
                botonBorrar.setEnabled(true);
                botonBuscar.setEnabled(true);
                Connection con = null;


                try{
                    con = getConection();
                    ps = con.prepareStatement("INSERT INTO productos (Id_Clie, Nom_Clie,Ciu_Clie,Pro_Clie,Cant_Clie, Dir_Clie) VALUES(?,?,?,?,?,?) ");

                    ps.setString(1, txtCedula.getText());
                    ps.setString(2, txtNombreC.getText());
                    // Variable para almacenar en el comboBox
                    String ciudadSeleccionada = comboBoxCiudad.getSelectedItem().toString();
                    ps.setString(3, ciudadSeleccionada);
                    // Variable para almacenar en el comboBox
                    String productoSeleccionada = comboBoxProducto.getSelectedItem().toString();
                    ps.setString(4,productoSeleccionada);
                    ps.setString(5, txtCantidad.getText());
                    ps.setString(6, txtDireccion.getText());
                    System.out.println(ps);//imprimo en consola para verificación

                    int res = ps.executeUpdate();

                    if (res > 0) {
                        JOptionPane.showMessageDialog(null, "Persona Guardada");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al Guardar persona");
                    }

                    con.close();//importante!!!!

                }catch (HeadlessException | SQLException f) {
                    System.err.println(f);
                }

            }
        });
        limpiarPantallaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtCedula.setText("");
                txtNombreC.setText("");
                comboBoxCiudad.setSelectedIndex(0);
                comboBoxProducto.setSelectedIndex(0);
                txtCantidad.setText("");
                txtDireccion.setText("");
            }
        });


        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con;

                try {
                    con = getConection();
                    st = con.createStatement();
                    ResultSet rs;
                    rs = st.executeQuery("select * from farmacia.productos where Id_Clie=" + txtCedula.getText() + ";");
                    while (rs.next()) {
                        txtNombreC.setText(rs.getString("Nom_Clie"));
                        txtCantidad.setText(rs.getString("Cant_Clie"));
                        txtDireccion.setText(rs.getString("Dir_Clie"));
                        String ciudadSeleccionada = rs.getString("Ciu_Clie");
                        comboBoxCiudad.setSelectedItem(ciudadSeleccionada);
                        String productoSeleccionado = rs.getString("Pro_Clie");
                        comboBoxProducto.setSelectedItem(productoSeleccionado);
                    }
                } catch (Exception s) {
                    System.err.println(s);
                }
            }
        });


    }

    public static Connection getConection() {
        Connection con = null;
        String base = "farmacia"; //Nombre de la base de datos
        String url = "jdbc:mysql://localhost:3306/" + base; //Direccion, puerto y nombre de la Base de Datos
        String user = "root"; //Usuario de Acceso a MySQL
        String password = "123456"; //Password del usuario

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
        return con;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("insert");
        frame.setContentPane(new ventanaCrud().panel );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(560,413);
        frame.setVisible(true);
    }
}
