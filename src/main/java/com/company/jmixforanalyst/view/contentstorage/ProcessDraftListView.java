package com.company.jmixforanalyst.view.contentstorage;

import com.company.jmixforanalyst.service.StormService;
import com.company.jmixforanalyst.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.bpm.entity.ContentStorage;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "contentStorages", layout = MainView.class)
@ViewController(id = "bpm_ProcessDraft.list")
@ViewDescriptor(path = "process-draft-list-view.xml")
@LookupComponent("contentStoragesDataGrid")
@DialogMode(width = "64em")
public class ProcessDraftListView extends StandardListView<ContentStorage> {
}