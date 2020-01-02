package cloud.timo.cloudtablist.managers;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Sebastian
 * Created in 20.08.2018
 */
public class FileManager {
    private File configsDirectory;
    private File configFile;
    private Map<String, Object> config;

    public void load() {
        try {
            configsDirectory = new File("plugins/CloudTablist/");
            configsDirectory.mkdirs();

            this.configFile = new File(configsDirectory, "config.yml");
            configFile.createNewFile();
            config = (Map<String, Object>) loadYaml(configFile);
            if (config == null) config = new LinkedHashMap<>();
            Map<String, Object> configDefaults = (Map<String, Object>) new Yaml().load(this.getClass().getResourceAsStream("/config.yml"));
            for (String key : configDefaults.keySet()) {
                if (!config.containsKey(key)) config.put(key, configDefaults.get(key));
            }
            saveConfigs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveConfigs() throws IOException {
        saveYaml(config, configFile);
    }

    public Object loadYaml(File file) throws IOException {
        FileReader reader = new FileReader(file);
        Object data = new Yaml().load(reader);
        reader.close();
        return data;
    }

    public void saveYaml(Object data, File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        new Yaml(dumperOptions).dump(data, writer);
        writer.close();
    }

    public String getMessage(String path) {
        return getConfig().get(path).toString();
    }

    public File getConfigFile() {
        return configFile;
    }

    public File getConfigsDirectory() {
        return configsDirectory;
    }

    public Map<String, Object> getConfig() {
        return config;
    }

}


