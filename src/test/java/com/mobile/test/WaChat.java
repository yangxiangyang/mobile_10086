package com.mobile.test;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class WaChat {

	/**
	 * ����ͼ�������ƽ̨�ӿ� ��Ҫ����İ���commons-logging-1.0.4.jar��
	 * httpclient-4.3.1.jar��httpcore-4.3.jar
	 */
	/*public static void main(String[] args) throws IOException {

		String INFO = URLEncoder.encode("���", "utf-8");
		String requesturl = "http://www.tuling123.com/openapi/api?key= ע�ἤ��ص�Apikey&info="
				+ INFO;
		HttpGet request = new HttpGet(requesturl);
		HttpResponse response = HttpClients.createDefault().execute(request);

		// 200����ȷ�ķ�����
		if (response.getStatusLine().getStatusCode() == 200) {
			String result = EntityUtils.toString(response.getEntity());
			System.out.println("���ؽ����" + result);
		}
	}
	 */
}
