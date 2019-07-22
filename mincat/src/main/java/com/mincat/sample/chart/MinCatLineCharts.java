package com.mincat.sample.chart;

import android.view.View;

import com.mincat.sample.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * @author Gin
 * @描述 线形图
 */

public class MinCatLineCharts {

    private static Line line;

    /**
     * x坐标轴
     */
    private static Axis axisX;

    /**
     * y坐标轴
     */
    private static Axis axisY;

    private static List<Line> lines;
    /**
     * X轴坐标字体大小
     */
    private static int x_line_test_size = 9;

    /**
     * y轴坐标字体大小
     */
    private static int y_line_test_size = 9;

    private static LineChartData data;

    private static int chart_text_color = R.color.white;

    // 禁止创建本类对象
    private MinCatLineCharts() {
    }

    /**
     * @param lineChart    LineChartView对象
     * @param mPointValues
     * @param listPoint
     * @param mAxisValues
     * @param listX
     * @param descX        X轴坐标文字描述
     * @param descY        Y轴坐标文字描述
     */
    public static void showLineCharts(LineChartView lineChart,
                                      List<PointValue> mPointValues,
                                      List<Integer> listPoint,
                                      List<AxisValue> mAxisValues,
                                      List<String> listX,
                                      String descX, String descY) {

        // 获取x轴的标注
        getAxisLables(mAxisValues, listX);

        // 获取坐标点
        getAxisPoints(mPointValues, listPoint);


        //折线的颜色
        line = new Line(mPointValues).setColor(R.color.md_blue_200).setCubic(false);
        lines = new ArrayList<Line>();

        // 折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.SQUARE）
        line.setShape(ValueShape.CIRCLE);
        // 曲线是否平滑//曲线是否平滑
        line.setCubic(true);
        // 是否填充曲线的面积
        line.setFilled(true);

        // line.setHasLabels(true);//曲线的数据坐标是否加上备注

        // 点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLabelsOnlyForSelected(true);

        // 是否用直线显示。如果为false 则没有曲线只有点显示
        line.setHasLines(true);

        // 是否显示圆点 如果为false 则没有原点只有点显示
        line.setHasPoints(true);
        lines.add(line);

        data = new LineChartData();

        data.setLines(lines);

        axisX = new Axis();
        axisX.setHasTiltedLabels(true);
        // 设置字体颜色
        axisX.setTextColor(chart_text_color);
        // 表格名称
        axisX.setName(descX);
        // 设置字体大小
        axisX.setTextSize(x_line_test_size);
        // 最多几个X轴坐标
        axisX.setMaxLabelChars(7);
        // 填充X轴的坐标名称
        axisX.setValues(mAxisValues);
        // x 轴在底部
        data.setAxisXBottom(axisX);
        //  data.setAxisXTop(axisX);  //x 轴在顶部


        // Y轴
        axisY = new Axis();
        // 默认是3，只能看最后三个数字
        axisY.setMaxLabelChars(7);
        // y轴标注
        axisY.setName(descY);
        // 设置字体大小
        axisY.setTextSize(y_line_test_size);

        data.setAxisYLeft(axisY);  //Y轴设置在左边
        // data.setAxisYRight(axisY);  //y轴设置在右边

        //设置行为属性，支持缩放、滑动以及平移
        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
        lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart.setLineChartData(data);
        lineChart.setVisibility(View.VISIBLE);


    }

    /**
     * X 轴的显示
     */
    private static void getAxisLables(List<AxisValue> mAxisValues, List<String> listX) {
        for (int i = 0; i < listX.size(); i++) {
            mAxisValues.add(new AxisValue(i).setLabel(listX.get(i)));
        }
    }

    /**
     * 图表的每个点的显示
     */
    private static void getAxisPoints(List<PointValue> mPointValues, List<Integer> listPoint) {
        for (int i = 0; i < listPoint.size(); i++) {
            mPointValues.add(new PointValue(i, listPoint.get(i)));
        }
    }


}
