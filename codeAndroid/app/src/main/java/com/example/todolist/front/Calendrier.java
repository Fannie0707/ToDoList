package com.example.todolist.front;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.applandeo.materialcalendarview.CalendarView;
import com.example.todolist.R;

import java.util.Calendar;

public class Calendrier extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_principale);

        Calendar calendar = Calendar.getInstance();

        CalendarView calendarView = findViewById(R.id.calendarView);
    }
}

// Style
        /* calendarView.setDateTextAppearance(R.style.CalendarDateStyle);
        calendarView.setHeaderTextAppearance(R.style.CalendarHeaderStyle);

        // Décorateur du jour actuel
        calendarView.addDecorator(new CurrentDayDecorator());

        // Désactivation de la sélection
        calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_NONE);
    }

    private static class CurrentDayDecorator implements DayViewDecorator {
        private final CalendarDay today = CalendarDay.today();

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return today.equals(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new DotSpan(8, Color.parseColor("#FFB6C1")));
        }
    }*/