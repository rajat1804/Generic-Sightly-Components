package com.aem.cwr.framework.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.adobe.cq.sightly.WCMUsePojo;

public class BlogComponent extends WCMUsePojo {
	private String authoredDateString;
	Date authoredDateFormatted;

	@Override
	public void activate() throws Exception {
		authoredDateString = get("authoredDate", String.class);
	}

	public String blogDate() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
		if (authoredDateString != null) {

			long authoredDateFormatted1 = dateFormat.parse(authoredDateString).getTime();
			authoredDateFormatted = new Date(authoredDateFormatted1);

		} else {

			authoredDateFormatted = Calendar.getInstance().getTime();

		}
		SimpleDateFormat formattedDate = new SimpleDateFormat("EEE, d MMM yyyy");
		return formattedDate.format(authoredDateFormatted);

	}

}
