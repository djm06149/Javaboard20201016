import java.util.ArrayList;
import java.util.Scanner;

public class test3 {

	public static void main(String[] args) {

		ArrayList<Article> articles = new ArrayList<>();

		for (int i = 1; i <= 50; i++) {
			Article a1 = new Article();
			a1.setId(i);
			a1.setTitle("����" + i);
			a1.setBody("����" + i);

			articles.add(a1);
		}

		Scanner sc = new Scanner(System.in);
		int currentPageNo = 123; // ���� ������
		int totalCntOfItems = articles.size(); // ��ü �Խù� ����
		int startPageNo = 1; // ���� ������ ��ȣ
		int itemsCntPerPage = 3; // �������� ��� �Խù� ����
		int pageCntPerBlock = 5; // �� ������ ��� �� ������ ����
		int endPageNo = (int) Math.ceil((double) totalCntOfItems / itemsCntPerPage); // ������ ������ ��ȣ

		// ���� �������� �������������� ������ �ȵ�
		if (currentPageNo < startPageNo) {
			currentPageNo = startPageNo;
		}
		// ���� �������� ���������������� ũ�� �ȵ�
		if (currentPageNo > endPageNo) {
			currentPageNo = endPageNo;
		}

		int currentPageBlock = (int) Math.ceil((double) currentPageNo / pageCntPerBlock); // ���� ������ ���
		int startPageNoInBlock = (currentPageBlock - 1) * pageCntPerBlock + 1; // ���� ������ ����� ���� ������ ��ȣ
		int endPageNoInBlock = startPageNoInBlock + pageCntPerBlock - 1;// // ���� ������ ����� ������ ������ ��ȣ

		// ������ ��ȣ�� ������ �������� ������ �ȵ�
		if (endPageNoInBlock > endPageNo) {
			endPageNoInBlock = endPageNo;
		}
		// �ش� �������� �Խù� ����� ù �ε���
		int startIndex = (currentPageNo - 1) * itemsCntPerPage;

		// �ش� �������� �Խù� ����� ������ �ε���
		int endIndex = startIndex + itemsCntPerPage;

		// �������� ������ �ε����� ������� ������ �ε������� ũ�� �ȵ�
		if (endIndex > totalCntOfItems) {
			endIndex = totalCntOfItems;
		}
		// �������� �Խù� ���
		for (int i = startIndex; i < endIndex; i++) {
			System.out.println("��ȣ : " + articles.get(i).getId());
			System.out.println("���� : " + articles.get(i).getTitle());
			System.out.println("���� : " + articles.get(i).getBody());
			System.out.println("======================================");
		}

		for (int i = startPageNoInBlock; i <= endPageNoInBlock; i++) {

			if (i == currentPageNo) {
				System.out.print("[" + i + "] ");
			} else {
				System.out.print(i + " ");
			}
		}
	}

}