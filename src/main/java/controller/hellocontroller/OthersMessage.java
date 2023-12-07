package controller.hellocontroller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class OthersMessage extends Message {
    private static final String OTHERS_MESSAGE_STYLE = "other-s-message-content";
    public OthersMessage(String content) {
        super(content);
        setContentStyle();
    }

    @Override
    protected void setContentStyle() {
        super.setContentStyle();
        this.content.getStyleClass().add(OTHERS_MESSAGE_STYLE);
    }

    private void setAvatarImage() {

    }
    @Override
    protected void setPosition() {
        this.content.setLayoutX(10);
    }
}
