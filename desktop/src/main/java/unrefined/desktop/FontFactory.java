package unrefined.desktop;

import unrefined.internal.FontUtils;
import unrefined.io.UnsupportedFormatException;
import unrefined.media.graphics.FontNotFoundException;
import unrefined.util.NotInstantiableError;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public final class FontFactory {

    private FontFactory() {
        throw new NotInstantiableError(FontFactory.class);
    }

    public static final int BOLD_ITALIC = Font.BOLD | Font.ITALIC;
    
    public static final Font DIALOG_FONT = Font.decode(Font.DIALOG);
    public static final Font DIALOG_INPUT_FONT = Font.decode(Font.DIALOG_INPUT);
    public static final Font SANS_SERIF_FONT = Font.decode(Font.SANS_SERIF);
    public static final Font SERIF_FONT = Font.decode(Font.SERIF);
    public static final Font MONOSPACED_FONT = Font.decode(Font.MONOSPACED);

    public static Font getDefaultFont() {
        return FontUtils.getDefaultFont();
    }

    public static Font getFont(String family, int style) throws FontNotFoundException {
        if (family == null) throw new FontNotFoundException();
        if (unrefined.media.graphics.Font.DEFAULT.equals(family)) return getDefaultFont();
        StringBuilder builder = new StringBuilder(family);
        switch (style) {
            case Font.PLAIN -> builder.append(" plain");
            case Font.BOLD -> builder.append(" bold");
            case Font.ITALIC -> builder.append(" italic");
            case BOLD_ITALIC -> builder.append(" bolditalic");
        }
        Font font = Font.decode(builder.toString());
        if (!Font.DIALOG.equals(family) && DIALOG_FONT.equals(font)) throw new FontNotFoundException();
        else return font;
    }

    public static Font readFont(File input) throws IOException {
        Objects.requireNonNull(input);
        try {
            return Font.createFont(Font.TRUETYPE_FONT, input);
        } catch (FontFormatException e) {
            try {
                return Font.createFont(Font.TYPE1_FONT, input);
            } catch (FontFormatException ex) {
                throw new UnsupportedFormatException();
            }
        }
    }

    public static Font readFont(InputStream input) throws IOException {
        Objects.requireNonNull(input);
        try {
            return Font.createFont(Font.TRUETYPE_FONT, input);
        } catch (FontFormatException e) {
            try {
                return Font.createFont(Font.TYPE1_FONT, input);
            } catch (FontFormatException ex) {
                throw new UnsupportedFormatException();
            }
        }
    }

}
