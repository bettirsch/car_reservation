package com.example.jedi.mapper.tablemap;

import java.sql.JDBCType;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class PersonTableMap {
	public static final Person PERSON_TABLE = new Person();
	public static final SqlColumn<Integer> PERSON_ID = PERSON_TABLE.personId;
	public static final SqlColumn<String> FIRST_NAME = PERSON_TABLE.firstName;
	public static final SqlColumn<String> LAST_NAME = PERSON_TABLE.lastName;

	private PersonTableMap() {}
	
	public static final class Person extends SqlTable {
		public final SqlColumn<Integer> personId = column("person_id", JDBCType.INTEGER);
		public final SqlColumn<String> firstName = column("first_name", JDBCType.VARCHAR);
		public final SqlColumn<String> lastName = column("last_name", JDBCType.VARCHAR);

		public Person() {
			super("person");
		}
	}
}
