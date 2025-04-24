package com.company.jmixforanalyst.view.processdefinitionlist;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.bpmflowui.view.processdefinition.ProcessDefinitionListView;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.DefaultMainViewParent;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import org.flowable.engine.repository.ProcessDefinition;

import java.util.List;

@Route(value = "bpm/processdefinitions", layout = DefaultMainViewParent.class)
@ViewController(id = "bpm_ProcessDefinition.list")
@ViewDescriptor(path = "jal-process-definition-list-view.xml")
public class JalProcessDefinitionListView extends ProcessDefinitionListView {
    @Subscribe(id = "deleteAllBtn", subject = "clickListener")
    public void onDeleteAllBtnClick(final ClickEvent<JmixButton> event) {
        List<ProcessDefinition> definitions = repositoryService.createProcessDefinitionQuery().list();
        for (ProcessDefinition definition : definitions) {
            repositoryService.deleteDeployment(definition.getDeploymentId(), true);
        }
        applyFilter();
    }
}