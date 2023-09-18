package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * The Activity class contains fields, getter/setter methods, and Object methods
 * to list information about activities.
 * 
 * @author Levi Whitener
 */
public abstract class Activity {

	/** constant representing the max amount of hours in a day */
	private static final int UPPER_HOUR = 24;
	/** constant representing the max amount of minutes in an hour */
	private static final int UPPER_MINUTE = 60;
	/** Activity's title. */
	private String title;
	/** Activity's meeting days */
	private String meetingDays;
	/** Activity's starting time */
	private int startTime;
	/** Activity's ending time */
	private int endTime;

	/**
	 * Array representing the short display for the GUI when not all aspects are
	 * needed
	 * 
	 * @return shortDisplayArray the array of 4 values
	 */

	public abstract String[] getShortDisplayArray();

	/**
	 * Array representing the long display for the GUI when all aspects are needed
	 * 
	 * @return longDisplayArray the array of 7 values
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * Checks to see if an activity exists already
	 * 
	 * @param activity an Activity object to check
	 * @return Boolean that returns true if a activity already exists
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * Constructs an Activity object with values for all fields.
	 * 
	 * @param title       title of Activity
	 * @param meetingDays meeting days for Activity as series of chars
	 * @param startTime   start time for Activity
	 * @param endTime     end time for Activity
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Returns the Activity's title
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Activity's title. If the title is null or an empty String an
	 * IllegalArgumentException is thrown.
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException if the title parameter is invalid
	 */
	public void setTitle(String title) {
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}

		this.title = title;
	}

	/**
	 * Returns the Activity's days of meeting
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Activity's start time
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Activity's end time
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the Activity's meeting time and days. If the meeting days is null,
	 * empty, or contains invalid characters; if start time is an incorrect time; if
	 * end time is an incorrect time; or if end time is less than start time, an
	 * IllegalArgumentException is thrown.
	 * 
	 * @param meetingDays the days of meeting to set
	 * @param startTime   the start time to set
	 * @param endTime     the end time to set
	 * @throws IllegalArgumentException if the meetingDays parameter is invalid
	 * @throws IllegalArgumentException if the startTime parameter is invalid
	 * @throws IllegalArgumentException if the endTime parameter is invalid
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		if (startTime > endTime) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		int startHour = startTime / 100;
		int startMin = startTime % 100;
		int endHour = endTime / 100;
		int endMin = endTime % 100;

		if (startHour < 0 || startHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		if (startMin < 0 || startMin >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		if (endHour < 0 || endHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		if (endMin < 0 || endMin >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;

	}

	/**
	 * Returns the Activity's days and times of meeting
	 * 
	 * @return the meetingString
	 */
	public String getMeetingString() {
		String meetingString = "";
		String dayString = "";
		String startT = "";
		String endT = "";

		for (int i = 0; i < meetingDays.length(); i++) {
			char day = meetingDays.charAt(i);
			if (Character.isLetter(day)) {
				dayString += day;
			}
		}
		int startTimeMilitary = startTime;
		int endTimeMilitary = endTime;
		if (startTimeMilitary == 0 || endTimeMilitary == 0) {
			return "Arranged";
		}
		startT = getTimeString(startTimeMilitary);
		endT = getTimeString(endTimeMilitary);
		meetingString = dayString + " " + startT + "-" + endT;
		return meetingString;
	}

	/**
	 * Returns the Activity's times of meeting after converting them from military
	 * time to normal time
	 * 
	 * @param time the military time to be converted
	 * @return the timeString
	 */
	private String getTimeString(int time) {
		int hours = time / 100;
		int minutes = time % 100;
		String period;

		if (hours >= 12) {
			period = "PM";
			if (hours > 12) {
				hours -= 12;
			}
		} else {
			period = "AM";
		}

		if (hours == 0) {
			hours = 12;
		}

		String minuteString;
		if (minutes < 10) {
			minuteString = "0" + minutes;
		} else {
			minuteString = String.valueOf(minutes);
		}
		String timeString = String.valueOf(hours) + ":" + minuteString + period;

		return timeString;
	}

	/**
	 * Generates a hashCode for Activity using all fields.
	 * 
	 * @return hashCode for Activity
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Compares a given object to this object for equality on all fields.
	 * 
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}