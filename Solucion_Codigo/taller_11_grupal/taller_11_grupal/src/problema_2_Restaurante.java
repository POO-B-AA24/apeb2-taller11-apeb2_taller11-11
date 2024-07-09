//AUTORES: RICARDO ESPINOSA Y JUAN DIEGO GUERRERO
import java.util.ArrayList;
import java.util.Scanner;

public class problema_2_Restaurante {
    public static void main(String[] args) {
        Scanner tc = new Scanner(System.in);
        ArrayList<Menu> listaMenu = new ArrayList<>();
        System.out.println("****RESTAURANTE LLOREM TORRES GRILL****");
        int seguir = 1, opcion = 0;
        do{
            System.out.println("ELIJA UNA OPCION:");
            System.out.println("1 --> MENU A LA CARTA - CANGREJO");
            System.out.println("2 --> MENU DEL DIA - POLLO FRITO");
            System.out.println("3 --> MENU DE INFANTES - FRUTA");
            System.out.println("4 --> MENU ECONOMICO - ENCEBOLLADO");
            System.out.println("5 --> TERMINAR Y ORDENAR");
            opcion = tc.nextInt();
            switch(opcion){
                case 1:
                    MenuCarta menu1 = new MenuCarta(2, 0.5, 3, "CANGREJO", 5);
                    menu1.calcularValorTotal();
                    listaMenu.add(menu1);
                    break;
                case 2:
                    MenuDia menu2 = new MenuDia(2.5, 0.5, "POLLO FRITO", 5);
                    menu2.calcularValorTotal();
                    listaMenu.add(menu2);
                    break;
                case 3:
                    MenuNinos menu3 = new MenuNinos(1, 1.5, "FRUTA", 5);
                    menu3.calcularValorTotal();
                    listaMenu.add(menu3);
                    break;
                case 4:
                    MenuEconomico menu4 = new MenuEconomico(2, "ENCEBOLLADO", 5);
                    menu4.calcularValorTotal();
                    listaMenu.add(menu4);
                    break;
                case 5:
                    System.out.println("ORDEN TERMINADA");
                    seguir = 0;
                    break;
                default:
                    System.out.println("OPCION INCORRECTA, VUELVE A INTENTAR");
                    break;
            }
        }while(seguir == 1);
        System.out.println("A CONTINUACION PROPORCIONE SUS DATOS PARA LA FACTURA");
        System.out.println("INGRESE UN NOMBRE");
        String nombre = tc.next();
        System.out.println("INGRESE UN APELLIDO");
        String apellido = tc.next();
        
        Cuenta cuenta = new Cuenta(nombre+" "+apellido);
        cuenta.setListaMenu(listaMenu);//SE LE PASA LA LISTA YA LLENA
        cuenta.calcularSubTotal();
        cuenta.calcularIVA();
        cuenta.calcularTotal();
        System.out.println(cuenta);
        System.out.println("GRACIAS POR VISITAR LLOREM TORRES GRILL");
    }
}

class Cuenta{
    //ATRIBUTOS
    protected String nombreCliente;
    protected ArrayList<Menu> listaMenu;
    protected double subtotal;
    protected double IVA;
    protected double total;
    
    //CONSTRUCTOR
    public Cuenta(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    
    //METODOS
    public void calcularSubTotal(){
        for(Menu menu : this.listaMenu){
            this.subtotal += menu.valorTotal;
        }
    }
    
    public void calcularIVA(){
        this.IVA = this.subtotal*0.15;
    }
    
    public void calcularTotal(){
        this.total = this.subtotal+this.IVA;
    }
    
    public void setListaMenu(ArrayList<Menu> listaMenu) {
        this.listaMenu = listaMenu;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CUENTA:").append("\n");
        sb.append("NOMBRE:").append(nombreCliente).append("\n");
        sb.append("MENUS ELEGIDOS:").append("\n");
        for(Menu menu : this.listaMenu){
            sb.append(menu).append("\n");
        }      
        sb.append("SUBTOTAL:").append(subtotal);
        sb.append(" IVA:").append(IVA);
        sb.append(" TOTAL:").append(total);
        return sb.toString();
    }
}

abstract class Menu{
    //ATRIBUTOS
    protected String nombrePlato;
    protected double valorInicial;
    protected double valorTotal;
    
