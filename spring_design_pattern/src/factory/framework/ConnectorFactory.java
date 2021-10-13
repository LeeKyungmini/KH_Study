package factory.framework;

import java.util.Date;

public class ConnectorFactory {

	private String url;
	private String id;
	private String password;
	private Date connectTime;

	public ConnectorFactory(ConnectorFactoryBuiler builder) {
		this.url = builder.url;
		this.id = builder.id;
		this.password = builder.password;
		this.connectTime = new Date();
	}

	public static ConnectorFactoryBuiler buileder() {
		return new ConnectorFactoryBuiler();
	}

	public static class ConnectorFactoryBuiler {
		private String url;
		private String id;
		private String password;

		public ConnectorFactoryBuiler url(String url) {
			this.url = url;
			return this;
		}

		public ConnectorFactoryBuiler id(String id) {
			this.id = id;
			return this;
		}

		public ConnectorFactoryBuiler password(String password) {
			this.password = password;
			return this;
		}
		
		public ConnectorFactory build() {
			return new ConnectorFactory(this);
		}
	}
	
	public SMTPConnector getConnector() throws Exception {
		
		if(url.equalsIgnoreCase("smtp.naver.com")) {
			return new NaverMailConnector(url, id, password, connectTime);
		}else if(url.equalsIgnoreCase("smtp.daum.net")) {
			return new DaumMailConnector(url, id, password, connectTime);
		}else if(url.equalsIgnoreCase("smtp.google.com")) {
			return new GoogleMailConnector(url, id, password, connectTime);
		}else {
			throw new Exception("잘못된 smtp host를 입력하였습니다.");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
