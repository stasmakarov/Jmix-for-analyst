package com.company.jmixforanalyst.view.extcontentstoragedetail;

import com.vaadin.flow.router.Route;
import io.jmix.bpm.entity.ContentStorage;
import io.jmix.bpmflowui.view.contentstorage.ContentStorageDetailView;
import io.jmix.flowui.view.DefaultMainViewParent;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "ext-content-storage-detail", layout = DefaultMainViewParent.class)
@ViewController(id = "ext_ContentStorage.detail")
@ViewDescriptor(path = "ext-content-storage-detail-view.xml")
public class ExtContentStorageDetailView extends ContentStorageDetailView {

    private ContentStorage contentStorage;

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        setEntityToEdit(contentStorage);
    }

    public void setContentStorage(ContentStorage contentStorage) {
        this.contentStorage = contentStorage;
    }
}