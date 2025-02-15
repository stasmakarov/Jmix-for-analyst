package com.company.jmixforanalyst.action;

import com.company.jmixforanalyst.entity.StormDiagramDto;
import com.company.jmixforanalyst.view.contentstorage.ProcessDraftDetailView;
import com.company.jmixforanalyst.view.stormdiagramdto.StormDiagramDtoDetailView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.QueryParameters;
import io.jmix.flowui.action.ActionType;
import io.jmix.flowui.action.list.ReadAction;
import io.jmix.flowui.component.UiComponentUtils;
import io.jmix.flowui.view.View;

@ActionType("readStormDiagram")
public class ReadStormDiagramAction<E> extends ReadAction<E> {

    public ReadStormDiagramAction(String id) {
        super(id);
        setText("View diagram details");
    }

    @Override
    public void actionPerform(Component component) {
        if (getTarget() != null) {
            E selected = getTarget().getSingleSelectedItem();
            if (selected != null) {
                if (selected instanceof StormDiagramDto diagram) {
                    openReader(diagram, component);
                }
            }
        }
    }

    private void openReader(StormDiagramDto diagram, Component component) {
        View<?> originView = UiComponentUtils.getView(component);
        if (originView != null) {
            QueryParameters queryParameters = QueryParameters.of("id", diagram.getId());
            viewNavigators.detailView(originView, StormDiagramDto.class)
                    .withViewClass(StormDiagramDtoDetailView.class)
                    .withQueryParameters(queryParameters)
                    .withBackwardNavigation(true)
                    .navigate();
        }
    }
}
