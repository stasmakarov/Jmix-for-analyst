package com.company.jmixforanalyst.view.stormdiagramdto;

import com.company.jmixforanalyst.entity.StormDiagramDto;
import com.company.jmixforanalyst.service.StormService;
import com.company.jmixforanalyst.view.main.MainView;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;
import io.jmix.core.LoadContext;
import io.jmix.core.SaveContext;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Route(value = "stormDiagramDtoes/:id", layout = MainView.class)
@ViewController(id = "jal_StormDiagramDto.detail")
@ViewDescriptor(path = "storm-diagram-dto-detail-view.xml")
@EditedEntityContainer("stormDiagramDtoDc")
public class StormDiagramDtoDetailView extends StandardDetailView<StormDiagramDto> {

    @Autowired
    private StormService stormService;

    @ViewComponent
    private InstanceContainer<StormDiagramDto> stormDiagramDtoDc;

    private StormDiagramDto stormDiagramDto;

    @Subscribe
    public void onQueryParametersChange(final QueryParametersChangeEvent event) {
        List<String> ids = event.getQueryParameters().getParameters().get("id");
        if (ids != null && !ids.isEmpty()) {
            Optional<StormDiagramDto> dtoOptional = stormService.getStormDiagramDtoById(ids.get(0));
            dtoOptional.ifPresent(stormDiagramDto -> this.stormDiagramDto = stormDiagramDto);
        }
    }

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        stormDiagramDtoDc.setItem(stormDiagramDto);
    }

}
