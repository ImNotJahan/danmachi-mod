package imnotjahan.mod.danmachi.util;

import java.util.HashMap;
import java.util.Map;

/**
 * String to dictionary, dictionary to string
 *
 * Dict = Dictionary
 * */
public class STD
{
    /**
     * This is just for testing
     */
    public static void main(String[] args)
    {
        Map<String, Integer> siDict = new HashMap<String, Integer>()
        {{
            put("one", 1);
            put("two", 2);
            put("three", 3);
        }};
        System.out.println(SIDictToString(siDict));
        System.out.println(SIDictToString(SIStringToDict(SIDictToString(siDict))));

        Map<String, Integer[]> siaDict = new HashMap<String, Integer[]>()
        {{
            put("one through 4", new Integer[]{1, 2, 3, 4});
            put("two and six", new Integer[]{2, 6});
            put("three", new Integer[]{3});
        }};
        System.out.println(SIADictToString(siaDict));
        System.out.println(SIADictToString(SIAStringToDict(SIADictToString(siaDict))));

        Map<String, String> ssDict = new HashMap<String, String>()
        {{
            put("first letter", "a");
            put("5 words", "very very very very words");
            put("three", "3");
        }};
        System.out.println(SSDictToString(ssDict));
        System.out.println(SSDictToString(SSStringToDict(SSDictToString(ssDict))));

        Map<String, double[]> sdaDict = new HashMap<String, double[]>()
        {{
            put("14", new double[]{14, 13, .25D, 4});
            put("second is 5", new double[]{10, 5, .4D, 10});
            put("7 values", new double[]{15, 20, .35D, 3, 8, 2, 1.4D});
        }};
        System.out.println(SDADictToString(sdaDict));
        System.out.println(SDADictToString(SDAStringToDict(SDADictToString(sdaDict))));
    }


    /**
     * SI = String, Integer
     */
    public static String SIDictToString(Map<String, Integer> dictionary)
    {
        StringBuilder stringedDict = new StringBuilder();
        for(String key : dictionary.keySet())
        {
            stringedDict.append(key).append("'").append(dictionary.get(key)).append(",");
        }

        return stringedDict.substring(0, stringedDict.length() - 1);
    }

    /**
     * SI = String, Integer
     */
    public static Map<String, Integer> SIStringToDict(String string)
    {
        Map<String, Integer> remappedDict = new HashMap<>();
        String[] keysAndValues = string.split(",");

        for(String keyAndValue : keysAndValues)
        {
            //KAV = Key and Value
            String[] splitKAV = keyAndValue.split("'");
            remappedDict.put(splitKAV[0], Integer.valueOf(splitKAV[1]));
        }

        return remappedDict;
    }


    /**
     * SIA = String, Integer Array
     */
    public static String SIADictToString(Map<String, Integer[]> dictionary)
    {
        StringBuilder stringedDict = new StringBuilder();
        for(String key : dictionary.keySet())
        {
            StringBuilder stringedArray = new StringBuilder();
            for(Integer number : dictionary.get(key))
            {
                stringedArray.append(number).append("-");
            }
            stringedArray.substring(0, stringedArray.length() - 1);
            stringedDict.append(key).append(":").append(stringedArray.toString()).append(",");
        }

        return stringedDict.substring(0, stringedDict.length() - 1);
    }

    /**
     * SIA = String, Integer Array
     */
    public static Map<String, Integer[]> SIAStringToDict(String string)
    {
        Map<String, Integer[]> remappedDict = new HashMap<>();
        String[] keysAndValues = string.split(",");

        for(String keyAndValue : keysAndValues)
        {
            //KAV = Key and Value
            String[] splitKAV = keyAndValue.split(":");
            String[] splitValues = splitKAV[1].split("-");

            Integer[] integerArray = new Integer[splitValues.length];
            for(int k = 0; k < integerArray.length; k++)
            {
                integerArray[k] = Integer.valueOf(splitValues[k]);
            }

            remappedDict.put(splitKAV[0], integerArray);
        }

        return remappedDict;
    }

    /**
     * SS = String, String
     */
    public static String SSDictToString(Map<String, String> dictionary)
    {
        StringBuilder stringedDict = new StringBuilder();
        for(String key : dictionary.keySet())
        {
            stringedDict.append(key).append(":").append(dictionary.get(key)).append(",");
        }

        return stringedDict.substring(0, stringedDict.length() - 1);
    }


    /**
     * SS = String, String
     */
    public static Map<String, String> SSStringToDict(String string)
    {
        Map<String, String> remappedDict = new HashMap<>();
        String[] keysAndValues = string.split(",");

        for(String keyAndValue : keysAndValues)
        {
            //KAV = Key and Value
            String[] splitKAV = keyAndValue.split(":");
            remappedDict.put(splitKAV[0], splitKAV[1]);
        }

        return remappedDict;
    }

    /**
     * SDA = String, double Array
     */
    public static String SDADictToString(Map<String, double[]> dictionary)
    {
        StringBuilder stringedDict = new StringBuilder();
        for(String key : dictionary.keySet())
        {
            StringBuilder stringedArray = new StringBuilder();
            for(double number : dictionary.get(key))
            {
                stringedArray.append(number).append("-");
            }
            stringedArray.substring(0, stringedArray.length() - 1);
            stringedDict.append(key).append(":").append(stringedArray.toString()).append(",");
        }

        return stringedDict.substring(0, stringedDict.length() - 1);
    }

    /**
     * SDA = String, double Array
     */
    public static Map<String, double[]> SDAStringToDict(String string)
    {
        Map<String, double[]> remappedDict = new HashMap<>();
        String[] keysAndValues = string.split(",");

        for(String keyAndValue : keysAndValues)
        {
            //KAV = Key and Value
            String[] splitKAV = keyAndValue.split(":");
            String[] splitValues = splitKAV[1].split("-");

            double[] doubleArray = new double[splitValues.length];
            for(int k = 0; k < doubleArray.length; k++)
            {
                doubleArray[k] = Double.parseDouble(splitValues[k]);
            }

            remappedDict.put(splitKAV[0], doubleArray);
        }

        return remappedDict;
    }
}
