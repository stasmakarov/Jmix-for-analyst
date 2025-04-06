package com.company.jmixforanalyst.view.bpmleadform;


import com.company.jmixforanalyst.entity.Lead;
import com.company.jmixforanalyst.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.bpmflowui.processform.ProcessFormContext;
import io.jmix.bpmflowui.processform.annotation.Outcome;
import io.jmix.bpmflowui.processform.annotation.OutputVariable;
import io.jmix.bpmflowui.processform.annotation.ProcessForm;
import io.jmix.bpmflowui.processform.annotation.ProcessVariable;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ProcessForm(outcomes = {
        @Outcome(id = "complete")
}, outputVariables = {
        @OutputVariable(name = "comment", type = String.class),
        @OutputVariable(name = "lead", type = Lead.class)
})
@Route(value = "bpm-lead-form", layout = MainView.class)
@ViewController(id = "jal_BpmLeadForm")
@ViewDescriptor(path = "bpm-lead-form.xml")
public class BpmLeadForm extends StandardView {

    @Autowired
    private ProcessFormContext processFormContext;
    @ProcessVariable(name = "comment")
    @ViewComponent
    private TypedTextField<String> commentField;
    @ProcessVariable(name = "lead")
    private Lead lead;
    @ViewComponent
    DataContext dataContext;
    @ViewComponent
    private InstanceContainer<Lead> leadDc;

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        if (lead == null) {
            lead = dataContext.create(Lead.class);
        }
        leadDc.setItem(dataContext.merge(lead));
    }

    @Subscribe(id = "completeBtn", subject = "clickListener")
    protected void onCompleteBtnClick(ClickEvent<JmixButton> event) {
        dataContext.save();
        processFormContext.taskCompletion()
                .withOutcome("complete")
                .saveInjectedProcessVariables()
                .complete();
        closeWithDefaultAction();
    }
}