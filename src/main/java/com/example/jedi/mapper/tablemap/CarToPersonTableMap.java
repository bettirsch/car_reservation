package com.example.jedi.mapper.tablemap;

import java.sql.JDBCType;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CarToPersonTableMap {
	public static final CarToPerson CAR_TO_PERSON_TABLE = new CarToPerson();
	public static final SqlColumn<Integer> CAR_ID = CAR_TO_PERSON_TABLE.carId;
	public static final SqlColumn<Integer> PERSON_ID = CAR_TO_PERSON_TABLE.personId;
	
	private CarToPersonTableMap() {}
	
	public static final class CarToPerson extends SqlTable{
		public final SqlColumn<Integer> carId = column("car_id", JDBCType.INTEGER);
		public final SqlColumn<Integer> personId = column("person_id", JDBCType.INTEGER);
		
		protected CarToPerson() {
			super("car_to_person");
		}
		
	} 
}
