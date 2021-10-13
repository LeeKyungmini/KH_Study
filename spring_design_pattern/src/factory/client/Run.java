package factory.client;

import factory.framework.ConnectorFactory;
import factory.framework.SMTPConnector;

public class Run {

	public static void main(String[] args) {
		
		//네이버 SMSP서버에 연결을 해보자
		try {
			SMTPConnector conn = ConnectorFactory.buileder()
					.url("smtp.daum.net")
					.id("alssgo70051@naver.com")
					.password("123456")
					.build()
					.getConnector();
			conn.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
