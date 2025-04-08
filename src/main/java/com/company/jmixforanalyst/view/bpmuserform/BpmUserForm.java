package com.company.jmixforanalyst.view.bpmuserform;


import com.company.jmixforanalyst.entity.User;
import com.company.jmixforanalyst.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.bpmflowui.processform.ProcessFormContext;
import io.jmix.bpmflowui.processform.annotation.Outcome;
import io.jmix.bpmflowui.processform.annotation.OutputVariable;
import io.jmix.bpmflowui.processform.annotation.ProcessForm;
import io.jmix.bpmflowui.processform.annotation.ProcessVariable;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@ProcessForm(outcomes = {
        @Outcome(id = "submit"),
        @Outcome(id = "reject")
}, outputVariables = {
        @OutputVariable(name = "userVar", type = User.class)
})
@Route(value = "bpm-user-form", layout = MainView.class)
@ViewController(id = "jal_BpmUserForm")
@ViewDescriptor(path = "bpm-user-form.xml")
public class BpmUserForm extends StandardView {

    @Autowired
    private ProcessFormContext processFormContext;
    @ProcessVariable(name = "userVar")
    private User userVar;
    @ViewComponent
    DataContext dataContext;
    @ViewComponent
    private InstanceContainer<User> userDc;

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        if (userVar == null) {
            userVar = dataContext.create(User.class);
        }
        userDc.setItem(dataContext.merge(userVar));
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