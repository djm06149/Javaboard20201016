
public class test {
	public static void main(String[] args) {
		String str = "사과(apple) 가격은 개당 1,000원 입니다.";

		// contains 사용
		if (str.contains("사과")) {
			System.out.println("contain - 포함");
		} else {
			System.out.println("contain - 미포함");
		}

		System.out.println(str.contains("APPLE")); // false
		System.out.println(str.contains("apple")); // true

	}

}
