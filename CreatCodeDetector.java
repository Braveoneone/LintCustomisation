//import "..."
import javax.lang.model.element.Modifier;
"""
This Class implements automatically generate the methodDetector for java files in projects.
"""
public class CreateCodeDetector {

    // define the method Detector java Code and generated the code automatically
    public void createMethodDetector(String name, String classPath, @Nullable String args, Boolean exJudge) throws IOException {
        String longName = toUP(name) + "MethodNameEgDetector";
        FieldSpec ISSUE = FieldSpec.builder(Issue.class, "ISSUE", Modifier.PUBLIC, Modifier.STATIC,Modifier.FINAL)
            .initializer("Issue.create(\n" + 
                "\"find the using of " + toUP(name) + \",\n" +
                "briefDescription" + ",\n" +
                "explanation" + ",\n" +
                "com.android.tools.lint.detector.api.Category.create(\"ISSUE_MESSAGE\", \"ISSUE_NUMBER\") ,\n" + //define the issue message and issue number by yourself
                "6" + ",\n" +
                "com.android.tools.lint.detector.api.Severity.ERROR ,\n" +
                "new com.android.tools.lint.detector.api.Implementation(" + longName + ".class, com.android.tools.lint.detector.api.Scope.JAVA_FILE_SCOPE \n"
            ")")

        MethodSpec methodBody = MethodSpec.methodBuilder("getApplicableMethodNmaes")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Nullable.class)
                .addAnnotation(Override.class)
                .returns(ParameterizedTypeName.get(List.class, String.class))
                .addStatement(" return java.util.Arrays.asList(\"" +name + "\")")
                .build();

        ParameterSpec context = ParameterSpec.builder(JavaContext.class, "context")
                    .addAnnotation(NotNull.class)
                    .build();

        ParameterSpec node = ParameterSpec.builder(UCallExpression.class, "node")
    
        String state = new Striing();

        if(args.isEmpty()) {
            // the method does not have arguments
            state = "if(context.getEvaluator().methodMatches(method, " + "\"" +
                        classPath + "\"" + "," +
                        exJudge + ")) {" +
                        "context.report(ISSUE, node, context.getLocation(node), \"
                        Find the" + name + "\");}";

        }
        else {
            // the method has argument(s)
            state = "if(context.getEvaluator().methodMatches(method, " + "\"" +
                        classPath + "\"" + "," +
                        exJudge + "," + args + ")) {" +
                        "context.report(ISSUE, node, context.getLocation(node), \"
                        Find the" + name + "\");}";

        }
    
        ParameterSpec method = ParameterSpec.builder(PsiMethod.class, "method")
                    .addAnnotation(NotNull.class)
                    .build();
        
        MethodSpec visitMethodCall = MethodSpec.methodBuilder("visitMethodCall")
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(Override.class)
                    .returns(void.class)
                    .addParamter(context)
                    .addParamter(node)
                    .addParamter(method)
                    .addStatement(state)
                    .build();
    
        TypeSpec.Build builder = TypeSpec.classBuilder(longName)
                    .addModifiers(Modifier.PUBLIC)
                    .addField(ISSUE)
                    .addMethod(methodBody)
                    .addMethod(visitMethodCall)
                    .superclass(Detector.class)
                    .addSuperinterface(Detector.UastScanner.class);
        
        // Generate the methodDetector Java Code
        JavaFile javaFile = JavaFile.builder("com.lint.detector.uastscanner", builder.build()).build();

