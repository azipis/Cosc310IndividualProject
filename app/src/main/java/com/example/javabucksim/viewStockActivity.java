package com.example.javabucksim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.chad.designtoast.DesignToast;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class viewStockActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button back;
    ArrayList <String> statKeys;
    ArrayList statValues;
    ArrayList barArraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stock);

        back = findViewById(R.id.backButton);

        mFirebaseAuth = FirebaseAuth.getInstance();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getInventory();



    }

    public void makeBarChart(){


        BarChart barChart = findViewById(R.id.barchart);
        BarDataSet barDataSet = new BarDataSet(barArraylist,"Inventory");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        //color bar data set
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //text color
        barDataSet.setValueTextColor(Color.BLACK);
        //setting text size
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(true);
        barChart.setVisibleXRangeMaximum(5);

        statKeys.add(0, "dummy");

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(statKeys));
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);

        barChart.getDescription().setEnabled(false);
        barChart.invalidate();

    }

    // puts retrieved data from
    private void getData()
    {
        barArraylist = new ArrayList();
        int count = 1;

        for (Object stock: statValues ) {

            float val = Float.parseFloat(stock.toString());
            barArraylist.add(new BarEntry(count, val));
            count++;
        }

        makeBarChart();

    }

    // get all item values and store them in arraylists
    private void getInventory() {

        String doc = "j9BQe3OtLP6XnUK66MWK";
        DocumentReference docRef = db.collection("Inventory").document(doc);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {
                        Map<String, Object> stats = document.getData();

                        Collection <String> keys = stats.keySet();
                        Collection <Object> values = stats.values();

                        statKeys = new ArrayList<>(keys);
                        statValues = new ArrayList<>(values);

                        getData();


                    }
                    else {

                    }
                }
                else {
                    DesignToast.makeText(viewStockActivity.this, "Error getting reports", DesignToast.LENGTH_SHORT, DesignToast.TYPE_ERROR).show();
                }
            }
        });
    }



}