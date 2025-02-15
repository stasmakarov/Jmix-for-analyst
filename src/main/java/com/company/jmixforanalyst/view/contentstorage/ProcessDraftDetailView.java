package com.company.jmixforanalyst.view.contentstorage;

import com.company.jmixforanalyst.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.bpm.entity.ContentStorage;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "contentStorages/:id", layout = MainView.class)
@ViewController(id = "bpm_ProcessDraft.detail")
@ViewDescriptor(path = "process-draft-detail-view.xml")
@EditedEntityContainer("contentStorageDc")
public class ProcessDraftDetailView extends StandardDetailView<ContentStorage> {
}