    //CONSTRUCTOR
    public Menu(String nombrePlato, double valorInicial) {
        this.nombrePlato = nombrePlato;
        this.valorInicial = valorInicial;
    }
    
    //METODOS
    public abstract void calcularValorTotal();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" PLATO:").append(nombrePlato);
        sb.append(" VALOR INICIAL:").append(valorInicial);
        sb.append(" VALOR DEL MENU:").append(valorTotal);
        return sb.toString();
    }
}

class MenuCarta extends Menu{
    //ATRIBUTOS
    private double valorGuarnicion;
    private double valorBebida;
    private double valorAdicional; 
    
    //CONSTRUCTOR
    public MenuCarta(double valorGuarnicion, double valorBebida, 
            double valorAdicional, String nombrePlato, double valorInicial) {
        super(nombrePlato, valorInicial);
        this.valorGuarnicion = valorGuarnicion;
        this.valorBebida = valorBebida;
        this.valorAdicional = valorAdicional;
    }
    
    //METODOS
    @Override
    public void calcularValorTotal(){
        this.valorTotal = this.valorInicial+this.valorGuarnicion+this.valorBebida+
                this.valorAdicional;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MENU A LA CARTA:");
        sb.append(super.toString());
        sb.append(" VALOR GUARNICION:").append(valorGuarnicion);
        sb.append(" VALOR DE LA BEBIDA").append(valorBebida);
        sb.append(" VALOR ADICIONAL").append(valorAdicional);
        return sb.toString();
    }
}

class MenuDia extends Menu{
    //ATRIBUTOS
    private double Valorpostre;
    private double Valorbebida;
    
    //CONSTRUCTOR
    public MenuDia(double Valorpostre, double Valorbebida, String nombrePlato, 
            double valorInicial) {
        super(nombrePlato, valorInicial);
        this.Valorpostre = Valorpostre;
        this.Valorbebida = Valorbebida;
    }
    
    //METODOS
    @Override
    public void calcularValorTotal(){
        this.valorTotal = this.valorInicial+this.Valorpostre+this.Valorbebida;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MENU DEL DIA:");
        sb.append(super.toString());
        sb.append(" VALOR DEL POSTRE").append(Valorpostre);
        sb.append(" VALOR DE LA BEBIDA").append(Valorbebida);
        return sb.toString();
    }
}

class MenuNinos extends Menu{
    //ATRIBUTOS
    private double valorHelado;
    private double valorPastel;
    
    //CONSTRUCTOR
    public MenuNinos(double valorHelado, double valorPastel, String nombrePlato, 
            double valorInicial) {
        super(nombrePlato, valorInicial);
        this.valorHelado = valorHelado;
        this.valorPastel = valorPastel;
    }
    
    //METODOS
    @Override
    public void calcularValorTotal(){
        this.valorTotal=this.valorInicial+this.valorHelado+this.valorPastel;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MENU DE INFANTES:");
        sb.append(super.toString());
        sb.append(" VALOR HELADO").append(valorHelado);
        sb.append(" VALOR PASTEL:").append(valorPastel);
        return sb.toString();
    }
    
}

class MenuEconomico extends Menu{
    //ATRIBUTOS
    private double valorDescuento;
    
    //CONSTRUCTOR
    public MenuEconomico(double valorDescuento, String nombrePlato, 
            double valorInicial) {
        super(nombrePlato, valorInicial);
        this.valorDescuento = valorDescuento;
    }
    
    //METODOS
    @Override
    public void calcularValorTotal(){
        this.valorTotal = this.valorInicial - this.valorDescuento;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MENU ECONOMICO:");
        sb.append(super.toString());
        sb.append(" VALOR DESCUENTO:").append(valorDescuento);
        return sb.toString();
    }
}