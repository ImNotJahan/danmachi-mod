package imnotjahan.mod.danmachi.world.structures;

import com.google.common.collect.ImmutableMap;
import imnotjahan.mod.danmachi.Main;
import imnotjahan.mod.danmachi.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.event.RegistryEvent;

import java.util.HashMap;
import java.util.Map;

public class Structures
{
    // Static instance of our structure so we can reference it
    // and use it to make configured structures in STConfiguredStructures
    public static Structure<NoFeatureConfig> RUN_DOWN_HOUSE = new Babel(NoFeatureConfig.CODEC);
    public static IStructurePieceType RDHP = BabelPieces.Piece::new;

    /*
     * Registers the structure itself and sets what its path is. In this case, the
     * structure will have the resourcelocation of structure_tutorial:run_down_house.
     *
     * This is also where the rarity of your structure is set. See the
     * comments in below in new StructureSeparationSettings for details.
     *
     * It is always a good idea to register your Structures so that other mods can
     * use them too directly from the Forge Registry. It great for mod compatibility.
     */
    public static void registerStructures(RegistryEvent.Register<Structure<?>> event) {

        // Registers the structure (our helper method attaches the modid to the front of the Structure's ResourceLocation)
        Main.register(event.getRegistry(), RUN_DOWN_HOUSE, "babel");

        /*
         * IMPORTANT: Once you have set the name for your structure below and distributed your mod,
         * it should NEVER be changed or else it can cause worlds to become corrupted if they generated
         * any chunks with your mod with the old structure name. See MC-194811 in Mojang's bug tracker for details.
         *
         * Forge has an issue report here: https://github.com/MinecraftForge/MinecraftForge/issues/7363
         * Keep watch on that to know when it is safe to remove or change structure's registry names
         */
        registerStructure(
                RUN_DOWN_HOUSE, /* The instance of the structure */
                new StructureSeparationSettings(10 /* maximum distance apart in chunks between spawn attempts */,
                        5 /* minimum distance apart in chunks between spawn attempts */,
                        1234567890 /* this modifies the seed of the structure so no two structures always spawn over each-other. Make this large and unique. */),
                true);



        Structures.registerAllPieces();
    }

    /*
     * Adds the provided structure to the registry, and adds the separation settings.
     * The rarity of the structure is determined based on the values passed into
     * this method in the structureSeparationSettings argument. Called by registerFeatures.
     */
    public static <F extends Structure<?>> void registerStructure(
            F structure,
            StructureSeparationSettings structureSeparationSettings,
            boolean transformSurroundingLand)
    {
        /*
         * We need to add our structures into the map in Structure alongside vanilla
         * structures or else it will cause errors. Called by registerStructure.
         *
         * (If you are using deferred registries, do not put this line inside the deferred method.
         *  Instead, call it anywhere else before you start the configuredstructure registering)
         */
        Structure.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);

        /*
         * Will add land at the base of the structure like it does for Villages and Outposts.
         * Doesn't work well on structure that have pieces stacked vertically.
         */

        /*
         * Adds the structure's spacing into several places so that the structure's spacing remains
         * correct in any dimension or worldtype instead of not spawning.
         *
         * However, it seems it doesn't always work for code made dimensions as they read from
         * this list beforehand. Use the WorldEvent.Load event in StructureTutorialMain to add
         * the structure spacing from this list into that dimension.
         */

        ImmutableMap<Structure<?>, StructureSeparationSettings> defaultStructures = ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
                .putAll(DimensionStructuresSettings.DEFAULTS).put(structure, structureSeparationSettings).build();
        DimensionStructuresSettings.DEFAULTS = defaultStructures;

        if(transformSurroundingLand)
        {
            WorldGenRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(noiseSettings ->
            {
                DimensionStructuresSettings dimensionSettings = noiseSettings.getValue().structureSettings();
                Map<Structure<?>, StructureSeparationSettings> structureConfig = dimensionSettings.structureConfig();
                if (structureConfig instanceof ImmutableMap)
                {
                    Map<Structure<?>, StructureSeparationSettings> tempStructureConfig = new HashMap<>(structureConfig);
                    tempStructureConfig.put(structure, structureSeparationSettings);
                    dimensionSettings.structureConfig = tempStructureConfig;
                }
            });
        }
    }

    /*
     * If you have multiple structures it is helpful to break out the registering of the pieces.
     * If you change the name you register the pieces with and load a world from before the name
     * was changed it will spam errors to the console, so pick a name you like before distributing
     * your mod and don't change it. Called by registerFeatures.
     */
    public static void registerAllPieces() {
        registerStructurePiece(RDHP, new ResourceLocation(Reference.MODID, "rdhp"));
    }

    /*
     * Registers the structures pieces themselves. If you don't do this part, Forge will complain to
     * you in the Console. Called by registerPieces.
     */
    static void registerStructurePiece(IStructurePieceType structurePiece, ResourceLocation rl) {
        Registry.register(Registry.STRUCTURE_PIECE, rl, structurePiece);
    }
}
