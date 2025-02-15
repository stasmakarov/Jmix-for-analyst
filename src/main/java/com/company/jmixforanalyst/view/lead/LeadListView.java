package com.company.jmixforanalyst.view.lead;

import com.company.jmixforanalyst.entity.Lead;
import com.company.jmixforanalyst.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "leads", layout = MainView.class)
@ViewController(id = "jal_Lead.list")
@ViewDescriptor(path = "lead-list-view.xml")
@LookupComponent("leadsDataGrid")
@DialogMode(width = "64em")
public class LeadListView extends StandardListView<Lead> {
}