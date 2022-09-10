package meteordevelopment.meteorclient.utils.misc;

import static meteordevelopment.meteorclient.MeteorClient.mc;

public class WindowUtils {
    public static class Serta {
        public static void set() {
            setIcon();
            setTitle();
        }

        public static void setIcon() {
            // Use the icon from textures/icons/chat/meteor.png as a InputStream
            mc.getWindow().setIcon(WindowUtils.class.getResourceAsStream("/assets/meteor-client/textures/meteor.png"), WindowUtils.class.getResourceAsStream("/assets/meteor-client/textures/meteor.png"));
        }

        public static void setTitleLoading() {
            mc.getWindow().setTitle("Serta " + Version.getStylized() + " - " + mc.getVersionType() + " " + Version.getMinecraft() + " is being loaded...");
        }

        public static void setTitleLoaded() {
            mc.getWindow().setTitle("Serta " + Version.getStylized() + " - " + mc.getVersionType() + " " + Version.getMinecraft() + " loaded!");
        }

        public static void setTitle() {
            mc.getWindow().setTitle("Serta " + Version.getStylized() + " - " + mc.getVersionType() + " " + Version.getMinecraft());
        }
    }

    public static class Meteor {
        public static void set() {
            setIcon();
            setTitle();
        }

        public static void setIcon() {
            mc.getWindow().setIcon(WindowUtils.class.getResourceAsStream("/assets/meteor-client/textures/meteor.png"), WindowUtils.class.getResourceAsStream("/assets/meteor-client/textures/meteor.png"));
        }

        public static void setTitle() {
            mc.getWindow().setTitle("Serta Client " + Version.getStylized() + " - " + mc.getVersionType() + " " + Version.getMinecraft());
        }
    }
}