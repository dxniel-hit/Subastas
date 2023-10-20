package co.edu.uniquindio.pr3.subastasUQ.hilos;

import co.edu.uniquindio.pr3.subastasUQ.controllers.ModelFactoryController;

public class WriteBackupObjectsThread extends Thread{
    @Override
    public void run() {
        ModelFactoryController.writeBackupProduct();
        ModelFactoryController.writeBackupUser();
        ModelFactoryController.writeBackupAdvertisement();
        ModelFactoryController.writeBackupBid();
        ModelFactoryController.writeBackupBuys();
    }
}
