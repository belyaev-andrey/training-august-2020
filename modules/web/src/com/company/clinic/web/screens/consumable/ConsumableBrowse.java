package com.company.clinic.web.screens.consumable;

import com.haulmont.charts.gui.components.charts.SerialChart;
import com.haulmont.charts.gui.data.DataItem;
import com.haulmont.charts.gui.data.ListDataProvider;
import com.haulmont.charts.gui.data.MapDataItem;
import com.haulmont.charts.gui.data.SimpleDataItem;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.*;
import com.company.clinic.entity.Consumable;
import com.haulmont.reports.gui.actions.RunReportAction;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@UiController("clinic_Consumable.browse")
@UiDescriptor("consumable-browse.xml")
@LookupComponent("consumablesTable")
@LoadDataBeforeShow
public class ConsumableBrowse extends StandardLookup<Consumable> {
    @Inject
    private Button consumablesTableRunReportBtn;

    @Inject
    private SerialChart consumablesPriceChart;

    @Subscribe
    public void onInit(InitEvent event) {
        consumablesTableRunReportBtn.setAction(new RunReportAction());
    }

    @Subscribe(id = "consumablesDc", target = Target.DATA_CONTAINER)
    public void onConsumablesDcCollectionChange(CollectionContainer.CollectionChangeEvent<Consumable> event) {
        List<DataItem> items = event.getSource().getItems().stream()
                .map(c -> MapDataItem.of("name", c.getTitle(), "price", c.getPrice()))
                .collect(Collectors.toList());

        consumablesPriceChart.setDataProvider(new ListDataProvider(items));
    }


    public static class ConsumablePrice {
        private String name;
        private BigDecimal price;

        public ConsumablePrice(String name, BigDecimal price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }

}