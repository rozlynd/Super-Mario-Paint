package gui.components.arranger;

import backend.songs.Sequence;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.image.Image;

public class SMPArranger<T extends Sequence> extends Control {
	
	/** The data held by the arranger */
	private ListProperty<T> items;
    public ListProperty<T> items() {
        if (items == null) {
            items = new SimpleListProperty<>(this, "items", null);
        }
        return items;
    }
    public ObservableList<T> getItems() { return items().getValue(); }
    public void setItems(ObservableList<T> items) { items().setValue(items); }
    
    /** The single sequence used for adding and synchronizing */
    private ObjectProperty<T> watched;
    public ObjectProperty<T> watched() {
    	if (watched == null) {
    		watched = new SimpleObjectProperty<>(this, "watched", null);
    	}
    	return watched;
    }
    public T getWatched() { return watched().getValue(); }
    public void setWatched(T watched) { watched().setValue(watched); }

    /** The image for the Add button */
    private ObjectProperty<Image> imageAdd;
    public ObjectProperty<Image> imageAdd() {
        if (imageAdd == null) {
        	imageAdd = new SimpleObjectProperty<>(this, "imageAdd", null);
        }
        return imageAdd;
    }
    public Image getImageAdd() { return imageAdd().getValue(); }
    public void setImageAdd(Image imageAdd) { imageAdd().setValue(imageAdd); }

    /** The image for the Delete button */
    private ObjectProperty<Image> imageDelete;
    public ObjectProperty<Image> imageDelete() {
        if (imageDelete == null) {
        	imageDelete = new SimpleObjectProperty<>(this, "imageDelete", null);
        }
        return imageDelete;
    }
    public Image getImageDelete() { return imageDelete().getValue(); }
    public void setImageDelete(Image imageDelete) { imageDelete().setValue(imageDelete); }

    /** The image for the Move Up button */
    private ObjectProperty<Image> imageMvUp;
    public ObjectProperty<Image> imageMvUp() {
        if (imageMvUp == null) {
        	imageMvUp = new SimpleObjectProperty<>(this, "imageMvUp", null);
        }
        return imageMvUp;
    }
    public Image getImageMvUp() { return imageMvUp().getValue(); }
    public void setImageMvUp(Image imageMvUp) { imageMvUp().setValue(imageMvUp); }

    /** The image for the Move Down button */
    private ObjectProperty<Image> imageMvDown;
    public ObjectProperty<Image> imageMvDown() {
        if (imageMvDown == null) {
        	imageMvDown = new SimpleObjectProperty<>(this, "imageMvDown", null);
        }
        return imageMvDown;
    }
    public Image getImageMvDown() { return imageMvDown().getValue(); }
    public void setImageMvDown(Image imageMvDown) { imageMvDown().setValue(imageMvDown); }
	
	public SMPArranger() {
		super();
		setUp();
	}
    
    private void setUp() {
        getStyleClass().add(STYLE_CLASS_ARRANGER);
    }
	
	@Override
	protected Skin<?> createDefaultSkin() {
		return new SMPArrangerSkin<>(this);
	}
	
	public static String STYLE_CLASS_ARRANGER = "smp-arranger";

}
