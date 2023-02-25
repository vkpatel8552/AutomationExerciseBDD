package test.AutomationExerciseBDD.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.Properties;

public class Efficacies {
	

	public String properties;
	public InputStream systemPropertyInputStream;
	public String packageName = null;

	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	
	public InputStream getSystemPropertyInputStream() {
		return systemPropertyInputStream;
	}
	public void setSystemPropertyInputStream(InputStream systemPropertyInputStream) {
		this.systemPropertyInputStream = systemPropertyInputStream;
	}

	/**
	 * Method to load the property file
	 * data as mentioned in params
	 * 
	 * @param fileName valid file name
	 *            
	 * @return properties
	 * @throws IOException
	 *             throws exception
	 */
	public synchronized Properties loadPropertyFile(String fileName) throws IOException
	{		
		fileName = System.getProperty("user.dir") + File.separator+"src"+File.separator+"main"+File.separator+fileName;
		File file = new File(fileName); 
		FileInputStream fileInput = null; 
		fileInput = new FileInputStream(file);
		Properties properties= new Properties(); //load properties file 
		properties.load(fileInput);
		return properties;
	}
	
	/**
	 * Loads the property file from resources
	* @param fileName Provide valid file address
	* @throws IOException throws IO Exception
	* @return properties            
	 */
	public Properties loadPropertiesFromResources(String fileName) throws IOException
	{
		fileName = System.getProperty("user.dir") + File.separator+"src"+File.separator+"main"+File.separator+
				"resources"+File.separator+fileName;
		File file = new File(fileName); 
		FileInputStream fileInput = null; 
		fileInput = new FileInputStream(file);
		Properties properties= new Properties(); //load properties file 
		properties.load(fileInput);
		return properties;
		
	}
	
