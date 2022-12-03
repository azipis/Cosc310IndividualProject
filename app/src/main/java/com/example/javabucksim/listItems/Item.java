package com.example.javabucksim.listItems;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.designtoast.DesignToast;
import com.example.javabucksim.R;
import com.example.javabucksim.orders.orderTile;
import com.example.javabucksim.viewStockActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.travijuu.numberpicker.library.NumberPicker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Item extends AppCompatActivity {

    TextView productName, smol, mid, chonk, one, two, three, price12, price16, price20;
    Button back;
    FirebaseFirestore db;
    private FirebaseAuth mFirebaseAuth;
    ArrayList <String> statKeys;
    ArrayList statValues;
    NumberPicker numPick;
    Map<String,Object> map;
//    String sCalories, sProtein, sFat, sCarbs, sFiber, sSugar;
//    String mCalories, mProtein, mFat, mCarbs, mFiber, mSugar;
//    String cCalories, cProtein, cFat, cCarbs, cFiber, cSugar;
//    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        db = FirebaseFirestore.getInstance();

        getInventory();


        back = findViewById(R.id.backButton);
        productName = findViewById(R.id.productName);
        smol = findViewById(R.id.smol);
        mid = findViewById(R.id.mid);
        chonk = findViewById(R.id.chonk);
        one = findViewById(R.id.price_12oz);
        two = findViewById(R.id.price_16oz);
        three = findViewById(R.id.price_20oz);
        price12 = findViewById(R.id.price12oz);
        price16 = findViewById(R.id.price16oz);
        price20 = findViewById(R.id.price20oz);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("productName");
        productName.setText(name);


        if (name.equals("Pumpkin Spice Latte")) {
            price12.setText("$4.75");
            price16.setText("$5.25");
            price20.setText("$5.75");
        } else if (name.equals("Dark Roast")) {
            price12.setText("$2.25");
            price16.setText("$2.75");
            price20.setText("$3.25");
        } else if (name.equals("Medium Roast")) {
            price12.setText("$2.25");
            price16.setText("$2.75");
            price20.setText("$3.25");
        } else if (name.equals("Blonde Roast")) {
            price12.setText("$2.25");
            price16.setText("$2.75");
            price20.setText("$3.25");
        } else if (name.equals("Cappuccino")) {
            price12.setText("$3.25");
            price16.setText("$3.75");
            price20.setText("$4.25");
        } else if (name.equals("Iced Pumpkin Spice Latte")) {
            price12.setText("$4.75");
            price16.setText("$5.25");
            price20.setText("$5.75");
        } else if (name.equals("Iced Coffee")) {
            price12.setText("$2.25");
            price16.setText("$2.75");
            price20.setText("$3.25");
        } else if (name.equals("Cold Brew")) {
            price12.setText("$2.25");
            price16.setText("$2.75");
            price20.setText("$3.25");
        } else if (name.equals("Iced Cappuccino")) {
            price12.setText("$3.25");
            price16.setText("$3.75");
            price20.setText("$4.25");
        } else if (name.equals("Pumpkin Chai Latte")) {
            price12.setText("$4.75");
            price16.setText("$5.25");
            price20.setText("$5.75");
        } else if (name.equals("Tea")) {
            price12.setText("$2.25");
            price16.setText("$2.75");
            price20.setText("$3.25");
        } else if (name.equals("Hot Chocolate")) {
            price12.setText("$3.25");
            price16.setText("$3.75");
            price20.setText("$4.25");
        } else if (name.equals("Chai Latte")) {
            price12.setText("$3.25");
            price16.setText("$3.75");
            price20.setText("$4.25");
        } else if (name.equals("Matcha Latte")) {
            price12.setText("$3.25");
            price16.setText("$3.75");
            price20.setText("$4.25");
        } else if (name.equals("Iced Pumpkin Chai Latte")) {
            price12.setText("$4.75");
            price16.setText("$5.25");
            price20.setText("$5.75");
        } else if (name.equals("Iced Tea")) {
            price12.setText("$2.25");
            price16.setText("$2.75");
            price20.setText("$3.25");
        } else if (name.equals("Iced Chai Latte")) {
            price12.setText("$3.25");
            price16.setText("$3.75");
            price20.setText("$4.25");
        } else if (name.equals("Iced Matcha Latte")) {
            price12.setText("$3.25");
            price16.setText("$3.75");
            price20.setText("$4.25");
        } else if (name.equals("Juice")) {
            smol.setText("Orange");
            mid.setText("Apple");
            chonk.setText("Cranberry");

            one.setText("Orange:");
            two.setText("Apple:");
            three.setText("Cranberry:");

            price12.setText("$3.25");
            price16.setText("$3.25");
            price20.setText("$3.25");
        } else if (name.equals("Flavour Shots")) {
            smol.setText("Vanilla");
            mid.setText("Hazelnut");
            chonk.setText("Caramel");

            one.setText("Vanilla:");
            two.setText("Hazelnut:");
            three.setText("Caramel:");

            price12.setText("$0.25");
            price16.setText("$0.25");
            price20.setText("$0.25");
        } else if (name.equals("Espresso")) {
            smol.setText("One");
            mid.setText("Two");
            chonk.setText("Three");

            one.setText("One:");
            two.setText("Two:");
            three.setText("Three:");

            price12.setText("$0.75");
            price16.setText("$0.75");
            price20.setText("$0.75");
        } else if (name.equals("Milk")) {
            smol.setText("Splash");
            mid.setText("Normal");
            chonk.setText("Extra");

            one.setText("Splash:");
            two.setText("Normal:");
            three.setText("Extra:");

            price12.setText("$0.25");
            price16.setText("$0.50");
            price20.setText("$0.75");
        } else if (name.equals("Cream")) {
            smol.setText("Splash");
            mid.setText("Normal");
            chonk.setText("Extra");

            one.setText("Splash:");
            two.setText("Normal:");
            three.setText("Extra:");

            price12.setText("$0.25");
            price16.setText("$0.50");
            price20.setText("$0.75");
        } else if (name.equals("Sugar")) {
            smol.setText("One");
            mid.setText("Two");
            chonk.setText("Three");

            one.setText("One:");
            two.setText("Two:");
            three.setText("Three:");

            price12.setText("$0.25");
            price16.setText("$0.50");
            price20.setText("$0.75");
        }


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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

                        Collection<String> keys = stats.keySet();
                        Collection <Object> values = stats.values();

                        statKeys = new ArrayList<>(keys);
                        statValues = new ArrayList<>(values);


                    }

                }
                else {
                    DesignToast.makeText(Item.this, "Error getting reports", DesignToast.LENGTH_SHORT, DesignToast.TYPE_ERROR).show();
                }
            }
        });
    }

    public void submit(View view) {

        map = new HashMap<>();

        numPick = findViewById(R.id.number_picker);
        int numItems = numPick.getValue();

        for(int i =0; i< statKeys.size(); i++)
        {

            int curNum = Integer.valueOf((String) statValues.get(i));
            curNum = curNum - numItems;
            String curValue = Integer.toString(curNum);

            map.put(statKeys.get(i), curValue);
        }


        String doc = "j9BQe3OtLP6XnUK66MWK";
        db.collection("Inventory").document(doc).update(map);

        finish();

    }

}