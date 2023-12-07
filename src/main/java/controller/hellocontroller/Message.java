package controller.hellocontroller;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.util.Stack;

public abstract class Message extends Pane {
    protected static final int CELL_PREF_WIDTH = 405;

    private static final int MAX_CONTENT_WIDTH = 195;

    protected Label content;
    public Label getContent() {
        return content;
    }

    public void setContent(Label content) {
        this.content = content;
    }

    private Message() {

    }

    public Message(String content) {
        this.content = new Label(content);
        this.getChildren().add(this.content);
        setContentStyle();
        setCellStyle();


    }

    protected void setContentStyle() {
        this.content.getStyleClass().add("message-content");
    }


    protected void setCellStyle() {
        this.getStyleClass().add("message-cell");
        this.setPrefHeight(getContentHeight());
    }

    protected double getContentWidth() {
        Text text = new Text(content.getText());
        return Math.min(text.layoutBoundsProperty().get().getWidth(), MAX_CONTENT_WIDTH);
    }

    protected double getContentHeight() {
        Text text = new Text(content.getText());
        int nLine = (int) Math.ceil((text.layoutBoundsProperty().get().getWidth() / MAX_CONTENT_WIDTH));
        return 10 + text.layoutBoundsProperty().get().getHeight()
                * nLine;
    }
    protected abstract void setPosition();
}