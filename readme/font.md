## Font load bugfix

### ✅ **How to Properly Load a `.ttf` Font in Java**

```java
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;

public class FontLoader {
    public static void main(String[] args) throws Exception {
        File fontFile = new File("path/to/Roboto-Regular.ttf"); // must be TTF or OTF

        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        Font derived = font.deriveFont(24f); // Set size

        // Optional: register to system graphics
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        System.out.println("Font loaded: " + derived.getFontName());
    }
}
```

---

### 🛠️ **Workarounds / Fixes**

1. ✅ **Make sure the font is `.ttf` or `.otf`**

   * Not `.woff`, `.woff2`, `.pfa`, or `.pbf` — those are **not supported**.
   * Many Google Fonts ZIPs include **both TTF and web fonts** — pick the `.ttf` only.

2. 🔎 **Try re-downloading from fonts.google.com**

   * Click "Download family"
   * Extract the `.ttf` inside, not the web versions

3. ❌ Do **not** use font URLs or compressed fonts (like WOFF2) directly

---

### 💡 Tip: Detect bad files

If the `.ttf` fails but you suspect it's okay, try opening it:

* In a font viewer (on your OS)
* Or in a vector editor (e.g., Figma, Illustrator)

If those work but Java doesn't: it may be a **variable font**, which Java 8–17 doesn’t support well.

---

Would you like a code snippet that auto-loads all `.ttf` files from a folder?
