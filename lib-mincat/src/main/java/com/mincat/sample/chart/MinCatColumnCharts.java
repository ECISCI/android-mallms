package com.mincat.sample.chart;

import android.content.Context;

import com.mincat.sample.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.animation.ChartDataAnimator;
import lecho.lib.hellocharts.animation.ChartViewportAnimator;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * @author Gin
 * @描述 HelloChart 柱状图
 */

public class MinCatColumnCharts {

    /**
     * 禁止创建本类对象
     */
    private MinCatColumnCharts() {
    }


    private static Axis bottom;

    private static Axis left;

    private static List<Column> columns;

    private static List<SubcolumnValue> values;

    private static ColumnChartData columnChartData;

    protected ChartDataAnimator dataAnimator;

    protected ChartViewportAnimator viewportAnimator;

    /**
     * 底部文字字体大小
     */
    private static int bottom_text_size = 10;
    /**
     * 左侧文字大小
     */
    private static int left_text_size = 10;
    /**
     * 设置是否支持手势方法缩小
     */
    private static boolean is_zoom_enable = true;

    public static void showColumnCharts(final Context context, ColumnChartView columnChartView, final List<Float> list, List<String> bottomExplain, String leftName, String bottomName, boolean isEnableShow) {

        columnChartView.setZoomEnabled(is_zoom_enable);
        columns = new ArrayList<>();
        values = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            values = new ArrayList<>();
            values.add(new SubcolumnValue(list.get(i), ChartUtils.pickColor()).setTarget(list.get(i)));
            Column column = new Column(values);
            column.setHasLabels(true);
            /**
             * isEnableShow = true  柱图显示时不加文字标注
             *
             *
             *sEnableShow = false 柱图显示时自动添加文字标注
             */
            column.setHasLabelsOnlyForSelected(isEnableShow);
            columns.add(column);


        }
        columnChartData = new ColumnChartData(columns);

        bottom = new Axis();

        left = new Axis();

        List<AxisValue> axisValue = new ArrayList<>();

        for (int i = 0; i < bottomExplain.size(); i++) {

            axisValue.add(new AxisValue(i).setLabel(bottomExplain.get(i)));
        }
        setAxisAttribute(leftName, bottomName);

        bottom.setValues(axisValue);

        columnChartData.setAxisXBottom(bottom);
        columnChartData.setAxisYLeft(left);


        setColumnsChartsAttribute(columnChartView);
        columnChartView.setColumnChartData(columnChartData);


    }

    /**
     * @param leftName   竖坐标名称
     * @param bottomName 横坐标名称
     * @描述 为Axis设置属性
     */
    private static void setAxisAttribute(String leftName, String bottomName) {
        bottom.setName(bottomName);
        bottom.setTextColor(R.color.md_yellow_500);
        // 设置横坐标每个点的说明是否倾斜显示
        bottom.setHasTiltedLabels(false);
        bottom.setTextSize(bottom_text_size);


        left.setName(leftName);
        left.setTextColor(R.color.md_blue_500);
        // bottom.setMaxLabelChars(7);
        left.setTextSize(left_text_size);


    }

    /**
     * @param columnChartView
     * @描述 设置 columnChartView 动画及滑动属性
     */
    private static void setColumnsChartsAttribute(ColumnChartView columnChartView) {
        columnChartView.setValueSelectionEnabled(true);
        //设置行为属性，支持缩放、滑动以及平移
        columnChartView.setInteractive(true);
        columnChartView.setZoomType(ZoomType.HORIZONTAL);
        columnChartView.setMaxZoom((float) 2);//最大方法比例
        columnChartView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        columnChartView.setZoomType(ZoomType.HORIZONTAL);
        //启动动画
        columnChartView.startDataAnimation(2000);

    }
}
