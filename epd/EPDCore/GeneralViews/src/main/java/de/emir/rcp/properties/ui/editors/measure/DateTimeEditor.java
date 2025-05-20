package de.emir.rcp.properties.ui.editors.measure;

import com.github.lgooddatepicker.components.DatePickerSettings;
import java.awt.Component;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.optionalusertools.DateTimeChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateTimeChangeEvent;

import de.emir.model.universal.units.Time;
import de.emir.model.universal.units.TimeUnit;
import de.emir.model.universal.units.impl.TimeImpl;
import de.emir.rcp.properties.ui.editors.AbstractPropertyEditor;
import java.awt.Color;
import javax.swing.UIManager;

public class DateTimeEditor extends AbstractPropertyEditor<Time> {

	private DateTimePicker mEditor;

	@Override
	public void dispose() {

	}

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	protected Component createComponent() {
		if (mEditor == null) {
			if (getValue() == null)
				setValue(new TimeImpl(System.currentTimeMillis(), TimeUnit.MILLISECOND));
			
			mEditor = new DateTimePicker();
            // Set colors to LaF defaults
            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.BackgroundCalendarPanelLabelsOnHover, UIManager.getColor("ComboBox.selectionBackground"));
            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.BackgroundClearLabel, UIManager.getColor("ComboBox.background"));
            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.BackgroundMonthAndYearMenuLabels, UIManager.getColor("ComboBox.background"));
            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.BackgroundMonthAndYearNavigationButtons, UIManager.getColor("ComboBox.buttonBackground"));
            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.BackgroundOverallCalendarPanel, UIManager.getColor("ComboBox.background"));
            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.BackgroundTodayLabel, UIManager.getColor("ComboBox.background"));
            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.BackgroundTopLeftLabelAboveWeekNumbers, UIManager.getColor("ComboBox.background"));
			mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.CalendarBackgroundNormalDates, UIManager.getColor("ComboBox.background"));
            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.CalendarBackgroundSelectedDate, UIManager.getColor("ComboBox.selectionBackground"));
            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.CalendarBackgroundVetoedDates, UIManager.getColor("ComboBox.disabledBackground"));
            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.CalendarBorderSelectedDate, UIManager.getColor("ComboBox.selectionBackground"));
            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.CalendarDefaultBackgroundHighlightedDates, UIManager.getColor("ComboBox.selectionForeground"));
            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.CalendarDefaultTextHighlightedDates, UIManager.getColor("ComboBox.selectionBckground"));
            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.CalendarTextNormalDates, UIManager.getColor("ComboBox.foreground"));
            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.CalendarTextWeekNumbers, UIManager.getColor("ComboBox.foreground"));
            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.CalendarTextWeekdays, UIManager.getColor("ComboBox.foreground"));
            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.DatePickerTextDisabled, UIManager.getColor("ComboBox.disabledForeground"));
            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.DatePickerTextValidDate, UIManager.getColor("ComboBox.foreground"));
            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.DatePickerTextVetoedDate, Color.YELLOW);
            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.DatePickerTextInvalidDate, Color.RED);
            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.TextCalendarPanelLabelsOnHover, UIManager.getColor("ComboBox.selectionForeground"));
            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.TextClearLabel, UIManager.getColor("ComboBox.foreground"));
            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.TextFieldBackgroundDisabled, UIManager.getColor("ComboBox.disabledBackground"));
            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.TextFieldBackgroundDisallowedEmptyDate, Color.RED);
            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.TextFieldBackgroundInvalidDate, Color.YELLOW);
            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.TextFieldBackgroundValidDate, UIManager.getColor("ComboBox.background"));
            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.TextFieldBackgroundVetoedDate, UIManager.getColor("ComboBox.background"));
            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.TextMonthAndYearMenuLabels, UIManager.getColor("ComboBox.foreground"));
            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.TextMonthAndYearNavigationButtons, UIManager.getColor("ComboBox.foreground"));
            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.TextTodayLabel, UIManager.getColor("ComboBox.foreground"));
            
            mEditor.getTimePicker().getSettings().setColor(TimePickerSettings.TimeArea.TextFieldBackgroundDisabled, UIManager.getColor("ComboBox.disabledBackground"));
            mEditor.getTimePicker().getSettings().setColor(TimePickerSettings.TimeArea.TextFieldBackgroundDisallowedEmptyTime, Color.RED);
            mEditor.getTimePicker().getSettings().setColor(TimePickerSettings.TimeArea.TextFieldBackgroundInvalidTime, Color.YELLOW);
            mEditor.getTimePicker().getSettings().setColor(TimePickerSettings.TimeArea.TextFieldBackgroundValidTime, UIManager.getColor("ComboBox.background"));
            mEditor.getTimePicker().getSettings().setColor(TimePickerSettings.TimeArea.TextFieldBackgroundVetoedTime, UIManager.getColor("ComboBox.background"));
            mEditor.getTimePicker().getSettings().setColor(TimePickerSettings.TimeArea.TimePickerTextDisabled, UIManager.getColor("ComboBox.disabledForeground"));
            mEditor.getTimePicker().getSettings().setColor(TimePickerSettings.TimeArea.TimePickerTextInvalidTime, Color.RED);
            mEditor.getTimePicker().getSettings().setColor(TimePickerSettings.TimeArea.TimePickerTextValidTime, UIManager.getColor("ComboBox.foreground"));
            mEditor.getTimePicker().getSettings().setColor(TimePickerSettings.TimeArea.TimePickerTextVetoedTime, Color.YELLOW);
//            mEditor.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.CalendarBackgroundVetoedDates, UIManager.getColor("ComboBox.buttonBackground"));
//			mEditor.getDatePicker().setForeground(UIManager.getColor("ComboBox.foreground"));
//			mEditor.getDatePicker().setBackground(UIManager.getColor("ComboBox.background"));
//			mEditor.setBackground(UIManager.getColor("ComboBox.background"));
//          mEditor.setForeground(UIManager.getColor("ComboBox.foreground"));
			LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli((long)getValue().getAs(TimeUnit.MILLISECOND)), ZoneId.ofOffset("", ZoneOffset.UTC));
			mEditor.setDateTimeStrict(dateTime);
			mEditor.addDateTimeChangeListener(new DateTimeChangeListener() {
				
				@Override
				public void dateOrTimeChanged(DateTimeChangeEvent event) {
					LocalDateTime time = event.getNewDateTimeStrict();
					
					getValue().set(new TimeImpl(time.toInstant(ZoneOffset.UTC).toEpochMilli(), TimeUnit.MILLISECOND));
				}
			});
		}
		return mEditor;
	}
}
