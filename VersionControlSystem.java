package task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
class FileVersion 
{
    private String content;
    public FileVersion(String content) 
    {
        this.content = content;
    }
    public String getContent() 
    {
        return content;
    }
}
class FileDelta 
{
    private String content;
    public FileDelta(String content) 
    {
        this.content = content;
    }
    public String getContent() 
    {
        return content;
    }
}
class File 
{
    private List<FileVersion> versions;
    private Map<Integer, FileDelta> deltas; 
    public File(String baseContent) 
    {
        versions = new ArrayList<>();
        deltas = new HashMap<>();
        addVersion(baseContent);
    }
    public void addVersion(String content)
    {
        int versionNumber = versions.size() + 1;
        versions.add(new FileVersion(content));
        if (versionNumber > 1) 
        {
            FileVersion previousVersion = versions.get(versionNumber - 2);
            String deltaContent = computeDelta(previousVersion.getContent(), content);
            deltas.put(versionNumber, new FileDelta(deltaContent));
        }
    }
    public FileVersion getVersion(int versionNumber) 
    {
        return versions.get(versionNumber - 1);
    }

    public FileVersion getLatestVersion() 
    {
        return versions.get(versions.size() - 1);
    }

    public FileVersion getVersionWithDeltas(int versionNumber) 
    {
        StringBuilder content = new StringBuilder(getVersion(versionNumber).getContent());
        for (int i = versionNumber; i > 1; i--) 
        {
            FileDelta delta = deltas.get(i);
            content.insert(0, delta.getContent());
        }
        return new FileVersion(content.toString());
    }

    private String computeDelta(String previousContent, String newContent) 
    {
        StringBuilder delta = new StringBuilder();
        for (int i = 0; i < newContent.length(); i++) 
        {
            if (i >= previousContent.length() || newContent.charAt(i) != previousContent.charAt(i)) 
            {
                delta.append(newContent.charAt(i));
            }
        }
        return delta.toString();
    }
}

public class VersionControlSystem
{
    public static void main(String[] args) 
    {
        File file = new File("Hello, World!"); 
        file.addVersion("Hello, Java!"); 
        file.addVersion("Hello, Java Developers!");
        FileVersion version1 = file.getVersionWithDeltas(1);
        FileVersion version2 = file.getVersionWithDeltas(2);
        FileVersion version3 = file.getVersionWithDeltas(3);
        System.out.println("Version 1: " + version1.getContent());
        System.out.println("Version 2: " + version2.getContent());
        System.out.println("Version 3: " + version3.getContent());
    }
}

