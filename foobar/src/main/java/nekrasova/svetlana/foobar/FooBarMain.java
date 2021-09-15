package nekrasova.svetlana.foobar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class FooBarMain {

	public static void main(String[] args) {
		System.out.println("Enter version: ");
		System.out.println("1 - cycle");
		System.out.println("2 - cycle & switch");
		System.out.println("3 - cycle & two if");
		System.out.println("4 - ternary operator");
		System.out.println("5 - Stream");

		try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in));) {
			int verNum = Integer.parseInt(in.readLine());
			if (verNum < 1 || verNum > 4) {
				throw new NumberFormatException();
			}
			System.out.println("Enter positive number:");
			int n = Integer.parseInt(in.readLine());
			if (n < 1) {
				throw new NumberFormatException();
			}

			switch (verNum) {
			case 1:
				printByCycle(n);
				break;
			case 2:
				printByCycleAndSwitch(n);
				break;
			case 3:
				printByTwoIf(n);
				break;
			case 4:
				printByTernary(n);
			case 5:
				printByStream(n);
				break;
			default:
				break;

			}

		} catch (NumberFormatException e) {
			System.out.println("Not correct input");
		} catch (IOException e) {
			System.out.println("Not correct input");
		}
	}

	private static void printByCycle(int n) {
		for (int i = 1; i <= n; i++) {
			if (i % 15 == 0) {
				System.out.println("FooBar");
			} else if (i % 3 == 0) {
				System.out.println("Foo");
			} else if (i % 5 == 0) {
				System.out.println("Bar");
			} else {
				System.out.println(i);
			}

		}
	}

	private static void printByTwoIf(int n) {

		for (int i = 1; i <= n; i++) {
			String result = Integer.toString(i);
			if (i % 3 == 0) {
				result = result.replace(Integer.toString(i), "") + "Foo";
			}
			if (i % 5 == 0) {
				result = result.replace(Integer.toString(i), "") + "Bar";
			}
			System.out.println(result);
		}
	}

	private static void printByCycleAndSwitch(int n) {

		for (int i = 1; i <= n; i++) {
			String remainders = Integer.toString(Math.min(i % 3, 1)) + Integer.toString(Math.min(i % 5, 1));
			switch (remainders) {
			case "00":
				System.out.println("FooBar");
				break;
			case "01":
				System.out.println("Foo");
				break;
			case "10":
				System.out.println("Bar");
				break;
			default:
				System.out.println(i);
			}
		}
	}

	private static void printByTernary(int n) {
		for (int i = 1; i <= n; i++) {
			System.out.println((i % 15 == 0) ? "FooBar" : (i % 3 == 0) ? "Foo" : (i % 5 == 0) ? "Bar" : i);
		}
	}

	private static void printByStream(int n) {
		IntStream.rangeClosed(1, n).mapToObj(
				i -> (i % 15 == 0) ? "FooBar" : (i % 3 == 0) ? "Foo" : (i % 5 == 0) ? "Bar" : Integer.toString(i))
				.forEach(System.out::println);
		;
	}

}
