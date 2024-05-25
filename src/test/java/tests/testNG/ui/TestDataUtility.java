package tests.testNG.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TestDataUtility {

    public static List<Object[]> getTestData() {
        List<Object[]> testData = new ArrayList<>();

        testData.add(new Object[]{"testData1"});
        testData.add(new Object[]{"testData2"});
        testData.add(new Object[]{"testData3"});
        testData.add(new Object[]{"testData4"});
        testData.add(new Object[]{"testData5"});

        return testData;
    }

    public static Stream<String> provideTestData() {
        return Stream.of("testData1", "testData2", "testData3", "testData4", "testData5");
    }
}
