package fixmessage.server;

import quickfix.Application;
import quickfix.ConfigError;
import quickfix.DefaultMessageFactory;
import quickfix.FileLogFactory;
import quickfix.FileStoreFactory;
import quickfix.MessageFactory;
import quickfix.SessionSettings;
import quickfix.SocketAcceptor;

public class StartAcceptor {

	public static void main(String args[]) throws Exception {
		SocketAcceptor socketAcceptor = null;
		try {
            SessionSettings executorSettings = new SessionSettings("./acceptorSettings.txt");
			Application application = new TradeAppAcceptor();
			FileStoreFactory fileStoreFactory = new FileStoreFactory(executorSettings);
			MessageFactory messageFactory = new DefaultMessageFactory();
			FileLogFactory fileLogFactory = new FileLogFactory(executorSettings);
			socketAcceptor = new SocketAcceptor(application, fileStoreFactory, executorSettings, fileLogFactory,
					messageFactory);
			socketAcceptor.start();
		} catch (ConfigError configError) {
			configError.printStackTrace();
		}
	}
}
