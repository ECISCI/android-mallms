package com.mincat.sample.chart;

import android.graphics.Color;

import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * @author Gin
 * @描述 HelloChart 饼状图
 */

public class MinCatPieCharts {


    private static PieChartData pieChartData;
    /**
     * 环形图大小级别
     */
    private static float pei_center_circle_scale = 0.5f;

    /**
     * 设置环形中间的颜色
     */
    private static int center_circle_color = Color.WHITE;

    /**
     * 设置 饼状图透明度
     */
    private static float pie_chart_alpha = 1f;

    /**
     * 设置饼图大小
     */
    private static float circle_fill_ratio = 1f;

    /**
     * 显示表格
     */
    private static boolean pie_chart_data_has_lables = true;

    /**
     * 不用点击显示占的百分比
     */
    private static boolean pie_chart_data_has_lables_only_for_selected = false;

    /**
     * 占的百分比是否显示在饼图外面
     */
    private static boolean pie_chart_data_has_lables_outside = false;

    /**
     * 选择某一区域变大
     */
    private static boolean pie_chart_set_value_selection_enabled = true;


    // 禁止创建本类对象
    private MinCatPieCharts() {
    }

    public static void showPieCharts(PieChartView pie_chart, List<SliceValue> values, final List<String> leaf_data, final List<Integer> leaf_color, boolean isCenterCircle) {


        for (int i = 0; i < leaf_data.size(); ++i) {
            SliceValue sliceValue = new SliceValue((float) Integer.valueOf(leaf_data.get(i)), leaf_color.get(i));
            values.add(sliceValue);
        }
        pieChartData = new PieChartData();

        pieChartData.setHasLabels(pie_chart_data_has_lables);

        pieChartData.setHasLabelsOnlyForSelected(pie_chart_data_has_lables_only_for_selected);

        pieChartData.setHasLabelsOutside(pie_chart_data_has_lables_outside);

        //是否是环形显示
        pieChartData.setHasCenterCircle(isCenterCircle);

        //填充数据
        pieChartData.setValues(values);
        if (isCenterCircle) {

            pieChartData.setCenterCircleColor(center_circle_color);

            pieChartData.setCenterCircleScale(pei_center_circle_scale);
        }


        pie_chart.setPieChartData(pieChartData);
        pie_chart.setValueSelectionEnabled(pie_chart_set_value_selection_enabled);
        pie_chart.setAlpha(pie_chart_alpha);
        pie_chart.setCircleFillRatio(circle_fill_ratio);


    }

}
