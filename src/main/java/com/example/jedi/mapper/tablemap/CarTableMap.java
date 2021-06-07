package com.example.jedi.mapper.tablemap;

import java.sql.JDBCType;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CarTableMap {
	public static final Car CAR_TABLE = new Car();
	public static final SqlColumn<Integer> CAR_ID = CAR_TABLE.carId;
	public static final SqlColumn<String> NAME = CAR_TABLE.name;
	public static final SqlColumn<String> PLATE_NUMBER = CAR_TABLE.plateNumber;
	public static final SqlColumn<Integer> NR_OF_WHEEL = CAR_TABLE.nrOfWheel;
	
	private CarTableMap() {}
	
	public static final class Car extends SqlTable {
		public final SqlColumn<Integer> carId = column("car_id", JDBCType.INTEGER);
		public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);
		public final SqlColumn<String> plateNumber = column("plate_number", JDBCType.VARCHAR);
		public final SqlColumn<Integer> nrOfWheel = column("nr_of_wheel", JDBCType.INTEGER);

		public Car() {
			super("car");
		}
	}
}
