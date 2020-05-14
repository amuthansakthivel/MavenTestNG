# MavenTestNG

Features Supported by Framework :
1. Parallel execution with multiple browsers.
2. Capability to run in docker with selenium grid
3. Capability to run in Zalenium.
4. Data driven tests from Excel using testng dataprovider.
5. Integrated with Jenkins effortlessly.
6. Extent reporting which works in parallel runs.

Other Details:
An simple robust framework for selenium build with java. It will generate the extent report with the screenshots appended to the report.
Easily customisable according to an project need from config.properties.

Test Cases Run Mode can be selected easily from Excel.

TestData from the excel sheet is fetched with the help of dataprovider and storing it in hashmap for easier processing.

Annotation transformer helps in setting the annotations for the test cases at run time including the ability to re-run the failed scripts

Easily usable framework with all the reusable methods were stored inside com.utils package

How to use this framework?
1. Clone the repository to your workspace.
2. Open the testdata.xlsx under the src/test/resources folder
3. In the RunManager sheet -->Choose the test cases you want to run by choosing yes
4. In the testdata sheet --->Choose the test data you want to pass to the testcase from excel sheet.
5. The data from the excel sheet will be passed to the test method as a hashtable.
6. Run the testng.xml file. You can even run as mvn test which will trigger the testng.xml

How the framework works?

1. AnnotationTransformer class which implements IAnnotationTransformer is reponsible for reading the data from RunManager sheet in testdata.xlsx. It sets the annotation of the test methods like description,enabled, priority, dataprovider values read from the excel dynamically.

Things to note : Test name in the first column of the excel sheet should match with atleast an @Test available in test classes mentioned in the testng.xml

All the tests will have the same dataprovider in the TestUtils class. For example the test1 in RunManager sheet of testdata.xlsx will take the data from TestData sheet which have row where the testname is test1. If there are multiple rows with test1 as test name , framework will consider it as this as multiple iterations for a test case.

How to run my scripts in local?
1. Open the TestRunDetails.properties file under src/test/resources and change the run mode to local.

How to run my scripts in Docker which is hosted in my local system?
1. Open the TestRunDetails.properties file under src/test/resources and change the run mode to remote and RemoteMode as Selenium or Zalenium.

How to run my scripts in Docker which is hosted in some system connected in the same network in office?
1. Open the TestRunDetails.properties file under src/test/resources and change the RemoteUrl to the ipaddress. So your remote URL should look like http://ipaddressofthemachine:4444/wd/hub

Please feel free to raise issues in github or contact me mech.amuthansakthivel@gmail.com
