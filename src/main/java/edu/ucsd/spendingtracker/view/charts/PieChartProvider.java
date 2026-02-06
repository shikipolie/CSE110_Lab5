package edu.ucsd.spendingtracker.view.charts;

import edu.ucsd.spendingtracker.model.Category;
import javafx.scene.Node;
import java.util.Map;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

public class PieChartProvider implements IChartProvider {
    @Override
    public Node createChart(Map<Category, Double> data) {
        PieChart chart = new PieChart();
        
        data.forEach((cat, sum) -> {
            PieChart.Data slice = new PieChart.Data(cat.name(), sum);
            chart.getData().add(slice);
        });
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        data.forEach((cat, sum) -> {
            series.getData().add(new XYChart.Data<>(cat.name(), sum));

        });

        for(PieChart.Data slice : chart.getData()) {
            String color = Category.valueOf(slice.getName()).color;
            if(slice != null) {
                slice.getNode().setStyle("-fx-pie-color: " + color + ";");
            }
        }
        
        chart.setLegendVisible(false);
        return chart;
    }

    @Override
    public String getDisplayName() {
        return "Pie Chart";
    }
}