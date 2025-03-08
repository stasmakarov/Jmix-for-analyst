package com.company.jmixforanalyst.action;

import com.company.jmixforanalyst.dto.UploadResult;
import com.company.jmixforanalyst.service.StormService;
import com.vaadin.flow.component.Component;
import io.jmix.bpm.entity.ContentStorage;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.action.ActionType;
import io.jmix.flowui.action.list.ItemTrackingAction;
import org.springframework.beans.factory.annotation.Autowired;

@ActionType("uploadToStorm")
public class UploadToStormAction<E> extends ItemTrackingAction<E> {

    @Autowired
    private Notifications notifications;

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
                    UploadResult uploadResult = stormService.uploadToStorm(contentStorage);
                    if (uploadResult.result()) {
                        notifications.create("Upload to Storm", uploadResult.message())
                                .withType(Notifications.Type.SUCCESS)
                                .show();
                    } else {
                        notifications.create(uploadResult.message())
                                .withType(Notifications.Type.ERROR)
                                .show();
                    }
                }
            }
        }
    }

}