        // Set the generated directory by your self
        File file = new File("Path_of_LintDetector") 
        if(!file.exists()) {
            file.mkdirs();
            System.out.println("mk dir finished");

        }
        javaFile.writeTo(file);
    }

    //Depend on your code, define other Detector generator by your self
    public void createReferDetector(String name, String classPath) throws IOException {
        String longName = toUP(name) + "ReferenceNameEgDetector";
        FieldSpec ISSUE = FieldSpec.builder(Issue.class, "ISSUE", Modifier.PUBLIC, Modifier.STATIC,Modifier.FINAL)
            .initializer("Issue.create(\n" + 
                "\"find the using of " + toUP(name) + \",\n" +
                "com.android.tools.lint.detector.api.Category.create(\"ISSUE_MESSAGE\", \"ISSUE_NUMBER\") ,\n" + //define the issue message and issue number by yourself
                "com.android.tools.lint.detector.api.Severity.ERROR ,\n" +
                "new com.android.tools.lint.detector.api.Implementation(" + longName + ".class, com.android.tools.lint.detector.api.Scope.JAVA ,\n"
            ")")

        MethodSpec methodBody = MethodSpec.methodBuilder("getApplicableMethodNmaes")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Nullable.class)
                .addAnnotation(Override.class)
                .returns(ParameterizedTypeName.get(List.class, String.class))
                .addStatement(" return java.util.Arrays.asList(\"" +name + "\")")
                .build();

        ParameterSpec context = ParameterSpec.builder(JavaContext.class, "context")
                    .addAnnotation(NotNull.class)
                    .build();

        ParameterSpec node = ParameterSpec.builder(UCallExpression.class, "node")
    
        String state = new Striing();

        if(args.isEmpty()) {
            // the method does not have arguments
            state = "if(context.getEvaluator().methodMatches(method, " + "\"" +
                        classPath + "\"" + "," +
                        exJudge + ")) {" +
                        "context.report(ISSUE, node, context.getLocation(node), \"
                        Find the" + name + "\");}";

        }
        else {
            // the method has argument(s)
            state = "if(context.getEvaluator().methodMatches(method, " + "\"" +
                        classPath + "\"" + "," +
                        exJudge + "," + args + ")) {" +
                        "context.report(ISSUE, node, context.getLocation(node), \"
                        Find the" + name + "\");}";

        }
    
        ParameterSpec method = ParameterSpec.builder(PsiMethod.class, "method")
                    .addAnnotation(NotNull.class)
                    .build();
        
        MethodSpec visitMethodCall = MethodSpec.methodBuilder("visitMethodCall")
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(Override.class)
                    .returns(void.class)
                    .addParamter(context)
                    .addParamter(node)
                    .addParamter(method)
                    .addStatement(state)
                    .build();
    
        TypeSpec.Build builder = TypeSpec.classBuilder(longName)
                    .addModifiers(Modifier.PUBLIC)
                    .addField(ISSUE)
                    .addMethod(methodBody)
                    .addMethod(visitMethodCall)
                    .superclass(Detector.class)
                    .addSuperinterface(Detector.UastScanner.class);
        
        // Generate the methodDetector Java Code
        JavaFile javaFile = JavaFile.builder("com.lint.detector.uastscanner", builder.build()).build();

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

    public static void main(String[] args) throws IOException {
        CreateCodeDetector createCodeDetector = new CreateCodeDetector();
        List<String> methodClassList - CodeReTemp.getMethodClassList();

        @Nullable
        List<String> methodArgs = CodeReTemp.getMethodArguments();

        Boolean exJudge = true;

        List<String> methodNameList = CodeReTemp.getMehodNameList();
        List<String> referNameList = CodeReTemp.getReferNameList();
        List<String> referClassList = CodeReTemp.getReferClassList();

        for(int i = 0; i < methodNameList.size(); i++) {
            System.out.println(methodNameList.get(i));
            System.out.println(methodArgs.get(i));
            System.out.println(methodClassList.get(i));
            CreateCodeDetector.createMethodDetector(methodNameList.get(i), methodClassList.get(i), methodArgs.get(i), exJudge);
            
        }

        for(int i = 0; i < referNameList.size(); i++) {
            CreateCodeDetector.createReferDetector(referNameList.get(i), referClassList.get(i));
            
        }

        CreateRegistry createRegistry = new CreateRegistry();
        createRegistry.createRegi(methodNameList, referNameList);

    }
    
}