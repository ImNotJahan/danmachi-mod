package imnotjahan.mod.danmachi.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * String to dictionary, dictionary to string
 *
 * Dict = Dictionary
 * */
public class STDTS
{
    /**
     * This is just for testing
     */
    public static void main(String[] args) throws IOException
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
    }


    /**
     * SI = String, Integer
     */
    public static String SIDictToString(Map<String, Integer> dictionary)
    {
        StringBuilder stringedDict = new StringBuilder();
        for(String key : dictionary.keySet())
        {
            stringedDict.append(key).append(":").append(dictionary.get(key)).append(",");
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
            String[] splitKAV = keyAndValue.split(":");
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
}
