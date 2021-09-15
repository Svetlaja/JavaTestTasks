package nekrasova.svetlana.threads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MyThread implements Runnable {
	private File file;
	private int num;
	private String name;

	public MyThread(File file, int num, String name) {
		super();
		this.file = file;
		this.num = num;
		this.name = name;
	}

	@Override
	public void run() {
		int current = 0;

		for (int i = 0; i < num; i++) {

			synchronized (file) {

				try (BufferedReader reader = new BufferedReader(new FileReader(file.getPath()))) {
					String text = null;

					if ((text = reader.readLine()) != null) {
						current = Integer.parseInt(text);

						if (current < num) {
							System.out.println(Integer.toString(current) + "  " + Integer.toString(current + 1)
									+ "  thread " + name);
							try (FileWriter writer = new FileWriter(file.getPath(), false)) {
								writer.write(Integer.toString(current + 1));

								i = current;

							} catch (IOException ex) {
								System.out.println(ex.getMessage());
							}
							file.notifyAll();
							if (i < num - 1) {
								try {
									file.wait();
								} catch (InterruptedException e) {
									System.out.println(e.getMessage());
								}
							}
						}
					}
				} catch (FileNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}

		}

	}

}
