import java.util.*;
public class Strings {

    public static String findLongestSubstring(String s1, String s2) {
        int max = 0;
        int count = 0;
        int startIndex = 0;
        String biggerString = null;
        String smallerOrEqualString = null;
        if (s1.length() > s2.length()) {
            biggerString = s1;
            smallerOrEqualString = s2;
        } else {
            biggerString = s2;
            smallerOrEqualString = s1;
        }
        int i = 0;
        int j = 0;
        int finalStartIndex = 0;
        int finalEndIndex = 0;
        int previousStart=0;
        boolean trackFirstMatch = false;
        boolean trackPreviousStart = false;
        while (i < biggerString.length() && j < smallerOrEqualString.length()) {
            if (biggerString.charAt(i) == smallerOrEqualString.charAt(j)) {
                startIndex = i;
                if(trackPreviousStart==false){
                    previousStart=startIndex;
                }
                if(trackFirstMatch==false){
                    finalStartIndex = startIndex;
                    trackFirstMatch=true;
                }
                count++;
                i++;
                j++;
            } else if (count > max) {
                previousStart=finalStartIndex;
                trackPreviousStart = true;
                trackFirstMatch=false;
                max = count;
                finalEndIndex = i - 1;
                i++;
                count=0;
            } else {
                i++;
            }
        }
        //if everything matches, final index = length of shorter/equal string
        if(finalEndIndex==0){
            finalEndIndex=biggerString.length()-1;
        }


        return biggerString.substring(previousStart,finalEndIndex+1);
    }
    //Input:{"root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"}
    //Output:{{"root/a/2.txt","root/c/d/4.txt","root/4.txt"},{"root/a/1.txt","root/c/3.txt}}
    public HashMap<String,ArrayList<String>> findDuplicateFiles(ArrayList<String> list) {
        ArrayList<String> inputList = new ArrayList();
        HashMap<String, ArrayList<String>> result = new HashMap();
        ArrayList<String> duplicateFiles = new ArrayList();
        StringBuilder filePath = new StringBuilder();
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            String[] contents = list.get(i).split(" ");
            for (int j = 1; j < contents.length; j++) {
                String root = contents[0];
                String directory = root + contents[j];
                inputList.add(directory);
            }
        }
        for(int i=0;i<inputList.size();i++){
            int j=0;
            for(j=0;j<inputList.get(i).length();j++) {
                if(inputList.get(i).charAt(j)!='(') {
                    filePath.append(inputList.get(i).charAt(j));
                }
                if (inputList.get(i).charAt(j) == '(') {
                    while (inputList.get(i).charAt(j) != ')') {
                        j++;
                        if(inputList.get(i).charAt(j) != ')'){
                            content.append(inputList.get(i).charAt(j));}
                    }
                    duplicateFiles.add(filePath.toString());
                    result.put(content.toString(),duplicateFiles);
                    filePath= new StringBuilder();
                    content = new StringBuilder();
                }
            }
        }
        return result;
    }



    public int countOccurrences(String pattern, String s){
        int count =0;
        for(int i=0;i<s.length();i++){
            int j=0;
            for(j=0;j<pattern.length();j++){
                if(s.charAt(i)==pattern.charAt(j) && i<s.length()){
                    i++;
                }else{
                    break;
                }
            }
            if(j==pattern.length()){
                count++;
                int index = i-pattern.length();
                System.out.println("match found at index " + index);
            }
        }

        return count;
    }

    public HashMap<String, Integer> categorize(String[] reviews, String[] categories){
        HashMap<String, Integer> result = new HashMap();
        for(int i=0;i<reviews.length;i++){
            for(int c =0;c<categories.length;c++){
                String[] arr = reviews[i].split(" ");
                for(int j=0;j<arr.length;j++){
                    if(arr[j].equals(categories[c])){
                        if(!result.containsKey(categories[c])){
                            result.put(categories[c],1);
                        }else{
                            int count = result.get(categories[c]);
                            result.put(categories[c],++count);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static StringBuilder reverse(String s){
        String[] newS = s.split(" ");
        int start =0;
        int end = newS.length-1;
        while(start<end){
            String temp = newS[start];
            newS[start]= newS[end];
            newS[end]=temp;
            start++;
            end--;
        }
        StringBuilder newString = new StringBuilder(newS.toString());
        return newString;
    }

    public static StringBuilder removeLeadingZeroes(String s){
        StringBuilder newString = new StringBuilder();
        char[] arr = s.toCharArray();
        int i = 0;
        while (arr[i] == '0' && i < arr.length) {
            i++;
        }
        while (i < arr.length) {
            newString.append(arr[i]);
            i++;
        }
        return newString;
    }

    public static void main(String[] args){
        HashMap<String,ArrayList<String>> result = new HashMap();
        Strings string = new Strings();
        String pattern = "how";
        String s = "howmanyhowmanyhow";
        System.out.println(string.countOccurrences(pattern, s));
        ArrayList<String> list = new ArrayList();
        list.add("root/a 1.txt(abcd) 2.txt(efgh)");
        list.add("root/c 3.txt(abcd)");
        list.add("root/c/d 4.txt(efgh)");
        list.add("root 4.txt(efgh)");
        result= string.findDuplicateFiles(list);
        System.out.println(result.entrySet());
        String[] reviews = {"pizza and steak are great", "The fish is overcooked", "The steak was juicy"};
        String[] categories = {"pizza", "fish", "steak"};
        HashMap<String,Integer> map = new HashMap();
        map = string.categorize(reviews, categories);
        System.out.println(map.entrySet());
    }

}