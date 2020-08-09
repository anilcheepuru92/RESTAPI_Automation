package restassuredhelper;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import io.restassured.http.ContentType;


@RunWith(Parameterized.class) //1st Step
public class TestPostWithDataDriver extends BaseClass{

	//Use case: We can pass multiple sets of data to perform Post request
	//This can be achieved using Parameterized concept of JUnit
	//We need to use @RunWith(Parameterized.class) before the class declaration statement
	
	//2nd Step
	/*@Parameter(value=1)
	public int parameterOne;
	@Parameter(value=0)
	public int parameterTwo;*/
	
	//3rd Step
	//The modifiers of this method should be public static. Otherwise the test will fail with Initialization error
	//The rows of Object[][] represent number of sets of data, columns represent number of parameter
	//i.e. 2 rows -> 2 sets of data, 2 columns -> 2 parameter
	/*@Parameters
	public static Collection<Object[]> getData()
	{
		Object[][] data = new Object[][]{{1,2},{3,4}};
		return Arrays.asList(data);
	}*/
	
	@Parameter
	public LaptopBag bag;
	
	
	@Parameters
	public static ArrayList<LaptopBag> getTestDataList()
	{
		return getLaptopBagObject();
	}
	
	@Test
	public void testPostWithDynamicData()
	{
		//System.out.println(parameterOne+"==="+parameterTwo);
		//getLaptopBagObject();
		given()
		.log()
		.body()
		.accept(ContentType.XML)
		.with()
		.contentType(ContentType.XML)
		.body(bag)
		.post("/add")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.body("Laptop.Id", equalTo(bag.getId()));
	}
	
	//1. Read the data from file
	//2. Store the data in ArrayList
	//3. Create the object of laptop bag class
	//4. Create the object of laptop bag class for every entry inside the ArrayList
	//5. Use the Parameterized concept to pass the object to post request
	
	private static ArrayList<LaptopBag> getLaptopBagObject()
	{
		ArrayList<LaptopBag> laptopBagList = new ArrayList<>();
		ArrayList<String> testData = getTestData();
		for(String entry: testData)
		{
			LaptopBag laptopBag = new LaptopBag();
			if(null!=entry)
			{
				laptopBagList.add(getLaptopBag(entry));
			}
			
		}
		return laptopBagList;
	}

	private static LaptopBag getLaptopBag(String entry) {
		LaptopBag laptopBag = new LaptopBag();
		String[] data = entry.split(",");
		laptopBag.setBrandName(data[0]);
		laptopBag.setId(data[data.length-2]);
		laptopBag.setFeature(getFeatures(data));
		laptopBag.setLaptopName(data[data.length-1]);
		return laptopBag;
	}
	
	
	private static Features getFeatures(String[] data) {
		Features features = new Features();
		ArrayList<String> feature = new ArrayList();
		for(int i=1; i< data.length-2; i++)
		{
			if(null != data[i])
			{
				feature.add(data[i]);
			}
		}
		features.setFeature(feature);
		return features;
	}

	private static ArrayList<String> getTestData()
	{
		ArrayList<String> testData = new ArrayList<>();
		String str = "";
		//BufferedReader br = new BufferedReader(new FileReader(new File("")));
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\Electrania.com\\workspace\\rest-api-helper\\testdata\\testdata.txt")));
			do{
				str = br.readLine();
				if(null !=str)
				{
					testData.add(str);
				}
			}
			while(str!=null);
			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testData;
	}
}
