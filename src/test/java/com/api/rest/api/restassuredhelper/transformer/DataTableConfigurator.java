package com.api.rest.api.restassuredhelper.transformer;

import java.util.Locale;
import java.util.Map;
import io.cucumber.core.api.TypeRegistry;
import io.cucumber.core.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;
import io.cucumber.datatable.TableEntryTransformer;

public class DataTableConfigurator implements TypeRegistryConfigurer {

	@Override
	public void configureTypeRegistry(TypeRegistry typeRegistry) {
		TableEntryTransformer<User> transformer = new TableEntryTransformer<User>(){
			
		@Override
		public User transform(Map<String, String> entry) throws Throwable {
			System.out.println(entry.toString());
			return new User("nameuser","wordpass");
		}
		};
		DataTableType tableType = new DataTableType(User.class, transformer);
		typeRegistry.defineDataTableType(tableType);
	}

	@Override
	public Locale locale() {
		return Locale.ENGLISH;
	}
}
