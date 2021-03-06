package lukfor.reports.examples;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

import lukfor.reports.HtmlReport;

public class ExampleTableReport {

	public static void main(String[] args) throws IOException {

		String title = "Magic Reports";

		List<Person> persons = new Vector<Person>();

		for (int i = 0; i < 10000; i++) {
			persons.add(new Person("Max" + i, "Mustermann" + i, "max" + i + ".mustermann@mail.com"));
		}

		HtmlReport report = new HtmlReport("/example-tables");
		report.setSelfContained(true);
		report.set("title", title);
		report.set("persons", persons);

		report.generate(new File("example-tables.html"));
	}

	public static class Person {

		public String firstName;

		public String lastName;

		public String email;

		public int age = 0;

		public int salary = 0;

		public Person(String firstName, String lastName, String email) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.age = ThreadLocalRandom.current().nextInt(10, 80);
			this.salary = ThreadLocalRandom.current().nextInt(10 * age, 5000);
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public int getSalary() {
			return salary;
		}

		public void setSalary(int salary) {
			this.salary = salary;
		}

	}

}
