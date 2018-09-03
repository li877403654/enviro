package pub.functions;

import org.apache.commons.io.IOUtils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

/**
 * describe: Created by IntelliJ IDEA.
 * @author zzl
 * @version 2010-8-9
 */
public class IoFuncs {

	public static void tryClose(Closeable closable) {
		if (closable == null) {
			return;
		}
		try {
			closable.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String readResourceAsText(String path) {
        InputStream is = IoFuncs.class.getResourceAsStream(path);
        try {
            List<String> lines = IOUtils.readLines(is, "utf-8");
            return StrFuncs.join(lines, "\n");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
