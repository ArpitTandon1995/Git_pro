package utils;
import java.text.ParseException;
import java.util.List;
import java.util.Date;

import com.app.core.Color;
import com.app.core.Vehicle;

import custom_exceptions.VehicleValidationException;
import static com.app.core.Vehicle.sdf;

public class VehicleValidations {
	public static final double MIN_PRICE;
	public static final double MAX_PRICE;
	static {
		MIN_PRICE = 10000;
		MAX_PRICE = 100000;
	}

	// add public static method for validating all i/ps
	public static Vehicle validateAllInputs(String chasisNo, String color, double price, String manufactureDate,
			List<Vehicle> showroom) throws VehicleValidationException, ParseException {
		// chk for dup vehicle based upon chasis no
		checkForDup(chasisNo, showroom);
		Color clr = validateColor(color);
		double validPrice = validatePrice(price);
		// => valid clr n price
		// update actual price=base price + color specific addtional cost
		validPrice += clr.getAdditionalCost();
		Date validDate = validateDate(manufactureDate);
		// => al i/ps valid
		return new Vehicle(chasisNo, clr, validPrice, validDate);
	}

	// add public static method for checking vehicle dups
	public static void checkForDup(String chasisNo, List<Vehicle> showroom) throws VehicleValidationException {
		// showrooom : {v1(abc-1234,....) ,v2(abc-1235,....) ,v3(abc-1236,....)
		// ,v4(abc-1237,....) ,null.....}
		// chasisNo : abc-1236
		Vehicle newVehicle = new Vehicle(chasisNo);
		if (showroom.contains(newVehicle))
			throw new VehicleValidationException("Dup Chasis No!!!!!!!!!!");
		System.out.println("no dup chasis no");

	}

	// add public static method for validating price
	public static double validatePrice(double price) throws VehicleValidationException {
		if (price < MIN_PRICE || price > MAX_PRICE)
			throw new VehicleValidationException("Invalid Price !!!!!!!!!!!!");
		// => price : valid
		return price;
	}

	// add public static method for validating color
	public static Color validateColor(String clr) throws IllegalArgumentException {
		return Color.valueOf(clr.toUpperCase());
	}

	// add public static method to validate manufacture date : manufactureDate :
	// must be in current financial year(1st Apr 2022 ---31st Mar 2023)
	// method decl :
	public static Date validateDate(String date) throws ParseException, VehicleValidationException {
		// 1. parsing (conversion String ---> Date)
		Date d1 = sdf.parse(date);
		// => conversion success ---> proceed to validations
		Date start = sdf.parse("1-4-2022");
		Date end = sdf.parse("31-3-2023");
		// chk range
		if (d1.before(start) || d1.after(end))
			throw new VehicleValidationException("Manufacturing Date out of range!!!!!!!!!!!!!!");
		return d1;
	}

	// add a public static method to find a vehicle in a showroom , by it's chasis
	// no
	public static Vehicle findByChasisNo(String chasisNo, List<Vehicle> showroom)
			throws VehicleValidationException {
		//chasisNo : abc-1230
		// showrooom : {v1(abc-1234,....) ,v2(abc-1235,....) ,v3(abc-1236,....)
		// ,v4(abc-1237,....) ,null.....}
		// create vehicle class instance to wrap : PK (i.e chsisNO)
		Vehicle testVehicle = new Vehicle(chasisNo);
		// API of AL : public int indexOf(E element)
		int index = showroom.indexOf(testVehicle);
		if (index == -1) // vehicle not found
			throw new VehicleValidationException("Vehicle with chasis no " + chasisNo + " not found!!!!!!!!!!!");
		//=> vehicle found : index
		//API of AL : public E get(int index)
		return showroom.get(index);
	}

}


	

