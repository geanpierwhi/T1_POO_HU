package t1_poo_hu;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
public class Principal_Inventario {

    public static void main(String[] args) {
        LosProductos P1 = new LosProductos();
        while(true){
            int opcion = Integer.parseInt(JOptionPane.showInputDialog("1.- Ingresar Prodcuto\n"+
                    "2.- Ver Producto con su stock y precio\n3.- Entrada de stock \n4.- Salida de stock\n"
                    + "5.- Actualizar Precio\n6.- Eliminar Producto\n"+
                    "7.- Consultar stock de un producto\n 8.-Salir"));
            switch(opcion){
                case 1:
                    P1.registrarProducto();
                    break;
                case 2:
                    P1.visualizarProductos();
                    break;
                case 3:
                    P1.entradaStock();
                    break;
                case 4:
                    P1.salidaStock();
                    break;
                case 5:
                    P1.actualizarPrecio();
                    break;
                case 6:
                    P1.eliminarProducto();
                    break;
                case 7:
                    P1.consultarStock();
                    break;
                case 8:
                    JOptionPane.showMessageDialog(null,"Saliendo...");
                    return;
                default:
                    JOptionPane.showMessageDialog(null,"Opcion no valida");           
            }
        }
    }
    
}
