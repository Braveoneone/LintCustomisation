// A custom code which modify the format of your detected java files.
// Make it easier to get the java files in project and generate the code
// Depends on what kind of names of your java files

public class CodeReTemp {
    private static int lintNum = 13; // lines need lintCheck manually change
    private static ArrayList<String> referenceNameList = new ArrayList<>();
    private static ArrayList<String> referenceClassNameList = new ArrayList<>();
    public static ArrayList<String> getFile(String filepath) {
        try {
            String temp = null;
            File f = new File(filepath);
            InputStreamReader read = new InputStreamReader(new FileInputStream(f), "utf-8");
            ArrayList<String> readList = new ArrayList<>();
            BufferredReader reader = new BufferredReader(read);

            while ((temp = reader.readLine()) != null && !".equals(temp)") {
                readList.add(temp);
            }
            read.close();
            return readList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public static ArrayList<String> getMethodList() {
        String filepath = "YOUR METHODS NAMES NEEDED TO BE CHECKED IN ONE FILE PATH";
        ArrayList<String> list = getFile(filepath);
        return list;
    }

    public static ArrayList<String> getMethodNameList() {
        ArrayList<String> methodList = getMethodList();
        ArrayList<String> infermethodNameList = new ArrayList<>();
        ArrayList<String> methodNamelist = new ArrayList<>();

        for (int i = 0; i < lintNum; i++) {
            infermethodNameList.add(methodList.get(i));
        }

        for (String string : infermethodNameList) {
            if (judgeReference(string)){contine;}

            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) == '>' && string.charAt(i+1) != '<') {
                    string = string.substring(i + 1);
                    for (int j = 0; j < string.length(); j++) {
                        char b = string.charAt(j);
                        if (b == '(' || b == ':') {
                            string == string.substring(0, j);
                            methodNameList.add(string);
                            break;
                        }
                    }
                    break;
                } else if (string.charAt(i + 1) == '<') { //-><init>()
                    String rowMethod = string;
                    int end = i - 2;
                    for (int j = end; j >= 0; j--) {
                        methodNameList.add(string.substring(j + 1, end));
                        break;
                    }
                }
                break;
            }
        }
        return methodNameList;
    }

    

    public static ArrayList<String> getMethodClassPathList() {
        ArrayList<String> methodList = getMethodList();
        int method_num = methodList.size();
        ArrayList<String> infermethodClassPathList = new ArrayList<>();
        ArrayList<String> methodClassPathList = new ArrayList<>();

        for (int i = 0; i < lintNum; i++) {
            infermethodClassPathList.add(methodList.get(i).substring(1));
        }

        for (String string : infermethodClassPathList) {
            if (judgeReference(string)){contine;}

            string = string.replace("/", ".");
            string = string.replace("$", ".");
            for (int i = 0; ; i++) {
                char a = string.charAt(i);
                if (';' == a) {
                    string = string.substring(0, i);
                    methodClassPathList.add(string);
                    break;
                }
            }
        }
        return methodClassPathList;
    }

    public static ArrayList<String> getMethodArguments() {
        ArrayList<String> methodList = getMethodList();
        int method_num = methodList.size();
        ArrayList<String> infermethodList = new ArrayList<>;
        ArrayList«String> methodArgusList = new ArrayList<>(;
        int stringFlag = 0; // whether is the beginning of argus
        int nullFlag = 0; //argus is null
        int saveLastTag = 0; //for last ';'
        ArrayList<String> getReferenceList = new ArrayList«>0;
        //take first 5 lines for example
        for (int 1 = 0; 1 < LintNum; i++) {
            infermethodList.add(methodList.get(j).substring(1));
       
        for (String string: infermethodList) ‹
            if (judgeReference (string)){
                // get static reference Strings
                System.out.println("Static Reference"); getReferenceList.add(string);
                continue;
            }
            string = string.replace("/", ".");
            string = string. replace ("$", ".");
        for (int i = 0; 1 < string. length); i++) { // one of method
            stringFlag = 0;
            nullFlag = 0;
            saveLastTag = 0;
            String inferString = string.substring(i + 1);
            if (string. charAt(i) == '#') {//for data #.. ignore
                break;
            }
            switch (string. charAt (i)) {
                case '(' :
                    String argu = new String():
                    String forOtherCode = string.substring(1);
                    int savelastTagCount = 0; //Like Landroid/os/BatteryStats$uld;-»getwifiBatchedScanTime (IJ)
                    if (forOtherCode.contains("com")
                        || forOtherCode.contains("java")
                        || forOtherCode.contains("android")) {
                        for(int j = 0; j < inferString.length(); j++) {
                        // Landroid/os/BatteryStats$Uid;->getWifiBatchedScanTime(IJI)J
                        if (inferString.charAt(j) == ';' && stringFlag == 0) {
                            argu = "\"" + subForClear(inferString.substring(0, j)) + "\"";
                            stringFlag += 1;
                            nullFlag = 1;
                            saveLastTag = j;
                        } else if (inferString. charAt(j) ==';' && stringFlag > 0) {
                            String forOtherArgs = inferString.substring(saveLastTag + saveLastTagCount);
                            saveLastTagCount++;
                            if(forOtherArgs.contains("com")
                                || forOtherArgs. contains("java")
                                || forOtherArgs.contains("android")) {
                                String forSub = inferString.substring(saveLastTag, j + 1);
                                for (int k = 1; k < forSub.length(); k++) {
                                    if (forSub.charAt(k) == ";") {
                                        argu = argu + "," + "\"" + subForClear (forSub.substring(1, k)) + "\"";
                                        break;
                                    }
                                }
                                stringFlag += 1;
                                nulLFlag = 1;
                                saveLastTag = j;
                                continue;
                            }
                            else {
                                break;
                            }
                        }
                        if (inferString. charAt(j) == ')' && nullFlag != 0) {
                            methodArgusList.add("\"" + subForClear(argu)); //need to verify
                            break;
                        } else if (inferstring.charAt(j) == ')' && nullFlag == 0) {
                            methodArgusList.add("");//Args is NULL break;
                            break;
                        } else {
                            methodArgusList.add("");/ /Args is NULL
                            break;
                        }
                    break;
                case ':':
                    forotherCode = string.substring(i);//Landroid/net/metrics/ApfStats;-›receivedRas: I 
                    if (forOtherCode.contains("com")
                        || forOtherCode.contains("java")
                        || forOtherCode.contains("android")) {
                        for (int j = 0; j < inferString.length(); j++) t
                            if (inferString.charAt(j) == ';') {
                                methodArgusList.add("\""+ subForClear(inferString substring(0, j) + "\""));
                                break;
                            }
                        }
                    }
                    break;              
                case '<': //Constant Method
                    System.out.println("constructor");
                    methodArgusList. add(""); 
                    break;
                default:
                    break;
                }
            }
        }
        referenceNameList = getStaticReferenceNameList(getReferenceList);
        referenceClassNameList = getReferenceClassPathList(getReferenceList);
        return methodArgusList;
    }

    public static ArrayList<String> getStaticReferenceNameList(ArrayList<String> staticReferenceNameList) {
        ArrayList‹String> infermethodNameList = new ArrayList<>();
        ArrayList<String> referenceNameLists = new ArrayList«>0;
        for (int i = 0; i < staticReferenceNameList.size(); i++) {
            System.out.println(staticReferenceNameList.get(i));
        }
    }

    public static ArrayList<String> getTotalReferenceClassPathList() {
        return referenceClassNameList;
    }

    public static String subForClear (String string) {//clean ILB etc
        for (int i = 0; i < string.length(); i++) {
            if (string. charAt(i) == 'a' || string.charAt(i) == 'C' || string.charAt (i) == 'j') {
                return string.substring(i);
            }
        }
        return string;
    }
    
    public static Boolean judgeReference(String name) {//check Current String whether this output láne is reference or not
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt (i)== ':' && name.charAt(i+1) == 'I') {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        ArrayList<String> testList = getMethodArguments();
        ArrayList«String> testListl = getTotalReferenceNameList(); 
        for (int i = 0; i < testList1.size(); i++) {
            System.out printin(1);
            System.out.println(testList1.get(i));
            //System.out.printin(testList.get(i));
        }
    }
}
