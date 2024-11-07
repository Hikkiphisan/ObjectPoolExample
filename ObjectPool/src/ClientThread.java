import org.omg.CORBA.Current;

import java.util.Currency;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class ClientThread implements Runnable {
    private WaiterPool waiterPool;
    private String clientName;

    public ClientThread(WaiterPool waiterPool, String clientName) {
        this.waiterPool = waiterPool;
        this.clientName = clientName;
    }


    public void run() {
        orderaDrink();
    }


    private void orderaDrink() {
        try {
            System.out.println("Khách hàng " + clientName + " vừa mới gọi món!");

            WaiterInServer waiter = waiterPool.getWaiter();

            TimeUnit.MILLISECONDS.sleep(randomInt(1000,1500));
            waiterPool.release(waiter);
            System.out.println("Vị khách hàng tên là " + clientName + " đã được phục vụ!");
        } catch (InterruptedException | WaiterNotFoundException e) {
            System.out.println("Vị khách " + clientName + " sẽ phải chờ đợi một lúc.");
        }
    }



    public static int randomInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }
}