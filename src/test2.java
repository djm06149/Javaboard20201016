import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class test2 {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<Num> nlist = new ArrayList<Num>();

		list.add(4);
		list.add(1);
		list.add(2);
		list.add(5);
		list.add(3);

		printList(list);
		Collections.sort(list); // ��������
		printList(list);
		Collections.sort(list, Collections.reverseOrder()); // ��������
		printList(list);

		nlist.add(new Num(4, 5));
		nlist.add(new Num(1, 1));
		nlist.add(new Num(2, 4));
		nlist.add(new Num(5, 3));
		nlist.add(new Num(3, 2));

//		Collections.sort(nlist);
		MyComparator comp = new MyComparator();
		Collections.sort(nlist, comp);

		for (int i = 0; i < nlist.size(); i++) {
			System.out.println(nlist.get(i).n2);
		}

//		for (int i = 0; i < nlist.size(); i++) {
//			System.out.println(nlist.get(i).n2);
//		}
	}

	public static void printList(ArrayList<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}

class MyComparator implements Comparator<Num> {

	@Override
	public int compare(Num o1, Num o2) {
		if (o1.n2 > o2.n2) { // ���� ���� �� ũ�� �ڸ��� �ٲ�� -> �������� <-> ������ ���� �� Ŭ�� �ٲ��� ���ƶ�
			return 1; // 0, 1 �ٲ��
		}

		return -1; // -1 �ٲ��� ���ƶ�
	}

}

class Num {

	int n1;
	int n2;

	Num(int a, int b) {
		n1 = a;
		n2 = b;
	}

}