package utils;
import static utils.VehicleValidations.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.app.core.Color;
import com.app.core.Vehicle;

import custom_exceptions.VehicleValidationException;

public class CollectionUtils {
	public static List<Vehicle> populateVehicles() throws ParseException, VehicleValidationException {
		ArrayList<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(new Vehicle("abc-12345", Color.BLACK, 55000, validateDate("25-5-2022")));
		vehicles.add(new Vehicle("abc-12340", Color.WHITE, 57000, validateDate("2-5-2022")));
		vehicles.add(new Vehicle("abc-12343", Color.RED, 51000, validateDate("12-6-2022")));
		vehicles.add(new Vehicle("abc-12356", Color.WHITE, 54000, validateDate("1-5-2022")));
		vehicles.add(new Vehicle("abc-12350", Color.WHITE, 65000, validateDate("21-5-2022")));
		vehicles.add(new Vehicle("abc-12349", Color.RED, 59000, validateDate("12-6-2022")));
		vehicles.add(new Vehicle("abc-12351", Color.WHITE, 57000, validateDate("12-6-2022")));
		vehicles.add(new Vehicle("abc-12339", Color.WHITE, 62000, validateDate("12-6-2022")));
		return vehicles;
	}

	// add a static method to delete vehicle details
	public static void deleteVehicleDetails(String chasisNo, List<Vehicle> list) throws VehicleValidationException {
		// wrap chasis no in Vehicle object
		Vehicle v = new Vehicle(chasisNo);
		// API : public boolean remove(Object o)
		
		
		if (!list.remove(v))
			throw new VehicleValidationException("Can't  delete vehicle details : Invalid chasis no");
	}

	// add a static method to apply discount
	public static void applyDiscount(String date, String clr, double discount, List<Vehicle> list)
			throws ParseException, VehicleValidationException {
		Date d1 = validateDate(date);
		Color color = validateColor(clr);
		for (Vehicle v : list)
			if (v.getManufactureDate().before(d1) && v.getColor().equals(color))
				v.setPrice(v.getPrice() - discount);

	
	}
}
