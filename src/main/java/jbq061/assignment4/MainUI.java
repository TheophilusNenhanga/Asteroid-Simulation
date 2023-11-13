package jbq061.assignment4;

import javafx.scene.layout.StackPane;

public class MainUI extends StackPane {
	private final SpaceView spaceView;

	public MainUI() {
		PublishSubscribe publishSubscribe = new PublishSubscribe();
		spaceView = new SpaceView(800);
		SpaceModel spaceModel = new SpaceModel();
		SpaceController spaceController = new SpaceController(publishSubscribe);
		IModel iModel = new IModel();

		// Linking
		spaceView.setupEvents(spaceController);
		spaceController.setModel(spaceModel);
		spaceModel.addSubscriber(spaceView);
		spaceController.setIModel(iModel);

		this.getChildren().add(spaceView);
	}

	public void setFocus(){
		spaceView.requestFocus();
	}
}
