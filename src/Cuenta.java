public class Cuenta {
    private String nombre;
    private double saldo = 100;

    public Cuenta(int saldo, String nombre){
        this.saldo=saldo;
        this.nombre=nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNombre() {

        return nombre;
    }

    public double getSaldo() {
        return saldo;
    }
    public void deposito(int saldo){
        this.saldo += saldo;
    }
    public void retiro(int cantidad){
        this.saldo -= cantidad;
    }
}
