import java.util.*;
public class Strings {
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
    }

}