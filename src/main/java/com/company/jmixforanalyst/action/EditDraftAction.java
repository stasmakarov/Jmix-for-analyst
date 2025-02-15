package com.company.jmixforanalyst.action;

import com.company.jmixforanalyst.view.contentstorage.ProcessDraftDetailView;
import com.vaadin.flow.component.Component;
import io.jmix.bpm.entity.ContentStorage;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.action.ActionType;
import io.jmix.flowui.action.list.EditAction;
import io.jmix.flowui.component.UiComponentUtils;
import io.jmix.flowui.view.View;
import org.springframework.beans.factory.annotation.Autowired;

@ActionType("editProcessDraft")
public class EditDraftAction<E> extends EditAction<E> {

    public EditDraftAction(String id) {
        super(id);
        setText("Edit draft");
    }

    @Autowired
    private ViewNavigators viewNavigators;

    @Override
    public void actionPerform(Component component) {
        if (getTarget() != null) {
            E selected = getTarget().getSingleSelectedItem();
            if (selected != null) {
                if (selected instanceof ContentStorage contentStorage) {
                    openEditor(contentStorage, component);
                }
            }
        }
    }

    private void openEditor(ContentStorage contentStorage, Component component) {
        View<?> originView = UiComponentUtils.getView(component);
        if (originView != null) {
            viewNavigators.detailView(originView, ContentStorage.class)
                    .withViewClass(ProcessDraftDetailView.class)
                    .editEntity(contentStorage)
                    .withBackwardNavigation(true)
                    .navigate();
        }
    }
}
