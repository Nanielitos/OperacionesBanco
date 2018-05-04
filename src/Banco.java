
public class Banco {
    public static void main (String[]args){
        Cuenta cuentaOrigen = new Cuenta(100,"nomina");
        Cuenta cuentaDestino = new Cuenta(0,"Tienda");

        HiloTransferencia Cajero = new HiloTransferencia(cuentaOrigen,
                cuentaDestino);
        Thread operacionesPedro = new Thread(Cajero);
        Thread operacionesJuan = new Thread(Cajero);
        Thread operacionesMaria = new Thread(Cajero);
        Thread operacioneslola = new Thread(Cajero);

        operacionesPedro.start();
        operacionesJuan.start();
        operacionesMaria.start();
        operacioneslola.start();
    }
}

