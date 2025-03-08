package com.company.jmixforanalyst.view.lead1processform;


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
        @Outcome(id = "submit"),
        @Outcome(id = "reject")
}, outputVariables = {
        @OutputVariable(name = "comment", type = String.class),
        @OutputVariable(name = "leadVar", type = Lead.class)
})
@Route(value = "lead-1-process-form", layout = MainView.class)
@ViewController(id = "jal_Lead1ProcessForm")
@ViewDescriptor(path = "lead-1-process-form.xml")
public class Lead1ProcessForm extends StandardView {

    @Autowired
    private ProcessFormContext processFormContext;
    @ProcessVariable(name = "comment")
    @ViewComponent
    private TypedTextField<String> commentField;
    @ProcessVariable(name = "leadVar")
    private Lead leadVar;
    @ViewComponent
    DataContext dataContext;
    @ViewComponent
    private InstanceContainer<Lead> leadDc;

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        if (leadVar == null) {
            leadVar = dataContext.create(Lead.class);
        }
        leadDc.setItem(dataContext.merge(leadVar));
    }

    @Subscribe(id = "submitBtn", subject = "clickListener")
    protected void onSubmitBtnClick(ClickEvent<JmixButton> event) {
        dataContext.save();
        processFormContext.taskCompletion()
                .withOutcome("submit")
                .saveInjectedProcessVariables()
                .complete();
        closeWithDefaultAction();
    }

    @Subscribe(id = "rejectBtn", subject = "clickListener")
    protected void onRejectBtnClick(ClickEvent<JmixButton> event) {
        dataContext.save();
        processFormContext.taskCompletion()
                .withOutcome("reject")
                .saveInjectedProcessVariables()
                .complete();
        closeWithDefaultAction();
    }
}