package jbq061.assignment4;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.HBox;

public class MainUI extends HBox {
	private final SpaceView spaceView;

	public static double spaceCanvasWidth = 800;

	public MainUI() {

		PublishSubscribe publishSubscribe = new PublishSubscribe();
		spaceView = new SpaceView(0.5, 0.5, spaceCanvasWidth);
		SpaceModel spaceModel = new SpaceModel(publishSubscribe);
		SpaceController spaceController = new SpaceController(publishSubscribe);
		IModel iModel = new IModel();
		SpaceView sidePanelSpaceView = new SpaceView(0.5, 0.5, 200);
		CursorView cursorView = new CursorView(200);

		publishSubscribe.subscribe("create", spaceView);
		publishSubscribe.subscribe("animate", spaceView);

		publishSubscribe.subscribe("create", sidePanelSpaceView);
		publishSubscribe.subscribe("animate", sidePanelSpaceView);

		publishSubscribe.subscribe("areaCursor", spaceView);
		publishSubscribe.subscribe("areaCursor", sidePanelSpaceView);

		publishSubscribe.subscribe("areaCursor-off", spaceView);
		publishSubscribe.subscribe("areaCursor-off", sidePanelSpaceView);

		publishSubscribe.subscribe("show", cursorView);

		// Linking
		spaceView.setupEvents(spaceController);
		spaceController.setModel(spaceModel);
		spaceModel.addSubscriber(spaceView);
		spaceModel.addSubscriber(sidePanelSpaceView);
		spaceController.setIModel(iModel);

		ControlPanelView controlPanelView = new ControlPanelView();
		SidePanel sidePanel = new SidePanel(sidePanelSpaceView, cursorView, controlPanelView);

		controlPanelView.setController(spaceController);


		this.getChildren().add(sidePanel);
		this.getChildren().add(spaceView);
		this.setStyle("-fx-base:#191919; -fx-background-color: #191919");

		spaceController.startSystem();
		AnimationTimer animationTimer = new AnimationTimer() {
			@Override
			public void handle(long l) {
				spaceController.handleTimerTick();
			}
		};
		animationTimer.start();
	}

	public void setFocus() {
		spaceView.requestFocus();
	}
}
