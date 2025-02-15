package com.company.jmixforanalyst.view.lead;

import com.company.jmixforanalyst.entity.Lead;
import com.company.jmixforanalyst.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "leads/:id", layout = MainView.class)
@ViewController(id = "jal_Lead.detail")
@ViewDescriptor(path = "lead-detail-view.xml")
@EditedEntityContainer("leadDc")
public class LeadDetailView extends StandardDetailView<Lead> {
}