package com.company.jmixforanalyst.action;

import com.company.jmixforanalyst.entity.StormDiagramDto;
import com.company.jmixforanalyst.service.StormService;
import com.vaadin.flow.component.Component;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.action.ActionType;
import io.jmix.flowui.action.list.ItemTrackingAction;
import org.springframework.beans.factory.annotation.Autowired;

@ActionType("saveDraft")
public class SaveDraftAction<E> extends ItemTrackingAction<E> {

    public SaveDraftAction(String id) {
        super(id);
        setText("Save draft");
    }

    @Autowired
    private StormService stormService;
    @Autowired
    private Notifications notifications;

    @Override
    public void actionPerform(Component component) {
        if (getTarget() != null) {
            E selected = getTarget().getSingleSelectedItem();
            if (selected != null) {
                if (selected instanceof StormDiagramDto diagram) {
                    stormService.saveDraft(diagram.getId());

                    notifications.create("Storm diagram saved as draft: "
                            + diagram.getId() + " - " + diagram.getName())
                            .withType(Notifications.Type.SUCCESS)
                            .withDuration(3000)
                            .show();
                }
            }
        }
    }
}
