package com.eddd.qatar2022;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DialogStatistics extends DialogFragment {

    int cantidadFiguritas;
    final int TOTAL_FIGURITAS = 646;

    public void setCantidadFiguritas(int cantidadFiguritas){
        this.cantidadFiguritas = cantidadFiguritas;
    }
    DecimalFormat formatTwo = new DecimalFormat("#.##");

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        //defino qué voy a inflar (una View) y cuál vista voy a inflar, en este caso será la dialog_statistics
        View dialogView = inflater.inflate(R.layout.dialog_statistics, null);

        final Button btn_close = dialogView.findViewById(R.id.btn_close);
        final PieChart pieChart = dialogView.findViewById(R.id.pieChart);
        final TextView numberFText = dialogView.findViewById(R.id.numberFText);

        float percentCompleted = cantidadFiguritas*100.0f / TOTAL_FIGURITAS;
        numberFText.setText(String.valueOf(cantidadFiguritas) + " " + getString(R.string.of) + " " + String.valueOf(TOTAL_FIGURITAS) + " (" + formatTwo.format(percentCompleted) + "%)");
        int restantes = TOTAL_FIGURITAS - cantidadFiguritas;

        //coloco el título del fragmento
        builder.setView(dialogView).setMessage(getResources().getString(R.string.estad_album));

        //si el usuario presiona close, se cierra el diálogo
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        Description description = new Description();
        description.setText(getResources().getString(R.string.fig_restantes));
        description.setTextSize(15);

        pieChart.setDescription(description);

        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(cantidadFiguritas, getResources().getString(R.string.tengo)));
        pieEntries.add(new PieEntry(restantes, getResources().getString(R.string.faltan)));

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);

        return  builder.create();
    }


}
