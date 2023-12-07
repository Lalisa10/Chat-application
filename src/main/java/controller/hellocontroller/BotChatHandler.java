package controller.hellocontroller;

import com.example.messenger.MessengerApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import javax.swing.table.JTableHeader;
import java.util.Random;
import java.util.Timer;

public class BotChatHandler implements Runnable {

    private static final long WAITING_TIME_TO_SEND_MESSAGE = 5000;
    private MessengerController messengerController;
    private Thread thread;
    private final static String[] allMessageContents = ("It's the end of the month and the weekend\n" +
            "I'ma spend this check, everything on me, yeah\n" +
            "I'ma tip myself, I'ma spend it on myself\n" +
            "I'ma drop it like it's pouring, I'ma pour it on myself\n" +
            "Check, check, checkㅤ\n" +
            "Check that money making bank account number (yikes)\n" +
            "That's that shit that's never getting bounced on ya\n" +
            "Bitch, I do the money dance, I just made a hundred bands\n" +
            "When the store says \"Sign for it, \" I'ma leave my autograph\n" +
            "Dollar bills, dollar bills\n" +
            "Watch it falling for me, I love the way that feels\n" +
            "Dollar bills, dollar bills\n" +
            "Keep on falling for me, I love the way it feels\n" +
            "I came here to drop some money, dropping all my money\n" +
            "Drop some money, all this bread so yummy, yeah\n" +
            "Twerking, twerking when I buy the things I like\n" +
            "Dollar, dollars dropping on my ass tonight\n" +
            "Everyone silent, listen to my money talk\n" +
            "Spend how I like it\n" +
            "Yeah, everyone know what I mean, mean\n" +
            "When they see green, when they see green, that mean go\n" +
            "Give me what the hell I want\n" +
            "Give me what the hell I want\n" +
            "Check that money making bank account number (yikes)\n" +
            "That's that shit that's never getting bounced on ya\n" +
            "Bitch, I do the money dance, I just made a hundred bands\n" +
            "When the store says \"Sign for it, \" I'ma leave my autograph\n" +
            "Dollar bills, dollar bills\n" +
            "Watch it falling for me, I love the way that feels\n" +
            "Dollar bills, dollar bills\n" +
            "Keep on falling for me, I love the way it feels\n" +
            "I came here to drop some money, dropping all my money\n" +
            "Drop some money, all this bread so yummy, yeah\n" +
            "Twerking, twerking when I buy the things I like\n" +
            "Dollar, dollars, dropping on my ass tonight\n" +
            "Drop some money, dropping all my money\n" +
            "Drop some money, all this bread so yummy, yeah\n" +
            "Drop some money, dropping all my money\n" +
            "Drop some money, all this bread so yummy, yeah\n" +
            "My money moves, money, I choose\n" +
            "Celine, my shoes, walking on you\n" +
            "My money rules\n" +
            "My money moves, money, I choose\n" +
            "Watch how my Wons and Yens and Dollars be dropping on you, you, you\n" +
            "Dun, da, la, la, dun-dun, dun, da-la-la, dun-dun\n" +
            "Dun, da, la, la, dun-dun, dropping on you\n" +
            "Dun, da, la, la, dun-dun, dun, da-la-la, dun-dun\n" +
            "Dun, da, la, la, dun-dun, dropping on you").split("\n");



    private int currentListSize;

    private CountDownTimer countDownTimer;

    private BotChatHandler() {

    }

    public BotChatHandler(MessengerController messengerController) {
            this.messengerController = messengerController;
            countDownTimer = new CountDownTimer(WAITING_TIME_TO_SEND_MESSAGE);
            currentListSize = 1;
    }

    private boolean isGotAMessage() {
        return messengerController.getMessageList().getItems().size() > currentListSize;
    }

    private void sendMessage() {
        Random random = new Random();
        int index = random.nextInt(allMessageContents.length);
        messengerController.addMessage(MessageFactory.getMessage(MessageFactory.MessageType.OTHERS,
                allMessageContents[index]));
        currentListSize ++;
        countDownTimer.reset();
    }

    private void handleSendingMessage() {
        try {
            if (countDownTimer.isEnd()) {
                sendMessage();
            }
        } catch (RuntimeException ignored) {

        }
    }

    private void handleWaiting() {
        if (isGotAMessage()) {
            countDownTimer.start();
            updateCurrentListSize();
        }
    }

    private void updateCurrentListSize() {
        currentListSize = messengerController.getMessageList().getItems().size();
    }

    @Override
    public void run() {
        while (MessengerApplication.getStatus() == MessengerApplication.Status.RUNNING) {
            handleWaiting();
            handleSendingMessage();
        }
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
        }
        thread.start();

    }
}
