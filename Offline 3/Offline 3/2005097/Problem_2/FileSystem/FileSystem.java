package Problem_2.FileSystem;

import Problem_2.Drive.Drive;
import Problem_2.File.File;
import Problem_2.Folder.Folder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileSystem {

    protected String name;
    protected String type;
    protected int size;
    protected String directory="root";
    protected int componentCount;
    protected LocalDateTime creationTime;
    List<Drive> drives=new ArrayList<>();


    public FileSystem(String type)
    {
        this.type=type;

    }



    public void showDetails()
    {
        LocalDateTime currentTime= creationTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy h:mm a");
        String formattedTime = currentTime.format(formatter);

        System.out.println("Name: "+name);
        System.out.println("Type: "+type);
        System.out.println("Size: "+getSize()+" kB");
        System.out.println("Directory: "+directory);
        System.out.println("Component Count: "+getComponentCount());
        System.out.println("Creation Time: "+formattedTime);
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }


    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getComponentCount() {
        int count=0;
        for(int i=0;i<drives.size();i++)
        {
            count+=drives.get(i).getComponentCount();

        }
        return count;

    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public String getDirectory() {
        return directory;
    }


    public  int getSize() {
        int size=0;
        for(int i=0;i<drives.size();i++)
        {
            size+=drives.get(i).getSize();
        }
        return  size;
    };

    public  void delete(String name, boolean isRecursive) {};

    public  void addFile(File f) {
        System.out.println("Permission Denied");
    };
    public final void addDrive(Drive d )
    {
        drives.add(d);
        d.setDirectory(this.directory+":\\"+d.getName());

    }
    public void getList() {
        for (int i = 0; i < drives.size(); i++) {
            Drive f = drives.get(i);
            System.out.println(f.getName() + "    " + f.getSize() + " kB       " + f.getCreationTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        }
    }

    public FileSystem search(String name)
    {
        for(int i=0;i<drives.size();i++)
        {
            if(name.equals(drives.get(i).getName()))
            {
                return drives.get(i);
            }
        }
        return null;
    }
    public  void addFolder(Folder f) {
        System.out.println("Permission Denied");

    };

}
