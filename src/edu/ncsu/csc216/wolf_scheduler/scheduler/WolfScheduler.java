package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * The WolfScheduler class contains fields, getter/setter methods, and Object
 * methods to create a schedule.
 * 
 * @author Levi Whitener
 */
public class WolfScheduler {

	/** Catalog of Courses. */
	private ArrayList<Course> catalog;

	/** ArrayList of courses in a student's schedule. */
	private ArrayList<Activity> schedule;

	/** Title of the schedule */
	private String title;

	/**
	 * Constructs a WolfScheduler object with values for all fields.
	 * 
	 * @param fileName name of file
	 * @throws IllegalArgumentException if the file cannot be found or read
	 * 
	 */
	public WolfScheduler(String fileName) {
		schedule = new ArrayList<>();
		title = "My Schedule";

		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Cannot find file.");
		}

	}

	/**
	 * Returns the course catalog
	 * 
	 * @return courseCatalog the course catalog
	 */
	public String[][] getCourseCatalog() {
		int size = catalog.size();

		if (size == 0) {
			return new String[0][0];
		}

		String[][] courseCatalog = new String[size][4];

		for (int i = 0; i < size; i++) {
			Course course = catalog.get(i);

			String[] courseInfo = course.getShortDisplayArray();

			for (int j = 0; j < 3; j++) {
				courseCatalog[i][j] = courseInfo[j];
			}

			courseCatalog[i][3] = course.getMeetingString();
		}

		return courseCatalog;
	}

	/**
	 * Returns the scheduled activities of a student
	 * 
	 * @return an empty 2D array if the schedule size is 0 scheduledActivities a 2D
	 *         array of a schedule containing the name, section, and title
	 */
	public String[][] getScheduledActivities() {
		int size = schedule.size();

		if (size == 0) {
			return new String[0][0];
		}

		String[][] scheduledActivities = new String[size][4];

		for (int i = 0; i < size; i++) {
			Activity activity = schedule.get(i);

			String[] activityInfo = activity.getShortDisplayArray();

			for (int j = 0; j < 3; j++) {
				scheduledActivities[i][j] = activityInfo[j];
			}

			scheduledActivities[i][3] = activity.getMeetingString();
		}

		return scheduledActivities;
	}

	/**
	 * Returns the scheduled activities of a student
	 * 
	 * @return an empty 2D array if the schedule size is 0 scheduledActivities a 2D
	 *         array of a schedule containing the name, section, title, credits,
	 *         instructor id, and meeting day and time
	 */
	public String[][] getFullScheduledActivities() {
		int size = schedule.size();

		if (size == 0) {
			return new String[0][0];
		}

		String[][] scheduledActivities = new String[size][7];

		for (int i = 0; i < size; i++) {
			Activity activity = schedule.get(i);

			String[] activityInfo = activity.getLongDisplayArray();

			for (int j = 0; j < 7; j++) {
				scheduledActivities[i][j] = activityInfo[j];
			}
		}

		return scheduledActivities;
	}

	/**
	 * Returns the a course if it is contained in the catalog
	 * 
	 * @param name    the name of the course
	 * @param section the section of the course
	 * @return null if the course is not in the catalog course the name of a course
	 *         if it is contained in the catalog
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			Course course = catalog.get(i);
			if (course.getName().equals(name) && course.getSection().equals(section)) {
				return course;
			}
		}
		return null;
	}

	/**
	 * Returns a boolean if a course was added to a schedule
	 * 
	 * @param name    the name of the course
	 * @param section the section of the course
	 * @return false if the course was not added true if the course was added
	 * @throws IllegalArgumentException if the course is already in the schedule
	 */
	public boolean addCourseToSchedule(String name, String section) {
		Course courseToAdd = getCourseFromCatalog(name, section);
		if (courseToAdd == null) {
			return false;
		}

		for (Activity existingActivity : schedule) {
			if (existingActivity.isDuplicate(courseToAdd)) {
				throw new IllegalArgumentException("You are already enrolled in " + name);
			}
		}

		schedule.add(courseToAdd);
		return true;
	}

	/**
	 * Adds an event to the schedule
	 * 
	 * @param eventTitle       the title of the event
	 * @param eventMeetingDays the event meeting days
	 * @param eventStartTime   the event start time
	 * @param eventEndTime     the event end time
	 * @param eventDetails     the event details
	 * @throws IllegalArgumentException if the event is already in the schedule
	 */
	public void addEventToSchedule(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime,
			String eventDetails) {
		Event newEvent = new Event(eventTitle, eventMeetingDays, eventStartTime, eventEndTime, eventDetails);

		for (Activity existingActivity : schedule) {
			if (existingActivity.isDuplicate(newEvent)) {
				throw new IllegalArgumentException("You have already created an event called " + eventTitle);
			}
		}
		schedule.add(newEvent);

	}

	/**
	 * Returns a boolean if an activity was removed to a schedule
	 * 
	 * @param idx the index of the element being removed
	 * 
	 * @return false if the activity was not removed, as a default true if the
	 *         activity was removed
	 */
	public boolean removeActivityFromSchedule(int idx) {
		try {
			schedule.remove(idx);
			return true;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}

	}

	/**
	 * Resets a schedule by making schedule an empty ArrayList
	 */
	public void resetSchedule() {
		schedule = new ArrayList<>();
	}

	/**
	 * Returns the Schedule's title
	 * 
	 * @return the title
	 */
	public String getScheduleTitle() {
		return title;
	}

	/**
	 * Sets the Schedule's title. If the title is null an IllegalArgumentException
	 * is thrown.
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException if the title parameter is invalid
	 */
	public void setScheduleTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
	}

	/**
	 * Exports the schedule by writing onto the file provided. An
	 * IllegalArgumentException is thrown if the file cannot be saved.
	 * 
	 * @param fileName the file to be wrote upon
	 * @throws IllegalArgumentException if the file cannot be saved
	 */
	public void exportSchedule(String fileName) {
		try {
			ActivityRecordIO.writeActivityRecords(fileName, schedule);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}

}
