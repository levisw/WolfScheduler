package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.FileNotFoundException;

import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Course;
import java.io.FileInputStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Reads Course records from text files. Writes a set of CourseRecords to a
 * file.
 * 
 * @author Levi Whitener, Sarah Heckman
 */
public class CourseRecordIO {

	/**
	 * Reads course records from a file and generates a list of valid Courses. Any
	 * invalid Courses are ignored. If the file to read cannot be found or the
	 * permissions are incorrect a File NotFoundException is thrown.
	 * 
	 * @param fileName file to read Course records from
	 * @return a list of valid Courses
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static ArrayList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName)); // Create a file scanner to read the file
		ArrayList<Course> courses = new ArrayList<Course>(); // Create an empty array of Course objects
		while (fileReader.hasNextLine()) { // While we have more lines in the file
			try { // Attempt to do the following
					// Read the line, process it in readCourse, and get the object
					// If trying to construct a Course in readCourse() results in an exception, flow
					// of control will transfer to the catch block, below
				Course course = readCourse(fileReader.nextLine());

				// Create a flag to see if the newly created Course is a duplicate of something
				// already in the list
				boolean duplicate = false;
				// Look at all the courses in our list
				for (int i = 0; i < courses.size(); i++) {
					// Get the course at index i
					Course current = courses.get(i);
					// Check if the name and section are the same
					if (course.getName().equals(current.getName())
							&& course.getSection().equals(current.getSection())) {
						// It's a duplicate!
						duplicate = true;
						break; // We can break out of the loop, no need to continue searching
					}
				}
				// If the course is NOT a duplicate
				if (!duplicate) {
					courses.add(course); // Add to the ArrayList!
				} // Otherwise ignore
			} catch (IllegalArgumentException e) {
				// The line is invalid b/c we couldn't create a course, skip it!
			}
		}
		// Close the Scanner b/c we're responsible with our file handles
		fileReader.close();
		// Return the ArrayList with all the courses we read!
		return courses;
	}

	/**
	 * This method then takes the strings that are read in and converts them to
	 * Course objects that contain all the information about the course.
	 * 
	 * @param nextLine the line read in from the file
	 * @return c returns the course information as a Course object
	 */
	private static Course readCourse(String nextLine) {
		Scanner scnr = new Scanner(nextLine);
		scnr.useDelimiter(",");

		String name;
		String title;
		String section;
		int credits;
		String instructorId;
		String meetingDays;
		int startTime;
		int endTime;

		try {
			name = scnr.next();
			title = scnr.next();
			section = scnr.next();
			credits = scnr.nextInt();
			instructorId = scnr.next();
			meetingDays = scnr.next();

			if ("A".equals(meetingDays)) {
				if (scnr.hasNext()) {
					scnr.close();
					throw new IllegalArgumentException("Invalid meeting days.");
				} else {
					Course course = new Course(name, title, section, credits, instructorId, meetingDays);
					scnr.close();
					return course;
				}
			} else {
				startTime = scnr.nextInt();
				endTime = scnr.nextInt();

				if (scnr.hasNext()) {
					scnr.close();
					throw new IllegalArgumentException("Invalid File");
				} else {
					Course course = new Course(name, title, section, credits, instructorId, meetingDays, startTime,
							endTime);
					scnr.close();
					return course;
				}
			}
		} catch (InputMismatchException e) {
			scnr.close();
			throw new IllegalArgumentException("Invalid File");
		} catch (NoSuchElementException e) {
			scnr.close();
			throw new IllegalArgumentException("Invalid File");
		}
	}

}