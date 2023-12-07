package controller.hellocontroller;

import com.example.messenger.MessengerApplication;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class MessengerController implements Initializable {

    @FXML
    private TextField messageField;

    @FXML
    private Circle avatarCircle;

    @FXML
    private ListView<Pane> messageList;

    @FXML
    private Button sendButton;

    @FXML
    private Pane introductionPane;

    @FXML
    private Circle introductionAvatarCircle;

    public Button getSendButton() {
        return sendButton;
    }

    public TextField getMessageField() {
        return messageField;
    }

    public ListView<Pane> getMessageList() {
        return messageList;
    }

    private void setAvatarImage(String link) {
        try {
            Image image = new Image(link, false);
            avatarCircle.setFill(new ImagePattern(image));
            introductionAvatarCircle.setFill(new ImagePattern(image));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void initIntroductionPane() {
        messageList.getItems().add(introductionPane);
    }

    private UserMessage getNewUserMessage() {
        String content = messageField.getText();
        return (UserMessage) MessageFactory.getMessage(MessageFactory.MessageType.USER, content);
    }

    public void addMessage(Message message) {
        if (message.getContent().getText().isEmpty()) {
            throw new IllegalArgumentException("Message is empty!");
        }
        Platform.runLater(() -> {
            messageList.getItems().add(message);
        });
    }


    public void addUserMessage(UserMessage userMessage) {
        addMessage(userMessage);
        if (!messageList.getItems().isEmpty())
       // System.out.println(messageList.getItems().get(messageList.getItems().size() - 1).getHeight());
        messageField.setText("");
    }
    private void handleClickSending() {
        sendButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    addUserMessage(getNewUserMessage());
                } catch (IllegalArgumentException ignored) {

                }

            }
        });
    }

    private void handlePressSending() {
        messageField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    try {
                        addUserMessage(getNewUserMessage());
                    } catch (IllegalArgumentException ignored) {

                    }
                }
            }
        });
    }

    private void initAvatarImage() {
        setAvatarImage(String.valueOf(MessengerApplication.class.getResource("/icon/lisa.jpg")));
    }

    private void initSendingButton() {
        SendingButtonVisibilityHandler sendingButtonVisibilityHandler = new SendingButtonVisibilityHandler(this);
        sendingButtonVisibilityHandler.start();
    }

    private void initBotChat() {
        BotChatHandler botChatHandler = new BotChatHandler(this);
        botChatHandler.start();
    }
    
    private void manageInteraction() {
        handleClickSending();
        handlePressSending();
        handleCloseButtonAction();
    }

    @FXML
    public void handleCloseButtonAction() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initNodes();
        initBotChat();
        manageInteraction();
    }

    private void initNodes() {
        initAvatarImage();
        initSendingButton();
        initIntroductionPane();
    }

}