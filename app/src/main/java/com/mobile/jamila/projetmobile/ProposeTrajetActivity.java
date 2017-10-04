package com.mobile.jamila.projetmobile;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.mobile.jamila.projetmobile.models.Proposition;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ProposeTrajetActivity extends AppCompatActivity {

    EditText pointDep;
    EditText pointArr;
    CheckBox allerRetour;
    Button continuer;
    EditText dateAller;
    EditText heureAller;
    EditText dateRetour;
    EditText heureRetour;
    Proposition proposition;
    java.text.SimpleDateFormat formater;
    RelativeLayout layoutRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.propose_trajet_iteneraire);

        pointDep=(EditText)findViewById(R.id.ETpointDepart);
        pointArr=(EditText)findViewById(R.id.ETpointArrivee);
        allerRetour=(CheckBox)findViewById(R.id.CBallerRetour);
        dateAller=(EditText)findViewById(R.id.ETdateAller);
        dateAller.setInputType(InputType.TYPE_NULL);
        heureAller=(EditText)findViewById(R.id.ETheureAller);
        heureAller.setInputType(InputType.TYPE_NULL);
        dateRetour=(EditText)findViewById(R.id.ETdateRetour);
        dateRetour.setInputType(InputType.TYPE_NULL);
        heureRetour=(EditText)findViewById(R.id.ETheureRetour);
        heureRetour.setInputType(InputType.TYPE_NULL);
        continuer=(Button)findViewById(R.id.Bcontinuer);
        proposition=new Proposition();

        String format = "dd/MM/yy";
        formater = new java.text.SimpleDateFormat( format );
        Date aujourdhui= new Date();
        dateAller.setHint(formater.format(aujourdhui));
        dateRetour.setHint(formater.format(aujourdhui));
        Calendar cal=Calendar.getInstance();
        final int heure=cal.get(Calendar.HOUR_OF_DAY);
        int minute=cal.get(Calendar.MINUTE);
        heureAller.setHint(heure+" h "+minute+" min");
        heureRetour.setHint(heure+" h "+minute+" min");
        proposition.setAllerReour(true);


        allerRetour.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                layoutRetour=(RelativeLayout)findViewById(R.id.retourLayout);
                if (!allerRetour.isChecked()){
                    layoutRetour.setVisibility(View.INVISIBLE);
                    proposition.setAllerReour(false);
                    proposition.setJourRetour(0);
                    proposition.setMoisRetour(0);
                    heureRetour.setText("100");
                    proposition.setHeureRetour(100);
                    proposition.setMinRetour(100);

                }else {
                    layoutRetour.setVisibility(View.VISIBLE);
                    dateRetour.setText("");
                    heureRetour.setText("");
                    proposition.setAllerReour(true);
                }
            }
        }
        );


       dateAller.setOnFocusChangeListener(new View.OnFocusChangeListener() {
           @Override
           public void onFocusChange(View v, boolean hasFocus) {
               if (dateAller.hasFocus()){

                   final Calendar c = Calendar.getInstance();
                   int mYear = c.get(Calendar.YEAR);
                   int mMonth = c.get(Calendar.MONTH);
                   int mDay = c.get(Calendar.DAY_OF_MONTH);

                   DatePickerDialog dpd = new DatePickerDialog(ProposeTrajetActivity.this,
                           new DatePickerDialog.OnDateSetListener() {

                               @Override
                               public void onDateSet(DatePicker view, int year,
                                                     int monthOfYear, int dayOfMonth) {

                                   String dateInString = dayOfMonth + "/"
                                           + (monthOfYear + 1) + "/" + year;
                                   dateAller.setText(dateInString);

                                   proposition.setJourAller(dayOfMonth);
                                   proposition.setMoisAller(monthOfYear);

                               }
                           }, mYear, mMonth, mDay);
                   dpd.show();
               }

           }

       });

        dateRetour.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                    if (dateRetour.hasFocus()){

                        final Calendar c = Calendar.getInstance();
                        int mYear = c.get(Calendar.YEAR);
                        int mMonth = c.get(Calendar.MONTH);
                        int mDay = c.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog dpd = new DatePickerDialog(ProposeTrajetActivity.this,
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {
                                        String dateInString = dayOfMonth + "/"
                                                + (monthOfYear + 1) + "/" + year;
                                        dateRetour.setText(dateInString);
                                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                                        proposition.setJourRetour(dayOfMonth);
                                        proposition.setMoisRetour(monthOfYear);
                                    }
                                }, mYear, mMonth, mDay);
                        dpd.show();

                    }


            }

        });

        heureAller.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                    if (heureAller.hasFocus()){

                        final Calendar c = Calendar.getInstance();
                        final int hour = c.get(Calendar.HOUR_OF_DAY);
                        int minute = c.get(Calendar.MINUTE);

                        TimePickerDialog tpd = new TimePickerDialog(
                                ProposeTrajetActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                heureAller.setText(selectedHour + " h " + selectedMinute+" min");
                                proposition.setHeureAller(selectedHour);
                                proposition.setMinAller(selectedMinute);

                            }},hour,minute,true);
                        tpd.show();
                    }


            }

        });

        heureRetour.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (heureRetour.hasFocus()){

                    final Calendar c = Calendar.getInstance();
                    int hour = c.get(Calendar.HOUR_OF_DAY);
                    int minute = c.get(Calendar.MINUTE);

                    // Launch Date Picker Dialog
                    TimePickerDialog tpd = new TimePickerDialog(
                            ProposeTrajetActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            heureRetour.setText(selectedHour + " h " + selectedMinute+" min");
                            proposition.setHeureRetour(selectedHour);
                            proposition.setMinRetour(selectedMinute);
                        }},hour,minute,true);
                    tpd.show();
                }
            }

        });

        continuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proposition.setVilleDep(pointDep.getText().toString());
                proposition.setVilleArr(pointArr.getText().toString());
                if(allerRetour.isChecked())
                {
                    if((proposition.getVilleDep()==null)||(proposition.getVilleArr()==null)||(proposition.getJourAller()==0)||(proposition.getMinAller()==0)||
                            (proposition.getHeureAller()==100)||(proposition.getMinAller()==100)||(proposition.getJourRetour()==0)|| (proposition.getMoisRetour()==0)||
                            (proposition.getHeureRetour()==100)||(proposition.getMinRetour()==100)){
                        Toast.makeText(ProposeTrajetActivity.this,"Veuillez remplir tous les champs",Toast.LENGTH_LONG).show();
                    }else{
                        Intent intent=new Intent(ProposeTrajetActivity.this, AnnonceTrajetActivity.class);
                        intent.putExtra("trajet",proposition);
                        startActivity(intent);
                    }

                }else {
                    if ((proposition.getVilleDep() == null) || (proposition.getVilleArr() == null) || (proposition.getJourAller()==0)||
                            (proposition.getHeureAller() == 100) || (proposition.getMinAller() == 100)) {
                        Toast.makeText(ProposeTrajetActivity.this, "Veuillez remplir tous les champs2", Toast.LENGTH_LONG).show();
                    }else {
                        Intent intent=new Intent(ProposeTrajetActivity.this, AnnonceTrajetActivity.class);
                        intent.putExtra("trajet",proposition);
                        startActivity(intent);
                    }
                }
            }
        });


    }
}
