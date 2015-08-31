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
    public static void main(String[] args) {
        //System.out.println(args[0]);
        SceneDef scene = null;
        try {
            scene = SceneDef.parseFrom(
                    CodedInputStream.newInstance(
                            new FileInputStream(new File(args[0]))
                    )
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    for (TsTriggerDef trigger:scene.getTriggersList()) {

        }

    }

    public String convertToFunction(String inputName) {
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

    public String convertFromFunction(String inputName) {
        return inputName;
    }

    public String codeFromTrigger(TsTriggerDef trigger) {
        String output = new String();


        return output;
    }

    public String codeFromTriggerEvent(TsBlock event) {
        String output = new String();


        return output;
    }

    public String codeFromTriggerConditions(TsBlock conditions) {
        String output = new String();


        return output;
    }

    public String codeFromTriggerActions(TsBlock actions) {
        String output = new String();


        return output;
    }

    public String codeFromTriggerElseActions(TsBlock elseActions) {
        String output = new String();


        return output;
    }

    public String codeFromBlock(TsBlock block) {
        String output = new String();


        return output;
    }

    public String codeFromOps(TsCall ops) {
        String output = new String();


        return output;
    }

    public String codeBuildDataSection(SceneDef scene) {
        String output = new String();


        return output;
    }

    public String codeFromEvents(SceneDef scene) {
        String output = new String();


        return output;
    }

    public String codeFromTags(SceneDef scene) {
        String output = new String();


        return output;
    }

    public String codeFromGoals(SceneDef scene) {
        String output = new String();


        return output;
    }

    public String codeFromVariables(SceneDef scene) {
        String output = new String();


        return output;
    }


}
