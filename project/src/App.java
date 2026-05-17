import java.awt.Desktop;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) {
        try {
            Path indexHtml = locateIndexHtml();

            if (indexHtml == null) {
                System.out.println("Site page not found. Open index.html from the repository root.");
                return;
            }

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(indexHtml.toUri());
                System.out.println("Opened site: " + indexHtml.toAbsolutePath());
            } else {
                System.out.println("Desktop browsing is not supported on this machine.");
                System.out.println("Open this file in your browser: " + indexHtml.toAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Path locateIndexHtml() {
        Path[] candidates = new Path[] {
            Paths.get("..", "..", "index.html"),
            Paths.get("..", "index.html"),
            Paths.get("index.html")
        };

        for (Path candidate : candidates) {
            Path normalized = candidate.toAbsolutePath().normalize();
            if (Files.exists(normalized)) {
                return normalized;
            }
        }

        return null;
    }
}
