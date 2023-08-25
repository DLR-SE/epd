package de.emir.rcp.properties.ui.editors.measure;

import java.awt.Component;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.TimeZone;

import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.optionalusertools.DateTimeChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateTimeChangeEvent;

import de.emir.model.universal.units.Time;
import de.emir.model.universal.units.TimeUnit;
import de.emir.model.universal.units.impl.TimeImpl;
import de.emir.rcp.properties.ui.editors.AbstractPropertyEditor;

public class DateTimeEditor extends AbstractPropertyEditor<Time> {

	private DateTimePicker mEditor;

	@Override
	public void dispose() {

	}

	@Override
	protected Component createComponent() {
		if (mEditor == null) {
			if (getValue() == null)
				setValue(new TimeImpl(System.currentTimeMillis(), TimeUnit.MILLISECOND));
			
			mEditor = new DateTimePicker();
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
