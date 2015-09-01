/**
 * Created by neona on 8/24/2015.
 */
import com.ShadowCode.ShadowrunDefs.*;
import com.google.protobuf.CodedInputStream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.String;

public class ShadowCode2 {
    static int tabs = 4;  //number of spaces to use for indentation

    public static void main(String[] args) {
        //System.out.println(args[0]);
        SceneDef scene = null;
        StringBuilder outputDocument = new StringBuilder();

        try {
            scene = SceneDef.parseFrom(
                    CodedInputStream.newInstance(
                            new FileInputStream(new File(args[0]))
                    )
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        outputDocument.append(codeBuildStaticDataSection(scene));
        outputDocument.append(codeBuildDynamicDataSection(scene));

        outputDocument.append(codeFromTriggers(scene));


    }

    static String convertToFunction(String inputName) {
        String outputName = new String();

        // use lazy/fast method with character operations for roughing
        // will be replaced with table lookup
        boolean paren = false;
        for(int i = 0; i < inputName.length(); i++) {
            switch (inputName.charAt(i)) {
                case '(':
                    paren = true;
                    outputName = outputName + '_';
                    break;
                case ')':
                    paren = false;
                    break;
                case ' ':
                    break;
                default:
                    outputName = outputName + inputName.charAt(i);
                    break;
            }

        }
        return outputName;
    }

    static String convertFromFunction(String inputName) {
        return inputName;
    }

    static StringBuilder codeFromTriggers(SceneDef scene) {
        StringBuilder output = new StringBuilder();

        if(scene.getTriggersCount() > 0) {
            for (TsTriggerDef trigger : scene.getTriggersList()) {
                output.append(codeFromTrigger(trigger));
            }
        }

        return output;
    }

    static StringBuilder codeFromTrigger(TsTriggerDef trigger) {
        StringBuilder innerCode = new StringBuilder();
        StringBuilder output;

        if(trigger.hasEvents()) innerCode.append(codeFromTriggerEvent(trigger.getEvents()));
        if(trigger.hasConditions()) innerCode.append(codeFromTriggerConditions(trigger.getConditions()));
        if(trigger.hasActions()) innerCode.append(codeFromTriggerActions(trigger.getActions()));
        if(trigger.hasElseActions()) innerCode.append(codeFromTriggerElseActions(trigger.getElseActions()));

        output = codeBlock(innerCode, "Trigger", trigger.getName());

        return output;
    }

    static StringBuilder codeFromTriggerEvent(TsBlock event) {
        StringBuilder output = new StringBuilder();


        return output;
    }

    static StringBuilder codeFromTriggerConditions(TsBlock conditions) {
        StringBuilder output = new StringBuilder();


        return output;
    }

    static StringBuilder codeFromTriggerActions(TsBlock actions) {
        StringBuilder output = new StringBuilder();


        return output;
    }

    static StringBuilder codeFromTriggerElseActions(TsBlock elseActions) {
        StringBuilder output = new StringBuilder();


        return output;
    }

    static StringBuilder codeFromBlock(TsBlock block) {
        StringBuilder output = new StringBuilder();


        return output;
    }

    static StringBuilder codeFromOps(TsCall ops) {   //ops is the container for functions - do not break down further
        StringBuilder output = new StringBuilder();


        return output;
    }

    static StringBuilder codeBuildDynamicDataSection(SceneDef scene) {
        StringBuilder output = new StringBuilder();
        StringBuilder innerCode = new StringBuilder();

        innerCode.append(codeFromTags(scene));
        innerCode.append(codeFromEvents(scene));
        innerCode.append(codeFromVariables(scene));

        output.append(codeBlock(innerCode, "DynamicData"));

        return output;
    }

    static StringBuilder codeBuildStaticDataSection(SceneDef scene) {
        StringBuilder output = new StringBuilder();
        StringBuilder innerCode = new StringBuilder();

        innerCode.append(codeFromCharacters(scene));
        innerCode.append(codeFromGoals(scene));
        innerCode.append(codeFromRegions(scene));

        output.append(codeBlock(innerCode, "StaticData"));

        return output;
    }

    static StringBuilder codeFromEvents(SceneDef scene) {
        StringBuilder output = new StringBuilder();


        return output;
    }

    static StringBuilder codeFromTags(SceneDef scene) {
        StringBuilder output = new StringBuilder();


        return output;
    }

    static StringBuilder codeFromGoals(SceneDef scene) {
        StringBuilder output = new StringBuilder();


        return output;
    }

    static StringBuilder codeFromVariables(SceneDef scene) {
        StringBuilder innerCode = new StringBuilder();
        StringBuilder output = new StringBuilder();

        if(scene.getVariablesCount() > 0) {
            for (TsVariant variable : scene.getVariablesList()) {
                switch (variable.getVariablerefValue().getTypeName()) {
                    case "int":
                        innerCode.append("Int ");
                        break;
                    case "bool":
                        innerCode.append("Bool ");
                        break;
                    case "float":
                        innerCode.append("Float ");
                        break;
                    case "string":
                        innerCode.append("String ");
                        break;
                    default:
                        innerCode.append("Unknown ");
                        break;
                }
                innerCode.append(variable.getVariablerefValue().getName() + " = ");

                switch (variable.getVariablerefValue().getTypeName()) {
                    case "int":
                        innerCode.append(variable.getIntValue());
                        break;
                    case "bool":
                        innerCode.append(variable.getBoolValue());
                        break;
                    case "float":
                        innerCode.append(variable.getFloatValue());
                        break;
                    case "string":
                        innerCode.append('"' + variable.getStringValue() + '"');
                        break;
                    default:
                        innerCode.append("null");
                        break;
                }
                innerCode.append(";\n");
            }

            output = codeBlock(innerCode, "Variables");

        }

        return output;
    }

    public static StringBuilder codeFromRegions(SceneDef scene) {
        StringBuilder output = new StringBuilder();


        return output;
    }

    public static StringBuilder codeFromCharacters(SceneDef scene) {
        StringBuilder output = new StringBuilder();


        return output;
    }

    static StringBuilder codeBlock(StringBuilder innerCode) {
        StringBuilder output = new StringBuilder();
        String tabBlock = new String(new char[tabs]).replace('\0', ' ');

        output.append("{\n");


        for(int i = 0;i < innerCode.length(); i++) {
            if(innerCode.charAt(i) == '\n' && i+1 != innerCode.length()) {
                innerCode.insert(i+1,tabBlock);
            }
        }
        output.append(innerCode);

        output.append("}\n");

        return output;
    }

    static StringBuilder codeBlock(StringBuilder innerCode, String blockTitle) {
        StringBuilder output = new StringBuilder();

        output.append(blockTitle + ' ');
        output.append(codeBlock(innerCode));

        return output;
    }

    static StringBuilder codeBlock(StringBuilder innerCode, String blockTitle, String blockIdentifier) {
        StringBuilder output = new StringBuilder();

        output.append(blockTitle + ' ' + '"' + blockIdentifier + '"' + ' ');
        output.append(codeBlock(innerCode));

        return output;
    }
}
