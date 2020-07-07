package com.example.hailameci.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hailameci.R;
import com.example.hailameci.fragments.DatePickerFragment;
import com.example.hailameci.fragments.UpcomingMatchesFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class AddEditPlanActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    public static final String EXTRA_ID = "com.example.hailameci.activities.EXTRA_ID";
    public static final String EXTRA_TEAMHOME = "com.example.hailameci.activities.EXTRA_TEAMHOME";
    public static final String EXTRA_TEAMAWAY = "com.example.hailameci.activities.EXTRA_TEAMAWAY";
    public static final String EXTRA_DATE = "com.example.hailameci.activities.EXTRA_DATE";
    public static final String EXTRA_STADIUM = "com.example.hailameci.activities.EXTRA_STADIUM";
    public static final String EXTRA_ISHOMEMATCH = "com.example.hailameci.activities.EXTRA_ISHOMEMATCH";
    public static final String EXTRA_TICKETPRICE = "com.example.hailameci.activities.EXTRA_TICKETPRICE";
    public static final String EXTRA_NUMBEROFTICKETS = "com.example.hailameci.activities.EXTRA_NUMBEROFTICKETS";
    public static final String EXTRA_ARRIVALDATE = "com.example.hailameci.activities.EXTRA_ARRIVALDATE";
    public static final String EXTRA_LEAVINGDATE = "com.example.hailameci.activities.EXTRA_LEAVINGDATE";
    public static final String EXTRA_PLACETOSTAY = "com.example.hailameci.activities.EXTRA_PLACETOSTAY";

    private TextView textViewMatch;
    private RadioButton radioButtonHome;
    private RadioButton radioButtonAway;
    private TextView textViewStadium;
    private TextView textViewDate;
    private EditText editTextTicketPrice;
    private EditText editTextNumberTickets;
    private EditText editTextDateArrival;
    private EditText editTextDateLeaving;
    private EditText editTextPlaceToStay;

    private String homeTeam;
    private String awayTeam;
    private String stadium;
    private String date;


    public static final byte ARRIVAL = 0;
    public static final byte LEAVING = 1;
    private byte dateSetFor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan);

        textViewMatch = findViewById(R.id.textViewMatch);
        radioButtonHome = findViewById(R.id.radioButtonHome);
        radioButtonAway = findViewById(R.id.radioButtonAway);
        textViewStadium = findViewById(R.id.textViewStadium);
        textViewDate = findViewById(R.id.textViewDate);
        editTextTicketPrice = findViewById(R.id.editTextTicketPrice);
        editTextNumberTickets = findViewById(R.id.editTextNumberTickets);
        editTextDateArrival = findViewById(R.id.editTextDateArrival);
        editTextDateLeaving = findViewById(R.id.editTextDateLeaving);
        editTextPlaceToStay = findViewById(R.id.editTextPlaceToStay);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_ID)) {
            setTitle(getString(R.string.edit_plan));
            if(intent.getBooleanExtra(EXTRA_ISHOMEMATCH, false)) {
                radioButtonHome.toggle();
            } else {
                radioButtonAway.toggle();
            }
            homeTeam = intent.getStringExtra(EXTRA_TEAMHOME);
            awayTeam = intent.getStringExtra(EXTRA_TEAMAWAY);
            stadium = intent.getStringExtra(EXTRA_STADIUM);
            date = intent.getStringExtra(EXTRA_DATE);
            editTextTicketPrice.setText(String.valueOf(intent.getDoubleExtra(EXTRA_TICKETPRICE, 0)));
            editTextNumberTickets.setText(String.valueOf(intent.getIntExtra(EXTRA_NUMBEROFTICKETS, 0)));
            editTextDateArrival.setText(intent.getStringExtra(EXTRA_ARRIVALDATE));
            editTextDateLeaving.setText(intent.getStringExtra(EXTRA_LEAVINGDATE));
            editTextPlaceToStay.setText(intent.getStringExtra(EXTRA_PLACETOSTAY));
        } else {
            setTitle(getString(R.string.add_plan));
            homeTeam = intent.getStringExtra(UpcomingMatchesFragment.EXTRA_TEAMHOME);
            awayTeam = intent.getStringExtra(UpcomingMatchesFragment.EXTRA_TEAMAWAY);
            stadium = intent.getStringExtra(UpcomingMatchesFragment.EXTRA_STADIUM);
            date = intent.getStringExtra(UpcomingMatchesFragment.EXTRA_DATE);
        }

        textViewMatch.setText(homeTeam + " - " + awayTeam);
        textViewStadium.setText(stadium);
        textViewDate.setText(date);

        Button buttonSelectArrivalDate = findViewById(R.id.buttonSelectArrivalDate);
        buttonSelectArrivalDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
                dateSetFor = ARRIVAL;
            }
        });

        Button buttonSelectLeavingDate = findViewById(R.id.buttonSelectLeavingDate);
        buttonSelectLeavingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
                dateSetFor = LEAVING;
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.SHORT).format(c.getTime());

        if(dateSetFor == ARRIVAL) {
            editTextDateArrival.setText(currentDateString);
        } else {
            editTextDateLeaving.setText(currentDateString);
        }
    }

    private void savePlan() {
        boolean isHomeMatch = radioButtonHome.isChecked();
        if(editTextTicketPrice.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.please_provide_price_of_ticket, Toast.LENGTH_SHORT).show();
            return;
        }
        double ticketPrice = Double.parseDouble(editTextTicketPrice.getText().toString());

        if(editTextNumberTickets.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.please_provide_number_of_tickets, Toast.LENGTH_SHORT).show();
            return;
        }
        int numberOfTickets = Integer.parseInt(editTextNumberTickets.getText().toString());

        String arrivalDate = editTextDateArrival.getText().toString();
        String leavingDate = editTextDateLeaving.getText().toString();
        String placeToStay = editTextPlaceToStay.getText().toString();

        if(arrivalDate.trim().isEmpty() || leavingDate.trim().isEmpty() || placeToStay.trim().isEmpty()) {
            Toast.makeText(this, R.string.please_provide_arriving_leaving_place_to_stay, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TEAMHOME, homeTeam);
        data.putExtra(EXTRA_TEAMAWAY, awayTeam);
        data.putExtra(EXTRA_DATE, date);
        data.putExtra(EXTRA_STADIUM, stadium);
        data.putExtra(EXTRA_ISHOMEMATCH, isHomeMatch);
        data.putExtra(EXTRA_TICKETPRICE, ticketPrice);
        data.putExtra(EXTRA_NUMBEROFTICKETS, numberOfTickets);
        data.putExtra(EXTRA_ARRIVALDATE, arrivalDate);
        data.putExtra(EXTRA_LEAVINGDATE, leavingDate);
        data.putExtra(EXTRA_PLACETOSTAY, placeToStay);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if(id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_plan_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_plan:
                savePlan();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
