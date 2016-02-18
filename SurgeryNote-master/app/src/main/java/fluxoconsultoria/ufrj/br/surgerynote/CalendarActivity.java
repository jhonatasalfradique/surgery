package fluxoconsultoria.ufrj.br.surgerynote;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import fluxoconsultoria.ufrj.br.surgerynote.model.dao.DaoFinancialData;
import fluxoconsultoria.ufrj.br.surgerynote.model.dao.DaoSurgery;
import fluxoconsultoria.ufrj.br.surgerynote.model.entity.FinancialData;
import fluxoconsultoria.ufrj.br.surgerynote.model.entity.Surgery;


public class CalendarActivity extends ActionBarActivity
{
    private Button menuButton;

    private ExtendedCalendarView calendar;

    private DaoSurgery daoSurgery = new DaoSurgery();
    private DaoFinancialData daoFinancialData = new DaoFinancialData();

    private List<Surgery> surgeries;
    private List<FinancialData> financialDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        this.surgeries = this.daoSurgery.getAll();
        this.financialDatas = this.daoFinancialData.getAll();

        this.menuButton = (Button) findViewById(R.id.menuButton);

        this.calendar = (ExtendedCalendarView) findViewById(R.id.calendar);

        this.menuButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CalendarActivity.this, MenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        calendar.setOnDayClickListener(new ExtendedCalendarView.OnDayClickListener()
        {
            @SuppressWarnings("deprecation")
            @Override
            public void onDayClicked(AdapterView<?> adapter, View view, int position, long id, Day day)
            {
                Date selectedDate = new Date(day.getYear() - 1900, day.getMonth(), day.getDay());

                List<Surgery> selectedDateSurgeries = new ArrayList<Surgery>();
                if (surgeries != null)
                {
                    for (Surgery s : surgeries)
                    {
                        if (s.getDate().getDate() == selectedDate.getDate() &&
                                s.getDate().getMonth() == selectedDate.getMonth() &&
                                s.getDate().getYear() == selectedDate.getYear())
                        {
                            Log.d("Selected Surgery", s.toString());
                            selectedDateSurgeries.add(s);
                        }
                    }
                }

                Intent intent = new Intent(CalendarActivity.this, SurgeryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("FROM_CALENDAR", true);
                intent.putExtra("SELECTED_SURGERIES", selectedDateSurgeries.toArray());
                startActivity(intent);
            }
        });
    }

//    @SuppressLint("NewApi")
//    private void initializeCalendar(final ExtendedCalendarView calendarView)
//    {
//        // sets whether to show the week number.
//        calendarView.setShowWeekNumber(false);
//
//        // sets the first day of week according to Calendar.
//        // here we set Monday as the first day of the Calendar
//        calendarView.setFirstDayOfWeek(2);
//
//        //The background color for the selected week.
//        calendarView.setSelectedWeekBackgroundColor(getResources().getColor(R.color.moss_green));
//
//        //sets the color for the dates of an unfocused month.
//        calendarView.setUnfocusedMonthDateColor(getResources().getColor(R.color.transparent));
//
//        //sets the color for the separator line between weeks.
//        calendarView.setWeekSeparatorLineColor(getResources().getColor(R.color.transparent));
//
//        //sets the color for the vertical bar shown at the beginning and at the end of the selected date.
//        calendarView.setSelectedDateVerticalBar(R.color.dark_green);
//
//        //sets the listener to be notified upon selected date change.
//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
//        {
//            //show the selected date as a toast
//            @SuppressWarnings("deprecation")
//            @Override
//            public void onSelectedDayChange(CalendarView view, int year, int month, int day)
//            {
//                Toast.makeText(getApplicationContext(), day + "/" + (month + 1) + "/" + year, Toast.LENGTH_LONG).show();
//
//                Date selectedDate = new Date(year - 1900, month, day);
//
//                List<Surgery> selectedDateSurgeries = new ArrayList<Surgery>();
//                for (Surgery s : surgeries)
//                {
//                    if (s.getDate().getDate() == selectedDate.getDate() &&
//                        s.getDate().getMonth() == selectedDate.getMonth() &&
//                        s.getDate().getYear() == selectedDate.getYear())
//                    {
//                        Log.d("Selected Surgery", s.toString());
//                        selectedDateSurgeries.add(s);
//                    }
//                }
//
//                Intent intent = new Intent(CalendarActivity.this, SurgeryActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                intent.putExtra("FROM_CALENDAR", true);
//                intent.putExtra("SELECTED_SURGERIES", selectedDateSurgeries.toArray());
//                startActivity(intent);
//            }
//        });
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calandar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
