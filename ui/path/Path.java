package ui.path;

public class Path {
    public static String path = "ui/img/";
    public static String nameTemplate = "Card_";

    public static enum Directory {
        BOARDS("boards/"),
        CARDS("cards/");
        
        private String dir;
        
        private Directory(String dir) {
            this.dir = dir;
        }
        
        public String getValue() {
            return this.dir;
        }
    }

    public static enum Extensions {
        PNG(".png"),
        JPEG(".jpeg");
        
        private String value;
        
        private Extensions(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return this.value;
        }
    }
}
