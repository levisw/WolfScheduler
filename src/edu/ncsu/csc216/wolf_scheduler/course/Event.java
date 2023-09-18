package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * The Event class contains fields, getter/setter methods, and Object methods to
 * list information about events.
 * 
 * @author Levi Whitener
 */
public class Event extends Activity {

	/** Event details */
	private String eventDetails;

	/**
	 * Constructs an Event object with values for all fields.
	 * 
	 * @param title        title of Event
	 * @param meetingDays  meeting days for Event as series of chars
	 * @param startTime    start time for Event
	 * @param endTime      end time for Event
	 * @param eventDetails details for event
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setEventDetails(eventDetails);
	}

	/**
	 * Returns the event details
	 * 
	 * @return the eventDetails
	 */
	public String getEventDetails() {
		return eventDetails;
	}

	/**
	 * Sets the event details
	 * 
	 * @param eventDetails the eventDetails to set
	 * @throws IllegalArgumentException if the eventDetails parameter is invalid
	 */
	public void setEventDetails(String eventDetails) {
		if (eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details.");
		}
		this.eventDetails = eventDetails;
	}

	/**
	 * Returns an array of String type that contains a shortened display.
	 * 
	 * @return String representation of name, section, title, meeting time
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplayArray = new String[4];
		String emptyString = "";

		shortDisplayArray[0] = emptyString;
		shortDisplayArray[1] = emptyString;
		shortDisplayArray[2] = getTitle();
		shortDisplayArray[3] = getMeetingString();

		return shortDisplayArray;
	}

	/**
	 * Returns an array of String type that contains all Event fields
	 * 
	 * @return String representation of name, section, title, credits, instructor
	 *         id, and meeting time
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplayArray = new String[7];
		String emptyString = "";

		longDisplayArray[0] = emptyString;
		longDisplayArray[1] = emptyString;
		longDisplayArray[2] = getTitle();
		longDisplayArray[3] = emptyString;
		longDisplayArray[4] = emptyString;
		longDisplayArray[5] = getMeetingString();
		longDisplayArray[6] = eventDetails;

		return longDisplayArray;
	}

	/**
	 * Returns a comma separated value String of all Event fields.
	 * 
	 * @return String representation of Event
	 */
	@Override
	public String toString() {
		return getTitle() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime() + "," + eventDetails;
	}

	/**
	 * Sets the Event's meeting time and days. If the meeting days is null, empty,
	 * or contains invalid characters; if start time is an incorrect time; if end
	 * time is an incorrect time; or if end time is less than start time, an
	 * IllegalArgumentException is thrown.
	 * 
	 * @param meetingDays the days of meeting to set
	 * @param startTime   the start time to set
	 * @param endTime     the end time to set
	 * @throws IllegalArgumentException if the meetingDays parameter is invalid
	 * @throws IllegalArgumentException if the startTime parameter is invalid
	 * @throws IllegalArgumentException if the endTime parameter is invalid
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		for (int i = 0; i < meetingDays.length(); i++) {
			char c = meetingDays.charAt(i);
			if (c != 'M' && c != 'T' && c != 'W' && c != 'H' && c != 'F' && c != 'S' && c != 'U') {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}

		int[] meetingDaysCount = new int[7];

		for (int i = 0; i < meetingDays.length(); i++) {
			char c = meetingDays.charAt(i);

			if (c == 'M') {
				meetingDaysCount[0]++;
			}

			else if (c == 'T') {
				meetingDaysCount[1]++;
			}

			else if (c == 'W') {
				meetingDaysCount[2]++;
			}

			else if (c == 'H') {
				meetingDaysCount[3]++;
			}

			else if (c == 'F') {
				meetingDaysCount[4]++;
			}

			else if (c == 'S') {
				meetingDaysCount[5]++;
			}

			else if (c == 'U') {
				meetingDaysCount[6]++;
			}

			else {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}

		for (int i = 0; i < meetingDaysCount.length; i++) {
			if (meetingDaysCount[i] > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}

		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Checks to see if an activity exists already
	 * 
	 * @return true if a activity already exists
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Event) {
			Event otherEvent = (Event) activity;
			return getTitle().equals(otherEvent.getTitle());
		}
		return false;
	}

}
