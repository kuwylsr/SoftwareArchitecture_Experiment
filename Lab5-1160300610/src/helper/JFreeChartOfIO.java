package helper;

import java.awt.Font;

import java.io.FileOutputStream;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;



public class JFreeChartOfIO {

  private static CategoryDataset getCategoryDataset(String file, long bufferR, long read,
      long streamR, long nioR, long bufferW, long write, long streamW, long nioW) {
    // 大关键字
    final String category1 = "Buffer";
    final String category2 = "Reader/Writer";
    final String category3 = "Stream";
    final String category4 = "Nio";

    // 小关键字
    final String aspect1 = "读文件";
    final String aspect2 = "写文件";

    // 创建分类数据集
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    // 向柱状图的数据集中添加数据
    dataset.setValue(bufferR, category1, aspect1);
    dataset.setValue(read, category2, aspect1);
    dataset.setValue(streamR, category3, aspect1);
    dataset.setValue(nioR, category4, aspect1);
    dataset.setValue(bufferW, category1, aspect2);
    dataset.setValue(write, category2, aspect2);
    dataset.setValue(streamW, category3, aspect2);
    dataset.setValue(nioW, category4, aspect2);

    return dataset;
  }

  public static JFreeChart getJFreeChart(String file, long bufferR, long read, long streamR,
      long nioR, long bufferW, long write, long streamW, long nioW) {
    CategoryDataset dataset =
        getCategoryDataset(file, bufferR, read, streamR, nioR, bufferW, write, streamW, nioW);
    // 生成JFreeChart对象
    JFreeChart chart = ChartFactory.createBarChart3D("读取以及写入" + file + "文件的IO时间比较图", "IO的种类",
        "时间/ms", dataset, PlotOrientation.VERTICAL, true, false, false);
    updateFont(chart);
    return chart;
  }

  public static void updateFont(JFreeChart chart) {
    //
    TextTitle textTitle = chart.getTitle();
    textTitle.setFont(new Font("黑体", Font.PLAIN, 20));
    //
    CategoryPlot categoryPlot = (CategoryPlot) chart.getCategoryPlot();
    CategoryAxis categoryAxis = categoryPlot.getDomainAxis();
    //
    LegendTitle legendTitle = chart.getLegend();
    legendTitle.setItemFont(new Font("黑体", Font.PLAIN, 16));
    //
    categoryAxis.setTickLabelFont(new Font("黑体", Font.PLAIN, 14));
    //
    categoryAxis.setLabelFont(new Font("黑体", Font.PLAIN, 14));
    //
    ValueAxis valueAxis = (ValueAxis) categoryPlot.getRangeAxis();
    valueAxis.setTickLabelFont(new Font("黑体", Font.PLAIN, 14));
    //
    valueAxis.setLabelFont(new Font("黑体", Font.PLAIN, 14));
    
    
//    BarRenderer barrenderer = new BarRenderer();
//    barrenderer.setMaximumBarWidth(0.1);
//    barrenderer.setMinimumBarLength(0.1);
//    plot.setRenderer(renderer);
    
    BarRenderer3D customBarRenderer =new BarRenderer3D(); 
    customBarRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());//显示每个柱的数值
    customBarRenderer.setItemLabelFont(new Font("黑体",Font.PLAIN,18));
    customBarRenderer.setBaseItemLabelsVisible(true);
    categoryPlot.setRenderer(customBarRenderer);
    
//    ItemLabelPosition itemLabelPosition= new ItemLabelPosition(
//        ItemLabelAnchor.OUTSIDE3,TextAnchor.CENTER_LEFT,
//        TextAnchor.CENTER_LEFT,90);
//    //设置正常显示的柱子label的position
//    customBarRenderer.setBasePositiveItemLabelPosition(itemLabelPosition);
//    customBarRenderer.setBaseNegativeItemLabelPosition(itemLabelPosition);
    
    ItemLabelPosition itemLabelPositionFallback=new ItemLabelPosition(   
        ItemLabelAnchor.OUTSIDE12,TextAnchor.CENTER, TextAnchor.CENTER,0);   
    customBarRenderer.setPositiveItemLabelPositionFallback(itemLabelPositionFallback);   
    customBarRenderer.setNegativeItemLabelPositionFallback(itemLabelPositionFallback); 

  }

  public static void updatePlot(JFreeChart chart) {

    CategoryPlot categroyPlot = chart.getCategoryPlot();

    categroyPlot.setForegroundAlpha(0.6f);

  }
  
}
