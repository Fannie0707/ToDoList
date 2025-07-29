package com.example.todolist.front;

import android.os.Bundle; // Cet import manquait
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

public class Calendrier extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Bundle était manquant
        super.onCreate(savedInstanceState); // Appel au parent obligatoire
        setContentView(R.layout.page_principale);

        MaterialCalendarView calendarView = findViewById(R.id.calendarView);

        // Style
        calendarView.setDateTextAppearance(R.style.CalendarDateStyle);
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
    }
}