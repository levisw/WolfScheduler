package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * The Course class contains fields, getter/setter methods, and Object methods
 * to list information about courses.
 * 
 * @author Levi Whitener
 */
public class Course extends Activity {

	/** constant representing the min length of a course name */
	private static final int MIN_NAME_LENGTH = 5;

	/** constant representing the max length of a course name */
	private static final int MAX_NAME_LENGTH = 8;

	/** constant representing the min length of a course abbreviation */
	private static final int MIN_LETTER_COUNT = 1;

	/** constant representing the max length of a course abbreviation */
	private static final int MAX_LETTER_COUNT = 4;

	/** constant representing the max length of a course number */
	private static final int DIGIT_COUNT = 3;

	/** constant representing the max length of a course section number */
	private static final int SECTION_LENGTH = 3;

	/** constant representing the max amount of credits a course can be worth */
	private static final int MAX_CREDITS = 5;

	/** constant representing the min amount of credits a course can be worth */
	private static final int MIN_CREDITS = 1;

	/** Course's name. */
	private String name;

	/** Course's section. */
	private String section;

	/** Course's credit hours */
	private int credits;

	/** Course's instructor */
	private String instructorId;

	/**
	 * Constructs a Course object with values for all fields.
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays  meeting days for Course as series of chars
	 * @param startTime    start time for Course
	 * @param endTime      end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * and meetingDays for courses that are arranged.
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays  meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * Returns the Course's name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name. If the name is null, has a length less than 5 or more
	 * than 8, does not contain a space between letter characters and number
	 * characters, has less than 1 or more than 4 letter characters, and not exactly
	 * three trailing digit characters, an IllegalArgumentException is thrown.
	 * 
	 * @param name the name to set
	 * @throws IllegalArgumentException if the name parameter is invalid
	 */
	private void setName(String name) {
		if (name == null || "".equals(name)) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		int numLetters = 0;
		int numNumbers = 0;
		boolean isSpace = false;

		for (int i = 0; i < name.length(); i++) {
			char character = name.charAt(i);

			if (!isSpace) {
				if (Character.isLetter(character)) {
					numLetters++;
				} else if (character == 32) {
					isSpace = true;
				} else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			} else {
				if (Character.isDigit(character)) {
					numNumbers++;
				} else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			}
		}

		if (numLetters < MIN_LETTER_COUNT || numLetters > MAX_LETTER_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		if (numNumbers != DIGIT_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		this.name = name;
	}

	/**
	 * Returns the Course's section number
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section number. If the section number is null, an empty
	 * String, a character in the section string is not a number, or if the length
	 * of the section number is not 3, an IllegalArgumentException is thrown.
	 * 
	 * @param section the section to set
	 * @throws IllegalArgumentException if the section parameter is invalid
	 */
	public void setSection(String section) {

		if (section == null || "".equals(section)) {
			throw new IllegalArgumentException("Invalid section.");
		}

		int numNumbers = 0;

		for (int i = 0; i < section.length(); i++) {
			char character = section.charAt(i);

			if (Character.isDigit(character)) {
				numNumbers++;
			}

			else {
				throw new IllegalArgumentException("Invalid section.");
			}
		}

		if (numNumbers != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}

		this.section = section;
	}

	/**
	 * Returns the Course's credits
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course's number of credits. If the number of credits is not a number
	 * or if the number of credits is less than 1 or greater than 5, an
	 * IllegalArgumentException is thrown.
	 * 
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if the credits parameter is invalid
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}

		this.credits = credits;
	}

	/**
	 * Returns the Course's instructor Id
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course's instructor id. If the instructor id is null or an empty
	 * String an IllegalArgumentException is thrown.
	 * 
	 * @param instructorId the instructor id to set
	 * @throws IllegalArgumentException if the instructor id parameter is invalid
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || "".equals(instructorId)) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}

		this.instructorId = instructorId;
	}

	/**
	 * Generates a hashCode for Course using all fields.
	 * 
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if ("A".equals(getMeetingDays())) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
					+ getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays()
				+ "," + getStartTime() + "," + getEndTime();
	}

	/**
	 * Returns an array of String type that contains a shortened display.
	 * 
	 * @return String representation of name, section, title, meeting time
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplayArray = new String[4];
		shortDisplayArray[0] = getName();
		shortDisplayArray[1] = getSection();
		shortDisplayArray[2] = getTitle();
		shortDisplayArray[3] = getMeetingString();

		return shortDisplayArray;
	}

	/**
	 * Returns an array of String type that contains all Course fields
	 * 
	 * @return String representation of name, section, title, credits, instructor
	 *         id, and meeting time
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplayArray = new String[7];
		String emptyString = "";

		longDisplayArray[0] = getName();
		longDisplayArray[1] = getSection();
		longDisplayArray[2] = getTitle();
		longDisplayArray[3] = String.valueOf(credits);
		longDisplayArray[4] = getInstructorId();
		longDisplayArray[5] = getMeetingString();
		longDisplayArray[6] = emptyString;

		return longDisplayArray;
	}

	/**
	 * Sets the Course's meeting time and days. If the meeting days is null, empty,
	 * or contains invalid characters; if an arranged class has non-zero start or
	 * end times; if start time is an incorrect time; if end time is an incorrect
	 * time; or if end time is less than start time, an IllegalArgumentException is
	 * thrown.
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

		for (int i = 0; i < meetingDays.length(); i++) {
			char c = meetingDays.charAt(i);
			if (c != 'M' && c != 'T' && c != 'W' && c != 'H' && c != 'F' && c != 'A') {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}

		if ("A".equals(meetingDays)) {
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			super.setMeetingDaysAndTime(meetingDays, 0, 0);

		} else {
			int[] meetingDaysCount = new int[5];

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
	}

	/**
	 * Checks to see if an activity exists already
	 * 
	 * @return true if a activity already exists
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			Course otherCourse = (Course) activity;
			return name.equals(otherCourse.name);
		}
		return false;
	}

}
