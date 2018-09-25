package managexml;

import exceptions.InternalDBError;
import seven.group.Client;
import seven.group.Market;
import seven.group.Order;
import seven.group.Product;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.List;

public final class ManageXML {
	private static final String xmlFilePath = "C:\\\\Users\\oiness\\markets.xml";
	private static final Object lock = new Object();

	public static void CreateXML(List elements) {
		synchronized (lock) {
			try {
				File file = new File(xmlFilePath);
				Root root = null;

				if(!file.exists()){
					if(!file.createNewFile())
						throw new InternalDBError("There is a problem with our database, please try again in a moment");
				}
				else
					root = ReadXML();

				if (root == null) {
					root = new Root();
				}

				if (elements != null && elements.size() > 0) {
					if (elements.get(0) instanceof Market)		root.setMarkets(elements);
					else if (elements.get(0) instanceof Client) root.setClients(elements);
					else return ;
				} else return ;

				JAXBContext jaxbContext = JAXBContext.newInstance(Root.class, Market.class, Product.class, Client.class, Order.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				jaxbMarshaller.marshal(root, file);

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
					if(!file.createNewFile())
						throw new InternalDBError("There is a problem with our database, please try again in a moment");
					else
						return new Root();
				JAXBContext jaxbContext = JAXBContext.newInstance(Root.class, Market.class, Product.class, Client.class, Order.class);

				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				return (Root) jaxbUnmarshaller.unmarshal(file);
			} catch (JAXBException | IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
