package controller.hellocontroller;

public class UserMessage extends Message {
    private static final String USER_MESSAGE_STYLE = "user-message-content";
    public UserMessage(String content) {
        super(content);
        setContentStyle();
        setPosition();
    }

    protected void setContentStyle() {
        super.setContentStyle();
        this.content.getStyleClass().add(USER_MESSAGE_STYLE);
    }

    protected void setPosition() {
        this.content.setLayoutX(CELL_PREF_WIDTH - getContentWidth() + 5);
    }
}
