package com.puzzle.languine;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.puzzle.languine.datamodel.Festividad;
import com.puzzle.languine.ui.MaterialActivity;
import com.puzzle.languine.ui.adapter.FestividadesAdapter;
import com.puzzle.languine.utils.IntentConts;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Festividades extends MaterialActivity {

    private int[] span_images = {R.drawable.fest1,R.drawable.fest2,R.drawable.fest3, R.drawable.fest4, R.drawable.fest5, R.drawable.fest6,R.drawable.fest7, R.drawable.fest8, R.drawable.fest9, R.drawable.fest10};
    private int[] mex_images = {R.drawable.fest1, R.drawable.fest2, R.drawable.fest4, R.drawable.fest_mex4, R.drawable.fest_mex5, R.drawable.fest6,R.drawable.fest_mex7, R.drawable.fest_mex8, R.drawable.fest_mex9, R.drawable.fest10};

    private int[] span_sound = {R.raw.h01_s, R.raw.h02_s,R.raw.h03_s, R.raw.h04_s, R.raw.h05_s, R.raw.h06_s, R.raw.h07_s, R.raw.h08_s, R.raw.h09_s, R.raw.h10_s};
    private int[] mex_sound = {R.raw.h01_m, R.raw.h02_m,R.raw.h03_m, R.raw.h04_m, R.raw.h05_m, R.raw.h06_m, R.raw.h07_m, R.raw.h08_m, R.raw.h09_m, R.raw.h10_m};


    private String language;

    private String[] dates = {"Uno de enero","Seis de enero", "Marzo o abril", "Uno de mayo", "25 de julio", "12 de octubre", "Uno de noviembre", "Seis de diciembre", "Ocho de diciembre", "25 de diciembre"};
    private String[] spanishNames = {"Año Nuevo"," Día de Reyes", "Semanta santa", "Día del Trabajo", "Día de Santiago", "Día de la Hispanidad", "Día de Todos los Santos", "Día de la Constitucíon Española", "Inmaculada Conceptíon", "Día de la Navidad"};
    private String[] englishNames = {"New Year's Day","Epiphany", "Easter(March or April)", "Labour Day", "St James' Day", "Columbus Day(National Day)", "All Saints' Day", "Constitution Day", "Immaculate Conception", "Christmas Day"};

    private String[] mexDates = {"Primero de enero","El 6 de enero", "Primero de mayo", "El Cinco de mayo", "16 de septiembre", "El 12 de octubre", "El 2 de noviembre", "El 20 de noviembre", "12 de diciembre", "El 25 de diciembre"};
    private String[] mexNames = {"El año nuevo","El Día de los Reyes Magos", "Día del Trabajo", "La Batalla de Puebla", "La Independencia de México", "El Descumbrimiento de América", "El Día de los Muertos", "La Revolución de Mexcicana", "Día de la Virgen de Guadalupe", "La Navidad"};
    private String[] mexEnglishNames = {"New Year's Day","Epiphany", "Labour Day", "5th May-Batalla of Puebla", "Independence Day", "Discovery of Americas", "Day of the Dead", "Revolution Day", "Day of the virgin of Guadalupe", "Christmas Day"};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_festividades);

        TextView pageTitle = (TextView) findViewById(R.id.festividad_title);
        language = getIntent().getStringExtra(IntentConts.LANGUAGE);

        if(language.equals("Mexican"))
        {
            pageTitle.setText("Festivals and national holidays - Mexico");
        }

        setupRecyclerView();

        addFragment(new com.puzzle.languine.RecordingToolFragment());
    }

    private void setupRecyclerView()
    {
        RecyclerView cardList = (RecyclerView) findViewById(R.id.card_list);
        cardList.setHasFixedSize(true);

        // use a linear layout manager
        cardList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // specify an adapter (see also next example)
        ArrayList<Festividad> myDataset = new ArrayList<Festividad>();
        for (int i = 0; i < dates.length; ++i)
        {
            if(language.equals("Spanish"))
            {
                myDataset.add(new Festividad(span_images[i], dates[i], spanishNames[i], englishNames[i]));
            }
            else
            {
                myDataset.add(new Festividad(mex_images[i], mexDates[i], mexNames[i], mexEnglishNames[i]));
            }
        }

        FestividadesAdapter festividadesAdapter = new FestividadesAdapter(myDataset);
        cardList.setAdapter(festividadesAdapter);
    }

    public void showPopup(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("This section contains two pages.   1, Festivals and National Holidays in Spain. " +
                "2, Festivals and National Holidays in Mexico. Translations and sounds are available in both pages.")
                .setTitle("Holidays");

        builder.setPositiveButton("OK", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}