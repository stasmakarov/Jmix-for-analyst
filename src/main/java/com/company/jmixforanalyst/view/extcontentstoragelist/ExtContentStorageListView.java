package com.company.jmixforanalyst.view.extcontentstoragelist;

import com.vaadin.flow.router.Route;
import io.jmix.bpmflowui.view.contentstorage.ContentStorageListView;
import io.jmix.flowui.action.list.EditAction;
import io.jmix.flowui.view.*;

@Route(value = "bpm/extactivateprocessdefinition", layout = DefaultMainViewParent.class)
@ViewController(id = "ext_bpm_ContentStorage.list")
@ViewDescriptor(path = "ext-content-storage-list-view.xml")
public class ExtContentStorageListView extends ContentStorageListView {
    @ViewComponent("contentStoragesTable.edit")
    private EditAction<Object> contentStoragesTableEdit;

    @Subscribe
    public void onInit(final InitEvent event) {
        contentStoragesTableEdit.setViewId("ext_ContentStorage.detail");
    }


}