package gui.components.arranger;

import backend.songs.Sequence;
import javafx.scene.Node;
import javafx.scene.control.SkinBase;

public class SMPArrangerSkin<T extends Sequence> extends SkinBase<SMPArranger<T>> {

	protected SMPArrangerSkin(SMPArranger<T> control) {
		super(control);
	}

	@Override
	public void install() {
		getChildren().add(buildArrangerView());
	}

	private Node buildArrangerView() {
		return null;
	}

	@Override
	public void dispose() {

	}

}
