package meteordevelopment.meteorclient.systems.modules.client;

import meteordevelopment.meteorclient.settings.*;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.utils.render.color.SettingColor;
import meteordevelopment.meteorclient.utils.misc.WindowUtils;

public class ClientSpoof extends Module {
    private final SettingGroup sgVersion = settings.createGroup("Version");
    private final SettingGroup sgWatermark = settings.createGroup("Watermark");
    private final SettingGroup sgChatFeedback = settings.createGroup("Chat Feedback");
    private final SettingGroup sgWindow = settings.createGroup("Window");

    public final Setting<Boolean> version = sgVersion.add(new BoolSetting.Builder()
        .name("enabled")
        .description("Changes the client version.")
        .defaultValue(false)
        .build()
    );

    public final Setting<String> versionText = sgVersion.add(new StringSetting.Builder()
        .name("text")
        .description("The text to replace the version with.")
        .defaultValue("0.4.7")
        .build()
    );

    // Watermark

    public final Setting<Boolean> watermark = sgWatermark.add(new BoolSetting.Builder()
        .name("enabled")
        .description("Changes the watermark client name.")
        .defaultValue(false)
        .build()
    );

    public final Setting<String> watermarkText = sgWatermark.add(new StringSetting.Builder()
        .name("text")
        .description("The text to replace the watermark with.")
        .defaultValue("Serta Client")
        .build()
    );

    public final Setting<Boolean> watermarkMeteorIcon = sgWatermark.add(new BoolSetting.Builder()
        .name("meteor-icon")
        .description("Changes the watermark icon to Meteor.")
        .defaultValue(true)
        .build()
    );

    // Chat Feedback

    public final Setting<Boolean> chatFeedback = sgChatFeedback.add(new BoolSetting.Builder()
        .name("enabled")
        .description("Changes the chat feedback client name.")
        .defaultValue(false)
        .build()
    );

    public final Setting<String> chatFeedbackText = sgChatFeedback.add(new StringSetting.Builder()
        .name("text")
        .description("The text to replace chat feedback with.")
        .defaultValue("Serta")
        .build()
    );

    public final Setting<Boolean> chatFeedbackChangeTextColor = sgChatFeedback.add(new BoolSetting.Builder()
        .name("change-color")
        .description("Changes the chat feedback client name color.")
        .defaultValue(true)
        .build()
    );

    public final Setting<SettingColor> chatFeedbackTextColor = sgChatFeedback.add(new ColorSetting.Builder()
        .name("color")
        .description("The text color of the chat feedback.")
        .defaultValue(new SettingColor(145, 61, 226))
        .build()
    );

    public final Setting<Boolean> chatFeedbackMeteorIcon = sgChatFeedback.add(new BoolSetting.Builder()
        .name("meteor-icon")
        .description("Changes the chat feedback icon to Meteor.")
        .defaultValue(true)
        .build()
    );

    // Window

    public final Setting<Boolean> window = sgWindow.add(new BoolSetting.Builder()
        .name("enabled")
        .description("Changes the title and icon of the window.")
        .defaultValue(false)
        .onChanged(window -> {
            titleChanged();
            iconChanged();
        })
        .build()
    );

    public final Setting<Boolean> windowMeteorTitle = sgWindow.add(new BoolSetting.Builder()
        .name("meteor-title")
        .description("Changes the title of the window to Serta.")
        .defaultValue(true)
        .onChanged(title -> titleChanged())
        .build()
    );

    public final Setting<Boolean> windowMeteorIcon = sgWindow.add(new BoolSetting.Builder()
        .name("meteor-icon")
        .description("Changes the window icon to Serta.")
        .defaultValue(true)
        .onChanged(icon -> iconChanged())
        .build()
    );

    public ClientSpoof() {
        super(Categories.Client, "client-spoof", "Allows you to change the name of the client.");
    }

    @Override
    public void onActivate() {
        // Icon
        if (window.get() && windowMeteorIcon.get()) WindowUtils.Meteor.setIcon();

        // Window Title
        if (window.get() && windowMeteorTitle.get()) WindowUtils.Meteor.setTitle();
    }

    @Override
    public void onDeactivate() {
        // Icon & Window Title
        WindowUtils.Serta.set();
    }

    private void titleChanged() {
        if (window.get()) {
            if (windowMeteorTitle.get()) WindowUtils.Meteor.setTitle();
            else WindowUtils.Serta.setTitle();
        } else WindowUtils.Serta.setTitle();
    }

    private void iconChanged() {
        if (window.get()) {
            if (windowMeteorIcon.get()) WindowUtils.Meteor.setIcon();
            else WindowUtils.Serta.setIcon();
        } else WindowUtils.Serta.setIcon();
    }

    public boolean changeVersion() {
        return isActive() && version.get();
    }

    public boolean changeWatermark() {
        return isActive() && watermark.get();
    }

    public boolean changeWatermarkIcon() {
        return isActive() && watermark.get() && watermarkMeteorIcon.get();
    }

    public boolean changeChatFeedback() {
        return isActive() && chatFeedback.get();
    }

    public boolean changeChatFeedbackColor() {
        return isActive() && chatFeedback.get() && chatFeedbackChangeTextColor.get();
    }

    public boolean changeChatFeedbackIcon() {
        return isActive() && chatFeedback.get() && chatFeedbackMeteorIcon.get();
    }

    public boolean changeWindowTitle() {
        return isActive() && window.get() && windowMeteorTitle.get();
    }

    public boolean changeWindowIcon() {
        return isActive() && window.get() && windowMeteorIcon.get();
    }
}