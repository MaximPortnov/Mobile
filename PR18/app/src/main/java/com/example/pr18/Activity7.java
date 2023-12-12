package com.example.pr18;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.view.MenuItem;

public class Activity7 extends AppCompatActivity {
    ArrayList<Product> products = new ArrayList<Product>();
    BoxAdapter boxAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_7);
        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        fillData();
        boxAdapter = new BoxAdapter(this, products);

        // настраиваем список
        ListView lvMain = (ListView) findViewById(R.id.lvMain);
        lvMain.setAdapter(boxAdapter);
    }

    // генерируем данные для адаптера
    void fillData() {
        for (int i = 1; i <= 20; i++) {
            products.add(new Product("Product " + i, i * 1000,
                    R.drawable.ic_launcher_background, false));
        }
    }
    // выводим информацию о корзине

    public void showResult(View v) {
        String result = "Товары в корзине:";
        for (Product p : boxAdapter.getBox()) {
            if (p.box)
                result += "\n" + p.name;
        }
        Toast toast = Toast.makeText(this, result, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0); // устанавливаем выравнивание
        toast.setMargin(0, 0.1f); // устанавливаем отступы
        toast.show();
    }


//    public void showResult(View v) {
//        String result = "Товары в корзине:";
//        for (Product p : boxAdapter.getBox()) {
//            if (p.box)
//                result += "\n" + p.name;
//        }
//        LayoutInflater inflater = getLayoutInflater();
//        View layout = inflater.inflate(R.layout.custom_toast,
//                (ViewGroup) findViewById(R.id.custom_toast_container));
//
//        TextView text = (TextView) layout.findViewById(R.id.text);
//        text.setText(result);
//
//        Toast toast = new Toast(getApplicationContext());
//        toast.setGravity(Gravity.CENTER, 0, 0);
//        toast.setDuration(Toast.LENGTH_LONG);
//        toast.setView(layout);
//        toast.show();
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

