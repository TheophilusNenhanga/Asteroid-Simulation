package jbq061.assignment4;


import javafx.scene.layout.VBox;

public class SidePanel extends VBox {
	public SidePanel(SpaceView spaceView, CursorView cursorView, ControlPanelView controlPanelView) {
		this.getChildren().addAll(spaceView, cursorView, controlPanelView);
	}
}

// YOu can only have one instance of a  view on the screen at a time.