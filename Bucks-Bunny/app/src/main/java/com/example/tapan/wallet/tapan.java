package com.example.tapan.wallet;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class tapan extends AppCompatActivity {

    private Button Save;
    //Save=(Button)findViewbyId(R.id.btnsave);
    PieChart pc;
    //PieView pv=new PieView(tapan.this);

    public static String TAG="Expense Chart";

    private float[] ydata = {20f,10f,30f,10f,30f};
    private String[] xdata={"Food","Clothing","Travel","Health","Others"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tapan);
        //pc.init();
        //pc.calculateOffsets();
        //pc.calcMinMax();
        //pc.getDataSetIndexForIndex(1);
        //float a[]=pc.getDrawAngles();
        //boolean ans=pc.isDrawEntryLabelsEnabled();
        //pc.calcAngle();
        //float b[]=pc.getAbsoluteAngles();
       // pc = (PieChart)findViewById(R.id.mPChart);
        //pc.setUsePercentValues(true);
        //pc.getDescription().setEnabled(false);
        //pc.setExtraOffsets(5, 10, 5, 5);
        Log.d(TAG, "onCreate: Start");
        pc=(PieChart)findViewById(R.id.mPChart);

        //pc.setDescription("Expense this month per category");
        pc.setRotationEnabled(true);
        pc.setDrawEntryLabels(true);

        addDataSet();
        pc.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d(TAG, "onValueSelected: ");
                Log.d(TAG, "onValueSelected: "+e.toString());
                Log.d(TAG, "onValueSelected: "+h.toString());

                int pos1=e.toString().indexOf("wait");
                String exp=e.toString().substring((pos1+7));

                for(int i=0;i<ydata.length;i++)
                {
                    if(ydata[i]==Float.parseFloat("expense"));
                    pos1=i;
                    break;
                }
                String employee = xdata[pos1 + 1];
                // Toast.makeText(tapan.this,"Employee" + employee + "\n",Toast LENGTH_LONG);
            }


            @Override
            public void onNothingSelected() {

            }
        });

    }
    private void addDataSet()
    {
        Log.d(TAG, "addDataSet: Started");
        ArrayList<PieEntry> yEntry=new ArrayList<>();
        ArrayList<String> xEntry=new ArrayList<>();

        for(int i=0;i<ydata.length;i++)
        {
            yEntry.add(new PieEntry(ydata[i],1));
        }
        for(int i=1;i<xdata.length;i++)
        {
            xEntry.add(xdata[i]);
        }

        PieDataSet pieds = new PieDataSet(yEntry,"Expense Sheet");
        pieds.setSliceSpace(2);
        pieds.setValueTextSize(12);



        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.GRAY);
        colors.add(Color.YELLOW);
        pieds.setColors(colors);


        Legend legend = pc.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData pd = new PieData(pieds);
        pc.setData(pd);
        pc.invalidate();

    }
}
