package com.company.jmixforanalyst.action;

import com.vaadin.flow.component.Component;
import io.jmix.bpm.entity.ContentStorage;
import io.jmix.bpmflowui.view.modeler.BpmnModelerView;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.action.ActionType;
import io.jmix.flowui.action.list.ItemTrackingAction;
import io.jmix.flowui.component.UiComponentUtils;
import io.jmix.flowui.view.View;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@ActionType("openModeler")
public class OpenModelerAction<E> extends ItemTrackingAction<E> {

    @Autowired
    private ViewNavigators viewNavigators;

    public OpenModelerAction(String id) {
        super(id);
        setText("Open in modeler");
    }



    @Override
    public void actionPerform(Component component) {
        if (getTarget() != null) {
            E selected = getTarget().getSingleSelectedItem();
            if (selected != null) {
                if (selected instanceof ContentStorage contentStorage) {
                    byte[] content = contentStorage.getContent();
                    if (content != null){
                        openInModeler(content, component);
                    }
                }
            }
        }
    }

    protected void openInModeler(byte[] content, Component component) {
        try (InputStream is = new ByteArrayInputStream(content)) {
            String bpmnXml = IOUtils.toString(is, StandardCharsets.UTF_8);

            View<?> originView = UiComponentUtils.getView(component);
            if (originView != null) {
                viewNavigators.view(originView, BpmnModelerView.class)
                        .withAfterNavigationHandler(event ->
                                event.getView().setBpmnXml(bpmnXml)
                        )
                        .withBackwardNavigation(true)
                        .navigate();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading BPMN XML", e);
        }
    }
}
