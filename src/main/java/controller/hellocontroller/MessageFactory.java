package controller.hellocontroller;

public class MessageFactory {
    private MessageFactory() {

    }

    public enum MessageType {
        USER,
        OTHERS
    }
    public static Message getMessage(MessageType messageType, String content) {
        switch (messageType) {
            case USER -> {
                return new UserMessage(content);
            }
            case OTHERS -> {
                return new OthersMessage(content);
            }
            default -> throw new IllegalArgumentException("This message type doesn't exist");
        }
    }
}
