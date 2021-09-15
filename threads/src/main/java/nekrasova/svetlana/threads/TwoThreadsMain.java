package nekrasova.svetlana.threads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class TwoThreadsMain {
	static final File file = new File("out.txt");

	public static void main(String[] args) {
		System.out.println("Enter number ");

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		try {
			int num = Integer.parseInt(in.readLine());
			in.close();
			if (num > 0 && num % 2 == 0) {

				try (FileWriter writer = new FileWriter(file.getPath(), false)) {
					writer.write(Integer.toString(0));
				} catch (IOException ex) {
					System.out.println(ex.getMessage());
				}
				Thread th1 = new Thread(new MyThread(file, num, "first"));
				Thread th2 = new Thread(new MyThread(file, num, "second"));

				th1.start();
				th2.start();

				try {
					th1.join();
					th2.join();
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}

				try (BufferedReader fin = new BufferedReader(new FileReader(file));) {
					String line;
					while ((line = fin.readLine()) != null)
						System.out.println(line);
				} catch (FileNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			} else {
				System.out.println("Not correct input");
			}
		} catch (NumberFormatException e) {
			System.out.println("Not correct input");
		} catch (IOException e) {
			System.out.println("Not correct input");
		}

	}

}
