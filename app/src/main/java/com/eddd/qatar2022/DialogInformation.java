package com.eddd.qatar2022;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;

public class DialogInformation extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        //defino qué voy a inflar (una View) y cuál vista voy a inflar, en este caso será la dialog_information
        View dialogView = inflater.inflate(R.layout.dialog_information, null);

        final Button btn_close = dialogView.findViewById(R.id.btn_close);

        //coloco el título del fragmento
        builder.setView(dialogView).setMessage(getResources().getString(R.string.ayuda));

        //si el usuario presiona close, se cierra el diálogo
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return  builder.create();
    }

}
