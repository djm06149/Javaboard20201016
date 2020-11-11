import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class test4 {
	public static void main(String[] args) {
		
//		String jsonStr = "[0," + "{\\\"1\\\":{\\\"2\\\":{\\\"3\\\":{\\\"4\\\":[5,{\\\"6\\\":7}]}}}}" + "]";
//		
//		JSONArray array = (JSONArray)JSONValue.parse(jsonStr);
//		
//		System.out.println(array.get(1));
//		System.out.println();
//		
//		JSONObject obj2 = (JSONObject) array.get(1);
//		System.out.println("Field \"1\"");
//		System.out.println(obj2.get("1"));
		
		JSONObject obj = new JSONObject();
	      String jsonText;

	      obj.put("id", new Integer(100));
	      obj.put("title", "안녕하세요");
	      obj.put("body", "파일을 저장해봅시다");
	      obj.put("nickname", "홍길동");
	      jsonText = obj.toString();
//
//	      System.out.println("Encode a JSON Object - to String");
//	      System.out.print(jsonText);
//	      
//	      JSONObject jobj = (JSONObject)JSONValue.parse(jsonText);
//	      String s1 = (String)jobj.get("name");
//	      long n1 = (long)jobj.get("num");
//	      double d1 = (double)jobj.get("balance");
//	      boolean b1 = (boolean)jobj.get("is_vip");
//	      System.out.println(s1);
		
		try {
			// 파일 객체 생성
			File file = new File("C:\\test/test.txt");
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

			if (file.isFile()) {
				// 쓰기
				bufferedWriter.write(jsonText);
				// 개행문자쓰기
				bufferedWriter.newLine();
				bufferedWriter.write(jsonText);

				bufferedWriter.close();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
//
//		try {
//			// 파일 객체 생성
//			File file = new File("C:\\test/test.txt");
//			// 입력 스트림 생성
//			FileReader filereader = new FileReader(file);
//			// 입력 버퍼 생성
//			BufferedReader bufReader = new BufferedReader(filereader);
//			String line = "";
//			while ((line = bufReader.readLine()) != null) {
//				System.out.println(line);
//			}
//			// .readLine()은 끝에 개행문자를 읽지 않는다.
//			bufReader.close();
//		} catch (FileNotFoundException e) {
//			// TODO: handle exception
//		} catch (IOException e) {
//			System.out.println(e);
//		}

	}

}
