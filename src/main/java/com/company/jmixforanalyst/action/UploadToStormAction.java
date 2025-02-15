package com.company.jmixforanalyst.action;

import com.company.jmixforanalyst.service.StormService;
import com.vaadin.flow.component.Component;
import io.jmix.bpm.entity.ContentStorage;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.action.ActionType;
import io.jmix.flowui.action.list.ItemTrackingAction;
import org.springframework.beans.factory.annotation.Autowired;

@ActionType("uploadToStorm")
public class UploadToStormAction<E> extends ItemTrackingAction<E> {

    public UploadToStormAction(String id) {
        super(id);
        setText("Upload to Storm");
    }

    @Autowired
    private ViewNavigators viewNavigators;
    @Autowired
    private StormService stormService;

    @Override
    public void actionPerform(Component component) {
        if (getTarget() != null) {
            E selected = getTarget().getSingleSelectedItem();
            if (selected != null) {
                if (selected instanceof ContentStorage contentStorage) {
                    stormService.uploadToStorm(contentStorage);
                }
            }
        }
    }

}
