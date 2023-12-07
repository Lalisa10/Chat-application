package controller.hellocontroller;

import com.example.messenger.MessengerApplication;

class SendingButtonVisibilityHandler implements Runnable {
    private final MessengerController messengerController;

    private Thread thread;

    public SendingButtonVisibilityHandler(MessengerController messengerController) {
        this.messengerController = messengerController;
    }

    @Override
    public void run() {
        while (MessengerApplication.getStatus() == MessengerApplication.Status.RUNNING) {
            messengerController.
                    getSendButton().
                    setVisible(!messengerController.getMessageField().getText().isEmpty());
        }
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

}
