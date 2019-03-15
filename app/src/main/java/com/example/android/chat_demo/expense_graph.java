package com.example.android.chat_demo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class expense_graph extends AppCompatActivity {
    private static String TAG = "Main Activity";
    private float[] expenses = {23.2f,45.1f,15.32f,19.78f,58.65f,3.45f};
    private String[] monthName = {"January","February","March","April","May","June"};
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense_graph);
        Log.d(TAG, "onCreate: starting to create pie chart");
        pieChart = (PieChart) findViewById(R.id.idPieChart);
        Description description = new Description();
        description.setText("Monthly expenses");
        pieChart.setDescription(description);
        pieChart.setDrawEntryLabels(true);
        addDataSet();
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                int pos = e.toString().indexOf("(sum): ");
                String exp = e.toString().substring(pos+6);

                for (int i=0;i<expenses.length;i++){
                    if (expenses[i] == Float.parseFloat(exp)){
                        pos = i;
                        break;
                    }
                }
                String mon = monthName[pos+1];
                Toast.makeText(expense_graph.this, "Month: "+mon +"\n"+"Expense: "+exp,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    public void addDataSet(){
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> expense_entry = new ArrayList<>();
        ArrayList<String> months_entry = new ArrayList<>();
        for (int i=0;i<expenses.length;i++){
            expense_entry.add(new PieEntry(expenses[i],i));
        }
        for (int i=0;i<monthName.length;i++){
            months_entry.add(monthName[i]);
        }

        PieDataSet pieDataSet = new PieDataSet(expense_entry,"Expenses");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> color_names = new ArrayList<>();
        color_names.add(Color.GRAY);
        //color_names.add(Color.BLUE);
        color_names.add(Color.RED);
        color_names.add(Color.GREEN);
        color_names.add(Color.CYAN);
        color_names.add(Color.YELLOW);
        color_names.add(Color.MAGENTA);
        pieDataSet.setColors(color_names);
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}