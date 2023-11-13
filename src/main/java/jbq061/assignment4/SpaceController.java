package jbq061.assignment4;

public class SpaceController {
	private SpaceModel model;
	private IModel iModel;
	private final PublishSubscribe publishSubscribe;

	public SpaceController(PublishSubscribe publishSubscribe){
		this.publishSubscribe = publishSubscribe;
	}

	public void setModel(SpaceModel model) {
		this.model = model;
	}

	public void setIModel(IModel iModel) {
		this.iModel = iModel;
	}
}
