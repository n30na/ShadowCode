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

}
