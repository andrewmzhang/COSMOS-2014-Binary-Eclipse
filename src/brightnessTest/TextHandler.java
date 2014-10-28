/* Code by Andrew Zhang
 * This segment just handles some text and puts it onto the application face
 * rather than the console. Helps with user friendly GUI. 
 */

package brightnessTest;

import java.awt.TextArea;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

public class TextHandler extends OutputStream {
    private JTextArea OutGoing;

    public TextHandler(JTextArea textArea) {
        this.OutGoing = textArea;
    }

    @Override
    public void write(int e) throws IOException {
        OutGoing.append(String.valueOf((char) e));
        OutGoing.setCaretPosition(OutGoing.getDocument().getLength());
    }
}
