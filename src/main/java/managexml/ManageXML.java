package managexml;

import seven.group.Market;
import seven.group.Product;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ManageXML {
	private static final String xmlFilePath = "C:\\\\Users\\oiness\\markets.xml";
	private static final Object lock = new Object();

	public static void CreateXML(List<Market> markets) {
		synchronized (lock) {
			try {
				File file = new File(xmlFilePath);
				if(!file.exists())
					file.createNewFile();

				JAXBContext jaxbContext = JAXBContext.newInstance(Root.class, Market.class, Product.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				jaxbMarshaller.marshal(new Root(markets), file);

			} catch (JAXBException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static Root ReadXML() {
		synchronized (lock) {
			try {
				File file = new File(xmlFilePath);
				if (!file.exists())
					return new Root();
				JAXBContext jaxbContext = JAXBContext.newInstance(Root.class, Market.class, Product.class);

				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				return (Root) jaxbUnmarshaller.unmarshal(file);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static boolean AddProduct(Market market, Product product) {
		Root root = ManageXML.ReadXML();
		// TODO: Exception handling
		if (root == null)
			return false;
		List<Market> markets = root.getMarkets();
		for (int i = 0; i < markets.size(); i++) {
			if (markets.get(i).getId() == market.getId()) {
				long id = markets.get(i).getProduct().get(markets.size()-1).getId();
				product.setId(id+1);
				markets.get(i).getProduct().add(product);
				ManageXML.CreateXML(markets);
				return true;
			}
		}
		return false;
	}

	public static boolean AddMarket(Market market) {
		Root root = ManageXML.ReadXML();
		// TODO: Exception handling
		if (root == null)
			return false;

		List<Market> markets = root.getMarkets();
		long id = markets.get(markets.size()-1).getId();
		market.setId(id+1);

		markets.add(market);
		ManageXML.CreateXML(markets);
		return true;
	}

	public static boolean ModifyMarket(Market market) {
		Root root = ManageXML.ReadXML();
		// TODO: Exception handling
		if (root == null)
			return false;
		List<Market> markets = root.getMarkets();
		for (int i = 0; i < markets.size(); i++) {
			if (markets.get(i).getId() == market.getId()) {
				markets.set(i, market);
				ManageXML.CreateXML(markets);
				return true;
			}
		}
		return false;
	}

	public static boolean DeleteMarket(long id) {
		Root root = ManageXML.ReadXML();
		// TODO: Exception handling
		if (root == null)
			return false;
		List<Market> markets = root.getMarkets();
		for (int i = 0; i < markets.size(); i++) {
			if (markets.get(i).getId() == id) {
				markets.remove(i);
				ManageXML.CreateXML(markets);
				return true;
			}
		}
		return false;
	}
}
