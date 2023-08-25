package de.emir.rcp.util;

import net.sourceforge.jeuclid.context.LayoutContextImpl;
import net.sourceforge.jeuclid.context.Parameter;
import net.sourceforge.jeuclid.converter.Converter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import uk.ac.ed.ph.snuggletex.SnuggleEngine;
import uk.ac.ed.ph.snuggletex.SnuggleInput;
import uk.ac.ed.ph.snuggletex.SnuggleSession;
import uk.ac.ed.ph.snuggletex.internal.util.XMLUtilities;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LatexRender {

    private static final SnuggleEngine engine = new SnuggleEngine();
    private static final Map<String, BufferedImage> cache = new HashMap<>();

    /**
     * Converts a MathML compatible tex string to a buffered image. This is mainly used for units
     * to display symbols.
     * <p>
     * Thanks to https://tex.stackexchange.com/a/41634
     * <p>
     * List of alternative Latex Tools: https://stackoverflow.com/questions/456002/displaying-fancy-equations-with-java
     *
     * @param tex math ml compatible tex string
     * @return image representation of input
     * @throws IOException
     */
    public static BufferedImage createImage(String tex) throws IOException {

        if (cache.containsKey(tex)){
            BufferedImage result = cache.get(tex);
            if (result != null){
                return result;
            }
        }

        SnuggleSession session = engine.createSession();
        /* Parse some very basic Math Mode input */
        SnuggleInput input = new SnuggleInput(
                "$$ " + tex + " $$ "
        );
        session.parseInput(input);

        // create xml document from snuggle output
        DocumentBuilder documentBuilder = XMLUtilities.createNSAwareDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Element temporaryRoot = document.createElement("root");
        document.appendChild(temporaryRoot);
        session.buildDOMSubtree(temporaryRoot);
        
        LayoutContextImpl lc = (LayoutContextImpl) LayoutContextImpl.getDefaultLayoutContext();

        BufferedImage result = Converter.getInstance()
                .render(
                		document,
                        lc.setParameter(Parameter.MATHCOLOR, UIManager.getColor("TextArea.foreground"))
                );

        cache.put(tex, result);

        return result;
    }

    /**
     * Same as createImage, but creates an ImageIcon object instead.
     *
     * @param tex math ml compatible tex string
     * @return image icon representation of input
     * @throws IOException
     */
    public static ImageIcon createIcon(String tex) throws IOException {
        return new ImageIcon(createImage(tex));
    }
}
