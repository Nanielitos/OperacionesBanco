public class HiloTransferencia implements Runnable {

    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;
    private boolean bandEnUso = false;

    public HiloTransferencia(Cuenta cuentaOrigen, Cuenta cuentaDestino) {
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
    }

    public void run() {
        String hilo = Thread.currentThread().getName();
        synchronized (cuentaDestino) {
            while (bandEnUso) {

                try {
                    System.out.println("Waiting for b to complete...");
                    cuentaDestino.wait();
                } catch (InterruptedException e) {
                }
            }

        bandEnUso = true;

        System.out.println(hilo + " bloquea cuenta de " + cuentaOrigen.getNombre());

        if ((cuentaOrigen.getSaldo() == 0)
            || (cuentaOrigen.getSaldo() < 50)) {
            System.out.println("no hay saldo o la Cuenta sobregirada");
        }
        else {
            hacerRetiro(cuentaOrigen, 50);
            System.out.println(hilo + " inicia el deposito " + "en cuenta de " +
                    cuentaDestino.getNombre());
            hacerDeposito(cuentaDestino, 50);
            System.out.println(hilo + " termina la transferencia");
            System.out.println(hilo + " termina el bloqueo de " +
                    cuentaOrigen.getNombre());
        }
        bandEnUso = false;
        cuentaDestino.notifyAll();
    }

}
    private void hacerRetiro(Cuenta cuenta,int cantidad){
        String hilo = Thread.currentThread().getName();
        if(cuenta.getSaldo() >= cantidad){
            System.out.println(hilo + " procede a realizar un retiro "
                    + cuenta.getSaldo());
            try{
                Thread.sleep(500);
            } catch(InterruptedException e){
                System.out.println("Error de thread");
            }
            cuenta.retiro(cantidad);
            System.out.println(hilo + " termino de realizar un retiro "
                    + cuenta.getSaldo());
        }         else{
            System.out.println("No hay suficiente saldo para que "
                    +hilo+" realice un retiro ("+cuenta.getSaldo()+")");
        }
    }
    private void hacerDeposito(Cuenta cuenta, int cantidad){
    String hilo = Thread.currentThread().getName();
    synchronized(cuenta){
        System.out.println(hilo + " procede a realizar un deposito "
                + cuenta.getSaldo());
        try{
            Thread.sleep(500);
        } catch(InterruptedException e){
            System.out.println("Error de thread");
        }
        cuenta.deposito(cantidad);
        System.out.println(hilo + " termino de realizar un deposito "
                + cuenta.getSaldo());
    }
  }
}