	/**
	 * reads the property file
	* @param filename provide valid filename
	* @param Key provide valid key
	* @return string value of property
	 */
	public String readPropFile(String filename, String Key) {
		Properties prop = new Properties();
		try {
				prop.load(new FileInputStream(filename));
				return prop.getProperty(Key).toString();
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * generates random number
	* @return number generated 
	 
	 */
	public long generateRandomNumber() {
		return Instant.now().toEpochMilli();
	}	
	
//	
//	/**
//	 * converts gson object to json object
//	* @param object provide valid object
//	* @return string JSON String
//	 */
//	
//	public String gsonToJson(Object object) {
//		Gson gson = new Gson();
//		return gson.toJson(object);
//	}
//	
//
//	/**
//	 * reads json file
//	* @param fileName provide valid filename
//	* @return Map testdata map
//	  * @throws Exception  throws Exception
//	 */	
//	public Map<String, String> readJsonFile(String fileName) throws Exception{
//		String methodName = null;
//		String filePath = null;
//	    StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
//	    for (int i = 0; i < stacktrace.length; i++) {
//	    	if(stacktrace[i].getMethodName().equals("readJsonFile")) {
//	    		methodName = stacktrace[i+1].getMethodName();
//	    		break;
//	    	}
//		}
//	   	StackTraceElement packagePath = null;
//	    StackTraceElement[] packageStackTraceArray = Thread.currentThread().getStackTrace();
//	    for (int i = 0; i < packageStackTraceArray.length; i++) {
//	    	if(packageStackTraceArray[i].toString().contains("com.apttus.sfdc") 
//	    	|| packageStackTraceArray[i].toString().contains("com.conga")) {
//	    		packagePath = packageStackTraceArray[i];
//	    		break;
//	    	}
//	    }	
//	    String [] arrayPackageName = packagePath.toString().split("\\.");
//	    String packageName = arrayPackageName[3];		
//	    filePath = System.getProperty("user.dir") + File.separator+"src"+File.separator+
//				"main"+File.separator+"resources"+File.separator+ packageName+File.separator+fileName;
//		JsonElement root = new JsonParser().parse(new FileReader(filePath));
//		JsonObject jsonObject = root.getAsJsonObject();		
//		JsonElement some = jsonObject.get(methodName);
//		JsonObject testData = some.getAsJsonObject();
//		Map<String, String> testDataMap = new HashMap<String, String>();
//		for (Map.Entry<String,JsonElement> entry : testData.entrySet()) {
//			testDataMap.put(entry.getKey().toString(), entry.getValue().getAsString());
//		}
//		return testDataMap;
//	}
//	
//	
//
//	/**
//	 * reads json elements
//	* @param fileName provide valid filename
//	*  @param elementName  provide element name
//	* @return Map testDataMap
//	  * @throws Exception throws Exception
//	 */
//	public Map<String, String> readJsonElement(String fileName, 
//		String elementName) throws Exception{
//		String filePath = null;
//	   	StackTraceElement packagePath = null;
//	    StackTraceElement[] packageStackTraceArray = Thread.currentThread().getStackTrace();
//	    for (int i = 0; i < packageStackTraceArray.length; i++) {
//	    	if(packageStackTraceArray[i].toString().contains("com.apttus.sfdc") 
//	    	|| packageStackTraceArray[i].toString().contains("com.conga")) {
//	    		packagePath = packageStackTraceArray[i];
//	    		break;
//	    	}
//		}
//	    String [] arrayPackageName = packagePath.toString().split("\\.");
//	    String packageName = arrayPackageName[3];		
//	    filePath = System.getProperty("user.dir") + File.separator+"src"+File.separator+
//				"main"+File.separator+"resources"+File.separator+ packageName+File.separator+fileName;	
//		JsonElement root = new JsonParser().parse(new FileReader(filePath));
//		JsonObject jsonObject = root.getAsJsonObject();		
//		JsonElement some = jsonObject.get(elementName);
//		JsonObject testData = some.getAsJsonObject();
//		Map<String, String> testDataMap = new HashMap<String, String>();
//		for (Map.Entry<String,JsonElement> entry : testData.entrySet()) {
//			testDataMap.put(entry.getKey().toString(), entry.getValue().getAsString());
//		}
//		return testDataMap;		
//	}
//	
//	

//    
//
//	/**
//	 * Downloads the file
//	* @param downloadLink provide the download link 
//	* @param downloadDestn provide download destination
//	 * @throws Exception throws IO Exception
//	 */
//    public void downloadFile(String downloadLink, String downloadDestn) throws Exception {
//
//      	  File dir = new File(downloadDestn.substring(0,downloadDestn.lastIndexOf(File.separator)));
//            // create output directory if it doesn't exist
//          if(!dir.exists()) dir.mkdirs();
//          
//          URL website = new URL(downloadLink);
//          ReadableByteChannel readableByteChannel = Channels.newChannel(website.openStream());
//          FileOutputStream fileOutputStream = new FileOutputStream(downloadDestn);
//          fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
//          fileOutputStream.close();
//          readableByteChannel.close();
//          Reporter.log("Downloaded chrome driver to " +downloadDestn);
//
//   } 
//   
//    /**
//	 * Extracts the zip file
//	* @param zipFilePath provide zipfile path
//	*  @param destDir  provide the destination address
//	 * @throws Exception throws exception
//	 */
//        public void unzip(String zipFilePath, String destDir) throws Exception{
//        File dir = new File(destDir);
//        // create output directory if it doesn't exist
//        if(!dir.exists()) dir.mkdirs();
//        FileInputStream fileInputStream;
//        
//        byte[] buffer = new byte[1024];
//            fileInputStream = new FileInputStream(zipFilePath);
//            ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);
//            ZipEntry zipEntry = zipInputStream.getNextEntry();
//            while(zipEntry != null){
//                String fileName = zipEntry.getName();
//                File newFile = new File(destDir + File.separator + fileName);
//                Reporter.log("Unzipping to "+newFile.getAbsolutePath());
//               
//                //create directories for sub directories in zip
//                new File(newFile.getParent()).mkdirs();  
//                FileOutputStream fos = new FileOutputStream(newFile);
//                int len;
//                while ((len = zipInputStream.read(buffer)) > 0) {
//                fos.write(buffer, 0, len);
//                }
//                fos.close();
//                zipInputStream.closeEntry();
//                zipEntry = zipInputStream.getNextEntry();
//            }
//            zipInputStream.closeEntry();
//            zipInputStream.close();
//            fileInputStream.close();
//        
//        
//    }
//        
//        public ArrayList<String> readValues(ArrayList<String> namesList){
//        	ArrayList<String> readValueList = new ArrayList<String>();
//        	for(String read : namesList) {
//        		readValueList.add(new String(Base64.getDecoder().decode(read)));
//        	}
//        	return readValueList;
//        }
//        
//	public Map<String, String> readJsonElementInOrder(String fileName, String elementName) throws Exception {
//		String filePath = null;
//		StackTraceElement packagePath = null;
//		StackTraceElement[] packageStackTraceArray = Thread.currentThread().getStackTrace();
//		for (int i = 0; i < packageStackTraceArray.length; i++) {
//			if (packageStackTraceArray[i].toString().contains("com.apttus.sfdc") 
//			 || packageStackTraceArray[i].toString().contains("com.conga")) {
//				packagePath = packageStackTraceArray[i];
//				break;
//			}
//		}
//		String[] arrayPackageName = packagePath.toString().split("\\.");
//		String packageName = arrayPackageName[3];
//		filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator
//				+ "resources" + File.separator + packageName + File.separator + fileName;
//		JsonElement root = new JsonParser().parse(new FileReader(filePath));
//		JsonObject jsonObject = root.getAsJsonObject();
//		JsonElement some = jsonObject.get(elementName);
//		JsonObject testData = some.getAsJsonObject();
//		Map<String, String> testDataMap = new LinkedHashMap<String, String>();
//		for (Map.Entry<String, JsonElement> entry : testData.entrySet()) {
//			testDataMap.put(entry.getKey().toString(), entry.getValue().getAsString());
//		}
//		return testDataMap;
//	}
//
//	public Map<String, String> readJsonFileInOrder(String fileName) throws Exception {
//		String methodName = null;
//		String filePath = null;
//		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
//		for (int i = 0; i < stacktrace.length; i++) {
//			if (stacktrace[i].getMethodName().equals("readJsonFileInOrder")) {
//				methodName = stacktrace[i + 1].getMethodName();
//				break;
//			}
//		}
//		StackTraceElement packagePath = null;
//		StackTraceElement[] packageStackTraceArray = Thread.currentThread().getStackTrace();
//		for (int i = 0; i < packageStackTraceArray.length; i++) {
//			if (packageStackTraceArray[i].toString().contains("com.apttus.sfdc") 
//			 || packageStackTraceArray[i].toString().contains("com.conga")) {
//				packagePath = packageStackTraceArray[i];
//				break;
//			}
//		}
//		String[] arrayPackageName = packagePath.toString().split("\\.");
//		String packageName = arrayPackageName[3];
//		filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator
//				+ "resources" + File.separator + packageName + File.separator + fileName;
//		JsonElement root = new JsonParser().parse(new FileReader(filePath));
//		JsonObject jsonObject = root.getAsJsonObject();
//		JsonElement some = jsonObject.get(methodName);
//		JsonObject testData = some.getAsJsonObject();
//		Map<String, String> testDataMap = new LinkedHashMap<String, String>();
//		for (Map.Entry<String, JsonElement> entry : testData.entrySet()) {
//			testDataMap.put(entry.getKey().toString(), entry.getValue().getAsString());
//		}
//		return testDataMap;
//	}
}