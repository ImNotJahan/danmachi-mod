package imnotjahan.mod.danmachi.keybinds;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class Keybinds
{
    public static KeyBinding status;
    public static KeyBinding magic;

    public static void RegisterKeybinds()
    {
        status = addKeybind("key.status", Keyboard.KEY_V);
        magic = addKeybind("key.magic", Keyboard.KEY_R);
    }

    private static KeyBinding addKeybind(String description, int keyCode)
    {
        KeyBinding binding = new KeyBinding(description, keyCode, "key.categories.danmachi");
        ClientRegistry.registerKeyBinding(binding);

        return binding;
    }
}
