package com.company.jmixforanalyst.view.stormdiagramdto;

import com.company.jmixforanalyst.dto.DiagramDetailsDto;
import com.company.jmixforanalyst.dto.DiagramDto;
import com.company.jmixforanalyst.dto.DiagramListResponse;
import com.company.jmixforanalyst.entity.StormDiagramDto;
import com.company.jmixforanalyst.service.StormService;
import com.company.jmixforanalyst.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.data.selection.SelectionEvent;
import com.vaadin.flow.router.Route;
import io.jmix.bpm.entity.ContentStorage;
import io.jmix.core.DataManager;
import io.jmix.core.LoadContext;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Route(value = "stormDiagramDtoes", layout = MainView.class)
@ViewController(id = "jal_StormDiagramDto.list")
@ViewDescriptor(path = "storm-diagram-dto-list-view.xml")
@LookupComponent("stormDiagramDtoesDataGrid")
@DialogMode(width = "50em")
public class StormDiagramDtoListView extends StandardListView<StormDiagramDto> {

    private List<DiagramDto> diagramDtos;

    @Autowired
    private DataManager dataManager;

    @ViewComponent
    private CollectionContainer<StormDiagramDto> stormDiagramDtoesDc;

    public StormDiagramDtoListView() {
        diagramDtos = new ArrayList<>();
    }

    public void setDiagramDtos(List<DiagramDto> diagramDtos) {
        this.diagramDtos = diagramDtos;
    }

    @Autowired
    private StormService stormService;

    @Subscribe(id = "loadBtn", subject = "clickListener")
    public void onLoadBtnClick(final ClickEvent<JmixButton> event) {
        List<StormDiagramDto> stormDiagramDtos = stormService.loadFromStorm();
        stormDiagramDtoesDc.setItems(stormDiagramDtos);
    }

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        List<StormDiagramDto> stormDiagrams = StormService.getStormDiagrams();
        stormDiagramDtoesDc.setItems(stormDiagrams);
    }

}
