"""
Generating Registry code in bulk
"""

public class CreateRegistry {
    
    public void createRegistry(List<String> methodName, List<String> referName ) throws IOException {
        StringBuilder issueList = new StringBuilder();
        int endFlag = 0;
        for (String methodname : methodName) {
            methodname = toUP(methodname);
            if(endFlag == 0) {
                issueList.append("com.lint.detector.uastscanner." + methodname + "MethodDetector.ISSUE");


            }
            else {
                issueList.append("," + "com.lint.detector.uastscanner" + methodname + "MethodDetector.ISSUE");
            }

            endFlag += 1;

        }
        endFlag = 0;
        for (String refername : referName) {
            methodname = toUP(methodname);
            if(endFlag == 0) {
                issueList.append("com.lint.detector.uastscanner." + refername + "MethodDetector.ISSUE");


            }
            else {
                issueList.append("," + "com.lint.detector.uastscanner" + refername + "MethodDetector.ISSUE");
            }

            endFlag += 1;

        }        

        MethodSpec getIssues = MethodSpec.methodBuilder("getIssues")
                .addModifiers(Modifer.PUBLIC)
                .addAnnotation(NotNull.class)
                .addAnnotation(Override.class)
                .returns(ParameterizedTypeName.get(List.class, Issue.class))
                .addStatement("return java.util.Arrays.asList(" + issueList + ")")
                .build();

        MethodSpec getMinApi = MethodSpec.methodBuilder("getMinApi")
                .addModifiers(Modifer.PUBLIC)
                .addAnnotation(Override.class)
                .returns(int.class)
                .addStatement("return 1")
                .build();
        
        MethodSpec getApi = MethodSpec.methodBuilder("getApi")
                .addModifiers(Modifer.PUBLIC)
                .addAnnotation(Override.class)
                .returns(int.class)
                .addStatement("return com.android.tools.lint.detector.api.ApiKt.CURRENT_API")
                .build();  

        TypeSpec.Build builder = TypeSpec.classBuilder("CodeIssueRegistry")
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(getIssues)
                    .addMethod(getMinApi)
                    .addMethod(getApi)
                    .superclass(IssueRegistry.class);
        
        // Generate the methodDetector Java Code
        JavaFile javaFile = JavaFile.builder("com.lint", builder.build()).build();

        // Set the generated directory by your self
        File file = new File("Path_of_LintDetector") 
        if(!file.exists()) {
            file.mkdirs();
            System.out.println("mk dir finished");

        }
        javaFile.writeTo(file);        
    }

    public static String toUP(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return name;
    }
}
