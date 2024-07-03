//AUTOR: RICARDO ESPINOSA Y JUAN DIEGO GUERRERO
import java.util.ArrayList;
public class problema_1_JuegoRoles {
    public static void main(String[] args) {
        
    }
}
//CLASE PADRE
abstract class Personaje{
    //ATRIBUTOS
    protected int vida;
    protected int experiencia;
}
//CLASE HIJA 1
class Mago extends Personaje{
    //ATRIBUTOS
    private ArrayList<String> hechizos;
    private ArrayList<String> poderes;
}
//CLASE HIJA 2
class Guerrero extends Personaje{
    //ATRIBUTOS
    private int fuerza;
    private ArrayList<String> habilidades;
}
//CLASE HIJA 3
class Arquero extends Personaje{
    //ATRIBUTOS
    private int precision;
    private ArrayList<String> habilidadesDistancia;
}