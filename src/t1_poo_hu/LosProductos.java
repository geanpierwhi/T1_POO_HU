package t1_poo_hu;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
public class LosProductos {
    private static List<Producto> inventario;
    
    public LosProductos(){
        this.inventario = new ArrayList<>();
    }
    
    public void registrarProducto() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto:");
        if (nombre == null) return; // Si el usuario cancela
        // Verificar si ya existe un producto con el mismo nombre
        for (Producto p : inventario) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                JOptionPane.showMessageDialog(null, 
                    "Error: Ya existe un producto con el nombre '" + nombre + "'.\n" +
                    "No se pueden registrar productos duplicados.");
                return;
            }
        }
        
        String stockStr = JOptionPane.showInputDialog("Ingrese la cantidad en stock:");
        if (stockStr == null) return;
        
        String precioStr = JOptionPane.showInputDialog("Ingrese el precio del producto:");
        if (precioStr == null) return;
        
        try {
            int stock = Integer.parseInt(stockStr);
            double precio = Double.parseDouble(precioStr);
            
            inventario.add(new Producto(nombre, stock, precio));
            JOptionPane.showMessageDialog(null, "Producto registrado correctamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Ingrese valores numéricos válidos para stock y precio.");
        }
    
}
    // 2. Visualizar productos
    public void visualizarProductos() {
        if (inventario.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos registrados en el inventario.");
            return;
        }
        
        StringBuilder listaProductos = new StringBuilder("PRODUCTOS EN INVENTARIO:\n\n");
        for (int i = 0; i < inventario.size(); i++) {
            listaProductos.append("Nombre").append(": ").append(inventario.get(i).getNombre()).append("\n");
            listaProductos.append("Stock").append(": ").append(inventario.get(i).getStock()).append("\n");
            listaProductos.append("Precio").append(": ").append(inventario.get(i).getPrecio()).append("\n");

        }
        
        JOptionPane.showMessageDialog(null, listaProductos.toString());
    }
    
    // 3. Entrada de stock (incrementar)
    public void entradaStock() {
        if (inventario.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos registrados para actualizar stock.");
            return;
        }
        
        // Mostrar lista de productos
        String[] nombresProductos = new String[inventario.size()];
        for (int i = 0; i < inventario.size(); i++) {
            nombresProductos[i] = (i + 1) + ". " + inventario.get(i).getNombre() + " (Stock actual: " + inventario.get(i).getStock() + ")";
        }
        
        int indice = JOptionPane.showOptionDialog(
            null, 
            "Seleccione el producto para registrar entrada de stock:", 
            "Entrada de Stock",
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.QUESTION_MESSAGE,
            null, 
            nombresProductos, 
            nombresProductos[0]
        );
        
        if (indice == -1) return; // Si el usuario cancela
        
        Producto producto = inventario.get(indice);
        
        try {
            String cantidadStr = JOptionPane.showInputDialog("Ingrese la cantidad de unidades que ingresaron:");
            if (cantidadStr != null) {
                int cantidad = Integer.parseInt(cantidadStr);
                if (cantidad < 0) {
                    JOptionPane.showMessageDialog(null, "Error: Ingrese una cantidad existente.");
                    return;
                }
                
                int nuevoStock = producto.getStock() + cantidad;
                producto.setStock(nuevoStock);
                JOptionPane.showMessageDialog(null, 
                    "Entrada registrada correctamente.\n" +
                    "Producto: " + producto.getNombre() + "\n" +
                    "Stock anterior: " + (nuevoStock - cantidad) + "\n" +
                    "Entrada: " + cantidad + "\n" +
                    "Stock actual: " + nuevoStock
                );
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Ingrese un valor.");
        }
    }
    
    // 4. Salida de stock (decrementar)
    public void salidaStock() {
        if (inventario.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos registrados para actualizar stock.");
            return;
        }
        
        // Mostrar lista de productos
        String[] nombresProductos = new String[inventario.size()];
        for (int i = 0; i < inventario.size(); i++) {
            nombresProductos[i] = (i + 1) + ". " + inventario.get(i).getNombre() + " (Stock actual: " + inventario.get(i).getStock() + ")";
        }
        
        int indice = JOptionPane.showOptionDialog(
            null, 
            "Seleccione el producto para registrar salida de stock:", 
            "Salida de Stock",
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.QUESTION_MESSAGE,
            null, 
            nombresProductos, 
            nombresProductos[0]
        );
        
        if (indice == -1) return; // Si el usuario cancela
        
        Producto producto = inventario.get(indice);
        
        try {
            String cantidadStr = JOptionPane.showInputDialog("Ingrese la cantidad de unidades vendidas:");
            if (cantidadStr != null) {
                int cantidad = Integer.parseInt(cantidadStr);
                if (cantidad < 0) {
                    JOptionPane.showMessageDialog(null, "Error: Ingrese una cantidad existente.");
                    return;
                }
                
                if (cantidad > producto.getStock()) {
                    JOptionPane.showMessageDialog(null, 
                        "Error: No hay suficiente stock disponible.\n" +
                        "Stock actual: " + producto.getStock() + "\n" +
                        "Cantidad solicitada: " + cantidad
                    );
                    return;
                }
                
                int nuevoStock = producto.getStock() - cantidad;
                producto.setStock(nuevoStock);
                JOptionPane.showMessageDialog(null, 
                    "Salida registrada correctamente.\n" +
                    "Producto: " + producto.getNombre() + "\n" +
                    "Stock anterior: " + (nuevoStock + cantidad) + "\n" +
                    "Salida: " + cantidad + "\n" +
                    "Stock actual: " + nuevoStock
                );
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Ingrese un valor.");
        }
    }
    
    // 5.Actualizar precio (función auxiliar)
    public void actualizarPrecio() {
        if (inventario.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos registrados para actualizar precio.");
            return;
        }
        
        // Mostrar lista de productos
        String[] nombresProductos = new String[inventario.size()];
        for (int i = 0; i < inventario.size(); i++) {
            nombresProductos[i] = (i + 1) + ". " + inventario.get(i).getNombre() + " (Precio actual: " + inventario.get(i).getPrecio() + ")";
        }
        
        int indice = JOptionPane.showOptionDialog(
            null, 
            "Seleccione el producto para actualizar precio:", 
            "Actualizar Precio",
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.QUESTION_MESSAGE,
            null, 
            nombresProductos, 
            nombresProductos[0]
        );
        
        if (indice == -1) return; // Si el usuario cancela
        
        Producto producto = inventario.get(indice);
        
        try {
            String nuevoPrecioStr = JOptionPane.showInputDialog("Ingrese el nuevo precio:", producto.getPrecio());
            if (nuevoPrecioStr != null) {
                double nuevoPrecio = Double.parseDouble(nuevoPrecioStr);
                if (nuevoPrecio < 0) {
                    JOptionPane.showMessageDialog(null, "Error: El precio debe ser un número positivo.");
                    return;
                }
                producto.setPrecio(nuevoPrecio);
                JOptionPane.showMessageDialog(null, "Precio actualizado correctamente.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Ingrese un valor válido.");
        }
    }
    
    // 6. Eliminar producto
    public void eliminarProducto() {
        if (inventario.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos registrados para eliminar.");
            return;
        }
        
        String[] nombresProductos = new String[inventario.size()];
        for (int i = 0; i < inventario.size(); i++) {
            nombresProductos[i] = (i + 1) + ". " + inventario.get(i).getNombre();
        }
        
        int indice = JOptionPane.showOptionDialog(
            null, 
            "Seleccione el producto a eliminar:", 
            "Eliminar Producto",
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.QUESTION_MESSAGE,
            null, 
            nombresProductos, 
            nombresProductos[0]
        );
        
        if (indice == -1) return; // Si el usuario cancela
        
        int confirmacion = JOptionPane.showConfirmDialog(
            null, 
            "¿Está seguro de eliminar el producto: " + inventario.get(indice).getNombre() + "?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            inventario.remove(indice);
            JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");
        }
    }
    
    // 7. Consultar stock de un producto
    public void consultarStock() {
        if (inventario.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos registrados en el inventario.");
            return;
        }
        
        String[] nombresProductos = new String[inventario.size()];
        for (int i = 0; i < inventario.size(); i++) {
            nombresProductos[i] = inventario.get(i).getNombre();
        }
        
        String productoSeleccionado = (String) JOptionPane.showInputDialog(
            null, 
            "Seleccione el producto para consultar stock:", 
            "Consultar Stock",
            JOptionPane.QUESTION_MESSAGE,
            null, 
            nombresProductos, 
            nombresProductos[0]
        );
        
        if (productoSeleccionado == null) return; // Si el usuario cancela
        
        // Buscar el producto y mostrar su stock
        for (Producto p : inventario) {
            if (p.getNombre().equals(productoSeleccionado)) {
                JOptionPane.showMessageDialog(
                    null, 
                    "Producto: " + p.getNombre() + "\nStock actual: " + p.getStock() + "\nPrecio: " + p.getPrecio(),
                    "Consulta de Stock",
                    JOptionPane.INFORMATION_MESSAGE
                );
                break;
            }
        }
    }
}
